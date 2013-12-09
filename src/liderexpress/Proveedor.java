
package liderexpress;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import javax.swing.*;
import javax.swing.border.*;

public class Proveedor {
    int id;
    String compañia;
    int rup;
    String pais;
    String ciudad;
    String dueño;
    int telf;
    
    Proveedor(int ids, String com, int rucp, String country, String city, String contacto, int telefo){
        id=ids;
        compañia=com;
        rup=rucp;
        pais=country;
        ciudad=city;
        dueño=contacto;
        telf=telefo;
    }
    
    static public void crearProv(final ArrayList<Proveedor> proveedores){
        final JFrame jCrearProv = new JFrame("Creacion de Importador");
        jCrearProv.setSize(500, 300);
        jCrearProv.setVisible(true);
        Panel panelPrin=new Panel(new GridLayout(6, 1));
        Panel panelCom=new Panel(new GridLayout(1, 2));
        Panel panelRup=new Panel(new GridLayout(1, 2));
        Panel panelDueño=new Panel(new GridLayout(1, 2));
        Panel panelLugar=new Panel(new GridLayout(1, 3));
        Panel panelTelf=new Panel(new GridLayout(1, 2));
        Panel panelboton=new Panel(new GridLayout(1, 2));
        Label labelCom=new Label("Compañia:", Label.CENTER);
        Label labelRup=new Label("RUP:", Label.CENTER);
        Label labelDueño=new Label("Dueño:", Label.CENTER);
        Label labelLugar=new Label("Lugar", Label.CENTER);
        Label labeltelf=new Label("Telefonos:", Label.CENTER);
        Button guardar=new Button("Guardar");
        Button cancelar=new Button("Cancelar");
        final TextField txtCom=new TextField("Nombre", 20);
        final TextField txtRUP=new TextField("Cargo", 20);
        final TextField txtDueño=new TextField("Contacto", 20);
        final TextField txtPais=new TextField("Pais", 20);
        final TextField txtTelf=new TextField("00000000", 20);
        final TextField txtCiudad=new TextField("Ciudad", 20);
        panelCom.add(labelCom);
        panelCom.add(txtCom);
        panelRup.add(labelRup);
        panelRup.add(txtRUP);
        panelDueño.add(labelDueño);
        panelDueño.add(txtDueño);
        panelLugar.add(labelLugar);
        panelLugar.add(txtPais);
        panelLugar.add(txtCiudad);
        panelTelf.add(labeltelf);
        panelTelf.add(txtTelf);
        panelboton.add(guardar);
        panelboton.add(cancelar);
        panelPrin.add(panelCom);
        panelPrin.add(panelRup);
        panelPrin.add(panelDueño);
        panelPrin.add(panelLugar);
        panelPrin.add(panelTelf);
        panelPrin.add(panelboton);
        jCrearProv.add(panelPrin);
        guardar.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){
                Proveedor c = new Proveedor(proveedores.size()+1,txtCom.getText(),Integer.parseInt(txtRUP.getText()),txtPais.getText(),txtCiudad.getText(),txtDueño.getText(),Integer.parseInt(txtTelf.getText()));
                proveedores.add(c);
                jCrearProv.setVisible(false);
            }
        });
        cancelar.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){
                jCrearProv.setVisible(false);
            }
        });
        
    }
    static public void modProv(Proveedor prov){
        final JFrame jModProv = new JFrame("Modificacion de Importador");
        jModProv.setSize(500, 300);
        jModProv.setVisible(true);
        Panel panelPrin=new Panel(new GridLayout(6, 1));
        Panel panelCom=new Panel(new GridLayout(1, 2));
        Panel panelRup=new Panel(new GridLayout(1, 2));
        Panel panelDueño=new Panel(new GridLayout(1, 2));
        Panel panelLugar=new Panel(new GridLayout(1, 3));
        Panel panelTelf=new Panel(new GridLayout(1, 2));
        Panel panelboton=new Panel(new GridLayout(1, 2));
        Label labelCom=new Label("Compañia:", Label.CENTER);
        Label labelRup=new Label("RUP:", Label.CENTER);
        Label labelDueño=new Label("Dueño:", Label.CENTER);
        Label labelLugar=new Label("Lugar", Label.CENTER);
        Label labeltelf=new Label("Telefonos:", Label.CENTER);
        Button guardar=new Button("Guardar");
        Button cancelar=new Button("Cancelar");
        TextField txtCom=new TextField(prov.compañia, 20);
        TextField txtRUP=new TextField(String.valueOf(prov.rup), 20);
        TextField txtDueño=new TextField(prov.dueño, 20);
        TextField txtPais=new TextField(prov.pais, 20);
        TextField txtTelf=new TextField(String.valueOf(prov.telf), 20);
        TextField txtCiudad=new TextField(prov.ciudad, 20);
        panelCom.add(labelCom);
        panelCom.add(txtCom);
        panelRup.add(labelRup);
        panelRup.add(txtRUP);
        panelDueño.add(labelDueño);
        panelDueño.add(txtDueño);
        panelLugar.add(labelLugar);
        panelLugar.add(txtPais);
        panelLugar.add(txtCiudad);
        panelTelf.add(labeltelf);
        panelTelf.add(txtTelf);
        panelboton.add(guardar);
        panelboton.add(cancelar);
        panelPrin.add(panelCom);
        panelPrin.add(panelRup);
        panelPrin.add(panelDueño);
        panelPrin.add(panelLugar);
        panelPrin.add(panelTelf);
        panelPrin.add(panelboton);
        jModProv.add(panelPrin);
        guardar.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){
                jModProv.setVisible(false);
            }
        });
        cancelar.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){
                jModProv.setVisible(false);
            }
        });
        
    }    
        public Object[] arreglo(){
        Object[] arreglo={id, compañia, rup, pais, ciudad, dueño, telf};
        return arreglo;
    }  
}