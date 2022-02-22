@Entity

public class CineSala {

    private long idsala;
    private long idcine;

    public CineSala(long idsala, long idcine) {
        this.idsala = idsala;
        this.idcine = idcine;
    }

    public long getIdsala() {
        return idsala;
    }

    public void setIdsala(long idsala) {
        this.idsala = idsala;
    }

    public long getIdcine() {
        return idcine;
    }

    public void setIdcine(long idcine) {
        this.idcine = idcine;
    }
}
