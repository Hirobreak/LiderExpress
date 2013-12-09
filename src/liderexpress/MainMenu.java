
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
        Mercaderia adminMercaderia = new Mercaderia(1,"test","test","test","test","test", 1);
        Empaquetado adminEmpaquetado = new Empaquetado(1,1,1,1);
        Importacion adminImportacion = new Importacion(1,1,1,1,1,1);
        Proveedor adminProveedor = new Proveedor (1,"test",1,"test","test","test",1);
        Trabajador adminTrabajador = new Trabajador(1,"test","test",1,1,1,"test");
        Object[][] arreglo;
        int entidad=0;
        MenuBar entidades=new MenuBar();
        Menu mostrar=new Menu("Mostrar");
        Menu opciones=new Menu("Opciones");
        MenuItem cliente, orden, merca, empaq, impo, provee, trab;
        MenuItem nuevo, editar, eliminar, buscar;
        DefaultTableModel model;
        JTable tabla;
        JPanel panel;
    
    MainMenu(){

        panel = new JPanel ();
        panel.setBorder (BorderFactory.createTitledBorder (BorderFactory.createEtchedBorder (), "Tabla", TitledBorder.CENTER, TitledBorder.TOP));
        panel.setLayout(new GridBagLayout());
        GridBagConstraints c = new GridBagConstraints();
        c.insets = new Insets(10,10,10,10);
        c.fill = GridBagConstraints.BOTH;
        c.weightx = 1;
        c.weighty = 1;
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
        listScroller.setPreferredSize(new Dimension(650, 200));
        panel.add (listScroller, c);
        add(panel);
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
            this.panel.setBorder (BorderFactory.createTitledBorder (BorderFactory.createEtchedBorder (), "Clientes", TitledBorder.CENTER, TitledBorder.TOP));
            Object[] columns={"id", "Nombre", "Cedula", "Compañia", "RUC", "Telf1", "Telf2"};
            DefaultTableModel modelo1=new DefaultTableModel(null, columns);
            modelo1.addRow(adminCliente.arreglo());
            this.tabla.setModel(modelo1);
        }
        if(ae.getSource()==orden){
            entidad=2;
            this.panel.setBorder (BorderFactory.createTitledBorder (BorderFactory.createEtchedBorder (), "Orden", TitledBorder.CENTER, TitledBorder.TOP));
            Object[] columns={"id", "Pais", "Ciudad", "Tiempo", "Numero", "Estado", "Cliente"};
            DefaultTableModel modelo2=new DefaultTableModel(null, columns);
            modelo2.addRow(adminOrden.arreglo());
            this.tabla.setModel(modelo2);
        }
        if(ae.getSource()==merca){
            entidad=3;
            this.panel.setBorder (BorderFactory.createTitledBorder (BorderFactory.createEtchedBorder (), "Mercaderia", TitledBorder.CENTER, TitledBorder.TOP));
            Object[] columns={"id", "Estilo", "Marca", "Descripcion", "Composicion", "Origen", "idOrden"};
            DefaultTableModel modelo3=new DefaultTableModel(null, columns);
            modelo3.addRow(adminMercaderia.arreglo());
            this.tabla.setModel(modelo3);
        }
        if(ae.getSource()==empaq){
            entidad=4;
            this.panel.setBorder (BorderFactory.createTitledBorder (BorderFactory.createEtchedBorder (), "Empaquetado", TitledBorder.CENTER, TitledBorder.TOP));
            Object[] columns={"id", "Contenedor", "Mercaderia", "Caja", "Estado"};
            DefaultTableModel modelo4=new DefaultTableModel(null, columns);
            modelo4.addRow(adminEmpaquetado.arreglo());
            this.tabla.setModel(modelo4);
        }
        if(ae.getSource()==impo){
            entidad=5;
            this.panel.setBorder (BorderFactory.createTitledBorder (BorderFactory.createEtchedBorder (), "Importaciones", TitledBorder.CENTER, TitledBorder.TOP));
            Object[] columns={"id", "Trabajador", "Proveedor", "Dia", "Mes", "Año"};
            DefaultTableModel modelo5=new DefaultTableModel(null, columns);
            modelo5.addRow(adminImportacion.arreglo());
            this.tabla.setModel(modelo5);
        }
        if(ae.getSource()==provee){
            entidad=6;
            this.panel.setBorder (BorderFactory.createTitledBorder (BorderFactory.createEtchedBorder (), "Proveedores", TitledBorder.CENTER, TitledBorder.TOP));
            Object[] columns={"id", "Compañia", "RUP", "Pais", "Ciudad", "Dueño", "Telefono"};
            DefaultTableModel modelo6=new DefaultTableModel(null, columns);
            modelo6.addRow(adminProveedor.arreglo());
            this.tabla.setModel(modelo6);
        }
        if(ae.getSource()==trab){
            entidad=7;
            this.panel.setBorder (BorderFactory.createTitledBorder (BorderFactory.createEtchedBorder (), "Trabajadores", TitledBorder.CENTER, TitledBorder.TOP));
            Object[] columns={"id", "Nombre", "Cargo", "Cedula", "Telefono", "Salario", "E-Mail"};
            DefaultTableModel modelo7=new DefaultTableModel(null, columns);
            modelo7.addRow(adminTrabajador.arreglo());
            this.tabla.setModel(modelo7);
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
            Proveedor.modProv(adminProveedor);
        }
        if(ae.getSource()==editar && entidad==7){
            Trabajador.modTrab(adminTrabajador);
        }
        
        
    }
}
