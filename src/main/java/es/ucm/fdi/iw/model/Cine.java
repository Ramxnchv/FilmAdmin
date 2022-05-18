package es.ucm.fdi.iw.model;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.MonthDay;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.persistence.*;

import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.AllArgsConstructor;

import es.ucm.fdi.iw.model.converters.MonthDayToDateConverter;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@NamedQuery(name = "Cine.FindAll", query = "SELECT c FROM Cine c")


public class Cine implements Transferable<Cine.Transfer> {


    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "cine_id_seq")
    @SequenceGenerator(name = "cine_id_seq", sequenceName = "cine_id_seq", allocationSize = 1, initialValue = 6)
    private long id;

    @OneToMany(targetEntity = Sala.class)
    private List<Sala> salas = new ArrayList<>();

    @OneToMany(targetEntity = Sesion.class)
    private List<Sesion> sesiones = new ArrayList<>();

    @ElementCollection
    @CollectionTable(name = "cine_dias_apertura", joinColumns = @JoinColumn(name = "cine_id"))
    @Column(name = "dia_apertura")
    private List<DayOfWeek> dias_apertura = new ArrayList<>();

    @ElementCollection
    @CollectionTable(name = "cine_festivos_cierre", joinColumns = @JoinColumn(name = "cine_id"))
    @Column(name = "festivo_cierre")
    @Convert(converter = MonthDayToDateConverter.class)
    private List<MonthDay> festivos_cierre = new ArrayList<>();

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
        private List<DayOfWeek> dias_apertura;
        private List<MonthDay> festivos_cierre;
        private String nombre;
        private String telefono;
        private String direccion;
        private String ciudad;
        private String coordenadas;
        private String imagen;
    }


    @Override
    public Transfer toTransfer() {
		  return new Transfer(id,hora_apertura, hora_cierre, dias_apertura, festivos_cierre, nombre, telefono, direccion, ciudad, coordenadas, imagen);
	  }
	
	@Override
	public String toString() {
		return toTransfer().toString();
	}

}
