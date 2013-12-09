
package liderexpress;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import javax.swing.*;
import javax.swing.border.*;
import javax.swing.table.DefaultTableModel;

public class MainMenu extends JFrame implements ActionListener{
        ArrayList<Cliente> clientes = new ArrayList<Cliente>();
        ArrayList<Orden> ordenes = new ArrayList<Orden>();
        ArrayList<Mercaderia> mercaderias = new ArrayList<Mercaderia>();
        ArrayList<Empaquetado> empaquetados = new ArrayList<Empaquetado>();
        ArrayList<Importacion> importaciones = new ArrayList<Importacion>();
        ArrayList<Proveedor> proveedores = new ArrayList<Proveedor>();
        ArrayList<Trabajador> trabajadores = new ArrayList<Trabajador>();
        ArrayList<Contenedor> contenedores = new ArrayList<Contenedor>();
        ArrayList<Caja> cajas = new ArrayList<Caja>();
        Cliente adminCliente = new Cliente(1,"admin","admin","admin","admin", 123, 123);
        Orden adminOrden = new Orden(1,"test","test","test","test","test",1);
        Mercaderia adminMercaderia = new Mercaderia(1,"test","test","test","test","test", 1);
        Empaquetado adminEmpaquetado = new Empaquetado(1,1,1,1,"Desmontado");
        Importacion adminImportacion = new Importacion(1,1,1,1,1,1);
        Proveedor adminProveedor = new Proveedor (1,"test",1,"test","test","test",1);
        Trabajador adminTrabajador = new Trabajador(1,"test","test",1,1,1,"test");
        Caja adminCaja = new Caja(1,"10x10x10",1,"Enviado","RR45");
        Contenedor adminContenedor = new Contenedor(1,"20x20x100",1,"Enviado",1);
        Object[][] arreglo;
        int entidad=0;
        MenuBar entidades=new MenuBar();
        Menu mostrar=new Menu("Mostrar");
        Menu opciones=new Menu("Opciones");
        MenuItem cliente, orden, merca, empaq, impo, provee, trab, caja, contenedor;
        MenuItem nuevo, editar, eliminar, buscar;
        DefaultTableModel model;
        JTable tabla;
        JPanel panel;
    
    MainMenu(){
        clientes.add(adminCliente);
        ordenes.add(adminOrden);
        mercaderias.add(adminMercaderia);
        empaquetados.add(adminEmpaquetado);
        importaciones.add(adminImportacion);
        proveedores.add(adminProveedor);
        trabajadores.add(adminTrabajador);
        contenedores.add(adminContenedor);
        cajas.add(adminCaja);
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
        mostrar.add(caja=new MenuItem("Caja"));
        mostrar.add(contenedor = new MenuItem("Contenedor"));
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
        caja.addActionListener(this);
        contenedor.addActionListener(this);
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
            for(Cliente c : clientes)
                modelo1.addRow(c.arreglo());
            this.tabla.setModel(modelo1);

        }
        if(ae.getSource()==orden){
            entidad=2;
            this.panel.setBorder (BorderFactory.createTitledBorder (BorderFactory.createEtchedBorder (), "Orden", TitledBorder.CENTER, TitledBorder.TOP));
            Object[] columns={"id", "Pais", "Ciudad", "Tiempo", "Numero", "Estado", "Cliente"};
            DefaultTableModel modelo2=new DefaultTableModel(null, columns);
            for(Orden o : ordenes)
                    modelo2.addRow(o.arreglo());
            this.tabla.setModel(modelo2);
        }
        if(ae.getSource()==merca){
            entidad=3;
            this.panel.setBorder (BorderFactory.createTitledBorder (BorderFactory.createEtchedBorder (), "Mercaderia", TitledBorder.CENTER, TitledBorder.TOP));
            Object[] columns={"id", "Estilo", "Marca", "Descripcion", "Composicion", "Origen", "idOrden"};
            DefaultTableModel modelo3=new DefaultTableModel(null, columns);
            for(Mercaderia m : mercaderias)
                    modelo3.addRow(m.arreglo());
            this.tabla.setModel(modelo3);
        }
        if(ae.getSource()==empaq){
            entidad=4;
            this.panel.setBorder (BorderFactory.createTitledBorder (BorderFactory.createEtchedBorder (), "Empaquetado", TitledBorder.CENTER, TitledBorder.TOP));
            Object[] columns={"id", "Contenedor", "Mercaderia", "Caja", "Estado"};
            DefaultTableModel modelo4=new DefaultTableModel(null, columns);
            for(Empaquetado e : empaquetados)
                    modelo4.addRow(e.arreglo());
            this.tabla.setModel(modelo4);
        }
        if(ae.getSource()==impo){
            entidad=5;
            this.panel.setBorder (BorderFactory.createTitledBorder (BorderFactory.createEtchedBorder (), "Importaciones", TitledBorder.CENTER, TitledBorder.TOP));
            Object[] columns={"id", "Trabajador", "Proveedor", "Dia", "Mes", "Año"};
            DefaultTableModel modelo5=new DefaultTableModel(null, columns);
            for(Importacion i : importaciones)
                    modelo5.addRow(i.arreglo());
            this.tabla.setModel(modelo5);
        }
        if(ae.getSource()==provee){
            entidad=6;
            this.panel.setBorder (BorderFactory.createTitledBorder (BorderFactory.createEtchedBorder (), "Proveedores", TitledBorder.CENTER, TitledBorder.TOP));
            Object[] columns={"id", "Compañia", "RUP", "Pais", "Ciudad", "Dueño", "Telefono"};
            DefaultTableModel modelo6=new DefaultTableModel(null, columns);
            for(Proveedor p : proveedores)
			modelo6.addRow(p.arreglo());
            this.tabla.setModel(modelo6);
        }
        if(ae.getSource()==trab){
            entidad=7;
            this.panel.setBorder (BorderFactory.createTitledBorder (BorderFactory.createEtchedBorder (), "Trabajadores", TitledBorder.CENTER, TitledBorder.TOP));
            Object[] columns={"id", "Nombre", "Cargo", "Cedula", "Telefono", "Salario", "E-Mail"};
            DefaultTableModel modelo7=new DefaultTableModel(null, columns);
            for(Trabajador t : trabajadores)
			modelo7.addRow(t.arreglo());
            this.tabla.setModel(modelo7);
        }
        if(ae.getSource()==caja){
            entidad=8;
            this.panel.setBorder (BorderFactory.createTitledBorder (BorderFactory.createEtchedBorder (), "Cajas", TitledBorder.CENTER, TitledBorder.TOP));
            Object[] columns={"id", "Dimensiones", "Peso", "Estado", "Numero"};
            DefaultTableModel modelo8=new DefaultTableModel(null, columns);
            for(Caja c : cajas)
                modelo8.addRow(c.arreglo());
            this.tabla.setModel(modelo8);
        }
        if(ae.getSource()==contenedor){
            entidad=9;
            this.panel.setBorder (BorderFactory.createTitledBorder (BorderFactory.createEtchedBorder (), "Contenedores", TitledBorder.CENTER, TitledBorder.TOP));
            Object[] columns={"id", "Dimensiones", "Peso", "Estado", "Importacion"};
            DefaultTableModel modelo9=new DefaultTableModel(null, columns);
            for(Contenedor c : contenedores)
                modelo9.addRow(c.arreglo());
            this.tabla.setModel(modelo9);
        }
        
