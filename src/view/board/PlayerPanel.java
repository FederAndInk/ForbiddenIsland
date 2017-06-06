/**
 * 
 */
package view.board;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.util.ArrayList;

import javax.swing.JPanel;

import model.adventurers.AdventurerType;
import model.game.Coords;
import util.LogType;
import util.Parameters;



/**
 * @author nihil
 *
 */
public class PlayerPanel extends JPanel {
    private ArrayList<AdventurerType> pawns;
    private GridBagConstraints        constraints;
    private GridBagLayout             layout;
    
    
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
        layout = new GridBagLayout();
        setLayout(layout);
        
        constraints = new GridBagConstraints();
        constraints.weighty = 1;
        constraints.weightx = 1;
    }
    
    
    /**
     * @author nihil
     *
     * @return the next position to place the pawn
     */
    private Coords getNextCoords() {
        switch (pawns.size()) {
        case 0:
            return new Coords(0, 0);
        
        case 1:
            return new Coords(2, 1);
        
        case 2:
            return new Coords(1, 1);
        
        default:
            return new Coords(3, 0);
        }// end switch
    }// end getNextCoords
    
    
    /**
     * @author nihil
     * @param size
     *
     */
    protected void addPawn(AdventurerType pawn) {
        Parameters.printLog("Add " + pawn + " to " + ((TilePanel) getParent()).getPos(), LogType.INFO);
        constraints.gridx = getNextCoords().getCol();
        constraints.gridy = getNextCoords().getRow();
        
        pawns.add(pawn);
        PawnComponant pComponant = new PawnComponant(pawn);
        add(pComponant, constraints);
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
            remove(pawns.size());
        }
    }
    
}
