/**
 * 
 */
package util;

import java.awt.Color;
import java.util.Observer;

import javax.swing.BorderFactory;
import javax.swing.border.Border;

import model.adventurers.AdventurerType;
import view.board.PawnComponant;



/**
 * @author nihil
 *
 */
public class FIGraphics {
    // borders
    public static final Border ACTIVE_BORDER_SELECTED = BorderFactory.createLineBorder(Color.GREEN, 5, true);
    
    public static final Border ACTIVE_BORDER_HOVER       = BorderFactory.createLineBorder(new Color(10, 194, 10), 5,
            true);
    public static final Border ACTIVE_BORDER_SHORE_HOVER = BorderFactory.createLineBorder(new Color(255, 212, 2), 5,
            true);
    public static final Border ACTIVE_BORDER_SWIM_HOVER  = BorderFactory.createLineBorder(Color.BLUE, 5, true);
    public static final Border ACTIVE_BORDER_EXIT        = BorderFactory.createLineBorder(new Color(70, 255, 104), 4,
            true);
    public static final Border ACTIVE_BORDER_SWIM_EXIT   = BorderFactory.createLineBorder(new Color(20, 79, 254), 4,
            true);
    public static final Border ACTIVE_BORDER_SHORE_EXIT  = BorderFactory.createLineBorder(new Color(249, 170, 0), 4,
            true);
    public static final Border INACTIVE_BORDER           = BorderFactory.createLineBorder(Color.GRAY, 3, true);
    
    // Pawns
    private static PawnComponant DIVER;
    private static PawnComponant EXPLORER;
    private static PawnComponant ENGINEER;
    private static PawnComponant MESSENGER;
    private static PawnComponant PILOT;
    private static PawnComponant NAVIGATOR;
    
    
    /**
     * @author nihil
     *
     */
    public static void init(Observer obs) {
        DIVER = new PawnComponant(AdventurerType.DIVER, obs);
        EXPLORER = new PawnComponant(AdventurerType.EXPLORER, obs);
        ENGINEER = new PawnComponant(AdventurerType.ENGINEER, obs);
        MESSENGER = new PawnComponant(AdventurerType.MESSENGER, obs);
        PILOT = new PawnComponant(AdventurerType.PILOT, obs);
        NAVIGATOR = new PawnComponant(AdventurerType.NAVIGATOR, obs);
    }
    
    
    /**
     * @author nihil
     *
     */
    public static PawnComponant getPawn(AdventurerType adv) {
        switch (adv) {
        case DIVER:
            return DIVER;
        case ENGINEER:
            return ENGINEER;
        
        case EXPLORER:
            return EXPLORER;
        
        case MESSENGER:
            return MESSENGER;
        
        case NAVIGATOR:
            return NAVIGATOR;
        
        case PILOT:
            return PILOT;
        
        default:
            return null;
        }// end switch
    }
}
