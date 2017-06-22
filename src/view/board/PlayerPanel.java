/**
 * 
 */
package view.board;

import java.awt.FlowLayout;
import java.awt.Point;
import java.util.ArrayList;
import java.util.Observer;

import javax.swing.JPanel;

import model.adventurers.AdventurerType;
import util.FIGraphics;
import util.LogType;
import util.Parameters;



/**
 * @author nihil
 *
 */
public class PlayerPanel extends JPanel {
    private ArrayList<AdventurerType> pawns;
    private ArrayList<Observer>       obs;
    
    
    /**
     * @author nihil
     *
     */
    public PlayerPanel() {
        super();
        setLayout(new FlowLayout(FlowLayout.CENTER, 20, -1));
        // to see the background
        setOpaque(false);
        pawns = new ArrayList<>();
        obs = new ArrayList<>();
        init();
    }
    
    
    /**
     * @author nihil
     *
     */
    private void init() {
        
    }
    
    
    /**
     * @author nihil
     *
     * @return the next position to place the pawn
     */
    private Point getNextCoords() {
        switch (pawns.size()) {
        case 0:
            return new Point((int) (getSize().getWidth() * 0.05), (int) (getSize().getHeight() * 0.05));
        
        case 1:
            return new Point((int) (getSize().getWidth() * 0.15), (int) (getSize().getHeight() * 0.15));
        
        case 2:
            return new Point((int) (getSize().getWidth() * 0.25), (int) (getSize().getHeight() * 0.25));
        
        default:
            return new Point((int) (getSize().getWidth() * 0.45), (int) (getSize().getHeight() * 0.05));
        }// end switch
    }// end getNextCoords
    
    
    /**
     * @author nihil
     * @param pawn
     *
     */
    protected void addPawn(AdventurerType pawn) {
        Parameters.printLog("Add " + pawn + " n°" + pawns.size() + " to " + ((TilePanel) getParent()).getPos(),
                LogType.INFO);
        PawnComponent pComponent = FIGraphics.getPawn(pawn);
        add(pComponent);
        pawns.add(pawn);
        doLayout();
    }
    
    
    /**
     * @author nihil
     * @param pawn
     * to remove if {@link AdventurerType#RANDOM} remove all
     *
     */
    protected void removePawn(AdventurerType pawn) {
        if (pawn.equals(AdventurerType.RANDOM)) {
            removeAll();
            pawns.clear();
        } else {
            Parameters.printLog("Remove " + pawn + " from " + ((TilePanel) getParent()).getPos(), LogType.INFO);
            pawns.remove(pawn);
            remove(FIGraphics.getPawn(pawn));
        }
        doLayout();
    }
    
    
    /**
     * @author nihil
     *
     * @param selected
     * @param advType
     */
    protected void setEnable(boolean enable, AdventurerType advType) {
        FIGraphics.getPawn(advType).setEnabled(enable);
    }
    
    
    /**
     * @author nihil
     *
     * @param selected
     * @param advType
     */
    public void setSelected(boolean selected, AdventurerType advType) {
        FIGraphics.getPawn(advType).setSelected(selected);
    }
    
    
    /**
     * @author nihil
     *
     * @param observer
     */
    public void addObs(Observer observer) {
        obs.add(observer);
    }
}
