/*******************************************************************************
 * Nombre de la clase: Fecha                                                   
 *
 * Informacion de la version: Obtiene la fecha en tiempo real
 *
 * Fecha: 09 de Marzo 2020
 *
 * @autor Victor Manuel Arredondo Reyes
 ******************************************************************************/
package model;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Fecha {
   

    public String fecha(){ 
        Date objDate = new Date(); 
        String strDateFormat = "hh:mm dd-MMM-yyyy"; //Formato en que se genera la fecha
        SimpleDateFormat objSDF = new SimpleDateFormat(strDateFormat); 
    
        String fechaV=objSDF.format(objDate);
        
        return fechaV; //retorna la fecha
    }
}
    

