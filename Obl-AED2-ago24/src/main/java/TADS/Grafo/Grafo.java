package TADS.Grafo;

import TADS.Lista.Lista;

public class Grafo<T extends Comparable<T>> implements IGrafo<T> {
    //Atributos
    private int cantidadMaximaVertices;
    private int cantidadActualVertices;

    private boolean esDirigido;

    private T[] vertices;
    private Arista[][] matrizAdyacente;

    //Constructores
    public Grafo(int cantidadMaximaVertices) {
        this.cantidadMaximaVertices = cantidadMaximaVertices;
        this.esDirigido = true;
        this.cantidadActualVertices = 0;

        this.vertices = (T[]) new Object[cantidadMaximaVertices];
        iniciarMatrizAdyacenciaOptimizada();
    }

    public Grafo(int cantidadMaximaVertices, boolean esDirigido) {
        this.cantidadMaximaVertices = cantidadMaximaVertices;
        this.esDirigido = esDirigido;
        this.cantidadActualVertices = 0;

        this.vertices = (T[]) new Object[cantidadMaximaVertices];
        iniciarMatrizAdyacenciaOptimizada();
    }

    private void iniciarMatrizAdyacenciaOptimizada(){
        this.matrizAdyacente = new Arista[this.cantidadMaximaVertices][this.cantidadMaximaVertices];

        if(this.esDirigido){
            for (int i = 0; i < this.cantidadMaximaVertices; i++) {
                for (int j = 0; j < this.cantidadMaximaVertices; j++) {
                    this.matrizAdyacente[i][j] = new Arista();
                }
            }
        }else{
            for (int i = 0; i < this.cantidadMaximaVertices; i++) {
                for (int j = i; j < this.cantidadMaximaVertices; j++) {
                    this.matrizAdyacente[i][j] = new Arista();
                    this.matrizAdyacente[j][i] = this.matrizAdyacente[i][j];
                }
            }
        }
    }

    //Getters y Setters
    public int getCantidadMaximaVertices() {
        return cantidadMaximaVertices;
    }

    public int getCantidadActualVertices() {
        return cantidadActualVertices;
    }

    public void setCantidadActualVertices(int cantidadActualVertices) {
        this.cantidadActualVertices = cantidadActualVertices;
    }

    public boolean isEsDirigido() {
        return esDirigido;
    }

    public void setEsDirigido(boolean esDirigido) {
        this.esDirigido = esDirigido;
    }

    public T[] getVertices() {
        return vertices;
    }

    public void setVertices(T[] vertices) {
        this.vertices = vertices;
    }

    public Arista[][] getMatrizAdyacente() {
        return matrizAdyacente;
    }

    public void setMatrizAdyacente(Arista[][] matrizAdyacente) {
        this.matrizAdyacente = matrizAdyacente;
    }

    //Metodos
    @Override
    public void agregarVertice(T v) {
        if(this.cantidadActualVertices > this.cantidadMaximaVertices){
            return;
        }

        this.vertices[primeraPosicionVacia()] = v;
        this.cantidadActualVertices++;
    }

    @Override
    public void agregarArista(T origen, T destino, int peso) {
        int iOrigen = buscarPosicionVertice(origen);
        int iDestino = buscarPosicionVertice(destino);

        this.matrizAdyacente[iOrigen][iDestino].setPeso(peso);
        this.matrizAdyacente[iOrigen][iDestino].setExiste(true);

        if(!this.esDirigido){
            this.matrizAdyacente[iDestino][iOrigen].setPeso(peso); //A chequear, si alias funca no va
            this.matrizAdyacente[iDestino][iOrigen].setExiste(true); //A chequear, si alias funca no va
        }
    }


    @Override
    public void borrarVertice(T v) {
        if(this.cantidadActualVertices < 1){
            return;
        }
        int iVertice  = buscarPosicionVertice(v);
        this.vertices[iVertice] = null;
        this.cantidadActualVertices--;

        for (int i = 0; i < this.cantidadMaximaVertices; i++) {
            this.matrizAdyacente[iVertice][i] = new Arista();
            this.matrizAdyacente[i][iVertice] = new Arista();
        }
    }

    @Override
    public void borrarArista(T origen, T destino) {
        int iOrigen = buscarPosicionVertice(origen);
        int iDestino = buscarPosicionVertice(destino);

        this.matrizAdyacente[iOrigen][iDestino] = new Arista();
        if(!this.esDirigido){
            this.matrizAdyacente[iDestino][iOrigen] = new Arista();
        }
    }

    @Override
    public boolean esVacio() {
        return this.cantidadActualVertices == 0;
    }

    @Override
    public Lista<T> verticesAdyacentes(T v) {

        int iVertice = buscarPosicionVertice(v);
        if(iVertice < 0) {
            return new Lista<>();
        }

        Lista<T> adyacentes = new Lista<>();
        for (int i = 0; i < this.cantidadMaximaVertices; i++) {
            if(this.matrizAdyacente[iVertice][i].isExiste()){
                adyacentes.insertar(this.vertices[i]);
            }
        }

        return adyacentes;
    }

    @Override
    public boolean sonAdyacentes(T origen, T destino) {
        int iOrigen = buscarPosicionVertice(origen);
        int iDestino = buscarPosicionVertice(destino);
        return this.matrizAdyacente[iOrigen][iDestino].isExiste();
    }

    @Override
    public boolean existeVertice(T v) {
        return buscarPosicionVertice(v) != -1;
    }

    private int buscarPosicionVertice(T origen) {
        int pos = -1;
        int i = 0;
        while(pos == -1 && i < this.cantidadMaximaVertices) {
            if(this.vertices[i].equals(origen)) {
                pos = i;
            }
            i++;
        }
        return pos;
    }

    private int primeraPosicionVacia(){
        int pos = -1;
        int i = 0;
        while(pos == -1 && i < this.cantidadMaximaVertices) {
            if(this.vertices[i] == null) {
                pos = i;
            }
            i++;
        }
        return pos;
    }

}
