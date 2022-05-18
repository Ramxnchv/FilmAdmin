package es.ucm.fdi.iw.controller;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.time.LocalDate;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.method.annotation.StreamingResponseBody;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import javax.persistence.EntityManager;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.transaction.Transactional;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;

import es.ucm.fdi.iw.LocalData;
import es.ucm.fdi.iw.model.Cine;
import es.ucm.fdi.iw.model.Pelicula;
import es.ucm.fdi.iw.model.Sala;
import es.ucm.fdi.iw.model.Transferable;
import es.ucm.fdi.iw.model.User;
import es.ucm.fdi.iw.model.User.Role;

/**
 *  Controlador para las peliculas
 */

@Controller
@RequestMapping("peliculas")
public class PeliculaController {

	private static final Logger log = LogManager.getLogger(PeliculaController.class);

    @Autowired
	private EntityManager entityManager;

    @Autowired
	private LocalData localData;

    @ResponseStatus(value = HttpStatus.FORBIDDEN, reason = "No eres administrador.") // 403
	public static class NoEresAdminException extends RuntimeException {
	}

	@GetMapping("/")
    public String index(Model model) {

        List<Pelicula> sesiones = (List<Pelicula>) entityManager.createNamedQuery("Pelicula.getAll", Pelicula.class).getResultList();

        List<Cine> cines = (List<Cine>) entityManager.createNamedQuery("Cine.FindAll", Cine.class).getResultList();

        model.addAttribute("sesiones", sesiones);
        model.addAttribute("cines", cines);
        model.addAttribute("dia", LocalDate.now());
       
        return "peliculas";
    }

    @GetMapping("/{id}")
    public String infoPelicula(@PathVariable long id, Model model) {

        Pelicula pel = entityManager.find(Pelicula.class, id);

        List<Pelicula> sesiones = (List<Pelicula>) entityManager.createNamedQuery("Pelicula.getAll", Pelicula.class).getResultList();

        List<Cine> cines = (List<Cine>) entityManager.createNamedQuery("Cine.FindAll",Cine.class).getResultList();

        model.addAttribute("sesiones", sesiones);
        model.addAttribute("pelicula", pel);
        model.addAttribute("cines", cines);
        model.addAttribute("dia", LocalDate.now());

        return "pelicula";
    }

    @GetMapping(path = "salas/{id}", produces = "application/json")
	@ResponseBody // para indicar que no devuelve vista, sino un objeto (jsonizado)
	public List<Sala.Transfer> retrieveMovies(@PathVariable long id,HttpSession session) {
        List<Sala> salas = (List<Sala>) entityManager.createNamedQuery("Sala.findByCine", Sala.class)
        .setParameter("cineId", id)
        .getResultList();
        for(Sala s: salas){
            log.info(s.getNombre());
        }
		return salas.stream().map(Transferable::toTransfer).collect(Collectors.toList());
	}

    @PostMapping(path = "/{id}", consumes = "application/json")
	@ResponseBody
	@Transactional
	public String postPelicula(
			HttpServletResponse response,
			@PathVariable long id,
			@RequestBody JsonNode o,
			Model model, HttpSession session) throws IOException {

		User requester = (User) session.getAttribute("u");
        if (!requester.hasRole(Role.ADMIN)) 
		    throw new NoEresAdminException();
		
		Pelicula target = null;
		if (id == -1 && requester.hasRole(Role.ADMIN)) {
			target = new Pelicula();
			entityManager.persist(target);
			entityManager.flush();
			id = target.getId(); // retrieve assigned id from DB
		}

		// retrieve requested user
		target = entityManager.find(Pelicula.class, id);
		model.addAttribute("pelicula", target);

		if(o.get("titulo")!=null){
			String titulo = o.get("titulo").asText(); 
			if (titulo != null) {target.setTitulo(titulo);}
		}
		if(o.get("duracion")!=null){
			int duracion = o.get("duracion").asInt();
			if (duracion != 0) {target.setDuraccion(duracion);}
		}
		if(o.get("genero")!=null){
			String genero = o.get("genero").asText();
			if (genero != null) {target.setGenero(genero);}
		}
		
		ObjectMapper mapper = new ObjectMapper();
		ObjectNode n = mapper.createObjectNode();
		n.put("id", target.getId());
		String json = mapper.writeValueAsString(n);

		return json;
	}

    /**
	 * Returns the default profile pic
	 * 
	 * @return
	 */
	private static InputStream defaultPic() {
		return new BufferedInputStream(Objects.requireNonNull(
				PeliculaController.class.getClassLoader().getResourceAsStream(
						"static/img/default-pic.jpg")));
	}

	/**
	 * Downloads a profile pic for a user id
	 * 
	 * @param id
	 * @return
	 * @throws IOException
	 */
	@GetMapping("{id}/pic")
	public StreamingResponseBody getPic(@PathVariable long id) throws IOException {
		File f = localData.getFile("pelicula", ""+id+".jpg");
		InputStream in = new BufferedInputStream(f.exists() ? new FileInputStream(f) : PeliculaController.defaultPic());
		return os -> FileCopyUtils.copy(in, os);
	}

	/**
	 * Uploads a profile pic for a user id
	 * 
	 * @param id
	 * @return
	 * @throws IOException
	 */
	@PostMapping("{id}/pic")
	public String setPic(@RequestParam("photo") MultipartFile photo, @PathVariable long id,
			HttpServletResponse response, HttpSession session, Model model) throws IOException {

		Pelicula target = entityManager.find(Pelicula.class, id);
		model.addAttribute("pelicula", target);

		// check permissions
		User requester = (User) session.getAttribute("u");
        if (!requester.hasRole(Role.ADMIN)) 
		    throw new NoEresAdminException();

		log.info("Updating photo for pelicula {}", id);
		File f = localData.getFile("pelicula", ""+id+".jpg");
		if (photo.isEmpty()) {
			log.info("failed to upload photo: emtpy file?");
		} else {
			try (BufferedOutputStream stream = new BufferedOutputStream(new FileOutputStream(f))) {
				byte[] bytes = photo.getBytes();
				stream.write(bytes);
				log.info("Uploaded photo for {} into {}!", id, f.getAbsolutePath());
			} catch (Exception e) {
				response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
				log.warn("Error uploading " + id + " ", e);
			}
		}
		return "pelicula";
	}

}