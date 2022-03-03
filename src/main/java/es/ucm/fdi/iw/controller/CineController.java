package es.ucm.fdi.iw.controller;

import es.ucm.fdi.iw.LocalData;
import es.ucm.fdi.iw.model.Cine;
import es.ucm.fdi.iw.model.Message;
import es.ucm.fdi.iw.model.Transferable;
import es.ucm.fdi.iw.model.User;
import es.ucm.fdi.iw.model.User.Role;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.method.annotation.StreamingResponseBody;

import javax.persistence.EntityManager;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.transaction.Transactional;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;

import java.io.*;
import java.security.SecureRandom;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Base64;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;


/**
 *  Controlador para las peliculas
 */

@Controller
@RequestMapping("cines")
public class CineController {

	private static final Logger log = LogManager.getLogger(CineController.class);

    @Autowired
	private EntityManager entityManager;

	@GetMapping("/")
    public String index(Model model) {
        /*List<Cine.Transfer> miLista = entityManager.createNamedQuery("Cine.FindAll", Cine.class).getResultStream().map(Transferable::toTransfer).collect(Collectors.toList());
        model.addAttribute("listaCines", miLista);
        return "cines";*/

        List<Cine.Transfer> miLista = new ArrayList<>();
        miLista.add(1, new Cine.Transfer(1, null, null, null, null, LocalTime.now(), LocalTime.now(), "Madrid", "telefono", "direccion", "ciudad", "urlmap"));
        miLista.add(2, new Cine.Transfer(2, null, null, null, null, LocalTime.now(), LocalTime.now(), "Valencia", "telefono", "direccion", "ciudad", "urlmap"));
        miLista.add(3, new Cine.Transfer(3, null, null, null, null, LocalTime.now(), LocalTime.now(), "barcelona", "telefono", "direccion", "ciudad", "urlmap"));
   
        model.addAttribute("listaCines", miLista);
        return "cines";
    }

    /*@GetMapping("/1")
    public String infoCine(Model model) {
        return "cine";
    }
    */
}