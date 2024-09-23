package sistema;

import interfaz.Retorno;
import org.junit.Before;

public class TestCase13AnalizarSucursal {
    private ImplementacionSistema miSistema;
    Retorno retorno;

    @Before
    public void setUp() {
        miSistema = new ImplementacionSistema();
        miSistema.inicializarSistema(4);
    }
}