package es.ucm.fdi.iw.model;

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
@Getter
@Setter
public class Asiento implements Transferable<Asiento.Transfer> {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "gen")
    @SequenceGenerator(name = "gen", sequenceName = "gen")
    private long id;

    @ManyToOne(targetEntity = Sala.class)
    @JoinColumn(name="sala_id")
    @JsonIgnore
    private Sala sala;

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
