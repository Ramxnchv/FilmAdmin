package es.ucm.fdi.iw.controller;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

/**
 *  Non-authenticated requests only.
 */
@Controller
public class RootController {

	private static final Logger log = LogManager.getLogger(RootController.class);

	@GetMapping("/login")
    public String login(Model model) {
        return "login";
    }

	@GetMapping("/")
    public String index(Model model) {
        return "index";
    }

    @GetMapping("/peliculas")
    public String peliculas(Model model) {
        return "peliculas";
    }

    @GetMapping("/cines")
    public String cines(Model model) {
        return "cines";
    }

    @GetMapping("/atencion-cliente")
    public String atencionCliente(Model model) {
        return "atencionCliente";
    }

    @GetMapping("/gestion-entradas")
    public String gestionEntradas(Model model) {
        return "gestionentradas";
    }

    @GetMapping("/atencion-cliente-admin")
    public String atencionClienteAdmin(Model model) {
        return "atencionCliente";
    }
}