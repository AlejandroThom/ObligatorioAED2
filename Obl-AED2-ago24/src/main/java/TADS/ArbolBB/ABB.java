package TADS.ArbolBB;

public class ABB <T extends Comparable<T>> implements IABB<T> {

    private NodoABB<T> raiz = null;

    private int longitud = 0;


    @Override
    public void insertar(T element) {
        NodoABB<T> nuevo = new NodoABB<>(element);
        if(raiz == null) raiz = nuevo;
        else {
            insertarREC(nuevo,raiz);
        }
        longitud++;
    }

    private void insertarREC(NodoABB<T> nuevo,NodoABB<T> nodo) {
        if(nodo == null) return;
        if(nodo.getDato().compareTo(nuevo.getDato()) > 0){
            if(nodo.getHijoIzquierdo() != null)
                insertarREC(nuevo,nodo.getHijoIzquierdo());
            else
                nodo.setHijoIzquierdo(nuevo);
        }else {
            if(nodo.getHijoDerecho() != null)
                insertarREC(nuevo,nodo.getHijoDerecho());
            nodo.setHijoDerecho(nuevo);
        }
    }

    @Override
    public void remover(T element) throws Exception {
        throw new Exception();
    }

    private void removerREC(NodoABB<T> nodo, T element) {
        if(nodo == null) return;
        if(nodo.getDato().equals(element)){
            nodo.setDato(null);
        }
    }

    @Override
    public T encontrar(T element) {
        return null;
    }

    @Override
    public int longitud() {
        return this.longitud;
    }

    @Override
    public boolean isEquilibrado() {
        return false;
    }

    @Override
    public void Equilibrar() {

    }

    @Override
    public String listarAscendente() {
        return listarAscendenteREC(this.raiz);
    }

    private String listarAscendenteREC(NodoABB<T> nodo) {
        if(nodo == null) return "";
        if (nodo.getHijoIzquierdo() == null && nodo.getHijoDerecho() == null){
            return nodo.getDato().toString();
        }
        return listarAscendenteREC(nodo.getHijoIzquierdo()) + nodo.getDato().toString() + listarAscendenteREC(nodo.getHijoDerecho()) ;
    }

    @Override
    public String listarDescendente() {
        return listarDescendenteREC(this.raiz);
    }

    private String listarDescendenteREC(NodoABB<T> nodo) {
        if(nodo == null) return "";
        if (nodo.getHijoIzquierdo() == null && nodo.getHijoDerecho() == null){
            return nodo.getDato().toString();
        }
        return listarDescendenteREC(nodo.getHijoDerecho()) + nodo.getDato().toString() + listarDescendenteREC(nodo.getHijoIzquierdo());
    }

    @Override
    public boolean estaVacia() {
        return this.longitud == 0;
    }

    @Override
    public boolean pertenece(T elemento) {
        return perteneceREC(this.raiz,elemento);
    }

    private boolean perteneceREC(NodoABB<T> nodo, T elemento) {
        if(nodo == null) return false;
        if(nodo.getDato().equals(elemento)) return true;
        if(nodo.getDato().compareTo(elemento) > 0){
            return perteneceREC(nodo.getHijoIzquierdo(),elemento) ;
        }else {
            return perteneceREC(nodo.getHijoDerecho(),elemento);
        }
    }
}
