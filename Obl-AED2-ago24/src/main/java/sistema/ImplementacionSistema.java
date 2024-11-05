package sistema;

import TADS.ArbolBB.ABB;
import TADS.Grafo.Grafo;
import TADS.Lista.Lista;
import TADS.Pair.Pair;
import dominio.Equipo;
import dominio.Jugador;
import dominio.Sucursal;
import interfaz.*;

public class ImplementacionSistema implements Sistema {

    private int maxSucursacles;

    private Grafo<Sucursal> sucursales;
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

        this.sucursales = new Grafo<>(maxSucursales,false);
        this.equipos = new ABB<>();
        this.jugadores = new ABB<>();

        this.jugadoresPrincipiantes = new ABB<>();
        this.jugadoresEstandar = new ABB<>();
        this.jugadoresProfesionales = new ABB<>();
        return Retorno.ok();
    }

    @Override
    public Retorno registrarJugador(String alias, String nombre, String apellido, Categoria categoria) {

        if(nullOrEmpty(alias) || nullOrEmpty(nombre) || nullOrEmpty(apellido) || categoria == null){
            return Retorno.error1("Todos los parametros deben contar con un valor.");
        }else if(obtenerJugador(alias).getFirst() != null){
            return Retorno.error2("El jugador con alias " + alias + " ya existe en el sistema.");
        }

        Jugador nuevoJugador = new Jugador(alias, nombre, apellido, categoria);
        jugadores.insertar(nuevoJugador);

        agregarJuegadorACategoria(nuevoJugador);

        return Retorno.ok();
    }

    @Override
    public Retorno buscarJugador(String alias) {
        if(nullOrEmpty(alias)){
            return Retorno.error1("Ingrese un alias valido :)");
        }
        Pair<Jugador,Integer> dupla = obtenerJugador(alias);
        Jugador jugador = dupla.getFirst();
        if(jugador == null){
            return Retorno.error2("El jugador con alias " + alias + " no existe.");
        }
        return  Retorno.ok(dupla.getSecond(),dupla.getFirst().toString());
    }

    @Override
    public Retorno listarJugadoresAscendente() {
        return Retorno.ok(jugadores.listarAscendente());
    }

    //Precondicion: La categoria no puede ser nula
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
        if(nullOrEmpty(nombre) ||  nullOrEmpty(manager)){
            return Retorno.error1("Ingrese un nombre y un manager valido");
        }
        Pair<Equipo,Integer> dupla = buscarEquipo(nombre);
        Equipo equipo = dupla.getFirst();
        if(equipo != null){
            return Retorno.error2("El equipo con el nombre " + nombre + " ya existe.");
        }
        this.equipos.insertar(new Equipo(nombre, manager));
        return Retorno.ok();
    }

    @Override
    public Retorno agregarJugadorAEquipo(String nombreEquipo, String aliasJugador) {
        if(nullOrEmpty(nombreEquipo) || nullOrEmpty(aliasJugador)){
            return  Retorno.error1("Ingrese un nombre y un manager valido");
        }

        Pair<Equipo,Integer> dupla = buscarEquipo(nombreEquipo);
        Equipo equipo = dupla.getFirst();
        if(equipo == null){
            return Retorno.error2("El equipo " + nombreEquipo + " no existe.");
        }

        Pair<Jugador,Integer> duplaJugador = obtenerJugador(aliasJugador);
        Jugador jugador = duplaJugador.getFirst();
        if(jugador == null){
            return Retorno.error3("El jugador con alias " + aliasJugador + " no existe.");
        }

        if(equipo.obtenerCantidadDeJugadores() >= 5){
            return Retorno.error4("El equipo no puede tener mas de 5 jugadores.");
        }

        if(jugador.getCategoria().getIndice() != Categoria.PROFESIONAL.getIndice()){
            return Retorno.error5("El jugador debe ser profesional.");
        }

        if(jugador.tieneEquipo()){
            return Retorno.error6("El jugador ya tiene un equipo.");
        }

        equipo.agregarJugador(jugador);
        jugador.setEquipo(equipo);

        return Retorno.ok();
    }

    @Override
    public Retorno listarJugadoresDeEquipo(String nombreEquipo) {
        if(nullOrEmpty(nombreEquipo)){
            return Retorno.error1("Ingrese un nombre valido.");
        }
        Pair<Equipo,Integer> dupla = buscarEquipo(nombreEquipo);
        Equipo equipo = dupla.getFirst();
        if(equipo == null){
            return Retorno.error2("El equipo " + nombreEquipo + " no existe.");
        }
        return Retorno.ok(equipo.obtenerListadoDeJugadores());
    }

    @Override
    public Retorno listarEquiposDescendente() {
        return Retorno.ok(equipos.listarDescendente());
    }

    @Override
    public Retorno registrarSucursal(String codigo, String nombre) {
        if(sucursales.getCantidadActualVertices() >= maxSucursacles){
            return Retorno.error1("No se pueden registrar mas de " + maxSucursacles + " sucursales.");
        }
        if(nullOrEmpty(nombre) || nullOrEmpty(codigo)){
            return Retorno.error2("Ingrese un nombre y un codigo valido");
        }
        if(buscarSucursal(codigo) != null){
            return Retorno.error3("La sucursal " + codigo + " ya existe.");
        }

        Sucursal nuevaSucursal = new Sucursal(codigo, nombre);
        sucursales.agregarVertice(nuevaSucursal);

        return Retorno.ok();
    }

    @Override
    public Retorno registrarConexion(String codigoSucursal1, String codigoSucursal2, int latencia) {
        if(latencia < 0)
            return  Retorno.error1("La latencia no puede ser menor a 0");
        if(nullOrEmpty(codigoSucursal1) || nullOrEmpty(codigoSucursal2))
            return Retorno.error2("Ingrese un codigo valido");

        int suc1 = buscarPosSucursal(codigoSucursal1);
        int suc2 = buscarPosSucursal(codigoSucursal2);

        if(suc1 == -1 ){
            return Retorno.error3("La sucursal con codigo " + codigoSucursal1 + " no existe.");
        }else if(suc2 == -1 )
            return Retorno.error3("La sucursal con codigo " + codigoSucursal2 + " no existe.");

        if(sucursales.sonAdyacentes(suc1,suc2))
            return Retorno.error4("Las sucursales ya estan conectadas");

        sucursales.agregarArista(suc1,suc2,latencia);

        return Retorno.ok();
    }

    @Override
    public Retorno actualizarConexion(String codigoSucursal1, String codigoSucursal2, int latencia) {
        if(latencia < 0)
            return  Retorno.error1("La latencia no puede ser menor a 0");
        if(nullOrEmpty(codigoSucursal1) || nullOrEmpty(codigoSucursal2))
            return Retorno.error2("Ingrese un codigo valido");

        int suc1 = buscarPosSucursal(codigoSucursal1);
        int suc2 = buscarPosSucursal(codigoSucursal2);

        if(suc1 == -1 ){
            return Retorno.error3("La sucursal con codigo " + codigoSucursal1 + " no existe.");
        }else if(suc2 == -1 )
            return Retorno.error3("La sucursal con codigo " + codigoSucursal2 + " no existe.");

        if(!sucursales.sonAdyacentes(suc1,suc2))
            return Retorno.error4("Las sucursales no estan conectadas");

        sucursales.actualizarArista(suc1,suc2,latencia);

        return Retorno.ok();
    }


    @Override
    public Retorno analizarSucursal(String codigoSucursal) {
        if(nullOrEmpty(codigoSucursal) )
            return Retorno.error1("Ingrese un codigo valido");
        //Sucursal sucursal = buscarSucursal(codigoSucursal);
        //con la solucion O(n),busqueda de la posicion + O(1) en saber si es critico
        int posSucursal = buscarPosSucursal(codigoSucursal);
        if(posSucursal == -1){
            return  Retorno.error2("La sucursal con codigo " + codigoSucursal + " no existe.");
        }
        return sucursales.verticeEsCritico(posSucursal) ? Retorno.ok("SI") : Retorno.ok("NO");
        //return sucursales.verticeEsCritico(sucursal) ? Retorno.ok("SI") : Retorno.ok("NO");
    }

    @Override
    public Retorno sucursalesParaTorneo(String codigoSucursalAnfitriona, int latenciaLimite) {
        if(nullOrEmpty(codigoSucursalAnfitriona) )
            return Retorno.error1("Ingrese un codigo valido");
        int sucursalPos = buscarPosSucursal(codigoSucursalAnfitriona);
        if(sucursalPos == -1){
            return  Retorno.error2("La sucursal con codigo " + codigoSucursalAnfitriona + " no existe.");
        }
        if(latenciaLimite<=0){
            return Retorno.error3("La latencia no puede ser menor o igual a 0");
        }
        //logica
        //SE REALIZA UN BFS PARA AGREGAR A TODAS LAS SUCURSALES QUE ESTAN EN EL LIMITE
        Pair<Lista<Sucursal>,Integer> dupla = sucursales.aristasConectadasAConMenosPesoA(sucursalPos,latenciaLimite);
        return Retorno.ok(dupla.getSecond(),dupla.getFirst().mostrar());
    }

    // -------------------------------------------------------------------------------------- Metodos auxiliares

    private int buscarPosSucursal(String codigoSucursal) {
        return sucursales.buscarPosicionVertice(new Sucursal(codigoSucursal));
    }

    private void agregarJuegadorACategoria(Jugador nuevoJugador) {
        if(nuevoJugador.getCategoria().getIndice() == Categoria.PRINCIPIANTE.getIndice()){
            jugadoresPrincipiantes.insertar(nuevoJugador);
        }else if(nuevoJugador.getCategoria().getIndice() == Categoria.ESTANDARD.getIndice()){
            jugadoresEstandar.insertar(nuevoJugador);
        }else{
            jugadoresProfesionales.insertar(nuevoJugador);
        }
    }

    private Pair<Equipo,Integer> buscarEquipo(String nombre) {
            return this.equipos.encontrar(new Equipo(nombre));
    }

    private Pair<Jugador,Integer> obtenerJugador(String aliasJugador) {
        return jugadores.encontrar(new Jugador(aliasJugador));
    }

    private Sucursal buscarSucursal(String codigo) {
        return sucursales.obtenerVertice(new Sucursal(codigo));
    }

    private boolean nullOrEmpty(String texto) {
        return texto == null || texto.isEmpty();
    }

    public Retorno cantidadDeJugadores(){
        return Retorno.ok(this.jugadores.longitud());
    }

    public Retorno cantidadSucursales(){
        return Retorno.ok(this.sucursales.getCantidadActualVertices());
    }

    public Retorno cantidadEquipos(){
        return Retorno.ok(this.equipos.longitud());
    }
    public Retorno cantidadConexiones(){
        return Retorno.ok(this.sucursales.getCantidadAristas());
    }


}
