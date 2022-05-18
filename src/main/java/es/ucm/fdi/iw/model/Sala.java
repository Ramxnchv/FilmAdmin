package es.ucm.fdi.iw.model;

import java.util.ArrayList;
import java.util.List;
import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.AllArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@NamedQuery(name = "Sala.FindAll", query = "SELECT s FROM Sala s")
@NamedQuery(name = "Sala.findByCine", query = "SELECT s FROM Sala s WHERE s.cine.id = :cineId")
public class Sala implements Transferable<Sala.Transfer> {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sala_id_seq")
    @SequenceGenerator(name = "sala_id_seq", sequenceName = "sala_id_seq", allocationSize = 1, initialValue = 17)   
    private long id;

    @ManyToOne(targetEntity = Cine.class)
    @JoinColumn(name="cine_id")
    @JsonIgnore
    private Cine cine;

    @OneToMany(targetEntity = Asiento.class)
    private List<Asiento> asientos = new ArrayList<>();

    @OneToMany(targetEntity = Sesion.class)
    @JsonIgnore
    private List<Sesion> sesiones = new ArrayList<>();

    private String nombre;
    private int filas;
    private int columnas;

    @Getter
    @AllArgsConstructor
    public static class Transfer {
        private long id;
        private String nombre;
        private int filas;
        private int columnas;
        private List<Asiento> asientos;
    }

    @Override
    public Transfer toTransfer() {
		return new Transfer(id, nombre, filas, columnas, asientos);
	}
	
	@Override
	public String toString() {
		return toTransfer().toString();
	}

}
