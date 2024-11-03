package dominio;

import TADS.ArbolBB.ABB;

@SuppressWarnings("unused")
public class Equipo implements Comparable<Equipo> {
    private String nombre;
    private String manager;

    private ABB<Jugador> jugadores;

    public  Equipo(String nombre, String manager) {
        this.nombre = nombre;
        this.manager = manager;
        this.jugadores = new ABB<>();
    }

    //Usado para búsquedas
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


    // methods
    public int obtenerCantidadDeJugadores() {
        return this.jugadores.longitud();
    }

    public void agregarJugador(Jugador jugador) {
        jugadores.insertar(jugador);
    }

    public String obtenerListadoDeJugadores() {
        return jugadores.listarAscendente();
    }

    @Override
    public int compareTo(Equipo o) {
        return this.nombre.compareTo(o.getNombre());
    }

    @Override
    public String toString() {
        return this.nombre + ";" + this.manager + ";" + this.obtenerCantidadDeJugadores();
    }

    @Override
    public boolean equals(Object obj) {
        return obj instanceof Equipo && this.nombre.equals(((Equipo) obj).nombre);
    }
}
