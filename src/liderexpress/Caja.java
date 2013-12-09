
package liderexpress;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import javax.swing.*;
import javax.swing.border.*;

public class Caja {
    int id;
    int dimension;
    int peso;
    int num;
    int estado;
    
    Caja(int ids, int dim, int kilos, int estad, int nume){
        id=ids;
        num=nume;
        dimension=dim;
        peso=kilos;
        estado=estad;    
    }
    
    static public void crearCaja(){
        final JFrame jCrearCaja = new JFrame("Creacion de Con");
        jCrearCaja.setSize(500, 300);
        jCrearCaja.setVisible(true);
        Panel panelPrin=new Panel(new GridLayout(4, 1));
        Panel panelNum=new Panel(new GridLayout(1, 2));
        Panel panelDim=new Panel(new GridLayout(1, 2));
        Panel panelPeso=new Panel(new GridLayout(1, 2));
        Panel panelEstado=new Panel(new GridLayout(1, 2));
        Panel panelboton=new Panel(new GridLayout(1, 2));
        Label labelNum=new Label("Numero", Label.CENTER);
        Label labelDim=new Label("Dimensiones:", Label.CENTER);
        Label labelPeso=new Label("Peso:", Label.CENTER);
        Label labelEstado=new Label("Estado:", Label.CENTER);
        Button guardar=new Button("Guardar");
        Button cancelar=new Button("Cancelar");
        TextField txtNum=new TextField("#", 20);
        TextField txtDim=new TextField("Nombre", 20);
        TextField txtPeso=new TextField("Cargo", 20);
        JComboBox bEstado=new JComboBox();
        bEstado.addItem("Enviado");
        bEstado.addItem("Recibido");
        bEstado.addItem("Desmontado");
        panelNum.add(labelNum);
        panelNum.add(txtNum);
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
        jCrearCaja.add(panelPrin);
        guardar.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){
                jCrearCaja.setVisible(false);
            }
        });
        cancelar.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){
                jCrearCaja.setVisible(false);
            }
        });
        
        
    }
    
}