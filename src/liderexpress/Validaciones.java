/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package liderexpress;

import javax.swing.JOptionPane;

/**
 *
 * @author Your Name <Edison Sanchez>
 */
public class Validaciones {
    public static boolean esMail(String email){
   
      String EMAIL_REGEX = "^[\\w-_\\.+]*[\\w-_\\.]\\      @([\\w]+\\.)+[\\w]+[\\w]$";
      
      Boolean b = email.matches(EMAIL_REGEX);
      
      return b;
   
    }
            
    public static boolean largoString(String str, int largo)  
                {               
                 
                 if(str.length()<=largo)               
                 return true;  
                 else
                     return false;
                }
    public static boolean largoInt(String str, int largo)  
                {  
                 try  
                {  
                 double d = Double.parseDouble(str);  
                 if(str.length()>largo)
                     return false;
                 }  
                  catch(NumberFormatException nfe)  
                 {  
                return false;  
                }  
                 return true;  
                }
    
    
    public static boolean esTELF(String str)  
                {  
                 try  
                {  
                 double d = Double.parseDouble(str);  
                 if(str.length()>20)
                     return false;
                 }  
                  catch(NumberFormatException nfe)  
                 {  
                return false;  
                }  
                 return true;  
                }
    
              public static boolean esCEDULA(String str)  
                {  
                 try  
                {  
                 double d = Double.parseDouble(str);  
                 if(str.length()!=10)
                     return false;
                 }  
                  catch(NumberFormatException nfe)  
                 {  
                return false;  
                }  
                 return true;  
                }
              public static boolean esRUC(String str)  
                {  
                 try  
                {  
                 double d = Double.parseDouble(str);  
                 if(str.length()!=13)
                     return false;
                 }  
                  catch(NumberFormatException nfe)  
                 {  
                return false;  
                }  
                 return true;  
                }
     static public boolean validarProv(String txtCom, String txtRUP, String txtPais, String txtCiudad, String txtDueño, String txtTelf){
        if(largoString(txtCom,40)==false){
                JOptionPane.showMessageDialog(null,"Error,la compañia es de hasta 40 caracteres.Intente de nuevo");
                return false;}
        if(largoInt(txtRUP,20)==false){
                JOptionPane.showMessageDialog(null,"Error,el RUP es un numero de hasta 20 digitos.Intente de nuevo");   
                return false;}
        if(largoString(txtPais,20)==false){
                JOptionPane.showMessageDialog(null,"Error,el pais debe tener hasta 20 caracteres.Intente de nuevo");
                return false;}
        if(largoString(txtCiudad,30)==false){
                JOptionPane.showMessageDialog(null,"Error,la ciudad debe tener hasta 30 caracteres.Intente de nuevo");
                return false;}
        if(largoString(txtDueño,20)==false){
                JOptionPane.showMessageDialog(null,"Error,el dueño debe tener hasta 20 caracteres.Intente de nuevo");
                return false;}
        if(largoInt(txtTelf,20)==false){
                JOptionPane.showMessageDialog(null,"Error,el telefono debe tener hasta 20 caraceres.Intente de nuevo");
                return false;}
        return true;
     }         
              
    
}
