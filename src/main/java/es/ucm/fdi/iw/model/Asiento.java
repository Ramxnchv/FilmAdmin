@Entity

public class Asiento {
    private long id;
    private long idSala;
    private int fila;
    private int column;




    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getIdSala() {
        return idSala;
    }

    public Asiento(long id, long idSala, int fila, int column) {
        this.id = id;
        this.idSala = idSala;
        this.fila = fila;
        this.column = column;

    }

    public void setIdSala(long idSala) {
        this.idSala = idSala;
    }

    public int getFila() {
        return fila;
    }

    public void setFila(int fila) {
        this.fila = fila;
    }

    public int getColumn() {
        return column;
    }

    public void setColumn(int column) {
        this.column = column;
    }


}
