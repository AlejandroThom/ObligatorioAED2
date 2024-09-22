package sistema;

import interfaz.Retorno;
import org.junit.Before;

public class Test12_ActualizarConexionTest {
    private ImplementacionSistema miSistema;
    Retorno retorno;

    @Before
    public void setUp() {
        miSistema = new ImplementacionSistema();
        miSistema.inicializarSistema(4);
    }
}