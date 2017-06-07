package view.board;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Observable;
import java.util.Observer;

import javax.swing.*;

import model.adventurers.AdventurerType;
import model.game.Coords;
import model.game.Island;
import model.game.Site;
import model.game.TileState;
import util.LogType;
import util.Parameters;
import util.message.InGameAction;
import util.message.InGameMessage;



public class GameView extends JFrame {
    private static final String END_TURN     = "endTurn";
    private static final String MOVE         = "move";
    private static final String SHORE_UP     = "shore";
    private static final String USE_CAPACITY = "cap";
    
    private JPanel     mainPane;
    private BoardPanel gamePane;
    private JPanel     eastPane;
    private JPanel     westPane;
    private JPanel     actionCommands;
    
    private JTextPane     messages;
    private JLabel        infoPlayerC;
    private PawnComponant currentP;
    private JPanel        info;
    
    private JButton endTurnBtn;
    private JButton moveBtn;
    private JButton shoreUpBtn;
    private JButton useCapacityBtn;
    
    private ListenerAction listObs;
    
    
    public GameView() {
        super();
        initComponents();
        initListeners();
        setScreen();
        
    }
    
    
    /**
     * @author nihil
     *
     */
    private void initComponents() {
        mainPane = new JPanel(new BorderLayout());
        gamePane = new BoardPanel(this);
        eastPane = new JPanel(new BorderLayout());
        westPane = new JPanel(new BorderLayout());
        actionCommands = new JPanel(new GridLayout(4, 1));
        
        endTurnBtn = new JButton("Fin de tour");
        moveBtn = new JButton("Se déplacer");
        shoreUpBtn = new JButton("Assécher un endroit");
        useCapacityBtn = new JButton("Utiliser sa capacité");
        
        messages = new JTextPane();
        infoPlayerC = new JLabel("Joueur ");
        currentP = new PawnComponant(AdventurerType.DIVER);
        info = new JPanel(new GridLayout(2, 1));
        
        getContentPane().add(mainPane);
        mainPane.add(gamePane, BorderLayout.CENTER);
        mainPane.add(eastPane, BorderLayout.EAST);
        mainPane.add(messages, BorderLayout.NORTH);
        mainPane.add(westPane, BorderLayout.WEST);
        eastPane.add(actionCommands, BorderLayout.NORTH);
        
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
        
        endTurnBtn.addActionListener(getListObs());
        moveBtn.addActionListener(getListObs());
        shoreUpBtn.addActionListener(getListObs());
        useCapacityBtn.addActionListener(getListObs());
    }
    
    
    /**
     * @author nihil
     *
     */
    public void setEndTurn(boolean b) {
        // FIXME : do something for endTurn
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
        setSize(Parameters.appSize);
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
     */
    public void setEnabled(boolean b, Coords c) {
        getTileG(c).setEnabled(b);
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
        gamePane.initGrid(board, observer);
    }
    
    
    /**
     * @return the listObs
     */
    public ListenerAction getListObs() {
        return listObs;
    }
    
    
    /**
     * @param listObs
     * the listObs to set
     */
    private void setListObs(ListenerAction listObs) {
        this.listObs = listObs;
    }
    
    public static class ListenerAction extends Observable implements ActionListener {
        
        /**
         * @see java.awt.event.ActionListener#actionPerformed(java.awt.event.ActionEvent)
         */
        @Override
        public void actionPerformed(ActionEvent e) {
            
            switch (e.getActionCommand()) {
            case END_TURN:
                setChanged();
                notifyObservers(new InGameMessage(InGameAction.END_TURN, null));
                clearChanged();
                break;
            case MOVE:
                setChanged();
                notifyObservers(new InGameMessage(InGameAction.MOVE, null));
                clearChanged();
                break;
            case SHORE_UP:
                setChanged();
                notifyObservers(new InGameMessage(InGameAction.SHORE_UP_TILE, null));
                clearChanged();
                break;
            case USE_CAPACITY:
                setChanged();
                notifyObservers(new InGameMessage(InGameAction.USE_CAPACITY, null));
                clearChanged();
                break;
            
            default:
                break;
            }// end switch
            
        }
        
    }
}
