package es.ucm.fdi.iw.controller;


import java.time.DayOfWeek;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;
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

import es.ucm.fdi.iw.model.Pelicula;
import es.ucm.fdi.iw.model.Sesion;

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

        for (Pelicula p: sesiones){
            for(Sesion s: p.getSesiones()){
                if(s.getDia_hora().getMinute() < 10){
                    log.info(s.getPelicula().getTitulo() + ": " + s.getDia_hora().getHour() + ":0" + s.getDia_hora().getMinute());
                }else{
                    log.info(s.getPelicula().getTitulo() + ": " + s.getDia_hora().getHour() + ":" + s.getDia_hora().getMinute());
                }
            }
        }

        model.addAttribute("sesiones", sesiones);
       
        return "peliculas";
    }

    @GetMapping("/{id}")
    public String infoPelicula(@PathVariable long id, Model model) {

        Pelicula pel = entityManager.find(Pelicula.class, id);

        @SuppressWarnings("unchecked")
        List<Pelicula> sesiones = (List<Pelicula>) entityManager.createNamedQuery("Pelicula.getAll").getResultList();

        for (Pelicula p: sesiones){
            for(Sesion s: p.getSesiones()){
                if(s.getDia_hora().getMinute() < 10){
                    log.info(s.getPelicula().getTitulo() + ": " + s.getDia_hora().getHour() + ":0" + s.getDia_hora().getMinute());
                }else{
                    log.info(s.getPelicula().getTitulo() + ": " + s.getDia_hora().getHour() + ":" + s.getDia_hora().getMinute());
                }
            }
        }

        model.addAttribute("sesiones", sesiones);
        model.addAttribute("pelicula", pel);

        return "pelicula";
    }

}