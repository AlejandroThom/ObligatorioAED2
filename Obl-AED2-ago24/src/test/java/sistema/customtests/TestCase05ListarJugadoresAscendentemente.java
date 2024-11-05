package sistema.customtests;
import interfaz.Categoria;
import interfaz.Retorno;
import org.junit.Before;
import org.junit.Test;
import sistema.ImplementacionSistema;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TestCase05ListarJugadoresAscendentemente {
    private ImplementacionSistema miSistema;
    Retorno retorno;

    @Before
    public void setUp() {
        miSistema = new ImplementacionSistema();
        miSistema.inicializarSistema(4);
    }

    @Test
    public void test05_OK_ListarJugadoresAscendentemente() {
        //CUANDO NO HAY ELEMENTOS
        retorno = miSistema.listarJugadoresPorCategoria(Categoria.ESTANDARD);
        assertEquals("", retorno.getValorString());
        retorno = miSistema.listarJugadoresPorCategoria(Categoria.PRINCIPIANTE);
        assertEquals("", retorno.getValorString());
        retorno = miSistema.listarJugadoresPorCategoria(Categoria.PROFESIONAL);
        assertEquals("", retorno.getValorString());

        //CUANDO HAY UN ELEMENTO
        retorno = miSistema.registrarJugador("El Pepe","Pedro","Pedrales", Categoria.ESTANDARD);
        assertEquals(Retorno.Resultado.OK, retorno.getResultado());
        retorno = miSistema.listarJugadoresPorCategoria(Categoria.ESTANDARD);
        assertEquals("El Pepe;Pedro;Pedrales;ESTANDARD",retorno.getValorString());

        retorno = miSistema.registrarJugador("El Rober","Roberto","Roblares", Categoria.PRINCIPIANTE);
        assertEquals(Retorno.Resultado.OK, retorno.getResultado());
        retorno = miSistema.listarJugadoresPorCategoria(Categoria.PRINCIPIANTE);
        assertEquals("El Rober;Roberto;Roblares;PRINCIPIANTE",retorno.getValorString());

        retorno = miSistema.registrarJugador("El Quero","Querty","Quertares", Categoria.PROFESIONAL);
        assertEquals(Retorno.Resultado.OK, retorno.getResultado());
        retorno = miSistema.listarJugadoresPorCategoria(Categoria.PROFESIONAL);
        assertEquals("El Quero;Querty;Quertares;PROFESIONAL",retorno.getValorString());

        //CUANDO HAY MAS DE UN ELEMENTO
        retorno = miSistema.registrarJugador("El PepeS","Pedro","Pedrales", Categoria.ESTANDARD);
        assertEquals(Retorno.Resultado.OK, retorno.getResultado());
        retorno = miSistema.listarJugadoresPorCategoria(Categoria.ESTANDARD);
        assertEquals("El Pepe;Pedro;Pedrales;ESTANDARD|El PepeS;Pedro;Pedrales;ESTANDARD",retorno.getValorString());

        retorno = miSistema.registrarJugador("El RoberS","Roberto","Roblares", Categoria.PRINCIPIANTE);
        assertEquals(Retorno.Resultado.OK, retorno.getResultado());
        retorno = miSistema.listarJugadoresPorCategoria(Categoria.PRINCIPIANTE);
        assertEquals("El Rober;Roberto;Roblares;PRINCIPIANTE|El RoberS;Roberto;Roblares;PRINCIPIANTE",retorno.getValorString());

        retorno = miSistema.registrarJugador("El QueroS","Querty","Quertares", Categoria.PROFESIONAL);
        assertEquals(Retorno.Resultado.OK, retorno.getResultado());
        retorno = miSistema.listarJugadoresPorCategoria(Categoria.PROFESIONAL);
        assertEquals("El Quero;Querty;Quertares;PROFESIONAL|El QueroS;Querty;Quertares;PROFESIONAL",retorno.getValorString());
    }

}
