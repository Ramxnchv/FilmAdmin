

public class Cine {


    private long id;
    private String horario;
    private String telefono;
    private String direccion;
    private String ciudad;
    private String urlmap;


    public Cine(long id, String horario, String telefono, String direccion, String ciudad, String urlmap) {
        this.id = id;
        this.horario = horario;
        this.telefono = telefono;
        this.direccion = direccion;
        this.ciudad = ciudad;
        this.urlmap = urlmap;
    }

    public String getUrlmap() {
        return urlmap;
    }

    public void setUrlmap(String urlmap) {
        this.urlmap = urlmap;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getHorario() {
        return horario;
    }

    public void setHorario(String horario) {
        this.horario = horario;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public String getCiudad() {
        return ciudad;
    }

    public void setCiudad(String ciudad) {
        this.ciudad = ciudad;
    }
}
