package sistema;

import interfaz.Retorno;
import org.junit.Before;

public class Test11_RegistrarConexionTest {
    private ImplementacionSistema miSistema;
    Retorno retorno;

    @Before
    public void setUp() {
        miSistema = new ImplementacionSistema();
        miSistema.inicializarSistema(4);
    }
}