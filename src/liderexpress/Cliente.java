
package liderexpress;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import javax.swing.*;
import javax.swing.border.*;

public class Cliente{
    int id;
    String nombre;
    String ruc;
    String cedula;
    String compa;
    int telf1;
    int telf2;


    Cliente(int id, String nom, String ruc, String ced, String comp, int t1, int t2){
        this.id = id;
        this.nombre = nom;
        this.ruc = ruc;
        this.cedula= ced;
        this.compa = comp;
        this.telf1=t1;
        this.telf2=t2;
    }
    
    static public void crearCliente(){ 
        final JFrame jCrearCliente = new JFrame("Creacion de Cliente");
        jCrearCliente.setSize(500, 300);
        jCrearCliente.setVisible(true);
        Panel panelPrin=new Panel(new GridLayout(6, 1));
        Panel panelnombre=new Panel(new GridLayout(1, 2));
        Panel panelruc=new Panel(new GridLayout(1, 2));
        Panel panelcedula=new Panel(new GridLayout(1, 2));
        Panel panelcom=new Panel(new GridLayout(1, 2));
        Panel paneltelf=new Panel(new GridLayout(1, 3));
        Panel panelboton=new Panel(new GridLayout(1, 2));
        Label labelnom=new Label("Nombre:", Label.CENTER);
        Label labelruc=new Label("RUC:", Label.CENTER);
        Label labelced=new Label("Cedula:", Label.CENTER);
        Label labelcom=new Label("Compañia:", Label.CENTER);
        Label labeltelf=new Label("Telefonos:", Label.CENTER);
        Button guardar=new Button("Guardar");
        Button cancelar=new Button("Cancelar");
        TextField txtNombre=new TextField("Nombre", 20);
        TextField txtRuc=new TextField("RUC", 20);
        TextField txtCedula=new TextField("Cedula", 20);
        TextField txtCompa=new TextField("Compañia", 20);
        TextField txtTelf1=new TextField("00000000", 20);
        TextField txtTelf2=new TextField("00000000", 20);
        panelnombre.add(labelnom);
        panelnombre.add(txtNombre);
        panelcedula.add(labelced);
        panelcedula.add(txtCedula);
        panelcom.add(labelcom);
        panelcom.add(txtCompa);
        panelruc.add(labelruc);
        panelruc.add(txtRuc);
        paneltelf.add(labeltelf);
        paneltelf.add(txtTelf1);
        paneltelf.add(txtTelf2);
        panelboton.add(guardar);
        panelboton.add(cancelar);
        panelPrin.add(panelnombre);
        panelPrin.add(panelcedula);
        panelPrin.add(panelcom);
        panelPrin.add(panelruc);
        panelPrin.add(paneltelf);
        panelPrin.add(panelboton);
        jCrearCliente.add(panelPrin);
        guardar.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){
                jCrearCliente.setVisible(false);
            }
        });
        cancelar.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){
                jCrearCliente.setVisible(false);
            }
        });
    }
    
    static void modificarCliente(Cliente c){ 
        final JFrame jModCliente = new JFrame("Modificando Cliente: "+c.id);
        jModCliente.setSize(500, 300);
        jModCliente.setVisible(true);
        Panel panelPrin=new Panel(new GridLayout(6, 1));
        Panel panelnombre=new Panel(new GridLayout(1, 2));
        Panel panelruc=new Panel(new GridLayout(1, 2));
        Panel panelcedula=new Panel(new GridLayout(1, 2));
        Panel panelcom=new Panel(new GridLayout(1, 2));
        Panel paneltelf=new Panel(new GridLayout(1, 3));
        Panel panelboton=new Panel(new GridLayout(1, 2));
        Label labelnom=new Label("Nombre:", Label.CENTER);
        Label labelruc=new Label("RUC:", Label.CENTER);
        Label labelced=new Label("Cedula:", Label.CENTER);
        Label labelcom=new Label("Compañia:", Label.CENTER);
        Label labeltelf=new Label("Telefonos:", Label.CENTER);
        Button guardar=new Button("Guardar");
        Button cancelar=new Button("Cancelar");
        TextField txtNombre=new TextField(c.nombre, 20);
        TextField txtRuc=new TextField(String.valueOf(c.ruc), 20);
        TextField txtCedula=new TextField(String.valueOf(c.cedula), 20);
        TextField txtCompa=new TextField(c.compa, 20);
        TextField txtTelf1=new TextField(String.valueOf(c.telf1), 20);
        TextField txtTelf2=new TextField(String.valueOf(c.telf2), 20);
        panelnombre.add(labelnom);
        panelnombre.add(txtNombre);
        panelcedula.add(labelced);
        panelcedula.add(txtCedula);
        panelcom.add(labelcom);
        panelcom.add(txtCompa);
        panelruc.add(labelruc);
        panelruc.add(txtRuc);
        paneltelf.add(labeltelf);
        paneltelf.add(txtTelf1);
        paneltelf.add(txtTelf2);
        panelboton.add(guardar);
        panelboton.add(cancelar);
        panelPrin.add(panelnombre);
        panelPrin.add(panelcedula);
        panelPrin.add(panelcom);
        panelPrin.add(panelruc);
        panelPrin.add(paneltelf);
        panelPrin.add(panelboton);
        jModCliente.add(panelPrin);
        guardar.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){
                jModCliente.setVisible(false);
            }
        });
        cancelar.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){
                jModCliente.setVisible(false);
            }
        });
    }
    
    static void eliminarCliente(ArrayList<Cliente> clientes){  
        JFrame jElimCliente = new JFrame("Eliminacion de Clientes");
    }
    
    public Object[] arreglo(){
        Object[] arreglo={id, nombre, cedula, compa, ruc, telf1, telf2};
        return arreglo;
    }   

}
