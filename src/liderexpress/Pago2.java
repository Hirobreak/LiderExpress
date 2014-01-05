/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package liderexpress;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javax.swing.JOptionPane;
import static liderexpress.Cliente.connect;

/**
 *
 * @author Kevin
 */
public class Pago2 {
    public static ResultSet todosPagos(){
        ResultSet rs = null;
        try {
            String query;
            Connection con=connect.Conexion_SQL();
            Statement sentencia=con.createStatement();
            query="SELECT pago2.* FROM pago2;";
            rs=sentencia.executeQuery(query);
            
        }catch(SQLException e){
            JOptionPane.showMessageDialog(null, "Error dato");
        }
        return rs;
    }
    
    public static void eliminarPago(int id_pago){
        int confirm = JOptionPane.showConfirmDialog(null, "Esta seguro que desea eliminar Pago ID: "+id_pago+"?","ALERTA",JOptionPane.INFORMATION_MESSAGE);
        if(confirm==JOptionPane.OK_OPTION){
            try {
                Connection con=connect.Conexion_SQL();
                Statement sentencia=con.createStatement();
                String query="DELETE FROM pago2 WHERE pago2.id_pago="+id_pago+";";
                sentencia.executeUpdate(query);
            }catch(SQLException e){}
        }
    }
}
