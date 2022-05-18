package es.ucm.fdi.iw.controller;

import es.ucm.fdi.iw.LocalData;
import es.ucm.fdi.iw.model.Cine;
import es.ucm.fdi.iw.model.User;
import es.ucm.fdi.iw.model.User.Role;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.method.annotation.StreamingResponseBody;

import javax.persistence.EntityManager;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.transaction.Transactional;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.time.DayOfWeek;
import java.time.LocalTime;
import java.time.MonthDay;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;


/**
 *  Controlador para los cines
 */

@Controller
@RequestMapping("cines")
public class CineController {

	private static final Logger log = LogManager.getLogger(CineController.class);

    @Autowired
	private EntityManager entityManager;

    @Autowired
	private LocalData localData;

    @ResponseStatus(value = HttpStatus.FORBIDDEN, reason = "No eres administrador.") // 403
	public static class NoEresAdminException extends RuntimeException {
	}

    @GetMapping("{id}")
    public String index(@PathVariable long id, Model model) {
        ArrayList<Cine> miLista = (ArrayList<Cine>) entityManager.createNamedQuery("Cine.FindAll",Cine.class).getResultList();
        Cine cine = entityManager.find(Cine.class, id);
        for(Cine c: miLista){
            if(c.getId()==id){
                cine = c;
                break;
            }
        }
        List<String> ciudades = new ArrayList<>();
        String ciudadAnterior = null;
        for (Cine c: miLista) {
            if (!c.getCiudad().toLowerCase().equals(ciudadAnterior)) {
                ciudades.add(c.getCiudad());
                ciudadAnterior = c.getCiudad().toLowerCase();
            }
        }
        model.addAttribute("listaCines", miLista);
        model.addAttribute("cineInfo", cine);
        model.addAttribute("listaCiudades", ciudades);
        return "cines";
    }

	@GetMapping("/")
    public String index(Model model) {

        ArrayList<Cine> miLista = (ArrayList<Cine>) entityManager.createNamedQuery("Cine.FindAll",Cine.class).getResultList();
        List<String> ciudades = new ArrayList<>();
        String ciudadAnterior = null;
        for (Cine c: miLista) {
            if (!c.getCiudad().toLowerCase().equals(ciudadAnterior)) {
                ciudades.add(c.getCiudad());
                ciudadAnterior = c.getCiudad().toLowerCase();
            }
        }
        model.addAttribute("listaCines", miLista);
        model.addAttribute("listaCiudades", ciudades);
        return "cines";
    }

    @PostMapping(path = "/{id}", consumes = "application/json")
	@ResponseBody
	@Transactional
	public String postCine(
			HttpServletResponse response,
			@PathVariable long id,
			@RequestBody JsonNode o,
			Model model, HttpSession session) throws IOException {

		User requester = (User) session.getAttribute("u");
        if (!requester.hasRole(Role.ADMIN)) 
		    throw new NoEresAdminException();
		
		Cine target = null;
		if (id == -1 && requester.hasRole(Role.ADMIN)) {
			target = new Cine();
			entityManager.persist(target);
			entityManager.flush();
			id = target.getId(); // retrieve assigned id from DB
		}

		// retrieve requested user
		target = entityManager.find(Cine.class, id);
		model.addAttribute("cine", target);

		if(o.get("nombre")!=null){
			String nombre = o.get("nombre").asText(); 
			if (nombre != null) {target.setNombre(nombre);}
		}
		if(o.get("ciudad")!=null){
			String ciudad = o.get("ciudad").asText();
			if (ciudad != null) {target.setCiudad(ciudad);}
		}
		if(o.get("direccion")!=null){
			String direccion = o.get("direccion").asText();
			if (direccion != null) {target.setDireccion(direccion);}
		}
        if(o.get("hora_apertura")!=null){
			String hora_apertura = o.get("hora_apertura").asText();
			if (hora_apertura != null) {target.setHora_apertura(LocalTime.parse(hora_apertura));}
		}
        if(o.get("hora_cierre")!=null){
			String hora_cierre = o.get("hora_cierre").asText();
			if (hora_cierre != null) {target.setHora_cierre(LocalTime.parse(hora_cierre));}
		}
        if(o.get("festivoscierre")!=null){
		    JsonNode festivoscierreArray = o.get("festivoscierre");
            List<MonthDay> festivoscierre = new ArrayList<>();
            for (JsonNode festivo : festivoscierreArray) {
                festivoscierre.add(MonthDay.parse(festivo.asText()));
            }
            target.setFestivos_cierre(festivoscierre);
        }
        if(o.get("coordenadas")!=null){
			String coordenadas = o.get("coordenadas").asText();
			if (coordenadas != null) {target.setCoordenadas(coordenadas);}
		}
        if(o.get("dias_apertura")!=null){
			JsonNode diasaperturaArray = o.get("dias_apertura");
            List<DayOfWeek> diasapertura = new ArrayList<>();
            for (JsonNode dia : diasaperturaArray) {
                diasapertura.add(DayOfWeek.of(dia.asInt()));
            }
            target.setDias_apertura(diasapertura);
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
		File f = localData.getFile("cine", ""+id+".jpg");
		InputStream in = new BufferedInputStream(f.exists() ? new FileInputStream(f) : CineController.defaultPic());
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

		Cine target = entityManager.find(Cine.class, id);
		model.addAttribute("pelicula", target);

		// check permissions
		User requester = (User) session.getAttribute("u");
        if (!requester.hasRole(Role.ADMIN)) 
		    throw new NoEresAdminException();

		log.info("Updating photo for cine {}", id);
		File f = localData.getFile("cine", ""+id+".jpg");
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
		return "cines";
	}
}