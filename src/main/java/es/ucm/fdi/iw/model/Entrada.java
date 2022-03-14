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
public class Entrada implements Transferable<Entrada.Transfer>{

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "gen")
    @SequenceGenerator(name = "gen", sequenceName = "gen")
    private long id;

    @ManyToOne(targetEntity = Sesion.class)
    @JoinColumn(name="sesion_id")
    private Sesion sesion;

    @ManyToOne(targetEntity = User.class)
    @JoinColumn(name="user_id")
    private User user;

    @OneToMany(targetEntity = Asiento.class)
    private List<Asiento> asientos = new ArrayList<>();

    private String codigo;
    private double preciofinal;
    
    @Getter
    @AllArgsConstructor
    public static class Transfer {
        private long id;
        private Sesion sesion;
        private User user;
        private List<Asiento> asientos;
        private double preciofinal;
    }

    @Override
    public Transfer toTransfer() {
		return new Transfer(id,	sesion, user, asientos, preciofinal);
	}
	
	@Override
	public String toString() {
		return toTransfer().toString();
	}

}
