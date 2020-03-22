/*******************************************************************************
 * Nombre de la clase: Venta                                                  
 *
 * Informacion de la version: Genera un objeto tipo venta que tiene un 
 * constructor sus respectivos getters y setters.
 *
 * Fecha: 09 de Marzo 2020
 *
 * @autor Victor Manuel Arredondo Reyes
 ******************************************************************************/
package model;


public class Venta {
   
    protected String nombre;
    protected short cantidad;
    protected String fecha;
   
   

    public Venta(String nombre, short cantidad) {
        Fecha fechaV= new Fecha();
        this.nombre = nombre;
        this.cantidad = cantidad;
        this.fecha= fechaV.fecha();
       
    }

   

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }
   
    

   

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public short getCantidad() {
        return cantidad;
    }

    public void setCantidad(short cantidad) {
        this.cantidad = cantidad;
    }

   
    
    
}
