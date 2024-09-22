package TADS.Grafos;

import dominio.Sucursal;

public class AristaGrafo<T extends Comparable<T>> implements Comparable<AristaGrafo<T>> {
    private  T nodoConexion;
    private int peso;

    public AristaGrafo(T nodoConexion, int peso) {
        this.nodoConexion = nodoConexion;
        this.peso = peso;
    }

    //constructor para busquedas
    public AristaGrafo(T nodoConexion) {
        this.nodoConexion = nodoConexion;
    }

    public int getPeso() {
        return peso;
    }

    public void setPeso(int peso) {
        this.peso = peso;
    }

    @Override
    public boolean equals(Object obj) {
        return obj instanceof AristaGrafo<?> && nodoConexion.equals(((AristaGrafo<?>) obj).nodoConexion);
    }

    @Override
    public int compareTo(AristaGrafo<T> o) {
        return nodoConexion.compareTo(o.nodoConexion);
    }
}
