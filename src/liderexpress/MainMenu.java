
package liderexpress;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;
import javax.swing.border.*;

public class MainMenu extends JFrame implements ActionListener{
    
        Cliente adminCliente = new Cliente(1,"admin","admin","admin","admin", 123, 123);
        Orden adminOrden = new Orden(1,"test","test","test","test","test",1);
        Mercaderia adminMercaderia = new Mercaderia(1,"test","test","test","test","test");
        Empaquetado adminEmpaquetado = new Empaquetado(1,1,1,1);
        Importacion adminImportacion = new Importacion(1,1,1,1,1,1);
        Proveedor adminImportador = new Proveedor (1,"test",1,"test","test","test",1);
        Trabajador adminTrabajador = new Trabajador(1,"test","test",1,1,1,"test");
        
        MenuBar entidades=new MenuBar();
        Menu cliente=new Menu("Cliente");
        Menu orden=new Menu("Orden");
        Menu mercaderia=new Menu("Mercaderia");
        Menu envio=new Menu("Empaquetado");
        Menu importacion=new Menu("Importacion");
        Menu importador=new Menu("Importador");
        Menu trabajador=new Menu("Trabajador");
        MenuItem cnuevo, ceditar, celiminar, cbuscar;
        MenuItem onuevo, oeditar, oeliminar, obuscar;
        MenuItem mnuevo, meditar, meliminar, mbuscar;
        MenuItem enuevo, eeditar, eeliminar, ebuscar;
        MenuItem innuevo, ineditar, ineliminar, inbuscar;
        MenuItem inuevo, ieditar, ieliminar, ibuscar;
        MenuItem tnuevo, teditar, teliminar, tbuscar;
    
    MainMenu(){
        
        this.setTitle("Bienvenido Usuario XYZ");
        this.setSize(700, 300);
        this.setVisible(true);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        cliente.add(cnuevo=new MenuItem("Nuevo"));
        cliente.add(ceditar=new MenuItem("Editar"));
        cliente.add(celiminar=new MenuItem("Eliminar"));
        cliente.add(cbuscar=new MenuItem("Buscar"));
        orden.add(onuevo=new MenuItem("Nuevo"));
        orden.add(oeditar=new MenuItem("Editar"));
        orden.add(oeliminar=new MenuItem("Eliminar"));
        orden.add(obuscar=new MenuItem("Buscar"));
        mercaderia.add(mnuevo=new MenuItem("Nuevo"));
        mercaderia.add(meditar=new MenuItem("Editar"));
        mercaderia.add(meliminar=new MenuItem("Eliminar"));
        mercaderia.add(mbuscar=new MenuItem("Buscar"));
        envio.add(enuevo=new MenuItem("Nuevo"));
        envio.add(eeditar=new MenuItem("Editar"));
        envio.add(eeliminar=new MenuItem("Eliminar"));
        envio.add(ebuscar=new MenuItem("Buscar"));
        importacion.add(innuevo=new MenuItem("Nuevo"));
        importacion.add(ineditar=new MenuItem("Editar"));
        importacion.add(ineliminar=new MenuItem("Eliminar"));
        importacion.add(inbuscar=new MenuItem("Buscar"));
        importador.add(inuevo=new MenuItem("Nuevo"));
        importador.add(ieditar=new MenuItem("Editar"));
        importador.add(ieliminar=new MenuItem("Eliminar"));
        importador.add(ibuscar=new MenuItem("Buscar"));
        trabajador.add(tnuevo=new MenuItem("Nuevo"));
        trabajador.add(teditar=new MenuItem("Editar"));
        trabajador.add(teliminar=new MenuItem("Eliminar"));
        trabajador.add(tbuscar=new MenuItem("Buscar"));
        entidades.add(cliente);
        entidades.add(orden);
        entidades.add(mercaderia);
        entidades.add(envio);
        entidades.add(importacion);
        entidades.add(importador);
        entidades.add(trabajador);
        setMenuBar(entidades);
        
        cnuevo.addActionListener(this);
        ceditar.addActionListener(this);
        celiminar.addActionListener(this);
        cbuscar.addActionListener(this);
        onuevo.addActionListener(this);
        oeditar.addActionListener(this);
        oeliminar.addActionListener(this);
        obuscar.addActionListener(this);
        mnuevo.addActionListener(this);
        meditar.addActionListener(this);
        meliminar.addActionListener(this);
        mbuscar.addActionListener(this);
        enuevo.addActionListener(this);
        eeditar.addActionListener(this);
        eeliminar.addActionListener(this);
        ebuscar.addActionListener(this);
        innuevo.addActionListener(this);
        ineditar.addActionListener(this);
        ineliminar.addActionListener(this);
        inbuscar.addActionListener(this);
        inuevo.addActionListener(this);
        ieditar.addActionListener(this);
        ieliminar.addActionListener(this);
        ibuscar.addActionListener(this);
        tnuevo.addActionListener(this);
        teditar.addActionListener(this);
        teliminar.addActionListener(this);
        tbuscar.addActionListener(this);
            
    }
    
    @Override
    public void actionPerformed(ActionEvent ae){
        if(ae.getSource()==cnuevo){
            Cliente.crearCliente();
        }
        if(ae.getSource()==ceditar){
            Cliente.modificarCliente(adminCliente);
        }
        if(ae.getSource()==onuevo){
            Orden.crearOrden();
        }
        if(ae.getSource()==oeditar){
            Orden.modificarOrden(adminOrden);
        }
        if(ae.getSource()==mnuevo){
            Mercaderia.crearMerc();
        }
        if(ae.getSource()==meditar){
            Mercaderia.modMerc(adminMercaderia);
        }
        if(ae.getSource()==enuevo){
            Empaquetado.crearEmpaq();
        }
        if(ae.getSource()==eeditar){
            Empaquetado.modEmpaq(adminEmpaquetado);
        }
        if(ae.getSource()==innuevo){
            Importacion.crearImpo();
        }
        if(ae.getSource()==ineditar){
            Importacion.modImpo(adminImportacion);
        }
        if(ae.getSource()==tnuevo){
            Trabajador.crearTrab();
        }
        if(ae.getSource()==teditar){
            Trabajador.modTrab(adminTrabajador);
        }
        if(ae.getSource()==inuevo){
            Proveedor.crearProv();
        }
        if(ae.getSource()==ieditar){
            Proveedor.modProv(adminImportador);
        }       
    }
    
}
