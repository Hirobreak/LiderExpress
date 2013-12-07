
package liderexpress;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import javax.swing.*;
import javax.swing.border.*;

public class Trabajador {
    int id;
    String nombre;
    String cargo;
    int cedula;
    int telefono;
    float salario;
    String mail;
    
    Trabajador(int ids, String name, String puesto, int ced, int telf, int sal, String correo){
        id=ids;
        nombre=name;
        cargo=puesto;
        cedula=ced;
        telefono=telf;
        salario=sal;
        mail=correo;
    }
    
    static public void crearTrab(){
        final JFrame jCrearT = new JFrame("Creacion de Trabajador");
        jCrearT.setSize(500, 300);
        jCrearT.setVisible(true);
        Panel panelPrin=new Panel(new GridLayout(7, 1));
        Panel panelnombre=new Panel(new GridLayout(1, 2));
        Panel panelced=new Panel(new GridLayout(1, 2));
        Panel panelcargo=new Panel(new GridLayout(1, 2));
        Panel panelsal=new Panel(new GridLayout(1, 2));
        Panel panelcorreo=new Panel(new GridLayout(1, 2));
        Panel paneltelf=new Panel(new GridLayout(1, 3));
        Panel panelboton=new Panel(new GridLayout(1, 2));
        Label labelnom=new Label("Nombre:", Label.CENTER);
        Label labelcargo=new Label("Cargo:", Label.CENTER);
        Label labelced=new Label("Cedula:", Label.CENTER);
        Label labelsal=new Label("Salario: $", Label.CENTER);
        Label labelcorreo=new Label("Correo:", Label.CENTER);
        Label labeltelf=new Label("Telefonos:", Label.CENTER);
        Button guardar=new Button("Guardar");
        Button cancelar=new Button("Cancelar");
        TextField txtNombre=new TextField("Nombre", 20);
        TextField txtCargo=new TextField("Cargo", 20);
        TextField txtCedula=new TextField("Cedula", 20);
        TextField txtSalario=new TextField("$", 20);
        TextField txtTelf=new TextField("00000000", 20);
        TextField txtCorreo=new TextField("ejemplo@yatusabe.com", 20);
        panelnombre.add(labelnom);
        panelnombre.add(txtNombre);
        panelced.add(labelced);
        panelced.add(txtCedula);
        panelcargo.add(labelcargo);
        panelcargo.add(txtCargo);
        panelsal.add(labelsal);
        panelsal.add(txtSalario);
        paneltelf.add(labeltelf);
        paneltelf.add(txtTelf);
        panelcorreo.add(labelcorreo);
        panelcorreo.add(txtCorreo);
        panelboton.add(guardar);
        panelboton.add(cancelar);
        panelPrin.add(panelnombre);
        panelPrin.add(panelced);
        panelPrin.add(panelcargo);
        panelPrin.add(panelsal);
        panelPrin.add(paneltelf);
        panelPrin.add(panelcorreo);
        panelPrin.add(panelboton);
        jCrearT.add(panelPrin);
        guardar.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){
                jCrearT.setVisible(false);
            }
        });
        cancelar.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){
                jCrearT.setVisible(false);
            }
        });
        
        
    }
    static public void modTrab(Trabajador tr){
        final JFrame jModT = new JFrame("Modificacion de Trabajador");
        jModT.setSize(500, 300);
        jModT.setVisible(true);
        Panel panelPrin=new Panel(new GridLayout(7, 1));
        Panel panelnombre=new Panel(new GridLayout(1, 2));
        Panel panelced=new Panel(new GridLayout(1, 2));
        Panel panelcargo=new Panel(new GridLayout(1, 2));
        Panel panelsal=new Panel(new GridLayout(1, 2));
        Panel panelcorreo=new Panel(new GridLayout(1, 2));
        Panel paneltelf=new Panel(new GridLayout(1, 3));
        Panel panelboton=new Panel(new GridLayout(1, 2));
        Label labelnom=new Label("Nombre:", Label.CENTER);
        Label labelcargo=new Label("Cargo:", Label.CENTER);
        Label labelced=new Label("Cedula:", Label.CENTER);
        Label labelsal=new Label("Salario: $", Label.CENTER);
        Label labelcorreo=new Label("Correo:", Label.CENTER);
        Label labeltelf=new Label("Telefonos:", Label.CENTER);
        Button guardar=new Button("Guardar");
        Button cancelar=new Button("Cancelar");
        TextField txtNombre=new TextField(tr.nombre, 20);
        TextField txtCargo=new TextField(tr.cargo, 20);
        TextField txtCedula=new TextField(String.valueOf(tr.cedula), 20);
        TextField txtSalario=new TextField(String.valueOf(tr.salario), 20);
        TextField txtTelf=new TextField(String.valueOf(tr.telefono), 20);
        TextField txtCorreo=new TextField(tr.mail, 20);
        panelnombre.add(labelnom);
        panelnombre.add(txtNombre);
        panelced.add(labelced);
        panelced.add(txtCedula);
        panelcargo.add(labelcargo);
        panelcargo.add(txtCargo);
        panelsal.add(labelsal);
        panelsal.add(txtSalario);
        paneltelf.add(labeltelf);
        paneltelf.add(txtTelf);
        panelcorreo.add(labelcorreo);
        panelcorreo.add(txtCorreo);
        panelboton.add(guardar);
        panelboton.add(cancelar);
        panelPrin.add(panelnombre);
        panelPrin.add(panelced);
        panelPrin.add(panelcargo);
        panelPrin.add(panelsal);
        panelPrin.add(paneltelf);
        panelPrin.add(panelcorreo);
        panelPrin.add(panelboton);
        jModT.add(panelPrin);
        guardar.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){
                jModT.setVisible(false);
            }
        });
        cancelar.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){
                jModT.setVisible(false);
            }
        });
        
        
    }
    public Object[] arreglo(){
        Object[] arreglo={nombre, cargo, cedula, telefono, salario, mail};
        return arreglo;
    }
}