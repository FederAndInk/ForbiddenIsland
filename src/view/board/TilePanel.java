/**
 * 
 */
package view.board;

import java.awt.BorderLayout;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Observable;
import java.util.Observer;

import javax.imageio.ImageIO;
import javax.swing.JLayeredPane;
import javax.swing.JMenuItem;
import javax.swing.JPopupMenu;

import model.adventurers.AdventurerType;
import model.card.CardType;
import model.game.Coords;
import model.game.Site;
import model.game.TileState;
import util.FIGraphics;
import util.LogType;
import util.Parameters;
import util.message.InGameAction;
import util.message.InGameMessage;



/**
 * @author nihil
 *
 */
public class TilePanel extends JLayeredPane {
    private TileState    state;
    private InGameAction action;
    private final Site   site;
    private final Coords pos;
    
    // players
    private PlayerPanel playerPanel;
    private TextTile    text;
    
    // observers
    private MlTile listenerObs;
    private AlTile aListenerObs;
    
    // Debug (context menu)
    private JPopupMenu          debug;
    private JMenuItem           changeStateTile;
    private static final String DEBUG_FLOOD = "changeState";
    // Debug card actions
    private JMenuItem           sandBag;
    private static final String DEBUG_SAND_BAG    = "sandBag";
    private JMenuItem           heli;
    private JMenuItem           heli2;
    private static final String DEBUG_HELICOPTER  = "helicopter";
    private static final String DEBUG_HELICOPTER2 = "helicopter2";
    
    
    /**
     * @author nihil
     *
     */
    public TilePanel(Site site, Coords c) {
        super();
        setLayout(new BorderLayout());
        this.site = site;
        this.pos = c;
        setOpaque(false);
        
        init();
        initListeners();
    }
    
    
    /**
     * @author nihil
     *
     */
    private void init() {
        text = new TextTile(site.getNameStyle());
        playerPanel = new PlayerPanel();
        
        setEnabled(false);
        setState(TileState.DRIED);
        add(text, BorderLayout.SOUTH);
        add(playerPanel, BorderLayout.CENTER);
        
        if (Parameters.debug) {
            debug = new JPopupMenu();
            changeStateTile = new JMenuItem("Change Tile State");
            sandBag = new JMenuItem("Use SandBag");
            heli = new JMenuItem("select helicopter Pawn");
            heli2 = new JMenuItem("select helicopter location");
            
            changeStateTile.setActionCommand(DEBUG_FLOOD);
            sandBag.setActionCommand(DEBUG_SAND_BAG);
            heli.setActionCommand(DEBUG_HELICOPTER);
            heli2.setActionCommand(DEBUG_HELICOPTER2);
            
            setComponentPopupMenu(debug);
            debug.add(changeStateTile);
            debug.add(sandBag);
            debug.add(heli);
            debug.add(heli2);
        } // end if
    }
    
    
    /**
     * @author nihil
     *
     */
    public void addPawn(AdventurerType pawn) {
        playerPanel.addPawn(pawn);
    }
    
    
    /**
     * remove a pawn from the tile
     * 
     * if random, remove all
     * 
     * @author nihil
     *
     */
    public void removePawn(AdventurerType pawn) {
        playerPanel.removePawn(pawn);
    }
    
    
    /**
     * @see javax.swing.JComponent#paintComponent(java.awt.Graphics)
     */
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Parameters.printLog("paint Tile " + site, LogType.GRAPHICS);
        paintBackground(g);
    }
    
    
    /**
     * @author nihil
     * @param g
     *
     */
    private void paintBackground(Graphics g) {
        try {
            BufferedImage bi = ImageIO.read(new File(site.getFile(state)));
            if (state.equals(TileState.SINKED)) {
                remove(text);
                g.drawImage(bi, 0, 0, (int) getSize().getWidth(), (int) (getSize().getHeight()), this);
            } else {
                if (getIndexOf(text) == -1) {
                    add(text, BorderLayout.SOUTH);
                }
                
                if (site.isDoubleLigned()) {
                    Parameters.printLog("Draw double ligned image", LogType.GRAPHICS);
                    g.drawImage(bi, 0, 0, (int) getSize().getWidth(), (int) (getSize().getHeight() * 0.85), this);
                } else {
                    g.drawImage(bi, 0, 0, (int) getSize().getWidth(), (int) getSize().getHeight(), this);
                }
            } // end if
        } catch (IOException e1) {
            e1.printStackTrace();
        }
    }
    
    
    /**
     * @author nihil
     *
     */
    private void initListeners() {
        listenerObs = new MlTile();
        
        addMouseListener(listenerObs);
        
        if (Parameters.debug) {
            aListenerObs = new AlTile();
            changeStateTile.addActionListener(aListenerObs);
            heli.addActionListener(aListenerObs);
            heli2.addActionListener(aListenerObs);
            sandBag.addActionListener(aListenerObs);
        } // end if
    }
    
    
    /**
     * enable or not the tile (if the player can go there) </br>
     * and set the correct border color (gray or green)
     * 
     * @param b
     * 
     * @param action
     * the action that causes the button's activation
     * 
     * @see javax.swing.AbstractButton#setEnabled(boolean)
     */
    public void setEnabled(boolean b, InGameAction action) {
        setAction(action);
        if (b) {
            switch (action) {
            case SHORE_UP_TILE:
                setBorder(FIGraphics.ACTIVE_BORDER_SHORE_EXIT);
                
                break;
            case SWIM:
                setBorder(FIGraphics.ACTIVE_BORDER_SWIM_EXIT);
                
                break;
            default:
                setBorder(FIGraphics.ACTIVE_BORDER_EXIT);
                break;
            }// end
        } else {
            setBorder(FIGraphics.INACTIVE_BORDER);
        } // end if
        super.setEnabled(b);
    }
    
    
    /**
     * enable or not the tile (if the player can go there) </br>
     * and set the correct border color (gray or green)
     * 
     * @param b
     * 
     * 
     * @see javax.swing.AbstractButton#setEnabled(boolean)
     */
    @Override
    public void setEnabled(boolean b) {
        super.setEnabled(b);
        if (b) {
            setAction(InGameAction.MOVE);
            setBorder(FIGraphics.ACTIVE_BORDER_EXIT);
        } else {
            setBorder(FIGraphics.INACTIVE_BORDER);
        } // end if
    }
    
    
    /**
     * @return the site
     */
    public Site getSite() {
        return site;
    }
    
    
    /**
     * @param state
     * the state to set
     */
    public void setState(TileState state) {
        this.state = state;
        text.setState(state);
        repaint();
    }
    
    // the inner class for an event listener
    protected class AlTile extends Observable implements ActionListener {
        
        /**
         * @see java.awt.event.ActionListener#actionPerformed(java.awt.event.ActionEvent)
         */
        @Override
        public void actionPerformed(ActionEvent e) {
            Parameters.debugAction = true;
            switch (e.getActionCommand()) {
            case DEBUG_FLOOD:
                setChanged();
                notifyObservers(new InGameMessage(InGameAction.CHANGE_STATE_OF_TILE, getPos()));
                clearChanged();
                break;
            case DEBUG_SAND_BAG:
                setChanged();
                notifyObservers(new InGameMessage(InGameAction.USE_CARD, CardType.SANDBAG_CARD));
                clearChanged();
                break;
            case DEBUG_HELICOPTER:
                setChanged();
                notifyObservers(new InGameMessage(InGameAction.SELECT_PAWN));
                clearChanged();
                break;
            case DEBUG_HELICOPTER2:
                setChanged();
                notifyObservers(new InGameMessage(InGameAction.USE_CARD, CardType.HELICOPTER_CARD));
                clearChanged();
                break;
            default:
                break;
            }// end switch
        }
        
    }
    
    // the inner class for an event listener
    protected class MlTile extends Observable implements MouseListener {
        
        /**
         * @author nihil
         *
         */
        public MlTile() {
            super();
        }
        
        
        @Override
        public void mouseReleased(MouseEvent e) {
        }
        
        
        @Override
        public void mousePressed(MouseEvent e) {
        }
        
        
        @Override
        public void mouseExited(MouseEvent e) {
            if (isEnabled()) {
                switch (getAction()) {
                case SHORE_UP_TILE:
                    setBorder(FIGraphics.ACTIVE_BORDER_SHORE_EXIT);
                    
                    break;
                case SWIM:
                    setBorder(FIGraphics.ACTIVE_BORDER_SWIM_EXIT);
                    
                    break;
                default:
                    setBorder(FIGraphics.ACTIVE_BORDER_EXIT);
                    break;
                }// end switch
            } // end if
        }
        
        
        @Override
        public void mouseEntered(MouseEvent e) {
            if (isEnabled()) {
                switch (getAction()) {
                case SHORE_UP_TILE:
                    setBorder(FIGraphics.ACTIVE_BORDER_SHORE_HOVER);
                    break;
                case SWIM:
                    setBorder(FIGraphics.ACTIVE_BORDER_SWIM_HOVER);
                    break;
                default:
                    setBorder(FIGraphics.ACTIVE_BORDER_HOVER);
                    break;
                }// end switch
            } // end if
        }
        
        
        @Override
        public void mouseClicked(MouseEvent e) {
            if (isEnabled()) {
                setChanged();
                notifyObservers(new InGameMessage(InGameAction.SELECT_TILE, getPos()));
                clearChanged();
            } // end if
        }
    }
    
    
    /**
     * @return the pos
     */
    public Coords getPos() {
        return pos;
    }
    
    
    /**
     * @return the action
     */
    public InGameAction getAction() {
        return action;
    }
    
    
    /**
     * @param action
     * the action to set
     */
    public void setAction(InGameAction action) {
        this.action = action;
    }
    
    
    /**
     * @return the playerPanel
     */
    public PlayerPanel getPlayerPanel() {
        return playerPanel;
    }
    
    
    /**
     * @author nihil
     *
     * @param observer
     */
    public void addObs(Observer observer) {
        if (Parameters.debug) {
            aListenerObs.addObserver(observer);
        } // end if
        listenerObs.addObserver(observer);
        playerPanel.addObs(observer);
    }
}
