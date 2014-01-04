
package liderexpress;

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

public class Importacion{
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
            Statement sentencia=con.createStatement();
            String query="INSERT INTO importacion VALUES("+newID()+",'"+id_trab+"', '"+id_prov+"','"+año+"-"+mes+"-"+dia+"');";
            sentencia.executeUpdate(query);
        }catch(SQLException e){
            JOptionPane.showMessageDialog(null, "Error dato");
        }
        //return fa;
    } 
    
    public static ResultSet todasImport(){
        ResultSet rs = null;
        try {
            String query;
            Connection con=connect.Conexion_SQL();
            Statement sentencia=con.createStatement();
            query="SELECT importacion.* FROM importacion;";
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
            String query="SELECT max(importacion.id_import)+1 as maxID FROM importacion;";
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
    
    static public void modImpo(Importacion im){
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
        Button bCont=new Button("Contenedores");
        JComboBox superv=new JComboBox();
        superv.addItem(im.trabajador);
        JComboBox provee=new JComboBox();
        provee.addItem(im.prov);
        JComboBox cdia=new JComboBox();
        cdia.addItem(im.dia);
        JComboBox cmes=new JComboBox();
        cmes.addItem(im.mes);
        JComboBox caño=new JComboBox();
        caño.addItem(im.año);
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
        panelCont.add(bCont);
        panelboton.add(guardar);
        panelboton.add(cancelar);
        panelPrin.add(panelTrab);
        panelPrin.add(panelProv);
        panelPrin.add(panelFecha);
        panelPrin.add(panelCont);
        panelPrin.add(panelboton);
        jModImpo.add(panelPrin);
        guardar.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){
                jModImpo.setVisible(false);
            }
        });
        cancelar.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){
                jModImpo.setVisible(false);
            }
        });
    }
    public Object[] arreglo(){
        Object[] arreglo={id, trabajador, prov, dia, mes, año};
        return arreglo;
    }
    
    public Date devolverFecha(){
        return new Date(año, mes, dia);
    }
    
}