package dominio;

import interfaz.Categoria;

public class Jugador implements Comparable<Jugador> {

    private String nombre;
    private String apellido;
    private String alias;
    private Categoria categoria;

    private Equipo equipo;


    public  Jugador(String alias, String nombre, String apellido,Categoria categoria) {
        this.nombre = nombre;
        this.apellido = apellido;
        this.alias = alias;
        this.categoria = categoria;
    }

    //Usado para busquedas
    public Jugador(String alias){
        this.alias = alias;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public String getAlias() {
        return alias;
    }

    public void setAlias(String alias) {
        this.alias = alias;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public Categoria getCategoria() {
        return categoria;
    }

    public void setCategoria(Categoria categoria) {
        this.categoria = categoria;
    }

    public Equipo getEquipo() {
        return equipo;
    }

    public void setEquipo(Equipo equipo) {
        this.equipo = equipo;
    }

    @Override
    public String toString() {
        String categoriaImprimir = "";
        if(this.categoria.getIndice() == Categoria.PRINCIPIANTE.getIndice()){
            categoriaImprimir = "PRINCIPIANTE";
        }else if(this.categoria.getIndice() == Categoria.ESTANDARD.getIndice()){
            categoriaImprimir = "ESTANDARD";
        }else{
            categoriaImprimir = "PROFESIONAL";
        }
        return this.alias + ";" + this.nombre + ";" + this.apellido + ";" + categoriaImprimir;
    }

    @Override
    public boolean equals(Object obj) {
       return obj instanceof Jugador && this.alias.equals(((Jugador)obj).alias);

    }

    @Override
    public int compareTo(Jugador o) {
        return this.alias.compareTo(o.alias);
    }

    public boolean tieneEquipo() {
        return this.equipo != null;
    }
}
