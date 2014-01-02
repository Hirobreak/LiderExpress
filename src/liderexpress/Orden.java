
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

public class Orden{
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
    
    static public void crearOrden(final ArrayList<Cliente> clientes1, final ArrayList<Orden> ordenes){ 
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
        }catch(Exception ex){
                    
        }
        
        //final Cliente asignado = clientes1.get(clientes.getSelectedIndex());
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
                System.out.print((int)(txtAño.getSelectedItem())+"-"+(int)(txtMes.getSelectedItem())+"-"+(int)(txtDia.getSelectedItem())+" -- "+id_cliente);
                nuevaOrden(id_cliente,txtPais.getText(),txtCiudad.getText(),txtAño.getSelectedItem().toString(),txtMes.getSelectedItem().toString(),txtDia.getSelectedItem().toString(),txtTiempo.getText(),txtEstado.getText(),txtNumero.getText());
                jCrearOrden.setVisible(false);
            }
        });
        cancelar.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){
                jCrearOrden.setVisible(false);
            }
        });
    }
    
    static public void modificarOrden(final ArrayList<Cliente> clientes1, final ArrayList<Orden> ordenes, final int pos){
        Orden o = ordenes.get(pos);
        final JFrame jModOrden = new JFrame("Modificando Orden: "+o.id);
        jModOrden.setSize(500, 300);
        jModOrden.setVisible(true);
        Panel panelPrin=new Panel(new GridLayout(7, 1));
        Panel panelpais=new Panel(new GridLayout(1, 2));
        Panel panelciud=new Panel(new GridLayout(1, 2));
        Panel paneltiem=new Panel(new GridLayout(1, 2));
        Panel panelnum=new Panel(new GridLayout(1, 2));
        Panel panelestado=new Panel(new GridLayout(1, 2));
        Panel panelclien=new Panel(new GridLayout(1, 2));
        Panel panelboton=new Panel(new GridLayout(1, 2));
        Label labelpais=new Label("Pais:", Label.CENTER);
        Label labelciud=new Label("Ciudad:", Label.CENTER);
        Label labeltiem=new Label("Tiempo Entrega:", Label.CENTER);
        Label labelnum=new Label("# Rastreo:", Label.CENTER);
        Label labelclien=new Label("Cliente:", Label.CENTER);
        Label labelestado=new Label("Estado:", Label.CENTER);
        Button guardar=new Button("Guardar");
        Button cancelar=new Button("Cancelar");
        final TextField txtPais=new TextField(o.pais, 20);
        final TextField txtCiudad=new TextField(o.ciudad, 20);
        final TextField txtTiempo=new TextField(o.tiempo, 20);
        final TextField txtNumero=new TextField(o.numero, 20);
        final TextField txtEstado=new TextField(o.estado, 20);
        final JComboBox clientes=new JComboBox();
        for(Cliente c : clientes1)
            clientes.addItem(c.nombre);
        //final Cliente asignado = clientes1.get(clientes.getSelectedIndex());
        panelpais.add(labelpais);
        panelpais.add(txtPais);
        panelciud.add(labelciud);
        panelciud.add(txtCiudad);
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
        panelPrin.add(paneltiem);
        panelPrin.add(panelnum);
        panelPrin.add(panelestado);
        panelPrin.add(panelclien);
        panelPrin.add(panelboton);
        jModOrden.add(panelPrin);
        guardar.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){
                Orden mod = new Orden(pos+1,txtPais.getText(),txtCiudad.getText(),txtTiempo.getText(),txtNumero.getText(),txtEstado.getText(),clientes1.get(clientes.getSelectedIndex()).id);
                ordenes.remove(pos);
                ordenes.add(pos, mod);
                jModOrden.setVisible(false);
            }
        });
        cancelar.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){
                jModOrden.setVisible(false);
            }
        });
    }
    public static void nuevaOrden(String id_cliente,String pais, String ciudad, String año, String mes, String dia, String tiempo, String estado, String numero){
        try {
            Connection con=connect.Conexion_SQL();
            Statement sentencia=con.createStatement();
            //String query="INSERT INTO cliente VALUES(1, '"+ nombre+"','"+cedula+"','"+ruc+"','"+emp+"','"+telf+"')";
            String query="INSERT INTO orden VALUES("+(int)(Math.random()*100)+","+id_cliente+",'"+pais+"','"+ciudad+"','"+año+"-"+mes+"-"+dia+"',"+tiempo+",'"+estado+"','"+numero+"');";
            sentencia.executeUpdate(query);
        }catch(SQLException e){
            JOptionPane.showMessageDialog(null, "Error dato");
        }
        //return fa;
    }
    static public void eliminarOrden(final ArrayList<Orden> ordenes, final int pos){  
        Orden o = ordenes.get(pos);
        final JFrame jElimOrden = new JFrame("Eliminacion de Ordenes");
        jElimOrden.setSize(500, 300);
        jElimOrden.setVisible(true);
        Panel panelPrin=new Panel(new GridLayout(7, 1));
        Panel panelpais=new Panel(new GridLayout(1, 2));
        Panel panelciud=new Panel(new GridLayout(1, 2));
        Panel paneltiem=new Panel(new GridLayout(1, 2));
        Panel panelnum=new Panel(new GridLayout(1, 2));
        Panel panelestado=new Panel(new GridLayout(1, 2));
        Panel panelclien=new Panel(new GridLayout(1, 2));
        Panel panelboton=new Panel(new GridLayout(1, 2));
        Label labelpais=new Label("Pais:", Label.CENTER);
        Label labelciud=new Label("Ciudad:", Label.CENTER);
        Label labeltiem=new Label("Tiempo Entrega:", Label.CENTER);
        Label labelnum=new Label("# Rastreo:", Label.CENTER);
        Label labelestado=new Label("Estado:", Label.CENTER);
        Button guardar=new Button("Eliminar");
        Button cancelar=new Button("Cancelar");
        Label txtPais=new Label(o.pais, Label.CENTER);
        Label txtCiudad=new Label(o.ciudad, Label.CENTER);
        Label txtTiempo=new Label(o.tiempo, Label.CENTER);
        Label txtNumero=new Label(o.numero, Label.CENTER);
        Label txtEstado=new Label(o.estado, Label.CENTER);
        panelpais.add(labelpais);
        panelpais.add(txtPais);
        panelciud.add(labelciud);
        panelciud.add(txtCiudad);
        paneltiem.add(labeltiem);
        paneltiem.add(txtTiempo);
        panelnum.add(labelnum);
        panelnum.add(txtNumero);
        panelestado.add(labelestado);
        panelestado.add(txtEstado);
        panelboton.add(guardar);
        panelboton.add(cancelar);
        panelPrin.add(panelpais);
        panelPrin.add(panelciud);
        panelPrin.add(paneltiem);
        panelPrin.add(panelnum);
        panelPrin.add(panelestado);
        panelPrin.add(panelclien);
        panelPrin.add(panelboton);
        jElimOrden.add(panelPrin);
        guardar.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){
                ordenes.remove(pos);
                jElimOrden.setVisible(false);
            }
        });
        cancelar.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){
                jElimOrden.setVisible(false);
            }
        });
    }
    
    public Object[] arreglo(){
        Object[] arreglo={id, pais, ciudad, tiempo, numero, estado, cliente};
        return arreglo;
    }  

}
