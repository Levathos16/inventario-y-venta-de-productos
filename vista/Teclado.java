/*******************************************************************************
 * Nombre de la clase: Teclado
 *
 * Informacion de la version: Clase encargada de recibir y capturar los datos 
 * del producto o articulo y los retorna para guardarse en un objeto.
 *
 * @version: 09 de Marzo 2020
 *
 * @autor Victor Manuel Arredondo Reyes
 ******************************************************************************/
package vista;

import java.util.Scanner;

public class Teclado {
    
    public short leerEntero(){
    short entero = 0;
    
    Scanner sc= new Scanner(System.in);
 
      try{
      entero=sc.nextShort();
 
      } catch (Exception e){
      
       System.out.println("Error al leer el dato,  ingresa nuevamente un dato que sea entero o valido: " + e);
       return leerEntero();
      } 
    
    return entero;
    
    }
    
     public int leerFlotante(){
    float dato =0;
    
    Scanner sc= new Scanner(System.in);
    
        try{
         dato=sc.nextFloat();
      
        } catch (Exception e){
         
          System.out.println("Error al leer el dato, ingresa nuevamente un dato que sea flotante valido: ");
          return leerFlotante();
         }
      
    return (int) dato;
    
    }
    
    public String leerCadena(){
    String cadena= "";
    
    Scanner sc= new Scanner(System.in);
   
         try{
          cadena= sc.nextLine();
         
          
         } catch (Exception e){
         
          System.out.println("Error al leer el dato, ingresa nuevamente una cadena de texto: ");
           return leerCadena();
          }
          
       
    return cadena;
    }
}
