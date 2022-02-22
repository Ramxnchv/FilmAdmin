@Entity

public class Pelicula {
    private long id;
    private long titulo;
    private String duraccion;
    private String genero;
    private String img;

    public Pelicula(long id, long titulo, String duraccion, String genero, String img) {
        this.id = id;
        this.titulo = titulo;
        this.duraccion = duraccion;
        this.genero = genero;
        this.img = img;
    }

    public long getId() {

        return id;
    }


    public String getImg() {
        return img;
    }

    public void setId(long id) {
        this.id = id;
    }

    public void setImg(String img) {
        this.img = img;
    }

    public long getTitulo(){
        return titulo;
    }
    public void setTitulo(long titulo){
        this.titulo = titulo;
    }

    public String getDuraccion(){
        return duraccion;
    }
    public void setDuraccion(String duraccion){
        this.duraccion = duraccion;
    }

    public String getGenero(){
        return genero;
    }
    public void setGenero(String genero){
        this.genero = genero;
    }







}
