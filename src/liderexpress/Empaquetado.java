
package liderexpress;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import javax.swing.*;
import javax.swing.border.*;

public class Empaquetado {
    int idcont;
    int idmerca;
    int idcaja;
    int estado;
    
    Empaquetado(int conte, int merca, int caja, int est){
        idcont=conte;
        idmerca=merca;
        idcaja=caja;
        estado=est;
    }
    
    static public void crearEmpaq(){
        final JFrame jCrearEmp = new JFrame("Asignar Empaquetado");
        jCrearEmp.setSize(500, 300);
        jCrearEmp.setVisible(true);
        Panel panelPrin=new Panel(new GridLayout(5, 1));
        Panel panelMerc=new Panel(new GridLayout(1, 2));
        Panel panelCaja=new Panel(new GridLayout(1, 3));
        Panel panelCont=new Panel(new GridLayout(1, 3));
        Panel panelEst=new Panel(new GridLayout(1, 2));
        Panel panelboton=new Panel(new GridLayout(1, 2));
        Label labelMerc=new Label("Mercaderia:", Label.CENTER);
        Label labelCaja=new Label("Caja", Label.CENTER);
        Label labelCont=new Label("Contenedor", Label.CENTER);
        Label labelest=new Label("Estado", Label.CENTER);
        Button guardar=new Button("Guardar");
        Button cancelar=new Button("Cancelar");
        Button crearCaja=new Button("Crear");
        Button crearCont=new Button("Crear");
        JComboBox estados=new JComboBox();
        JComboBox mercas=new JComboBox();
        JComboBox cajas=new JComboBox();
        JComboBox contens=new JComboBox();
        estados.addItem("Embarcado");
        estados.addItem("Desmontado");
        panelMerc.add(labelMerc);
        panelMerc.add(mercas);
        panelCaja.add(labelCaja);
        panelCaja.add(crearCaja);
        panelCaja.add(cajas);
        panelCont.add(labelCont);
        panelCont.add(crearCont);
        panelCont.add(contens);
        panelEst.add(labelest);
        panelEst.add(estados);
        panelboton.add(guardar);
        panelboton.add(cancelar);
        panelPrin.add(panelMerc);
        panelPrin.add(panelCaja);
        panelPrin.add(panelCont);
        panelPrin.add(panelEst);
        panelPrin.add(panelboton);
        jCrearEmp.add(panelPrin);
        guardar.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){
                jCrearEmp.setVisible(false);
            }
        });
        cancelar.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){
                jCrearEmp.setVisible(false);
            }
        });
        crearCont.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){
                Contenedor.crearCont();
            }
        });
        crearCaja.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){
                Caja.crearCaja();
            }
        });
    }
    static public void modEmpaq(Empaquetado emp){
        final JFrame jModEmp = new JFrame("Modificar Empaquetado");
        jModEmp.setSize(500, 300);
        jModEmp.setVisible(true);
        Panel panelPrin=new Panel(new GridLayout(5, 1));
        Panel panelMerc=new Panel(new GridLayout(1, 2));
        Panel panelCaja=new Panel(new GridLayout(1, 3));
        Panel panelCont=new Panel(new GridLayout(1, 3));
        Panel panelEst=new Panel(new GridLayout(1, 2));
        Panel panelboton=new Panel(new GridLayout(1, 2));
        Label labelMerc=new Label("Mercaderia:", Label.CENTER);
        Label labelCaja=new Label("Caja", Label.CENTER);
        Label labelCont=new Label("Contenedor", Label.CENTER);
        Label labelest=new Label("Estado", Label.CENTER);
        Button guardar=new Button("Guardar");
        Button cancelar=new Button("Cancelar");
        Button crearCaja=new Button("Crear");
        Button crearCont=new Button("Crear");
        JComboBox estados=new JComboBox();
        JComboBox mercas=new JComboBox();
        JComboBox cajas=new JComboBox();
        JComboBox contens=new JComboBox();
        estados.addItem("Embarcado");
        estados.addItem("Desmontado");
        panelMerc.add(labelMerc);
        panelMerc.add(mercas);
        panelCaja.add(labelCaja);
        panelCaja.add(crearCaja);
        panelCaja.add(cajas);
        panelCont.add(labelCont);
        panelCont.add(crearCont);
        panelCont.add(contens);
        panelEst.add(labelest);
        panelEst.add(estados);
        panelboton.add(guardar);
        panelboton.add(cancelar);
        panelPrin.add(panelMerc);
        panelPrin.add(panelCaja);
        panelPrin.add(panelCont);
        panelPrin.add(panelEst);
        panelPrin.add(panelboton);
        jModEmp.add(panelPrin);
        guardar.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){
                jModEmp.setVisible(false);
            }
        });
        cancelar.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){
                jModEmp.setVisible(false);
            }
        });
        
    }  

    public Object[] arreglo(){
        Object[] arreglo={idcont, idmerca, idcaja, estado};
        return arreglo;
    }
}
