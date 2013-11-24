
package liderexpress;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;
import javax.swing.border.*;

public class NuevoCliente extends JFrame implements ActionListener {
        
    Button guardar=new Button("Guardar");
    Button cancelar=new Button("Cancelar");
    TextField id=new TextField("ID", 20);
    TextField nombre=new TextField("Nombre", 20);
    TextField ruc=new TextField("RUC", 20);
    TextField cedula=new TextField("Cedula", 20);
    TextField compa=new TextField("Compañia", 20);

    NuevoCliente(){
        this.setTitle("Bienvenido Usuario XYZ");
        this.setSize(500, 300);
        this.setVisible(true);
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
        panelid.add(labelid);
        panelid.add(id);
        panelnombre.add(labelnom);
        panelnombre.add(nombre);
        panelcedula.add(labelced);
        panelcedula.add(cedula);
        panelcom.add(labelcom);
        panelcom.add(compa);
        panelruc.add(labelruc);
        panelruc.add(ruc);
        panelboton.add(guardar);
        panelboton.add(cancelar);
        panelPrin.add(panelid);
        panelPrin.add(panelnombre);
        panelPrin.add(panelcedula);
        panelPrin.add(panelcom);
        panelPrin.add(panelruc);
        panelPrin.add(panelboton);
        add(panelPrin);
        guardar.addActionListener(this);
        cancelar.addActionListener(this);
        
    }
    
    @Override
    public void actionPerformed(ActionEvent ae){
        this.dispose();
    }
}
