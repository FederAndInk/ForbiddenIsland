/**
 * 
 */
package util;

import java.awt.Color;
import java.util.Observer;

import javax.swing.BorderFactory;
import javax.swing.border.Border;

import model.adventurers.AdventurerType;
import view.board.PawnComponent;



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
    private static PawnComponent DIVER;
    private static PawnComponent EXPLORER;
    private static PawnComponent ENGINEER;
    private static PawnComponent MESSENGER;
    private static PawnComponent PILOT;
    private static PawnComponent NAVIGATOR;
    
    
    /**
     * @author nihil
     *
     */
    public static void init(Observer obs) {
        DIVER = new PawnComponent(AdventurerType.DIVER, obs);
        EXPLORER = new PawnComponent(AdventurerType.EXPLORER, obs);
        ENGINEER = new PawnComponent(AdventurerType.ENGINEER, obs);
        MESSENGER = new PawnComponent(AdventurerType.MESSENGER, obs);
        PILOT = new PawnComponent(AdventurerType.PILOT, obs);
        NAVIGATOR = new PawnComponent(AdventurerType.NAVIGATOR, obs);
    }
    
    
    /**
     * @author nihil
     *
     */
    public static PawnComponent getPawn(AdventurerType adv) {
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
