package sistema.customtests;

import interfaz.Retorno;
import org.junit.Before;
import org.junit.Test;
import sistema.ImplementacionSistema;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TestCase12ActualizarConexion {
    private ImplementacionSistema miSistema;
    Retorno retorno;

    @Before
    public void setUp() {
        miSistema = new ImplementacionSistema();
        miSistema.inicializarSistema(4);
    }
    @Test
    public void TestCase12RegistrarSucursal_TestOK() {
        retorno = miSistema.registrarSucursal("UYU-MVD-01","La Montevideana");
        assertEquals(Retorno.Resultado.OK, retorno.getResultado());
        retorno = miSistema.registrarSucursal("PER-LIM-01","La Picante");
        assertEquals(Retorno.Resultado.OK, retorno.getResultado());
        retorno = miSistema.registrarSucursal("ARG-BAS-01","La Capitalina");
        assertEquals(Retorno.Resultado.OK, retorno.getResultado());
        retorno = miSistema.registrarSucursal("UYU-MVD-02","Porque pinto");
        assertEquals(Retorno.Resultado.OK, retorno.getResultado());

        retorno = miSistema.registrarConexion("UYU-MVD-01","UYU-MVD-02",13);
        assertEquals(Retorno.Resultado.OK, retorno.getResultado());

        retorno = miSistema.actualizarConexion("UYU-MVD-01","UYU-MVD-02",52);
        assertEquals(Retorno.Resultado.OK, retorno.getResultado());

        retorno = miSistema.registrarConexion("UYU-MVD-01","ARG-BAS-01",36);
        assertEquals(Retorno.Resultado.OK, retorno.getResultado());

        retorno = miSistema.actualizarConexion("ARG-BAS-01","UYU-MVD-01",20);
        assertEquals(Retorno.Resultado.OK, retorno.getResultado());

        retorno = miSistema.cantidadConexiones();
        assertEquals(4, retorno.getValorInteger());
    }

    @Test
    public void TestCase12RegistrarSucursal_TestError1() {
        retorno = miSistema.registrarSucursal("UYU-MVD-01","La Montevideana");
        assertEquals(Retorno.Resultado.OK, retorno.getResultado());
        retorno = miSistema.registrarSucursal("PER-LIM-01","La Picante");
        assertEquals(Retorno.Resultado.OK, retorno.getResultado());

        retorno = miSistema.actualizarConexion("UYU-MVD-01","PER-LIM-01",-1);
        assertEquals(Retorno.Resultado.ERROR_1, retorno.getResultado());
    }

    @Test
    public void TestCase12RegistrarSucursal_TestError2() {
        retorno = miSistema.registrarSucursal("UYU-MVD-01","La Montevideana");
        assertEquals(Retorno.Resultado.OK, retorno.getResultado());
        retorno = miSistema.registrarSucursal("PER-LIM-01","La Picante");
        assertEquals(Retorno.Resultado.OK, retorno.getResultado());

        retorno = miSistema.actualizarConexion(null,"PER-LIM-01",13);
        assertEquals(Retorno.Resultado.ERROR_2, retorno.getResultado());
        retorno = miSistema.actualizarConexion("","PER-LIM-01",13);
        assertEquals(Retorno.Resultado.ERROR_2, retorno.getResultado());
    }

    @Test
    public void TestCase12RegistrarSucursal_TestError3() {
        retorno = miSistema.registrarSucursal("UYU-MVD-01","La Montevideana");
        assertEquals(Retorno.Resultado.OK, retorno.getResultado());
        retorno = miSistema.registrarSucursal("PER-LIM-01","La Picante");
        assertEquals(Retorno.Resultado.OK, retorno.getResultado());

        retorno = miSistema.actualizarConexion("UYU-MVD-02","PER-LIM-01",13);
        assertEquals(Retorno.Resultado.ERROR_3, retorno.getResultado());
    }

    @Test
    public void TestCase12RegistrarSucursal_TestError4() {
        retorno = miSistema.registrarSucursal("UYU-MVD-01","La Montevideana");
        assertEquals(Retorno.Resultado.OK, retorno.getResultado());
        retorno = miSistema.registrarSucursal("PER-LIM-01","La Picante");
        assertEquals(Retorno.Resultado.OK, retorno.getResultado());

        retorno = miSistema.actualizarConexion("UYU-MVD-01","PER-LIM-01",13);
        assertEquals(Retorno.Resultado.ERROR_4, retorno.getResultado());
    }
}