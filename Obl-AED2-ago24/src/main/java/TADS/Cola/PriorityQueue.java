package TADS.Cola;

public class PriorityQueue<T extends Comparable<T>> implements ICola<T> {
    private NodoCola<T> frente;
    private NodoCola<T> ultimo;
    private int cantidadElementos;

    public PriorityQueue() {}


    @Override
    public void encolar(T elemento) {
        NodoCola<T> nuevo = new NodoCola<>(elemento);
        if (frente == null) {
            frente = nuevo;
            ultimo = nuevo;
        }
        else{
            NodoCola<T> actual = frente;
            boolean insertado = false;
            while (actual != null && !insertado) {
                if(actual.getDato().compareTo(elemento) > 0){
                    nuevo.setSiguiente(actual);
                    nuevo.setAnterior(actual.getAnterior());
                    actual.getAnterior().setSiguiente(nuevo);
                    actual.setAnterior(nuevo);
                    insertado = true;
                }
                actual = actual.getSiguiente();
            }
            if(!insertado){
                ultimo.setSiguiente(nuevo);
                nuevo.setAnterior(ultimo);
                ultimo = nuevo;
            }
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
