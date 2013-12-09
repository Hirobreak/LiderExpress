package liderexpress;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import javax.swing.*;
import javax.swing.border.*;

public class Contenedor {
    int id;
    String dimension;
    int peso;
    String estado;
    int idImpo;
    
    Contenedor(int ids, String dim, int kilos, String estad, int impo){
        id=ids;
        dimension=dim;
        peso=kilos;
        estado=estad;
        idImpo=impo;
    }
    
    static public void crearCont(final ArrayList<Contenedor>conts,final ArrayList<Importacion>imps){
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
        final TextField txtDim=new TextField("", 20);
        final TextField txtPeso=new TextField("", 20);
        final JComboBox bEstado=new JComboBox();
        final JComboBox impos=new JComboBox();
        for(Importacion i : imps)
            impos.addItem(i.id);
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
                Contenedor c = new Contenedor(conts.size()+1,txtDim.getText(),Integer.parseInt(txtPeso.getText()),bEstado.getSelectedItem().toString(),imps.get(impos.getSelectedIndex()).id);
                conts.add(c);
                jCrearCont.setVisible(false);
            }
        });
        cancelar.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){
                jCrearCont.setVisible(false);
            }
        });
        
        
    }
    public Object[] arreglo(){
        Object[] arreglo={id, dimension, peso, estado, idImpo};
        return arreglo;
    }   
}