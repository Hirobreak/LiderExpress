
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
            Statement sentencia=con.createStatement();
            String query="INSERT INTO trabajador VALUES("+newID()+",'"+nombre+"', '"+cedula+"', '"+puesto+"', '"+telf+"', "+sueldo+", '"+mail+"');";
            sentencia.executeUpdate(query);
            log.add(query);
        }catch(SQLException e){
            JOptionPane.showMessageDialog(null, "Error dato");
        }
        //return fa;
    } 
    
    public static ResultSet todosTrab(){
        ResultSet rs = null;
        try {
            String query;
            Connection con=connect.Conexion_SQL();
            Statement sentencia=con.createStatement();
            query="SELECT trabajador.* FROM trabajador;";
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
            String query="SELECT max(trabajador.id_trabajador)+1 as maxID FROM trabajador;";
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
                Statement sentencia=con.createStatement();
                String query="SELECT (trabajador.nombre) as nombre FROM trabajador WHERE trabajador.id_trabajador="+id_trab+";";
                String query1="SELECT (trabajador.cedula) as cedula FROM trabajador WHERE trabajador.id_trabajador="+id_trab+";";
                String query2="SELECT (trabajador.puesto) as puesto FROM trabajador WHERE trabajador.id_trabajador="+id_trab+";";
                String query3="SELECT (trabajador.telf) as telf FROM trabajador WHERE trabajador.id_trabajador="+id_trab+";";
                String query4="SELECT (trabajador.sueldo) as sueldo FROM trabajador WHERE trabajador.id_trabajador="+id_trab+";";
                String query5="SELECT (trabajador.mail) as mail FROM trabajador WHERE trabajador.id_trabajador="+id_trab+";";
                rs1 = sentencia.executeQuery(query);
                try{
                    while(rs1.next())
                    queryNom = rs1.getString("nombre");
                }catch (SQLException ex){}
                rs1 = sentencia.executeQuery(query1);
                try{
                     while(rs1.next())
                     queryCed = rs1.getString("cedula");
                }catch (SQLException ex){}
                rs1 = sentencia.executeQuery(query2);
                try{
                     while(rs1.next())
                     queryPue = rs1.getString("puesto");
                }catch (SQLException ex){}
                rs1 = sentencia.executeQuery(query3);
                try{
                     while(rs1.next())
                     queryTelf = rs1.getString("telf");
                }catch (SQLException ex){}
                rs1 = sentencia.executeQuery(query4);
                try{
                     while(rs1.next())
                     querySue = rs1.getString("sueldo");
                }catch (SQLException ex){}
                 rs1 = sentencia.executeQuery(query5);
                try{
                     while(rs1.next())
                     queryMail = rs1.getString("mail");
                }catch (SQLException ex){}
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
                    Statement sentencia=con.createStatement();
                    String query="UPDATE trabajador SET trabajador.nombre='"+txtNombre.getText()+"', trabajador.cedula='"+txtCedula.getText()+"', trabajador.puesto='"+txtCargo.getText()+"', trabajador.telf='"+txtTelf.getText()+"', trabajador.sueldo="+txtSalario.getText()+", trabajador.mail='"+txtCorreo.getText()+"' WHERE trabajador.id_trabajador="+id_trab+";";
                    sentencia.executeUpdate(query);
                    jModT.setVisible(false);
                    log.add(query);
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
                    Statement sentencia=con.createStatement();
                    String query="DELETE FROM trabajador WHERE trabajador.id_trabajador="+id_trab+";";
                    sentencia.executeUpdate(query);
                    log.add(query);
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
    
    
}