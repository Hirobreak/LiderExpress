
package liderexpress;

import java.sql.*;
import java.sql.SQLException;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import javax.swing.JOptionPane;

public class C_ConexionSQL {
    
    protected Connection Conexion_SQL(){
        Connection conexion=null;
        try{
            Class.forName("com.mysql.jdbc.Driver").newInstance();
            conexion=DriverManager.getConnection("jdbc:mysql://localhost:3306/liderexpress", "root", "admin");
        }catch(Exception e){
            JOptionPane.showMessageDialog(null, "Error de Conexion");
        }
        return conexion;      
        
    }
    
}
