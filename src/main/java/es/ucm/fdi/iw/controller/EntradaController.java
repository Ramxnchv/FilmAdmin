package es.ucm.fdi.iw.controller;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.apache.commons.lang3.RandomStringUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import javax.persistence.EntityManager;
import javax.servlet.http.HttpSession;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;

import es.ucm.fdi.iw.model.Asiento;
import es.ucm.fdi.iw.model.Entrada;
import es.ucm.fdi.iw.model.Sesion;
import es.ucm.fdi.iw.model.Transferable;
import es.ucm.fdi.iw.model.User;

/**
 *  Controlador para las entradas
 */

@Controller
@RequestMapping("entradas")
public class EntradaController {

    private static final Logger log = LogManager.getLogger(PeliculaController.class);
    private final double PRECIO_ENTRADA = 11;

    @Autowired
	private EntityManager entityManager;

    @GetMapping("/compra-entradas")
    public String compraEntradas(Model model, @RequestParam(name = "sesion") long idSesion) {
        Sesion s = entityManager.find(Sesion.class, idSesion);
        model.addAttribute("sesion", s);
        return "compraEntradas";
    }

    @GetMapping("/{codigo}")
    public String infoEntradaCodigo(@PathVariable String codigo, Model model) {
        Entrada e = entityManager.createNamedQuery("Entrada.findByCode",Entrada.class).setParameter("codigo", codigo).getSingleResult();
        Sesion s = e.getSesion();
        model.addAttribute("entrada", e);
        model.addAttribute("sesion", s);
        model.addAttribute("numeroAsientos",e.getAsientos().size());
        return "resumenEntrada";
    }

    @GetMapping(path = "/asientos/{idSesion}" , produces = "application/json")
    @ResponseBody
    public List<Entrada.Transfer> getAsientos(@PathVariable long idSesion, Model model) {

        List<Entrada> entradas = (List<Entrada>) entityManager.createNamedQuery("Entrada.findBySesion",Entrada.class).setParameter("sesionId", idSesion).getResultList();
        
        return entradas.stream().map(Transferable::toTransfer).collect(Collectors.toList());
    }

    @GetMapping(path = "/info/{codigo}" , produces = "application/json")
    @ResponseBody
    public List<Entrada.Transfer> getInfoEntrada(@PathVariable String codigo, Model model) {
        try{
            Entrada e = entityManager.createNamedQuery("Entrada.findByCode",Entrada.class).setParameter("codigo", codigo).getSingleResult();
            List<Entrada> entradas =  new ArrayList<>();
            entradas.add(e);
            return entradas.stream().map(Transferable::toTransfer).collect(Collectors.toList());
        }catch(Exception e){
            return new ArrayList<Entrada.Transfer>();
        }
    }

    @PostMapping(path = "/validate/{codigo}" , produces = "application/json")
    @ResponseBody
    @Transactional
    public List<Entrada.Transfer> validateEntrada(@PathVariable String codigo, Model model) {
        Entrada e = entityManager.createNamedQuery("Entrada.findByCode",Entrada.class).setParameter("codigo", codigo).getSingleResult();
        e.setValidate(true);
        e.setHoraValidacion(LocalDateTime.now());
        List<Entrada> entradas =  new ArrayList<>();
        entradas.add(e);
        return entradas.stream().map(Transferable::toTransfer).collect(Collectors.toList());
    }

    @PostMapping(path = "/compra-entradas/{sesion}", consumes = "application/json")
    @ResponseBody
    @Transactional
    public String compraEntradasPost(Model model, @PathVariable(name = "sesion") long idSesion, @RequestBody JsonNode o, HttpSession session) throws JsonProcessingException {
        Sesion s = entityManager.find(Sesion.class, idSesion);
        User u = (User)session.getAttribute("u");
        String codigo = RandomStringUtils.random(8,true,true).toUpperCase();

        List<Asiento> asientos = new ArrayList<>();
        JsonNode asientosNode = o.get("asientos");
        int numeroasientos = o.get("numeroasientos").asInt();
        for(int i=0;i<numeroasientos;i++){
            long idAsiento = asientosNode.get(i).asLong();
            Asiento a = entityManager.find(Asiento.class, idAsiento);
            asientos.add(a);
        }

        double preciofinal = PRECIO_ENTRADA*asientos.size();

        List<Entrada> entradas = (List<Entrada>) entityManager.createNamedQuery("Entrada.getAll",Entrada.class).getResultList();
        Entrada ultima = entradas.get(entradas.size()-1);
        long idnueva = ultima.getId()+1;
        Entrada e = new Entrada(idnueva,s,u,asientos,codigo, preciofinal, null, false);

        entityManager.persist(e);
        entityManager.flush();

        //actualizamos los asientos libres de la sesión
        s.setAsientosLibres(s.getAsientosLibres()-e.getAsientos().size());

        ObjectMapper mapper = new ObjectMapper();
        ObjectNode n = mapper.createObjectNode();
        n.put("id", e.getCodigo());
        String json = mapper.writeValueAsString(n);
        return json;
    }
}
