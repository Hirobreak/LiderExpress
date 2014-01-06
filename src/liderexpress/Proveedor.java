
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
import static liderexpress.Trabajador.newID;
import static liderexpress.Validaciones.*;

public class Proveedor implements QueryLog {
    int id;
    String compañia;
    int rup;
    String pais;
    String ciudad;
    String dueño;
    int telf;
    
    Proveedor(int ids, String com, int rucp, String country, String city, String contacto, int telefo){
        id=ids;
        compañia=com;
        rup=rucp;
        pais=country;
        ciudad=city;
        dueño=contacto;
        telf=telefo;
    }
    
    static public void crearProv(final MainMenu m){
        final JFrame jCrearProv = new JFrame("Creacion de Importador");
        jCrearProv.setSize(500, 300);
        jCrearProv.setVisible(true);
        Panel panelPrin=new Panel(new GridLayout(6, 1));
        Panel panelCom=new Panel(new GridLayout(1, 2));
        Panel panelRup=new Panel(new GridLayout(1, 2));
        Panel panelDueño=new Panel(new GridLayout(1, 2));
        Panel panelLugar=new Panel(new GridLayout(1, 3));
        Panel panelTelf=new Panel(new GridLayout(1, 2));
        Panel panelboton=new Panel(new GridLayout(1, 2));
        Label labelCom=new Label("Compañia:", Label.CENTER);
        Label labelRup=new Label("RUP:", Label.CENTER);
        Label labelDueño=new Label("Dueño:", Label.CENTER);
        Label labelLugar=new Label("Lugar", Label.CENTER);
        Label labeltelf=new Label("Telefonos:", Label.CENTER);
        Button guardar=new Button("Guardar");
        Button cancelar=new Button("Cancelar");
        final TextField txtCom=new TextField("Nombre", 20);
        final TextField txtRUP=new TextField("RUP", 20);
        final TextField txtDueño=new TextField("Contacto", 20);
        final TextField txtPais=new TextField("Pais", 20);
        final TextField txtTelf=new TextField("00000000", 20);
        final TextField txtCiudad=new TextField("Ciudad", 20);
        panelCom.add(labelCom);
        panelCom.add(txtCom);
        panelRup.add(labelRup);
        panelRup.add(txtRUP);
        panelDueño.add(labelDueño);
        panelDueño.add(txtDueño);
        panelLugar.add(labelLugar);
        panelLugar.add(txtPais);
        panelLugar.add(txtCiudad);
        panelTelf.add(labeltelf);
        panelTelf.add(txtTelf);
        panelboton.add(guardar);
        panelboton.add(cancelar);
        panelPrin.add(panelCom);
        panelPrin.add(panelRup);
        panelPrin.add(panelDueño);
        panelPrin.add(panelLugar);
        panelPrin.add(panelTelf);
        panelPrin.add(panelboton);
        jCrearProv.add(panelPrin);
        guardar.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){
                if(largoString(txtCom.getText(),40)==false)
                        JOptionPane.showMessageDialog(null,"Error,la compañia es de hasta 40 caracteres.Intente de nuevo");
                if(largoInt(txtRUP.getText(),20)==false)
                        JOptionPane.showMessageDialog(null,"Error,el RUP es un numero de hasta 20 digitos.Intente de nuevo");
                if(largoString(txtPais.getText(),20)==false)
                        JOptionPane.showMessageDialog(null,"Error,el pais debe tener hasta 20 caracteres.Intente de nuevo");
                if(largoString(txtCiudad.getText(),20)==false)
                        JOptionPane.showMessageDialog(null,"Error,la ciudad debe tener hasta 30 caracteres.Intente de nuevo");
                if(largoString(txtDueño.getText(),20)==false)
                        JOptionPane.showMessageDialog(null,"Error,el dueño debe tener hasta 20 caracteres.Intente de nuevo");
                if(largoInt(txtTelf.getText(),20)==false)
                        JOptionPane.showMessageDialog(null,"Error,el telefono debe tener hasta 20 caraceres.Intente de nuevo");                
                if(largoString(txtCom.getText(),40)&&largoInt(txtRUP.getText(),20)&&largoString(txtPais.getText(),20)&&largoString(txtCiudad.getText(),20)&&largoString(txtDueño.getText(),20)&&largoInt(txtTelf.getText(),20))
                nuevoProv(txtCom.getText(),txtRUP.getText(),txtPais.getText(),txtCiudad.getText(),txtDueño.getText(),txtTelf.getText());
                jCrearProv.setVisible(false);
                m.paintProvs();
            }
        });
        cancelar.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){
                jCrearProv.setVisible(false);
            }
        });
        
    }
    
        public static void nuevoProv(String comp, String rup, String pais, String ciudad, String dueño, String telf){
        try {
            Connection con=connect.Conexion_SQL();
            Statement sentencia=con.createStatement();
            String query="INSERT INTO proveedor VALUES("+newID()+",'"+comp+"', '"+rup+"', '"+pais+"', '"+ciudad+"', '"+dueño+"', '"+telf+"');";
            sentencia.executeUpdate(query);
            log.add(query);
        }catch(SQLException e){
            JOptionPane.showMessageDialog(null, "Error dato");
        }
        //return fa;
    } 
    
    public static ResultSet todosProv(){
        ResultSet rs = null;
        try {
            String query;
            Connection con=connect.Conexion_SQL();
            Statement sentencia=con.createStatement();
            query="SELECT proveedor.* FROM proveedor;";
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
            String query="SELECT max(proveedor.id_proveedor)+1 as maxID FROM proveedor;";
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
    
    
    static public void modProv(final int id_prov, final MainMenu m){
            String queryCom = "";
            String queryRup = "";
            String queryPais = "";
            String queryCiu = "";
            String queryDue = "";
            String queryTelf = "";
            ResultSet rs1 = null;
            try {
                Connection con=connect.Conexion_SQL();
                Statement sentencia=con.createStatement();
                String query="SELECT (proveedor.compania) as comp FROM proveedor WHERE proveedor.id_proveedor="+id_prov+";";
                String query1="SELECT (proveedor.rup) as rup FROM proveedor WHERE proveedor.id_proveedor="+id_prov+";";
                String query2="SELECT (proveedor.pais) as pais FROM proveedor WHERE proveedor.id_proveedor="+id_prov+";";
                String query3="SELECT (proveedor.ciudad) as ciudad FROM proveedor WHERE proveedor.id_proveedor="+id_prov+";";
                String query4="SELECT (proveedor.dueño) as dueño FROM proveedor WHERE proveedor.id_proveedor="+id_prov+";";
                String query5="SELECT (proveedor.telf) as telf FROM proveedor WHERE proveedor.id_proveedor="+id_prov+";";
                rs1 = sentencia.executeQuery(query);
                try{
                    while(rs1.next())
                    queryCom = rs1.getString("comp");
                }catch (SQLException ex){}
                rs1 = sentencia.executeQuery(query1);
                try{
                     while(rs1.next())
                     queryRup = rs1.getString("rup");
                }catch (SQLException ex){}
                rs1 = sentencia.executeQuery(query2);
                try{
                     while(rs1.next())
                     queryPais = rs1.getString("pais");
                }catch (SQLException ex){}
                rs1 = sentencia.executeQuery(query3);
                try{
                     while(rs1.next())
                     queryCiu = rs1.getString("ciudad");
                }catch (SQLException ex){}
                rs1 = sentencia.executeQuery(query4);
                try{
                     while(rs1.next())
                     queryDue = rs1.getString("dueño");
                }catch (SQLException ex){}
                 rs1 = sentencia.executeQuery(query5);
                try{
                     while(rs1.next())
                     queryTelf = rs1.getString("telf");
                }catch (SQLException ex){}
            }catch(SQLException ex){}
        final JFrame jModProv = new JFrame("Modificacion de Importador");
        jModProv.setSize(500, 300);
        jModProv.setVisible(true);
        Panel panelPrin=new Panel(new GridLayout(6, 1));
        Panel panelCom=new Panel(new GridLayout(1, 2));
        Panel panelRup=new Panel(new GridLayout(1, 2));
        Panel panelDueño=new Panel(new GridLayout(1, 2));
        Panel panelLugar=new Panel(new GridLayout(1, 3));
        Panel panelTelf=new Panel(new GridLayout(1, 2));
        Panel panelboton=new Panel(new GridLayout(1, 2));
        Label labelCom=new Label("Compañia:", Label.CENTER);
        Label labelRup=new Label("RUP:", Label.CENTER);
        Label labelDueño=new Label("Dueño:", Label.CENTER);
        Label labelLugar=new Label("Lugar", Label.CENTER);
        Label labeltelf=new Label("Telefonos:", Label.CENTER);
        Button guardar=new Button("Guardar");
        Button cancelar=new Button("Cancelar");
        final TextField txtCom=new TextField(queryCom, 20);
        final TextField txtRUP=new TextField(queryRup, 20);
        final TextField txtDueño=new TextField(queryDue, 20);
        final TextField txtPais=new TextField(queryPais, 20);
        final TextField txtTelf=new TextField(queryTelf, 20);
        final TextField txtCiudad=new TextField(queryCiu, 20);
        panelCom.add(labelCom);
        panelCom.add(txtCom);
        panelRup.add(labelRup);
        panelRup.add(txtRUP);
        panelDueño.add(labelDueño);
        panelDueño.add(txtDueño);
        panelLugar.add(labelLugar);
        panelLugar.add(txtPais);
        panelLugar.add(txtCiudad);
        panelTelf.add(labeltelf);
        panelTelf.add(txtTelf);
        panelboton.add(guardar);
        panelboton.add(cancelar);
        panelPrin.add(panelCom);
        panelPrin.add(panelRup);
        panelPrin.add(panelDueño);
        panelPrin.add(panelLugar);
        panelPrin.add(panelTelf);
        panelPrin.add(panelboton);
        jModProv.add(panelPrin);
        guardar.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){
                int confirm = JOptionPane.showConfirmDialog(null, "Esta seguro que desea modificar Proveedor ID: "+id_prov+"?","ALERTA",JOptionPane.INFORMATION_MESSAGE);
                if(confirm==JOptionPane.OK_OPTION){
                try {
                    Connection con=connect.Conexion_SQL();
                    Statement sentencia=con.createStatement();
                    String query="UPDATE proveedor SET proveedor.compania='"+txtCom.getText()+"', proveedor.rup='"+txtRUP.getText()+"', proveedor.pais='"+txtPais.getText()+"', proveedor.ciudad='"+txtCiudad.getText()+"', proveedor.dueño='"+txtDueño.getText()+"', proveedor.telf='"+txtTelf.getText()+"' WHERE proveedor.id_proveedor="+id_prov+";";
                    sentencia.executeUpdate(query);
                    log.add(query);
                }catch(SQLException ex){}
                jModProv.setVisible(false);
                m.paintProvs();
            }}
        });
        cancelar.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){
                jModProv.setVisible(false);
            }
        });
        
    }
    
    public static void eliminarProv(int id_prov){
        boolean tieneImport = false;
        int confirm = JOptionPane.showConfirmDialog(null, "Esta seguro que desea eliminar Proveedor ID: "+id_prov+"?","ALERTA",JOptionPane.INFORMATION_MESSAGE);
        if(confirm==JOptionPane.OK_OPTION){
            try{
                Connection con=connect.Conexion_SQL();
                Statement sentencia=con.createStatement();
                ResultSet imports = Importacion.todasImport();
                try{
                    while(imports.next()){
                        if(imports.getInt(3)==id_prov){
                            tieneImport=true;
                            JOptionPane.showMessageDialog(null,"Error al intentar eliminar Proveedor ID: "+id_prov+"\nProveedor ID: "+id_prov+" tiene asignada una Importacion ID: "+imports.getInt(1));
                        }
                    }
                }catch(SQLException e){}   
            }catch(SQLException e){}
            if(tieneImport==false){
                try {
                    Connection con=connect.Conexion_SQL();
                    Statement sentencia=con.createStatement();
                    String query="DELETE FROM proveedor WHERE proveedor.id_proveedor="+id_prov+";";
                    sentencia.executeUpdate(query);
                    log.add(query);
                }catch(SQLException e){}
            }
        }
    }
    
    static public void eliminarProv(final ArrayList<Proveedor> proveedores, final int pos){
        Proveedor  prov = proveedores.get(pos);
        final JFrame jElimProv = new JFrame("Eliminacion de Importador");
        jElimProv.setSize(500, 300);
        jElimProv.setVisible(true);
        Panel panelPrin=new Panel(new GridLayout(6, 1));
        Panel panelCom=new Panel(new GridLayout(1, 2));
        Panel panelRup=new Panel(new GridLayout(1, 2));
        Panel panelDueño=new Panel(new GridLayout(1, 2));
        Panel panelLugar=new Panel(new GridLayout(1, 3));
        Panel panelTelf=new Panel(new GridLayout(1, 2));
        Panel panelboton=new Panel(new GridLayout(1, 2));
        Label labelCom=new Label("Compañia:", Label.CENTER);
        Label labelRup=new Label("RUP:", Label.CENTER);
        Label labelDueño=new Label("Dueño:", Label.CENTER);
        Label labelLugar=new Label("Lugar", Label.CENTER);
        Label labeltelf=new Label("Telefonos:", Label.CENTER);
        Button guardar=new Button("Guardar");
        Button cancelar=new Button("Cancelar");
        Label txtCom=new Label(prov.compañia,Label.CENTER);
        Label txtRUP=new Label(String.valueOf(prov.rup), Label.CENTER);
        Label txtDueño=new Label(prov.dueño, Label.CENTER);
        Label txtPais=new Label(prov.pais, Label.CENTER);
        Label txtTelf=new Label(String.valueOf(prov.telf), Label.CENTER);
        Label txtCiudad=new Label(prov.ciudad, Label.CENTER);
        panelCom.add(labelCom);
        panelCom.add(txtCom);
        panelRup.add(labelRup);
        panelRup.add(txtRUP);
        panelDueño.add(labelDueño);
        panelDueño.add(txtDueño);
        panelLugar.add(labelLugar);
        panelLugar.add(txtPais);
        panelLugar.add(txtCiudad);
        panelTelf.add(labeltelf);
        panelTelf.add(txtTelf);
        panelboton.add(guardar);
        panelboton.add(cancelar);
        panelPrin.add(panelCom);
        panelPrin.add(panelRup);
        panelPrin.add(panelDueño);
        panelPrin.add(panelLugar);
        panelPrin.add(panelTelf);
        panelPrin.add(panelboton);
        jElimProv.add(panelPrin);
        guardar.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){
                proveedores.remove(pos);
                jElimProv.setVisible(false);
            }
        });
        cancelar.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){
                jElimProv.setVisible(false);
            }
        });
        
    }    
        public Object[] arreglo(){
        Object[] arreglo={id, compañia, rup, pais, ciudad, dueño, telf};
        return arreglo;
    }  
}