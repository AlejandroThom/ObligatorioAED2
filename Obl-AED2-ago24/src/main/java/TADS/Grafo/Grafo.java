package TADS.Grafo;

import TADS.Cola.PriorityQueue;
import TADS.Lista.Lista;
import TADS.Pair.Pair;

public class Grafo<T extends Comparable<T>> implements IGrafo<T> {
    //Atributos
    private int cantidadMaximaVertices;
    private int cantidadActualVertices;
    private int cantidadAristas;
    private boolean esDirigido;

    public int getCantidadAristas(){
        return cantidadAristas;
    }

    private T[] vertices;
    private Arista[][] matrizAdyacente;

    //Constructores
    public Grafo(int cantidadMaximaVertices) {
        this.cantidadMaximaVertices = cantidadMaximaVertices;
        this.esDirigido = true;
        this.cantidadActualVertices = 0;

        this.vertices = (T[]) new Comparable[cantidadMaximaVertices];
        iniciarMatrizAdyacenciaOptimizada();
    }

    public Grafo(int cantidadMaximaVertices, boolean esDirigido) {
        this.cantidadMaximaVertices = cantidadMaximaVertices;
        this.esDirigido = esDirigido;
        this.cantidadActualVertices = 0;

        this.vertices = (T[]) new Comparable[cantidadMaximaVertices];
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
        cantidadAristas++;
        if(!this.esDirigido){
            this.matrizAdyacente[iDestino][iOrigen].setPeso(peso); //A chequear, si alias funca no va
            this.matrizAdyacente[iDestino][iOrigen].setExiste(true); //A chequear, si alias funca no va
            cantidadAristas++;
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

    @Override
    public T obtenerVertice(T v) {
        int pos = buscarPosicionVertice(v);
        return pos == -1 ? null: this.vertices[pos];
    }

    @Override
    public boolean aristaEsCritica(T a) {
        return false;
    }

    @Override
    public Lista<T> aristasConectadasAConMenosPesoA(T inicio,int pesoMax) {
        int pos = buscarPosicionVertice(inicio);
        boolean[] visitados = new boolean[this.cantidadMaximaVertices];
        Lista<T> sucursalesObtenidas = new Lista<>();
        //NECESITO AL HIJO posicion Y AL ESPIRITU SANTO(EL PESO ACUMULADO) EN LA COLA DE PRIORIDAD
        //AL INICIO EL HIJO ES EL INICIO(DONDE PARTO) Y EL PADRE -1 POR LO TANTO EL PESO ACUMULADO VA A SER 0
        PriorityQueue<Pair<Integer,Integer>> cola = new PriorityQueue<>();
        cola.encolar(new Pair<>(pos,0));
        while(!cola.estaVacia()){
            //OBTENGO EL DATO DEL OBJETO QUE TIENE AL PADRE AL HIJO Y AL PESO
            Pair<Integer,Integer> par = cola.desencolar();
            if(!visitados[par.getFirst()]){
                //MARCO COMO VISITADO
                visitados[par.getFirst()] = true;
                //AGREGO A SUS 'HIJOS' A LA COLA
                for(int i = 0; i < this.cantidadMaximaVertices; i++){
                    Arista ar = matrizAdyacente[par.getFirst()][i];
                    if(ar.isExiste()){
                        Pair<Integer,Integer> nuevo = new Pair<>(i,ar.getPeso()+par.getSecond());
                    }
                }
                // SI EL PESO ACUMULADO ACTUAL <= pesoMax se agrega a la lista
                if(par.getSecond() <= pesoMax){
                    sucursalesObtenidas.insertarOrdenado(vertices[par.getFirst()]);
                }
            }
        }
        return sucursalesObtenidas;
    }



    private int buscarPosicionVertice(T origen) {
        if(this.cantidadActualVertices == 0){
            return -1;
        }
        int pos = -1;
        int i = 0;
        while(pos == -1 && i < this.cantidadMaximaVertices) {
            if(this.vertices[i] != null && this.vertices[i].equals(origen)) {
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
