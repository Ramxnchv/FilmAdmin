package es.ucm.fdi.iw.controller;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import javax.persistence.EntityManager;
import javax.servlet.http.HttpSession;
import javax.transaction.Transactional;

import es.ucm.fdi.iw.model.Cine;
import es.ucm.fdi.iw.model.Pelicula;
import es.ucm.fdi.iw.model.Sala;
import es.ucm.fdi.iw.model.Transferable;

/**
 *  Controlador para las peliculas
 */

@Controller
@RequestMapping("peliculas")
public class PeliculaController {

	private static final Logger log = LogManager.getLogger(PeliculaController.class);

    @Autowired
	private EntityManager entityManager;

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

}