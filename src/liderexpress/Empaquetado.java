
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
import static liderexpress.Importacion.newID;

public class Empaquetado {
    int id;
    int idcont;
    int idmerca;
    int idcaja;
    String estado;
    
    Empaquetado(int ide, int conte, int merca, int caja, String est){
        id = ide;
        idcont=conte;
        idmerca=merca;
        idcaja=caja;
        estado=est;
    }
    
    static public void crearEmpaq(final MainMenu m){
        final JFrame jCrearEmp = new JFrame("Asignar Empaquetado");
        jCrearEmp.setSize(800, 200);
        jCrearEmp.setVisible(true);
        Panel panelRefresh = new Panel(new GridLayout(2,2));
        Panel panelPrin=new Panel(new GridLayout(5, 1));
        Panel panelMerc=new Panel(new GridLayout(1, 2));
        Panel panelCaja=new Panel(new GridLayout(1, 3));
        Panel panelCont=new Panel(new GridLayout(1, 3));
        Panel panelEst=new Panel(new GridLayout(1, 2));
        Panel panelboton=new Panel(new GridLayout(1, 2));
        Label labelMerc=new Label("Mercaderia:", Label.CENTER);
        Label labelCaja=new Label("Caja", Label.CENTER);
        Label labelCont=new Label("Contenedor", Label.CENTER);
        Label labelest=new Label("Estado", Label.CENTER);
        Button guardar=new Button("Guardar");
        Button cancelar=new Button("Cancelar");
        final JComboBox estados=new JComboBox();
        estados.addItem("Embarcado");
        estados.addItem("Desmontado");
        final JComboBox mercas=new JComboBox();
        try{
          ResultSet rs = Mercaderia.todasMercas();
          while(rs.next()){
              int id = rs.getInt(1);
              String estilo = rs.getString(2);
              mercas.addItem(id+" - "+estilo);
          }  
        }catch(Exception ex){
                    
        }
        final JComboBox cajas1=new JComboBox();
        try{
          ResultSet rs = Caja.todasCajas();
          while(rs.next()){
              int id = rs.getInt(1);
              String numero = rs.getString(2);
              cajas1.addItem(id+" - "+numero);
          }  
        }catch(Exception ex){
                    
        }
        final JComboBox contens=new JComboBox();
        try{
          ResultSet rs = Contenedor.todosConts();
          while(rs.next()){
              int id = rs.getInt(1);
              String dimensiones = rs.getString(2);
              String estado = rs.getString(4);
              contens.addItem(id+" - "+dimensiones+" - "+estado);
          }  
        }catch(Exception ex){
                    
        }
        panelMerc.add(labelMerc);
        panelMerc.add(mercas);
        panelCaja.add(labelCaja);
        panelCaja.add(cajas1);
        panelCont.add(labelCont);
        panelCont.add(contens);
        panelEst.add(labelest);
        panelEst.add(estados);
        panelboton.add(guardar);
        panelboton.add(cancelar);
        panelPrin.add(panelMerc);
        panelPrin.add(panelCaja);
        panelPrin.add(panelCont);
        panelPrin.add(panelEst);
        panelPrin.add(panelboton);
        panelPrin.add(panelRefresh);
        jCrearEmp.add(panelPrin);
        guardar.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){
                String cont = contens.getSelectedItem().toString();
                String cont1[] = cont.split("\\ ");
                String id_cont = cont1[0];
                String merc = mercas.getSelectedItem().toString();
                String merc1[] = merc.split("\\ ");
                String id_merc = merc1[0];
                String caja = cajas1.getSelectedItem().toString();
                String caja1[] = caja.split("\\ ");
                String id_caja = caja1[0];
                nuevoEmpaq(id_cont,id_merc,id_caja,estados.getSelectedItem().toString());
                jCrearEmp.setVisible(false);
                m.paintEmpaqs();
            }
        });
        cancelar.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){
                jCrearEmp.setVisible(false);
            }
        });
    }
    
        public static void nuevoEmpaq(String id_cont, String id_merc, String id_caja, String estado){
        try {
            Connection con=connect.Conexion_SQL();
            Statement sentencia=con.createStatement();
            String query="INSERT INTO empaquetado VALUES("+newID()+","+id_cont+", "+id_merc+", "+id_caja+",'"+estado+"');";
            sentencia.executeUpdate(query);
        }catch(SQLException e){
            JOptionPane.showMessageDialog(null, "Error dato");
        }
        //return fa;
    } 
    
    public static ResultSet todosEmpaqs(){
        ResultSet rs = null;
        try {
            String query;
            Connection con=connect.Conexion_SQL();
            Statement sentencia=con.createStatement();
            query="SELECT empaquetado.* FROM empaquetado;";
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
            String query="SELECT max(empaquetado.id_empaquetado)+1 as maxID FROM empaquetado;";
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
    
    
    static public void modEmpaq(Empaquetado emp){
        final JFrame jModEmp = new JFrame("Modificar Empaquetado");
        jModEmp.setSize(500, 300);
        jModEmp.setVisible(true);
        Panel panelPrin=new Panel(new GridLayout(5, 1));
        Panel panelMerc=new Panel(new GridLayout(1, 2));
        Panel panelCaja=new Panel(new GridLayout(1, 3));
        Panel panelCont=new Panel(new GridLayout(1, 3));
        Panel panelEst=new Panel(new GridLayout(1, 2));
        Panel panelboton=new Panel(new GridLayout(1, 2));
        Label labelMerc=new Label("Mercaderia:", Label.CENTER);
        Label labelCaja=new Label("Caja", Label.CENTER);
        Label labelCont=new Label("Contenedor", Label.CENTER);
        Label labelest=new Label("Estado", Label.CENTER);
        Button guardar=new Button("Guardar");
        Button cancelar=new Button("Cancelar");
        Button crearCaja=new Button("Crear");
        Button crearCont=new Button("Crear");
        JComboBox estados=new JComboBox();
        JComboBox mercas=new JComboBox();
        JComboBox cajas=new JComboBox();
        JComboBox contens=new JComboBox();
        estados.addItem("Embarcado");
        estados.addItem("Desmontado");
        panelMerc.add(labelMerc);
        panelMerc.add(mercas);
        panelCaja.add(labelCaja);
        panelCaja.add(crearCaja);
        panelCaja.add(cajas);
        panelCont.add(labelCont);
        panelCont.add(crearCont);
        panelCont.add(contens);
        panelEst.add(labelest);
        panelEst.add(estados);
        panelboton.add(guardar);
        panelboton.add(cancelar);
        panelPrin.add(panelMerc);
        panelPrin.add(panelCaja);
        panelPrin.add(panelCont);
        panelPrin.add(panelEst);
        panelPrin.add(panelboton);
        jModEmp.add(panelPrin);
        guardar.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){
                jModEmp.setVisible(false);
            }
        });
        cancelar.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){
                jModEmp.setVisible(false);
            }
        });
    }  

    public static void eliminarEmpaq(int id_empaq){
        int confirm = JOptionPane.showConfirmDialog(null, "Esta seguro que desea eliminar Empaquetado ID: "+id_empaq+"?","ALERTA",JOptionPane.INFORMATION_MESSAGE);
        if(confirm==JOptionPane.OK_OPTION){
            try {
                Connection con=connect.Conexion_SQL();
                Statement sentencia=con.createStatement();
                String query="DELETE FROM empaquetado WHERE empaquetado.id_empaquetado="+id_empaq+";";
                sentencia.executeUpdate(query);
            }catch(SQLException e){}
        }
    }
    
    public Object[] arreglo(){
        Object[] arreglo={id, idcont, idmerca, idcaja, estado};
        return arreglo;
    }
}
