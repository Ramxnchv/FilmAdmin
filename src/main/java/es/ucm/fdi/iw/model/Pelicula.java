package es.ucm.fdi.iw.model;

import java.util.ArrayList;
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
@NamedQuery(name = "Pelicula.getAll", query = "SELECT DISTINCT p FROM Sesion ss JOIN ss.cine c JOIN ss.pelicula p JOIN ss.sala sl")
@NamedQuery(name = "Pelicula.getList", query = "SELECT p FROM Pelicula p")
public class Pelicula implements Transferable<Pelicula.Transfer> {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "pelicula_id_seq")
    @SequenceGenerator(name = "pelicula_id_seq", sequenceName = "pelicula_id_seq", allocationSize = 1, initialValue = 6)    
    private long id;

    @OneToMany(targetEntity = Sesion.class)
    private List<Sesion> sesiones = new ArrayList<>();

    private String titulo;
    private int duraccion;
    private String genero;
    private String img;


    @Getter
    @AllArgsConstructor
    public static class Transfer {
		    private long id;
        private String titulo;
        private int duraccion;
        private String genero;
        private String img;
    }

    @Override
    public Transfer toTransfer() {
		return new Transfer(id,	titulo, duraccion, genero, img);
	}
	
	@Override
	public String toString() {
		return toTransfer().toString();
	}

}

