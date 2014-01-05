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
import static liderexpress.QueryLog.log;

/**
 *
 * @author Kevin
 */
public class Factura2 implements QueryLog {
    
    public static ResultSet todasFacts(){
        ResultSet rs = null;
        try {
            String query;
            Connection con=connect.Conexion_SQL();
            Statement sentencia=con.createStatement();
            query="SELECT factura2.* FROM factura2;";
            rs=sentencia.executeQuery(query);
            
        }catch(SQLException e){
            JOptionPane.showMessageDialog(null, "Error dato");
        }
        return rs;
    }
    
    public static void eliminarFact(int id_fact){
        boolean tienePago = false;
        int confirm = JOptionPane.showConfirmDialog(null, "Esta seguro que desea eliminar Factura ID: "+id_fact+"?","ALERTA",JOptionPane.INFORMATION_MESSAGE);
        if(confirm==JOptionPane.OK_OPTION){
            try{
                Connection con=connect.Conexion_SQL();
                Statement sentencia=con.createStatement();
                ResultSet pagos = Pago2.todosPagos();
                try{
                    while(pagos.next()){
                        if(pagos.getInt(6)==id_fact){
                            tienePago=true;
                            JOptionPane.showMessageDialog(null,"Error al intentar eliminar Factura ID: "+id_fact+"\nFactura ID: "+id_fact+" tiene asignado un Pago ID: "+pagos.getInt(1));
                        }
                    }
                }catch(SQLException e){}   
            }catch(SQLException e){}
            if(tienePago==false){
                try {
                    Connection con=connect.Conexion_SQL();
                    Statement sentencia=con.createStatement();
                    String query="DELETE FROM factura2 WHERE factura2.id_factura="+id_fact+";";
                    sentencia.executeUpdate(query);
                    log.add(query);
                }catch(SQLException e){}
            }
        }
    }
}
