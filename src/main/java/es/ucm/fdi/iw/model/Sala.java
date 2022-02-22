@Entity

public class Sala {

    private long id;
    private long fila;
    private long columna;

    public Sala(long id, long fila, long columna) {
        this.id = id;
        this.fila = fila;
        this.columna = columna;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getFila() {
        return fila;
    }

    public void setFila(long fila) {
        this.fila = fila;
    }

    public long getColumna() {
        return columna;
    }

    public void setColumna(long columna) {
        this.columna = columna;
    }
}
