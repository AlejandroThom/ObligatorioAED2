package sistema;

import interfaz.Retorno;
import org.junit.Before;

public class Test10_RegistrarSucursalTest {
    private ImplementacionSistema miSistema;
    Retorno retorno;

    @Before
    public void setUp() {
        miSistema = new ImplementacionSistema();
        miSistema.inicializarSistema(4);
    }
}