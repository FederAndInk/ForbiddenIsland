/**
 * 
 */
package view.board;

import java.awt.Component;
import java.awt.Point;
import java.util.ArrayList;

import javax.swing.JPanel;

import model.adventurers.AdventurerType;
import util.LogType;
import util.Parameters;



/**
 * @author nihil
 *
 */
public class PlayerPanel extends JPanel {
    private ArrayList<AdventurerType> pawns;
    
    
    /**
     * @author nihil
     *
     */
    public PlayerPanel() {
        super();
        // to see the background
        setOpaque(false);
        pawns = new ArrayList<>();
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
     * @param size
     *
     */
    protected void addPawn(AdventurerType pawn) {
        Parameters.printLog("Add " + pawn + " n°" + pawns.size() + " to " + ((TilePanel) getParent()).getPos(),
                LogType.INFO);
        PawnComponant pComponant = new PawnComponant(pawn);
        add(pComponant);
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
            for (Component comp : getComponents()) {
                PawnComponant paw = (PawnComponant) comp;
                if (paw.getPawn().equals(pawn)) {
                    remove(paw);
                } // end if
            } // end for
        }
        doLayout();
    }
    
}
