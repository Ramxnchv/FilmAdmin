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
public class Cine implements Transferable<Cine.Transfer> {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "gen")
    @SequenceGenerator(name = "gen", sequenceName = "gen")
    private long id;

    @OneToMany(targetEntity = Asiento.class)
    private List<Asiento> asientos = new ArrayList<>();

    @OneToMany(targetEntity = Sesion.class)
    private List<Sesion> sesiones = new ArrayList<>();

    private String horario;
    private String telefono;
    private String direccion;
    private String ciudad;
    private String urlmap;


    @Getter
    @AllArgsConstructor
    public static class Transfer {
		private long id;
        private List<Asiento> asientos;
        private List<Sesion> sesiones;
        private String horario;
        private String telefono;
        private String direccion;
        private String ciudad;
        private String urlmap;
    }


    @Override
    public Transfer toTransfer() {
		return new Transfer(id,	asientos, sesiones, horario, telefono, direccion, ciudad, urlmap);
	}
	
	@Override
	public String toString() {
		return toTransfer().toString();
	}

}
