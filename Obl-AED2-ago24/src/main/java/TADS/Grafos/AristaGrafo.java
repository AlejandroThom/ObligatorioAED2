package TADS.Grafos;

import dominio.Sucursal;

public class AristaGrafo<T extends Comparable<T>> implements Comparable<AristaGrafo<T>> {
    private T nodoInicio;
    private  T nodoFin;
    private int peso;

    public AristaGrafo(T nodoInicio, T nodoFin) {
        this.nodoInicio = nodoInicio;
        this.nodoFin = nodoFin;
    }

    public T getNodoInicio() {
        return nodoInicio;
    }

    public void setNodoInicio(T nodoInicio) {
        this.nodoInicio = nodoInicio;
    }

    public T getNodoFin() {
        return nodoFin;
    }

    public void setNodoFin(T nodoFin) {
        this.nodoFin = nodoFin;
    }

    public int getPeso() {
        return peso;
    }

    public void setPeso(int peso) {
        this.peso = peso;
    }

    @Override
    public int compareTo(AristaGrafo<T> o) {
        return nodoInicio.compareTo(o.nodoInicio) + nodoFin.compareTo(o.nodoFin);
    }
}
