package TADS.Cola;

public class NodoCola<T extends Comparable<T>> {
    private T dato;
    private NodoCola<T> siguiente;
    private NodoCola<T> anterior;

    public NodoCola(T dato) {
        this.dato = dato;
    }

    public NodoCola<T> getSiguiente() {
        return siguiente;
    }

    public void setSiguiente(NodoCola<T> siguiente) {
        this.siguiente = siguiente;
    }

    public NodoCola<T> getAnterior() {
        return anterior;
    }

    public void setAnterior(NodoCola<T> anterior) {
        this.anterior = anterior;
    }

    public T getDato() {
        return dato;
    }

    public void setDato(T dato) {
        this.dato = dato;
    }
}
