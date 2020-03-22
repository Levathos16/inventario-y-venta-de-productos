/*******************************************************************************
 * Nombre de la clase: Operaciones
 *
 * Informacion de la version: Crea el objeto articulo o tambien nombrado produc-
 * to para luego enviarlo a la base de datos con la mayoria de datos obtenidos 
 * a traves de teclado y uno es derivado del precio y la cantidad.
 *
 * Fecha: 09 de Marzo 2020
 *
 * @author Victor Manuel Arredondo Reyes
 ******************************************************************************/
package vista;

import java.util.List;
import model.Articulo;
import model.FuncionesArticulo;

import model.Venta;


public class Operaciones {
    
    Mensajes ms;
    FuncionesArticulo articuloFuncion;
    Articulo producto;
    String sentenciaSQL;
    Venta capturarVenta;
    
   public Operaciones(){
    this.ms= new Mensajes();
    this.articuloFuncion= new FuncionesArticulo(); 
    this.producto=new Articulo();
    }
    
    
    
    public void guardarProducto(){
    ms.agregarProducto();
    String table="inventarioproductos";
    producto= new Articulo(ms.leerClave(),ms.leerPrecio(),ms.leerNombre(),ms.leerUnidad(),ms.leerCantidad());
    sentenciaSQL = "INSERT INTO " + table + " values("+ producto.getClave()+ ","+producto.getPrecio() + ",'"+ producto.getNombre() + "','" + producto.getUnidad() + "'," + producto.getCantidad() +","+ (producto.getCantidad()* producto.getPrecio()) +");";
    articuloFuncion.actionProduct(sentenciaSQL,"create");
    }
    
    public void mostrarProductos(){
        
    List<Articulo> lista= articuloFuncion.readAll("Select * from inventarioproductos");
    ms.mostrarInventario(lista);
    
    }
    
    public void actualizarProducto(){    
    ms.actualizandoProducto();
    producto= new Articulo(ms.leerClave(),ms.leerPrecio(),ms.leerNombre(),ms.leerUnidad(),ms.leerCantidad());
    
    sentenciaSQL = "UPDATE inventarioproductos set clave='"+ producto.getClave()+ "',preciounitario='"+producto.getPrecio() + "',nombre='"+ producto.getNombre() + "', tipounidad='" + producto.getUnidad() + "', cantidad='" + producto.getCantidad() +"', preciototal='"+ (producto.getCantidad()* producto.getPrecio()) + "' where clave='"+producto.getClave()+"'";     
    
    articuloFuncion.actionProduct(sentenciaSQL,"update");
    
    }
        
    public void eliminarProducto(){
    ms.eliminarProducto();
    
    producto= new Articulo(ms.leerClave(),ms.leerNombre());
    
    articuloFuncion.delete(producto);
    }
    
    public void realizarVenta(){
     
   
      List<Articulo> listaVenta= articuloFuncion.readAll("Select *  from inventarioproductos");
      ms.mostrarVenta(listaVenta);
     if(listaVenta.isEmpty()){
       return;
     } else {
       System.out.println("\t Ingresa los datos necesarios de la venta \t");   
   
        capturarVenta= new Venta(ms.leerNombre(),ms.leerCantidad());
    
        articuloFuncion.venta(capturarVenta);
     }
    }
    
    public void añadirProductoexistente(){
    
    ms.actualizandoProducto();
    System.out.println("\t Ingresa la cantidad que se agregara y el nombre del producto \t"); 
    producto= new Articulo(ms.leerNombre(),ms.leerCantidad());
    
    articuloFuncion.agregarCantidad(producto);
    
    }
    
     public void reducirProductoexistente(){
    
    ms.actualizandoProducto();
    System.out.println("\t Ingresa la cantidad que se eliminara y el nombre del producto \t"); 
    producto= new Articulo(ms.leerNombre(),ms.leerCantidad());
    
    articuloFuncion.eliminarCantidad(producto);
    
    }
     
    public void ejecutarAplicacion(){
        Operaciones op= new Operaciones();

        int opcion=0;
        do{
             ms.menu();
             opcion= ms.opcionEntero();
             
             switch(opcion){
            case 1: 
             op.guardarProducto();
               break;
             
            case 2:
              
              op.mostrarProductos();
              
              op.actualizarProducto();;
                break;
                
            case 3:
                op.mostrarProductos();
                
                op.eliminarProducto();
                break;
            case 4:
                op.mostrarProductos();
                break;
            case 5:
                
                op.realizarVenta();
                break;
            case 6:
                op.mostrarProductos();
                op.añadirProductoexistente();
                break;
                
            case 7:
                op.mostrarProductos();
                op.reducirProductoexistente();
                break;  
            case 0:
                System.out.println("Saliendo  [...]");
                break;
             }
        
        } while (opcion !=0);
        
    }
    
    
    
}
