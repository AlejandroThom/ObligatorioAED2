package TADS.Grafo;

public class Arista {
    private int peso;
    private boolean existe;

    public int getPeso() {
        return peso;
    }

    public void setPeso(int peso) {
        this.peso = peso;
    }

    public boolean isExiste() {
        return existe;
    }

    public void setExiste(boolean existe) {
        this.existe = existe;
    }

    public Arista(int peso) {
        this.peso = peso;
        this.existe = true;
    }

    public Arista() {
        this.peso = 0;
        this.existe = false;
    }
}