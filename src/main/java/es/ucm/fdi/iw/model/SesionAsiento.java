@Entity

public class SesionAsiento {

    private long idasiento;
    private long idsesion;
    private boolean ocupado;

    public SesionAsiento(long idasiento, long idsesion, boolean ocupado) {
        this.idasiento = idasiento;
        this.idsesion = idsesion;
        this.ocupado = ocupado;
    }

    public long getIdasiento() {
        return idasiento;
    }

    public void setIdasiento(long idasiento) {
        this.idasiento = idasiento;
    }

    public long getIdsesion() {
        return idsesion;
    }

    public void setIdsesion(long idsesion) {
        this.idsesion = idsesion;
    }

    public boolean isOcupado() {
        return ocupado;
    }

    public void setOcupado(boolean ocupado) {
        this.ocupado = ocupado;
    }
}
