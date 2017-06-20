package view.board;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GraphicsEnvironment;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.util.ArrayList;
import java.util.Observable;
import java.util.Observer;

import javax.swing.BorderFactory;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JLayeredPane;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JRadioButtonMenuItem;
import javax.swing.JTextPane;
import javax.swing.UIManager;

import model.adventurers.AdventurerType;
import model.game.Coords;
import model.game.Island;
import model.game.Site;
import model.game.TileState;
import util.BoardType;
import util.LogType;
import util.Parameters;
import util.message.InGameAction;
import util.message.InGameMessage;
import util.message.MainAction;
import util.message.MainMessage;
import view.player.Icone;
import view.player.playerInventory;



public class GameView extends JFrame {
    private static final String END_TURN     = "endTurn";
    private static final String MOVE         = "move";
    private static final String SHORE_UP     = "shore";
    private static final String USE_CAPACITY = "cap";
    private static final String NEW_GAME     = "newGame";
    private static final String QUIT         = "quit";
    
    private JPanel     mainPane;
    private BoardPanel gamePane;
    private JPanel     eastPane;
    private JPanel     westPane;
    private JPanel     actionCommands;
    
    private JMenuBar  bar;
    private JMenu     option;
    private JMenuItem newGame;
    
    private JMenu                gameOpt;
    private JMenu                board;
    private JRadioButtonMenuItem defaultB;
    private JRadioButtonMenuItem hardTestB;
    private ButtonGroup          grpBoard;
    private JMenu                playerSelect;
    private JRadioButtonMenuItem randomP;
    private ButtonGroup          grpPlayer;
    private JMenuItem            quit;
    
    private JTextPane     messages;
    private JLabel        infoPlayerC;
    private PawnComponant currentP;
    private JPanel        info;
    
    // player info
    private JPanel          paneDroit;
    private Icone           player3spe;
    private Icone           player4spe;
    private playerInventory inventory;
    // private JPanel playerSpe3;
    // private JPanel playerSpe4;
    // private JPanel icone3;
    // private JPanel descriptionSpe3;
    // private JPanel icone4;
    // private JPanel descriptionSpe4;
    private JPanel flood;
    
    private JButton endTurnBtn;
    private JButton moveBtn;
    private JButton shoreUpBtn;
    private JButton useCapacityBtn;
    
