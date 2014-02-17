package liderexpress;

import com.mysql.jdbc.CallableStatement;
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
import static liderexpress.QueryLog.log;

public class Contenedor extends Validaciones implements QueryLog{
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
    
    static public void crearCont(final MainMenu m){
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
                 if(largoInt(txtDim.getText(),10)==false)
                        JOptionPane.showMessageDialog(null,"Error, las dimensiones son de hasta 10 digitos.Intente de nuevo");
                  if(largoInt(txtPeso.getText(),10)==false)
                        JOptionPane.showMessageDialog(null,"Error, el peso es hasta 10 digitos.Intente de nuevo");
                   if(largoString(bEstado.getSelectedItem().toString(),20)==false)
                        JOptionPane.showMessageDialog(null,"Error, las dimensiones son de hasta 10 digitos.Intente de nuevo");
                   
                   if(largoInt(txtDim.getText(),10)&&largoInt(txtPeso.getText(),10)&&largoString(bEstado.getSelectedItem().toString(),20))
                nuevoCont(txtDim.getText(),txtPeso.getText(),bEstado.getSelectedItem().toString(),id_imp);
                   
                jCrearCont.setVisible(false);
                m.paintConts();
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
           // Statement sentencia=con.createStatement();
            CallableStatement pro = (CallableStatement) con.prepareCall("{call crearCont(?,?,?,?,?)}");
            pro.setInt(1, newID());
            pro.setString(2, dim);
            pro.setString(3, peso);
            pro.setString(4, estado);                        
            pro.setInt(5, Integer.parseInt(id_imp));
            System.out.println(Integer.parseInt(id_imp));
          // pro.setString(6, telf);
            pro.executeQuery();
            //String query="{call crearCont("+newID()+",'"+dim+"', '"+peso+"', '"+estado+"',"+id_imp+")}";
           // sentencia.executeUpdate(query);
           // log.add(query);
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
    
    public static void eliminarCont(int id_cont){
        boolean tieneEmpaq = false;
        int confirm = JOptionPane.showConfirmDialog(null, "Esta seguro que desea eliminar Contenedor ID: "+id_cont+"?","ALERTA",JOptionPane.INFORMATION_MESSAGE);
        if(confirm==JOptionPane.OK_OPTION){
            try{
                Connection con=connect.Conexion_SQL();
                Statement sentencia=con.createStatement();
                ResultSet empaqs = Empaquetado.todosEmpaqs();
                try{
                    while(empaqs.next()){
                        if(empaqs.getInt(2)==id_cont){
                            tieneEmpaq=true;
                            JOptionPane.showMessageDialog(null,"Error al intentar eliminar Contenedor ID: "+id_cont+"\nContenedor ID: "+id_cont+" tiene asignada un Empaquetado ID: "+empaqs.getInt(1));
                        }
                    }
                }catch(SQLException e){}   
            }catch(SQLException e){}
            if(tieneEmpaq==false){
                try {
                    Connection con=connect.Conexion_SQL();
                  CallableStatement pro = (CallableStatement) con.prepareCall("{call deleteCont(?)}");
            pro.setInt(1, id_cont);
            
          // pro.setString(6, telf);
            pro.executeQuery();
      //      String query="{call deleteCont("+newID()+")}";
           // sentencia.executeUpdate(query);
           // log.add(query);
                   // log.add(query);
                }catch(SQLException e){}
            }
        }
    }
    
    public Object[] arreglo(){
        Object[] arreglo={id, dimension, peso, estado, idImpo};
        return arreglo;
    }   
}