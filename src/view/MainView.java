package view;

import java.awt.CardLayout;
import java.awt.GridLayout;

import javax.swing.JFrame;
import javax.swing.JPanel;



public class MainView extends JFrame {
    
    private JPanel           main;
    private JPanel           lesBoutons;
    private JPanelPicture    picture;
    private JPanelMenu       mainMenu;
    private JPanelSelectHero selectHero;
    private JPanelTuto       tutoPanel;
    
    private JPanel card;
    
    
    public MainView() {
        
        lesBoutons = new JPanel(new GridLayout(1, 2));
        picture = new JPanelPicture();
        mainMenu = new JPanelMenu();
        card = new JPanel();
        main = new JPanel(new CardLayout());
        tutoPanel = new JPanelTuto();
        getContentPane().add(main);
        
        card.setLayout(new CardLayout());
        selectHero = new JPanelSelectHero();
        
        // les cardlayout
        
        // l'image
        card.add(picture, "picture");
        
        // la selection de hero
        card.add(selectHero, "heroSelection");
        
        main.add(lesBoutons, "lesBoutons");
        lesBoutons.add(mainMenu);
        lesBoutons.add(card);
        
        main.add(tutoPanel, "tutoPanel");
        
    }
    
}