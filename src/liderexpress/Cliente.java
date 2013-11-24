
package liderexpress;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import javax.swing.*;
import javax.swing.border.*;

public class Cliente{
    String id;
    String nombre;
    String ruc;
    String cedula;
    String compa;


    Cliente(String id, String nom, String ruc, String ced, String comp){
        this.id = id;
        this.nombre = nom;
        this.ruc = ruc;
        this.cedula= ced;
        this.compa = comp;
    }
    
    public void crearCliente(){ 
        final JFrame jCrearCliente = new JFrame("Creacion de Cliente");
        jCrearCliente.setSize(500, 300);
        jCrearCliente.setVisible(true);
        Panel panelPrin=new Panel(new GridLayout(6, 1));
        Panel panelid=new Panel(new GridLayout(1, 2));
        Panel panelnombre=new Panel(new GridLayout(1, 2));
        Panel panelruc=new Panel(new GridLayout(1, 2));
        Panel panelcedula=new Panel(new GridLayout(1, 2));
        Panel panelcom=new Panel(new GridLayout(1, 2));
        Panel panelboton=new Panel(new GridLayout(1, 2));
        Label labelid=new Label("ID:", Label.CENTER);
        Label labelnom=new Label("Nombre:", Label.CENTER);
        Label labelruc=new Label("RUC:", Label.CENTER);
        Label labelced=new Label("Cedula:", Label.CENTER);
        Label labelcom=new Label("Compañia:", Label.CENTER);
        Button guardar=new Button("Guardar");
        Button cancelar=new Button("Cancelar");
        TextField txtId=new TextField("ID", 20);
        TextField txtNombre=new TextField("Nombre", 20);
        TextField txtRuc=new TextField("RUC", 20);
        TextField txtCedula=new TextField("Cedula", 20);
        TextField txtCompa=new TextField("Compañia", 20);
        panelid.add(labelid);
        panelid.add(txtId);
        panelnombre.add(labelnom);
        panelnombre.add(txtNombre);
        panelcedula.add(labelced);
        panelcedula.add(txtCedula);
        panelcom.add(labelcom);
        panelcom.add(txtCompa);
        panelruc.add(labelruc);
        panelruc.add(txtRuc);
        panelboton.add(guardar);
        panelboton.add(cancelar);
        panelPrin.add(panelid);
        panelPrin.add(panelnombre);
        panelPrin.add(panelcedula);
        panelPrin.add(panelcom);
        panelPrin.add(panelruc);
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
    
    public void modificarCliente(Cliente c){ 
        final JFrame jModCliente = new JFrame("Modificando Cliente: "+c.id);
        jModCliente.setSize(500, 300);
        jModCliente.setVisible(true);
        Panel panelPrin=new Panel(new GridLayout(6, 1));
        Panel panelid=new Panel(new GridLayout(1, 2));
        Panel panelnombre=new Panel(new GridLayout(1, 2));
        Panel panelruc=new Panel(new GridLayout(1, 2));
        Panel panelcedula=new Panel(new GridLayout(1, 2));
        Panel panelcom=new Panel(new GridLayout(1, 2));
        Panel panelboton=new Panel(new GridLayout(1, 2));
        Label labelid=new Label("ID:", Label.CENTER);
        Label labelnom=new Label("Nombre:", Label.CENTER);
        Label labelruc=new Label("RUC:", Label.CENTER);
        Label labelced=new Label("Cedula:", Label.CENTER);
        Label labelcom=new Label("Compañia:", Label.CENTER);
        Button guardar=new Button("Guardar");
        Button cancelar=new Button("Cancelar");
        TextField txtId=new TextField(c.id, 20);
        TextField txtNombre=new TextField(c.nombre, 20);
        TextField txtRuc=new TextField(c.ruc, 20);
        TextField txtCedula=new TextField(c.cedula, 20);
        TextField txtCompa=new TextField(c.compa, 20);
        panelid.add(labelid);
        panelid.add(txtId);
        panelnombre.add(labelnom);
        panelnombre.add(txtNombre);
        panelcedula.add(labelced);
        panelcedula.add(txtCedula);
        panelcom.add(labelcom);
        panelcom.add(txtCompa);
        panelruc.add(labelruc);
        panelruc.add(txtRuc);
        panelboton.add(guardar);
        panelboton.add(cancelar);
        panelPrin.add(panelid);
        panelPrin.add(panelnombre);
        panelPrin.add(panelcedula);
        panelPrin.add(panelcom);
        panelPrin.add(panelruc);
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
    
    public void eliminarCliente(ArrayList<Cliente> clientes){  
        JFrame jElimCliente = new JFrame("Eliminacion de Clientes");
    }
    

}
