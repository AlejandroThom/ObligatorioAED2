package sistema;

import interfaz.Retorno;
import org.junit.Before;
import org.junit.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TestCase11RegistrarConexion {
    private ImplementacionSistema miSistema;
    Retorno retorno;

    @Before
    public void setUp() {
        miSistema = new ImplementacionSistema();
        miSistema.inicializarSistema(4);
    }

    @Test
    public void TestCase11RegistrarSucursal_TestOK() {
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
        retorno = miSistema.registrarConexion("UYU-MVD-01","PER-LIM-01",52);
        assertEquals(Retorno.Resultado.OK, retorno.getResultado());
        retorno = miSistema.registrarConexion("UYU-MVD-01","ARG-BAS-01",36);
        assertEquals(Retorno.Resultado.OK, retorno.getResultado());
        retorno = miSistema.registrarConexion("ARG-BAS-01","PER-LIM-01",50);
        assertEquals(Retorno.Resultado.OK, retorno.getResultado());
        retorno = miSistema.cantidadConexiones();
        assertEquals(8, retorno.getValorInteger());
    }
}