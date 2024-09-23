package sistema;

import interfaz.Categoria;
import interfaz.Retorno;
import org.junit.Before;
import org.junit.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TestCase03BuscarJugador {
    private ImplementacionSistema miSistema;
    Retorno retorno;

    @Before
    public void setUp() {
        miSistema = new ImplementacionSistema();
        miSistema.inicializarSistema(4);
    }

    @Test
    public void test03_Error01_BuscarJugador() {
        retorno = miSistema.buscarJugador("");
        assertEquals(Retorno.Resultado.ERROR_1, retorno.getResultado());
        retorno = miSistema.buscarJugador(null);
        assertEquals(Retorno.Resultado.ERROR_1, retorno.getResultado());
    }

    @Test
    public void test03_Error02_BuscarJugador() {
        retorno = miSistema.registrarJugador("El Pepe","Pedro","Pedrales", Categoria.ESTANDARD);
        assertEquals(Retorno.Resultado.OK, retorno.getResultado());
        retorno = miSistema.buscarJugador("El Pepes");
        assertEquals(Retorno.Resultado.ERROR_2, retorno.getResultado());
    }

    @Test
    public void test03_OK_BuscarJugador() {
        retorno = miSistema.registrarJugador("El Pepe","Pedro","Pedrales", Categoria.ESTANDARD);
        assertEquals(Retorno.Resultado.OK, retorno.getResultado());
        retorno = miSistema.buscarJugador("El Pepe");
        assertEquals(Retorno.Resultado.OK, retorno.getResultado());
    }

}
