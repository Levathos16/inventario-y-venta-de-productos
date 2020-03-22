/*******************************************************************************
 * Nombre de la clase: FuncionesArticulo
 *
 * Información @version: El codigo establece el funcionamiento para guardar
 * , actualizar, eliminar y capturar los datos de los productos de la base de 
 * datos. La base de datos principal es inventarioproductos y 
 * recuperacioniventario sirve para almacenar productos eliminados. 
 * 
 * @version: 09 de Marzo 2020
 *
 * @autor: Victor Manuel Arredondo Reyes
 ******************************************************************************/

package model;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import vista.Mensajes;


public class FuncionesArticulo {
        
    FuncionesArticulo funcionArticulo; //Se crea el objeto tipo funciones articulo para posteriormente instanciarlo
    Coneccion_BD crearConexion=null;
    Statement ejecutorSentencia_sql= null;
    Connection estadoConexion = null;
    String sentenciaSQL="";            //Establece el comando a ejecutar en la mysql 
    List<Articulo> listaInventario;
    Iterator<Articulo> lectorRegistros;
    Mensajes ms;                       //Se crea el objeto tipo mensajes para posteriormente instanciarlo
    
    /*********************************************************************************************
    * Metodo: actionProduct                                                                              
    * Descripcion: Recibe como parametro la cadena sql que muestra el comando mysql y la cadena  
    *              que especifica la funcion a realizar que puede ser actualizar y agregar un    
    *              nuevo producto.                                                               
    *                                                                                              
    * @param sql cadena que establece el comando a ejecutar en la mysql
    * @param funcion cadena de la informacion de la accion que se ejecutara 
    * @return la variable booleana action si fue correcto el metodo mostrara que fue correcto
    * 
    **********************************************************************************************/
  public boolean actionProduct(String sql, String funcion) {
        
   boolean action= false;
       
   crearConexion= new Coneccion_BD();  
          
   try{
            
     estadoConexion= crearConexion.conectar();
     ejecutorSentencia_sql= estadoConexion.createStatement();
     ejecutorSentencia_sql.execute(sql);
     action= true;
     ejecutorSentencia_sql.close();
     estadoConexion.close();
           
    }catch (SQLException e) {
             
      if(funcion.equals("create")){
           
       System.out.println("Error al agregar Producto, metodo registrar");
             
      } else if(funcion.equals("update")){
            
         System.out.println("Error al actualizar Producto, metodo registrar");
         
      }
        e.printStackTrace();
      } catch (ClassNotFoundException ex) {
            Logger.getLogger(FuncionesArticulo.class.getName()).log(Level.SEVERE, null, ex);
      }
         
     if (action==true) {
               
      if (funcion.equals("create")) {
            
       System.out.println("Producto nuevo agregado");
       
      } else if (funcion.equals("update")) {
            
        System.out.println("Producto actaulizado");
      }     
     }
           
     return action;
        
    }
    
    /*Modificar producto
    public boolean update(String sql) {
        boolean update= false;
        Statement stm= null;
        Connection con = null;
            Coneccion_BD cc= new Coneccion_BD();  
           try{
           con= cc.conexion();
           stm= con.createStatement();
           stm.execute(sql);
           update= true;
           stm.close();
           con.close();
           
           }catch (SQLException e) {
            System.out.println("Error al actualizar Producto, metodo registrar");
             e.printStackTrace();
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(FuncionesArticulo.class.getName()).log(Level.SEVERE, null, ex);
        }
           if(update=true){
           System.out.println("Producto actualizado");
           }
           return update;
    }
    */
    
    
   /*********************************************************************************************
    * Metodo: delete                                                                              
    * Descripcion: Recibe como parametro la clave y nombre del objeto que se eliminara del 
    *              inventario de productos.
    * 
    * @param artic 
    * @return 
    **********************************************************************************************/
    
