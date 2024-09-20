package TADS.Grafos;

public class AristaGrafo<T extends Comparable<T>> {
    private NodoGrafo<T> nodoInicio;
    private  NodoGrafo<T> nodoFin;
    private int peso;

    public AristaGrafo(NodoGrafo<T> nodoInicio, NodoGrafo<T> nodoFin) {
        this.nodoInicio = nodoInicio;
        this.nodoFin = nodoFin;
    }

    public NodoGrafo<T> getNodoInicio() {
        return nodoInicio;
    }

    public void setNodoInicio(NodoGrafo<T> nodoInicio) {
        this.nodoInicio = nodoInicio;
    }

    public NodoGrafo<T> getNodoFin() {
        return nodoFin;
    }

    public void setNodoFin(NodoGrafo<T> nodoFin) {
        this.nodoFin = nodoFin;
    }

    public int getPeso() {
        return peso;
    }

    public void setPeso(int peso) {
        this.peso = peso;
    }
}
