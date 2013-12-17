
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
    float preProv;
    float preClien;
    int cantidad; 
    int idorden;
    
    Mercaderia(int ident, String estilo, String marca, String des, String compo, String orig, int idor, float pp, float pc, int canti){
        id=ident;
        style=estilo;
        mark=marca;
        desc=des;
        comp=compo;
        origen=orig;
        preProv=pp;
        preClien=pc;
        cantidad=canti;
        idorden=idor;
    }
    
   static public void crearMerc(final ArrayList<Orden> ordenes,final ArrayList<Mercaderia> mercaderias){ 
        final JFrame jCrearMerc = new JFrame("Creacion de Mercaderia");
        jCrearMerc.setSize(500, 300);
        jCrearMerc.setVisible(true);
        Panel panelPrin=new Panel(new GridLayout(10, 1));
        Panel panelEst=new Panel(new GridLayout(1, 2));
        Panel panelMarc=new Panel(new GridLayout(1, 2));
        Panel panelDesc=new Panel(new GridLayout(1, 2));
        Panel panelComp=new Panel(new GridLayout(1, 2));
        Panel panelorig=new Panel(new GridLayout(1, 2));
        Panel panelOrd=new Panel(new GridLayout(1, 2));
        Panel panelpp=new Panel(new GridLayout(1, 2));
        Panel panelpc=new Panel(new GridLayout(1, 2));
        Panel panelcant=new Panel(new GridLayout(1, 2));
        Panel panelboton=new Panel(new GridLayout(1, 2));
        Label labelEst=new Label("Estilo:", Label.CENTER);
        Label labelMarc=new Label("Marca", Label.CENTER);
        Label labelDesc=new Label("Descripción:", Label.CENTER);
        Label labelComp=new Label("Composición:", Label.CENTER);
        Label labelOrd=new Label("Pertenece a la Orden: ", Label.CENTER);
        Label labelOrg=new Label("Origen:", Label.CENTER);
        Label labelpp=new Label("Precio Proveedor $:", Label.CENTER);
        Label labelpc=new Label("Precio Venta $: ", Label.CENTER);
        Label labelcant=new Label("Cantidad: ", Label.CENTER);
        Button guardar=new Button("Guardar");
        Button cancelar=new Button("Cancelar");
        final TextField txtEst=new TextField("Estilo", 20);
        final TextField txtMarc=new TextField("Marca", 20);
        final TextField txtDesc=new TextField("Descripción", 20);
        final TextField txtComp=new TextField("Composición", 20);
        final TextField txtOrg=new TextField("Origen", 20);
        final TextField txtpp=new TextField("0.00", 20);
        final TextField txtpc=new TextField("0.00", 20);
        final TextField txtcant=new TextField("0", 20);
        final JComboBox listaOrd=new JComboBox();
        for(Orden o : ordenes)
            listaOrd.addItem(o.numero);
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
        panelpp.add(labelpp);
        panelpp.add(txtpp);
        panelpc.add(labelpc);
        panelpc.add(txtpc);
        panelcant.add(labelcant);
        panelcant.add(txtcant);
        panelboton.add(guardar);
        panelboton.add(cancelar);
        panelPrin.add(panelEst);
        panelPrin.add(panelMarc);
        panelPrin.add(panelDesc);
        panelPrin.add(panelComp);
        panelPrin.add(panelorig);
        panelPrin.add(panelpp);
        panelPrin.add(panelpc);
        panelPrin.add(panelcant);
        panelPrin.add(panelOrd);
        panelPrin.add(panelboton);
        jCrearMerc.add(panelPrin);
        guardar.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){
                Mercaderia m = new Mercaderia(mercaderias.size()+1,txtEst.getText(),txtMarc.getText(),txtDesc.getText(),txtComp.getText(),txtOrg.getText(),ordenes.get(listaOrd.getSelectedIndex()).id, Float.parseFloat(txtpp.getText()), Float.parseFloat(txtpc.getText()), Integer.parseInt(txtcant.getText()));
                mercaderias.add(m);
                jCrearMerc.setVisible(false);
            }
        });
        cancelar.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){
                jCrearMerc.setVisible(false);
            }
        });
    }
    static public void modMerc(final ArrayList<Orden> ordenes,final ArrayList<Mercaderia> mercaderias, final int pos){
        Mercaderia m = mercaderias.get(pos);
        final JFrame jModMerc = new JFrame("Creacion de Mercaderia");
        jModMerc.setSize(500, 300);
        jModMerc.setVisible(true);
        Panel panelPrin=new Panel(new GridLayout(10, 1));
        Panel panelEst=new Panel(new GridLayout(1, 2));
        Panel panelMarc=new Panel(new GridLayout(1, 2));
        Panel panelDesc=new Panel(new GridLayout(1, 2));
        Panel panelComp=new Panel(new GridLayout(1, 2));
        Panel panelorig=new Panel(new GridLayout(1, 2));
        Panel panelpp=new Panel(new GridLayout(1, 2));
        Panel panelpc=new Panel(new GridLayout(1, 2));
        Panel panelcant=new Panel(new GridLayout(1, 2));
        Panel panelOrd=new Panel(new GridLayout(1, 2));
        Panel panelboton=new Panel(new GridLayout(1, 2));
        Label labelEst=new Label("Estilo:", Label.CENTER);
        Label labelMarc=new Label("Marca", Label.CENTER);
        Label labelDesc=new Label("Descripción:", Label.CENTER);
        Label labelComp=new Label("Composición:", Label.CENTER);
        Label labelOrd=new Label("Pertenece a la Orden: ", Label.CENTER);
        Label labelOrg=new Label("Origen:", Label.CENTER);
        Label labelpp=new Label("Precio Proveedor $:", Label.CENTER);
        Label labelpc=new Label("Precio Venta $: ", Label.CENTER);
        Label labelcant=new Label("Cantidad: ", Label.CENTER);
        Button guardar=new Button("Guardar");
        Button cancelar=new Button("Cancelar");
        final TextField txtEst=new TextField(m.style, 20);
        final TextField txtMarc=new TextField(m.mark, 20);
        final TextField txtDesc=new TextField(m.desc, 20);
        final TextField txtComp=new TextField(m.comp, 20);
        final TextField txtOrg=new TextField(m.origen, 20);
        final TextField txtpp=new TextField(String.valueOf(m.preProv), 20);
        final TextField txtpc=new TextField(String.valueOf(m.preClien), 20);
        final TextField txtcant=new TextField(String.valueOf(m.cantidad), 20);
        final JComboBox listaOrd=new JComboBox();
        for(Orden o : ordenes)
            listaOrd.addItem(o.numero);
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
        panelpp.add(labelpp);
        panelpp.add(txtpp);
        panelpc.add(labelpc);
        panelpc.add(txtpc);
        panelcant.add(labelcant);
        panelcant.add(txtcant);
        panelboton.add(guardar);
        panelboton.add(cancelar);
        panelPrin.add(panelEst);
        panelPrin.add(panelMarc);
        panelPrin.add(panelDesc);
        panelPrin.add(panelComp);
        panelPrin.add(panelorig);
        panelPrin.add(panelpp);
        panelPrin.add(panelpc);
        panelPrin.add(panelcant);
        panelPrin.add(panelOrd);
        panelPrin.add(panelboton);
        jModMerc.add(panelPrin);
        guardar.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){
                Mercaderia mod = new Mercaderia(pos+1,txtEst.getText(),txtMarc.getText(),txtDesc.getText(),txtComp.getText(),txtOrg.getText(),ordenes.get(listaOrd.getSelectedIndex()).id, Float.parseFloat(txtpp.getText()), Float.parseFloat(txtpc.getText()), Integer.parseInt(txtcant.getText()));
                mercaderias.remove(pos);
                mercaderias.add(pos, mod);
                jModMerc.setVisible(false);
            }
        });
        cancelar.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){
                jModMerc.setVisible(false);
            }
        });
    }
    static public void eliminarMerc(final ArrayList<Mercaderia> mercaderias, final int pos){
        Mercaderia m = mercaderias.get(pos);
        final JFrame jElimMerc = new JFrame("Eliminacion de Mercaderia");
        jElimMerc.setSize(500, 300);
        jElimMerc.setVisible(true);
        Panel panelPrin=new Panel(new GridLayout(10, 1));
        Panel panelEst=new Panel(new GridLayout(1, 2));
        Panel panelMarc=new Panel(new GridLayout(1, 2));
        Panel panelDesc=new Panel(new GridLayout(1, 2));
        Panel panelComp=new Panel(new GridLayout(1, 2));
        Panel panelorig=new Panel(new GridLayout(1, 2));
        Panel panelpp=new Panel(new GridLayout(1, 2));
        Panel panelpc=new Panel(new GridLayout(1, 2));
        Panel panelcant=new Panel(new GridLayout(1, 2));
        Panel panelOrd=new Panel(new GridLayout(1, 2));
        Panel panelboton=new Panel(new GridLayout(1, 2));
        Label labelEst=new Label("Estilo:", Label.CENTER);
        Label labelMarc=new Label("Marca", Label.CENTER);
        Label labelDesc=new Label("Descripción:", Label.CENTER);
        Label labelComp=new Label("Composición:", Label.CENTER);
        Label labelOrg=new Label("Origen:", Label.CENTER);
        Label labelpp=new Label("Precio Proveedor $:", Label.CENTER);
        Label labelpc=new Label("Precio Venta $: ", Label.CENTER);
        Label labelcant=new Label("Cantidad: ", Label.CENTER);
        Button guardar=new Button("Eliminar");
        Button cancelar=new Button("Cancelar");
        Label txtEst=new Label(m.style, Label.CENTER);
        Label txtMarc=new Label(m.mark, Label.CENTER);
        Label txtDesc=new Label(m.desc, Label.CENTER);
        Label txtComp=new Label(m.comp, Label.CENTER);
        Label txtOrg=new Label(m.origen, Label.CENTER);
        Label txtpp=new Label(String.valueOf(m.preProv), Label.CENTER);
        Label txtpc=new Label(String.valueOf(m.preClien), Label.CENTER);
        Label txtcant=new Label(String.valueOf(m.cantidad), Label.CENTER);
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
        panelboton.add(guardar);
        panelboton.add(cancelar);
        panelpp.add(labelpp);
        panelpp.add(txtpp);
        panelpc.add(labelpc);
        panelpc.add(txtpc);
        panelcant.add(labelcant);
        panelcant.add(txtcant);
        panelPrin.add(panelEst);
        panelPrin.add(panelMarc);
        panelPrin.add(panelDesc);
        panelPrin.add(panelComp);
        panelPrin.add(panelorig);
        panelPrin.add(panelpp);
        panelPrin.add(panelpc);
        panelPrin.add(panelcant);
        panelPrin.add(panelOrd);
        panelPrin.add(panelboton);
        jElimMerc.add(panelPrin);
        guardar.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){
                mercaderias.remove(pos);
                jElimMerc.setVisible(false);
            }
        });
        cancelar.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){
                jElimMerc.setVisible(false);
            }
        });
    }
        public Object[] arreglo(){
        Object[] arreglo={id, style, mark, desc, comp, origen, preProv, preClien, cantidad, idorden};
        return arreglo;
    }   
    
}
