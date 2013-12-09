
package liderexpress;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import javax.swing.*;
import javax.swing.border.*;

public class Orden{
    int id;
    String pais;
    String ciudad;
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
        final TextField txtPais=new TextField("Pais de origen", 20);
        final TextField txtCiudad=new TextField("Ciudad de origen", 20);
        final TextField txtTiempo=new TextField("Tiempo de entrega", 20);
        final TextField txtNumero=new TextField("Numero de Rastreo", 20);
        final TextField txtEstado=new TextField("Estado", 20);
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
        jCrearOrden.add(panelPrin);
        guardar.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){
                Orden o = new Orden(ordenes.size()+1,txtPais.getText(),txtCiudad.getText(),txtTiempo.getText(),txtNumero.getText(),txtEstado.getText(),clientes1.get(clientes.getSelectedIndex()).id);
                ordenes.add(o);
                jCrearOrden.setVisible(false);
            }
        });
        cancelar.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){
                jCrearOrden.setVisible(false);
            }
        });
    }
    
    static public void modificarOrden(Orden o){ 
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
        TextField txtPais=new TextField(o.pais, 20);
        TextField txtCiudad=new TextField(o.ciudad, 20);
        TextField txtTiempo=new TextField(o.tiempo, 20);
        TextField txtNumero=new TextField(o.numero, 20);
        TextField txtEstado=new TextField(o.estado, 20);
        JComboBox clientes=new JComboBox();
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
                jModOrden.setVisible(false);
            }
        });
        cancelar.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){
                jModOrden.setVisible(false);
            }
        });
    }
    
    static public void eliminarOrden(ArrayList<Orden> ordenes){  
        JFrame jElimOrden = new JFrame("Eliminacion de Ordenes");
    }
    
    public Object[] arreglo(){
        Object[] arreglo={id, pais, ciudad, tiempo, numero, estado, cliente};
        return arreglo;
    }  

}
