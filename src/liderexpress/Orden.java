
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
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.*;
import javax.swing.border.*;
import static liderexpress.Cliente.connect;
import static liderexpress.QueryLog.log;

public class Orden implements QueryLog{
    int id;
    String pais;
    String ciudad;
    Date fecha;
    String tiempo;
    String numero;
    String estado;
    int cliente;

    Orden(int id, String pais, String ciud, String tiem, String num, String est, int cli){
        this.id = id;
        this.pais = pais;
        this.ciudad = ciud;
        this.tiempo = tiem;
        this.numero = num;
        this.estado = est;
        this.cliente = cli;
    }
    
    static public void crearOrden(final MainMenu m){ 
        final JFrame jCrearOrden = new JFrame("Creacion de Orden");
        jCrearOrden.setSize(500, 300);
        jCrearOrden.setVisible(true);
        Panel panelPrin=new Panel(new GridLayout(7, 1));
        Panel panelpais=new Panel(new GridLayout(1, 2));
        Panel panelciud=new Panel(new GridLayout(1, 2));
        Panel panelfecha = new Panel(new GridLayout(1,2));
        Panel paneltiem=new Panel(new GridLayout(1, 2));
        Panel panelnum=new Panel(new GridLayout(1, 2));
        Panel panelestado=new Panel(new GridLayout(1, 2));
        Panel panelclien=new Panel(new GridLayout(1, 2));
        Panel panelboton=new Panel(new GridLayout(1, 2));
        Label labelpais=new Label("Pais:", Label.CENTER);
        Label labelciud=new Label("Ciudad:", Label.CENTER);
        Label labelfecha=new Label("Fecha:", Label.CENTER);
        Label labeltiem=new Label("Tiempo Entrega:", Label.CENTER);
        Label labelnum=new Label("# Rastreo:", Label.CENTER);
        Label labelclien=new Label("Cliente:", Label.CENTER);
        Label labelestado=new Label("Estado:", Label.CENTER);
        Button guardar=new Button("Guardar");
        Button cancelar=new Button("Cancelar");
        final TextField txtPais=new TextField("", 20);
        final TextField txtCiudad=new TextField("", 20);
        final JComboBox txtAño=new JComboBox();
        final JComboBox txtMes=new JComboBox();
        final JComboBox txtDia=new JComboBox();
        for (int i=2000; i<2015; i++){
            txtAño.addItem(i);
        }
        for (int j=1; j<13; j++){
            txtMes.addItem(j);
        }
        for (int i=1; i<32; i++){
            txtDia.addItem(i);
        }
        final TextField txtTiempo=new TextField("", 20);
        final TextField txtNumero=new TextField("", 20);
        final TextField txtEstado=new TextField("", 20);
        final JComboBox clientes=new JComboBox();
        try{
          ResultSet rs = Cliente.todosClientes();
          while(rs.next()){
              int id = rs.getInt(1);
              String nombre = rs.getString(2);
              clientes.addItem(id+" - "+nombre);
          }  
        }catch(Exception ex){}
        panelpais.add(labelpais);
        panelpais.add(txtPais);
        panelciud.add(labelciud);
        panelciud.add(txtCiudad);
        panelfecha.add(labelfecha);
        panelfecha.add(txtAño);
        panelfecha.add(txtMes);
        panelfecha.add(txtDia);
        paneltiem.add(labeltiem);
        paneltiem.add(txtTiempo);
        panelnum.add(labelnum);
        panelnum.add(txtNumero);
        panelestado.add(labelestado);
        panelestado.add(txtEstado);
        panelclien.add(labelclien);
        panelclien.add(clientes);
        panelboton.add(guardar);
        panelboton.add(cancelar);
        panelPrin.add(panelpais);
        panelPrin.add(panelciud);
        panelPrin.add(panelfecha);
        panelPrin.add(paneltiem);
        panelPrin.add(panelnum);
        panelPrin.add(panelestado);
        panelPrin.add(panelclien);
        panelPrin.add(panelboton);
        jCrearOrden.add(panelPrin);
        guardar.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){
                String cliente = clientes.getSelectedItem().toString();
                String cliente1[] = cliente.split("\\ ");
                String id_cliente = cliente1[0];
                nuevaOrden(id_cliente,txtPais.getText(),txtCiudad.getText(),txtAño.getSelectedItem().toString(),txtMes.getSelectedItem().toString(),txtDia.getSelectedItem().toString(),txtTiempo.getText(),txtEstado.getText(),txtNumero.getText());
                jCrearOrden.setVisible(false);
                m.paintOrdenes();
            }
        });
        cancelar.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){
                jCrearOrden.setVisible(false);
            }
        });
    }
    
        public static int newID(){
        int id = 1;
        ResultSet rs = null;
        try {
            Connection con=connect.Conexion_SQL();
            Statement sentencia=con.createStatement();
            String query="SELECT max(orden.id_orden)+1 as maxID FROM orden;";
            rs = sentencia.executeQuery(query);
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
    
    public static void nuevaOrden(String id_cliente,String pais, String ciudad, String año, String mes, String dia, String tiempo, String estado, String numero){
        try {
            Connection con=connect.Conexion_SQL();
            Statement sentencia=con.createStatement();
            //String query="INSERT INTO cliente VALUES(1, '"+ nombre+"','"+cedula+"','"+ruc+"','"+emp+"','"+telf+"')";
            String query="INSERT INTO orden VALUES("+newID()+","+id_cliente+",'"+pais+"','"+ciudad+"','"+año+"-"+mes+"-"+dia+"',"+tiempo+",'"+estado+"','"+numero+"');";
            sentencia.executeUpdate(query);
            log.add(query);
        }catch(SQLException e){
            JOptionPane.showMessageDialog(null, "Error dato Orden");
        }
        //return fa;
    }
    
    public static ResultSet todasOrdenes(){
        ResultSet rs = null;
        try {
            String query;
            Connection con=connect.Conexion_SQL();
            Statement sentencia=con.createStatement();
            query="SELECT orden.* FROM orden;";
            rs=sentencia.executeQuery(query);
            
        }catch(SQLException e){
            JOptionPane.showMessageDialog(null, "Error dato");
        }
        return rs;
    }
    
    static public void modificarOrden(final int id_orden, final MainMenu m){

            String queryPais = "";
            String queryCiu = "";
            String queryEntr = "";
            String queryEst = "";
            String queryRas = "";
            ResultSet rs1 = null;
            try {
                Connection con=connect.Conexion_SQL();
                Statement sentencia=con.createStatement();
                String query="SELECT (orden.pais) as pais FROM orden WHERE orden.id_orden="+id_orden+";";
                String query1="SELECT (orden.ciudad) as ciudad FROM orden WHERE orden.id_orden="+id_orden+";";
                String query3="SELECT (orden.t_entrega) as tentrega FROM orden WHERE orden.id_orden="+id_orden+";";
                String query4="SELECT (orden.estado) as estado FROM orden WHERE orden.id_orden="+id_orden+";";
                String query5="SELECT (orden.num_rastreo) as rastreo FROM orden WHERE orden.id_orden="+id_orden+";";
                rs1 = sentencia.executeQuery(query);
                try{
                    while(rs1.next())
                    queryPais = rs1.getString("pais");
                }catch (SQLException ex){}
                rs1 = sentencia.executeQuery(query1);
                try{
                     while(rs1.next())
                     queryCiu = rs1.getString("ciudad");
                }catch (SQLException ex){}
                rs1 = sentencia.executeQuery(query3);
                try{
                     while(rs1.next())
                     queryEntr = rs1.getString("tentrega");
                }catch (SQLException ex){}
                rs1 = sentencia.executeQuery(query4);
                try{
                     while(rs1.next())
                     queryEst = rs1.getString("estado");
                }catch (SQLException ex){}
                rs1 = sentencia.executeQuery(query5);
                try{
                     while(rs1.next())
                     queryRas = rs1.getString("rastreo");
                }catch (SQLException ex){}
            }catch(SQLException ex){}
            
        final JFrame jModOrden = new JFrame("Modificacion de Orden");
        jModOrden.setSize(500, 300);
        jModOrden.setVisible(true);
        Panel panelPrin=new Panel(new GridLayout(7, 1));
        Panel panelpais=new Panel(new GridLayout(1, 2));
        Panel panelciud=new Panel(new GridLayout(1, 2));
        Panel panelfecha = new Panel(new GridLayout(1,2));
        Panel paneltiem=new Panel(new GridLayout(1, 2));
        Panel panelnum=new Panel(new GridLayout(1, 2));
        Panel panelestado=new Panel(new GridLayout(1, 2));
        Panel panelclien=new Panel(new GridLayout(1, 2));
        Panel panelboton=new Panel(new GridLayout(1, 2));
        Label labelpais=new Label("Pais:", Label.CENTER);
        Label labelciud=new Label("Ciudad:", Label.CENTER);
        Label labelfecha=new Label("Fecha:", Label.CENTER);
        Label labeltiem=new Label("Tiempo Entrega:", Label.CENTER);
        Label labelnum=new Label("# Rastreo:", Label.CENTER);
        Label labelclien=new Label("Cliente:", Label.CENTER);
        Label labelestado=new Label("Estado:", Label.CENTER);
        Button guardar=new Button("Guardar");
        Button cancelar=new Button("Cancelar");
        final TextField txtPais=new TextField(queryPais, 20);
        final TextField txtCiudad=new TextField(queryCiu, 20);
        final JComboBox txtAño=new JComboBox();
        final JComboBox txtMes=new JComboBox();
        final JComboBox txtDia=new JComboBox();
        for (int i=2000; i<2015; i++){
            txtAño.addItem(i);
        }
        for (int j=1; j<13; j++){
            txtMes.addItem(j);
        }
        for (int i=1; i<32; i++){
            txtDia.addItem(i);
        }
        final TextField txtTiempo=new TextField(queryEntr, 20);
        final TextField txtNumero=new TextField(queryRas, 20);
        final TextField txtEstado=new TextField(queryEst, 20);
        final JComboBox clientes=new JComboBox();
        try{
          ResultSet rs = Cliente.todosClientes();
          while(rs.next()){
              int id = rs.getInt(1);
              String nombre = rs.getString(2);
              clientes.addItem(id+" - "+nombre);
          }  
        }catch(Exception ex){}
        panelpais.add(labelpais);
        panelpais.add(txtPais);
        panelciud.add(labelciud);
        panelciud.add(txtCiudad);
        panelfecha.add(labelfecha);
        panelfecha.add(txtAño);
        panelfecha.add(txtMes);
        panelfecha.add(txtDia);
        paneltiem.add(labeltiem);
        paneltiem.add(txtTiempo);
        panelnum.add(labelnum);
        panelnum.add(txtNumero);
        panelestado.add(labelestado);
        panelestado.add(txtEstado);
        panelclien.add(labelclien);
        panelclien.add(clientes);
        panelboton.add(guardar);
        panelboton.add(cancelar);
        panelPrin.add(panelpais);
        panelPrin.add(panelciud);
        panelPrin.add(panelfecha);
        panelPrin.add(paneltiem);
        panelPrin.add(panelnum);
        panelPrin.add(panelestado);
        panelPrin.add(panelclien);
        panelPrin.add(panelboton);
        jModOrden.add(panelPrin);
        guardar.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){
                int confirm = JOptionPane.showConfirmDialog(null, "Esta seguro que desea modificar la Orden ID: "+id_orden+"?","ALERTA",JOptionPane.INFORMATION_MESSAGE);
                if(confirm==JOptionPane.OK_OPTION){
                String cliente = clientes.getSelectedItem().toString();
                String cliente1[] = cliente.split("\\ ");
                String id_cliente = cliente1[0];
                try {
                    Connection con=connect.Conexion_SQL();
                    Statement sentencia=con.createStatement();
                    String query="UPDATE orden SET orden.id_cliente="+id_cliente+", orden.pais='"+txtPais.getText()+"', orden.ciudad='"+txtCiudad.getText()+"', orden.fecha='"+txtAño.getSelectedItem().toString()+"-"+txtMes.getSelectedItem().toString()+"-"+txtDia.getSelectedItem().toString()+"', orden.t_entrega="+txtTiempo.getText()+", orden.estado='"+txtEstado.getText()+"', orden.num_rastreo='"+txtNumero.getText()+"' WHERE orden.id_orden="+id_orden+";";
                    sentencia.executeUpdate(query);
                    log.add(query);
                }catch(SQLException ex){}
                jModOrden.setVisible(false);
                m.paintOrdenes();
                }
            }
        });
        cancelar.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){
                jModOrden.setVisible(false);
            }
        });
    }
    
    public static void eliminarOrden(int id_orden){
        boolean tieneMerca = false;
        boolean tieneFact = false;
        int confirm = JOptionPane.showConfirmDialog(null, "Esta seguro que desea eliminar Orden ID: "+id_orden+"?","ALERTA",JOptionPane.INFORMATION_MESSAGE);
        if(confirm==JOptionPane.OK_OPTION){
            try{
                Connection con=connect.Conexion_SQL();
                Statement sentencia=con.createStatement();
                ResultSet mercas = Mercaderia.todasMercas();
                ResultSet facts = Factura2.todasFacts();
                try{
                    while(mercas.next()){
                        if(mercas.getInt(10)==id_orden){
                            tieneMerca=true;
                            JOptionPane.showMessageDialog(null,"Error al intentar eliminar Orden ID: "+id_orden+"\nOrden ID: "+id_orden+" tiene asignada una Mercaderia ID: "+mercas.getInt(1));
                        }
                    }
                }catch(SQLException e){}
                try{
                    while(facts.next()){
                        if(facts.getInt(7)==id_orden){
                            tieneFact=true;
                            JOptionPane.showMessageDialog(null,"Error al intentar eliminar Orden ID: "+id_orden+"\nOrden ID: "+id_orden+" tiene asignada una Factura ID: "+facts.getInt(1));
                        }
                    }
                }catch(SQLException e){}  
            }catch(SQLException e){}
            if(tieneMerca==false && tieneFact==false){
                try {
                    Connection con=connect.Conexion_SQL();
                    Statement sentencia=con.createStatement();
                    String query="DELETE FROM orden WHERE orden.id_orden="+id_orden+";";
                    sentencia.executeUpdate(query);
                    log.add(query);
                }catch(SQLException e){}
            }
        }
    }    

    
    public Object[] arreglo(){
        Object[] arreglo={id, pais, ciudad, tiempo, numero, estado, cliente};
        return arreglo;
    }  

}
