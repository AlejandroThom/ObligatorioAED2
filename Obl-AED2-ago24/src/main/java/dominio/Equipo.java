package dominio;

import TADS.ArbolBB.ABB;

public class Equipo implements Comparable<Equipo> {
    private String nombre;
    private String manager;

    private ABB<Jugador> jugadores ;

    public  Equipo(String nombre, String manager) {
        this.nombre = nombre;
        this.manager = manager;
        this.jugadores = new ABB<>();
    }

    //Usado para busquedas
    public Equipo(String nombre) {
        this.nombre = nombre;
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


    // Metodos
    public int obtenerCantidadDeJugadores() {
        return this.jugadores.longitud();
    }

    @Override
    public int compareTo(Equipo o) {
        return this.nombre.compareTo(o.getNombre());
    }

    public void agregarJugador(Jugador jugador) {
        jugadores.insertar(jugador);
    }
}
