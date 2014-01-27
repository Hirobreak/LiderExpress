
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
import java.util.Date;
import javax.swing.*;
import javax.swing.border.*;
import static liderexpress.Cliente.connect;
import static liderexpress.Proveedor.newID;
import static liderexpress.QueryLog.log;

public class Importacion implements QueryLog{
    int id;
    int trabajador;
    int prov;
    int dia;
    int mes;
    int año;
    
    Importacion(int ids, int trab, int pro, int d, int m, int a){
        id=ids;
        trabajador=trab;
        prov=pro;
        dia=d;
        mes=m;
        año=a;
    }
    
    static public void crearImpo(final MainMenu m){
        final JFrame jCrearImpo = new JFrame("Creacion de Importación");
        jCrearImpo.setSize(500, 300);
        jCrearImpo.setVisible(true);
        Panel panelPrin=new Panel(new GridLayout(5, 1));
        Panel panelTrab=new Panel(new GridLayout(1, 2));
        Panel panelProv=new Panel(new GridLayout(1, 2));
        Panel panelFecha=new Panel(new GridLayout(1, 4));
        Panel panelCont=new Panel(new GridLayout(1, 2));
        Panel panelboton=new Panel(new GridLayout(1, 2));
        Label labelTrab=new Label("Supervisor:", Label.CENTER);
        Label labelProv=new Label("Proveedor", Label.CENTER);
        Label labelFecha=new Label("Fecha:", Label.CENTER);
        Button guardar=new Button("Guardar");
        Button cancelar=new Button("Cancelar");
        final JComboBox superv=new JComboBox();
                try{
          ResultSet rs = Trabajador.todosTrab();
          while(rs.next()){
              int id = rs.getInt(1);
              String nombre = rs.getString(2);
              superv.addItem(id+" - "+nombre);
          }  
        }catch(Exception ex){
                    
        }
        final JComboBox provee=new JComboBox();
                try{
          ResultSet rs = Proveedor.todosProv();
          while(rs.next()){
              int id = rs.getInt(1);
              String comp = rs.getString(2);
              provee.addItem(id+" - "+comp);
          }  
        }catch(Exception ex){
                    
        }
        final JComboBox cdia=new JComboBox();
        final JComboBox cmes=new JComboBox();
        final JComboBox caño=new JComboBox();
        for (int i=2000; i<2015; i++){
            caño.addItem(i);
        }
        for (int j=1; j<13; j++){
            cmes.addItem(j);
        }
        for (int i=1; i<32; i++){
            cdia.addItem(i);
        }
        panelTrab.add(labelTrab);
        panelTrab.add(superv);
        panelProv.add(labelProv);
        panelProv.add(provee);
        panelFecha.add(labelFecha);
        panelFecha.add(cdia);
        panelFecha.add(cmes);
        panelFecha.add(caño);
        panelboton.add(guardar);
        panelboton.add(cancelar);
        panelPrin.add(panelTrab);
        panelPrin.add(panelProv);
        panelPrin.add(panelFecha);
        panelPrin.add(panelboton);
        jCrearImpo.add(panelPrin);
        guardar.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){
                String sup = superv.getSelectedItem().toString();
                String sup1[] = sup.split("\\ ");
                String id_trab = sup1[0];
                String prov = provee.getSelectedItem().toString();
                String prov1[] = prov.split("\\ ");
                String id_proveedor = prov1[0];
                nuevaImp(id_trab,id_proveedor,caño.getSelectedItem().toString(),cmes.getSelectedItem().toString(),cdia.getSelectedItem().toString());
                jCrearImpo.setVisible(false);
                m.paintImports();
            }
        });
        cancelar.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){
                jCrearImpo.setVisible(false);
            }
        });
    }
    
    public static void nuevaImp(String id_trab, String id_prov, String año, String mes, String dia){
        try {
            Connection con=connect.Conexion_SQL();
            String fecha=""+año+"-"+mes+"-"+dia+"";
            CallableStatement pro = (CallableStatement) con.prepareCall("{call crearImp(?,?,?,?)}");
            pro.setInt(1, newID());
            pro.setString(2, id_trab);
            pro.setString(3, id_prov);
            pro.setString(4, fecha); 
            pro.execute();
        }catch(SQLException e){
            JOptionPane.showMessageDialog(null, "Error dato");
        }
        //return fa;
    } 
    
    public static ResultSet todasImport(){
        ResultSet rs = null;
        try {
            Connection con=connect.Conexion_SQL();
            CallableStatement pro = (CallableStatement) con.prepareCall("{call todasImport()}");
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
            CallableStatement pro = (CallableStatement) con.prepareCall("{call lastIDImport()}");
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
    
    static public void modImpo(final int id_import, final MainMenu m){
        final JFrame jModImpo = new JFrame("Modificacion de Importación");
        jModImpo.setSize(500, 300);
        jModImpo.setVisible(true);
        Panel panelPrin=new Panel(new GridLayout(5, 1));
        Panel panelTrab=new Panel(new GridLayout(1, 2));
        Panel panelProv=new Panel(new GridLayout(1, 2));
        Panel panelFecha=new Panel(new GridLayout(1, 4));
        Panel panelCont=new Panel(new GridLayout(1, 2));
        Panel panelboton=new Panel(new GridLayout(1, 2));
        Label labelTrab=new Label("Supervisor:", Label.CENTER);
        Label labelProv=new Label("Proveedor", Label.CENTER);
        Label labelFecha=new Label("Fecha:", Label.CENTER);
        Button guardar=new Button("Guardar");
        Button cancelar=new Button("Cancelar");
        final JComboBox superv=new JComboBox();
                try{
          ResultSet rs = Trabajador.todosTrab();
          while(rs.next()){
              int id = rs.getInt(1);
              String nombre = rs.getString(2);
              superv.addItem(id+" - "+nombre);
          }  
        }catch(Exception ex){
                    
        }
        final JComboBox provee=new JComboBox();
                try{
          ResultSet rs = Proveedor.todosProv();
          while(rs.next()){
              int id = rs.getInt(1);
              String comp = rs.getString(2);
              provee.addItem(id+" - "+comp);
          }  
        }catch(Exception ex){
                    
        }
        final JComboBox cdia=new JComboBox();
        final JComboBox cmes=new JComboBox();
        final JComboBox caño=new JComboBox();
        for (int i=2000; i<2015; i++){
            caño.addItem(i);
        }
        for (int j=1; j<13; j++){
            cmes.addItem(j);
        }
        for (int i=1; i<32; i++){
            cdia.addItem(i);
        }
        panelTrab.add(labelTrab);
        panelTrab.add(superv);
        panelProv.add(labelProv);
        panelProv.add(provee);
        panelFecha.add(labelFecha);
        panelFecha.add(cdia);
        panelFecha.add(cmes);
        panelFecha.add(caño);
        panelboton.add(guardar);
        panelboton.add(cancelar);
        panelPrin.add(panelTrab);
        panelPrin.add(panelProv);
        panelPrin.add(panelFecha);
        panelPrin.add(panelboton);
        jModImpo.add(panelPrin);
        guardar.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){
                int confirm = JOptionPane.showConfirmDialog(null, "Esta seguro que desea modificar la Importacion ID: "+id_import+"?","ALERTA",JOptionPane.INFORMATION_MESSAGE);
                if(confirm==JOptionPane.OK_OPTION){
                String sup = superv.getSelectedItem().toString();
                String sup1[] = sup.split("\\ ");
                String id_trab = sup1[0];
                String prov = provee.getSelectedItem().toString();
                String prov1[] = prov.split("\\ ");
                String id_proveedor = prov1[0];
                try {
                    Connection con=connect.Conexion_SQL();
                    String fecha=""+caño.getSelectedItem().toString()+"-"+cmes.getSelectedItem().toString()+"-"+cdia.getSelectedItem().toString()+"";
                    CallableStatement pro = (CallableStatement) con.prepareCall("{call modImport(?,?,?,?)}");
                    pro.setInt(1, id_import);
                    pro.setString(2, id_trab);
                    pro.setString(3, id_proveedor);                   
                    pro.setString(4, fecha);
                    pro.executeQuery();
                }catch(SQLException ex){}
                jModImpo.setVisible(false);
                m.paintImports();
            }}
        });
        cancelar.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){
                jModImpo.setVisible(false);
            }
        });
    }
    
    public static void eliminarImport(int id_import){
        boolean tieneEmpaq = false;
        boolean tieneFact = false;
        int confirm = JOptionPane.showConfirmDialog(null, "Esta seguro que desea eliminar Importacion ID: "+id_import+"?","ALERTA",JOptionPane.INFORMATION_MESSAGE);
        if(confirm==JOptionPane.OK_OPTION){
            try{
                Connection con=connect.Conexion_SQL();
                Statement sentencia=con.createStatement();
                ResultSet conts = Contenedor.todosConts();
                ResultSet facts = Factura.todasFacts();
                try{
                    while(conts.next()){
                        if(conts.getInt(5)==id_import){
                            tieneEmpaq=true;
                            JOptionPane.showMessageDialog(null,"Error al intentar eliminar Importacion ID: "+id_import+"\nImportacion ID: "+id_import+" tiene asignado un Contenedor ID: "+conts.getInt(1));
                        }
                    }
                }catch(SQLException e){}
                try{
                    while(facts.next()){
                        if(facts.getInt(7)==id_import){
                            tieneFact=true;
                            JOptionPane.showMessageDialog(null,"Error al intentar eliminar Importacion ID: "+id_import+"\nImportacion ID: "+id_import+" tiene asignada una Factura ID: "+facts.getInt(1));
                        }
                    }
                }catch(SQLException e){}  
            }catch(SQLException e){}
            if(tieneEmpaq==false && tieneFact==false){
                try {
                    Connection con=connect.Conexion_SQL();
                    CallableStatement pro = (CallableStatement) con.prepareCall("{call elimImport(?)}");
                    pro.setInt(1, id_import);
                    pro.execute();
                }catch(SQLException e){}
            }
        }
    }    
    
    public Object[] arreglo(){
        Object[] arreglo={id, trabajador, prov, dia, mes, año};
        return arreglo;
    }
    
    public Date devolverFecha(){
        return new Date(año, mes, dia);
    }
    
}