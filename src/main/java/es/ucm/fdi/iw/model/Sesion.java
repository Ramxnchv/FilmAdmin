@Entity

public class Sesion {
    private long id;
    private long idSala;
    private String hora;
    private long numeroEntradas;
    private long idPelicula;

    public Sesion(long id, long idSala, String hora, long numeroEntradas, long idPelicula) {
        this.id = id;
        this.idSala = idSala;
        this.hora = hora;
        this.numeroEntradas = numeroEntradas;
        this.idPelicula = idPelicula;
    }


    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getIdSala() {
        return idSala;
    }

    public void setIdSala(long idSala) {
        this.idSala = idSala;
    }

    public String getHora() {
        return hora;
    }

    public void setHora(String hora) {
        this.hora = hora;
    }

    public long getNumeroEntradas() {
        return numeroEntradas;
    }

    public void setNumeroEntradas(long numeroEntradas) {
        this.numeroEntradas = numeroEntradas;
    }

    public long getIdPelicula() {
        return idPelicula;
    }

    public void setIdPelicula(long idPelicula) {
        this.idPelicula = idPelicula;
    }
}
