package TADS.ArbolBB;

public class NodoABB<T extends Comparable<T>> {
    private T dato;
    private NodoABB<T> hijoIzquierdo;
    private NodoABB<T> hijoDerecho;

    public NodoABB(T dato) {
        this.dato = dato;
    }

    public NodoABB(T dato, NodoABB<T> hijoIzquierdo, NodoABB<T> hijoDerecho) {
        this.dato = dato;
        this.hijoIzquierdo = hijoIzquierdo;
        this.hijoDerecho = hijoDerecho;
    }

    public T getDato() {
        return dato;
    }

    public void setDato(T dato) {
        this.dato = dato;
    }

    public NodoABB<T> getHijoIzquierdo() {
        return hijoIzquierdo;
    }

    public void setHijoIzquierdo(NodoABB<T> hijoIzquierdo) {
        this.hijoIzquierdo = hijoIzquierdo;
    }

    public NodoABB<T> getHijoDerecho() {
        return hijoDerecho;
    }

    public void setHijoDerecho(NodoABB<T> hijoDerecho) {
        this.hijoDerecho = hijoDerecho;
    }
}
