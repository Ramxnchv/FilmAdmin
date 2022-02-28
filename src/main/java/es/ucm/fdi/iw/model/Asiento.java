package es.ucm.fdi.iw.model;

import javax.persistence.*;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

@Entity
@Data
@NoArgsConstructor
public class Asiento implements Transferable<Asiento.Transfer> {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "gen")
    @SequenceGenerator(name = "gen", sequenceName = "gen")
    private long id;

    @ManyToOne(targetEntity = Cine.class)
    @JoinColumn(name="cine_id")
    private Cine cine;

    private int fila;
    private int columna;

    @Getter
    @AllArgsConstructor
    public static class Transfer {
        private long id;
        private Cine cine;
        private int fila;
        private int columna;
    }

    @Override
    public Transfer toTransfer() {
		return new Transfer(id,	cine, fila, columna);
	}
	
	@Override
	public String toString() {
		return toTransfer().toString();
	}

}
