package es.ucm.fdi.iw.model;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Asiento implements Transferable<Asiento.Transfer> {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "asiento_id_seq")
    @SequenceGenerator(name = "asiento_id_seq", sequenceName = "asiento_id_seq", allocationSize = 1, initialValue = 97)
    private long id;

    @ManyToOne(targetEntity = Sala.class)
    @JoinColumn(name="sala_id")
    @JsonIgnore
    private Sala sala;

    @ManyToMany(targetEntity = Entrada.class, mappedBy = "asientos")
    @JsonIgnore
    private List<Entrada> entradas = new ArrayList<>();

    private int fila;
    private int columna;

    @Getter
    @AllArgsConstructor
    public static class Transfer {
        private long id;
        private int fila;
        private int columna;
    }

    @Override
    public Transfer toTransfer() {
		return new Transfer(id, fila, columna);
	}
	
	@Override
	public String toString() {
		return toTransfer().toString();
	}

}
