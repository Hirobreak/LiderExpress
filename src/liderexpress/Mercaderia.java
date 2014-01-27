
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
        final TextField txtEst=new TextField("", 20);
        final TextField txtMarc=new TextField("", 20);
        final TextField txtDesc=new TextField("", 20);
        final TextField txtComp=new TextField("", 20);
        final TextField txtOrg=new TextField("", 20);
        final TextField txtpp=new TextField("", 20);
        final TextField txtpc=new TextField("", 20);
        final TextField txtcant=new TextField("", 20);
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
                //System.out.println(id_orden);
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
            CallableStatement pro = (CallableStatement) con.prepareCall("{call crearMerca(?,?,?,?,?,?,?,?,?,?)}");
            pro.setInt(1, newID());
            pro.setString(2, estilo);
            pro.setString(3, marca);
            pro.setString(4, dsc);                        
            pro.setString(5, compos);
            pro.setInt(6, Integer.parseInt(cantidad));
            pro.setString(7, origen);                        
            pro.setFloat(8, Float.parseFloat(p_venta));
            pro.setFloat(9, Float.parseFloat(p_compra));
            pro.setInt(10, Integer.parseInt(id_orden));
            pro.execute();
        }catch(SQLException e){
            JOptionPane.showMessageDialog(null, "Error dato Mercaderia");
        }
    }
    
    public static int newID(){
        int id = 0;
        ResultSet rs = null;
        try {
            Connection con=connect.Conexion_SQL();
            CallableStatement pro = (CallableStatement) con.prepareCall("{call maxMerca()}");
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
    
    public static ResultSet todasMercas(){
        ResultSet rs = null;
        try {
            String query;
            Connection con=connect.Conexion_SQL();
            CallableStatement pro = (CallableStatement) con.prepareCall("{call allMerca()}");
            pro.execute();
            rs=pro.getResultSet();
            
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
            int queryCan = 0;
            String queryOri = "";
            float queryVen = 0;
            float queryCompra = 0;
            ResultSet rs1 = null;
            try {
                Connection con=connect.Conexion_SQL();
                CallableStatement pro = (CallableStatement) con.prepareCall("{call takeMercaData(?)}");
                pro.setInt(1, id_merca);
                pro.execute();
                ResultSet res = pro.getResultSet();
                res.next();
                queryEst = res.getString("estilo");
                queryMar = res.getString("marca");
                queryDsc = res.getString("dsc");
                queryCompos = res.getString("compos");
                queryCan = res.getInt("cantidad");
                queryOri = res.getString("origen");
                queryVen = res.getFloat("precio_venta");
                queryCompra = res.getFloat("precio_compra");
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
        final TextField txtpp=new TextField(String.valueOf(queryVen), 20);
        final TextField txtpc=new TextField(String.valueOf(queryCompra), 20);
        final TextField txtcant=new TextField(String.valueOf(queryCan), 20);
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
                    CallableStatement pro = (CallableStatement) con.prepareCall("{call updateMerca(?,?,?,?,?,?,?,?,?,?)}");
                    pro.setInt(1, id_merca);
                    pro.setString(2, txtEst.getText());
                    pro.setString(3, txtMarc.getText());
                    pro.setString(4, txtDesc.getText());                        
                    pro.setString(5, txtComp.getText());
                    pro.setInt(6, Integer.parseInt(txtcant.getText()));
                    pro.setString(7, txtOrg.getText());                        
                    pro.setFloat(8, Float.parseFloat(txtpp.getText()));
                    pro.setFloat(9, Float.parseFloat(txtpc.getText()));
                    pro.setInt(10, Integer.parseInt(id_orden));
                    pro.execute();
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
                    CallableStatement pro = (CallableStatement) con.prepareCall("{call deleteMerca(?)}");
                    pro.setInt(1, id_merca);
                    pro.execute();
                }catch(SQLException e){}
            }
        }
    }
    
        public Object[] arreglo(){
        Object[] arreglo={id, style, mark, desc, comp, origen, preProv, preClien, cantidad, idorden};
        return arreglo;
    }   
    
}
