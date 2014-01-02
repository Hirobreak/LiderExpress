
package liderexpress;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import javax.swing.*;
import javax.swing.border.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.*;
import java.sql.ResultSet;
import com.mysql.jdbc.CallableStatement;

public class Cliente{
    int id;
    String nombre;
    String ruc;
    String cedula;
    String compa;
    int telf1;
    int telf2;
    static C_ConexionSQL connect=new C_ConexionSQL();
    static Conex c;
    Connection conexion;


    Cliente(int id, String nom, String ruc, String ced, String comp, int t1, int t2){
        this.id = id;
        this.nombre = nom;
        this.ruc = ruc;
        this.cedula= ced;
        this.compa = comp;
        this.telf1=t1;
        this.telf2=t2;
    }
    
    static public void crearCliente(final ArrayList<Cliente> clientes){ 
        final JFrame jCrearCliente = new JFrame("Creacion de Cliente");
        jCrearCliente.setSize(500, 300);
        jCrearCliente.setVisible(true);
        Panel panelPrin=new Panel(new GridLayout(6, 1));
        Panel panelnombre=new Panel(new GridLayout(1, 2));
        Panel panelruc=new Panel(new GridLayout(1, 2));
        Panel panelcedula=new Panel(new GridLayout(1, 2));
        Panel panelcom=new Panel(new GridLayout(1, 2));
        Panel paneltelf=new Panel(new GridLayout(1, 3));
        Panel panelboton=new Panel(new GridLayout(1, 2));
        Label labelnom=new Label("Nombre:", Label.CENTER);
        Label labelruc=new Label("RUC:", Label.CENTER);
        Label labelced=new Label("Cedula:", Label.CENTER);
        Label labelcom=new Label("Compañia:", Label.CENTER);
        Label labeltelf=new Label("Telefonos:", Label.CENTER);
        Button guardar=new Button("Guardar");
        Button cancelar=new Button("Cancelar");
        final TextField txtNombre=new TextField("Nombre", 20);
        final TextField txtRuc=new TextField("RUC", 20);
        final TextField txtCedula=new TextField("Cedula", 20);
        final TextField txtCompa=new TextField("Compañia", 20);
        final TextField txtTelf1=new TextField("00000000", 20);
        final TextField txtTelf2=new TextField("00000000", 20);
        panelnombre.add(labelnom);
        panelnombre.add(txtNombre);
        panelcedula.add(labelced);
        panelcedula.add(txtCedula);
        panelcom.add(labelcom);
        panelcom.add(txtCompa);
        panelruc.add(labelruc);
        panelruc.add(txtRuc);
        paneltelf.add(labeltelf);
        paneltelf.add(txtTelf1);
        paneltelf.add(txtTelf2);
        panelboton.add(guardar);
        panelboton.add(cancelar);
        panelPrin.add(panelnombre);
        panelPrin.add(panelcedula);
        panelPrin.add(panelcom);
        panelPrin.add(panelruc);
        panelPrin.add(paneltelf);
        panelPrin.add(panelboton);
        jCrearCliente.add(panelPrin);
        guardar.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){
                NuevoCliente(txtNombre.getText(), txtCedula.getText(), txtRuc.getText(), txtCompa.getText(), txtTelf1.getText());
                jCrearCliente.setVisible(false);
            }
        });
        cancelar.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){
                jCrearCliente.setVisible(false);
            }
        });
    }
    
    public static void NuevoCliente(String nombre, String cedula, String ruc, String emp, String telf){
        try {
            Connection con=connect.Conexion_SQL();
            Statement sentencia=con.createStatement();
            //String query="INSERT INTO cliente VALUES(1, '"+ nombre+"','"+cedula+"','"+ruc+"','"+emp+"','"+telf+"')";
            String query="INSERT INTO cliente VALUES("+ String.valueOf((int)(Math.random()*100)) +",'"+nombre+"', '"+cedula+"', '"+ruc+"', '"+emp+"', '"+telf+"');";
            sentencia.executeUpdate(query);
        }catch(SQLException e){
            JOptionPane.showMessageDialog(null, "Error dato");
        }
        //return fa;
    }
    public static ResultSet ConsultaCliente(String nombre, String cedula){
        ResultSet rs = null;
        try {
            String query;
            Connection con=connect.Conexion_SQL();
            Statement sentencia=con.createStatement();
            if (cedula.equals("")){
                query="SELECT cliente.* FROM cliente WHERE cliente.nombre LIKE '"+nombre+"';";
            }else if (nombre.equals("")){
                query="SELECT cliente.* FROM cliente WHERE cliente.cedula LIKE '"+cedula+"';";
            }else{
                query="SELECT cliente.* FROM cliente WHERE cliente.nombre LIKE '"+nombre+"' AND cliente.cedula LIKE '" +cedula+"';";
            }

            rs=sentencia.executeQuery(query);
            
        }catch(SQLException e){
            JOptionPane.showMessageDialog(null, "Error dato");
        }
        return rs;

    }
    
    public static ResultSet todosClientes(){
        ResultSet rs = null;
        try {
            String query;
            Connection con=connect.Conexion_SQL();
            Statement sentencia=con.createStatement();
            query="SELECT cliente.* FROM cliente;";
            rs=sentencia.executeQuery(query);
            
        }catch(SQLException e){
            JOptionPane.showMessageDialog(null, "Error dato");
        }
        return rs;
    }
    
    
    static void modificarCliente(final ArrayList<Cliente> clientes, final int pos){ 
        Cliente c = clientes.get(pos);
        final JFrame jModCliente = new JFrame("Modificando Cliente: "+c.id);
        jModCliente.setSize(500, 300);
        jModCliente.setVisible(true);
        Panel panelPrin=new Panel(new GridLayout(6, 1));
        Panel panelnombre=new Panel(new GridLayout(1, 2));
        Panel panelruc=new Panel(new GridLayout(1, 2));
        Panel panelcedula=new Panel(new GridLayout(1, 2));
        Panel panelcom=new Panel(new GridLayout(1, 2));
        Panel paneltelf=new Panel(new GridLayout(1, 3));
        Panel panelboton=new Panel(new GridLayout(1, 2));
        Label labelnom=new Label("Nombre:", Label.CENTER);
        Label labelruc=new Label("RUC:", Label.CENTER);
        Label labelced=new Label("Cedula:", Label.CENTER);
        Label labelcom=new Label("Compañia:", Label.CENTER);
        Label labeltelf=new Label("Telefonos:", Label.CENTER);
        Button guardar=new Button("Guardar");
        Button cancelar=new Button("Cancelar");
        final TextField txtNombre=new TextField(c.nombre, 20);
        final TextField txtRuc=new TextField(String.valueOf(c.ruc), 20);
        final TextField txtCedula=new TextField(String.valueOf(c.cedula), 20);
        final TextField txtCompa=new TextField(c.compa, 20);
        final TextField txtTelf1=new TextField(String.valueOf(c.telf1), 20);
        final TextField txtTelf2=new TextField(String.valueOf(c.telf2), 20);
        panelnombre.add(labelnom);
        panelnombre.add(txtNombre);
        panelcedula.add(labelced);
        panelcedula.add(txtCedula);
        panelcom.add(labelcom);
        panelcom.add(txtCompa);
        panelruc.add(labelruc);
        panelruc.add(txtRuc);
        paneltelf.add(labeltelf);
        paneltelf.add(txtTelf1);
        paneltelf.add(txtTelf2);
        panelboton.add(guardar);
        panelboton.add(cancelar);
        panelPrin.add(panelnombre);
        panelPrin.add(panelcedula);
        panelPrin.add(panelcom);
        panelPrin.add(panelruc);
        panelPrin.add(paneltelf);
        panelPrin.add(panelboton);
        jModCliente.add(panelPrin);
        guardar.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){
                Cliente mod = new Cliente(pos+1,txtNombre.getText(),txtRuc.getText(),txtCedula.getText(),txtCompa.getText(),Integer.parseInt(txtTelf1.getText()),Integer.parseInt(txtTelf2.getText()));
                clientes.remove(pos);
                clientes.add(pos, mod);
                jModCliente.setVisible(false);
            }
        });
        cancelar.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){
                jModCliente.setVisible(false);
            }
        });
    }
    
    static void eliminarCliente(final ArrayList<Cliente> clientes, final int pos){
        Cliente c = clientes.get(pos);
        final JFrame jElimCliente = new JFrame("Eliminacion de Cliente");
        jElimCliente.setSize(500, 300);
        jElimCliente.setVisible(true);
        Panel panelPrin=new Panel(new GridLayout(6, 1));
        Panel panelnombre=new Panel(new GridLayout(1, 2));
        Panel panelruc=new Panel(new GridLayout(1, 2));
        Panel panelcedula=new Panel(new GridLayout(1, 2));
        Panel panelcom=new Panel(new GridLayout(1, 2));
        Panel paneltelf=new Panel(new GridLayout(1, 3));
        Panel panelboton=new Panel(new GridLayout(1, 2));
        Label labelnom=new Label("Nombre:", Label.CENTER);
        Label labelruc=new Label("RUC:", Label.CENTER);
        Label labelced=new Label("Cedula:", Label.CENTER);
        Label labelcom=new Label("Compañia:", Label.CENTER);
        Label labeltelf=new Label("Telefonos:", Label.CENTER);
        Button guardar=new Button("Eliminar");
        Button cancelar=new Button("Cancelar");
        Label txtNombre=new Label(c.nombre, Label.CENTER);
        Label txtRuc=new Label(String.valueOf(c.ruc), Label.CENTER);
        Label txtCedula=new Label(String.valueOf(c.cedula), Label.CENTER);
        Label txtCompa=new Label(c.compa, Label.CENTER);
        Label txtTelf1=new Label(String.valueOf(c.telf1), Label.CENTER);
        Label txtTelf2=new Label(String.valueOf(c.telf2), Label.CENTER);
        panelnombre.add(labelnom);
        panelnombre.add(txtNombre);
        panelcedula.add(labelced);
        panelcedula.add(txtCedula);
        panelcom.add(labelcom);
        panelcom.add(txtCompa);
        panelruc.add(labelruc);
        panelruc.add(txtRuc);
        paneltelf.add(labeltelf);
        paneltelf.add(txtTelf1);
        paneltelf.add(txtTelf2);
        panelboton.add(guardar);
        panelboton.add(cancelar);
        panelPrin.add(panelnombre);
        panelPrin.add(panelcedula);
        panelPrin.add(panelcom);
        panelPrin.add(panelruc);
        panelPrin.add(paneltelf);
        panelPrin.add(panelboton);
        jElimCliente.add(panelPrin);
        guardar.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){
                clientes.remove(pos);
                jElimCliente.setVisible(false);
            }
        });
        cancelar.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){
                jElimCliente.setVisible(false);
            }
        });
    }
    
    public Object[] arreglo(){
        Object[] arreglo={id, nombre, cedula, compa, ruc, telf1, telf2};
        return arreglo;
    }   

}
