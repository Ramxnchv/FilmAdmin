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

    @GetMapping("{id}")
    public String index(@PathVariable long id, Model model, HttpSession session) {
        List<Cine.Transfer> miLista = new ArrayList<>();
        Cine.Transfer cine = null;
        miLista.add(new Cine.Transfer(1, null, null, null, null, LocalTime.of(10, 00), LocalTime.of(22, 00), "La Gavia", "914255401", "C/Adolfo Bioy Casares, 2", "Madrid", "40.369008,-3.599046","lagavia.jpg"));
        miLista.add(new Cine.Transfer(2, null, null, null, null, LocalTime.of(9, 00), LocalTime.of(23, 00), "Plaza Río 2", "911374548", "Av del Manzanares, 210", "Madrid", "40.39083,-3.70144","plazario2.jpg"));
        miLista.add(new Cine.Transfer(3, null, null, null, null, LocalTime.of(10, 00), LocalTime.of(23, 00), "Barnasud", "936625656", "Carrer del Progrés, 69", "Barcelona", "41.29555,2.00805","barnasud.jpg"));
        miLista.add(new Cine.Transfer(4, null, null, null, null, LocalTime.of(11, 00), LocalTime.of(00, 00), "Diagonal", "935677637", "Avinguda Diagonal, 3", "Barcelona", "41.40997,2.21648","diagonal.png"));
        miLista.add(new Cine.Transfer(5, null, null, null, null, LocalTime.of(17, 00), LocalTime.of(22, 30), "Bonaire", "961579224", "Autovía del Este, Km. 345", "Valencia", "39.47172,-0.48867","bonaire.jpg"));
        for(Cine.Transfer c: miLista){
            if(c.getId()==id){
                cine = c;
                break;
            }
        }
        List<String> ciudades = new ArrayList<>();
        String ciudadAnterior = null;
        for (Cine.Transfer c: miLista) {
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
        /*List<Cine.Transfer> miLista = entityManager.createNamedQuery("Cine.FindAll", Cine.class).getResultStream().map(Transferable::toTransfer).collect(Collectors.toList());
        model.addAttribute("listaCines", miLista);
        return "cines";*/

        List<Cine.Transfer> miLista = new ArrayList<>();
        miLista.add(new Cine.Transfer(1, null, null, null, null, LocalTime.now(), LocalTime.now(), "La Gavia", "914255401", "C/Adolfo Bioy Casares, 2", "Madrid", "https://www.google.com/maps/embed?pb=!1m18!1m12!1m3!1d3039.7973319854436!2d-3.601224384308786!3d40.369017966463225!2m3!1f0!2f0!3f0!3m2!1i1024!2i768!4f13.1!3m3!1m2!1s0xd4224dc15dad579%3A0xcbf9370ac84ce0db!2sCC%20La%20Gavia!5e0!3m2!1ses!2ses!4v1645031698047!5m2!1ses!2ses","lagavia.jpg"));
        miLista.add(new Cine.Transfer(2, null, null, null, null, LocalTime.now(), LocalTime.now(), "Plaza Río 2", "911374548", "Av del Manzanares, 210", "Madrid", "https://www.google.com/maps/embed?pb=!1m18!1m12!1m3!1d3038.811086247132!2d-3.703610384308298!3d40.390879365134694!2m3!1f0!2f0!3f0!3m2!1i1024!2i768!4f13.1!3m3!1m2!1s0xd42264aea26f26b%3A0xf00a1384ed1877c7!2sCentro%20Comercial%20Plaza%20R%C3%ADo%202!5e0!3m2!1ses!2ses!4v1646341938959!5m2!1ses!2ses","plazario2.jpg"));
        miLista.add(new Cine.Transfer(3, null, null, null, null, LocalTime.now(), LocalTime.now(), "Barnasud", "936625656", "Carrer del Progrés, 69", "Barcelona", "https://www.google.com/maps/embed?pb=!1m18!1m12!1m3!1d2997.617249088813!2d2.005895315711201!3d41.29543390965292!2m3!1f0!2f0!3f0!3m2!1i1024!2i768!4f13.1!3m3!1m2!1s0x12a49d2282241fcd%3A0x977c6f7948b4c0a8!2sBarnasud!5e0!3m2!1ses!2ses!4v1646341973548!5m2!1ses!2ses","barnasud.jpg"));
        miLista.add(new Cine.Transfer(4, null, null, null, null, LocalTime.now(), LocalTime.now(), "Diagonal", "935677637", "Avinguda Diagonal, 3", "Barcelona", "https://www.google.com/maps/embed?pb=!1m14!1m8!1m3!1d11969.413564338516!2d2.2165097!3d41.4098468!3m2!1i1024!2i768!4f13.1!3m3!1m2!1s0x0%3A0xcf8bb581de6135b1!2sDiagonal%20Mar%20Centro%20Comercial!5e0!3m2!1ses!2ses!4v1646341845559!5m2!1ses!2ses","diagonal.png"));
        miLista.add(new Cine.Transfer(5, null, null, null, null, LocalTime.now(), LocalTime.now(), "Bonaire", "961579224", "Autovía del Este, Km. 345", "Valencia", "https://www.google.com/maps/embed?pb=!1m18!1m12!1m3!1d3079.899095417963!2d-0.4908945843277087!3d39.47160822049665!2m3!1f0!2f0!3f0!3m2!1i1024!2i768!4f13.1!3m3!1m2!1s0xd60503c6905e429%3A0x86cea16515a6f00a!2sCentro%20Comercial%20Bonaire!5e0!3m2!1ses!2ses!4v1646342002796!5m2!1ses!2ses","bonaire.jpg"));
        List<String> ciudades = new ArrayList<>();
        String ciudadAnterior = null;
        for (Cine.Transfer c: miLista) {
            if (!c.getCiudad().toLowerCase().equals(ciudadAnterior)) {
                ciudades.add(c.getCiudad());
                ciudadAnterior = c.getCiudad().toLowerCase();
            }
        }
        model.addAttribute("listaCines", miLista);
        model.addAttribute("listaCiudades", ciudades);
        return "cines";
    }

    /*@GetMapping("/1")
    public String infoCine(Model model) {
        return "cine";
    }
    */
}