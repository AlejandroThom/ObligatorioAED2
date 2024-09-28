package sistema;

import interfaz.Retorno;
import org.junit.Before;
import org.junit.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TestCase14SucursalesParaTorneo {
    private ImplementacionSistema miSistema;
    Retorno retorno;

    @Before
    public void setUp() {
        miSistema = new ImplementacionSistema();
        miSistema.inicializarSistema(6);
    }
    @Test
    public void TestCase14RegistrarSucursal_TestOK() {
        retorno = miSistema.registrarSucursal("1","La Montevideana");
        assertEquals(Retorno.Resultado.OK, retorno.getResultado());
        retorno = miSistema.registrarSucursal("2","Porque pinto");
        assertEquals(Retorno.Resultado.OK, retorno.getResultado());
        retorno = miSistema.registrarSucursal("3","La Picante");
        assertEquals(Retorno.Resultado.OK, retorno.getResultado());
        retorno = miSistema.registrarSucursal("4","La Picantes");
        assertEquals(Retorno.Resultado.OK, retorno.getResultado());
        retorno = miSistema.registrarSucursal("5","La Capitalina");
        assertEquals(Retorno.Resultado.OK, retorno.getResultado());
        retorno = miSistema.registrarSucursal("6","La Capitalinas");
        assertEquals(Retorno.Resultado.OK, retorno.getResultado());


        retorno = miSistema.registrarConexion("1","2",5);
        assertEquals(Retorno.Resultado.OK, retorno.getResultado());

        retorno = miSistema.registrarConexion("1","3",6);
        assertEquals(Retorno.Resultado.OK, retorno.getResultado());

        retorno = miSistema.registrarConexion("2","3",3);
        assertEquals(Retorno.Resultado.OK, retorno.getResultado());

        retorno = miSistema.registrarConexion("1","4",8);
        assertEquals(Retorno.Resultado.OK, retorno.getResultado());

        retorno = miSistema.registrarConexion("1","5",16);
        assertEquals(Retorno.Resultado.OK, retorno.getResultado());

        retorno = miSistema.registrarConexion("4","5",18);
        assertEquals(Retorno.Resultado.OK, retorno.getResultado());

        retorno = miSistema.sucursalesParaTorneo("1",15);
        assertEquals(Retorno.Resultado.OK, retorno.getResultado());
        assertEquals("1;La Montevideana|2;Porque pinto|3;La Picante|4;La Picantes",retorno.getValorString());

    }

    @Test
    public void TestCase14RegistrarSucursal_TestError3() {
        retorno = miSistema.registrarSucursal("UYU-MVD-01","La Montevideana");
        assertEquals(Retorno.Resultado.OK, retorno.getResultado());
        retorno = miSistema.registrarSucursal("PER-LIM-01","La Picante");
        assertEquals(Retorno.Resultado.OK, retorno.getResultado());

        retorno = miSistema.sucursalesParaTorneo("PER-LIM-01",0);
        assertEquals(Retorno.Resultado.ERROR_3, retorno.getResultado());
    }

    @Test
    public void TestCase14RegistrarSucursal_TestError2() {
        retorno = miSistema.registrarSucursal("UYU-MVD-01","La Montevideana");
        assertEquals(Retorno.Resultado.OK, retorno.getResultado());
        retorno = miSistema.registrarSucursal("PER-LIM-01","La Picante");
        assertEquals(Retorno.Resultado.OK, retorno.getResultado());

        retorno = miSistema.sucursalesParaTorneo("UYU-MVD-03",18);
        assertEquals(Retorno.Resultado.ERROR_2, retorno.getResultado());
    }

    @Test
    public void TestCase14RegistrarSucursal_TestError1() {
        retorno = miSistema.registrarSucursal("UYU-MVD-01","La Montevideana");
        assertEquals(Retorno.Resultado.OK, retorno.getResultado());
        retorno = miSistema.registrarSucursal("PER-LIM-01","La Picante");
        assertEquals(Retorno.Resultado.OK, retorno.getResultado());

        retorno = miSistema.sucursalesParaTorneo(null,15);
        assertEquals(Retorno.Resultado.ERROR_1, retorno.getResultado());
        retorno = miSistema.sucursalesParaTorneo("",15);
        assertEquals(Retorno.Resultado.ERROR_1, retorno.getResultado());
    }
}