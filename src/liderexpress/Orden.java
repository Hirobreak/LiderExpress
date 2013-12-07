
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
    
    static public void crearOrden(){ 
        final JFrame jCrearOrden = new JFrame("Creacion de Orden");
        jCrearOrden.setSize(500, 300);
        jCrearOrden.setVisible(true);
        Panel panelPrin=new Panel(new GridLayout(8, 1));
        Panel panelpais=new Panel(new GridLayout(1, 2));
        Panel panelciud=new Panel(new GridLayout(1, 2));
        Panel paneltiem=new Panel(new GridLayout(1, 2));
        Panel panelnum=new Panel(new GridLayout(1, 2));
        Panel panelestado=new Panel(new GridLayout(1, 2));
        Panel panelboton=new Panel(new GridLayout(1, 2));
        Label labelpais=new Label("Pais:", Label.CENTER);
        Label labelciud=new Label("Ciudad:", Label.CENTER);
        Label labeltiem=new Label("Tiempo Entrega:", Label.CENTER);
        Label labelnum=new Label("# Rastreo:", Label.CENTER);
        Label labelestado=new Label("Estado:", Label.CENTER);
        Button cliente=new Button("Cliente");
        Button guardar=new Button("Guardar");
        Button cancelar=new Button("Cancelar");
        Button mercaderia=new Button("Agregar Mercadería");
        TextField txtId=new TextField("ID de orden", 20);
        TextField txtPais=new TextField("Pais de origen", 20);
        TextField txtCiudad=new TextField("Ciudad de origen", 20);
        TextField txtTiempo=new TextField("Tiempo de entrega", 20);
        TextField txtNumero=new TextField("Numero de Rastreo", 20);
        TextField txtEstado=new TextField("Estado", 20);
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
        panelPrin.add(mercaderia);
        panelPrin.add(cliente);
        panelPrin.add(panelboton);
        jCrearOrden.add(panelPrin);
        guardar.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){
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
        Panel panelPrin=new Panel(new GridLayout(6, 1));
        Panel panelid=new Panel(new GridLayout(1, 2));
        Panel panelpais=new Panel(new GridLayout(1, 2));
        Panel panelciud=new Panel(new GridLayout(1, 2));
        Panel paneltiem=new Panel(new GridLayout(1, 2));
        Panel panelnum=new Panel(new GridLayout(1, 2));
        Panel panelestado=new Panel(new GridLayout(1, 2));
        Panel panelcliente=new Panel(new GridLayout(1, 2));
        Panel panelboton=new Panel(new GridLayout(1, 2));
        Label labelid=new Label("ID:", Label.CENTER);
        Label labelpais=new Label("Pais:", Label.CENTER);
        Label labelciud=new Label("Ciudad:", Label.CENTER);
        Label labeltiem=new Label("Tiempo Entrega:", Label.CENTER);
        Label labelnum=new Label("# Rastreo:", Label.CENTER);
        Label labelestado=new Label("Estado:", Label.CENTER);
        Label labelcliente=new Label("ID Cliente:", Label.CENTER);
        Button guardar=new Button("Guardar");
        Button cancelar=new Button("Cancelar");
        TextField txtId=new TextField(String.valueOf(o.id), 20);
        TextField txtPais=new TextField(o.pais, 20);
        TextField txtCiudad=new TextField(o.ciudad, 20);
        TextField txtTiempo=new TextField(o.tiempo, 20);
        TextField txtNumero=new TextField(o.numero, 20);
        TextField txtEstado=new TextField(o.estado, 20);
        TextField txtCliente=new TextField(String.valueOf(o.cliente), 20);
        panelid.add(labelid);
        panelid.add(txtId);
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
        panelcliente.add(labelcliente);
        panelcliente.add(txtCliente);
        panelboton.add(guardar);
        panelboton.add(cancelar);
        panelPrin.add(panelid);
        panelPrin.add(panelpais);
        panelPrin.add(panelciud);
        panelPrin.add(paneltiem);
        panelPrin.add(panelnum);
        panelPrin.add(panelestado);
        panelPrin.add(panelcliente);
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
        Object[] arreglo={pais, ciudad, tiempo, numero, estado, "Aun no se puede referenciar"};
        return arreglo;
    }  

}
