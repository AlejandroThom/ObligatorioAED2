package TADS.Lista;

public class NodoLista <T extends Comparable<T>>{
    private T elemento;

    private NodoLista<T> siguiente;

    public NodoLista(T elemento){
        this.elemento = elemento;
    }

    public T getElemento() {
        return elemento;
    }

    public void setElemento(T elemento) {
        this.elemento = elemento;
    }

    public NodoLista<T> getSiguiente() {
        return siguiente;
    }

    public void setSiguiente(NodoLista<T> siguiente) {
        this.siguiente = siguiente;
    }
}
