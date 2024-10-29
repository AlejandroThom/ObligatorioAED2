package TADS.ArbolBB;

import TADS.Pair.Pair;

public interface IABB<T extends Comparable<T>> {
    public void insertar(T element);
    public void remover(T element) throws Exception;
    public Pair<T,Integer> encontrar(T element);
    public int longitud();
    public boolean isEquilibrado();
    public void Equilibrar();
    public String listarAscendente();
    public String listarDescendente();
    public boolean estaVacia();
    public boolean pertenece(T elemento);

}
