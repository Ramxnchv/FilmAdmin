package es.ucm.fdi.iw.model;

import java.time.LocalDateTime;
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
@NamedQuery(name = "Entrada.getAll", query = "SELECT e FROM Entrada e")
@NamedQuery(name = "Entrada.findBySesion", query = "SELECT e FROM Entrada e WHERE e.sesion.id = :sesionId")
@NamedQuery(name = "Entrada.findByUser", query = "SELECT e FROM Entrada e WHERE e.user.id = :userId")
@NamedQuery(name = "Entrada.findByCode", query = "SELECT e FROM Entrada e WHERE e.codigo = :codigo")
@Table(name = "ENTRADA")
public class Entrada implements Transferable<Entrada.Transfer>{

    @Id
    private long id;

    @ManyToOne(targetEntity = Sesion.class)
    @JoinColumn(name="sesion_id")
    private Sesion sesion;

    @ManyToOne(targetEntity = User.class)
    @JoinColumn(name="user_id")
    private User user;

    @ManyToMany(targetEntity = Asiento.class)
    private List<Asiento> asientos = new ArrayList<>();

    @Column(unique = true)
    private String codigo;

    private double preciofinal;

    private LocalDateTime horaValidacion;
    
    private boolean validate;
    
    @Getter
    @AllArgsConstructor
    public static class Transfer {
        private long id;
        private List<Asiento> asientos;
        private Sesion sesion;
        private String cine;
        private String pelicula;
        private LocalDateTime hora;
        private LocalDateTime horaValidacion;

        private boolean validate;
    }

    @Override
    public Transfer toTransfer() {
		return new Transfer(id,	asientos, sesion, sesion.getCine().getNombre(), sesion.getPelicula().getTitulo(), sesion.getDia_hora(),horaValidacion, validate);
	}
	
	@Override
	public String toString() {
		return toTransfer().toString();
	}

}
