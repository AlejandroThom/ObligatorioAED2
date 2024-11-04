package TADS.Pair;

public class Pair <T extends Comparable<T>, K extends Comparable<K>> implements Comparable<Pair<T,K>> {

    //<editor-fold desc="Attributes">
    private T first;
    private K second;
    //<editor-fold>

    //<editor-fold desc="Constructors">
    public Pair(T first, K second) {
        this.first = first;
        this.second = second;
    }
    //<editor-fold>

    //<editor-fold desc="Getters ans Setters">
    public T getFirst() {
        return first;
    }

    public void setFirst(T first) {
        this.first = first;
    }

    public K getSecond() {
        return second;
    }

    public void setSecond(K second) {
        this.second = second;
    }
    //<editor-fold >
    
    //<editor-fold desc="Override Methods">
    @Override
    public int compareTo(Pair<T, K> o) {
        return second.compareTo(o.getSecond());
    }
    //<editor-fold desc="Methods">

}