  public boolean delete(Articulo artic) {
        
   funcionArticulo= new FuncionesArticulo();
   crearConexion= new Coneccion_BD();  
   boolean deleted= false;
  
   String table="recuperacionproductos";
   Articulo deleteArticulo = funcionArticulo.capturarProducto(artic.getClave(),artic.getNombre());  
       if(deleteArticulo==null)
       return deleted;
     
     //Despues de capturar el producto se guarda en la otra base de datos   
     sentenciaSQL = "INSERT INTO " + table + " values("+ deleteArticulo.getClave() +
     "," + deleteArticulo.getPrecio() + ",'"+ deleteArticulo.getNombre() + "','" + 
             deleteArticulo.getUnidad() + "'," + deleteArticulo.getCantidad() +","+ 
             (deleteArticulo.getCantidad()* deleteArticulo.getPrecio()) +");";
     
     funcionArticulo.actionProduct(sentenciaSQL,"create");          
   
     //fin de copia de productos en base de datos para recuperacion
           
     sentenciaSQL = "Delete from inventarioproductos where nombre='"+ artic.getNombre() + "' and clave='"+ artic.getClave() + "' limit 1";
      
    try{
           
     try {
      
      estadoConexion= crearConexion.conectar();
      } catch (ClassNotFoundException ex) {
                   Logger.getLogger(FuncionesArticulo.class.getName()).log(Level.SEVERE, null, ex);
      }
     
      ejecutorSentencia_sql= estadoConexion.createStatement();
      ejecutorSentencia_sql.execute(sentenciaSQL);
      deleted=true;
      ejecutorSentencia_sql.close();
      estadoConexion.close();
           
    } catch (SQLException e) {
       System.out.println("Error al eliminar Producto en metodo: 'delete' debido a: " + e);
    } 
           
    if (deleted==true) {
       System.out.println("Producto eliminado");         
    }
      
      return deleted; 
       
    
    }
    

    public Articulo capturarProducto(int clave, String nombre){
      funcionArticulo = new FuncionesArticulo();
      int b=0;
      listaInventario= funcionArticulo.readAll("Select * from inventarioproductos where clave='"+ clave + "'");
        
      lectorRegistros= listaInventario.iterator();
      Articulo productoCapturado=lectorRegistros.next();
         
        while(lectorRegistros.hasNext() && b==0){
            
           productoCapturado= lectorRegistros.next();
           
            if (productoCapturado.getClave()==clave) 
                b=1;  
            
        }

      if (b==0) {
           
        System.out.println("\t No existe el producto buscado: " + nombre + " en el inventario " + "favor de ingresar otro...");
       
       return capturarProducto(ms.leerClave(),ms.leerNombre());
 
      }
        return productoCapturado;
 
    }
    /********************************************************************************************
    * Metodo: readAll                                                                               
    * Descripcion: Captura todos los registros que se piden en la sentencia sql dentro de una 
    *              lista que se retorna cuando ya no hay mas productos.          
    * 
    * @param sql    
    * 
    * @return 
    * 
    **********************************************************************************************/
    
    public List<Articulo> readAll(String sql) {
   
    ResultSet rs= null;
    listaInventario = new ArrayList<Articulo>();
    try{     
        try {
            estadoConexion = new Coneccion_BD().conectar();
        } catch (ClassNotFoundException ex) {
           System.out.println("Error con: " + ex);
        }
              
    ejecutorSentencia_sql = estadoConexion.createStatement();
    rs= ejecutorSentencia_sql.executeQuery(sql);
 
     while(rs.next()){  
       Articulo articuloCapturado= new Articulo(rs.getShort(1),rs.getFloat(2),
       rs.getString(3),rs.getString(4),rs.getShort(5));
       
       listaInventario.add(articuloCapturado);
       
     }
  
      ejecutorSentencia_sql.close();
      rs.close();
      estadoConexion.close();
   } catch (SQLException e){
      System.out.println("Error: Clase Articulo_Fun, metodo readAll");

   }    
   
     return listaInventario;
  }
    
    /********************************************************************************************
    * Metodo: Venta                                                                               
    * Descripcion: Obtiene el objeto venta que contiene el nombre del producto y su cantidad      
    *              comprada, ademas de la fecha generada en el constructos de la clase Venta.      
    *              Consulta si la cantidad del producto pedido es suficiente con la del almacen    
    *              y solo entonces actualiza el inventario y realiza el registro de la venta.             
    * 
    * @param capturaVenta captura la venta con el nombre del producto, su cantidad, el total y 
    *                     la fecha en que se realizo.
    * 
    * @return 
    * 
    **********************************************************************************************/

