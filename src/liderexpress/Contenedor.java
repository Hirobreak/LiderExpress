package liderexpress;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import javax.swing.*;
import javax.swing.border.*;

public class Contenedor {
    int id;
    int dimension;
    int peso;
    int estado;
    
    Contenedor(int ids, int dim, int kilos, int estad){
        id=ids;
        dimension=dim;
        peso=kilos;
        estado=estad;    
    }
    
    static public void crearCont(){
        final JFrame jCrearCont = new JFrame("Creacion de Con");
        jCrearCont.setSize(500, 300);
        jCrearCont.setVisible(true);
        Panel panelPrin=new Panel(new GridLayout(4, 1));
        Panel panelDim=new Panel(new GridLayout(1, 2));
        Panel panelPeso=new Panel(new GridLayout(1, 2));
        Panel panelEstado=new Panel(new GridLayout(1, 2));
        Panel panelboton=new Panel(new GridLayout(1, 2));
        Label labelDim=new Label("Dimensiones:", Label.CENTER);
        Label labelPeso=new Label("Peso:", Label.CENTER);
        Label labelEstado=new Label("Estado:", Label.CENTER);
        Button guardar=new Button("Guardar");
        Button cancelar=new Button("Cancelar");
        TextField txtDim=new TextField("Nombre", 20);
        TextField txtPeso=new TextField("Cargo", 20);
        JComboBox bEstado=new JComboBox();
        bEstado.addItem("Enviado");
        bEstado.addItem("Recibido");
        bEstado.addItem("Desmontado");
        panelDim.add(labelDim);
        panelDim.add(txtDim);
        panelPeso.add(labelPeso);
        panelPeso.add(txtPeso);
        panelEstado.add(labelEstado);
        panelEstado.add(bEstado);
        panelboton.add(guardar);
        panelboton.add(cancelar);
        panelPrin.add(panelDim);
        panelPrin.add(panelPeso);
        panelPrin.add(panelEstado);
        panelPrin.add(panelboton);
        jCrearCont.add(panelPrin);
        guardar.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){
                jCrearCont.setVisible(false);
            }
        });
        cancelar.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){
                jCrearCont.setVisible(false);
            }
        });
        
        
    }
    
}