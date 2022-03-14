package es.ucm.fdi.iw.model;

import java.time.LocalDateTime;
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

    @ManyToOne(targetEntity = Cine.class)
    @JoinColumn(name="sala_id")
    private Sala sala;

    @OneToMany(targetEntity = Entrada.class)
    private List<Entrada> entradas = new ArrayList<>();

    private LocalDateTime dia_hora;
    private int asientosLibres;
    
    @Getter
    @AllArgsConstructor
    public static class Transfer {
        private long id;
        private Pelicula pelicula;
        private Cine cine;
        private Sala sala;
        private List<Entrada> entradas;
        private LocalDateTime dia_hora;
        private int asientosLibres;
    }

    @Override
    public Transfer toTransfer() {
		return new Transfer(id,	pelicula, cine, sala, entradas, dia_hora, asientosLibres);
	}
	
	@Override
	public String toString() {
		return toTransfer().toString();
	}

}
