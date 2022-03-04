package es.ucm.fdi.iw.model;

import java.time.DayOfWeek;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.persistence.*;

import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@NamedQuery(name = "Cine.FindAll", query = "SELECT c FROM Cine c")


public class Cine implements Transferable<Cine.Transfer> {


    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "gen")
    @SequenceGenerator(name = "gen", sequenceName = "gen")
    private long id;

    @OneToMany(targetEntity = Sala.class)
    private List<Sala> salas = new ArrayList<>();

    @OneToMany(targetEntity = Sesion.class)
    private List<Sesion> sesiones = new ArrayList<>();

    @ElementCollection
    private List<DayOfWeek> dias_apertura = new ArrayList<>();

    @ElementCollection
    private List<Date> festivos_cierre = new ArrayList<>();

    private LocalTime hora_apertura;
    private LocalTime hora_cierre;
    private String nombre;
    private String telefono;
    private String direccion;
    private String ciudad;
    private String urlmap;
    private String imagen;


    @Getter
    @AllArgsConstructor
    public static class Transfer {
		    private long id;
        private List<Sala> salas;
        private List<Sesion> sesiones;
        private List<DayOfWeek> dias_apertura;
        private List<Date> festivos_cierre;
        private LocalTime hora_apertura;
        private LocalTime hora_cierre;
        private String nombre;
        private String telefono;
        private String direccion;
        private String ciudad;
        private String urlmap;
        private String imagen;
        
    }


    @Override
    public Transfer toTransfer() {
		return new Transfer(id,	salas, sesiones, dias_apertura, festivos_cierre, hora_apertura, hora_cierre, nombre, telefono, direccion, ciudad, urlmap, imagen);
	}
	
	@Override
	public String toString() {
		return toTransfer().toString();
	}

}
