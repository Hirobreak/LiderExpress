
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
    String estado;
    
    Caja(int ids, int dim, int kilos, String estad, int nume){
        id=ids;
        num=nume;
        dimension=dim;
        peso=kilos;
        estado=estad;    
    }
    
    static public void crearCaja(final ArrayList<Caja> cajas){
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
        final TextField txtNum=new TextField("#", 20);
        final TextField txtDim=new TextField("Nombre", 20);
        final TextField txtPeso=new TextField("Cargo", 20);
        final JComboBox bEstado=new JComboBox();
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
                Caja c = new Caja(cajas.size()+1,Integer.parseInt(txtDim.getText()),Integer.parseInt(txtPeso.getText()),bEstado.getSelectedItem().toString(),Integer.parseInt(txtNum.getText()));
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