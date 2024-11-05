package sistema.customtests;

import interfaz.Categoria;
import interfaz.Retorno;
import org.junit.Before;
import org.junit.Test;
import sistema.ImplementacionSistema;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TestCase04ListarJugadoresAscendentemente {
    private ImplementacionSistema miSistema;
    Retorno retorno;

    @Before
    public void setUp() {
        miSistema = new ImplementacionSistema();
        miSistema.inicializarSistema(4);
    }

    @Test
    public void test04_OK_ListarJugadoresAscendentemente() {
        //Vacio
        retorno = miSistema.listarJugadoresAscendente();
        assertEquals(retorno.getValorString(),"");

        //Solo 1 elemento
        retorno = miSistema.registrarJugador("El Pepe","Pedro","Pedrales", Categoria.ESTANDARD);
        assertEquals(Retorno.Resultado.OK, retorno.getResultado());
        retorno = miSistema.listarJugadoresAscendente();
        assertEquals("El Pepe;Pedro;Pedrales;ESTANDARD",retorno.getValorString());

        //Mas de un elemento
        retorno = miSistema.registrarJugador("El Rober","Roberto","Roblares", Categoria.PRINCIPIANTE);
        assertEquals(Retorno.Resultado.OK, retorno.getResultado());
        retorno = miSistema.listarJugadoresAscendente();
        assertEquals("El Pepe;Pedro;Pedrales;ESTANDARD|El Rober;Roberto;Roblares;PRINCIPIANTE",retorno.getValorString());
    }
}