        if(ae.getSource()==nuevo && entidad==1){
            Cliente.crearCliente(clientes);
        }
        if(ae.getSource()==nuevo && entidad==2){
            Orden.crearOrden(clientes,ordenes);
        }
        if(ae.getSource()==nuevo && entidad==3){
            Mercaderia.crearMerc(ordenes,mercaderias);
        }
        if(ae.getSource()==nuevo && entidad==4){
            Empaquetado.crearEmpaq(empaquetados,contenedores,mercaderias,cajas);
        }
        if(ae.getSource()==nuevo && entidad==5){
            Importacion.crearImpo(importaciones, trabajadores, proveedores);
        }
        if(ae.getSource()==nuevo && entidad==6){
            Proveedor.crearProv(proveedores);
        }
        if(ae.getSource()==nuevo && entidad==7){
            Trabajador.crearTrab(trabajadores);
        }
        if(ae.getSource()==nuevo && entidad==8){
            Caja.crearCaja(cajas);
        }
        if(ae.getSource()==nuevo && entidad==9){
            Contenedor.crearCont(contenedores, importaciones);
        }
        if(ae.getSource()==editar && entidad==1){
            int fila = tabla.getSelectedRow();
            Cliente.modificarCliente(clientes, fila);
        }
        if(ae.getSource()==editar && entidad==2){
            int fila = tabla.getSelectedRow();
            Orden.modificarOrden(clientes, ordenes, fila);
        }
        if(ae.getSource()==editar && entidad==3){
            int fila = tabla.getSelectedRow();
            Mercaderia.modMerc(ordenes,mercaderias, fila);
        }
        if(ae.getSource()==editar && entidad==4){
            int fila = tabla.getSelectedRow();
            Empaquetado.modEmpaq(adminEmpaquetado);
        }
        if(ae.getSource()==editar && entidad==5){
            int fila = tabla.getSelectedRow();
            Importacion.modImpo(adminImportacion);
        }
        if(ae.getSource()==editar && entidad==6){
            int fila = tabla.getSelectedRow();
            Proveedor.modProv(proveedores, fila);
        }
        if(ae.getSource()==editar && entidad==7){
            int fila = tabla.getSelectedRow();
            Trabajador.modTrab(adminTrabajador);
        }
        if(ae.getSource()==eliminar && entidad==1){
            int fila = tabla.getSelectedRow();
            Cliente.eliminarCliente(clientes, fila);
        }
        if(ae.getSource()==eliminar && entidad==2){
            int fila = tabla.getSelectedRow();
            Orden.eliminarOrden(ordenes, fila);
        }
        if(ae.getSource()==eliminar && entidad==3){
            int fila = tabla.getSelectedRow();
            Mercaderia.eliminarMerc(mercaderias, fila);
        }
        if(ae.getSource()==eliminar && entidad==4){
            int fila = tabla.getSelectedRow();
            //Empaquetado.eliminarEmpaq(adminEmpaquetado);
        }
        if(ae.getSource()==eliminar && entidad==5){
            int fila = tabla.getSelectedRow();
            //Importacion.eliminarImpo(adminImportacion);
        }
        if(ae.getSource()==eliminar && entidad==6){
            int fila = tabla.getSelectedRow();
            Proveedor.eliminarProv(proveedores, fila);
        }
        if(ae.getSource()==eliminar && entidad==7){
            int fila = tabla.getSelectedRow();
            //Trabajador.eliminarTrab(adminTrabajador);
        }
    }
}
