package TADS.Lista;

public interface ILista <T extends Comparable<T>>{

    public void insertar(T elemento);
    public void eliminar(T elemento);
    public void eliminar(int posicion);
    public void vaciar();
    public int size();
    public boolean isEmpty();
    public String mostrar();
    public boolean estaElemento(T elemento);
    public T getElemento(T elemento);
    public void insertarOrdenado(T elemento);
    public void Ordenar();
    public boolean isOrdenado();
}
