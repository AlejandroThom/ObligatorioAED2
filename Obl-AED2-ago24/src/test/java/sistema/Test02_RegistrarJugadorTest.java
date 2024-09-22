package sistema;

import dominio.Jugador;
import interfaz.Categoria;
import interfaz.Retorno;
import interfaz.Sistema;
import org.junit.Before;
import org.junit.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class Test02_RegistrarJugadorTest {
    private ImplementacionSistema miSistema;
    Retorno retorno;


    @Before
    public void setUp() {
        miSistema = new ImplementacionSistema();
        miSistema.inicializarSistema(4);
    }

    @Test
    public void test_Error01_RegistrarJugador() {
        retorno = miSistema.registrarJugador(null,"Pedro","Pedrales", Categoria.ESTANDARD);
        assertEquals(Retorno.Resultado.ERROR_1, retorno.getResultado());
        retorno = miSistema.registrarJugador("","Pedro","Pedrales", Categoria.ESTANDARD);
        assertEquals(Retorno.Resultado.ERROR_1, retorno.getResultado());
        retorno = miSistema.registrarJugador("ElPepe",null,"Pedrales", Categoria.ESTANDARD);
        assertEquals(Retorno.Resultado.ERROR_1, retorno.getResultado());
        retorno = miSistema.registrarJugador("ElPepe","","Pedrales", Categoria.ESTANDARD);
        assertEquals(Retorno.Resultado.ERROR_1, retorno.getResultado());
        retorno = miSistema.registrarJugador("ElPepe","Pedro",null, Categoria.ESTANDARD);
        assertEquals(Retorno.Resultado.ERROR_1, retorno.getResultado());
        retorno = miSistema.registrarJugador("ElPepe","Pedro","", Categoria.ESTANDARD);
        assertEquals(Retorno.Resultado.ERROR_1, retorno.getResultado());
        retorno = miSistema.registrarJugador("ElPepe","Pedro","Pedrales", null);
        assertEquals(Retorno.Resultado.ERROR_1, retorno.getResultado());
    }

    @Test
    public void test_Error02_RegistrarJugador() {
        retorno = miSistema.registrarJugador("El Pepe","Pedro","Pedrales", Categoria.ESTANDARD);
        assertEquals(Retorno.Resultado.OK, retorno.getResultado());
        retorno = miSistema.registrarJugador("El Pepe","Pedro","Pedrales", Categoria.ESTANDARD);
        assertEquals(Retorno.Resultado.ERROR_2, retorno.getResultado());
    }

    @Test
    public void test_OK_RegistrarJugador() {
        retorno = miSistema.registrarJugador("El Pepe","Pedro","Pedrales", Categoria.ESTANDARD);
        assertEquals(Retorno.Resultado.OK, retorno.getResultado());
        retorno = miSistema.registrarJugador("El Rober","Roberto","Roblares", Categoria.PRINCIPIANTE);
        assertEquals(Retorno.Resultado.OK, retorno.getResultado());
        retorno = miSistema.registrarJugador("El Quero","Querty","Quertares", Categoria.PROFESIONAL);
        assertEquals(Retorno.Resultado.OK, retorno.getResultado());
        assertEquals(3,miSistema.cantidadDeJugadores().getValorInteger());
    }
}
