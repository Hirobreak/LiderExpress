
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
import static liderexpress.Trabajador.newID;

public class Proveedor {
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
    
    
    static public void modProv(final ArrayList<Proveedor> proveedores, final int pos){
        Proveedor  prov = proveedores.get(pos);
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
        final TextField txtCom=new TextField(prov.compañia, 20);
        final TextField txtRUP=new TextField(String.valueOf(prov.rup), 20);
        final TextField txtDueño=new TextField(prov.dueño, 20);
        final TextField txtPais=new TextField(prov.pais, 20);
        final TextField txtTelf=new TextField(String.valueOf(prov.telf), 20);
        final TextField txtCiudad=new TextField(prov.ciudad, 20);
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
                Proveedor mod = new Proveedor(pos+1,txtCom.getText(),Integer.parseInt(txtRUP.getText()),txtPais.getText(),txtCiudad.getText(),txtDueño.getText(),Integer.parseInt(txtTelf.getText()));
                proveedores.remove(pos);
                proveedores.add(pos, mod);
                jModProv.setVisible(false);
            }
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