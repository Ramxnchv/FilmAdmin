package es.ucm.fdi.iw.controller;


import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 *  Controlador para las peliculas
 */

@Controller
@RequestMapping("peliculas")
public class PeliculaController {

	private static final Logger log = LogManager.getLogger(PeliculaController.class);

	@GetMapping("/")
    public String index(Model model) {
        return "peliculas";
    }

    @GetMapping("/1")
    public String infoPelicula(Model model) {
        return "pelicula";
    }

}