package sistema;

import TADS.ArbolBB.ABB;
import TADS.Lista.Lista;
import TADS.Lista.ListaConMaximo;
import dominio.Equipo;
import dominio.Jugador;
import dominio.Sucursal;
import interfaz.*;

public class ImplementacionSistema implements Sistema {

    private ListaConMaximo<Sucursal> sucursales;
    private ABB<Equipo> equipos;
    private ABB<Jugador> jugadores;

    private ABB<Jugador> principiantes;
    private ABB<Jugador> estandar;
    private ABB<Jugador> profesionales;

    @Override
    public Retorno inicializarSistema(int maxSucursales) {
        if(maxSucursales <= 3){
            return  Retorno.error1("El sistema tiene que poder almacenar más de 3 sucursales");
        }
        this.sucursales = new ListaConMaximo<>(maxSucursales);
        this.equipos = new ABB<>();
        this.jugadores = new ABB<>();
        this.principiantes = new ABB<>();
        this.estandar = new ABB<>();
        this.profesionales = new ABB<>();
        return Retorno.ok();
    }

    @Override
    public Retorno registrarJugador(String alias, String nombre, String apellido, Categoria categoria) {
        return Retorno.noImplementada();
    }

    @Override
    public Retorno buscarJugador(String alias) {
        return Retorno.noImplementada();
    }

    @Override
    public Retorno listarJugadoresAscendente() {
        return Retorno.noImplementada();
    }

    @Override
    public Retorno listarJugadoresPorCategoria(Categoria unaCategoria) {
        return Retorno.noImplementada();
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
}
