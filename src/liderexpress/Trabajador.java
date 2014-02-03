
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
import static liderexpress.Cliente.connect;
import static liderexpress.QueryLog.log;
import static liderexpress.Validaciones.*;

public class Trabajador implements QueryLog {
    int id;
    String nombre;
    String cargo;
    int cedula;
    int telefono;
    float salario;
    String mail;
    
    Trabajador(int ids, String name, String puesto, int ced, int telf, int sal, String correo){
        id=ids;
        nombre=name;
        cargo=puesto;
        cedula=ced;
        telefono=telf;
        salario=sal;
        mail=correo;
    }
    
    static public void crearTrab(final MainMenu m){
        final JFrame jCrearT = new JFrame("Creacion de Trabajador");
        jCrearT.setSize(500, 300);
        jCrearT.setVisible(true);
        Panel panelPrin=new Panel(new GridLayout(7, 1));
        Panel panelnombre=new Panel(new GridLayout(1, 2));
        Panel panelced=new Panel(new GridLayout(1, 2));
        Panel panelcargo=new Panel(new GridLayout(1, 2));
        Panel panelsal=new Panel(new GridLayout(1, 2));
        Panel panelcorreo=new Panel(new GridLayout(1, 2));
        Panel paneltelf=new Panel(new GridLayout(1, 3));
        Panel panelboton=new Panel(new GridLayout(1, 2));
        Label labelnom=new Label("Nombre:", Label.CENTER);
        Label labelcargo=new Label("Cargo:", Label.CENTER);
        Label labelced=new Label("Cedula:", Label.CENTER);
        Label labelsal=new Label("Salario: $", Label.CENTER);
        Label labelcorreo=new Label("Correo:", Label.CENTER);
        Label labeltelf=new Label("Telefonos:", Label.CENTER);
        Button guardar=new Button("Guardar");
        Button cancelar=new Button("Cancelar");
        final TextField txtNombre=new TextField("", 20);
        final TextField txtCargo=new TextField("", 20);
        final TextField txtCedula=new TextField("", 20);
        final TextField txtSalario=new TextField("", 20);
        final TextField txtTelf=new TextField("", 20);
        final TextField txtCorreo=new TextField("", 20);
        panelnombre.add(labelnom);
        panelnombre.add(txtNombre);
        panelced.add(labelced);
        panelced.add(txtCedula);
        panelcargo.add(labelcargo);
        panelcargo.add(txtCargo);
        panelsal.add(labelsal);
        panelsal.add(txtSalario);
        paneltelf.add(labeltelf);
        paneltelf.add(txtTelf);
        panelcorreo.add(labelcorreo);
        panelcorreo.add(txtCorreo);
        panelboton.add(guardar);
        panelboton.add(cancelar);
        panelPrin.add(panelnombre);
        panelPrin.add(panelced);
        panelPrin.add(panelcargo);
        panelPrin.add(panelsal);
        panelPrin.add(paneltelf);
        panelPrin.add(panelcorreo);
        panelPrin.add(panelboton);
        jCrearT.add(panelPrin);
        guardar.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){
                
                 if(largoString(txtNombre.getText(),40)==false)
                        JOptionPane.showMessageDialog(null,"Error, el nombre debe tener hasta 40 caracteres. Intente de nuevo");
                 if(esCEDULA(txtCedula.getText())==false)
                        JOptionPane.showMessageDialog(null,"Error, la cedula debe tener hasta 10 digitos. Intente de nuevo");
                 if(largoString(txtCargo.getText(),20)==false)
                        JOptionPane.showMessageDialog(null,"Error, el cargo debe tener hasta 20 caracteres. Intente de nuevo");
                 if(largoInt(txtTelf.getText(),30)==false)
                        JOptionPane.showMessageDialog(null,"Error, el telefono debe tener hasta 30 digitos. Intente de nuevo");
                 if(largoInt(txtSalario.getText(),10)==false)
                        JOptionPane.showMessageDialog(null,"Error, el salario debe ser un numero de hasta 10 digitos. Intente de nuevo");
                 if(esMail(txtCorreo.getText())==false)
                        JOptionPane.showMessageDialog(null,"Error,direccion de correo electronico no valida.Intente de nuevo");
                if(largoString(txtNombre.getText(),40)&&esCEDULA(txtCedula.getText())&&largoString(txtCargo.getText(),20)&&largoInt(txtTelf.getText(),30)&&largoInt(txtSalario.getText(),10)&&esMail(txtCorreo.getText())){
                nuevoTrab(txtNombre.getText(),txtCedula.getText(),txtCargo.getText(),txtTelf.getText(),txtSalario.getText(),txtCorreo.getText());
                jCrearT.setVisible(false);}
                m.paintTrabs();
            }
        });
        cancelar.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){
                jCrearT.setVisible(false);
            }
        });    
    }
    
    public static void nuevoTrab(String nombre, String cedula, String puesto, String telf, String sueldo, String mail){
        try {
            Connection con=connect.Conexion_SQL();
            CallableStatement pro = (CallableStatement) con.prepareCall("{call crearTrabajador(?,?,?,?,?,?,?)}");
            pro.setInt(1, newID());
            pro.setString(2, nombre);
            pro.setString(3, cedula);
            pro.setString(4, puesto);                        
            pro.setString(5, telf);
            pro.setString(6, sueldo);
            pro.setString(7, mail);
            pro.executeQuery();
        }catch(SQLException e){
            JOptionPane.showMessageDialog(null, "Error dato");
        }
        //return fa;
    } 
    
    public static ResultSet todosTrab(){
        ResultSet rs = null;
        try {
            Connection con=connect.Conexion_SQL();
            CallableStatement pro = (CallableStatement) con.prepareCall("{call todosTrab()}");
            pro.execute();
            rs=pro.getResultSet();
            
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
            CallableStatement pro = (CallableStatement) con.prepareCall("{call lastIDTrab()}");
            pro.execute();
            rs=pro.getResultSet();
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
        
        
    static public void modTrab(final int id_trab, final MainMenu m){
            String queryNom = "";
            String queryCed = "";
            String queryPue = "";
            String queryTelf = "";
            String querySue = "";
            String queryMail = "";
            ResultSet rs1 = null;
            try {
                Connection con=connect.Conexion_SQL();
                CallableStatement pro = (CallableStatement) con.prepareCall("{call takeTrabData(?)}");
                pro.setInt(1, id_trab);
                pro.execute();
                rs1 = pro.getResultSet();
                rs1.next();
                queryNom = rs1.getString("nombre");
                queryCed = rs1.getString("cedula");
                queryPue = rs1.getString("puesto");
                queryTelf = rs1.getString("telf");
                querySue = rs1.getString("sueldo");
                queryMail = rs1.getString("mail");
            }catch(SQLException ex){}
        final JFrame jModT = new JFrame("Modificacion de Trabajador");
        jModT.setSize(500, 300);
        jModT.setVisible(true);
        Panel panelPrin=new Panel(new GridLayout(7, 1));
        Panel panelnombre=new Panel(new GridLayout(1, 2));
        Panel panelced=new Panel(new GridLayout(1, 2));
        Panel panelcargo=new Panel(new GridLayout(1, 2));
        Panel panelsal=new Panel(new GridLayout(1, 2));
        Panel panelcorreo=new Panel(new GridLayout(1, 2));
        Panel paneltelf=new Panel(new GridLayout(1, 3));
        Panel panelboton=new Panel(new GridLayout(1, 2));
        Label labelnom=new Label("Nombre:", Label.CENTER);
        Label labelcargo=new Label("Cargo:", Label.CENTER);
        Label labelced=new Label("Cedula:", Label.CENTER);
        Label labelsal=new Label("Salario: $", Label.CENTER);
        Label labelcorreo=new Label("Correo:", Label.CENTER);
        Label labeltelf=new Label("Telefonos:", Label.CENTER);
        Button guardar=new Button("Guardar");
        Button cancelar=new Button("Cancelar");
        final TextField txtNombre=new TextField(queryNom, 20);
        final TextField txtCargo=new TextField(queryPue, 20);
        final TextField txtCedula=new TextField(queryCed, 20);
        final TextField txtSalario=new TextField(querySue, 20);
        final TextField txtTelf=new TextField(queryTelf, 20);
        final TextField txtCorreo=new TextField(queryMail, 20);
        panelnombre.add(labelnom);
        panelnombre.add(txtNombre);
        panelced.add(labelced);
        panelced.add(txtCedula);
        panelcargo.add(labelcargo);
        panelcargo.add(txtCargo);
        panelsal.add(labelsal);
        panelsal.add(txtSalario);
        paneltelf.add(labeltelf);
        paneltelf.add(txtTelf);
        panelcorreo.add(labelcorreo);
        panelcorreo.add(txtCorreo);
        panelboton.add(guardar);
        panelboton.add(cancelar);
        panelPrin.add(panelnombre);
        panelPrin.add(panelced);
        panelPrin.add(panelcargo);
        panelPrin.add(panelsal);
        panelPrin.add(paneltelf);
        panelPrin.add(panelcorreo);
        panelPrin.add(panelboton);
        jModT.add(panelPrin);
        guardar.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){
                int confirm = JOptionPane.showConfirmDialog(null, "Esta seguro que desea modificar Trabajador ID: "+id_trab+"?","ALERTA",JOptionPane.INFORMATION_MESSAGE);
                if(confirm==JOptionPane.OK_OPTION){
                try {
                if(largoString(txtNombre.getText(),40)==false)
                        JOptionPane.showMessageDialog(null,"Error, el nombre debe tener hasta 40 caracteres. Intente de nuevo");
                 if(esCEDULA(txtCedula.getText())==false)
                        JOptionPane.showMessageDialog(null,"Error, la cedula debe tener hasta 10 digitos. Intente de nuevo");
                 if(largoString(txtCargo.getText(),20)==false)
                        JOptionPane.showMessageDialog(null,"Error, el cargo debe tener hasta 20 caracteres. Intente de nuevo");
                 if(largoInt(txtTelf.getText(),30)==false)
                        JOptionPane.showMessageDialog(null,"Error, el telefono debe tener hasta 30 digitos. Intente de nuevo");
                 if(largoInt(txtSalario.getText(),10)==false)
                        JOptionPane.showMessageDialog(null,"Error, el salario debe ser un numero de hasta 10 digitos. Intente de nuevo");
                 if(esMail(txtCorreo.getText())==false)
                        JOptionPane.showMessageDialog(null,"Error,direccion de correo electronico no valida.Intente de nuevo");
                if(largoString(txtNombre.getText(),40)&&esCEDULA(txtCedula.getText())&&largoString(txtCargo.getText(),20)&&largoInt(txtTelf.getText(),30)&&largoInt(txtSalario.getText(),10)&&esMail(txtCorreo.getText())){
               
                    Connection con=connect.Conexion_SQL();
                    CallableStatement pro = (CallableStatement) con.prepareCall("{call modTrab(?,?,?,?,?,?,?)}");
                    pro.setInt(1, id_trab);
                    pro.setString(2, txtNombre.getText());
                    pro.setString(3, txtCedula.getText());
                    pro.setString(4, txtCargo.getText());                        
                    pro.setString(5, txtTelf.getText());
                    pro.setString(6, txtSalario.getText());
                    pro.setString(7, txtCorreo.getText());
                    pro.executeQuery();
                    jModT.setVisible(false);
                }
                }catch(SQLException ex){}
                m.paintTrabs();
            }}
        });
        cancelar.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){
                jModT.setVisible(false);
            }
        });
    }
    
    public static void eliminarTrab(int id_trab){
        boolean tieneImport = false;
        int confirm = JOptionPane.showConfirmDialog(null, "Esta seguro que desea eliminar Trabajador ID: "+id_trab+"?","ALERTA",JOptionPane.INFORMATION_MESSAGE);
        if(confirm==JOptionPane.OK_OPTION){
            try{
                Connection con=connect.Conexion_SQL();
                Statement sentencia=con.createStatement();
                ResultSet imports = Importacion.todasImport();
                try{
                    while(imports.next()){
                        if(imports.getInt(2)==id_trab){
                            tieneImport=true;
                            JOptionPane.showMessageDialog(null,"Error al intentar eliminar Trabajador ID: "+id_trab+"\nTrabajador ID: "+id_trab+" tiene asignada una Importacion ID: "+imports.getInt(1));
                        }
                    }
                }catch(SQLException e){}   
            }catch(SQLException e){}
            if(tieneImport==false){
                try {
                    Connection con=connect.Conexion_SQL();
                    CallableStatement pro = (CallableStatement) con.prepareCall("{call elimTrab(?)}");
                    pro.setInt(1, id_trab);
                    pro.execute();
                }catch(SQLException e){}
            }
        }
    }
    
    public Object[] arreglo(){
        Object[] arreglo={id, nombre, cargo, cedula, telefono, salario, mail};
        return arreglo;
    }
    public int intParse(String s){
        return Integer.parseInt(s);
    }
    
     public static ResultSet buscarTraba(String txtMail,String txtNom, String txtCedula, String txtCargo, String txtSalario, String txtTelf){
        ResultSet rs= null;
        try {
            Connection con=connect.Conexion_SQL();
            CallableStatement pro = (CallableStatement) con.prepareCall("{call buscarTrab(?,?,?,?,?,?)}");
            pro.setString(1, "%"+txtMail+"%");
            pro.setString(2, "%"+txtNom+"%");
            pro.setString(3, "%"+txtCedula+"%");
            pro.setString(4, "%"+txtCargo+"%");                        
            pro.setString(5, "%"+txtSalario+"%");
            pro.setString(6, "%"+txtTelf+"%");
            pro.execute();
            rs=pro.getResultSet();
          }catch(SQLException ex){
            JOptionPane.showMessageDialog(null, "Error busqueda");
          }
        return rs;
    }
}