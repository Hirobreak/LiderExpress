
package liderexpress;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Date;
import javax.swing.*;
import javax.swing.border.*;
import javax.swing.table.DefaultTableModel;
import java.sql.*;
import java.util.*;
import java.util.logging.Level;
import java.util.logging.Logger;

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
        int idc=0;
        Cliente adminCliente = new Cliente(1,"admin","admin","admin","admin", 123, 123);
        Orden adminOrden = new Orden(1,"test","test","test","test","test",1);
        Mercaderia adminMercaderia = new Mercaderia(1,"test","test","test","test","test", 1, 10, 15, 10);
        Empaquetado adminEmpaquetado = new Empaquetado(1,1,1,1,"Desmontado");
        Importacion adminImportacion = new Importacion(1,1,1,1,1,2010);
        Proveedor adminProveedor = new Proveedor (1,"test",1,"test","test","test",1);
        Trabajador adminTrabajador = new Trabajador(1,"test","test",1,1,1,"test");
        Caja adminCaja = new Caja(1,"10x10x10",1,"Enviado","RR45");
        Contenedor adminContenedor = new Contenedor(1,"20x20x100",1,"Enviado",1);
        Object[][] arreglo;
        int entidad=0;
        MenuBar entidades=new MenuBar();
        Menu mostrar=new Menu("Mostrar");
        Menu opciones=new Menu("Opciones");
        Menu consulta=new Menu("Consultas");
        Menu accion=new Menu("No hay posible Accion");
        MenuItem cliente, orden, merca, empaq, impo, provee, trab, caja, contenedor;
        MenuItem nuevo, editar, eliminar, buscar;
        MenuItem client, impor;
        MenuItem exe;
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
        consulta.add(client=new MenuItem("Clientes"));
        consulta.add(impor=new MenuItem("Importaciones Por Fecha"));
        accion.add(exe=new MenuItem("Ejecutar"));
        entidades.add(mostrar);
        entidades.add(opciones);
        entidades.add(consulta);
        entidades.add(accion);
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
        impor.addActionListener(this);
        client.addActionListener(this);
        exe.addActionListener(this);
    }
    
    @Override
    public void actionPerformed(ActionEvent ae){
        if(ae.getSource()==cliente){
            entidad=1;
            paintClientes();
            accion.setLabel("Mostrar Ordenes");
        }
        if(ae.getSource()==orden){
            entidad=2;
            paintOrdenes();
            accion.setLabel("Detalle");
        }
        if(ae.getSource()==merca){
            entidad=3;
            paintMercas();
        }
        if(ae.getSource()==empaq){
            entidad=4;
            paintEmpaqs();
        }
        if(ae.getSource()==impo){
            entidad=5;
            paintImports();
        }
        if(ae.getSource()==provee){
            entidad=6;
            paintProvs();
        }
        if(ae.getSource()==trab){
            entidad=7;
            paintTrabs();
        }
        if(ae.getSource()==caja){
            entidad=8;
            paintCajas();
        }
        if(ae.getSource()==contenedor){
            entidad=9;
            paintConts();
        }
        
        if(ae.getSource()==nuevo && entidad==1){
            Cliente.crearCliente(this);
        }
        if(ae.getSource()==nuevo && entidad==2){
            Orden.crearOrden(this);
        }
        if(ae.getSource()==nuevo && entidad==3){
            Mercaderia.crearMerc(this);
        }
        if(ae.getSource()==nuevo && entidad==4){
            Empaquetado.crearEmpaq(this);
        }
        if(ae.getSource()==nuevo && entidad==5){
            Importacion.crearImpo(this);
        }
        if(ae.getSource()==nuevo && entidad==6){
            Proveedor.crearProv(this);
        }
        if(ae.getSource()==nuevo && entidad==7){
            Trabajador.crearTrab(this);
        }
        if(ae.getSource()==nuevo && entidad==8){
            Caja.crearCaja(this);
        }
        if(ae.getSource()==nuevo && entidad==9){
            Contenedor.crearCont(this);
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
            int id = (int)tabla.getValueAt(tabla.getSelectedRow(),0);
            Cliente.eliminarCliente(id);
            paintClientes();
        }
        if(ae.getSource()==eliminar && entidad==2){
            int id = (int)tabla.getValueAt(tabla.getSelectedRow(),0);
            Orden.eliminarOrden(id);
            paintOrdenes();
        }
        if(ae.getSource()==eliminar && entidad==3){
            int id = (int)tabla.getValueAt(tabla.getSelectedRow(),0);
            Mercaderia.eliminarMerca(id);
        }
        if(ae.getSource()==eliminar && entidad==4){
            int id = (int)tabla.getValueAt(tabla.getSelectedRow(),0);
            Empaquetado.eliminarEmpaq(id);
            paintEmpaqs();
        }
        if(ae.getSource()==eliminar && entidad==5){
            int id = (int)tabla.getValueAt(tabla.getSelectedRow(),0);
            Importacion.eliminarImport(id);
            paintImports();
        }
        if(ae.getSource()==eliminar && entidad==6){
            int id = (int)tabla.getValueAt(tabla.getSelectedRow(),0);
            Proveedor.eliminarProv(id);
            paintProvs();
        }
        if(ae.getSource()==eliminar && entidad==7){
            int id = (int)tabla.getValueAt(tabla.getSelectedRow(),0);
            Trabajador.eliminarTrab(id);
            paintTrabs();
        }
        if(ae.getSource()==eliminar && entidad==8){
            int id = (int)tabla.getValueAt(tabla.getSelectedRow(),0);
            Caja.eliminarCaja(id);
            paintCajas();
        }
        if(ae.getSource()==eliminar && entidad==9){
            int id = (int)tabla.getValueAt(tabla.getSelectedRow(),0);
            Contenedor.eliminarCont(id);
            paintConts();
        }
        if(ae.getSource()==impor){
            intervalo();
        }
        if(ae.getSource()==exe && entidad==2){
            Factura.mostrarFact();
        }
        if(ae.getSource()==exe && entidad==1){
            idc=clientes.get((int)tabla.getSelectedRow()).id;
            this.panel.setBorder (BorderFactory.createTitledBorder (BorderFactory.createEtchedBorder (), "Ordenes de " + clientes.get(idc-1).nombre, TitledBorder.CENTER, TitledBorder.TOP));
            Object[] columns={"id", "Pais", "Ciudad", "Tiempo", "Numero", "Estado", "Cliente"};
            DefaultTableModel modelo2=new DefaultTableModel(null, columns);
            for(Orden o : ordenes)
                if(o.cliente==idc){
                    modelo2.addRow(o.arreglo());
                }
            this.tabla.setModel(modelo2);
            accion.setLabel("Detalle");
            entidad=2;
        }
        if(ae.getSource()==client){
            consultaClient();
        }
    }
    
    public void intervalo(){
        final JFrame interImpo = new JFrame("Seleccione Periodo de Tiempo Para Consulta");
        interImpo.setSize(500, 150);
        interImpo.setVisible(true);
        Panel panelPrin=new Panel(new GridLayout(3, 1));
        Panel panelIni=new Panel(new GridLayout(1, 4));
        Panel panelFin=new Panel(new GridLayout(1, 4));
        Panel panelboton=new Panel(new GridLayout(1, 2));
        Label labelIni=new Label("Fecha Inicial:", Label.CENTER);
        Label labelFin=new Label("Fecha Final:", Label.CENTER);
        Button guardar=new Button("Consultar");
        Button cancelar=new Button("Cancelar");
        final JComboBox cdia=new JComboBox();
        final JComboBox cmes=new JComboBox();
        final JComboBox caño=new JComboBox();
        for (int i=2000; i<2015; i++){
            caño.addItem(i);
        }
        for (int j=1; j<13; j++){
            cmes.addItem(j);
        }
        for (int i=1; i<32; i++){
            cdia.addItem(i);
        }
        final JComboBox cfdia=new JComboBox();
        final JComboBox cfmes=new JComboBox();
        final JComboBox cfaño=new JComboBox();
        for (int i=2000; i<2015; i++){
            cfaño.addItem(i);
        }
        for (int j=1; j<13; j++){
            cfmes.addItem(j);
        }
        for (int i=1; i<32; i++){
            cfdia.addItem(i);
        }
        panelIni.add(labelIni);
        panelIni.add(caño);
        panelIni.add(cmes);
        panelIni.add(cdia);
        panelFin.add(labelFin);
        panelFin.add(cfaño);
        panelFin.add(cfmes);
        panelFin.add(cfdia);
        panelboton.add(guardar);
        panelboton.add(cancelar);
        panelPrin.add(panelIni);
        panelPrin.add(panelFin);
        panelPrin.add(panelboton);
        interImpo.add(panelPrin);   
        guardar.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){
                entidad=0;
                panel.setBorder (BorderFactory.createTitledBorder (BorderFactory.createEtchedBorder (), "Importaciones", TitledBorder.CENTER, TitledBorder.TOP));
                Object[] columns={"id", "Trabajador", "Proveedor", "Dia", "Mes", "Año"};
                DefaultTableModel modelo5=new DefaultTableModel(null, columns);
                for(Importacion i : importaciones)
                    if(i.devolverFecha().before(new Date((int)cfaño.getSelectedItem(), (int)cfmes.getSelectedItem(),(int)cfdia.getSelectedItem())) && i.devolverFecha().after(new Date((int)caño.getSelectedItem(), (int)cmes.getSelectedItem(),(int)cdia.getSelectedItem()))){
                        modelo5.addRow(i.arreglo());
                    }
                tabla.setModel(modelo5);
                interImpo.dispose();
            }
        });
        cancelar.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){
                interImpo.dispose();
            }
        });
    }
    public void consultaClient(){
        final JFrame consClien = new JFrame("Escriba Cedula/Nombre Cliente");
        consClien.setSize(500, 100);
        consClien.setVisible(true);
        Panel panelPrin=new Panel(new GridLayout(3, 1));
        Panel panelced=new Panel(new GridLayout(1, 2));
        Panel panelnom=new Panel(new GridLayout(1, 2));
        Panel panelboton=new Panel(new GridLayout(1, 2));
        Label labelnom=new Label("Nombre:", Label.CENTER);
        Label labelced=new Label("Cedula:", Label.CENTER);
        final TextField txtced=new TextField("", 20);
        final TextField txtnom=new TextField("", 20);
        Button guardar=new Button("Consultar");
        Button cancelar=new Button("Cancelar");
        panelced.add(labelced);
        panelced.add(txtced);
        panelnom.add(labelnom);
        panelnom.add(txtnom);
        panelboton.add(guardar);
        panelboton.add(cancelar);
        panelPrin.add(panelnom);
        panelPrin.add(panelced);
        panelPrin.add(panelboton);
        consClien.add(panelPrin);   
        guardar.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){
                entidad=0;
                panel.setBorder (BorderFactory.createTitledBorder (BorderFactory.createEtchedBorder (), "Importaciones", TitledBorder.CENTER, TitledBorder.TOP));
                Object[] columns={"id", "Nombre", "Cedula", "Ruc", "Compañia", "Telf1"};
                DefaultTableModel modelo5=new DefaultTableModel(null, columns);
                try{
                    ResultSet rs=Cliente.ConsultaCliente(txtnom.getText(), txtced.getText());
                    
                    while(rs.next()){
                        Object[] fila={rs.getInt(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5), rs.getString(6)};
                        modelo5.addRow(fila);
                    }
                }catch(Exception ex){
                    
                }
                tabla.setModel(modelo5);
                consClien.dispose();
            }
        });
        cancelar.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){
                consClien.dispose();
            }
        });
    }
    
    public void paintClientes(){
        this.panel.setBorder (BorderFactory.createTitledBorder (BorderFactory.createEtchedBorder (), "Clientes", TitledBorder.CENTER, TitledBorder.TOP));
        Object[] columns={"id", "Nombre", "Cedula", "Compañia", "RUC", "Telefono"};
        DefaultTableModel modelo1=new DefaultTableModel(null, columns);
        ResultSet rs = Cliente.todosClientes();
        try {
            while(rs.next()){
                Object[] fila={rs.getInt(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5), rs.getString(6)};
                modelo1.addRow(fila);
            }
        } catch (SQLException ex) {
        }
        this.tabla.setModel(modelo1);
    }
    
    public void paintOrdenes(){
        this.panel.setBorder (BorderFactory.createTitledBorder (BorderFactory.createEtchedBorder (), "Orden", TitledBorder.CENTER, TitledBorder.TOP));
        Object[] columns={"id", "Cliente", "Pais", "Ciudad", "Fecha", "Tiempo", "Estado", "Numero"};
        DefaultTableModel modelo2=new DefaultTableModel(null, columns);
        ResultSet rs = Orden.todasOrdenes();
        try {
            while(rs.next()){
                Object[] fila={rs.getInt(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5), rs.getString(6), rs.getString(7), rs.getString(8)};
                modelo2.addRow(fila);
            }
        } catch (SQLException ex) {
        }
        this.tabla.setModel(modelo2);
    }
    
    public void paintMercas(){
        this.panel.setBorder (BorderFactory.createTitledBorder (BorderFactory.createEtchedBorder (), "Mercaderia", TitledBorder.CENTER, TitledBorder.TOP));
        Object[] columns={"id", "Estilo", "Marca", "Descripcion", "Composicion", "Cantidad", "Origen", "PVP", "Precio Compra",  "idOrden"};
        DefaultTableModel modelo3=new DefaultTableModel(null, columns);
        ResultSet rs = Mercaderia.todasMercas();
        try {
            while(rs.next()){
                Object[] fila={rs.getInt(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5), rs.getString(6), rs.getString(7), rs.getString(8), rs.getString(9), rs.getString(10)};
                modelo3.addRow(fila);
            }
        } catch (SQLException ex) {
        }
        this.tabla.setModel(modelo3);
    }
    
    public void paintEmpaqs(){
        this.panel.setBorder (BorderFactory.createTitledBorder (BorderFactory.createEtchedBorder (), "Empaquetado", TitledBorder.CENTER, TitledBorder.TOP));
        Object[] columns={"id", "Contenedor", "Mercaderia", "Caja", "Estado"};
        DefaultTableModel modelo4=new DefaultTableModel(null, columns);
        ResultSet rs = Empaquetado.todosEmpaqs();
        try {
            while(rs.next()){
                Object[] fila={rs.getInt(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5)};
                modelo4.addRow(fila);
            }
        } catch (SQLException ex) {
        }
        this.tabla.setModel(modelo4);
    }
    
    public void paintImports(){
        this.panel.setBorder (BorderFactory.createTitledBorder (BorderFactory.createEtchedBorder (), "Importaciones", TitledBorder.CENTER, TitledBorder.TOP));
        Object[] columns={"id", "Trabajador", "Proveedor", "Fecha"};
        DefaultTableModel modelo5=new DefaultTableModel(null, columns);
        ResultSet rs = Importacion.todasImport();
        try {
            while(rs.next()){
                Object[] fila={rs.getInt(1), rs.getString(2), rs.getString(3), rs.getString(4)};
                modelo5.addRow(fila);
            }
        } catch (SQLException ex) {
        }
        this.tabla.setModel(modelo5);
    }

    public void paintProvs(){
        this.panel.setBorder (BorderFactory.createTitledBorder (BorderFactory.createEtchedBorder (), "Proveedores", TitledBorder.CENTER, TitledBorder.TOP));
        Object[] columns={"id", "Compañia", "RUP", "Pais", "Ciudad", "Dueño", "Telefono"};
        DefaultTableModel modelo6=new DefaultTableModel(null, columns);
        ResultSet rs = Proveedor.todosProv();
        try {
            while(rs.next()){
                Object[] fila={rs.getInt(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5), rs.getString(6), rs.getString(7)};
                modelo6.addRow(fila);
            }
        } catch (SQLException ex) {
        }
        this.tabla.setModel(modelo6);
    }

    public void paintTrabs(){
        this.panel.setBorder (BorderFactory.createTitledBorder (BorderFactory.createEtchedBorder (), "Trabajadores", TitledBorder.CENTER, TitledBorder.TOP));
        Object[] columns={"id", "Nombre", "Cedula", "Puesto", "Telefono", "Sueldo", "E-Mail"};
        DefaultTableModel modelo7=new DefaultTableModel(null, columns);
        ResultSet rs = Trabajador.todosTrab();
        try {
            while(rs.next()){
                Object[] fila={rs.getInt(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5), rs.getString(6), rs.getString(7)};
                modelo7.addRow(fila);
            }
        } catch (SQLException ex) {
        }
        this.tabla.setModel(modelo7);
    }
    
    public void paintConts(){
        this.panel.setBorder (BorderFactory.createTitledBorder (BorderFactory.createEtchedBorder (), "Contenedores", TitledBorder.CENTER, TitledBorder.TOP));
        Object[] columns={"id", "Dimensiones", "Peso", "Estado", "Importacion"};
        DefaultTableModel modelo9=new DefaultTableModel(null, columns);
        ResultSet rs = Contenedor.todosConts();
        try {
            while(rs.next()){
                Object[] fila={rs.getInt(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5)};
                modelo9.addRow(fila);
            }
        } catch (SQLException ex) {
        }
        this.tabla.setModel(modelo9);        
    }
    
    public void paintCajas(){
        this.panel.setBorder (BorderFactory.createTitledBorder (BorderFactory.createEtchedBorder (), "Cajas", TitledBorder.CENTER, TitledBorder.TOP));
        Object[] columns={"id", "Numero", "Peso", "Estado", "Dimensiones"};
        DefaultTableModel modelo8=new DefaultTableModel(null, columns);
        ResultSet rs = Caja.todasCajas();
        try {
            while(rs.next()){
                Object[] fila={rs.getInt(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5)};
                modelo8.addRow(fila);
            }
        } catch (SQLException ex) {
        }
        this.tabla.setModel(modelo8);
    }
}
