
package liderexpress;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import javax.swing.*;
import javax.swing.border.*;
import java.util.Date;
import javax.swing.table.DefaultTableModel;

public class Pago {
    int id;
    float valor;
    String tipo;
    Date fecha;
    int idFact;
    
    Pago(int ident, float cantidad, String tip, Date fech, int fact){
        id=ident;
        valor=cantidad;
        tipo=tip;
        fecha=fech;
        idFact=fact;
    }
    
    public static void crearPago(int idF){
        final JFrame pag=new JFrame("Agregar Pago");
        pag.setSize(500, 300);
        pag.setVisible(true);
        Panel panelPrin=new Panel(new GridLayout(4, 1));
        Panel paneltipo=new Panel(new GridLayout(1, 2));
        Panel panelfech=new Panel(new GridLayout(1, 4));
        Panel panelvalor=new Panel(new GridLayout(1, 2));
        Panel panelbot=new Panel(new GridLayout(1, 2));
        Label labelfech=new Label("Fecha:", Label.CENTER);
        Label labelvalor=new Label("Valor $:", Label.CENTER);
        Label labeltipo=new Label("Tipo:", Label.CENTER);
        TextField txtvalor=new TextField("$", 20);
        JComboBox cdia=new JComboBox();
        JComboBox cmes=new JComboBox();
        JComboBox caño=new JComboBox();
        Button guardar=new Button("Eliminar");
        Button cancelar=new Button("Cancelar");
        for (int i=2000; i<2015; i++){
            caño.addItem(i);
        }
        for (int j=1; j<13; j++){
            cmes.addItem(j);
        }
        for (int i=1; i<32; i++){
            cdia.addItem(i);
        }
        JComboBox ctipo=new JComboBox();
        ctipo.addItem("Efectivo");
        ctipo.addItem("Cheque");
        ctipo.addItem("Deposito");
        panelvalor.add(labelvalor);
        panelvalor.add(txtvalor);
        paneltipo.add(labeltipo);
        paneltipo.add(ctipo);
        panelfech.add(labelfech);
        panelfech.add(caño);
        panelfech.add(cmes);
        panelfech.add(cdia);
        panelbot.add(guardar);
        panelbot.add(cancelar);
        panelPrin.add(panelvalor);
        panelPrin.add(paneltipo);
        panelPrin.add(panelfech);
        panelPrin.add(panelbot);
        pag.add(panelPrin);
        guardar.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){
                pag.dispose();
            }
        });
        cancelar.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){
                pag.dispose();
            }
        });
        
        
        
    }
    
    public static void verPagos(){
        final JFrame pag=new JFrame("Pagos Realizados");
        pag.setSize(500, 300);
        pag.setVisible(true);
        Object[] columns={"Valor", "Tipo", "Fecha"};
        DefaultTableModel modelo=new DefaultTableModel(null, columns);
        Panel panelPrin=new Panel(new GridLayout(2, 1));
        JPanel paneltabla=new JPanel();
        paneltabla.setBorder(BorderFactory.createTitledBorder (BorderFactory.createEtchedBorder (), "Pagos", TitledBorder.CENTER, TitledBorder.TOP));
        JTable tablaPag=new JTable();
        tablaPag.setModel(modelo);
        paneltabla.add(tablaPag);
        JButton eliminar=new JButton("Eliminar");
        panelPrin.add(paneltabla);
        panelPrin.add(eliminar);
        pag.add(panelPrin);
        
    }
    
}

