package es.ucm.fdi.iw.controller;

import java.time.LocalDate;
import java.util.List;

import javax.persistence.EntityManager;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import es.ucm.fdi.iw.model.Cine;
import es.ucm.fdi.iw.model.Pelicula;
import es.ucm.fdi.iw.model.Sala;
import es.ucm.fdi.iw.model.Sesion;
import es.ucm.fdi.iw.model.User;

/**
 *  Site administration.
 *
 *  Access to this end-point is authenticated.
 */
@Controller
@RequestMapping("admin")
public class AdminController {

	private static final Logger log = LogManager.getLogger(AdminController.class);

    @Autowired
	private EntityManager entityManager;

	@GetMapping("/")
    public String index(Model model) {

        @SuppressWarnings("unchecked")
        List<Pelicula> peliculas = (List<Pelicula>) entityManager.createNamedQuery("Pelicula.getList").getResultList();

        @SuppressWarnings("unchecked")
        List<Cine> cines = (List<Cine>) entityManager.createNamedQuery("Cine.FindAll").getResultList();

        @SuppressWarnings("unchecked")
        List<Sala> salas = (List<Sala>) entityManager.createNamedQuery("Sala.FindAll").getResultList();

        @SuppressWarnings("unchecked")
        List<Sesion> sesiones = (List<Sesion>) entityManager.createNamedQuery("Sesion.getList").getResultList();

        @SuppressWarnings("unchecked")
        List<User> usuarios = (List<User>) entityManager.createNamedQuery("User.getAll").getResultList();

        model.addAttribute("peliculas", peliculas);
        model.addAttribute("cines", cines);
        model.addAttribute("salas", salas);
        model.addAttribute("sesiones", sesiones);
        model.addAttribute("usuarios", usuarios);
        model.addAttribute("dia", LocalDate.now());

        return "admin";
    }

    @GetMapping("/gestion-entradas")
    public String gestionEntradas(Model model) {
        return "gestionentradas";
    }

    @GetMapping("/atencion-cliente")
    public String atencionCliente(Model model) {
        return "atencionCliente";
    }
}
