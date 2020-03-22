/*******************************************************************************
 * 
 * Nombre de la clase: Coneccion_BD
 *
 * Informacion de la version: Genera la conexion a la base de datos para despues
 * ejecutar las sentencias sql y guardar,actualizar, mostrar y eliminar 
 * productos o articulos.
 *
 * Fecha: 09 de Marzo 2020
 *
 * @autor Victor Manuel Arredondo Reyes
 ******************************************************************************/
package model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;


public class Coneccion_BD {

   private static String driver ="com.mysql.cj.jdbc.Driver";
   
   private static String database="proyectoinventario";
   
   private static String hostname="localhost";
   
   private static final String port="3306";
   
   // public static String url="jdbc:mysql://"+hostname+":"+ port + "/"+ database +"?useSSL=false";
   private static String url="jdbc:mysql://"+hostname+":"+ port + "/"+ database +"?useTimezone=true&serverTimezone=UTC";
   
   private static String username="admin";
   
   private static String password="admin";
   
   Connection estadoConexion = null;
   
   
     public Connection conectar() throws ClassNotFoundException{
   
      try{
   
       Class.forName(driver);

       estadoConexion = DriverManager.getConnection(url,username,password);
   
        if (estadoConexion == null) {
            
         System.out.println("Error en la conexion");
         
        } else {
         System.out.println("[...]");
        }
      }catch(SQLException e){
       System.out.println("Error al conectar debido a: " + e);
      } 
      
       return estadoConexion;
        
    }
    
}

