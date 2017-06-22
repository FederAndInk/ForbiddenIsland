package view.board;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.util.*;

import javax.swing.*;

import model.adventurers.AdventurerType;
import model.card.CardType;
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
import view.Cards.DeckComponent;
import view.player.PlayerInfo;
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
    
    // player info
    private JPanel                                   paneDroit;
    private ArrayList<PlayerInfo>                    pawns;
    private HashMap<AdventurerType, playerInventory> inventories;
    
    // Decks
    private DeckComponent treasureDeck;
    private DeckComponent floodDeck;
    private JPanel        decksPane;
    
    private WaterRise floodCursor;
    
    // Inventory
    private JPanel north;
    private JPanel south;
    
    private JButton endTurnBtn;
    private JButton moveBtn;
    private JButton shoreUpBtn;
    private JButton useCapacityBtn;
    private JButton discardCard;
    private JButton invoque;
    private JButton giveCard;
    private JButton useCard;
    
    private ListenerAction listObs;
    
    
    public GameView() {
        super();
        pawns = new ArrayList<>();
        inventories = new HashMap<>();
        initComponents();
        initDecks();
        setScreen();
        Parameters.appSize = getSize();
        
        initListeners();
        
    }
    
    
    /**
     * @author nihil
     *
     */
    private void initComponents() {
        mainPane = new JPanel(new BorderLayout());
        
        eastPane = new JPanel(new BorderLayout());
        westPane = new JPanel(new BorderLayout());
        actionCommands = new JPanel(new GridLayout(8, 1));
        
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
        
        paneDroit = new JPanel();
        GridBagLayout layout = new GridBagLayout();
        paneDroit.setLayout(layout);
        layout.rowHeights = new int[2];
        double[] weight = { 0.1, 0.8 };
        layout.rowWeights = weight;
        
        endTurnBtn = new JButton("Fin de tour");
        moveBtn = new JButton("Se déplacer");
        shoreUpBtn = new JButton("Assécher un endroit");
        useCapacityBtn = new JButton("Utiliser sa capacité");
        discardCard = new JButton("Défausser une carte");
        invoque = new JButton("Invoquer un trésor");
        giveCard = new JButton("Donner une carte");
        useCard = new JButton("Utiliser une carte");
        
        floodCursor = new WaterRise(2);
        
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
        
        eastPane.add(paneDroit, BorderLayout.CENTER);
        GridBagConstraints constraints = new GridBagConstraints();
        paneDroit.add(actionCommands, constraints);
        constraints.gridy = 1;
        constraints.fill = GridBagConstraints.BOTH;
        paneDroit.add(floodCursor, constraints);
        actionCommands.add(endTurnBtn);
        actionCommands.add(moveBtn);
        actionCommands.add(shoreUpBtn);
        actionCommands.add(useCapacityBtn);
        actionCommands.add(discardCard);
        actionCommands.add(giveCard);
        actionCommands.add(useCard);
        actionCommands.add(invoque);
        for (Component c : actionCommands.getComponents()) {
            if (c != null) {
                c.setFont(new Font(c.getFont().getFontName(), c.getFont().getStyle(),
                        (int) (c.getFont().getSize() * 0.7)));
            }
        }
    }
    
    
    public void initPlayerState(ArrayList<AdventurerType> advs) {
        boolean left;
        JPanel pane;
        String contraint;
        for (AdventurerType adventurerType : advs) {
            
            switch (pawns.size()) {
            case 0:
                pane = westPane;
                left = true;
                contraint = BorderLayout.NORTH;
                break;
            case 1:
                pane = eastPane;
                left = false;
                contraint = BorderLayout.NORTH;
                break;
            case 2:
                pane = westPane;
                left = true;
                contraint = BorderLayout.SOUTH;
                break;
            
            default:
                pane = eastPane;
                left = false;
                contraint = BorderLayout.SOUTH;
                break;
            }// end switch
            pawns.add(new PlayerInfo(adventurerType, left));
            pane.add(pawns.get(pawns.size() - 1), contraint);
            
        } // end for
        
        initInventory(advs);
    }
    
    
    // Inventory
    public void initInventory(ArrayList<AdventurerType> advs) {
        north = new JPanel(new BorderLayout());
        south = new JPanel(new BorderLayout());
        mainPane.add(north, BorderLayout.NORTH);
        mainPane.add(south, BorderLayout.SOUTH);
        boolean left;
        JPanel pane;
        String contraint;
        for (AdventurerType adv : advs) {
            
            switch (inventories.size()) {
            case 0:
                pane = north;
                left = true;
                contraint = BorderLayout.WEST;
                break;
            case 1:
                pane = north;
                left = false;
                contraint = BorderLayout.EAST;
                break;
            case 2:
                pane = south;
                left = true;
                contraint = BorderLayout.WEST;
                break;
            
            default:
                pane = south;
                left = false;
                contraint = BorderLayout.EAST;
                break;
            }// end switch
            inventories.put(adv, new playerInventory(adv, left));
            pane.add(inventories.get(adv), contraint);
        }
    }// end initInventory
    
    
    /***
     * @author nihil
     */
    private void initDecks() {
        treasureDeck = new DeckComponent(CardType.TREASURE_CARD);
        floodDeck = new DeckComponent(CardType.FLOOD_CARD);
        decksPane = new JPanel(new GridLayout(2, 1));
        
        westPane.add(decksPane, BorderLayout.CENTER);
        decksPane.add(treasureDeck);
        decksPane.add(floodDeck);
    }
    
    
    /**
     * @author nihil
     *
     * @param adv
     * @return the icone of the adventurer or null if not found
     */
    public PlayerInfo getIcon(AdventurerType adv) {
        Iterator<PlayerInfo> it = pawns.iterator();
        PlayerInfo p = null;
        while (it.hasNext() && (p = it.next()).getPawn() != adv) {
        } // end while
        return p == null ? null : p.getPawn() == adv ? p : null;
    }// end initIcon
    
    
    /**
     * @author nihil
     *
     */
    public void setCPlayer(String str, int act) {
    }
    
    
    /**
     * @param currentP
     * the currentP to set
     */
    public void setCurrentP(AdventurerType currentP) {
        for (PlayerInfo pInfo : pawns) {
            if (pInfo.getPawn().equals(currentP)) {
                pInfo.setEnabled(true);
            } else {
                pInfo.setEnabled(false);
            } // end if
        } // end for
    }
    
    
    /**
     * @author nihil
     *
     */
    public void notifyPlayers(String msg) {
        // FIXME to notify
    }
    
    
    /**
     * @author nihil
     * pawn = new ;
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
     * .getSize() * 0.8)));
     * if (left) {
     * 
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
        endTurnBtn.setEnabled(act.contains(InGameAction.END_TURN));
        treasureDeck.setEnabled(act.contains(InGameAction.DRAW_TREASURE));
        floodDeck.setEnabled(act.contains(InGameAction.DRAW_FLOOD));
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
     * @author nihil
     *
     * @param toggleSelectionPlayer
     * @param adventurer_TYPE
     */
    public void setSelectPawn(boolean selected, AdventurerType advType, Coords location) {
        ((TilePanel) getTileG(location)).getPlayerPanel().setSelected(selected, advType);
    }
    
    
    /**
     * @author nihil
     *
     */
    public void setActivePawn(boolean selected, AdventurerType advType, Coords location) {
        ((TilePanel) getTileG(location)).getPlayerPanel().setEnable(selected, advType);
    }
    
    
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
     * @return
     *
     */
    public playerInventory getPInventory(AdventurerType adv) {
        return inventories.get(adv);
    }
    
    
    /**
     * @return the floodCursor
     */
    public WaterRise getFloodCursor() {
        return floodCursor;
    }
    
    
    /**
     * @author nihil
     *
     * @param mainController
     */
    public void addObs(Observer observer) {
        listObs.addObserver(observer);
        treasureDeck.addObs(observer);
        floodDeck.addObs(observer);
    }
}
