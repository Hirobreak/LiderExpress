package liderexpress;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;
import javax.swing.border.*;

public class loginScreen extends JFrame implements ActionListener{
    TextField usuario=new TextField("pepejefe", 10);
    TextField password=new TextField("1234567", 10);
    
    loginScreen(){
        Panel panelGeneral=new Panel(new GridLayout(3, 1));
        Panel panelnorte=new Panel(new GridLayout(1, 2));
        Panel panelcentro=new Panel(new GridLayout(1, 2));
        Panel panelsur=new Panel(new BorderLayout());
        Label user=new Label("Usuario:", Label.CENTER);
        Label pass=new Label("Contraseña:", Label.CENTER);
        Button ingresar= new Button("Ingresar");
        this.setTitle("Bienvenido a LiderExpress");
        this.setSize(500, 130);
        this.setVisible(true);
        this.add(panelGeneral);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        panelGeneral.add(panelnorte);
        panelGeneral.add(panelcentro);
        panelGeneral.add(panelsur);
        panelnorte.add(user);
        panelnorte.add(usuario);
        panelcentro.add(pass);
        panelcentro.add(password, BorderLayout.CENTER);
        panelsur.add(ingresar, BorderLayout.CENTER);
        ingresar.addActionListener(this);                                
    }
    
    @Override
    public void actionPerformed(ActionEvent ae){
        //if(this.usuario.getText().equals("Pedro") && this.password.getText().equals("Andres")){
          //  this.setVisible(false);
            MainMenu menu=new MainMenu();
            this.dispose();
       // }
    }
       
}
