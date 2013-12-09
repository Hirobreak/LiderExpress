
package liderexpress;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import javax.swing.*;
import javax.swing.border.*;

public class Importacion {
    int id;
    int trabajador;
    int prov;
    int dia;
    int mes;
    int año;
    
    Importacion(int ids, int trab, int pro, int d, int m, int a){
        id=ids;
        trabajador=trab;
        prov=pro;
        dia=d;
        mes=m;
        año=a;
    }
    
    static public void crearImpo(){
        final JFrame jCrearImpo = new JFrame("Creacion de Importación");
        jCrearImpo.setSize(500, 300);
        jCrearImpo.setVisible(true);
        Panel panelPrin=new Panel(new GridLayout(5, 1));
        Panel panelTrab=new Panel(new GridLayout(1, 2));
        Panel panelProv=new Panel(new GridLayout(1, 2));
        Panel panelFecha=new Panel(new GridLayout(1, 4));
        Panel panelCont=new Panel(new GridLayout(1, 2));
        Panel panelboton=new Panel(new GridLayout(1, 2));
        Label labelTrab=new Label("Supervisor:", Label.CENTER);
        Label labelProv=new Label("Proveedor", Label.CENTER);
        Label labelFecha=new Label("Fecha:", Label.CENTER);
        Button guardar=new Button("Guardar");
        Button cancelar=new Button("Cancelar");
        JComboBox superv=new JComboBox();
        JComboBox provee=new JComboBox();
        JComboBox cdia=new JComboBox();
        JComboBox cmes=new JComboBox();
        JComboBox caño=new JComboBox();
        for (int i=2000; i<2015; i++){
            caño.addItem(i);
        }
        for (int j=1; j<13; j++){
            cmes.addItem(j);
        }
        for (int i=1; i<32; i++){
            cdia.addItem(i);
        }
        panelTrab.add(labelTrab);
        panelTrab.add(superv);
        panelProv.add(labelProv);
        panelProv.add(provee);
        panelFecha.add(labelFecha);
        panelFecha.add(cdia);
        panelFecha.add(cmes);
        panelFecha.add(caño);
        panelboton.add(guardar);
        panelboton.add(cancelar);
        panelPrin.add(panelTrab);
        panelPrin.add(panelProv);
        panelPrin.add(panelFecha);
        panelPrin.add(panelboton);
        jCrearImpo.add(panelPrin);
        guardar.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){
                jCrearImpo.setVisible(false);
            }
        });
        cancelar.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){
                jCrearImpo.setVisible(false);
            }
        });
    }
    
    static public void modImpo(Importacion im){
        final JFrame jModImpo = new JFrame("Modificacion de Importación");
        jModImpo.setSize(500, 300);
        jModImpo.setVisible(true);
        Panel panelPrin=new Panel(new GridLayout(5, 1));
        Panel panelTrab=new Panel(new GridLayout(1, 2));
        Panel panelProv=new Panel(new GridLayout(1, 2));
        Panel panelFecha=new Panel(new GridLayout(1, 4));
        Panel panelCont=new Panel(new GridLayout(1, 2));
        Panel panelboton=new Panel(new GridLayout(1, 2));
        Label labelTrab=new Label("Supervisor:", Label.CENTER);
        Label labelProv=new Label("Proveedor", Label.CENTER);
        Label labelFecha=new Label("Fecha:", Label.CENTER);
        Button guardar=new Button("Guardar");
        Button cancelar=new Button("Cancelar");
        Button bCont=new Button("Contenedores");
        JComboBox superv=new JComboBox();
        superv.addItem(im.trabajador);
        JComboBox provee=new JComboBox();
        provee.addItem(im.prov);
        JComboBox cdia=new JComboBox();
        cdia.addItem(im.dia);
        JComboBox cmes=new JComboBox();
        cmes.addItem(im.mes);
        JComboBox caño=new JComboBox();
        caño.addItem(im.año);
        for (int i=2000; i<2015; i++){
            caño.addItem(i);
        }
        for (int j=1; j<13; j++){
            cmes.addItem(j);
        }
        for (int i=1; i<32; i++){
            cdia.addItem(i);
        }
        panelTrab.add(labelTrab);
        panelTrab.add(superv);
        panelProv.add(labelProv);
        panelProv.add(provee);
        panelFecha.add(labelFecha);
        panelFecha.add(cdia);
        panelFecha.add(cmes);
        panelFecha.add(caño);
        panelCont.add(bCont);
        panelboton.add(guardar);
        panelboton.add(cancelar);
        panelPrin.add(panelTrab);
        panelPrin.add(panelProv);
        panelPrin.add(panelFecha);
        panelPrin.add(panelCont);
        panelPrin.add(panelboton);
        jModImpo.add(panelPrin);
        guardar.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){
                jModImpo.setVisible(false);
            }
        });
        cancelar.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){
                jModImpo.setVisible(false);
            }
        });
    }
    public Object[] arreglo(){
        Object[] arreglo={id, trabajador, prov, dia, mes, año};
        return arreglo;
    }
}