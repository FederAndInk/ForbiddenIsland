package view;

import java.awt.CardLayout;
import java.awt.GridLayout;

import javax.swing.JFrame;
import javax.swing.JPanel;



public class MainView extends JFrame {
    
    JPanel                   main;
    JPanelPicture            picture;
    JPanelMenu               option;
    private JPanelSelectHero selectHero;
    
    private JPanel card;
    
    
    public MainView() {
        main = new JPanel(new GridLayout(1, 2));
        picture = new JPanelPicture();
        option = new JPanelMenu();
        card = new JPanel();
        
        card.setLayout(new CardLayout());
        
        // les layout
        
        // l'image
        card.add(picture, "picture");
        
        // la selection de hero
        selectHero = new JPanelSelectHero(this);
        card.add(selectHero, "heroSelection");
        
        getContentPane().add(main);
        main.add(option);
        
        main.add(picture);
        
    }
    
}