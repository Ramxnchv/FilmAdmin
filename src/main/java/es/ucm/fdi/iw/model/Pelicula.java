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
public class Pelicula implements Transferable<Pelicula.Transfer> {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "gen")
    @SequenceGenerator(name = "gen", sequenceName = "gen")
    private long id;

    @OneToMany(targetEntity = Sesion.class)
    private List<Sesion> sesiones = new ArrayList<>();

    private long titulo;
    private String duraccion;
    private String genero;
    private String img;


    @Getter
    @AllArgsConstructor
    public static class Transfer {
		private long id;
        private long titulo;
        private List<Sesion> sesiones;
        private String duraccion;
        private String genero;
        private String img;
    }

    @Override
    public Transfer toTransfer() {
		return new Transfer(id,	titulo, sesiones, duraccion, genero, img);
	}
	
	@Override
	public String toString() {
		return toTransfer().toString();
	}

}

