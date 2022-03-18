package es.ucm.fdi.iw.model;

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
@Getter
@Setter
@NamedQuery(name = "Entrada.getAll", query = "SELECT e FROM Entrada e")
@NamedQuery(name = "Entrada.findBySesion", query = "SELECT e FROM Entrada e WHERE e.sesion.id = :sesionId")
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
        private List<Asiento> asientos;
        private Sesion sesion;
    }

    @Override
    public Transfer toTransfer() {
		return new Transfer(id,	asientos, sesion);
	}
	
	@Override
	public String toString() {
		return toTransfer().toString();
	}

}
