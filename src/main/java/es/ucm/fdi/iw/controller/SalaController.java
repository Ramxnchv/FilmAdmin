package es.ucm.fdi.iw.controller;

import java.io.IOException;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
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

import es.ucm.fdi.iw.model.Cine;
import es.ucm.fdi.iw.model.Sala;
import es.ucm.fdi.iw.model.User;
import es.ucm.fdi.iw.model.User.Role;

@Controller
@RequestMapping("salas")
public class SalaController {

	private static final Logger log = LogManager.getLogger(PeliculaController.class);

    @Autowired
	private EntityManager entityManager;

    @ResponseStatus(value = HttpStatus.FORBIDDEN, reason = "No eres administrador.") // 403
	public static class NoEresAdminException extends RuntimeException {
	}

    @PostMapping(path = "/{id}", consumes = "application/json")
	@ResponseBody
	@Transactional
	public String postSala(
			HttpServletResponse response,
			@PathVariable long id,
			@RequestBody JsonNode o,
			Model model, HttpSession session) throws IOException {

		User requester = (User) session.getAttribute("u");
        if (!requester.hasRole(Role.ADMIN)) 
		    throw new NoEresAdminException();
		
		Sala target = null;
		if (id == -1 && requester.hasRole(Role.ADMIN)) {
			target = new Sala();
			entityManager.persist(target);
			entityManager.flush();
			id = target.getId(); // retrieve assigned id from DB
		}

		// retrieve requested user
		target = entityManager.find(Sala.class, id);
		model.addAttribute("pelicula", target);

		if(o.get("nombre")!=null){
			String nombre = o.get("nombre").asText(); 
			if (nombre != null) {target.setNombre(nombre);}
		}
		if(o.get("filas")!=null){
			int filas = o.get("filas").asInt();
			if (filas != 0) {target.setFilas(filas);}
		}
		if(o.get("columnas")!=null){
			int columnas = o.get("columnas").asInt();
			if (columnas != 0) {target.setColumnas(columnas);}
		}
        if(o.get("cine_id")!=null){
			long cine_id = o.get("cine_id").asLong();
			if (cine_id != 0) {
                Cine cine = entityManager.find(Cine.class, cine_id);
                target.setCine(cine);
            }
		}
		
		ObjectMapper mapper = new ObjectMapper();
		ObjectNode n = mapper.createObjectNode();
		n.put("id", target.getId());
		String json = mapper.writeValueAsString(n);

		return json;
	}
    
}