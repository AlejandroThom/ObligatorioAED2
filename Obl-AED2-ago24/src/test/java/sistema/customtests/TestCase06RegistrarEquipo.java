package sistema.customtests;

import interfaz.Retorno;
import org.junit.Before;
import org.junit.Test;
import sistema.ImplementacionSistema;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TestCase06RegistrarEquipo {
    private ImplementacionSistema miSistema;
    Retorno retorno;

    @Before
    public void setUp() {
        miSistema = new ImplementacionSistema();
        miSistema.inicializarSistema(4);
    }

    @Test
    public void test06_OK_RegistrarEquipo() {
        retorno = miSistema.registrarEquipo("Los magicos","El mago");
        assertEquals(Retorno.Resultado.OK, retorno.getResultado());
        retorno = miSistema.registrarEquipo("Los mancos","El manco");
        assertEquals(Retorno.Resultado.OK, retorno.getResultado());
    }

    @Test
    public void test06_Error01_RegistrarEquipo() {
        retorno = miSistema.registrarEquipo(null,"El mago");
        assertEquals(Retorno.Resultado.ERROR_1, retorno.getResultado());
        retorno = miSistema.registrarEquipo("","El mago");
        assertEquals(Retorno.Resultado.ERROR_1, retorno.getResultado());
        retorno = miSistema.registrarEquipo("Los magicos",null);
        assertEquals(Retorno.Resultado.ERROR_1, retorno.getResultado());
        retorno = miSistema.registrarEquipo("Los magicos","");
        assertEquals(Retorno.Resultado.ERROR_1, retorno.getResultado());
    }

    @Test
    public void test06_Error02_RegistrarEquipo() {
        retorno = miSistema.registrarEquipo("Los magicos","El mago");
        assertEquals(Retorno.Resultado.OK, retorno.getResultado());
        retorno = miSistema.registrarEquipo("Los magicos","El mago");
        assertEquals(Retorno.Resultado.ERROR_2, retorno.getResultado());
        retorno = miSistema.registrarEquipo("Los magicos","El manco");
        assertEquals(Retorno.Resultado.ERROR_2, retorno.getResultado());
        retorno = miSistema.registrarEquipo("Los mancos","El manco");
        assertEquals(Retorno.Resultado.OK, retorno.getResultado());
    }
}
