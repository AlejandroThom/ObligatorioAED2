package sistema;

import interfaz.Categoria;
import interfaz.Retorno;
import org.junit.Before;
import org.junit.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class TestCase07AgregarJugadorAEquipo {
    private ImplementacionSistema miSistema;
    Retorno retorno;

    @Before
    public void setUp() {
        miSistema = new ImplementacionSistema();
        miSistema.inicializarSistema(4);
    }

    @Test
    public void Test07_OK_AgregarJugadorAEquipoTest() {
        retorno = miSistema.registrarJugador("El Pepe","Pedro","Pedrales", Categoria.PROFESIONAL);
        assertEquals(Retorno.Resultado.OK, retorno.getResultado());
        retorno = miSistema.registrarEquipo("Los magicos","El mago");
        assertEquals(Retorno.Resultado.OK, retorno.getResultado());

        retorno = miSistema.agregarJugadorAEquipo("Los magicos","El Pepe");
        assertEquals(Retorno.Resultado.OK, retorno.getResultado());
    }

    @Test
    public void Test07_Error01_AgregarJugadorAEquipoTest() {
        retorno = miSistema.registrarJugador("El Pepe","Pedro","Pedrales", Categoria.PROFESIONAL);
        assertEquals(Retorno.Resultado.OK, retorno.getResultado());
        retorno = miSistema.registrarEquipo("Los magicos","El mago");
        assertEquals(Retorno.Resultado.OK, retorno.getResultado());

        retorno = miSistema.agregarJugadorAEquipo(null,"El Pepe");
        assertEquals(Retorno.Resultado.ERROR_1, retorno.getResultado());
        retorno = miSistema.agregarJugadorAEquipo("","El Pepe");
        assertEquals(Retorno.Resultado.ERROR_1, retorno.getResultado());
        retorno = miSistema.agregarJugadorAEquipo("Los magicos",null);
        assertEquals(Retorno.Resultado.ERROR_1, retorno.getResultado());
        retorno = miSistema.agregarJugadorAEquipo("Los magicos","");
        assertEquals(Retorno.Resultado.ERROR_1, retorno.getResultado());
    }

    @Test
    public void Test07_Error02_AgregarJugadorAEquipoTest() {
        retorno = miSistema.registrarJugador("El Pepe","Pedro","Pedrales", Categoria.PROFESIONAL);
        assertEquals(Retorno.Resultado.OK, retorno.getResultado());
        retorno = miSistema.registrarEquipo("Los magicos","El mago");
        assertEquals(Retorno.Resultado.OK, retorno.getResultado());

        retorno = miSistema.agregarJugadorAEquipo("Los mancos","El Pepe");
        assertEquals(Retorno.Resultado.ERROR_2, retorno.getResultado());
    }

    @Test
    public void Test07_Error03_AgregarJugadorAEquipoTest() {
        retorno = miSistema.registrarJugador("El Pepe","Pedro","Pedrales", Categoria.PROFESIONAL);
        assertEquals(Retorno.Resultado.OK, retorno.getResultado());
        retorno = miSistema.registrarEquipo("Los magicos","El mago");
        assertEquals(Retorno.Resultado.OK, retorno.getResultado());

        retorno = miSistema.agregarJugadorAEquipo("Los magicos","El Bromas");
        assertEquals(Retorno.Resultado.ERROR_3, retorno.getResultado());
    }

    @Test
    public void Test07_Error04_AgregarJugadorAEquipoTest() {
        retorno = miSistema.registrarJugador("El Pepe","Pedro","Pedrales", Categoria.PROFESIONAL);
        assertEquals(Retorno.Resultado.OK, retorno.getResultado());
        retorno = miSistema.registrarJugador("El Querto","Querty","Quertales", Categoria.PROFESIONAL);
        assertEquals(Retorno.Resultado.OK, retorno.getResultado());
        retorno = miSistema.registrarJugador("El Rober","Roberto","Roblares", Categoria.PROFESIONAL);
        assertEquals(Retorno.Resultado.OK, retorno.getResultado());
        retorno = miSistema.registrarJugador("El Surce","Santiago","Surcales", Categoria.PROFESIONAL);
        assertEquals(Retorno.Resultado.OK, retorno.getResultado());
        retorno = miSistema.registrarJugador("El Tuerto","Timoteo","Tuertales", Categoria.PROFESIONAL);
        assertEquals(Retorno.Resultado.OK, retorno.getResultado());
        retorno = miSistema.registrarEquipo("Los magicos","El mago");
        assertEquals(Retorno.Resultado.OK, retorno.getResultado());
        retorno = miSistema.agregarJugadorAEquipo("Los magicos","El Pepe");
        assertEquals(Retorno.Resultado.OK, retorno.getResultado());
        retorno = miSistema.agregarJugadorAEquipo("Los magicos","El Querto");
        assertEquals(Retorno.Resultado.OK, retorno.getResultado());
        retorno = miSistema.agregarJugadorAEquipo("Los magicos","El Rober");
        assertEquals(Retorno.Resultado.OK, retorno.getResultado());
        retorno = miSistema.agregarJugadorAEquipo("Los magicos","El Surce");
        assertEquals(Retorno.Resultado.OK, retorno.getResultado());
        retorno = miSistema.agregarJugadorAEquipo("Los magicos","El Tuerto");
        assertEquals(Retorno.Resultado.OK, retorno.getResultado());


        retorno = miSistema.registrarJugador("El Urticaria","Uzman","Urtimales", Categoria.PROFESIONAL);
        assertEquals(Retorno.Resultado.OK, retorno.getResultado());
        retorno = miSistema.agregarJugadorAEquipo("Los magicos","El Urticaria");
        assertEquals(Retorno.Resultado.ERROR_4, retorno.getResultado());
    }

    @Test
    public void Test07_Error05_AgregarJugadorAEquipoTest() {
        retorno = miSistema.registrarJugador("El Pepe","Pedro","Pedrales", Categoria.PRINCIPIANTE);
        assertEquals(Retorno.Resultado.OK, retorno.getResultado());
        retorno = miSistema.registrarJugador("El Querto","Querty","Quertales", Categoria.ESTANDARD);
        assertEquals(Retorno.Resultado.OK, retorno.getResultado());
        retorno = miSistema.registrarEquipo("Los magicos","El mago");
        assertEquals(Retorno.Resultado.OK, retorno.getResultado());

        retorno = miSistema.agregarJugadorAEquipo("Los magicos","El Pepe");
        assertEquals(Retorno.Resultado.ERROR_5, retorno.getResultado());
        retorno = miSistema.agregarJugadorAEquipo("Los magicos","El Querto");
        assertEquals(Retorno.Resultado.ERROR_5, retorno.getResultado());
    }

    @Test
    public void Test07_Error06_AgregarJugadorAEquipoTest() {
        retorno = miSistema.registrarJugador("El Pepe","Pedro","Pedrales", Categoria.PROFESIONAL);
        assertEquals(Retorno.Resultado.OK, retorno.getResultado());
        retorno = miSistema.registrarEquipo("Los magicos","El mago");
        assertEquals(Retorno.Resultado.OK, retorno.getResultado());
        retorno = miSistema.registrarEquipo("Los mancos","El manco");
        assertEquals(Retorno.Resultado.OK, retorno.getResultado());

        retorno = miSistema.agregarJugadorAEquipo("Los magicos","El Pepe");
        assertEquals(Retorno.Resultado.OK, retorno.getResultado());
        retorno = miSistema.agregarJugadorAEquipo("Los mancos","El Pepe");
        assertEquals(Retorno.Resultado.ERROR_6, retorno.getResultado());
    }
}
