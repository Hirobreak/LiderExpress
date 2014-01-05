/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package liderexpress;

/**
 *
 * @author Your Name <Edison Sanchez>
 */
public class Validaciones {
    
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
                 if(str.length()<=largo)
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
                 if(str.length()>=20)
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
              
    
}
