
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
import java.util.Date;
import javax.swing.table.DefaultTableModel;
import static liderexpress.Cliente.connect;
import static liderexpress.QueryLog.log;
//factura orden
public class Factura implements QueryLog {
    int id;
    float valorp;
    float iva;
    float dscto;
    Date fecha;
    float vfinal;
    int idOrden;
    
    Factura(int ident, float vp, float iv, float dsc, float vf, Date fech, int ord){
        id=ident;
        valorp=vp;
        iva=iv;
        dscto=dsc;
        vfinal=vf;
        fecha=fech;
        idOrden=ord;
    }
    
    
    public static void mostrarFact(int idorden){
        ResultSet rs = null;
        try {
            Connection con=connect.Conexion_SQL();
            CallableStatement pro = (CallableStatement) con.prepareCall("{call searchfact2(?)}");
            pro.setInt(1, idorden);
            pro.execute();
            rs=pro.getResultSet();
            rs.next();
           // System.out.println("a");
            //System.out.println(idorden);
        }catch(SQLException e){
            JOptionPane.showMessageDialog(null, "Error dato 1");
        }
      //  System.out.println("b");
        final JFrame fact=new JFrame("Factura");
        fact.setResizable(false);
        JTable tabla=new JTable();
        fact.setSize(315, 310);
        fact.setVisible(true);
        Panel panelPrin=new Panel(new FlowLayout());
        Panel panelclien=new Panel(new GridLayout(1, 2));
        Panel panelfech=new Panel(new GridLayout(1, 2));
        Panel panelvp=new Panel(new GridLayout(1, 2));
        Panel paneliv=new Panel(new GridLayout(1, 2));
        Panel paneldsc=new Panel(new GridLayout(1, 2));
        Panel panelvf=new Panel(new GridLayout(1, 2));
        JPanel paneltabla=new JPanel();
        Panel panelpagos=new Panel(new GridLayout(1, 2));
        Label labelclien=new Label("Cliente:", Label.CENTER);
        Label labelfech=new Label("Fecha Emisión:", Label.CENTER);
        Label labelvp=new Label("Valor Parcial", Label.CENTER);
        Label labeliv=new Label("IVA 12%:", Label.CENTER);
        Label labeldsc=new Label("Dscto:", Label.CENTER);
        Label labelvf=new Label("Valor Final:", Label.CENTER);
       // System.out.println("c");
        Label labelclien2=new Label("Fulanito", Label.CENTER);
        Label labelfech2=new Label("12/12/2012", Label.CENTER);
        Label labelvp2=new Label("$3009.91", Label.CENTER);
        Label labeliv2=new Label("$120.23", Label.CENTER);
        Label labeldsc2=new Label("0%", Label.CENTER);
        Label labelvf2=new Label("$3302.45", Label.CENTER); 
       // System.out.println("d");
        try{
            //System.out.println(rs.getString("e"));
            labelclien2=new Label(rs.getString("nombre"), Label.CENTER);
           // System.out.println(rs.getString("b"));
            labelfech2=new Label(rs.getString("fecha"), Label.CENTER);
           //System.out.println(rs.getString("c"));
            labelvp2=new Label(rs.getString("valor_p"), Label.CENTER);
          // System.out.println(rs.getString("d"));
            labeliv2=new Label(rs.getString("iva"), Label.CENTER);
           // System.out.println(rs.getString("f"));
            labeldsc2=new Label("0%", Label.CENTER);
          // System.out.println(rs.getString("g"));
            labelvf2=new Label(rs.getString("valor_final"), Label.CENTER);   
            //System.out.println(rs.getString("h"));
        }catch(SQLException e){
             JOptionPane.showMessageDialog(null, "Error dato 2");
        }
        JButton regen=new JButton("Actualizar");
        JButton agregarp=new JButton("Agregar Pago");
        JButton verp=new JButton("Ver Pagos");
        
        paneltabla.setBorder(BorderFactory.createTitledBorder (BorderFactory.createEtchedBorder (), "Mercadería", TitledBorder.CENTER, TitledBorder.TOP));
        Object[] columns={"Estilo", "Descripcion", "Cantidad", "Precio"};
        DefaultTableModel modelo2=new DefaultTableModel(null, columns);
        ResultSet res = null;
        try {
            Connection con=connect.Conexion_SQL();
            CallableStatement pro = (CallableStatement) con.prepareCall("{call factMerca(?)}");
            pro.setInt(1, idorden);
            pro.execute();
            res=pro.getResultSet();
        }catch(SQLException e){
            JOptionPane.showMessageDialog(null, "Error dato 3");
        }
        try {
            while(res.next()){
                Object[] fila={res.getString(1), res.getString(2), res.getString(3), res.getString(4)};
                modelo2.addRow(fila);
            }
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Error dato 4");
        }
        tabla.setModel(modelo2);
        paneltabla.setPreferredSize(new Dimension(315, 100));
        
        panelclien.add(labelclien);
        panelclien.add(labelclien2);
        panelfech.add(labelfech);
        panelfech.add(labelfech2);
        panelvp.add(labelvp);
        panelvp.add(labelvp2);
        paneliv.add(labeliv);
        paneliv.add(labeliv2);
        paneldsc.add(labeldsc);
        paneldsc.add(labeldsc2);
        panelvf.add(labelvf);
        panelvf.add(labelvf2);
        paneltabla.add(tabla);
        panelpagos.add(agregarp);
        panelpagos.add(verp);
        panelPrin.add(panelclien);
        panelPrin.add(panelfech);
        panelPrin.add(panelvp);
        panelPrin.add(paneliv);
        panelPrin.add(paneldsc);
        panelPrin.add(panelvf);
        panelPrin.add(paneltabla);
        //panelPrin.add(regen);
        panelPrin.add(panelpagos);
        fact.add(panelPrin);
     
        agregarp.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){
            
                
                Pago2.crearPago(String.valueOf(1));
            }
        });
        verp.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){
                //Pago.verPagos();
            }
        });
        
        
        
    }
    
    public static ResultSet todasFacts(){
        ResultSet rs = null;
        try {
            String query;
            Connection con=connect.Conexion_SQL();
            Statement sentencia=con.createStatement();
            query="SELECT factura1.* FROM factura1;";
            rs=sentencia.executeQuery(query);
            
        }catch(SQLException e){
            JOptionPane.showMessageDialog(null, "Error dato 5");
        }
        return rs;
    }
    
    public static void eliminarFact(int id_fact){
        boolean tienePago = false;
        int confirm = JOptionPane.showConfirmDialog(null, "Esta seguro que desea eliminar Factura ID: "+id_fact+"?","ALERTA",JOptionPane.INFORMATION_MESSAGE);
        if(confirm==JOptionPane.OK_OPTION){
            try{
                Connection con=connect.Conexion_SQL();
                Statement sentencia=con.createStatement();
                ResultSet pagos = Pago.todosPagos();
                try{
                    while(pagos.next()){
                        if(pagos.getInt(4)==id_fact){
                            tienePago=true;
                            JOptionPane.showMessageDialog(null,"Error al intentar eliminar Factura ID: "+id_fact+"\nFactura ID: "+id_fact+" tiene asignado un Pago ID: "+pagos.getInt(1));
                        }
                    }
                }catch(SQLException e){}   
            }catch(SQLException e){}
            if(tienePago==false){
                try {
                    Connection con=connect.Conexion_SQL();
                    Statement sentencia=con.createStatement();
                    String query="DELETE FROM factura1 WHERE factura1.id_factura="+id_fact+";";
                    sentencia.executeUpdate(query);
                    log.add(query);
                }catch(SQLException e){}
            }
        }
    }
}
