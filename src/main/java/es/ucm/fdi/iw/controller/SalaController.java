package es.ucm.fdi.iw.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
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

import es.ucm.fdi.iw.model.Asiento;
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

	@ResponseStatus(value = HttpStatus.FORBIDDEN, reason = "Faltan datos para crear la nueva sesion.") // 403
	public static class FaltanDatosException extends RuntimeException {
	}

	@ResponseStatus(value = HttpStatus.FORBIDDEN, reason = "No se puede cambiar las filas, columnas y cine de una sala mientras tenga sesiones activas.") // 403
	public static class SesionesActivasException extends RuntimeException {
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
			
			if (o.get("nombre") == null || o.get("filas") == null ||
                o.get("columnas") == null || o.get("cine_id") == null)
                throw new FaltanDatosException();
			
			String nombre = o.get("nombre").asText(); 
			target.setNombre(nombre);

			int filas = o.get("filas").asInt();
			target.setFilas(filas);

			int columnas = o.get("columnas").asInt();
			target.setColumnas(columnas);

			long cine_id = o.get("cine_id").asLong();
			Cine cine = entityManager.find(Cine.class, cine_id);
			target.setCine(cine);

			entityManager.persist(target);
			entityManager.flush();
			id = target.getId(); // retrieve assigned id from DB

			for (int f = 1; f <= filas; f++) {
				for (int c = 1; c <= columnas; c++) {
					Asiento asiento = new Asiento();
					asiento.setColumna(c);
					asiento.setFila(f);
					asiento.setSala(target);
					entityManager.persist(asiento);
					entityManager.flush();
					target.getAsientos().add(asiento);
				}
			}
		}
		else {
			// retrieve requested user
			target = entityManager.find(Sala.class, id);
			model.addAttribute("sala", target);
			if(o.get("nombre")!=null){
				String nombre = o.get("nombre").asText(); 
				if (nombre != null) {target.setNombre(nombre);}
			}
			
			int filas = 0;
			int columnas = 0;
			Cine cine = null;

			if(o.get("filas")!=null){
				if (target.getFilas() != o.get("filas").asInt())
					filas = o.get("filas").asInt();
			}
			if(o.get("columnas")!=null){
				if (target.getColumnas() != o.get("columnas").asInt())
					columnas = o.get("columnas").asInt();
			}
			if(o.get("cine_id")!=null){
				long cine_id = o.get("cine_id").asLong();
				if (cine_id != 0 && cine_id != target.getCine().getId()) {
					cine = entityManager.find(Cine.class, cine_id);
				}
			}

			if (filas != 0 || columnas != 0 || cine != null) {
				if (target.getSesiones().size() > 0)
					throw new SesionesActivasException();

				if ((filas != target.getFilas() || columnas != target.getColumnas()) &&
					(filas != 0 || columnas != 0)) {

					for (Asiento asiento: target.getAsientos()) {
						entityManager.remove(asiento);
					}

					target.getAsientos().clear();

					if (filas != 0) target.setFilas(filas);
					if (columnas != 0) target.setColumnas(columnas);

					for (int f = 1; f <= target.getFilas(); f++) {
						for (int c = 1; c <= target.getColumnas(); c++) {
							Asiento asiento = new Asiento();
							asiento.setColumna(c);
							asiento.setFila(f);
							asiento.setSala(target);
							entityManager.persist(asiento);
							entityManager.flush();
							target.getAsientos().add(asiento);
						}
					}
				}

				if (cine != null) target.setCine(cine);
			}
		}
		ObjectMapper mapper = new ObjectMapper();
		ObjectNode n = mapper.createObjectNode();
		n.put("id", target.getId());
		String json = mapper.writeValueAsString(n);

		return json;
	}
    
}