package TADS.Lista;

public class Lista<T extends Comparable<T>> implements ILista<T>, Comparable<Lista<T>> {

    private NodoLista<T> inicio;
    private NodoLista<T> fin;
    private int cantidad = 0;

    public Lista() {}

    public Lista(T dato) {
        NodoLista<T> nuevo = new NodoLista<>(dato);
        this.inicio = nuevo;
        this.fin = nuevo;
        cantidad++;
    }

    public NodoLista<T> getInicio() {
        return inicio;
    }

    public void setInicio(NodoLista<T> inicio) {
        this.inicio = inicio;
    }

    public NodoLista<T> getFin() {
        return fin;
    }

    public void setFin(NodoLista<T> fin) {
        this.fin = fin;
    }
    // -------------------------------------------------------------------------------------------------------

    @Override
    public void insertar(T elemento) {
        NodoLista<T> nuevo = new NodoLista<>(elemento);
        if(inicio == null) {
            inicio = nuevo;
        }else{
            fin.setSiguiente(nuevo);
        }
        fin = nuevo;
        cantidad++;
    }

    @Override
    public void eliminar(T elemento) {
        NodoLista<T> actual = inicio;
        if(inicio.getElemento().equals(elemento)){
            inicio = inicio.getSiguiente();
            cantidad--;
        }
        while (actual != null) {
            if(actual.getSiguiente().getElemento().equals(elemento)) {
                actual.setSiguiente(actual.getSiguiente().getSiguiente());
                cantidad--;
            }
            actual = actual.getSiguiente();
        }
    }

    @Override
    public void eliminar(int posicion) {
        int current = 0;
        NodoLista<T> actual = inicio;
        while (actual != null && current < posicion) {
            if(current +1 == posicion){
                actual.setSiguiente(actual.getSiguiente().getSiguiente());
                current++;
                cantidad--;
            }
            actual = actual.getSiguiente();
        }
    }

    @Override
    public void vaciar() {
        inicio = null;
        fin = null;
        cantidad = 0;
    }

    @Override
    public int size() {
        return this.cantidad;
    }

    @Override
    public boolean isEmpty() {
        return this.cantidad == 0;
    }

    @Override
    public String mostrar() {
        StringBuilder sb = new StringBuilder();
        NodoLista<T> actual = inicio;
        while (actual != null) {
            sb.append(actual.getElemento().toString()).append("|");
            actual = actual.getSiguiente();
        }
        return sb.substring(0, sb.length() - 1);
    }

    @Override
    public boolean estaElemento(T elemento) {
        boolean encontrado = false;
        NodoLista<T> actual = inicio;
        while(actual != null && !encontrado){
            if(actual.getElemento().equals(elemento)){
                encontrado = true;
            }
            actual = actual.getSiguiente();
        }
        return encontrado;
    }

    @Override
    public T getElemento(T elemento) {
        T encontrado = null;
        NodoLista<T> actual = inicio;
        while(actual != null ){
            if(actual.getElemento().equals(elemento)){
                encontrado = actual.getElemento();
            }
            actual = actual.getSiguiente();
        }
        return encontrado;
    }

    @Override
    public void insertarOrdenado(T elemento) {
        NodoLista<T> nuevo = new NodoLista<>(elemento);
        if(inicio == null) {
            insertar(elemento);
        }else{
            NodoLista<T> actual = inicio;
            boolean insertado = insertarOrdenado(elemento,actual,nuevo);
            if(!insertado){
                insertar(elemento);
            }else {
                cantidad++;
            }
        }
    }

    @Override
    public void Ordenar() {
        //TODO: Ordenar la lista
        throw new RuntimeException("Method not implemented yet");
    }

    private boolean insertarOrdenado(T elemento, NodoLista<T> actual, NodoLista<T> nuevo) {
        boolean insertado = false;
        if(actual.getElemento().compareTo(elemento) > 0){
            nuevo.setSiguiente(actual);
            inicio = nuevo;
        }
        while (actual.getSiguiente() != null && !insertado) {
            if(actual.getSiguiente().getElemento().compareTo(elemento) > 0){
                nuevo.setSiguiente(actual.getSiguiente());
                actual.setSiguiente(nuevo);
                insertado = true;
            }
            actual = actual.getSiguiente();
        }
        return insertado;
    }

    @Override
    public boolean isOrdenado() {
        boolean ordenado = true;
        NodoLista<T> actual = inicio;
        while (actual.getSiguiente() != null && ordenado) {
            if(actual.getElemento().compareTo(actual.getSiguiente().getElemento()) > 0){
                ordenado = false;
            }
            actual = actual.getSiguiente();
        }
        return ordenado;
    }

    @Override
    public int compareTo(Lista<T> o) {
        return  Integer.compare(this.cantidad,o.cantidad);
    }
}
