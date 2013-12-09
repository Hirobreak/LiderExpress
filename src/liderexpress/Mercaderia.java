
package liderexpress;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import javax.swing.*;
import javax.swing.border.*;


public class Mercaderia {
    int id;
    String style;
    String mark;
    String desc;
    String comp;
    String origen;
    int idorden;
    
    Mercaderia(int ident, String estilo, String marca, String des, String compo, String orig,int idor){
        id=ident;
        style=estilo;
        mark=marca;
        desc=des;
        comp=compo;
        origen=orig;
        idorden=idor;
    }
    
    static public void crearMerc(){ 
        final JFrame jCrearMerc = new JFrame("Creacion de Mercaderia");
        jCrearMerc.setSize(500, 300);
        jCrearMerc.setVisible(true);
        Panel panelPrin=new Panel(new GridLayout(7, 1));
        Panel panelEst=new Panel(new GridLayout(1, 2));
        Panel panelMarc=new Panel(new GridLayout(1, 2));
        Panel panelDesc=new Panel(new GridLayout(1, 2));
        Panel panelComp=new Panel(new GridLayout(1, 2));
        Panel panelorig=new Panel(new GridLayout(1, 2));
        Panel panelOrd=new Panel(new GridLayout(1, 2));
        Panel panelboton=new Panel(new GridLayout(1, 2));
        Label labelEst=new Label("Estilo:", Label.CENTER);
        Label labelMarc=new Label("Marca", Label.CENTER);
        Label labelDesc=new Label("Descripción:", Label.CENTER);
        Label labelComp=new Label("Composición:", Label.CENTER);
        Label labelOrd=new Label("Pertenece a la Orden: ", Label.CENTER);
        Label labelOrg=new Label("Origen:", Label.CENTER);
        Button guardar=new Button("Guardar");
        Button cancelar=new Button("Cancelar");
        TextField txtEst=new TextField("Estilo", 20);
        TextField txtMarc=new TextField("Marca", 20);
        TextField txtDesc=new TextField("Descripción", 20);
        TextField txtComp=new TextField("Composición", 20);
        TextField txtOrg=new TextField("Origen", 20);
        JComboBox listaOrd=new JComboBox();
        panelEst.add(labelEst);
        panelEst.add(txtEst);
        panelMarc.add(labelMarc);
        panelMarc.add(txtMarc);
        panelDesc.add(labelDesc);
        panelDesc.add(txtDesc);
        panelComp.add(labelComp);
        panelComp.add(txtComp);
        panelorig.add(labelOrg);
        panelorig.add(txtOrg);
        panelOrd.add(labelOrd);
        panelOrd.add(listaOrd);
        panelboton.add(guardar);
        panelboton.add(cancelar);
        panelPrin.add(panelEst);
        panelPrin.add(panelMarc);
        panelPrin.add(panelDesc);
        panelPrin.add(panelComp);
        panelPrin.add(panelorig);
        panelPrin.add(panelOrd);
        panelPrin.add(panelboton);
        jCrearMerc.add(panelPrin);
        guardar.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){
                jCrearMerc.setVisible(false);
            }
        });
        cancelar.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){
                jCrearMerc.setVisible(false);
            }
        });
    }
    static public void modMerc(Mercaderia m){ 
        final JFrame jModMerc = new JFrame("Modificacion de Mercaderia");
        jModMerc.setSize(500, 300);
        jModMerc.setVisible(true);
        Panel panelPrin=new Panel(new GridLayout(7, 1));
        Panel panelEst=new Panel(new GridLayout(1, 2));
        Panel panelMarc=new Panel(new GridLayout(1, 2));
        Panel panelDesc=new Panel(new GridLayout(1, 2));
        Panel panelComp=new Panel(new GridLayout(1, 2));
        Panel panelorig=new Panel(new GridLayout(1, 2));
        Panel panelOrd=new Panel(new GridLayout(1, 2));
        Panel panelboton=new Panel(new GridLayout(1, 2));
        Label labelEst=new Label("Estilo:", Label.CENTER);
        Label labelMarc=new Label("Marca", Label.CENTER);
        Label labelDesc=new Label("Descripción:", Label.CENTER);
        Label labelComp=new Label("Composición:", Label.CENTER);
        Label labelOrd=new Label("Pertenece a la Orden: ", Label.CENTER);
        Label labelOrg=new Label("Origen:", Label.CENTER);
        Button guardar=new Button("Guardar");
        Button cancelar=new Button("Cancelar");
        TextField txtEst=new TextField(m.style, 20);
        TextField txtMarc=new TextField(m.mark, 20);
        TextField txtDesc=new TextField(m.desc, 20);
        TextField txtComp=new TextField(m.comp, 20);
        TextField txtOrg=new TextField(m.origen, 20);
        JComboBox listaOrd=new JComboBox();
        listaOrd.addItem(m.id);
        panelEst.add(labelEst);
        panelEst.add(txtEst);
        panelMarc.add(labelMarc);
        panelMarc.add(txtMarc);
        panelDesc.add(labelDesc);
        panelDesc.add(txtDesc);
        panelComp.add(labelComp);
        panelComp.add(txtComp);
        panelorig.add(labelOrg);
        panelorig.add(txtOrg);
        panelOrd.add(labelOrd);
        panelOrd.add(listaOrd);
        panelboton.add(guardar);
        panelboton.add(cancelar);
        panelPrin.add(panelEst);
        panelPrin.add(panelMarc);
        panelPrin.add(panelDesc);
        panelPrin.add(panelComp);
        panelPrin.add(panelorig);
        panelPrin.add(panelOrd);
        panelPrin.add(panelboton);
        jModMerc.add(panelPrin);
        guardar.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){
                jModMerc.setVisible(false);
            }
        });
        cancelar.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){
                jModMerc.setVisible(false);
            }
        });
    }
        public Object[] arreglo(){
        Object[] arreglo={id, style, mark, desc, comp, origen, idorden};
        return arreglo;
    }   
    
}
