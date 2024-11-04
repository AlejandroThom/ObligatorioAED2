package sistema.customtests;

import interfaz.Retorno;
import org.junit.Before;
import org.junit.Test;
import sistema.ImplementacionSistema;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TestCase13AnalizarSucursal {
    private ImplementacionSistema miSistema;
    Retorno retorno;

    @Before
    public void setUp() {
        miSistema = new ImplementacionSistema();
        miSistema.inicializarSistema(6);
    }
    @Test
    public void TestCase13RegistrarSucursal_TestOK() {
        retorno = miSistema.registrarSucursal("UYU-MVD-01","La Montevideana");
        assertEquals(Retorno.Resultado.OK, retorno.getResultado());
        retorno = miSistema.registrarSucursal("UYU-MVD-02","Porque pinto");
        assertEquals(Retorno.Resultado.OK, retorno.getResultado());
        retorno = miSistema.registrarSucursal("PER-LIM-01","La Picante");
        assertEquals(Retorno.Resultado.OK, retorno.getResultado());
        retorno = miSistema.registrarSucursal("PER-LIM-02","La Picantes");
        assertEquals(Retorno.Resultado.OK, retorno.getResultado());
        retorno = miSistema.registrarSucursal("ARG-BAS-01","La Capitalina");
        assertEquals(Retorno.Resultado.OK, retorno.getResultado());
        retorno = miSistema.registrarSucursal("ARG-BAS-02","La Capitalinas");
        assertEquals(Retorno.Resultado.OK, retorno.getResultado());


        retorno = miSistema.registrarConexion("UYU-MVD-01","PER-LIM-01",13);
        assertEquals(Retorno.Resultado.OK, retorno.getResultado());

        retorno = miSistema.registrarConexion("UYU-MVD-01","ARG-BAS-01",52);
        assertEquals(Retorno.Resultado.OK, retorno.getResultado());

        retorno = miSistema.registrarConexion("PER-LIM-01","ARG-BAS-01",36);
        assertEquals(Retorno.Resultado.OK, retorno.getResultado());

        retorno = miSistema.registrarConexion("UYU-MVD-01","ARG-BAS-02",52);
        assertEquals(Retorno.Resultado.OK, retorno.getResultado());

        retorno = miSistema.registrarConexion("UYU-MVD-01","PER-LIM-02",52);
        assertEquals(Retorno.Resultado.OK, retorno.getResultado());

        retorno = miSistema.registrarConexion("ARG-BAS-02","PER-LIM-02",52);
        assertEquals(Retorno.Resultado.OK, retorno.getResultado());

        retorno = miSistema.analizarSucursal("UYU-MVD-01");
        assertEquals(Retorno.Resultado.OK, retorno.getResultado());
        assertEquals("SI",retorno.getValorString());

        retorno = miSistema.analizarSucursal("UYU-MVD-02");
        assertEquals(Retorno.Resultado.OK, retorno.getResultado());
        assertEquals("NO",retorno.getValorString());

        retorno = miSistema.analizarSucursal("PER-LIM-02");
        assertEquals(Retorno.Resultado.OK, retorno.getResultado());
        assertEquals("NO",retorno.getValorString());

    }

    @Test
    public void TestCase13RegistrarSucursal_TestError2() {
        retorno = miSistema.registrarSucursal("UYU-MVD-01","La Montevideana");
        assertEquals(Retorno.Resultado.OK, retorno.getResultado());
        retorno = miSistema.registrarSucursal("PER-LIM-01","La Picante");
        assertEquals(Retorno.Resultado.OK, retorno.getResultado());

        retorno = miSistema.analizarSucursal("UYU-MVD-03");
        assertEquals(Retorno.Resultado.ERROR_2, retorno.getResultado());
    }

    @Test
    public void TestCase13RegistrarSucursal_TestError1() {
        retorno = miSistema.registrarSucursal("UYU-MVD-01","La Montevideana");
        assertEquals(Retorno.Resultado.OK, retorno.getResultado());
        retorno = miSistema.registrarSucursal("PER-LIM-01","La Picante");
        assertEquals(Retorno.Resultado.OK, retorno.getResultado());

        retorno = miSistema.analizarSucursal(null);
        assertEquals(Retorno.Resultado.ERROR_1, retorno.getResultado());
        retorno = miSistema.analizarSucursal("");
        assertEquals(Retorno.Resultado.ERROR_1, retorno.getResultado());
    }
}