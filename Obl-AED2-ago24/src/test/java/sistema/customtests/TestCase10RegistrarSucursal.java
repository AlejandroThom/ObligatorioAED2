package sistema.customtests;

import interfaz.Retorno;
import org.junit.Before;
import org.junit.Test;
import sistema.ImplementacionSistema;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TestCase10RegistrarSucursal {
    private ImplementacionSistema miSistema;
    Retorno retorno;

    @Before
    public void setUp() {
        miSistema = new ImplementacionSistema();
        miSistema.inicializarSistema(4);
    }

    @Test
    public void TestCase10RegistrarSucursal_TestOK() {
        retorno = miSistema.registrarSucursal("UYU-MVD-01","La Montevideana");
        assertEquals(Retorno.Resultado.OK, retorno.getResultado());
        retorno = miSistema.registrarSucursal("PER-LIM-01","La Picante");
        assertEquals(Retorno.Resultado.OK, retorno.getResultado());
        retorno = miSistema.registrarSucursal("ARG-BAS-01","La Capitalina");
        assertEquals(Retorno.Resultado.OK, retorno.getResultado());
        retorno = miSistema.registrarSucursal("UYU-MVD-02","Porque pinto");
        assertEquals(Retorno.Resultado.OK, retorno.getResultado());
    }

    @Test
    public void TestCase10RegistrarSucursal_TestError01() {
        retorno = miSistema.registrarSucursal("UYU-MVD-01","La Montevideana");
        assertEquals(Retorno.Resultado.OK, retorno.getResultado());
        retorno = miSistema.registrarSucursal("PER-LIM-01","La Picante");
        assertEquals(Retorno.Resultado.OK, retorno.getResultado());
        retorno = miSistema.registrarSucursal("ARG-BAS-01","La Capitalina");
        assertEquals(Retorno.Resultado.OK, retorno.getResultado());
        retorno = miSistema.registrarSucursal("UYU-MVD-02","Porque pinto");
        assertEquals(Retorno.Resultado.OK, retorno.getResultado());
        retorno = miSistema.registrarSucursal("UYU-MVD-03","Ya no sabemos que inventar");
        assertEquals(Retorno.Resultado.ERROR_1, retorno.getResultado());
    }

    @Test
    public void TestCase10RegistrarSucursal_TestError02() {
        retorno = miSistema.registrarSucursal(null,"La Montevideana");
        assertEquals(Retorno.Resultado.ERROR_2, retorno.getResultado());
        retorno = miSistema.registrarSucursal("","La Montevideana");
        assertEquals(Retorno.Resultado.ERROR_2, retorno.getResultado());
        retorno = miSistema.registrarSucursal("UYU-MVD-01",null);
        assertEquals(Retorno.Resultado.ERROR_2, retorno.getResultado());
        retorno = miSistema.registrarSucursal("UYU-MVD-01","");
        assertEquals(Retorno.Resultado.ERROR_2, retorno.getResultado());
    }

    @Test
    public void TestCase10RegistrarSucursal_TestError03() {
        retorno = miSistema.registrarSucursal("UYU-MVD-01","La Montevideana");
        assertEquals(Retorno.Resultado.OK, retorno.getResultado());
        retorno = miSistema.registrarSucursal("PER-LIM-01","La Picante");
        assertEquals(Retorno.Resultado.OK, retorno.getResultado());
        retorno = miSistema.registrarSucursal("ARG-BAS-01","La Capitalina");
        assertEquals(Retorno.Resultado.OK, retorno.getResultado());
        retorno = miSistema.registrarSucursal("UYU-MVD-01","La copiona");
        assertEquals(Retorno.Resultado.ERROR_3, retorno.getResultado());
    }
}