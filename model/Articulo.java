/*******************************************************************************
 * Nombre de la clase: Articulo 
 *
 * Informacion de la version: Crea el objeto articulo o tambien nombrado 
 * producto para luego enviarlo a la base de datos con la mayoria de datos 
 * obtenidos a traves de teclado y uno es derivado del precio y la cantidad.
 *
 * Fecha: 09 de Marzo 2020
 *
 * @autor Victor Manuel Arredondo Reyes
 ******************************************************************************/
package model;


public class Articulo {
    protected short clave;
    protected float precio;
    protected String nombre;
    protected String unidad;
    protected short cantidad;
    protected float total;
                    
    
    public Articulo(){
    this.clave= 0;
    this.nombre="";
    this.precio=0;
    this.cantidad=0;
    this.unidad="";
    
    }
 /*
 * Constructor únicamente para invocación en el metodo de eliminar producto
 * en la clase Operaciones
 */
    public Articulo(short clave, String nombre){
    this.clave= clave;
    this.nombre= nombre;
    this.precio=0;
    this.cantidad=0;
    this.unidad="";
    }
    
  /*
  * Constructor utilizado para los metodos añadirProductoexistente y 
  * reducirProductoexistente en la clase Operaciones
  */  
     public Articulo(String nombre, short cantidad){
    this.clave= 0;
    this.nombre= nombre;
    this.precio=0;
    this.cantidad= cantidad;
    this.unidad="";
    }
 
  /*
  * Constructor utilizado para los metodos guardarProducto y actualizarProducto
  * en la clase Operaciones 
  */ 
    public Articulo(short clave, float precio, String nombre, String unidad, short cantidad) {
        this.clave = clave;
        this.precio = precio;
        this.nombre = nombre;
        this.unidad = unidad;
        this.cantidad = cantidad;
        this.total = this.cantidad * this.precio;
    }

    public float getTotal() {
        return total;
    }

    public void setTotal(float total) {
        this.total = total;
    }

    public int getClave() {
        return clave;
    }

    public void setClave(short clave) {
        this.clave = clave;
    }

    public float getPrecio() {
        return precio;
    }

    public void setPrecio(float precio) {
        this.precio = precio;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getUnidad() {
        return unidad;
    }

    public void setUnidad(String unidad) {
        this.unidad = unidad;
    }

    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(short cantidad) {
        this.cantidad = cantidad;
    }

    void add(Articulo c) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
  
    
    
    
}
