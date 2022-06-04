package es.ucm.fdi.iw.controller;

import java.io.IOException;
import java.time.LocalDateTime;

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
import es.ucm.fdi.iw.model.Pelicula;
import es.ucm.fdi.iw.model.Sala;
import es.ucm.fdi.iw.model.Sesion;
import es.ucm.fdi.iw.model.User;
import es.ucm.fdi.iw.model.User.Role;

@Controller
@RequestMapping("sesiones")
public class SesionController {

	private static final Logger log = LogManager.getLogger(PeliculaController.class);

    @Autowired
	private EntityManager entityManager;

    @ResponseStatus(value = HttpStatus.FORBIDDEN, reason = "No eres administrador.") // 403
	public static class NoEresAdminException extends RuntimeException {
	}
    @ResponseStatus(value = HttpStatus.FORBIDDEN, reason = "Faltan datos para crear la nueva sesion.") // 403
	public static class FaltanDatosException extends RuntimeException {
	}
    @ResponseStatus(value = HttpStatus.FORBIDDEN, reason = "No se puede modificar una sesion de la que ya se han vendido entradas.") // 403
	public static class EntradasVendidasException extends RuntimeException {
	}

    @PostMapping(path = "/{id}", consumes = "application/json")
	@ResponseBody
	@Transactional
	public String postSesion(
			HttpServletResponse response,
			@PathVariable long id,
			@RequestBody JsonNode o,
			Model model, HttpSession session) throws IOException {

		User requester = (User) session.getAttribute("u");
        if (!requester.hasRole(Role.ADMIN)) 
		    throw new NoEresAdminException();
		
		Sesion target = null;
		if (id == -1 && requester.hasRole(Role.ADMIN)) {
			target = new Sesion();

            if (o.get("pelicula_id") == null || o.get("sala_id") == null ||
                o.get("cine_id") == null || o.get("dia_hora") == null || o.get("precio") == null)
                throw new FaltanDatosException();
            
            long pelicula_id = o.get("pelicula_id").asLong(); 
            Pelicula pelicula = entityManager.find(Pelicula.class, pelicula_id);
            target.setPelicula(pelicula);

            long sala_id = o.get("sala_id").asLong();
            Sala sala = entityManager.find(Sala.class, sala_id);
            target.setSala(sala);
            target.setAsientosLibres(sala.getFilas() * sala.getColumnas());

            long cine_id = o.get("cine_id").asLong(); 
            Cine cine = entityManager.find(Cine.class, cine_id);
            target.setCine(cine);

            String dia_hora = o.get("dia_hora").asText();
            target.setDia_hora(LocalDateTime.parse(dia_hora));

            double precio = o.get("precio").asDouble();
            target.setPrecioEntrada(precio);

			entityManager.persist(target);
			entityManager.flush();
			id = target.getId(); // retrieve assigned id from DB
            
            sala.getSesiones().add(target);
		}
        else {
            // retrieve requested user
            target = entityManager.find(Sesion.class, id);
        
            if (target.getAsientosLibres() != (target.getSala().getColumnas() * target.getSala().getFilas()))
                throw new EntradasVendidasException();

            model.addAttribute("sesion", target);

            if(o.get("pelicula_id")!=null){
                long pelicula_id = o.get("pelicula_id").asLong(); 
                if (pelicula_id != 0) {
                    Pelicula pelicula = entityManager.find(Pelicula.class, pelicula_id);
                    target.setPelicula(pelicula);
                }
            }
            if(o.get("sala_id")!=null){
                long sala_id = o.get("sala_id").asLong(); 
                if (sala_id != 0 && sala_id != target.getSala().getId()) {
                    target.getSala().getSesiones().remove(target);
                    Sala sala = entityManager.find(Sala.class, sala_id);
                    target.setSala(sala);
                    target.setAsientosLibres(sala.getFilas() * sala.getColumnas());
                    sala.getSesiones().add(target);
                }
            }
            if(o.get("cine_id")!=null){
                long cine_id = o.get("cine_id").asLong(); 
                if (cine_id != 0) {
                    Cine cine = entityManager.find(Cine.class, cine_id);
                    target.setCine(cine);
                }
            }
            if(o.get("dia_hora")!=null){
                String dia_hora = o.get("dia_hora").asText();
                if (dia_hora != null) {target.setDia_hora(LocalDateTime.parse(dia_hora));}
            }
            if(o.get("precio")!=null){
                double precio = o.get("precio").asDouble();
                if (precio != target.getPrecioEntrada()) {target.setPrecioEntrada(precio);}
            }
        }
		
		ObjectMapper mapper = new ObjectMapper();
		ObjectNode n = mapper.createObjectNode();
		n.put("id", target.getId());
		String json = mapper.writeValueAsString(n);

		return json;
	}
    
}