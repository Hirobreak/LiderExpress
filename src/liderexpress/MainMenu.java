
package liderexpress;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;
import javax.swing.border.*;

public class MainMenu extends JFrame{
    MainMenu(){
        MenuBar entidades=new MenuBar();
        Menu cliente=new Menu("Cliente");
        Menu orden=new Menu("Orden");
        Menu mercaderia=new Menu("Mercaderia");
        Menu ordenMerc=new Menu("OrdenMercaderia");
        Menu envio=new Menu("Envio");
        Menu importacion=new Menu("Importacion");
        Menu importador=new Menu("Importador");
        Menu trabajador=new Menu("Trabajador");
        this.setTitle("Bienvenido Usuario XYZ");
        this.setSize(500, 300);
        this.setVisible(true);
        cliente.add("Nuevo");
        cliente.add("Editar");
        cliente.add("Eliminar");
        cliente.add("Buscar");
        orden.add("Nuevo");
        orden.add("Editar");
        orden.add("Eliminar");
        orden.add("Buscar");
        mercaderia.add("Nuevo");
        mercaderia.add("Editar");
        mercaderia.add("Eliminar");
        mercaderia.add("Buscar");
        envio.add("Nuevo");
        envio.add("Editar");
        envio.add("Eliminar");
        envio.add("Buscar");
        importacion.add("Nuevo");
        importacion.add("Editar");
        importacion.add("Eliminar");
        importacion.add("Buscar");
        importador.add("Nuevo");
        importador.add("Editar");
        importador.add("Eliminar");
        importador.add("Buscar");
        trabajador.add("Nuevo");
        trabajador.add("Editar");
        trabajador.add("Eliminar");
        trabajador.add("Buscar");
        ordenMerc.add("Nuevo");
        ordenMerc.add("Editar");
        ordenMerc.add("Eliminar");
        ordenMerc.add("Buscar");
        entidades.add(cliente);
        entidades.add(orden);
        entidades.add(mercaderia);
        entidades.add(ordenMerc);
        entidades.add(envio);
        entidades.add(importacion);
        entidades.add(importador);
        entidades.add(trabajador);
        setMenuBar(entidades);
        
        
    }
}
