package es.ucm.fdi.iw.model;

import javax.persistence.*;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

@Entity
@Data
@NoArgsConstructor
public class Cine {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "gen")
    @SequenceGenerator(name = "gen", sequenceName = "gen")
    private long id;

    private String horario;
    private String telefono;
    private String direccion;
    private String ciudad;
    private String urlmap;


    @Getter
    @AllArgsConstructor
    public static class Transfer {
		private long id;
        private String horario;
        private String telefono;
        private String direccion;
        private String ciudad;
        private String urlmap;
    }

}
