package es.ucm.fdi.iw.model;

import javax.persistence.*;
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
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "gen")
    @SequenceGenerator(name = "gen", sequenceName = "gen")
    private long id;

    @ManyToOne(targetEntity = Sala.class)
    @JoinColumn(name="sala_id")
    private Sala sala;

    private int fila;
    private int columna;

    @Getter
    @AllArgsConstructor
    public static class Transfer {
        private long id;
        private Sala sala;
        private int fila;
        private int columna;
    }

    @Override
    public Transfer toTransfer() {
		return new Transfer(id,	sala, fila, columna);
	}
	
	@Override
	public String toString() {
		return toTransfer().toString();
	}

}
