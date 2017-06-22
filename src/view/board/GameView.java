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
import util.LogType;
import util.Parameters;
import util.message.InGameAction;
import util.message.InGameMessage;
import util.message.MainAction;
import util.message.MainMessage;
import view.Cards.DeckComponent;
import view.player.PlayerInfo;
import view.player.playerInventory;



public class GameView extends JLayeredPane {
    private static final String END_TURN     = "endTurn";
    private static final String MOVE         = "move";
    private static final String SHORE_UP     = "shore";
    private static final String USE_CAPACITY = "cap";
    private static final String NEW_GAME     = "newGame";
    private static final String QUIT         = "quit";
    
    private BoardPanel gamePane;
    private JPanel     eastPane;
    private JPanel     westPane;
    private JPanel     actionCommands;
    
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
    
    private ListenerAction listObs;
    
    
    public GameView() {
        super();
        pawns = new ArrayList<>();
        inventories = new HashMap<>();
        initComponents();
        initDecks();
        
        initListeners();
        
    }
    
    
    /**
     * @author nihil
     *
     */
    private void initComponents() {
        setLayout(new BorderLayout());
        eastPane = new JPanel(new BorderLayout());
        westPane = new JPanel(new BorderLayout());
        actionCommands = new JPanel(new GridLayout(4, 1));
        
        paneDroit = new JPanel();
        GridBagLayout layout = new GridBagLayout();
        paneDroit.setLayout(layout);
        layout.rowHeights = new int[2];
        double[] weight = { 0.15, 0.85 };
        layout.rowWeights = weight;
        
        endTurnBtn = new JButton("Fin de tour");
        moveBtn = new JButton("Se déplacer");
        shoreUpBtn = new JButton("Assécher un endroit");
        useCapacityBtn = new JButton("Utiliser sa capacité");
        
        floodCursor = new WaterRise();
        
        add(eastPane, BorderLayout.EAST);
        
        add(westPane, BorderLayout.WEST);
        
        endTurnBtn.setActionCommand(END_TURN);
        moveBtn.setActionCommand(MOVE);
        shoreUpBtn.setActionCommand(SHORE_UP);
        useCapacityBtn.setActionCommand(USE_CAPACITY);
        
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
        add(north, BorderLayout.NORTH);
        add(south, BorderLayout.SOUTH);
        boolean left;
        boolean top;
        JPanel pane;
        String contraint;
        for (AdventurerType adv : advs) {
            
            switch (inventories.size()) {
            case 0:
                pane = north;
                left = true;
                top = true;
                contraint = BorderLayout.WEST;
                break;
            case 1:
                pane = north;
                left = false;
                top = true;
                contraint = BorderLayout.EAST;
                break;
            case 2:
                pane = south;
                left = true;
                top = false;
                contraint = BorderLayout.WEST;
                break;
            
            default:
                pane = south;
                left = false;
                top = false;
                contraint = BorderLayout.EAST;
                break;
            }// end switch
            inventories.put(adv, new playerInventory(adv, left, top));
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
     * @param currentP
     * the currentP to set
     * @param name
     * @param actions
     */
    public void setCurrentP(AdventurerType currentP, String name, int actions) {
        
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
        gamePane = new BoardPanel();
        gamePane.initGrid(board, observer);
        add(gamePane, BorderLayout.CENTER);
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
    
    public class ListenerAction extends Observable implements ActionListener, WindowListener {
        
        /**
         * @see java.awt.event.ActionListener#actionPerformed(java.awt.event.ActionEvent)
         */
        @Override
        public void actionPerformed(ActionEvent e) {
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
