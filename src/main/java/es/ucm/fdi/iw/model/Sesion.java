package es.ucm.fdi.iw.model;

import java.time.LocalDateTime;
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
@NamedQuery(name = "Sesion.getAll", query = "SELECT ss FROM Sesion ss JOIN ss.cine c JOIN ss.pelicula p JOIN ss.sala sl")
@NamedQuery(name = "Sesion.getList", query = "SELECT ss FROM Sesion ss")
public class Sesion implements Transferable<Sesion.Transfer>{

    @Id
    private long id;

    @ManyToOne(targetEntity = Pelicula.class)
    @JoinColumn(name="pelicula_id")
    @JsonIgnore
    private Pelicula pelicula;

    @ManyToOne(targetEntity = Cine.class)
    @JsonIgnore
    @JoinColumn(name="cine_id")
    private Cine cine;

    @ManyToOne(targetEntity = Sala.class)
    @JoinColumn(name="sala_id")
    private Sala sala;

    @OneToMany(targetEntity = Entrada.class)
    @JsonIgnore
    private List<Entrada> entradas = new ArrayList<>();

    @JsonIgnore
    private LocalDateTime dia_hora;
    
    private int asientosLibres;
    
    @Getter
    @AllArgsConstructor
    public static class Transfer {
        private long id;
        private Sala sala;
        private int asientosLibres;
    }

    @Override
    public Transfer toTransfer() {
		return new Transfer(id, sala, asientosLibres);
	}
	
	@Override
	public String toString() {
		return toTransfer().toString();
	}

}
