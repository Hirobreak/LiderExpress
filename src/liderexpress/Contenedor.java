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
import static liderexpress.Caja.newID;
import static liderexpress.Cliente.connect;

public class Contenedor {
    int id;
    String dimension;
    int peso;
    String estado;
    int idImpo;
    
    Contenedor(int ids, String dim, int kilos, String estad, int impo){
        id=ids;
        dimension=dim;
        peso=kilos;
        estado=estad;
        idImpo=impo;
    }
    
    static public void crearCont(final ArrayList<Contenedor>conts,final ArrayList<Importacion>imps){
        final JFrame jCrearCont = new JFrame("Creacion de Con");
        jCrearCont.setSize(500, 300);
        jCrearCont.setVisible(true);
        Panel panelPrin=new Panel(new GridLayout(5, 1));
        Panel panelDim=new Panel(new GridLayout(1, 2));
        Panel panelPeso=new Panel(new GridLayout(1, 2));
        Panel panelEstado=new Panel(new GridLayout(1, 2));
        Panel panelImpo=new Panel(new GridLayout(1, 2));
        Panel panelboton=new Panel(new GridLayout(1, 2));
        Label labelDim=new Label("Dimensiones:", Label.CENTER);
        Label labelPeso=new Label("Peso:", Label.CENTER);
        Label labelEstado=new Label("Estado:", Label.CENTER);
        Label labelImpo=new Label("Importación: ", Label.CENTER);
        Button guardar=new Button("Guardar");
        Button cancelar=new Button("Cancelar");
        final TextField txtDim=new TextField("", 20);
        final TextField txtPeso=new TextField("", 20);
        final JComboBox bEstado=new JComboBox();
        final JComboBox impos=new JComboBox();
        try{
          ResultSet rs = Importacion.todasImport();
          while(rs.next()){
              int id = rs.getInt(1);
              String supervisor = rs.getString(2);
              impos.addItem(id+" - "+supervisor);
          }  
        }catch(Exception ex){
                    
        }
        bEstado.addItem("Enviado");
        bEstado.addItem("Recibido");
        bEstado.addItem("Desmontado");
        panelDim.add(labelDim);
        panelDim.add(txtDim);
        panelPeso.add(labelPeso);
        panelPeso.add(txtPeso);
        panelEstado.add(labelEstado);
        panelEstado.add(bEstado);
        panelImpo.add(labelImpo);
        panelImpo.add(impos);
        panelboton.add(guardar);
        panelboton.add(cancelar);
        panelPrin.add(panelDim);
        panelPrin.add(panelPeso);
        panelPrin.add(panelEstado);
        panelPrin.add(panelImpo);
        panelPrin.add(panelboton);
        jCrearCont.add(panelPrin);
        guardar.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){
                String imp = impos.getSelectedItem().toString();
                String imp1[] = imp.split("\\ ");
                String id_imp = imp1[0];
                nuevoCont(txtDim.getText(),txtPeso.getText(),bEstado.getSelectedItem().toString(),id_imp);
                jCrearCont.setVisible(false);
            }
        });
        cancelar.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){
                jCrearCont.setVisible(false);
            }
        });
    }
    
    public static void nuevoCont(String dim, String peso, String estado,String id_imp){
        try {
            Connection con=connect.Conexion_SQL();
            Statement sentencia=con.createStatement();
            String query="INSERT INTO contenedor VALUES("+newID()+",'"+dim+"', '"+peso+"', '"+estado+"',"+id_imp+");";
            sentencia.executeUpdate(query);
        }catch(SQLException e){
            JOptionPane.showMessageDialog(null, "Error dato");
        }
        //return fa;
    } 
    
    public static ResultSet todosConts(){
        ResultSet rs = null;
        try {
            String query;
            Connection con=connect.Conexion_SQL();
            Statement sentencia=con.createStatement();
            query="SELECT contenedor.* FROM contenedor;";
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
            String query="SELECT max(contenedor.id_contenedor)+1 as maxID FROM contenedor;";
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
        Object[] arreglo={id, dimension, peso, estado, idImpo};
        return arreglo;
    }   
}