package sistema;

import interfaz.Retorno;
import org.junit.Before;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class Test08_ListarJugadoresDeEquipoTest {
    private ImplementacionSistema miSistema;
    Retorno retorno;

    @Before
    public void setUp() {
        miSistema = new ImplementacionSistema();
        miSistema.inicializarSistema(4);
    }
}