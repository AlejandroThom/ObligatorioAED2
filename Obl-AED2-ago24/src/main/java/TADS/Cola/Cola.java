package TADS.Cola;

public class Cola<T extends Comparable<T>> implements ICola<T> {

    private NodoCola<T> frente;
    private NodoCola<T> ultimo;
    private int cantidadElementos;

    public Cola() {}


    @Override
    public void encolar(T elemento) {
        NodoCola<T> nuevo = new NodoCola<>(elemento);
        if (frente == null) {
            frente = nuevo;
            ultimo = nuevo;
        }
        else{
            ultimo.setSiguiente(nuevo);
            nuevo.setAnterior(ultimo);
            ultimo = nuevo;
        }
        cantidadElementos++;
    }

    @Override
    public T desencolar() {
        T devolver = frente.getDato();
        frente = frente.getSiguiente();
        frente.setAnterior(null);
        return devolver;
    }

    @Override
    public boolean estaVacia() {
        return cantidadElementos == 0;
    }

    @Override
    public T obtenerFrente() {
        return frente.getDato();
    }
}
