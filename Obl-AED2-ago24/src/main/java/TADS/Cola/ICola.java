package TADS.Cola;

public interface ICola<T extends Comparable<T>> {
    public void encolar(T elemento);
    public T desencolar();
    public boolean estaVacia();
    public T obtenerFrente();

}
