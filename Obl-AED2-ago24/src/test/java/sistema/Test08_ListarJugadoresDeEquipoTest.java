package sistema;

import interfaz.Categoria;
import interfaz.Retorno;
import org.junit.Before;
import org.junit.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class Test08_ListarJugadoresDeEquipoTest {
    private ImplementacionSistema miSistema;
    Retorno retorno;

    @Before
    public void setUp() {
        miSistema = new ImplementacionSistema();
        miSistema.inicializarSistema(4);
    }

    @Test
    public void Test08_OK_ListarJugadoresDeEquipoTest() {
        retorno = miSistema.registrarJugador("El Rober","Roberto","Roblares", Categoria.PROFESIONAL);
        assertEquals(Retorno.Resultado.OK, retorno.getResultado());
        retorno = miSistema.registrarJugador("El Querto","Querty","Quertales", Categoria.PROFESIONAL);
        assertEquals(Retorno.Resultado.OK, retorno.getResultado());
        retorno = miSistema.registrarJugador("El Pepe","Pedro","Pedrales", Categoria.PROFESIONAL);
        assertEquals(Retorno.Resultado.OK, retorno.getResultado());
        retorno = miSistema.registrarEquipo("Los magicos","El mago");
        assertEquals(Retorno.Resultado.OK, retorno.getResultado());
        retorno = miSistema.agregarJugadorAEquipo("Los magicos","El Rober");
        assertEquals(Retorno.Resultado.OK, retorno.getResultado());
        retorno = miSistema.agregarJugadorAEquipo("Los magicos","El Querto");
        assertEquals(Retorno.Resultado.OK, retorno.getResultado());
        retorno = miSistema.agregarJugadorAEquipo("Los magicos","El Pepe");
        assertEquals(Retorno.Resultado.OK, retorno.getResultado());

        retorno = miSistema.listarJugadoresDeEquipo("Los magicos");
        assertEquals(Retorno.Resultado.OK, retorno.getResultado());
        assertEquals("El Pepe;Pedro;Pedrales;Profesional|El Querto;Querty;Quertales;Profesional|El Rober;Roberto;Roblares;Profesional", retorno.getValorString());
    }

    @Test
    public void Test08_Error01_ListarJugadoresDeEquipoTest() {
        retorno = miSistema.registrarJugador("El Rober","Roberto","Roblares", Categoria.PROFESIONAL);
        assertEquals(Retorno.Resultado.OK, retorno.getResultado());
        retorno = miSistema.registrarEquipo("Los magicos","El mago");
        assertEquals(Retorno.Resultado.OK, retorno.getResultado());
        retorno = miSistema.agregarJugadorAEquipo("Los magicos","El Rober");
        assertEquals(Retorno.Resultado.OK, retorno.getResultado());

        retorno = miSistema.listarJugadoresDeEquipo(null);
        assertEquals(Retorno.Resultado.ERROR_1, retorno.getResultado());
        retorno = miSistema.listarJugadoresDeEquipo("");
        assertEquals(Retorno.Resultado.ERROR_1, retorno.getResultado());
    }

    @Test
    public void Test08_Error02_ListarJugadoresDeEquipoTest() {
        retorno = miSistema.registrarJugador("El Rober","Roberto","Roblares", Categoria.PROFESIONAL);
        assertEquals(Retorno.Resultado.OK, retorno.getResultado());
        retorno = miSistema.registrarEquipo("Los magicos","El mago");
        assertEquals(Retorno.Resultado.OK, retorno.getResultado());
        retorno = miSistema.agregarJugadorAEquipo("Los magicos","El Rober");
        assertEquals(Retorno.Resultado.OK, retorno.getResultado());

        retorno = miSistema.listarJugadoresDeEquipo("Los Mancos");
        assertEquals(Retorno.Resultado.ERROR_2, retorno.getResultado());
    }
}