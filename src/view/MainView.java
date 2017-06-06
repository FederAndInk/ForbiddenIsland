package view;

import java.awt.GridLayout;

import javax.swing.JFrame;
import javax.swing.JPanel;



public class MainView extends JFrame {
    
    JPanel        main;
    JPanelPicture picture;
    JPanelMenu    option;
    
    
    public MainView() {
        main = new JPanel(new GridLayout(1, 2));
        picture = new JPanelPicture();
        option = new JPanelMenu();
        
        getContentPane().add(main);
        main.add(option);
        
        main.add(picture);
        
    }
    
}