    private ListenerAction listObs;
    
    
    public GameView() {
        super();
        
        initComponents();
        setScreen();
        Parameters.appSize = getSize();
        
        initPlayerState();
        initPlayerInventory();
        initListeners();
        
    }
    
    
    private void initPlayerInventory() {
        inventory = new playerInventory();
        mainPane.add(inventory, BorderLayout.NORTH);
    }
    
    
    /**
     * @author nihil
     *
     */
    private void initComponents() {
        mainPane = new JPanel(new BorderLayout());
        
        eastPane = new JPanel(new BorderLayout());
        westPane = new JPanel(new BorderLayout());
        actionCommands = new JPanel(new GridLayout(4, 1));
        
        bar = new JMenuBar();
        option = new JMenu("Options");
        newGame = new JMenuItem("Nouvelle Partie");
        
        gameOpt = new JMenu("Option de nouvelle partie");
        board = new JMenu("Choix du Plateau");
        defaultB = new JRadioButtonMenuItem("Defaut");
        hardTestB = new JRadioButtonMenuItem("Test hard");
        grpBoard = new ButtonGroup();
        playerSelect = new JMenu("Selection des Aventuriers");
        randomP = new JRadioButtonMenuItem("Aleatoire");
        grpPlayer = new ButtonGroup();
        
        quit = new JMenuItem("Quitter");
        
        endTurnBtn = new JButton("Fin de tour");
        moveBtn = new JButton("Se déplacer");
        shoreUpBtn = new JButton("Assécher un endroit");
        useCapacityBtn = new JButton("Utiliser sa capacité");
        
        messages = new JTextPane();
        infoPlayerC = new JLabel("Joueur ");
        currentP = new PawnComponant(AdventurerType.DIVER);
        info = new JPanel(new GridLayout(2, 1));
        
        setJMenuBar(bar);
        bar.add(option);
        option.add(newGame);
        option.add(gameOpt);
        gameOpt.add(board);
        board.add(defaultB);
        board.add(hardTestB);
        grpBoard.add(defaultB);
        grpBoard.add(hardTestB);
        defaultB.setSelected(true);
        gameOpt.add(playerSelect);
        playerSelect.add(randomP);
        grpPlayer.add(randomP);
        randomP.setSelected(true);
        option.add(quit);
        
        newGame.setActionCommand(NEW_GAME);
        quit.setActionCommand(QUIT);
        
        getContentPane().add(mainPane);
        mainPane.add(eastPane, BorderLayout.EAST);
        
        mainPane.add(westPane, BorderLayout.WEST);
        
        endTurnBtn.setActionCommand(END_TURN);
        moveBtn.setActionCommand(MOVE);
        shoreUpBtn.setActionCommand(SHORE_UP);
        useCapacityBtn.setActionCommand(USE_CAPACITY);
        
        getRootPane().setDefaultButton(endTurnBtn);
        
        actionCommands.add(endTurnBtn);
        actionCommands.add(moveBtn);
        actionCommands.add(shoreUpBtn);
        actionCommands.add(useCapacityBtn);
        
        westPane.add(info, BorderLayout.NORTH);
        messages.setEditable(false);
        info.add(infoPlayerC);
        info.add(currentP);
        
    }
    
    
    private void initPlayerState() {
        player3spe = new Icone();
        player4spe = new Icone();
        paneDroit = new JPanel(new GridLayout(2, 1));
        flood = new JPanel();
        // icone3 = new JPanel(new BorderLayout());
        
        // descriptionSpe3 = new JPanel(new BorderLayout());
        // icone4 = new JPanel(new BorderLayout());
        // playerSpe4 = new JPanel(new GridLayout(1, 2));
        // descriptionSpe4 = new JPanel(ne BorderLayout());
        //
        // icone3.setBackground(Color.green);
        // descriptionSpe3.setBackground(Color.PINK);
        //
        //
        // descriptionSpe4.setBackground(Color.PINK);
        //
        // eastPane.add(paneDroit, BorderLayout.CENTER);
        eastPane.setBackground(Color.RED);
        eastPane.add(player3spe, BorderLayout.NORTH);
        eastPane.add(player4spe, BorderLayout.SOUTH);
        eastPane.add(paneDroit, BorderLayout.CENTER);
        
        // eastPane.add(playerSpe4, BorderLayout.SOUTH) ;
        
        // playerSpe4.add(descriptionSpe4);
        // playerSpe4.add(icone4);
        flood.setBackground(Color.BLUE);
        flood.add(new JLabel("   "));
        paneDroit.add(actionCommands);
        paneDroit.add(flood);
        //
    }
    
    
    /**
     * @author nihil
     *
     */
    public void setCPlayer(String str, int act) {
        infoPlayerC.setText("Joueur " + str + " Reste " + act + " actions");
    }
    
    
    /**
     * @param currentP
     * the currentP to set
     */
    public void setCurrentP(AdventurerType currentP) {
        info.remove(this.currentP);
        this.currentP = new PawnComponant(currentP);
        info.add(this.currentP);
    }
    
    
    /**
     * @author nihil
     *
     */
    public void notifyPlayers(String msg) {
        messages.setText(messages.getText() + "\n" + msg);
        Runnable test = () -> {
            try {
                String txt = messages.getText();
                Thread.sleep(3000);
                messages.setText(messages.getText().replaceFirst(txt, ""));
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        };// end Runnable
        
        Thread t = new Thread(test);
        t.start();
    }
    
    
    /**
     * @author nihil
     *
     */
    private void initListeners() {
        setListObs(new ListenerAction());
        
        addWindowListener(listObs);
        newGame.addActionListener(listObs);
        quit.addActionListener(listObs);
        
        endTurnBtn.addActionListener(listObs);
        moveBtn.addActionListener(listObs);
        shoreUpBtn.addActionListener(listObs);
        useCapacityBtn.addActionListener(listObs);
    }
    
    
    /**
     * @author nihil
     *
     */
    public void setEndTurn(boolean b) {
        endTurnBtn.setBorder(b ? BorderFactory.createLineBorder(Color.GREEN, 4) : UIManager.getBorder("Button.border"));
        endTurnBtn.repaint();
        moveBtn.setEnabled(!b);
        shoreUpBtn.setEnabled(!b);
        useCapacityBtn.setEnabled(!b);
    }
    
    
    /**
     * @author nihil
     *
     */
    public void setActions(ArrayList<InGameAction> act) {
        useCapacityBtn.setEnabled(act.contains(InGameAction.USE_CAPACITY));
        moveBtn.setEnabled(act.contains(InGameAction.MOVE));
        shoreUpBtn.setEnabled(act.contains(InGameAction.SHORE_UP_TILE));
    }
    
    
    /**
     * @author nihil
     *
     */
    public void setScreen() {
        setSize(Parameters.screenSize);
        if (Parameters.fullscreen) {
            setUndecorated(true);
            setExtendedState(MAXIMIZED_BOTH);
            GraphicsEnvironment.getLocalGraphicsEnvironment().getDefaultScreenDevice().setFullScreenWindow(this);
        } else {
            setUndecorated(false);
            GraphicsEnvironment.getLocalGraphicsEnvironment().getDefaultScreenDevice().setFullScreenWindow(null);
        } // end if
        
        // FIXME to remove
        messages.setPreferredSize(
                new Dimension((int) (getSize().getWidth() * 0.10), (int) (getSize().getHeight() * 0.08)));
    }
    
    
    public JLayeredPane getTileG(Coords c) {
        JLayeredPane tilePanel = (JLayeredPane) gamePane.getGridPane()
                .getComponent(c.getRow() * Island.GRID_SIZE.getCol() + c.getCol());
        if (!(tilePanel instanceof TilePanel)) {
            Parameters.printLog("get a JPanel", LogType.ACCESS);
        } else {
            Parameters.printLog("get " + ((TilePanel) tilePanel).getSite().getName() + " at " + c, LogType.ACCESS);
        } // end if
        return tilePanel;
    }// end
     // getTileG
    
    
    /**
     * @author nihil
     * enable a specific tile
     * @param b
     * @param c
     * @param action
     */
    public void setEnabled(boolean b, Coords c, InGameAction action) {
        ((TilePanel) getTileG(c)).setEnabled(b, action);
    }
    
    
    public void movePawn(AdventurerType p, Coords current, Coords next) {
        ((TilePanel) getTileG(next)).addPawn(p);
        ((TilePanel) getTileG(current)).removePawn(p);
        repaint();
        doLayout();
    }// end movePawn
    
    
    public void shoreUp(Coords tile) {
        ((TilePanel) getTileG(tile)).setState(TileState.DRIED);
    }// end shoreUp
    
    
    /**
     * to set the board of the view (create components ...)
     * 
     * @author nihil
     * @param observer
     *
     */
    public void setBoard(ArrayList<Site> board, Observer observer) {
        gamePane = new BoardPanel(this);
        gamePane.initGrid(board, observer);
        mainPane.add(gamePane, BorderLayout.CENTER);
        revalidate();
        repaint();
        doLayout();
    }
    
    
    /**
     * @param listObs
     * the listObs to set
     */
    private void setListObs(ListenerAction listObs) {
        this.listObs = listObs;
    }
    
    
    /**
     * @return the grpBoard
     */
    public BoardType getBoard() {
        if (hardTestB.isSelected()) {
            return BoardType.HARD_TEST;
        } else {
            return BoardType.DEFAULT;
        } // end if
    }
    
    public class ListenerAction extends Observable implements ActionListener, WindowListener {
        
        /**
         * @see java.awt.event.ActionListener#actionPerformed(java.awt.event.ActionEvent)
         */
        @Override
        public void actionPerformed(ActionEvent e) {
            // Menu
            switch (e.getActionCommand()) {
            case QUIT:
                setChanged();
                notifyObservers(new MainMessage(MainAction.QUIT));
                clearChanged();
                break;
            case NEW_GAME:
                mainPane.remove(gamePane);
                setChanged();
                notifyObservers(new MainMessage(MainAction.CREATE_GAME, getBoard()));
                clearChanged();
                setChanged();
                notifyObservers(new MainMessage(MainAction.BEGIN_GAME, getBoard()));
                clearChanged();
                break;
            
            default:
                break;
            }// end switch
            
            // InTurn
            switch (e.getActionCommand()) {
            case END_TURN:
                setChanged();
                notifyObservers(new InGameMessage(InGameAction.END_TURN));
                clearChanged();
                break;
            case MOVE:
                setChanged();
                notifyObservers(new InGameMessage(InGameAction.MOVE));
                clearChanged();
                break;
            case SHORE_UP:
                setChanged();
                notifyObservers(new InGameMessage(InGameAction.SHORE_UP_TILE));
                clearChanged();
                break;
            case USE_CAPACITY:
                setChanged();
                notifyObservers(new InGameMessage(InGameAction.USE_CAPACITY));
                clearChanged();
                break;
            
            default:
                break;
            }// end switch
            
        }
        
        
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
    
    
    /**
     * @author nihil
     *
     * @param mainController
     */
    public void addObs(Observer observer) {
        listObs.addObserver(observer);
    }
}
