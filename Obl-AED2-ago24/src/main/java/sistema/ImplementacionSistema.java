package sistema;

import TADS.ArbolBB.ABB;
import dominio.Equipo;
import dominio.Jugador;
import dominio.Sucursal;
import interfaz.*;

public class ImplementacionSistema implements Sistema {

    private int maxSucursacles;

    private ABB<Sucursal> sucursales;
    private ABB<Equipo> equipos;
    private ABB<Jugador> jugadores;

    private ABB<Jugador> jugadoresPrincipiantes;
    private ABB<Jugador> jugadoresEstandar;
    private ABB<Jugador> jugadoresProfesionales;

    @Override
    public Retorno inicializarSistema(int maxSucursales) {
        if(maxSucursales <= 3){
            return  Retorno.error1("El sistema tiene que poder almacenar más de 3 sucursales");
        }
        this.maxSucursacles = maxSucursales;

        this.sucursales = new ABB<>();
        this.equipos = new ABB<>();
        this.jugadores = new ABB<>();

        this.jugadoresPrincipiantes = new ABB<>();
        this.jugadoresEstandar = new ABB<>();
        this.jugadoresProfesionales = new ABB<>();
        return Retorno.ok();
    }

    @Override
    public Retorno registrarJugador(String alias, String nombre, String apellido, Categoria categoria) {
        if(alias.isEmpty() || nombre.isEmpty() || apellido.isEmpty() || categoria == null){
            return Retorno.error1("Todos los parametros deben contar con un valor.");
        }else if(buscarJugador(alias) != null){
            return Retorno.error2("El jugador con alias " + alias + " ya existe en el sistema.");
        }

        Jugador nuevoJugador = new Jugador(alias, nombre, apellido, categoria);
        jugadores.insertar(nuevoJugador);

        agregarJuegadorACategoria(nuevoJugador);

        return Retorno.ok();
    }

    @Override
    public Retorno buscarJugador(String alias) {
        if(alias == null || alias.isEmpty()){
            return Retorno.error1("Ingrese un alias valido :)");
        }
        Jugador jugador = jugadores.encontrar(new Jugador(alias));
        if(jugador == null){
            return Retorno.error2("El jugador con alias " + alias + " no existe.");
        }
        return  Retorno.ok(jugador.toString());
    }

    @Override
    public Retorno listarJugadoresAscendente() {
        return Retorno.ok(jugadores.listarAscendente());
    }

    //TODO: Precondicion: La categoria no puede ser nula
    @Override
    public Retorno listarJugadoresPorCategoria(Categoria unaCategoria) {
        String listaJugadores = "";

        if(unaCategoria.getIndice() == Categoria.PRINCIPIANTE.getIndice()){
             listaJugadores = jugadoresPrincipiantes.listarAscendente();
        }else if(unaCategoria.getIndice() == Categoria.ESTANDARD.getIndice()){
            listaJugadores = jugadoresEstandar.listarAscendente();
        }else{
            listaJugadores = jugadoresProfesionales.listarAscendente();
        }

        return Retorno.ok(listaJugadores);
    }

    @Override
    public Retorno registrarEquipo(String nombre, String manager) {
        return Retorno.noImplementada();
    }

    @Override
    public Retorno agregarJugadorAEquipo(String nombreEquipo, String aliasJugador) {
        return Retorno.noImplementada();
    }

    @Override
    public Retorno listarJugadoresDeEquipo(String nombreEquipo) {
        return Retorno.noImplementada();
    }

    @Override
    public Retorno listarEquiposDescendente() {
        return Retorno.noImplementada();
    }

    @Override
    public Retorno registrarSucursal(String codigo, String nombre) {
        return Retorno.noImplementada();
    }

    @Override
    public Retorno registrarConexion(String codigoSucursal1, String codigoSucursal2, int latencia) {
        return Retorno.noImplementada();
    }

    @Override
    public Retorno actualizarConexion(String codigoSucursal1, String codigoSucursal2, int latencia) {
        return Retorno.noImplementada();
    }

    @Override
    public Retorno analizarSucursal(String codigoSucursal) {
        return Retorno.noImplementada();
    }

    @Override
    public Retorno sucursalesParaTorneo(String codigoSucursalAnfitriona, int latenciaLimite) {
        return Retorno.noImplementada();
    }

    // -------------------------------------------------------------------------------------- Metodos auxiliares
    private void agregarJuegadorACategoria(Jugador nuevoJugador) {
        if(nuevoJugador.getCategoria().getIndice() == Categoria.PRINCIPIANTE.getIndice()){
            jugadoresPrincipiantes.insertar(nuevoJugador);
        }else if(nuevoJugador.getCategoria().getIndice() == Categoria.ESTANDARD.getIndice()){
            jugadoresPrincipiantes.insertar(nuevoJugador);
        }else{
            jugadoresProfesionales.insertar(nuevoJugador);
        }
    }
}
