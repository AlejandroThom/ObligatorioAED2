package TADS.Grafo;

import TADS.Lista.Lista;
import TADS.Pair.Pair;

public interface IGrafo<T extends Comparable<T>> {
//    void crearGrafoVacio(int cantMaxDeVertices);
    void agregarVertice(T vertice);
    void agregarArista(T origen, T destino, int peso);
    void borrarVertice(T v);
    void borrarArista(T origen, T destino);
    boolean esVacio();
    Lista<T> verticesAdyacentes(T v);
    boolean sonAdyacentes(T a, T b);
    boolean existeVertice (T v);
    T obtenerVertice (T v);
    void actualizarVerticesCriticos();
    Pair<Lista<T>,Integer> aristasConectadasAConMenosPesoA(T inicio, int pesoMax);
    boolean verticeEsCritico(T dato);
    void actualizarArista(T origen, T destino, int peso);
    int buscarPosicionVertice(T origen);
    boolean sonAdyacentes(int origen, int destino);
    void actualizarArista(int origen,int destino, int peso);

}
