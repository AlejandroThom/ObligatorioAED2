package TADS.ArbolBB;

public interface IABB<T extends Comparable<T>> {
    public void insertar(T element);
    public void remover(T element) throws Exception;
    public T encontrar(T element);
    public int longitud();
    public boolean isEquilibrado();
    public void Equilibrar();
    public String listarAscendente();
    public String listarDescendente();
    public boolean estaVacia();
    public boolean pertenece(T elemento);

}
