package dominio;

import TADS.Grafos.AristaGrafo;
import TADS.Lista.Lista;

public class Sucursal implements Comparable<Sucursal> {
    private String codigo;
    private String nombre;

    private Lista<AristaGrafo<Sucursal>> conexiones;

    public  Sucursal(String codigo, String nombre) {
        this.codigo = codigo;
        this.nombre = nombre;
    }

    //Usado para búsquedas
    public Sucursal(String codigo) {
        this.codigo = codigo;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public boolean tieneConexion(Sucursal sucursal){
        return this.conexiones.estaElemento(new AristaGrafo<>(sucursal));
    }

    public void agregarConexion(Sucursal sucursal, int peso){
        AristaGrafo<Sucursal> conexion = new AristaGrafo<>(sucursal,peso);
        conexiones.insertar(conexion);
    }

    public void actualizarConexion(Sucursal sucursal,int nuevaLatencia){
        AristaGrafo<Sucursal> conexion = new AristaGrafo<>(sucursal);
        conexion = conexiones.getElemento(conexion);
        conexion.setPeso(nuevaLatencia);
    }

    @Override
    public String toString() {
        return this.codigo+";"+this.nombre;
    }

    @Override
    public boolean equals(Object obj) {
        return obj instanceof Sucursal && codigo.equals(((Sucursal) obj).codigo);
    }

    @Override
    public int compareTo(Sucursal o) {
        return this.codigo.compareTo(o.getCodigo());
    }
}
