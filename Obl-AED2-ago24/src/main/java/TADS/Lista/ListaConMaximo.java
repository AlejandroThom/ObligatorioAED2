package TADS.Lista;

public class ListaConMaximo <T extends Comparable<T>> implements ILista<T>{

    private NodoLista<T> inicio;
    private NodoLista<T> fin;
    private int cantidad = 0;
    private int maximo;

    public ListaConMaximo(int maximo) {
        this.maximo = maximo;
    }

    public ListaConMaximo(T dato,int maximo) {
        NodoLista<T> nuevo = new NodoLista<>(dato);
        this.inicio = nuevo;
        this.fin = nuevo;
        this.maximo = maximo;
        cantidad++;
    }



    @Override
    public void insertar(T elemento) {
        if(cantidad < maximo){
            NodoLista<T> nuevo = new NodoLista<>(elemento);
            if(inicio == null) {
                inicio = nuevo;
            }else{
                fin.setSiguiente(nuevo);
            }
            fin = nuevo;
            cantidad++;
        }
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
        return sb.toString();
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
        if(this.cantidad < this.maximo){
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
    public void Ordenar() {

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

}
