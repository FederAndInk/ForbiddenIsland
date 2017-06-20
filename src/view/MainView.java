package view;

import java.awt.CardLayout;
import java.awt.GridLayout;

import javax.swing.JFrame;
import javax.swing.JPanel;



public class MainView extends JFrame {
    
    private JPanel           main;
    private JPanelPicture    picture;
    private JPanelMenu       mainMenu;
    private JPanelSelectHero selectHero;
    
    private JPanel card;
    
    
    public MainView() {
        main = new JPanel(new GridLayout(1, 2));
        picture = new JPanelPicture();
        mainMenu = new JPanelMenu();
        card = new JPanel();
        
        card.setLayout(new CardLayout());
        selectHero = new JPanelSelectHero(this);
        
        // les layout
        
        // l'image
        card.add(picture, "picture");
        
        // la selection de hero
        card.add(selectHero, "heroSelection");
        
        getContentPane().add(main);
        main.add(mainMenu);
        main.add(card);
        
    }
    
}