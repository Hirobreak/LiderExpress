
package liderexpress;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import javax.swing.*;
import javax.swing.border.*;
import static liderexpress.Cliente.connect;
import static liderexpress.Importacion.newID;
import static liderexpress.QueryLog.log;
import static liderexpress.Validaciones.largoInt;

public class Caja extends Validaciones implements QueryLog{
    int id;
    String dimension;
    int peso;
    String num;
    String estado;
    
    Caja(int ids, String dim, int kilos, String estad, String nume){
        id=ids;
        num=nume;
        dimension=dim;
        peso=kilos;
        estado=estad;    
    }
    
    static public int crearCaja(final MainMenu m){
        int i = 1;
        final JFrame jCrearCaja = new JFrame("Creacion de Caja");
        jCrearCaja.setSize(500, 300);
        jCrearCaja.setVisible(true);
        Panel panelPrin=new Panel(new GridLayout(4, 1));
        Panel panelNum=new Panel(new GridLayout(1, 2));
        Panel panelDim=new Panel(new GridLayout(1, 2));
        Panel panelPeso=new Panel(new GridLayout(1, 2));
        Panel panelEstado=new Panel(new GridLayout(1, 2));
        Panel panelboton=new Panel(new GridLayout(1, 2));
        Label labelNum=new Label("Numero", Label.CENTER);
        Label labelDim=new Label("Dimensiones:", Label.CENTER);
        Label labelPeso=new Label("Peso:", Label.CENTER);
        Label labelEstado=new Label("Estado:", Label.CENTER);
        Button guardar=new Button("Guardar");
        Button cancelar=new Button("Cancelar");
        final TextField txtNum=new TextField("#", 20);
        final TextField txtDim=new TextField("Nombre", 20);
        final TextField txtPeso=new TextField("Cargo", 20);
        final JComboBox bEstado=new JComboBox();
        bEstado.addItem("Enviado");
        bEstado.addItem("Recibido");
        bEstado.addItem("Desmontado");
        panelNum.add(labelNum);
        panelNum.add(txtNum);
        panelDim.add(labelDim);
        panelDim.add(txtDim);
        panelPeso.add(labelPeso);
        panelPeso.add(txtPeso);
        panelEstado.add(labelEstado);
        panelEstado.add(bEstado);
        panelboton.add(guardar);
        panelboton.add(cancelar);
        panelPrin.add(panelNum);
        panelPrin.add(panelDim);
        panelPrin.add(panelPeso);
        panelPrin.add(panelEstado);
        panelPrin.add(panelboton);
        jCrearCaja.add(panelPrin);
        guardar.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){
                
                
                if(largoInt(txtNum.getText(),10)==false)
                        JOptionPane.showMessageDialog(null,"Error, la caja tiene numero de hasta 10 digitos.Intente de nuevo");
                 if(largoInt(txtPeso.getText(),10)==false)
                        JOptionPane.showMessageDialog(null,"Error, el peso es un numero de hasta 10 digitos.Intente de nuevo");
                  if(largoString(bEstado.getSelectedItem().toString(),20)==false)
                        JOptionPane.showMessageDialog(null,"Error, el estado puede tener hasta 20 caracteres.Intente de nuevo");
                   if(largoInt(txtDim.getText(),10)==false)
                        JOptionPane.showMessageDialog(null,"Error, las dimensiones son de hasta 10 digitos.Intente de nuevo");
                   
                if(largoInt(txtNum.getText(),10)&&largoInt(txtPeso.getText(),10)&&largoString(bEstado.getSelectedItem().toString(),20)&&largoInt(txtDim.getText(),10))                
                nuevaCaja(txtNum.getText(),txtPeso.getText(),bEstado.getSelectedItem().toString(),txtDim.getText());
                
                jCrearCaja.setVisible(false);
                m.paintCajas();
            }
        });
        cancelar.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){
                jCrearCaja.setVisible(false);
            }
        });
        return i;
    }
    
    public static void nuevaCaja(String num, String peso, String estado, String dim){
        try {
            Connection con=connect.Conexion_SQL();
            Statement sentencia=con.createStatement();
            String query="INSERT INTO caja VALUES("+newID()+",'"+num+"', '"+peso+"', '"+estado+"', '"+dim+"');";
            sentencia.executeUpdate(query);
            log.add(query);
        }catch(SQLException e){
            JOptionPane.showMessageDialog(null, "Error dato");
        }
    } 
    
    public static void eliminarCaja(int id_caja){
        boolean tieneEmpaq = false;
        int confirm = JOptionPane.showConfirmDialog(null, "Esta seguro que desea eliminar Caja ID: "+id_caja+"?","ALERTA",JOptionPane.INFORMATION_MESSAGE);
        if(confirm==JOptionPane.OK_OPTION){
            try{
                Connection con=connect.Conexion_SQL();
                Statement sentencia=con.createStatement();
                ResultSet empaqs = Empaquetado.todosEmpaqs();
                try{
                    while(empaqs.next()){
                        if(empaqs.getInt(4)==id_caja){
                            tieneEmpaq=true;
                            JOptionPane.showMessageDialog(null,"Error al intentar eliminar Caja ID: "+id_caja+"\nCaja ID: "+id_caja+" tiene asignada un Empaquetado ID: "+empaqs.getInt(1));
                        }
                    }
                }catch(SQLException e){}   
            }catch(SQLException e){}
            if(tieneEmpaq==false){
                try {
                    Connection con=connect.Conexion_SQL();
                    Statement sentencia=con.createStatement();
                    String query="DELETE FROM caja WHERE caja.id_caja="+id_caja+";";
                    sentencia.executeUpdate(query);
                    log.add(query);
                }catch(SQLException e){}
            }
        }
    }
    
    public static ResultSet todasCajas(){
        ResultSet rs = null;
        try {
            String query;
            Connection con=connect.Conexion_SQL();
            Statement sentencia=con.createStatement();
            query="SELECT caja.* FROM caja;";
            rs=sentencia.executeQuery(query);
            
        }catch(SQLException e){
            JOptionPane.showMessageDialog(null, "Error dato");
        }
        return rs;
    }
    
    public static int newID(){
        int id = 1;
        ResultSet rs = null;
        try {
            Connection con=connect.Conexion_SQL();
            Statement sentencia=con.createStatement();
            String query="SELECT max(caja.id_caja)+1 as maxID FROM caja;";
            rs = sentencia.executeQuery(query);
            try{
                while(rs.next())
                    id = rs.getInt("maxID");
            }catch(SQLException e){  
            }
            if(id==0)
                id++;
            return id;
        }
        catch(SQLException e){
            JOptionPane.showMessageDialog(null, "Error dato ID");
        }
        return id;
    }
    
    public Object[] arreglo(){
        Object[] arreglo={id, dimension, peso, estado, num};
        return arreglo;
    }   
}