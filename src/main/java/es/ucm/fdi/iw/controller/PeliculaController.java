package es.ucm.fdi.iw.controller;


import java.time.DayOfWeek;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import javax.persistence.EntityManager;

import es.ucm.fdi.iw.model.Pelicula;
import es.ucm.fdi.iw.model.Sesion;
import es.ucm.fdi.iw.model.Cine;
import es.ucm.fdi.iw.model.Sala;
import es.ucm.fdi.iw.model.Entrada;
import es.ucm.fdi.iw.model.Asiento;

/**
 *  Controlador para las peliculas
 */

@Controller
@RequestMapping("peliculas")
public class PeliculaController {

	private static final Logger log = LogManager.getLogger(PeliculaController.class);

    @Autowired
	private EntityManager entityManager;

	@GetMapping("/")
    public String index(Model model) {
        List<Pelicula> peliculas = new ArrayList<>();
        List<Sesion> sesiones = new ArrayList<>();
        List<Sesion> sesiones2 = new ArrayList<>();
        List<Entrada> entradas = new ArrayList<>();
        List<Sala> salas = new ArrayList<>();
        List<Asiento> asientos = new ArrayList<>();
        List<Asiento> asientos2 = new ArrayList<>();
        List<Date> festivos_cierre = new ArrayList<>();
        List<DayOfWeek> dias_apertura = new ArrayList<>();
        dias_apertura.add(DayOfWeek.MONDAY);dias_apertura.add(DayOfWeek.TUESDAY);dias_apertura.add(DayOfWeek.WEDNESDAY);dias_apertura.add(DayOfWeek.THURSDAY);dias_apertura.add(DayOfWeek.FRIDAY);dias_apertura.add(DayOfWeek.SATURDAY);
        
        Pelicula elbuenpatron = new Pelicula(1, sesiones, "El buen patrón", 120, "Comedia", "img1.jpg");
        Pelicula delicioso = new Pelicula(2, sesiones2, "Delicioso", 112, "Comedia-Historia", "Delicioso-886756010-mmed.jpg");
        Cine lagavia = new Cine(1, salas, sesiones, dias_apertura, festivos_cierre, LocalTime.of(10, 00), LocalTime.of(22, 00), "La Gavia", "914255401", "C/Adolfo Bioy Casares, 2", "Madrid", "40.369008,-3.599046","lagavia.jpg");
        
        Sala sala1 = new Sala(1,lagavia,asientos,sesiones,"Sala 1",4,12);
        Sala sala2 = new Sala(2,lagavia,asientos2,sesiones2,"Sala 2",4,12);
        salas.add(sala1);
        salas.add(sala2);

        int asiento_id = 1;
        for(int i=1;i<5;i++){
            for(int j=1;j<13;j++){
                asientos.add(new Asiento(asiento_id, sala1, i, j));
                asiento_id++;
            }
        }

        int asiento_idd = 1;
        for(int i=1;i<5;i++){
            for(int j=1;j<13;j++){
                asientos2.add(new Asiento(asiento_idd, sala2, i, j));
                asiento_idd++;
            }
        }

        sesiones.add(new Sesion(1, elbuenpatron, lagavia, sala1, entradas, LocalDateTime.of(2022, 03, 15, 12, 00), 10));
        sesiones.add(new Sesion(2, elbuenpatron, lagavia, sala1, entradas, LocalDateTime.of(2022, 03, 15, 15, 00), 7));
        sesiones.add(new Sesion(3, elbuenpatron, lagavia, sala1, entradas, LocalDateTime.of(2022, 03, 15, 18, 00), 0));
        sesiones.add(new Sesion(4, elbuenpatron, lagavia, sala1, entradas, LocalDateTime.of(2022, 03, 15, 21, 00), 2));
        sesiones2.add(new Sesion(5, delicioso, lagavia, sala2, entradas, LocalDateTime.of(2022, 03, 15, 15, 00), 6));
        sesiones2.add(new Sesion(6, delicioso, lagavia, sala2, entradas, LocalDateTime.of(2022, 03, 15, 18, 00), 1));

        peliculas.add(elbuenpatron);
        peliculas.add(delicioso);

        model.addAttribute("peliculas", peliculas);
        model.addAttribute("sesiones", sesiones);
       
        return "peliculas";
    }

    @GetMapping("/{id}")
    public String infoPelicula(@PathVariable long id, Model model) {
        List<Sesion> sesiones = new ArrayList<>();
        List<Sesion> sesiones2 = new ArrayList<>();
        List<Entrada> entradas = new ArrayList<>();
        List<Sala> salas = new ArrayList<>();
        List<Asiento> asientos = new ArrayList<>();
        List<Asiento> asientos2 = new ArrayList<>();
        List<Date> festivos_cierre = new ArrayList<>();
        List<DayOfWeek> dias_apertura = new ArrayList<>();
        dias_apertura.add(DayOfWeek.MONDAY);dias_apertura.add(DayOfWeek.TUESDAY);dias_apertura.add(DayOfWeek.WEDNESDAY);dias_apertura.add(DayOfWeek.THURSDAY);dias_apertura.add(DayOfWeek.FRIDAY);dias_apertura.add(DayOfWeek.SATURDAY);
        
        Pelicula elbuenpatron = new Pelicula(1, sesiones, "El buen patrón", 120, "Comedia", "img1.jpg");
        Cine lagavia = new Cine(1, salas, sesiones, dias_apertura, festivos_cierre, LocalTime.of(10, 00), LocalTime.of(22, 00), "La Gavia", "914255401", "C/Adolfo Bioy Casares, 2", "Madrid", "40.369008,-3.599046","lagavia.jpg");
        
        Sala sala1 = new Sala(1,lagavia,asientos,sesiones,"Sala 1",4,12);
        Sala sala2 = new Sala(2,lagavia,asientos2,sesiones2,"Sala 2",4,12);
        salas.add(sala1);
        salas.add(sala2);

        int asiento_id = 1;
        for(int i=1;i<5;i++){
            for(int j=1;j<13;j++){
                asientos.add(new Asiento(asiento_id, sala1, i, j));
                asiento_id++;
            }
        }

        int asiento_idd = 1;
        for(int i=1;i<5;i++){
            for(int j=1;j<13;j++){
                asientos2.add(new Asiento(asiento_idd, sala2, i, j));
                asiento_idd++;
            }
        }

        sesiones.add(new Sesion(1, elbuenpatron, lagavia, sala1, entradas, LocalDateTime.of(2022, 03, 15, 12, 00), 10));
        sesiones.add(new Sesion(2, elbuenpatron, lagavia, sala1, entradas, LocalDateTime.of(2022, 03, 15, 15, 00), 7));
        sesiones.add(new Sesion(3, elbuenpatron, lagavia, sala1, entradas, LocalDateTime.of(2022, 03, 15, 18, 00), 0));
        sesiones.add(new Sesion(4, elbuenpatron, lagavia, sala1, entradas, LocalDateTime.of(2022, 03, 15, 21, 00), 2));

        model.addAttribute("pelicula", elbuenpatron);
        model.addAttribute("sesiones", sesiones);
        return "pelicula";
    }

}