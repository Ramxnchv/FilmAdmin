package es.ucm.fdi.iw.controller;

import java.time.LocalDate;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import javax.persistence.EntityManager;

import es.ucm.fdi.iw.model.Cine;
import es.ucm.fdi.iw.model.Pelicula;

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

        @SuppressWarnings("unchecked")
        List<Pelicula> sesiones = (List<Pelicula>) entityManager.createNamedQuery("Pelicula.getAll").getResultList();

        @SuppressWarnings("unchecked")
        List<Cine> cines = (List<Cine>) entityManager.createNamedQuery("Cine.FindAll").getResultList();

        model.addAttribute("sesiones", sesiones);
        model.addAttribute("cines", cines);
        model.addAttribute("dia", LocalDate.now());
       
        return "peliculas";
    }

    @GetMapping("/{id}")
    public String infoPelicula(@PathVariable long id, Model model) {

        Pelicula pel = entityManager.find(Pelicula.class, id);

        @SuppressWarnings("unchecked")
        List<Pelicula> sesiones = (List<Pelicula>) entityManager.createNamedQuery("Pelicula.getAll").getResultList();

        @SuppressWarnings("unchecked")
        List<Cine> cines = (List<Cine>) entityManager.createNamedQuery("Cine.FindAll").getResultList();

        model.addAttribute("sesiones", sesiones);
        model.addAttribute("pelicula", pel);
        model.addAttribute("cines", cines);
        model.addAttribute("dia", LocalDate.now());

        return "pelicula";
    }

}