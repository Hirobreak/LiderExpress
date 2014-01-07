
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


public class Mercaderia extends Validaciones implements QueryLog {
    int id;
    String style;
    String mark;
    String desc;
    String comp;
    String origen;
    float preProv;
    float preClien;
    int cantidad; 
    int idorden;
    
    Mercaderia(int ident, String estilo, String marca, String des, String compo, String orig, int idor, float pp, float pc, int canti){
        id=ident;
        style=estilo;
        mark=marca;
        desc=des;
        comp=compo;
        origen=orig;
        preProv=pp;
        preClien=pc;
        cantidad=canti;
        idorden=idor;
    }
    
   static public void crearMerc(final MainMenu m){ 
        final JFrame jCrearMerc = new JFrame("Creacion de Mercaderia");
        jCrearMerc.setSize(500, 300);
        jCrearMerc.setVisible(true);
        Panel panelPrin=new Panel(new GridLayout(10, 1));
        Panel panelEst=new Panel(new GridLayout(1, 2));
        Panel panelMarc=new Panel(new GridLayout(1, 2));
        Panel panelDesc=new Panel(new GridLayout(1, 2));
        Panel panelComp=new Panel(new GridLayout(1, 2));
        Panel panelorig=new Panel(new GridLayout(1, 2));
        Panel panelOrd=new Panel(new GridLayout(1, 2));
        Panel panelpp=new Panel(new GridLayout(1, 2));
        Panel panelpc=new Panel(new GridLayout(1, 2));
        Panel panelcant=new Panel(new GridLayout(1, 2));
        Panel panelboton=new Panel(new GridLayout(1, 2));
        Label labelEst=new Label("Estilo:", Label.CENTER);
        Label labelMarc=new Label("Marca", Label.CENTER);
        Label labelDesc=new Label("Descripción:", Label.CENTER);
        Label labelComp=new Label("Composición:", Label.CENTER);
        Label labelOrd=new Label("Pertenece a la Orden: ", Label.CENTER);
        Label labelOrg=new Label("Origen:", Label.CENTER);
        Label labelpp=new Label("Precio Proveedor $:", Label.CENTER);
        Label labelpc=new Label("Precio Venta $: ", Label.CENTER);
        Label labelcant=new Label("Cantidad: ", Label.CENTER);
        Button guardar=new Button("Guardar");
        Button cancelar=new Button("Cancelar");
        final TextField txtEst=new TextField("Estilo", 20);
        final TextField txtMarc=new TextField("Marca", 20);
        final TextField txtDesc=new TextField("Descripción", 20);
        final TextField txtComp=new TextField("Composición", 20);
        final TextField txtOrg=new TextField("Origen", 20);
        final TextField txtpp=new TextField("0.00", 20);
        final TextField txtpc=new TextField("0.00", 20);
        final TextField txtcant=new TextField("0", 20);
        final JComboBox listaOrd=new JComboBox();
        try{
          ResultSet rs = Orden.todasOrdenes();
          while(rs.next()){
              int id = rs.getInt(1);
              String idOrden = rs.getString(1);
              String idCliente = rs.getString(2);
              listaOrd.addItem("Orden: "+idOrden+" - Cliente: "+idCliente);
          }  
        }catch(Exception ex){
                    
        }
        panelEst.add(labelEst);
        panelEst.add(txtEst);
        panelMarc.add(labelMarc);
        panelMarc.add(txtMarc);
        panelDesc.add(labelDesc);
        panelDesc.add(txtDesc);
        panelComp.add(labelComp);
        panelComp.add(txtComp);
        panelorig.add(labelOrg);
        panelorig.add(txtOrg);
        panelOrd.add(labelOrd);
        panelOrd.add(listaOrd);
        panelpp.add(labelpp);
        panelpp.add(txtpp);
        panelpc.add(labelpc);
        panelpc.add(txtpc);
        panelcant.add(labelcant);
        panelcant.add(txtcant);
        panelboton.add(guardar);
        panelboton.add(cancelar);
        panelPrin.add(panelEst);
        panelPrin.add(panelMarc);
        panelPrin.add(panelDesc);
        panelPrin.add(panelComp);
        panelPrin.add(panelorig);
        panelPrin.add(panelpp);
        panelPrin.add(panelpc);
        panelPrin.add(panelcant);
        panelPrin.add(panelOrd);
        panelPrin.add(panelboton);
        jCrearMerc.add(panelPrin);
        guardar.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){
                String orden = listaOrd.getSelectedItem().toString();
                String orden1[] = orden.split("\\ ");
                String id_orden = orden1[1];
                System.out.println(id_orden);
                if(largoString(txtEst.getText(),20)==false)
                        JOptionPane.showMessageDialog(null,"Error, el estilo tiene hasta 20 caracteres.Intente de nuevo");
                if(largoString(txtMarc.getText(),20)==false)
                        JOptionPane.showMessageDialog(null,"Error, la marca es de hasta 20 carateres.Intente de nuevo");
                if(largoString(txtDesc.getText(),20)==false)
                        JOptionPane.showMessageDialog(null,"Error, el descuento es de hasta 20 caracteres.Intente de nuevo");
                if(largoString(txtComp.getText(),20)==false)
                        JOptionPane.showMessageDialog(null,"Error,la composicion es de hasta 20 caracteres.Intente de nuevo");
                if(largoInt(txtcant.getText(),10)==false)
                        JOptionPane.showMessageDialog(null,"Error, las cantidades son de hasta 5 digitos.Intente de nuevo");
                if(largoString(txtOrg.getText(),10)==false)
                        JOptionPane.showMessageDialog(null,"Error, el origen es hasta de 20 caracteres.Intente de nuevo");
                if(largoInt(txtpp.getText(),20)==false)
                        JOptionPane.showMessageDialog(null,"Error, el precio de venta es un numero de hasta 20 digitos.Intente de nuevo");
                if(largoInt(txtpc.getText(),20)==false)
                        JOptionPane.showMessageDialog(null,"Error,el precio de compra es un numero de hasta 20 digitos.Intente de nuevo");
                if(largoString(txtEst.getText(),20)&&largoString(txtMarc.getText(),20)&&largoString(txtDesc.getText(),20)&&largoString(txtComp.getText(),20)&&largoInt(txtcant.getText(),10)&&largoString(txtOrg.getText(),10)&&largoInt(txtpp.getText(),20)&&largoInt(txtpc.getText(),20)){
                nuevaMerca(txtEst.getText(),txtMarc.getText(),txtDesc.getText(),txtComp.getText(),txtcant.getText(),txtOrg.getText(), txtpp.getText(), txtpc.getText(),id_orden);
                jCrearMerc.setVisible(false);}
                m.paintMercas();
            }
        });
        cancelar.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){
                jCrearMerc.setVisible(false);
            }
        });
    }
    
    public static void nuevaMerca(String estilo,String marca, String dsc, String compos, String cantidad, String origen, String p_venta, String p_compra, String id_orden){
        try {
            Connection con=connect.Conexion_SQL();
            Statement sentencia=con.createStatement();
            String query="INSERT INTO mercaderia VALUES("+newID()+",'"+estilo+"','"+marca+"','"+dsc+"','"+compos+"',"+cantidad+",'"+origen+"',"+p_venta+","+p_compra+","+id_orden+");";
            sentencia.executeUpdate(query);
            log.add(query);
        }catch(SQLException e){
            JOptionPane.showMessageDialog(null, "Error dato Mercaderia");
        }
    }
    
    public static int newID(){
        int id = 0;
        ResultSet rs = null;
        try {
            Connection con=connect.Conexion_SQL();
            Statement sentencia=con.createStatement();
            String query="SELECT max(mercaderia.id_merca)+1 as maxID FROM mercaderia;";
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
    
    public static ResultSet todasMercas(){
        ResultSet rs = null;
        try {
            String query;
            Connection con=connect.Conexion_SQL();
            Statement sentencia=con.createStatement();
            query="SELECT mercaderia.* FROM mercaderia;";
            rs=sentencia.executeQuery(query);
            
        }catch(SQLException e){
            JOptionPane.showMessageDialog(null, "Error dato");
        }
        return rs;
    }
   
    static public void modMerc(final int id_merca, final MainMenu m){

            String queryEst = "";
            String queryMar = "";
            String queryDsc = "";
            String queryCompos = "";
            String queryCan = "";
            String queryOri = "";
            String queryVen = "";
            String queryCompra = "";
            ResultSet rs1 = null;
            try {
                Connection con=connect.Conexion_SQL();
                Statement sentencia=con.createStatement();
                String query="SELECT (mercaderia.estilo) as estilo FROM mercaderia WHERE mercaderia.id_merca="+id_merca+";";
                String query1="SELECT (mercaderia.marca) as marca FROM mercaderia WHERE mercaderia.id_merca="+id_merca+";";
                String query2="SELECT (mercaderia.dsc) as dsc FROM mercaderia WHERE mercaderia.id_merca="+id_merca+";";
                String query3="SELECT (mercaderia.compos) as compos FROM mercaderia WHERE mercaderia.id_merca="+id_merca+";";
                String query4="SELECT (mercaderia.cantidad) as cantidad FROM mercaderia WHERE mercaderia.id_merca="+id_merca+";";
                String query5="SELECT (mercaderia.origen) as origen FROM mercaderia WHERE mercaderia.id_merca="+id_merca+";";
                String query6="SELECT (mercaderia.precio_venta) as venta FROM mercaderia WHERE mercaderia.id_merca="+id_merca+";";
                String query7="SELECT (mercaderia.precio_compra) as compra FROM mercaderia WHERE mercaderia.id_merca="+id_merca+";";
                rs1 = sentencia.executeQuery(query);
                try{
                    while(rs1.next())
                    queryEst = rs1.getString("estilo");
                }catch (SQLException ex){}
                rs1 = sentencia.executeQuery(query1);
                try{
                     while(rs1.next())
                     queryMar = rs1.getString("marca");
                }catch (SQLException ex){}
                rs1 = sentencia.executeQuery(query2);
                try{
                     while(rs1.next())
                     queryDsc = rs1.getString("dsc");
                }catch (SQLException ex){}
                rs1 = sentencia.executeQuery(query3);
                try{
                     while(rs1.next())
                     queryCompos = rs1.getString("compos");
                }catch (SQLException ex){}
                rs1 = sentencia.executeQuery(query4);
                try{
                     while(rs1.next())
                     queryCan = rs1.getString("cantidad");
                }catch (SQLException ex){}
                 rs1 = sentencia.executeQuery(query5);
                try{
                     while(rs1.next())
                     queryOri = rs1.getString("origen");
                }catch (SQLException ex){}
                rs1 = sentencia.executeQuery(query6);
                try{
                     while(rs1.next())
                     queryVen = rs1.getString("venta");
                }catch (SQLException ex){}
                rs1 = sentencia.executeQuery(query7);
                try{
                     while(rs1.next())
                     queryCompra = rs1.getString("compra");
                }catch (SQLException ex){}
            }catch(SQLException ex){}
           
        
        final JFrame jCrearMerc = new JFrame("Modificacion de Mercaderia");
        jCrearMerc.setSize(500, 300);
        jCrearMerc.setVisible(true);
        Panel panelPrin=new Panel(new GridLayout(10, 1));
        Panel panelEst=new Panel(new GridLayout(1, 2));
        Panel panelMarc=new Panel(new GridLayout(1, 2));
        Panel panelDesc=new Panel(new GridLayout(1, 2));
        Panel panelComp=new Panel(new GridLayout(1, 2));
        Panel panelorig=new Panel(new GridLayout(1, 2));
        Panel panelOrd=new Panel(new GridLayout(1, 2));
        Panel panelpp=new Panel(new GridLayout(1, 2));
        Panel panelpc=new Panel(new GridLayout(1, 2));
        Panel panelcant=new Panel(new GridLayout(1, 2));
        Panel panelboton=new Panel(new GridLayout(1, 2));
        Label labelEst=new Label("Estilo:", Label.CENTER);
        Label labelMarc=new Label("Marca", Label.CENTER);
        Label labelDesc=new Label("Descripción:", Label.CENTER);
        Label labelComp=new Label("Composición:", Label.CENTER);
        Label labelOrd=new Label("Pertenece a la Orden: ", Label.CENTER);
        Label labelOrg=new Label("Origen:", Label.CENTER);
        Label labelpp=new Label("Precio Proveedor $:", Label.CENTER);
        Label labelpc=new Label("Precio Venta $: ", Label.CENTER);
        Label labelcant=new Label("Cantidad: ", Label.CENTER);
        Button guardar=new Button("Guardar");
        Button cancelar=new Button("Cancelar");
        final TextField txtEst=new TextField(queryEst, 20);
        final TextField txtMarc=new TextField(queryMar, 20);
        final TextField txtDesc=new TextField(queryDsc, 20);
        final TextField txtComp=new TextField(queryCompos, 20);
        final TextField txtOrg=new TextField(queryOri, 20);
        final TextField txtpp=new TextField(queryVen, 20);
        final TextField txtpc=new TextField(queryCompra, 20);
        final TextField txtcant=new TextField(queryCan, 20);
        final JComboBox listaOrd=new JComboBox();
        try{
          ResultSet rs = Orden.todasOrdenes();
          while(rs.next()){
              int id = rs.getInt(1);
              String idOrden = rs.getString(1);
              String idCliente = rs.getString(2);
              listaOrd.addItem("Orden: "+idOrden+" - Cliente: "+idCliente);
          }  
        }catch(Exception ex){
                    
        }
        panelEst.add(labelEst);
        panelEst.add(txtEst);
        panelMarc.add(labelMarc);
        panelMarc.add(txtMarc);
        panelDesc.add(labelDesc);
        panelDesc.add(txtDesc);
        panelComp.add(labelComp);
        panelComp.add(txtComp);
        panelorig.add(labelOrg);
        panelorig.add(txtOrg);
        panelOrd.add(labelOrd);
        panelOrd.add(listaOrd);
        panelpp.add(labelpp);
        panelpp.add(txtpp);
        panelpc.add(labelpc);
        panelpc.add(txtpc);
        panelcant.add(labelcant);
        panelcant.add(txtcant);
        panelboton.add(guardar);
        panelboton.add(cancelar);
        panelPrin.add(panelEst);
        panelPrin.add(panelMarc);
        panelPrin.add(panelDesc);
        panelPrin.add(panelComp);
        panelPrin.add(panelorig);
        panelPrin.add(panelpp);
        panelPrin.add(panelpc);
        panelPrin.add(panelcant);
        panelPrin.add(panelOrd);
        panelPrin.add(panelboton);
        jCrearMerc.add(panelPrin);
        guardar.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){
                int confirm = JOptionPane.showConfirmDialog(null, "Esta seguro que desea modificar la Mercaderia ID: "+id_merca+"?","ALERTA",JOptionPane.INFORMATION_MESSAGE);
                if(confirm==JOptionPane.OK_OPTION){
                String orden = listaOrd.getSelectedItem().toString();
                String orden1[] = orden.split("\\ ");
                String id_orden = orden1[1];
                System.out.println(id_orden);
                try {
                    
                                if(largoString(txtEst.getText(),20)==false)
                        JOptionPane.showMessageDialog(null,"Error, el estilo tiene hasta 20 caracteres.Intente de nuevo");
                if(largoString(txtMarc.getText(),20)==false)
                        JOptionPane.showMessageDialog(null,"Error, la marca es de hasta 20 carateres.Intente de nuevo");
                if(largoString(txtDesc.getText(),20)==false)
                        JOptionPane.showMessageDialog(null,"Error, el descuento es de hasta 20 caracteres.Intente de nuevo");
                if(largoString(txtComp.getText(),20)==false)
                        JOptionPane.showMessageDialog(null,"Error,la composicion es de hasta 20 caracteres.Intente de nuevo");
                if(largoInt(txtcant.getText(),10)==false)
                        JOptionPane.showMessageDialog(null,"Error, las cantidades son de hasta 5 digitos.Intente de nuevo");
                if(largoString(txtOrg.getText(),10)==false)
                        JOptionPane.showMessageDialog(null,"Error, el origen es hasta de 20 caracteres.Intente de nuevo");
                if(largoInt(txtpp.getText(),20)==false)
                        JOptionPane.showMessageDialog(null,"Error, el precio de venta es un numero de hasta 20 digitos.Intente de nuevo");
                if(largoInt(txtpc.getText(),20)==false)
                        JOptionPane.showMessageDialog(null,"Error,el precio de compra es un numero de hasta 20 digitos.Intente de nuevo");
                if(largoString(txtEst.getText(),20)&&largoString(txtMarc.getText(),20)&&largoString(txtDesc.getText(),20)&&largoString(txtComp.getText(),20)&&largoInt(txtcant.getText(),10)&&largoString(txtOrg.getText(),10)&&largoInt(txtpp.getText(),20)&&largoInt(txtpc.getText(),20))
 
                    {
                    Connection con=connect.Conexion_SQL();
                    Statement sentencia=con.createStatement();
                    String query="UPDATE mercaderia SET mercaderia.estilo="+txtEst.getText()+", mercaderia.marca='"+txtMarc.getText()+"', mercaderia.dsc='"+txtDesc.getText()+"', mercaderia.compos='"+txtComp.getText()+"', mercaderia.cantidad="+txtcant.getText()+", mercaderia.origen='"+txtOrg.getText()+"', mercaderia.precio_venta="+txtpp.getText()+", mercaderia.precio_compra="+txtpc.getText()+", mercaderia.id_orden="+id_orden+" WHERE mercaderia.id_merca="+id_merca+";";
                    sentencia.executeUpdate(query);
                    log.add(query);
                    jCrearMerc.setVisible(false);
                    }
                }catch(SQLException ex){}
               
                m.paintMercas();
                }
            }
        });
        cancelar.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){
                jCrearMerc.setVisible(false);
            }
        });
        
    }
    
    public static void eliminarMerca(int id_merca){
        boolean tieneEmpaq = false;
        int confirm = JOptionPane.showConfirmDialog(null, "Esta seguro que desea eliminar Mercaderia ID: "+id_merca+"?","ALERTA",JOptionPane.INFORMATION_MESSAGE);
        if(confirm==JOptionPane.OK_OPTION){
            try{
                Connection con=connect.Conexion_SQL();
                Statement sentencia=con.createStatement();
                ResultSet empaqs = Empaquetado.todosEmpaqs();
                try{
                    while(empaqs.next()){
                        if(empaqs.getInt(3)==id_merca){
                            tieneEmpaq=true;
                            JOptionPane.showMessageDialog(null,"Error al intentar eliminar Mercaderia ID: "+id_merca+"\nMercaderia ID: "+id_merca+" tiene asignada un Empaquetado ID: "+empaqs.getInt(1));
                        }
                    }
                }catch(SQLException e){}   
            }catch(SQLException e){}
            if(tieneEmpaq==false){
                try {
                    Connection con=connect.Conexion_SQL();
                    Statement sentencia=con.createStatement();
                    String query="DELETE FROM mercaderia WHERE mercaderia.id_merca="+id_merca+";";
                    sentencia.executeUpdate(query);
                    log.add(query);
                }catch(SQLException e){}
            }
        }
    }
    
        public Object[] arreglo(){
        Object[] arreglo={id, style, mark, desc, comp, origen, preProv, preClien, cantidad, idorden};
        return arreglo;
    }   
    
}
