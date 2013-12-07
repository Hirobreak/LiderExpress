
package liderexpress;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;
import javax.swing.border.*;
import javax.swing.table.DefaultTableModel;

public class MainMenu extends JFrame implements ActionListener{
    
        Cliente adminCliente = new Cliente(1,"admin","admin","admin","admin", 123, 123);
        Orden adminOrden = new Orden(1,"test","test","test","test","test",1);
        Mercaderia adminMercaderia = new Mercaderia(1,"test","test","test","test","test");
        Empaquetado adminEmpaquetado = new Empaquetado(1,1,1,1);
        Importacion adminImportacion = new Importacion(1,1,1,1,1,1);
        Proveedor adminImportador = new Proveedor (1,"test",1,"test","test","test",1);
        Trabajador adminTrabajador = new Trabajador(1,"test","test",1,1,1,"test");
        Object[][] arreglo;
        int entidad=0;
        MenuBar entidades=new MenuBar();
        Menu mostrar=new Menu("Mostrar");
        Menu opciones=new Menu("Orden");
        MenuItem cliente, orden, merca, empaq, impo, provee, trab;
        MenuItem nuevo, editar, eliminar, buscar;
        DefaultTableModel model;
        JTable tabla;
    
    MainMenu(){
        tabla=new JTable(model);
        this.setTitle("Bienvenido Usuario XYZ");
        this.setSize(700, 300);
        this.setVisible(true);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        mostrar.add(cliente=new MenuItem("Cliente"));
        mostrar.add(orden=new MenuItem("Orden"));
        mostrar.add(merca=new MenuItem("Mercaderia"));
        mostrar.add(empaq=new MenuItem("Empaque"));
        mostrar.add(impo=new MenuItem("Importacion"));
        mostrar.add(provee=new MenuItem("Proveedor"));
        mostrar.add(trab=new MenuItem("Trabajador"));
        opciones.add(nuevo=new MenuItem("Nuevo"));
        opciones.add(editar=new MenuItem("Editar"));
        opciones.add(eliminar=new MenuItem("Eliminar"));
        opciones.add(buscar=new MenuItem("Buscar"));
        entidades.add(mostrar);
        entidades.add(opciones);
        setMenuBar(entidades);
        JScrollPane listScroller = new JScrollPane(tabla);
        listScroller.setPreferredSize(new Dimension(250, 80));
        add(listScroller);
        cliente.addActionListener(this);
        orden.addActionListener(this);
        merca.addActionListener(this);
        empaq.addActionListener(this);
        impo.addActionListener(this);
        provee.addActionListener(this);
        trab.addActionListener(this);
        cliente.addActionListener(this);
        nuevo.addActionListener(this);
        editar.addActionListener(this);
        eliminar.addActionListener(this);
        buscar.addActionListener(this);
        
            
    }
    
    @Override
    public void actionPerformed(ActionEvent ae){
        if(ae.getSource()==cliente){
            entidad=1;
            Object[] columns={"Nombre", "Cedula", "Compañia", "RUC", "Telf1", "Telf2"};
            DefaultTableModel modelo1=new DefaultTableModel(null, columns);
            modelo1.addRow(adminCliente.arreglo());
            this.tabla.setModel(modelo1);
        }
        if(ae.getSource()==orden){
            entidad=2;
            Object[] columns={"Pais", "Ciudad", "Tiempo", "Numero", "Estado", "Cliente"};
            DefaultTableModel modelo2=new DefaultTableModel(null, columns);
            modelo2.addRow(adminOrden.arreglo());
            this.tabla.setModel(modelo2);
        }
        if(ae.getSource()==merca){
            entidad=3;
        }
        if(ae.getSource()==empaq){
            entidad=4;
        }
        if(ae.getSource()==impo){
            entidad=5;
        }
        if(ae.getSource()==provee){
            entidad=6;
        }
        if(ae.getSource()==trab){
            entidad=7;
        }
        
        if(ae.getSource()==nuevo && entidad==1){
            Cliente.crearCliente();
        }
        if(ae.getSource()==nuevo && entidad==2){
            Orden.crearOrden();
        }
        if(ae.getSource()==nuevo && entidad==3){
            Mercaderia.crearMerc();
        }
        if(ae.getSource()==nuevo && entidad==4){
            Empaquetado.crearEmpaq();
        }
        if(ae.getSource()==nuevo && entidad==5){
            Importacion.crearImpo();
        }
        if(ae.getSource()==nuevo && entidad==6){
            Proveedor.crearProv();
        }
        if(ae.getSource()==nuevo && entidad==7){
            Trabajador.crearTrab();
        }
        if(ae.getSource()==editar && entidad==1){
            Cliente.modificarCliente(adminCliente);
        }
        if(ae.getSource()==editar && entidad==2){
            Orden.modificarOrden(adminOrden);
        }
        if(ae.getSource()==editar && entidad==3){
            Mercaderia.modMerc(adminMercaderia);
        }
        if(ae.getSource()==editar && entidad==4){
            Empaquetado.modEmpaq(adminEmpaquetado);
        }
        if(ae.getSource()==editar && entidad==5){
            Importacion.modImpo(adminImportacion);
        }
        if(ae.getSource()==editar && entidad==6){
            Proveedor.modProv(adminImportador);
        }
        if(ae.getSource()==editar && entidad==7){
            Trabajador.modTrab(adminTrabajador);
        }
        
        
    }
}
