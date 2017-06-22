package view;

import java.awt.*;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.util.Observable;
import java.util.Observer;

import javax.swing.JFrame;
import javax.swing.JPanel;

import controller.MainController;
import util.Parameters;
import util.exception.ExceptionType;
import util.message.MainAction;
import util.message.MainMessage;
import view.board.GameView;
import view.pause.PausePanel;



public class MainView extends JFrame {
    
    private JPanel           main;
    private JPanel           lesBoutons;
    private JPanelPicture    picture;
    private JPanelMenu       mainMenu;
    private GameView         gameView;
    private JPanelSelectHero selectHero;
    private JPanelTuto       tutoPanel;
    private MainController   controller;
    private JPanelChoixMap   choixMap;
    private JPanelFin        fin;
    private PausePanel       pausePanel;
    private WList            wList;
    
    private JPanel card;
    
    
    public MainView() {
        
        lesBoutons = new JPanel(new GridLayout(1, 2));
        picture = new JPanelPicture();
        mainMenu = new JPanelMenu();
        gameView = new GameView();
        card = new JPanel();
        main = new JPanel(new CardLayout());
        tutoPanel = new JPanelTuto();
        choixMap = new JPanelChoixMap();
        fin = new JPanelFin(ExceptionType.END_GAME);
        pausePanel = new PausePanel();
        getContentPane().add(main);
        
        wList = new WList();
        addWindowListener(wList);
        
        card.setLayout(new CardLayout());
        selectHero = new JPanelSelectHero(mainMenu.getjeu());
        
        // les cardlayout
        
        // l'image
        card.add(picture, "picture");
        
        // la selection de hero
        card.add(selectHero, "heroSelection");
        
        // la selection de la map
        card.add(choixMap, "choixMap");
        
        main.add(lesBoutons, "lesBoutons");
        lesBoutons.add(mainMenu);
        lesBoutons.add(card);
        
        // Game
        gameView.setName("game");
        main.add(gameView, gameView.getName());
        
        // le tuto
        main.add(tutoPanel, "tutoPanel");
        // la pause
        main.add(pausePanel, "pause");
        // la fin
        fin.setName("fin");
        main.add(fin, fin.getName());
        
        initSize();
        
    }
    
    
    public void AddObs(Observer observer) {
        mainMenu.addObs(observer);
        selectHero.addObs(observer);
        choixMap.addObs(observer);
        wList.addObserver(observer);
    }
    
    
    public void initSize() {
        if (!Parameters.fullscreen) {
            dispose();
            Parameters.appSize = new Dimension((int) (Parameters.screenSize.getWidth() * 0.8),
                    (int) (Parameters.screenSize.getHeight() * 0.8));
            setLocation((int) (Parameters.screenSize.getWidth() / 2 - Parameters.appSize.getWidth() / 2),
                    (int) ((Parameters.screenSize.getHeight() - Parameters.appSize.getHeight()) / 2));
            setUndecorated(false);
            GraphicsEnvironment.getLocalGraphicsEnvironment().getDefaultScreenDevice().setFullScreenWindow(null);
            setExtendedState(MAXIMIZED_BOTH);
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
    
    
    /**
     * @return the gameView
     */
    public GameView getGameView() {
        return gameView;
    }
    
    
    public JPanelSelectHero getSelectHero() {
        return selectHero;
    }
    
    
    /**
     * @author nihil
     *
     */
    public void switchToGame() {
        ((CardLayout) main.getLayout()).show(main, gameView.getName());
    }
    
    
    /**
     * @author nihil
     *
     */
    public void switchToEnd(ExceptionType type) {
        fin.setExceptionType(type);
        ((CardLayout) main.getLayout()).show(main, fin.getName());
    }
    
    
    public JPanelMenu getMainMenu() {
        return mainMenu;
    }
    
    public class WList extends Observable implements WindowListener {
        
        /**
         * @see java.awt.event.WindowListener#windowOpened(java.awt.event.WindowEvent)
         */
        @Override
        public void windowOpened(WindowEvent e) {
        }
        
        
        /**
         * @see java.awt.event.WindowListener#windowClosing(java.awt.event.WindowEvent)
         */
        @Override
        public void windowClosing(WindowEvent e) {
            setChanged();
            notifyObservers(new MainMessage(MainAction.QUIT));
            clearChanged();
        }
        
        
        /**
         * @see java.awt.event.WindowListener#windowClosed(java.awt.event.WindowEvent)
         */
        @Override
        public void windowClosed(WindowEvent e) {
            
        }
        
        
        /**
         * @see java.awt.event.WindowListener#windowIconified(java.awt.event.WindowEvent)
         */
        @Override
        public void windowIconified(WindowEvent e) {
        }
        
        
        /**
         * @see java.awt.event.WindowListener#windowDeiconified(java.awt.event.WindowEvent)
         */
        @Override
        public void windowDeiconified(WindowEvent e) {
        }
        
        
        /**
         * @see java.awt.event.WindowListener#windowActivated(java.awt.event.WindowEvent)
         */
        @Override
        public void windowActivated(WindowEvent e) {
        }
        
        
        /**
         * @see java.awt.event.WindowListener#windowDeactivated(java.awt.event.WindowEvent)
         */
        @Override
        public void windowDeactivated(WindowEvent e) {
        }
        
    }
}