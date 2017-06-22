package view;

import java.awt.*;
import java.util.Observer;

import javax.swing.JFrame;
import javax.swing.JPanel;

import controller.MainController;
import util.Parameters;
import util.exception.ExceptionType;
import view.pause.PausePanel;



public class MainView extends JFrame {
    
    private JPanel           main;
    private JPanel           lesBoutons;
    private JPanelPicture    picture;
    private JPanelMenu       mainMenu;
    private JPanelSelectHero selectHero;
    private JPanelTuto       tutoPanel;
    private MainController   controller;
    private JPanelChoixMap   choixMap;
    private JPanelFin        fin;
    private ExceptionType    exceptionType;
    private PausePanel       pausePanel;
    
    private JPanel card;
    
    
    public MainView() {
        
        lesBoutons = new JPanel(new GridLayout(1, 2));
        picture = new JPanelPicture();
        mainMenu = new JPanelMenu();
        card = new JPanel();
        main = new JPanel(new CardLayout());
        tutoPanel = new JPanelTuto();
        choixMap = new JPanelChoixMap();
        exceptionType = ExceptionType.END_GAME;
        fin = new JPanelFin(exceptionType);
        pausePanel = new PausePanel();
        getContentPane().add(main);
        
        card.setLayout(new CardLayout());
        selectHero = new JPanelSelectHero(mainMenu.getjeu());
        
        // les cardlayout
        
        // l'image
        card.add(picture, "picture");
        
        // la selection de hero
        card.add(selectHero, "heroSelection");
        
        // la selection de la map
        card.add(choixMap, "choixMap");
        
        // la fin
        card.add(fin, "fin");
        
        main.add(lesBoutons, "lesBoutons");
        lesBoutons.add(mainMenu);
        lesBoutons.add(card);
        
        // le tuto
        main.add(tutoPanel, "tutoPanel");
        // la pause
        main.add(pausePanel, "pause");
        
        initSize();
        
    }
    
    
    public void AddObs(Observer observer) {
        mainMenu.addObs(observer);
        selectHero.addObs(observer);
        choixMap.addObs(observer);
    }
    
    
    public void initSize() {
        if (!Parameters.fullscreen) {
            dispose();
            Parameters.appSize = new Dimension(650, 500);
            setLocation((int) (Parameters.screenSize.getWidth() / 2 - Parameters.appSize.getWidth() / 2),
                    (int) ((Parameters.screenSize.getHeight() - Parameters.appSize.getHeight()) / 2));
            setUndecorated(false);
            GraphicsEnvironment.getLocalGraphicsEnvironment().getDefaultScreenDevice().setFullScreenWindow(null);
            setExtendedState(NORMAL);
            setVisible(true);
        } else {
            dispose();
            Parameters.appSize = Toolkit.getDefaultToolkit().getScreenSize();
            setUndecorated(true);
            GraphicsEnvironment.getLocalGraphicsEnvironment().getDefaultScreenDevice().setFullScreenWindow(this);
            setExtendedState(MAXIMIZED_BOTH);
        } // end if
        setSize((int) (Parameters.appSize.getWidth()), (int) Parameters.appSize.getHeight());
    }
    
    
    public JPanel getCard() {
        return card;
    }
    
    
    public JPanelSelectHero getSelectHero() {
        return selectHero;
    }
    
    
    public JPanelMenu getMainMenu() {
        return mainMenu;
    }
    
    
    public void setExceptionType(ExceptionType exceptionType) {
        this.exceptionType = exceptionType;
    }
}