    public boolean venta (Venta capturaVenta) {
      
       //Obtencion de cantidad de productos
        
        
       funcionArticulo= new FuncionesArticulo();

        boolean venta= false;
  
        listaInventario= funcionArticulo.readAll("Select * from inventarioproductos where nombre='"+ capturaVenta.getNombre() + "'");
         Articulo articuloCapturado= lectorRegistros.next();
         //Capturar datos para guardarlos en un objeto del producto
  
             if(articuloCapturado.getCantidad()>=capturaVenta.getCantidad()){
               sentenciaSQL = "UPDATE inventarioproductos set cantidad='" + ((articuloCapturado.getCantidad()) - (capturaVenta.getCantidad())) +"' where nombre='"+capturaVenta.getNombre()+"'";
                String table= "venta";
                funcionArticulo.actionProduct(sentenciaSQL,"update");
               
                sentenciaSQL = "INSERT INTO " + table + " values(default,'"+ capturaVenta.getNombre() + "','" + capturaVenta.getCantidad() +"','"+ (capturaVenta.getCantidad()* articuloCapturado.getPrecio()) +"','"+ capturaVenta.getFecha()+"');";
               
                funcionArticulo.actionProduct(sentenciaSQL,"create");
               
                } else {
                   System.out.println("La cantidad no es suficiente para realizar la venta, solo se tiene " + articuloCapturado.getCantidad() + " de " + capturaVenta.getNombre());
               
                 return venta;
            }
        
         
        if(venta=true){
           System.out.println("Venta realizada con exito, se compro "+ capturaVenta.getCantidad() + " de " + capturaVenta.getNombre());
           
        }  
           return venta;
    
    }
    
    /********************************************************************************************
    * Metodo: agregarCantidad                                                                              
    * Descripcion: Obtiene el objeto venta que contiene el nombre del producto y su cantidad      
    *              comprada, ademas de la fecha generada en el constructos de la clase Venta.      
    *             
    * @param capturarProducto 
    * @return 
    * 
    **********************************************************************************************/
    
    public boolean agregarCantidad(Articulo capturarProducto) {
      
       //Obtencion de cantidad de productos
        
       funcionArticulo= new FuncionesArticulo();

        boolean agregar= false;
  
        listaInventario= funcionArticulo.readAll("Select * from inventarioproductos where nombre='"+ capturarProducto.getNombre() + "'");
        
       //Capturar datos para guardarlos en un objeto del producto
  
       lectorRegistros= listaInventario.iterator();
        while(lectorRegistros.hasNext()){
            Articulo articuloCapturado= lectorRegistros.next();
            
            //Despues de capturar el producto se guarda en la otra base de datos    
             if(articuloCapturado.getNombre().equals(capturarProducto.getNombre())){
               sentenciaSQL = "UPDATE inventarioproductos set cantidad='" + ((articuloCapturado.getCantidad())+(capturarProducto.getCantidad())) + "' where nombre='"+capturarProducto.getNombre()+"'";     
    
               funcionArticulo.actionProduct(sentenciaSQL,"update");
     
                } else {
                   System.out.println("No se encuentra el producto: " + capturarProducto.getNombre());
               
                 return agregar;
            }
        }
         
        if(agregar=true){
           System.out.println("Se agrego con exito:  "+ capturarProducto.getCantidad() + " unidades de " + capturarProducto.getNombre());
           
        }  
           return agregar;
    
    }
    
    public boolean eliminarCantidad(Articulo capturarProducto) {
      
       //Obtencion de cantidad de productos
        
       funcionArticulo= new FuncionesArticulo();

        boolean disminuir= false;
  
        listaInventario= funcionArticulo.readAll("Select * from inventarioproductos where nombre='"+ 
                                                 capturarProducto.getNombre() + "'");
        
       //Capturar datos para guardarlos en un objeto del producto
  
       lectorRegistros= listaInventario.iterator();
        while(lectorRegistros.hasNext()){
            Articulo articuloCapturado= lectorRegistros.next();
            
            //Despues de capturar el producto se guarda en la otra base de datos    
             if(articuloCapturado.getNombre().equals(capturarProducto.getNombre())){
               sentenciaSQL = "UPDATE inventarioproductos set cantidad='" + 
                ((articuloCapturado.getCantidad())-(capturarProducto.getCantidad())) + 
                 "', preciounitario='"+ ((articuloCapturado.getTotal())/((articuloCapturado.getCantidad())-(capturarProducto.getCantidad()))) 
                   +"' where nombre='"+capturarProducto.getNombre()+"'";     
    
               funcionArticulo.actionProduct(sentenciaSQL,"update");
     
                } else {
                   System.out.println("No se encuentra el producto: " + capturarProducto.getNombre());
               
                 return disminuir;
            }
        }
         
        if(disminuir=true){
           System.out.println("Se elimino con exito:  "+ capturarProducto.getCantidad() + " unidades de " + capturarProducto.getNombre());
           
        }  
           return disminuir;
    
    }
    
    }


