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



/**
 * @author nihil
 *
 */
public class PlayerPanel extends JPanel {
    private ArrayList<AdventurerType> pawns;
    private Coords                    lastPawnPos;
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
        lastPawnPos = null;
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
     *
     */
    protected void addPawn(AdventurerType pawn) {
        constraints.gridx = getNextCoords().getX();
        constraints.gridy = getNextCoords().getY();
        add(new PawnComponant(pawn), constraints);
        pawns.add(pawn);
    }
    
    
    /**
     * @author nihil
     * @param pawn
     *
     */
    protected void removePawn(AdventurerType pawn) {
        pawns.remove(pawn);
        remove(pawns.size());
    }
    
}
