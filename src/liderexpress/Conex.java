/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package liderexpress;

import com.mysql.jdbc.CallableStatement;
import java.sql.*;

import java.sql.SQLException;

import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import javax.swing.JOptionPane;


public class Conex {
    
     public Connection conexion = null;
     String connectionUrl;

    public String getConnectionUrl() {
        return connectionUrl;
    }
     
        
    public void crearConexion() {        
        
         connectionUrl= "jdbc:mysql://localhost:3306/liderexpress";
        
        try {
            Class.forName("com.mysql.jdbc.Driver");
            conexion = DriverManager.getConnection(connectionUrl,"root","Dosmillones354879");
            
            
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Fallo la conexión con la base de datos:"+ex.getMessage(), "Conexion Fallida", JOptionPane.ERROR_MESSAGE);
            
        } catch (ClassNotFoundException ex) {
             JOptionPane.showMessageDialog(null, "Fallo la conexión con la base de datos:"+ex.getMessage(), "Conexion Fallida", JOptionPane.ERROR_MESSAGE);
            
        }

    }
    
    public void cerrarConexion(){
        try {
        conexion.close();
        
        }catch (SQLException ex) {
        ex.printStackTrace();
        System.out.println("Problema al cerrar la conexion");
      }
    }   

    public boolean ejecutarprocedure(String sql) {
        try {

            Statement sentencia = conexion.prepareCall(sql);
            sentencia.execute(sql);

        } catch (SQLException ex) {
            ex.printStackTrace();
            return false;
        }

        return true;
    }

    public ResultSet ejecutarProcedureSelect(String sql) {
        ResultSet resultado;
        try {
            Statement sentencia = conexion.createStatement();
            resultado = sentencia.executeQuery(sql);
        } catch (SQLException ex) {
            ex.printStackTrace();
            return null;
        }

        return resultado;
    }
    
      public Connection getConnection(){
      return conexion;
   }
  
}
