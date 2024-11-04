package sistema.customtests;

import interfaz.Categoria;
import interfaz.Retorno;
import org.junit.Before;
import org.junit.Test;
import sistema.ImplementacionSistema;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TestCase09ListarEquiposDescendente {
    private ImplementacionSistema miSistema;
    Retorno retorno;

    @Before
    public void setUp() {
        miSistema = new ImplementacionSistema();
        miSistema.inicializarSistema(4);
    }

    @Test
    public void Test09_OK_ListarEquiposDescendenteTest() {
        //Sin equipos
        retorno = miSistema.listarEquiposDescendente();
        assertEquals("",retorno.getValorString());

        //Un equipo sin jugadores
        retorno = miSistema.registrarEquipo("Los magicos","El mago");
        assertEquals(Retorno.Resultado.OK, retorno.getResultado());
        retorno = miSistema.listarEquiposDescendente();
        assertEquals("Los magicos;El mago;0",retorno.getValorString());

        //Un equipo con jugadores
        retorno = miSistema.registrarJugador("El Rober","Roberto","Roblares", Categoria.PROFESIONAL);
        assertEquals(Retorno.Resultado.OK, retorno.getResultado());
        retorno = miSistema.agregarJugadorAEquipo("Los magicos","El Rober");
        assertEquals(Retorno.Resultado.OK, retorno.getResultado());
        retorno = miSistema.listarEquiposDescendente();
        assertEquals("Los magicos;El mago;1",retorno.getValorString());

        //Multiples equipos
        retorno = miSistema.registrarEquipo("Los mancos","El manco");
        assertEquals(Retorno.Resultado.OK, retorno.getResultado());
        retorno = miSistema.listarEquiposDescendente();
        assertEquals("Los mancos;El manco;0|Los magicos;El mago;1",retorno.getValorString());
    }
}