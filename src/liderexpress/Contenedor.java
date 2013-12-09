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
    int idImpo;
    
    Contenedor(int ids, int dim, int kilos, int estad, int impo){
        id=ids;
        dimension=dim;
        peso=kilos;
        estado=estad;
        idImpo=impo;
    }
    
    static public void crearCont(){
        final JFrame jCrearCont = new JFrame("Creacion de Con");
        jCrearCont.setSize(500, 300);
        jCrearCont.setVisible(true);
        Panel panelPrin=new Panel(new GridLayout(5, 1));
        Panel panelDim=new Panel(new GridLayout(1, 2));
        Panel panelPeso=new Panel(new GridLayout(1, 2));
        Panel panelEstado=new Panel(new GridLayout(1, 2));
        Panel panelImpo=new Panel(new GridLayout(1, 2));
        Panel panelboton=new Panel(new GridLayout(1, 2));
        Label labelDim=new Label("Dimensiones:", Label.CENTER);
        Label labelPeso=new Label("Peso:", Label.CENTER);
        Label labelEstado=new Label("Estado:", Label.CENTER);
        Label labelImpo=new Label("Importación: ", Label.CENTER);
        Button guardar=new Button("Guardar");
        Button cancelar=new Button("Cancelar");
        TextField txtDim=new TextField("Nombre", 20);
        TextField txtPeso=new TextField("Cargo", 20);
        JComboBox bEstado=new JComboBox();
        JComboBox impos=new JComboBox();
        bEstado.addItem("Enviado");
        bEstado.addItem("Recibido");
        bEstado.addItem("Desmontado");
        panelDim.add(labelDim);
        panelDim.add(txtDim);
        panelPeso.add(labelPeso);
        panelPeso.add(txtPeso);
        panelEstado.add(labelEstado);
        panelEstado.add(bEstado);
        panelImpo.add(labelImpo);
        panelImpo.add(impos);
        panelboton.add(guardar);
        panelboton.add(cancelar);
        panelPrin.add(panelDim);
        panelPrin.add(panelPeso);
        panelPrin.add(panelEstado);
        panelPrin.add(panelImpo);
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