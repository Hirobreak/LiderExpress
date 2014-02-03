
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
import static liderexpress.QueryLog.log;

public class Cliente extends Validaciones implements QueryLog {
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
    
    static public void crearCliente(final MainMenu m){ 
        final JFrame jCrearCliente = new JFrame("Creacion de Cliente");
        jCrearCliente.setSize(600, 300);
        jCrearCliente.setVisible(true);
        Panel panelPrin=new Panel(new GridLayout(6, 1));
        Panel panelnombre=new Panel(new GridLayout(1, 2));
        Panel panelruc=new Panel(new GridLayout(1, 2));
        Panel panelcedula=new Panel(new GridLayout(1, 2));
        Panel panelcom=new Panel(new GridLayout(1, 2));
        Panel paneltelf=new Panel(new GridLayout(1, 3));
        Panel panelOrden= new Panel(new GridLayout(1, 3));
        
        Panel panelboton=new Panel(new GridLayout(1, 2));
        Label labelnom=new Label("Nombre:", Label.CENTER);
        Label labelruc=new Label("RUC:", Label.CENTER);
        Label labelced=new Label("Cedula:", Label.CENTER);
        Label labelcom=new Label("Compañia:", Label.CENTER);
        Label labeltelf=new Label("Telefono:", Label.CENTER);
        Label labelOrden =new Label(" Ordenes", Label.CENTER);
        Button guardar=new Button("Guardar");
        Button cancelar=new Button("Cancelar");
        Button Ordenes=new Button("Ordenes");
        final TextField txtNombre=new TextField("", 20);
        final TextField txtRuc=new TextField("", 20);
        final TextField txtCedula=new TextField("", 20);
        final TextField txtCompa=new TextField("", 20);
        final TextField txtTelf1=new TextField("", 20);
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
        panelOrden.add(labelOrden);
        panelOrden.add(Ordenes);
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
                if(esCEDULA(txtCedula.getText())==false)
                        JOptionPane.showMessageDialog(null,"Error, la cedula es un numero de 10 digitos.Intente de nuevo");
                if(esRUC(txtRuc.getText())==false)
                        JOptionPane.showMessageDialog(null,"Error, el RUC es un numero de 13 digitos.Intente de nuevo");
                if(esTELF(txtTelf1.getText())==false)
                        JOptionPane.showMessageDialog(null,"Error,el telefono maximo puede tener 20 digitos.Intente de nuevo");
                if(largoString(txtNombre.getText(),40)==false)
                        JOptionPane.showMessageDialog(null,"Error,el nombre puede tener maximo hasta 40 caracterres.Intente de nuevo");
                if(largoString(txtCompa.getText(),30)==false)
                        JOptionPane.showMessageDialog(null,"Error,la compañia puede tener maximo hasta 30 caracterres.Intente de nuevo");
                if(esCEDULA(txtCedula.getText())&& esRUC(txtRuc.getText())&&esTELF(txtTelf1.getText())&&largoString(txtNombre.getText(),40)&&largoString(txtCompa.getText(),30)  ) {                 
                    NuevoCliente(txtNombre.getText(), txtCedula.getText(), txtRuc.getText(), txtCompa.getText(), txtTelf1.getText());
                    jCrearCliente.setVisible(false);
                }
                m.paintClientes();
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
            CallableStatement pro = (CallableStatement) con.prepareCall("{call crearCliente(?,?,?,?,?,?)}");
            pro.setInt(1, newID());
            pro.setString(2, nombre);
            pro.setString(3, cedula);
            pro.setString(4, emp);                        
            pro.setString(5, ruc);
            pro.setString(6, telf);
            pro.executeQuery();
        }catch(SQLException e){
            JOptionPane.showMessageDialog(null, "Error dato");
        }
    }
    public static ResultSet ConsultaCliente(String nombre, String cedula, String telf){
        ResultSet rs = null;
        try {
            Connection con=connect.Conexion_SQL();
            CallableStatement pro = (CallableStatement) con.prepareCall("{call searchClient(?,?,?)}");
            pro.setString(1, nombre);
            pro.setString(2, cedula);
            pro.setString(3, telf);
            pro.execute();
            rs=pro.getResultSet();
        }catch(SQLException e){
            JOptionPane.showMessageDialog(null, "Error dato");
        }
        return rs;

    }
    
    public static ResultSet todosClientes(){
        ResultSet rs = null;
        try {
            Connection con=connect.Conexion_SQL();
            CallableStatement pro = (CallableStatement) con.prepareCall("{call allClient()}");
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
            CallableStatement pro = (CallableStatement) con.prepareCall("{call maxClient()}");
            pro.execute();
            rs=pro.getResultSet();
            try{
                while(rs.next())
                    id = rs.getInt("maxID");
            }catch(SQLException e){}
            if(id==0)
                id++;
            return id;
        }
        catch(SQLException e){
            JOptionPane.showMessageDialog(null, "Error dato ID");
        }
        return id;
    }
    
    static void modificarCliente(final int id_cliente, final MainMenu m){ 

            String queryNom = "";
            String queryCed = "";
            String queryRuc = "";
            String queryCom = "";
            String queryTelf = "";
            ResultSet rs = null;
            try {
                Connection con=connect.Conexion_SQL();
                CallableStatement pro = (CallableStatement) con.prepareCall("{call takeClientData(?)}");
                pro.setInt(1, id_cliente);
                pro.execute();
                ResultSet res = pro.getResultSet();
                res.next();
                queryNom = res.getString("nombre");
                queryCed = res.getString("cedula");
                queryRuc = res.getString("ruc");
                queryCom = res.getString("compania");
                queryTelf = res.getString("telf");
                
            }catch(SQLException ex){}
                        
                
        final JFrame jModCliente = new JFrame("Modificacion de Cliente");
        jModCliente.setSize(500, 300);
        jModCliente.setVisible(true);
        Panel panelPrin=new Panel(new GridLayout(6, 1));
        Panel panelnombre=new Panel(new GridLayout(1, 2));
        Panel panelruc=new Panel(new GridLayout(1, 2));
        Panel panelcedula=new Panel(new GridLayout(1, 2));
        Panel panelcom=new Panel(new GridLayout(1, 2));
        Panel paneltelf=new Panel(new GridLayout(1, 3));
        Panel panelboton=new Panel(new GridLayout(1, 2));
        Panel panelOrden= new Panel(new GridLayout(1, 2));
        Label labelnom=new Label("Nombre:", Label.CENTER);
        Label labelruc=new Label("RUC:", Label.CENTER);
        Label labelced=new Label("Cedula:", Label.CENTER);
        Label labelcom=new Label("Compañia:", Label.CENTER);
        Label labeltelf=new Label("Telefono:", Label.CENTER);
        Label labelOrden =new Label(" Ordenes", Label.CENTER);
        Button guardar=new Button("Guardar");
        Button cancelar=new Button("Cancelar");
        Button Ordenes=new Button("Ordenes");
        final TextField txtNombre=new TextField(queryNom, 20);
        final TextField txtRuc=new TextField(queryRuc, 20);
        final TextField txtCedula=new TextField(queryCed, 20);
        final TextField txtCompa=new TextField(queryCom, 20);
        final TextField txtTelf1=new TextField(queryTelf, 20);
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
        panelOrden.add(labelOrden);
        panelOrden.add(Ordenes);
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
                if(esCEDULA(txtCedula.getText())==false)
                        JOptionPane.showMessageDialog(null,"Error, la cedula es un numero de 10 digitos.Intente de nuevo");
                if(esRUC(txtRuc.getText())==false)
                        JOptionPane.showMessageDialog(null,"Error, el RUC es un numero de 13 digitos.Intente de nuevo");
                if(esTELF(txtTelf1.getText())==false)
                        JOptionPane.showMessageDialog(null,"Error,el telefono maximo puede tener 20 digitos.Intente de nuevo");
                if(largoString(txtNombre.getText(),40)==false)
                        JOptionPane.showMessageDialog(null,"Error,el nombre puede tener maximo hasta 40 caracterres.Intente de nuevo");
                if(largoString(txtCompa.getText(),30)==false)
                        JOptionPane.showMessageDialog(null,"Error,la compañia puede tener maximo hasta 30 caracterres.Intente de nuevo");
                
                if(esCEDULA(txtCedula.getText())&& esRUC(txtRuc.getText())&&esTELF(txtTelf1.getText())&&largoString(txtNombre.getText(),40)&&largoString(txtCompa.getText(),30)  ) { 
                int confirm = JOptionPane.showConfirmDialog(null, "Esta seguro que desea modificar al Cliente ID: "+id_cliente+"?","ALERTA",JOptionPane.INFORMATION_MESSAGE);
                if(confirm==JOptionPane.OK_OPTION){
                try {
                    Connection con=connect.Conexion_SQL();
                    CallableStatement pro = (CallableStatement) con.prepareCall("{call updateClient(?,?,?,?,?,?)}");
                    pro.setInt(1, id_cliente);
                    pro.setString(2, txtNombre.getText());
                    pro.setString(3, txtCedula.getText());
                    pro.setString(4, txtCompa.getText());                        
                    pro.setString(5, txtRuc.getText());
                    pro.setString(6, txtTelf1.getText());
                    pro.executeQuery();
                }catch(SQLException ex){}}
                jModCliente.setVisible(false);
                m.paintClientes();
            }
        }
        });
        cancelar.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){
                jModCliente.setVisible(false);
            }
        });
    }
    
    public static void eliminarCliente(int id_cliente){
        boolean tieneOrden = false;
        int confirm = JOptionPane.showConfirmDialog(null, "Esta seguro que desea eliminar Cliente ID: "+id_cliente+"?","ALERTA",JOptionPane.INFORMATION_MESSAGE);
        if(confirm==JOptionPane.OK_OPTION){
            try{
                Connection con=connect.Conexion_SQL();
                Statement sentencia=con.createStatement();
                ResultSet ordenes = Orden.todasOrdenes();
                try{
                    while(ordenes.next()){
                        if(ordenes.getInt(2)==id_cliente){
                            tieneOrden=true;
                            JOptionPane.showMessageDialog(null,"Error al intentar eliminar Cliente ID: "+id_cliente+"\nCliente ID: "+id_cliente+" tiene asignada una Orden ID: "+ordenes.getInt(1));
                        }
                    }
                }catch(SQLException e){}   
            }catch(SQLException e){}
            if(tieneOrden==false){
                try {
                    Connection con=connect.Conexion_SQL();
                    CallableStatement pro = (CallableStatement) con.prepareCall("{call deleteClient(?)}");
                    pro.setInt(1, id_cliente);
                    pro.execute();
                }catch(SQLException e){}
            }
        }
    }
    
    public static ResultSet ordenesDeCliente(int id_cliente){
        ResultSet rs = null;
        try{
            Connection con=connect.Conexion_SQL();
            Statement sentencia=con.createStatement();
            String query = "SELECT orden.* FROM orden WHERE orden.id_cliente="+id_cliente+";"; 
            rs = sentencia.executeQuery(query);
        }catch(SQLException e){}
        return rs;
    }
    
    public Object[] arreglo(){
        
        Object[] arreglo={id, nombre, cedula, compa, ruc, telf1, telf2};
        return arreglo;
    }   

}
