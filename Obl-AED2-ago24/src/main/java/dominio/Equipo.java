package dominio;

public class Equipo {
    private String nombre;
    private String manager;

    //private List<Jugador> jugadores; TODO: ImplementarListas

    public  Equipo(String nombre, String manager) {
        this.nombre = nombre;
        this.manager = manager;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getManager() {
        return manager;
    }

    public void setManager(String manager) {
        this.manager = manager;
    }
}
