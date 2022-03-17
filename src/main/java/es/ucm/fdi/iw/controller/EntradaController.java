package es.ucm.fdi.iw.controller;

import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import javax.persistence.EntityManager;

import es.ucm.fdi.iw.model.Cine;
import es.ucm.fdi.iw.model.Entrada;
import es.ucm.fdi.iw.model.Pelicula;
import es.ucm.fdi.iw.model.Sesion;

/**
 *  Controlador para las entradas
 */

@Controller
@RequestMapping("entradas")
public class EntradaController {

    private static final Logger log = LogManager.getLogger(PeliculaController.class);

    @Autowired
	private EntityManager entityManager;

    @GetMapping("/compra-entradas")
    public String compraEntradas(Model model, @RequestParam(name = "sesion") long idSesion) {
        Sesion s = entityManager.find(Sesion.class, idSesion);
        model.addAttribute("sesion", s);
        return "compraEntradas";
    }

    @GetMapping("/{id}")
    public String infoEntrada(@PathVariable long id, Model model) {
        Entrada e = entityManager.find(Entrada.class, id);
        model.addAttribute("entrada", e);
        return "resumenEntrada";
    }
    
}
