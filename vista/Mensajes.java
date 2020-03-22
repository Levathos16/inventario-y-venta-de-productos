/*******************************************************************************
 * Nombre de la clase: Mensajes
 *
 * Informacion de la version: Muestra los mensaje para el menu del programa, 
 * ademas de pedir los datos para capturarlos posteriormente en el objeto 
 * Articulo. Ademas se encarga de mostrar los datos de los articulos de la base
 * de datos que son capturados en una lista obtenida por el metodo readAll de 
 * la clase FuncionesArticulo y mostradas en pantalla tanto para consulta como 
 * para realizar ventas.
 *
 * Fecha: 09 de Marzo 2020
 *
 * @autor Victor Manuel Arredondo Reyes
 ******************************************************************************/

package vista;

import java.util.Iterator;
import java.util.List;

import model.Articulo;


public class Mensajes {
    
    Teclado teclado;
    Articulo productoCapturado;
    Iterator<Articulo> lectorRegistros;
    
    public Mensajes(){
    this.teclado= new Teclado();
    }
    
    public int opcionEntero(){
    int opcion= 9;
    while(opcion<=0 || opcion>8 ){
    System.out.println("Introduce el dato de tipo entero: ");
    opcion= teclado.leerEntero();
    }
    return opcion;
    
    }
    //Venta
    //registro
    //
    
    public void menu(){
      System.out.println("   Menu ");
            System.out.println("1) Agregar producto ");
            System.out.println("2) Modificador producto ");
            System.out.println("3) Eliminar producto ");
            System.out.println("4) Mostrar productos ");
            System.out.println("5) Realizar venta ");
            System.out.println("6) Agregar mas cantidad de un producto ");
            System.out.println("7) Eliminar cantidad de un producto ");
            System.out.println("0) Salir ");
            System.out.println("   Ingrese una opcion:  ");
    }
    
    public void agregarProducto(){
    System.out.println("Ingresando un producto... ");
    }
    public void eliminarProducto(){
    System.out.println("Eliminando producto... ");
    }
    public void actualizandoProducto(){
    System.out.println("Actualizando producto... : ");
    }
    
    public short leerClave(){
    short clave=0;
     while(clave==0 || clave<0){
       System.out.println("Introduce su clave del articulo: ");
       clave= teclado.leerEntero();
       
     }
       return clave;
    }
    
    public String leerNombre(){
    String nombre="";
    while(nombre.equals("")){
    System.out.println("Introduce un nombre: ");
    nombre= teclado.leerCadena();
    }
    return nombre;
    
    }
    public float leerPrecio(){
    float precio=0;
    while(precio==0 || precio<0){
    System.out.println("Introduce su precio: ");
    
    precio= teclado.leerFlotante();
    }
    
    return precio;
    
    }
    
    public short leerCantidad(){
    short cantidad=0;
    while(cantidad==0 || cantidad<0){
    System.out.println("Introduce su cantidad: ");
    cantidad=teclado.leerEntero();
    }
    return cantidad;
    
    }
    
     public String leerUnidad(){
    String unidad="";
    while(unidad.equals("")){
    System.out.println("Introduce su unidad: ");
    unidad=teclado.leerCadena();
    }
    return unidad;
    
    }
  
    public void mostrarInventario(List<Articulo> lista){
        
       if(lista.isEmpty()){
         System.out.println("¡Añada algun producto para realizar la accion! \n");
         return;
       } else {
        
        System.out.println("\t El inventario actual es: ");
        lectorRegistros= lista.iterator();
        
        
        while(lectorRegistros.hasNext()){
        productoCapturado= lectorRegistros.next();
     
        System.out.println("\t Clave: "+ productoCapturado.getClave()+ " | Nombre: "+ productoCapturado.getNombre() + 
        " | Precio: $"+ productoCapturado.getPrecio()+" | Cantidad: "+ productoCapturado.getCantidad()+" Unidad: " + 
         productoCapturado.getUnidad()+ " | Precio Total: $" + productoCapturado.getTotal() + " |");
            
        }
        System.out.println("\n");
       }
    }
    
    public void mostrarVenta(List<Articulo> lista){
       
       if(lista.isEmpty()){
         System.out.println("¡Añada algun producto para realizar la accion! \n");
         return;
       } else {
        
        System.out.println("\t El inventario actual es: ");
        Iterator<Articulo> iterador= lista.iterator();
        while(iterador.hasNext()){
            productoCapturado= iterador.next();
            System.out.println("\t | Nombre: "+ productoCapturado.getNombre()
            +" | Precio: $"+ ((productoCapturado.getPrecio())*(1.20)) +" | Existencias: "+ productoCapturado.getCantidad()+" Unidad: "+ productoCapturado.getUnidad()+ " |");
            
        }
        System.out.println("\n");
       }
    }
    
    
    
    // Agregar cantidades y eliminar cantidades
    // 
    
    public void resultadoOperacion(boolean resultado, String operacion){
    if(resultado){
    
    System.out.println("\n Resultado exitoso al "+ operacion + "\n");
    }else{
    System.out.println("\n Resultado fallido al "+ operacion + "\n");
    }
    
    
    
    }
    
}
