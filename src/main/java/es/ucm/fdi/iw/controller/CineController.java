package es.ucm.fdi.iw.controller;

import es.ucm.fdi.iw.model.Cine;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import javax.persistence.EntityManager;
import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;


/**
 *  Controlador para los cines
 */

@Controller
@RequestMapping("cines")
public class CineController {

	private static final Logger log = LogManager.getLogger(CineController.class);

    @Autowired
	private EntityManager entityManager;

    @GetMapping("{id}")
    public String index(@PathVariable long id, Model model, HttpSession session) {
        @SuppressWarnings("unchecked")
        ArrayList<Cine> miLista = (ArrayList<Cine>) entityManager.createNamedQuery("Cine.FindAll").getResultList();
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
        @SuppressWarnings("unchecked")
        ArrayList<Cine> miLista = (ArrayList<Cine>) entityManager.createNamedQuery("Cine.FindAll").getResultList();
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

}