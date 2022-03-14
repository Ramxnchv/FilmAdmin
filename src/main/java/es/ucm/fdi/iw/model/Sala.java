package es.ucm.fdi.iw.model;

import java.util.ArrayList;
import java.util.List;
import javax.persistence.*;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Sala implements Transferable<Sala.Transfer> {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "gen")
    @SequenceGenerator(name = "gen", sequenceName = "gen")
    private long id;

    @ManyToOne(targetEntity = Cine.class)
    @JoinColumn(name="cine_id")
    private Cine cine;

    @OneToMany(targetEntity = Asiento.class)
    private List<Asiento> asientos = new ArrayList<>();

    @OneToMany(targetEntity = Sesion.class)
    private List<Sesion> sesiones = new ArrayList<>();

    private String nombre;
    private int filas;
    private int columnas;

    @Getter
    @AllArgsConstructor
    public static class Transfer {
        private long id;
        private Cine cine;
        private List<Asiento> asientos;
        private List<Sesion> sesiones;
        private String nombre;
        private int filas;
        private int columnas;
    }

    @Override
    public Transfer toTransfer() {
		return new Transfer(id,	cine, asientos, sesiones, nombre, filas, columnas);
	}
	
	@Override
	public String toString() {
		return toTransfer().toString();
	}

}
