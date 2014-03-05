
package liderexpress;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Date;
import javax.swing.*;
import javax.swing.border.*;
import javax.swing.table.DefaultTableModel;
import java.sql.*;
import java.util.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.io.Writer;
import javax.swing.table.TableCellRenderer;
import static liderexpress.Cliente.connect;
import static liderexpress.Empaquetado.newID;
import static liderexpress.QueryLog.log;

public class MainMenu extends JFrame implements ActionListener,QueryLog{
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
        Menu imprimir=new Menu("Imprimir");
        Menu opciones=new Menu("Opciones");
        Menu consulta=new Menu("Consultas");
        Menu accion=new Menu("No hay posible Accion");
        MenuItem cliente, orden, merca, empaq, impo, provee, trab, caja, contenedor;
        MenuItem nuevo, editar, eliminar, buscar;
        MenuItem client, impor;
        MenuItem exe, pdf;
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
        //setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.addWindowListener(new java.awt.event.WindowAdapter() {
        @Override
        public void windowClosing(WindowEvent e) {
            BufferedWriter writer = null; 
            try {
                writer = new BufferedWriter(new FileWriter("output.txt",true));
            } catch (IOException ex) {
                Logger.getLogger(MainMenu.class.getName()).log(Level.SEVERE, null, ex);
            }
            for(String str: log) {
                try {
                    writer.append(str);
                    writer.newLine();
                } catch (IOException ex) {
                    Logger.getLogger(MainMenu.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            try {
                writer.close();
            } catch (IOException ex) {
                Logger.getLogger(MainMenu.class.getName()).log(Level.SEVERE, null, ex);
            }
            System.exit(0);
        }
        });
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
        imprimir.add(pdf=new MenuItem("PDF"));
        accion.add(exe=new MenuItem("Ejecutar"));
        entidades.add(mostrar);
        entidades.add(opciones);
        entidades.add(consulta);
        entidades.add(accion);
        entidades.add(imprimir);
        setMenuBar(entidades);
        JScrollPane listScroller = new JScrollPane(tabla);
        listScroller.setPreferredSize(new Dimension(650, 200));
        panel.add (listScroller, c);
        add(panel,BorderLayout.NORTH);
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
        pdf.addActionListener(this);
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
            accion.setLabel("Detalle Factura");
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
            accion.setLabel("Detalle Factura");
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
            int id = (int)tabla.getValueAt(tabla.getSelectedRow(),0);
            Cliente.modificarCliente(id, this);
            paintClientes();
        }
        if(ae.getSource()==editar && entidad==2){
            int id = (int)tabla.getValueAt(tabla.getSelectedRow(),0);
            Orden.modificarOrden(id, this);
        }
        if(ae.getSource()==editar && entidad==3){
            int id = (int)tabla.getValueAt(tabla.getSelectedRow(),0);
            Mercaderia.modMerc(id, this);
        }
        if(ae.getSource()==editar && entidad==4){
            int id = (int)tabla.getValueAt(tabla.getSelectedRow(),0);
            Empaquetado.modEmpaq(id, this);
        }
        if(ae.getSource()==editar && entidad==5){
            int id = (int)tabla.getValueAt(tabla.getSelectedRow(),0);
            Importacion.modImpo(id, this);
        }
        if(ae.getSource()==editar && entidad==6){
            int id = (int)tabla.getValueAt(tabla.getSelectedRow(),0);
            Proveedor.modProv(id, this);
        }
        if(ae.getSource()==editar && entidad==7){
            int id = (int)tabla.getValueAt(tabla.getSelectedRow(),0);
            Trabajador.modTrab(id, this);
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
            paintMercas();
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
        if(ae.getSource()==buscar && entidad==1){
            consultaClient();
        }
        if(ae.getSource()==buscar && entidad==2){
            searchOrden();
        }
        if(ae.getSource()==buscar && entidad==3){
            consultaMerca();
        }
        if(ae.getSource()==buscar && entidad==4){
            consultaEmp();
        }
        if(ae.getSource()==buscar && entidad==5){
            searchImpo();
        }
        if(ae.getSource()==buscar && entidad==6){
            buscarProv();
        }
        if(ae.getSource()==buscar && entidad==7){
            buscarTrab();
        }
        if(ae.getSource()==impor){
            intervalo();
        }
        if(ae.getSource()==exe && entidad==2){
            int id = (int)tabla.getValueAt(tabla.getSelectedRow(),0);
            Factura.mostrarFact(id);
        }
        if(ae.getSource()==exe && entidad==5){
            int id = (int)tabla.getValueAt(tabla.getSelectedRow(),0);
            Factura2.mostrarFact(id);
        }
        if(ae.getSource()==exe && entidad==1){
            int id = (int)tabla.getValueAt(tabla.getSelectedRow(),0);
            paintOrdenesCliente(id);
            /*this.tabla.setModel(modelo2);
            accion.setLabel("Detalle");
            entidad=2;*/
        }
        if(ae.getSource()==client){
            consultaClient();
        }
        if(ae.getSource()==pdf && entidad==1){
            int id = (int)tabla.getValueAt(tabla.getSelectedRow(),0);
            pdf reporte;
            reporte = new pdf();
            reporte.newpdf(String.valueOf(id),entidad);
           
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
    public void buscarProv(){
        final JFrame jBusProv = new JFrame("Modificacion de Importador");
        jBusProv.setSize(500, 300);
        jBusProv.setVisible(true);
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
        Button guardar=new Button("Buscar");
        Button cancelar=new Button("Cancelar");
        final TextField txtCom=new TextField("", 20);
        final TextField txtRUP=new TextField("", 20);
        final TextField txtDueño=new TextField("", 20);
        final TextField txtPais=new TextField("", 20);
        final TextField txtTelf=new TextField("", 20);
        final TextField txtCiudad=new TextField("", 20);
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
        jBusProv.add(panelPrin);
        guardar.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){
                panel.setBorder (BorderFactory.createTitledBorder (BorderFactory.createEtchedBorder (), "Proveedores", TitledBorder.CENTER, TitledBorder.TOP));
                Object[] columns={"id", "Compañia", "RUP", "Pais", "Ciudad", "Dueño", "Telefono"};
                DefaultTableModel modelo6=new DefaultTableModel(null, columns);
                if (txtCom.getText().compareTo("")!=0 || txtRUP.getText().compareTo("")!=0 || txtDueño.getText().compareTo("")!=0 || txtPais.getText().compareTo("")!=0 || txtTelf.getText().compareTo("")!=0 || txtCiudad.getText().compareTo("")!=0){
                    try {
                        ResultSet rs = Proveedor.buscarProve(txtCom.getText(), txtRUP.getText(), txtDueño.getText(), txtPais.getText(), txtTelf.getText(), txtCiudad.getText());
                        while(rs.next()){
                            Object[] fila={rs.getInt(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5), rs.getString(6), rs.getString(7)};
                            modelo6.addRow(fila);
                            }
                    } catch (SQLException ex) {}
                }
                tabla.setModel(modelo6);
                jBusProv.dispose();
            }
        });
        cancelar.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){
                jBusProv.dispose();
            }
        });
        
    }
    public void buscarTrab(){
        final JFrame jBusT = new JFrame("Buscar Trabajador");
        jBusT.setSize(500, 300);
        jBusT.setVisible(true);
        Panel panelPrin=new Panel(new GridLayout(7, 1));
        Panel panelNom=new Panel(new GridLayout(1, 2));
        Panel panelced=new Panel(new GridLayout(1, 2));
        Panel panelcargo=new Panel(new GridLayout(1, 2));
        Panel panelsal=new Panel(new GridLayout(1, 2));
        Panel panelmail=new Panel(new GridLayout(1, 2));
        Panel paneltelf=new Panel(new GridLayout(1, 3));
        Panel panelboton=new Panel(new GridLayout(1, 2));
        Label labelNom=new Label("Nombre:", Label.CENTER);
        Label labelcargo=new Label("Cargo:", Label.CENTER);
        Label labelced=new Label("Cedula:", Label.CENTER);
        Label labelsal=new Label("Salario:", Label.CENTER);
        Label labelmail=new Label("E-Mail:", Label.CENTER);
        Label labeltelf=new Label("Telefonos:", Label.CENTER);
        Button buscar=new Button("Buscar");
        Button cancelar=new Button("Cancelar");
        final TextField txtNom=new TextField("", 20);
        final TextField txtCargo=new TextField("", 20);
        final TextField txtCedula=new TextField("", 20);
        final TextField txtSalario=new TextField("", 20);
        final TextField txtTelf=new TextField("", 20);
        final TextField txtMail=new TextField("", 20);
        panelNom.add(labelNom);
        panelNom.add(txtNom);
        panelced.add(labelced);
        panelced.add(txtCedula);
        panelcargo.add(labelcargo);
        panelcargo.add(txtCargo);
        panelsal.add(labelsal);
        panelsal.add(txtSalario);
        paneltelf.add(labeltelf);
        paneltelf.add(txtTelf);
        panelmail.add(labelmail);
        panelmail.add(txtMail);
        panelboton.add(buscar);
        panelboton.add(cancelar);
        panelPrin.add(panelNom);
        panelPrin.add(panelced);
        panelPrin.add(panelcargo);
        panelPrin.add(panelsal);
        panelPrin.add(paneltelf);
        panelPrin.add(panelmail);
        panelPrin.add(panelboton);
        jBusT.add(panelPrin);
        buscar.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){
                panel.setBorder (BorderFactory.createTitledBorder (BorderFactory.createEtchedBorder (), "Trabajadores", TitledBorder.CENTER, TitledBorder.TOP));
                Object[] columns={"id", "Nombre", "Cedula", "Puesto", "Telefono", "Sueldo", "E-Mail"};
                DefaultTableModel modelo7=new DefaultTableModel(null, columns);
                if (txtMail.getText().compareTo("")!=0 || txtNom.getText().compareTo("")!=0 || txtCedula.getText().compareTo("")!=0 || txtCargo.getText().compareTo("")!=0 || txtSalario.getText().compareTo("")!=0 || txtTelf.getText().compareTo("")!=0){
                    try{
                        ResultSet rs=Trabajador.buscarTraba(txtMail.getText(), txtNom.getText(), txtCedula.getText(), txtCargo.getText(), txtSalario.getText(), txtTelf.getText());
                        while(rs.next()){
                            Object[] fila={rs.getInt(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5), rs.getString(6), rs.getString(7)};
                            modelo7.addRow(fila);
                        }
                    } catch (SQLException ex) {}
                }
            tabla.setModel(modelo7);
            jBusT.dispose();
        }
            
        });
        cancelar.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){
                jBusT.dispose();
            }
        });
    }
    public void consultaClient(){
        final JFrame consClien = new JFrame("Busqueda de Clientes");
        consClien.setSize(500, 150);
        consClien.setVisible(true);
        Panel panelPrin=new Panel(new GridLayout(4, 1));
        Panel panelced=new Panel(new GridLayout(1, 2));
        Panel panelnom=new Panel(new GridLayout(1, 2));
        Panel paneltel=new Panel(new GridLayout(1, 2));
        Panel panelboton=new Panel(new GridLayout(1, 2));
        Label labelnom=new Label("Nombre:", Label.CENTER);
        Label labelced=new Label("Cedula:", Label.CENTER);
        Label labeltelf=new Label("Telefono:", Label.CENTER);
        final TextField txtced=new TextField("", 20);
        final TextField txtnom=new TextField("", 20);
        final TextField txttelf=new TextField("", 20);
        Button guardar=new Button("Consultar");
        Button cancelar=new Button("Cancelar");
        panelced.add(labelced);
        panelced.add(txtced);
        panelnom.add(labelnom);
        panelnom.add(txtnom);
        paneltel.add(labeltelf);
        paneltel.add(txttelf);
        panelboton.add(guardar);
        panelboton.add(cancelar);
        panelPrin.add(panelnom);
        panelPrin.add(panelced);
        panelPrin.add(paneltel);
        panelPrin.add(panelboton);
        consClien.add(panelPrin);   
        guardar.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){
                panel.setBorder (BorderFactory.createTitledBorder (BorderFactory.createEtchedBorder (), "Resultado de Clientes", TitledBorder.CENTER, TitledBorder.TOP));
                Object[] columns={"id", "Nombre", "Cedula", "Ruc", "Compañia", "Telf1"};
                DefaultTableModel modelo5=new DefaultTableModel(null, columns);
                try{
                    ResultSet rs=Cliente.ConsultaCliente(txtnom.getText(), txtced.getText(), txttelf.getText());
                    
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
    
    public void searchOrden(){
        final JFrame consClien = new JFrame("Busqueda de Clientes");
        consClien.setSize(600, 200);
        consClien.setVisible(true);
        Panel panelPrin=new Panel(new GridLayout(5, 1));
        Panel panelclien=new Panel(new GridLayout(1, 2));
        Panel panelfecha=new Panel(new GridLayout(1, 4));
        Panel panelest=new Panel(new GridLayout(1, 2));
        Panel panelnum=new Panel(new GridLayout(1, 2));
        Panel panelboton=new Panel(new GridLayout(1, 2));
        Label labelclien=new Label("Cliente:", Label.CENTER);
        Label labelfech=new Label("Fecha:", Label.CENTER);
        Label labelest=new Label("Estado:", Label.CENTER);
        Label labelnum=new Label("Numero de Rastreo:", Label.CENTER);
        final TextField txtclien=new TextField("", 20);
        final TextField txtnumr=new TextField("", 20);
        final JComboBox txtest=new JComboBox();
        final JComboBox txtAño=new JComboBox();
        final JComboBox txtMes=new JComboBox();
        final JComboBox txtDia=new JComboBox();
        txtest.addItem("");
        txtest.addItem("Pendiente");
        txtest.addItem("Tramitada");
        txtest.addItem("Entregada");
        txtAño.addItem(0);
        for (int i=2000; i<2015; i++){
            txtAño.addItem(i);
        }
        for (int j=0; j<13; j++){
            txtMes.addItem(j);
        }
        for (int i=0; i<32; i++){
            txtDia.addItem(i);
        }
        Button guardar=new Button("Consultar");
        Button cancelar=new Button("Cancelar");
        panelclien.add(labelclien);
        panelclien.add(txtclien);
        panelfecha.add(labelfech);
        panelfecha.add(txtAño);
        panelfecha.add(txtMes);
        panelfecha.add(txtDia);
        panelest.add(labelest);
        panelest.add(txtest);
        panelnum.add(labelnum);
        panelnum.add(txtnumr);
        panelboton.add(guardar);
        panelboton.add(cancelar);
        panelPrin.add(panelclien);
        panelPrin.add(panelfecha);
        panelPrin.add(panelest);
        panelPrin.add(panelnum);
        panelPrin.add(panelboton);
        consClien.add(panelPrin);   
        guardar.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){
                panel.setBorder (BorderFactory.createTitledBorder (BorderFactory.createEtchedBorder (), "Resultados de Ordenes", TitledBorder.CENTER, TitledBorder.TOP));
                Object[] columns={"id", "Cliente", "Pais", "Ciudad", "Fecha", "Tiempo", "Estado", "Numero"};
                DefaultTableModel modelo11=new DefaultTableModel(null, columns);
                try{
                    ResultSet rs=Orden.ConsultaOrden(txtclien.getText(), txtAño.getSelectedItem().toString(),txtMes.getSelectedItem().toString(),txtDia.getSelectedItem().toString(), txtest.getSelectedItem().toString(), txtnumr.getText());
                    
                    while(rs.next()){
                        Object[] fila={rs.getInt(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5), rs.getString(6), rs.getString(7), rs.getString(8)};
                        modelo11.addRow(fila);
                    }
                }catch(Exception ex){
                    
                }
                tabla.setModel(modelo11);
                consClien.dispose();
            }
        });
        cancelar.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){
                consClien.dispose();
            }
        });
    }
    
    public void consultaMerca(){
        final JFrame consClien = new JFrame("Busqueda de Mercaderia");
        consClien.setSize(500, 150);
        consClien.setVisible(true);
        Panel panelPrin=new Panel(new GridLayout(4, 1));
        Panel panelsty=new Panel(new GridLayout(1, 2));
        Panel panelmar=new Panel(new GridLayout(1, 2));
        Panel panelmm=new Panel(new GridLayout(1, 4));
        Panel panelboton=new Panel(new GridLayout(1, 2));
        Label labelsty=new Label("Estilo:", Label.CENTER);
        Label labelmar=new Label("Marca:", Label.CENTER);
        Label labelma=new Label("Menor que:", Label.CENTER);
        Label labelme=new Label(", Mayor que:", Label.CENTER);
        final TextField txtsty=new TextField("", 20);
        final TextField txtmar=new TextField("", 20);
        final TextField txtma=new TextField("0", 20);
        final TextField txtme=new TextField("0", 20);
        Button guardar=new Button("Consultar");
        Button cancelar=new Button("Cancelar");
        panelsty.add(labelsty);
        panelsty.add(txtsty);
        panelmar.add(labelmar);
        panelmar.add(txtmar);
        panelmm.add(labelma);
        panelmm.add(txtma);
        panelmm.add(labelme);
        panelmm.add(txtme);
        panelboton.add(guardar);
        panelboton.add(cancelar);
        panelPrin.add(panelsty);
        panelPrin.add(panelmar);
        panelPrin.add(panelmm);
        panelPrin.add(panelboton);
        consClien.add(panelPrin);   
        guardar.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){
                panel.setBorder (BorderFactory.createTitledBorder (BorderFactory.createEtchedBorder (), "Resultado de Mercaderia", TitledBorder.CENTER, TitledBorder.TOP));
                Object[] columns={"id", "Estilo", "Marca", "Descripcion", "Composicion", "Cantidad", "Origen", "PVP", "Precio Compra", "idOrden"};
                DefaultTableModel modelo14=new DefaultTableModel(null, columns);
                try{
                    ResultSet rs=Mercaderia.consultaMerca(txtsty.getText(), txtmar.getText(), Integer.parseInt(txtma.getText()), Integer.parseInt(txtme.getText()));
                    
                    while(rs.next()){
                        Object[] fila={rs.getInt(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5), rs.getString(6), rs.getString(7), rs.getString(8), rs.getString(9), rs.getString(10)};
                        modelo14.addRow(fila);
                    }
                }catch(Exception ex){
                    
                }
                tabla.setModel(modelo14);
                consClien.dispose();
            }
        });
        cancelar.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){
                consClien.dispose();
            }
        });
    }
    
    public void consultaEmp(){
        final JFrame consClien = new JFrame("Busqueda de Empaques");
        consClien.setSize(600, 150);
        consClien.setVisible(true);
        Panel panelPrin=new Panel(new GridLayout(4, 1));
        Panel panelest=new Panel(new GridLayout(1, 2));
        Panel panelnum=new Panel(new GridLayout(1, 2));
        Panel panelsta=new Panel(new GridLayout(1, 2));
        Panel panelboton=new Panel(new GridLayout(1, 2));
        Label labelest=new Label("Estilo Mercaderia:", Label.CENTER);
        Label labelnum=new Label("Numero de Caja:", Label.CENTER);
        Label labelsta=new Label("Estado:", Label.CENTER);
        final TextField txtest=new TextField("", 20);
        final TextField txtnum=new TextField("", 20);
        final JComboBox estados=new JComboBox();
        estados.addItem("");
        estados.addItem("Embarcado");
        estados.addItem("Desmontado");
        Button guardar=new Button("Consultar");
        Button cancelar=new Button("Cancelar");
        panelest.add(labelest);
        panelest.add(txtest);
        panelnum.add(labelnum);
        panelnum.add(txtnum);
        panelsta.add(labelsta);
        panelsta.add(estados);
        panelboton.add(guardar);
        panelboton.add(cancelar);
        panelPrin.add(panelnum);
        panelPrin.add(panelest);
        panelPrin.add(panelsta);
        panelPrin.add(panelboton);
        consClien.add(panelPrin);   
        guardar.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){
                panel.setBorder (BorderFactory.createTitledBorder (BorderFactory.createEtchedBorder (), "Resultado de Empaques", TitledBorder.CENTER, TitledBorder.TOP));
                Object[] columns={"id", "Contenedor", "Mercaderia", "Caja", "Estado"};
                DefaultTableModel modelo16=new DefaultTableModel(null, columns);
                try{
                    ResultSet rs=Empaquetado.consultaEmpq(txtest.getText(), txtnum.getText(), estados.getSelectedItem().toString());
                    
                    while(rs.next()){
                        Object[] fila={rs.getInt(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5)};
                        modelo16.addRow(fila);
                    }
                }catch(Exception ex){
                    
                }
                tabla.setModel(modelo16);
                consClien.dispose();
            }
        });
        cancelar.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){
                consClien.dispose();
            }
        });
    }
    
    public void searchImpo(){
        final JFrame consClien = new JFrame("Busqueda de Clientes");
        consClien.setSize(600, 200);
        consClien.setVisible(true);
        Panel panelPrin=new Panel(new GridLayout(4, 1));
        Panel paneltrab=new Panel(new GridLayout(1, 2));
        Panel panelfecha=new Panel(new GridLayout(1, 4));
        Panel panelprov=new Panel(new GridLayout(1, 2));
        Panel panelboton=new Panel(new GridLayout(1, 2));
        Label labeltrab=new Label("Cedula Trabajador:", Label.CENTER);
        Label labelfech=new Label("Fecha:", Label.CENTER);
        Label labelprov=new Label("Nombre Compañia:", Label.CENTER);
        final TextField txttrab=new TextField("", 20);
        final TextField txtprov=new TextField("", 20);
        final JComboBox txtAño=new JComboBox();
        final JComboBox txtMes=new JComboBox();
        final JComboBox txtDia=new JComboBox();
        txtAño.addItem(0);
        for (int i=2000; i<2015; i++){
            txtAño.addItem(i);
        }
        for (int j=0; j<13; j++){
            txtMes.addItem(j);
        }
        for (int i=0; i<32; i++){
            txtDia.addItem(i);
        }
        Button guardar=new Button("Consultar");
        Button cancelar=new Button("Cancelar");
        paneltrab.add(labeltrab);
        paneltrab.add(txttrab);
        panelfecha.add(labelfech);
        panelfecha.add(txtAño);
        panelfecha.add(txtMes);
        panelfecha.add(txtDia);
        panelprov.add(labelprov);
        panelprov.add(txtprov);
        panelboton.add(guardar);
        panelboton.add(cancelar);
        panelPrin.add(paneltrab);
        panelPrin.add(panelprov);
        panelPrin.add(panelfecha);
        panelPrin.add(panelboton);
        consClien.add(panelPrin);   
        guardar.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){
                panel.setBorder (BorderFactory.createTitledBorder (BorderFactory.createEtchedBorder (), "Resultados de Ordenes", TitledBorder.CENTER, TitledBorder.TOP));
                Object[] columns={"id", "Trabajador", "Proveedor", "Fecha"};
                DefaultTableModel modelo17=new DefaultTableModel(null, columns);
                try{
                    ResultSet rs=Importacion.consultarImpo(txttrab.getText(), txtprov.getText().toString(), txtAño.getSelectedItem().toString(),txtMes.getSelectedItem().toString(),txtDia.getSelectedItem().toString());
                    
                    while(rs.next()){
                        Object[] fila={rs.getInt(1), rs.getString(2), rs.getString(3), rs.getString(4)};
                        modelo17.addRow(fila);
                    }
                }catch(Exception ex){
                    
                }
                tabla.setModel(modelo17);
                consClien.dispose();
            }
        });
        cancelar.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){
                consClien.dispose();
            }
        });
    }
   
    public void paintOrdenesCliente(int id_cliente){
        ResultSet rs = Cliente.ordenesDeCliente(id_cliente);
        panel.setBorder (BorderFactory.createTitledBorder (BorderFactory.createEtchedBorder (), "Ordenes del Cliente ID: "+id_cliente, TitledBorder.CENTER, TitledBorder.TOP));
        Object[] columns={"id", "Cliente", "Pais", "Ciudad", "Fecha", "Tiempo", "Estado", "Numero"};
        DefaultTableModel modelo2=new DefaultTableModel(null, columns);
        try {
            while(rs.next()){
                Object[] fila={rs.getInt(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5), rs.getString(6), rs.getString(7), rs.getString(8)};
                modelo2.addRow(fila);
            }
        } catch (SQLException ex) {
        }
        this.tabla.setModel(modelo2);
    }
    
    public void paintClientes(){
        this.panel.setBorder (BorderFactory.createTitledBorder (BorderFactory.createEtchedBorder (), "Clientes", TitledBorder.CENTER, TitledBorder.TOP));
        Object[] columns={"id", "Nombre", "Cedula", "RUC", "Compañia", "Telefono"};
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
        Object[] columns={"id", "Estilo", "Marca", "Descripcion", "Composicion", "Cantidad", "Origen", "PVP", "Precio Compra", "idOrden"};
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
