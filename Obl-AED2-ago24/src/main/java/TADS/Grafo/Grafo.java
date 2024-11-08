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

    private T[] vertices;
    private Arista[][] matrizAdyacente;

    private Lista<T> verticesCriticos;
    private boolean[] verticesCriticosArray;

    public int getCantidadAristas(){
        return cantidadAristas;
    }


    //Constructores
    public Grafo(int cantidadMaximaVertices) {
        this.cantidadMaximaVertices = cantidadMaximaVertices;
        this.esDirigido = true;
        this.cantidadActualVertices = 0;
        this.verticesCriticos = new Lista<>();
        verticesCriticosArray = new boolean[cantidadMaximaVertices];
        this.vertices = (T[]) new Comparable[cantidadMaximaVertices];
        iniciarMatrizAdyacenciaOptimizada();
    }

    public Grafo(int cantidadMaximaVertices, boolean esDirigido) {
        this.cantidadMaximaVertices = cantidadMaximaVertices;
        this.esDirigido = esDirigido;
        this.cantidadActualVertices = 0;
        this.verticesCriticosArray = new boolean[cantidadMaximaVertices];
        this.verticesCriticos = new Lista<>();
        this.vertices = (T[]) new Comparable[cantidadMaximaVertices];
        iniciarMatrizAdyacenciaOptimizada();
    }

    private void iniciarMatrizAdyacenciaOptimizada(){
        this.matrizAdyacente = new Arista[this.cantidadMaximaVertices][this.cantidadMaximaVertices];

        if(this.esDirigido){
            for (int i = 0; i < this.cantidadMaximaVertices; i++) {
                this.verticesCriticosArray[i] = false;
                for (int j = 0; j < this.cantidadMaximaVertices; j++) {
                    this.matrizAdyacente[i][j] = new Arista();
                }
            }
        }else{
            for (int i = 0; i < this.cantidadMaximaVertices; i++) {
                this.verticesCriticosArray[i] = false;
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
        this.verticesCriticosArray = new boolean[cantidadMaximaVertices];
        actualizarVerticesCriticos();
    }

    @Override
    public void agregarArista(int origen, int destino, int peso) {
        this.matrizAdyacente[origen][destino].setPeso(peso);
        this.matrizAdyacente[origen][destino].setExiste(true);
        cantidadAristas++;
        if(!this.esDirigido){
            this.matrizAdyacente[origen][destino].setPeso(peso); //A chequear, si alias funca no va
            this.matrizAdyacente[origen][destino].setExiste(true); //A chequear, si alias funca no va
            cantidadAristas++;
        }
        this.verticesCriticosArray = new boolean[cantidadMaximaVertices];
        actualizarVerticesCriticos();
    }

    @Override
    public void actualizarArista(T origen, T destino, int peso) {
        int iOrigen = buscarPosicionVertice(origen);
        int iDestino = buscarPosicionVertice(destino);

        this.matrizAdyacente[iOrigen][iDestino].setPeso(peso);
        if(!this.esDirigido){
            this.matrizAdyacente[iDestino][iOrigen].setPeso(peso); //A chequear, si alias funca no va
        }
    }

    @Override
    public void actualizarArista(int origen,int destino, int peso) {
        this.matrizAdyacente[origen][destino].setPeso(peso);
        if(!this.esDirigido){
            this.matrizAdyacente[destino][origen].setPeso(peso);
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
        actualizarVerticesCriticos();
    }

    @Override
    public void borrarArista(T origen, T destino) {
        int iOrigen = buscarPosicionVertice(origen);
        int iDestino = buscarPosicionVertice(destino);

        this.matrizAdyacente[iOrigen][iDestino] = new Arista();
        if(!this.esDirigido){
            this.matrizAdyacente[iDestino][iOrigen] = new Arista();
        }
        actualizarVerticesCriticos();
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
    public boolean sonAdyacentes(int origen, int destino) {
        return this.matrizAdyacente[origen][destino].isExiste();
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
    public void actualizarVerticesCriticos() {
        this.verticesCriticos = new Lista<>();
        boolean[] visitados = new boolean[this.cantidadMaximaVertices];
        int[] time = new int[this.cantidadMaximaVertices];
        int[] low = new int[this.cantidadMaximaVertices];
        // voy por todos los nodos por si es no conexo
        for(int i = 0; i < this.cantidadMaximaVertices; i++){
            if(this.vertices[i] != null && !visitados[i]){
                verticesCriticosDFS(verticesCriticos,i,-1,visitados,time,low,1);
            }
        }
    }

    private void verticesCriticosDFS(Lista<T> verticesCriticos, int posActual, int posPadre, boolean[] visitados, int[] time, int[] low, int currentTime){
        //Marco como visitado
        visitados[posActual] = true;
        int children = 0;
        //Marco el tiempo y lo más bajo que puede llegar
        time[posActual] = currentTime;
        low[posActual] = currentTime;

        Arista[] adyacentes = matrizAdyacente[posActual];

        for(int i = 0; i < adyacentes.length; i++){
            if(adyacentes[i].isExiste() && !visitados[i]){
                children++;

                //Voy por cada hijo del nodo actual
                verticesCriticosDFS(verticesCriticos,i, posActual,visitados,time,low,currentTime+1);

                //Marco cuál es el nodo más joven en ser descubierto al que puedo llegar a travez de mi hijo
                low[posActual] = Math.min(low[posActual], low[i]);

                //Si el nodo más joven al que puede llegar mi hijo es mayor o igual a mi, soy un nodo de articulación
                if(low[i] >= time[posActual] && posPadre != -1){
                    verticesCriticos.insertar(this.vertices[posActual]);
                    //Otra Solucion o(1)
                    this.verticesCriticosArray[posActual] = true;
                }else{
                    //Si ya esta agregado en la lista de vertices criticos, no se puede "quitar" de la lista
                    if(!this.verticesCriticosArray[posActual]){
                        this.verticesCriticosArray[posActual] = false;
                    }
                }
            }else if(adyacentes[i].isExiste() && i != posPadre){
                //Si el nodo al que visito es distinto a mi padre
                // y fue descubierto antes que yo mismo, puedo llegar a un nodo más joven
                low[posActual] = Math.min(low[posActual], time[i]);
            }
        }

        //Si el nodo 'raiz' tiene más de un hijo visitado entonces el nodo raiz es una articulación (es critico)
        if(posPadre == -1 && children > 1){
            verticesCriticos.insertar(this.vertices[posActual]);
            //otra solucion o(1)
            this.verticesCriticosArray[posActual] = true;
        }
    }

    @Override
    public Pair<Lista<T>,Integer> aristasConectadasAConMenosPesoA(T inicio,int pesoMax) {
        boolean[] visitados = new boolean[this.cantidadMaximaVertices];
        Lista<T> sucursalesObtenidas = new Lista<>();

        int pos = buscarPosicionVertice(inicio);

        //NECESITO AL PADRE, AL HIJO (posicion) Y AL ESPIRITU SANTO(EL PESO ACUMULADO) EN LA COLA DE PRIORIDAD
        //AL INICIO EL HIJO ES EL INICIO(DONDE PARTO) Y EL PADRE -1 POR LO TANTO EL PESO ACUMULADO VA A SER 0
        PriorityQueue<Pair<Integer,Integer>> cola = new PriorityQueue<>();
        cola.encolar(new Pair<>(pos,0));
        int latMax = 0;

        while(!cola.estaVacia()){
            //OBTENGO EL DATO DEL OBJETO QUE TIENE AL PADRE, AL HIJO Y AL PESO
            Pair<Integer,Integer> par = cola.desencolar();

            if(!visitados[par.getFirst()]){
                //MARCO COMO VISITADO
                visitados[par.getFirst()] = true;

                //AGREGO A SUS 'HIJOS' A LA COLA
                for(int i = 0; i < this.cantidadMaximaVertices; i++){
                    Arista ar = matrizAdyacente[par.getFirst()][i];
                    int pesoAcumulado = ar.getPeso()+par.getSecond();
                    if(ar.isExiste() && pesoAcumulado <= pesoMax){
                        Pair<Integer,Integer> nuevo = new Pair<>(i,pesoAcumulado);
                        cola.encolar(nuevo);
                    }
                }
                // SI EL PESO ACUMULADO ACTUAL <= pesoMax se agrega a la lista
                if(par.getSecond() <= pesoMax){
                    latMax = Math.max(latMax, par.getSecond());
                    sucursalesObtenidas.insertarOrdenado(vertices[par.getFirst()]);
                }
            }
        }
        return new Pair<>(sucursalesObtenidas,latMax);
    }

    @Override
    public Pair<Lista<T>,Integer> aristasConectadasAConMenosPesoA(int pos,int pesoMax) {
        boolean[] visitados = new boolean[this.cantidadMaximaVertices];
        Lista<T> sucursalesObtenidas = new Lista<>();
        //NECESITO AL HIJO posicion Y AL ESPIRITU SANTO(EL PESO ACUMULADO) EN LA COLA DE PRIORIDAD
        //AL INICIO EL HIJO ES EL INICIO(DONDE PARTO) Y EL PADRE -1 POR LO TANTO EL PESO ACUMULADO VA A SER 0
        PriorityQueue<Pair<Integer,Integer>> cola = new PriorityQueue<>();
        cola.encolar(new Pair<>(pos,0));
        int latMax = 0;

        while(!cola.estaVacia()){
            //OBTENGO EL DATO DEL OBJETO QUE TIENE AL PADRE AL HIJO Y AL PESO
            Pair<Integer,Integer> par = cola.desencolar();
            if(!visitados[par.getFirst()]){
                //MARCO COMO VISITADO
                visitados[par.getFirst()] = true;
                //AGREGO A SUS 'HIJOS' A LA COLA
                for(int i = 0; i < this.cantidadMaximaVertices; i++){
                    Arista ar = matrizAdyacente[par.getFirst()][i];
                    if(ar.isExiste() && ar.getPeso()+par.getSecond()<=pesoMax){
                        Pair<Integer,Integer> nuevo = new Pair<>(i,ar.getPeso()+par.getSecond());
                        cola.encolar(nuevo);
                    }
                }
                // SI EL PESO ACUMULADO ACTUAL <= pesoMax se agrega a la lista
                // NO DEBERIA HABER UNA LATENCIA MAYOR AL MAXIMO PERO MEJOR PREVENIR
                if(par.getSecond() <= pesoMax){
                    latMax = Math.max(latMax, par.getSecond());
                    sucursalesObtenidas.insertarOrdenado(vertices[par.getFirst()]);
                }
            }
        }
        return new Pair<>(sucursalesObtenidas,latMax);
    }
    //solucion O(n)
    @Override
    public boolean verticeEsCritico(T dato) {
        //Se podria mejorar con hashMap
        return this.verticesCriticos.estaElemento(dato);
    }

    //solucion O(1)
    @Override
    public boolean verticeEsCritico(int posdato) {
        return this.verticesCriticosArray[posdato];
    }

    @Override
    public int buscarPosicionVertice(T origen) {
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
