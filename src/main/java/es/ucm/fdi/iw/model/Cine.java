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
import lombok.Setter;
import lombok.AllArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
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
    private String coordenadas;
    private String imagen;


    @Getter
    @AllArgsConstructor
    public static class Transfer {
		    private long id;
        private LocalTime hora_apertura;
        private LocalTime hora_cierre;
        private String nombre;
        private String telefono;
        private String direccion;
        private String ciudad;
        private String coordenadas;
        private String imagen;
        
    }


    @Override
    public Transfer toTransfer() {
		return new Transfer(id,hora_apertura, hora_cierre, nombre, telefono, direccion, ciudad, coordenadas, imagen);
	}
	
	@Override
	public String toString() {
		return toTransfer().toString();
	}

}
