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
public class Sesion implements Transferable<Sesion.Transfer>{

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "gen")
    @SequenceGenerator(name = "gen", sequenceName = "gen")
    private long id;

    @ManyToOne(targetEntity = Pelicula.class)
    @JoinColumn(name="pelicula_id")
    private Pelicula pelicula;

    @ManyToOne(targetEntity = Cine.class)
    @JoinColumn(name="cine_id")
    private Cine cine;

    @OneToMany(targetEntity = Entrada.class)
    private List<Entrada> entradas = new ArrayList<>();

    private String hora;
    private int asientosLibres;
    
    @Getter
    @AllArgsConstructor
    public static class Transfer {
        private long id;
        private Pelicula pelicula;
        private Cine cine;
        private List<Entrada> entradas;
        private String hora;
        private int asientosLibres;
    }

    @Override
    public Transfer toTransfer() {
		return new Transfer(id,	pelicula, cine, entradas, hora, asientosLibres);
	}
	
	@Override
	public String toString() {
		return toTransfer().toString();
	}

}
