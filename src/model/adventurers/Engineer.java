package model.adventurers;

import java.util.ArrayList;
import model.game.Tile;
import model.game.TileState;
import model.player.Player;
import util.exception.MoveException;



/**
 * the engineer can shore 2 tiles up for 1 action
 * 
 * @author nihil
 *
 */
public class Engineer extends Adventurer {
    
    private boolean continueShoreUp;
    
    
    public Engineer(Player player) {
        super(player, AdventurerType.ENGINEER);
    }
    
    
    /**
     * 
     * @return true if a ShoreUp tile is remaining
     */
    public boolean isSUTileRemaining() {
        return !getShoreUpTiles().isEmpty();
    }
    
    
    @Override
    public void shoreUp(Tile tile) {
        if (getShoreUpTiles().contains(tile)) {
            if (isContinueShoreUp()) {
                tile.setState(TileState.DRIED);
                setContinueShoreUp(false);
            } else if (getActionPoints() >= 1) {
                setContinueShoreUp(isContinueShoreUp());
                tile.setState(TileState.DRIED);
                setActionPoints(getActionPoints() - 1);
            } else {
                // FIXME : add throws
            }
        } else {
            // FIXME : add throws
        }
    }
    
    
    @Override
    public void move(Tile tile) throws MoveException {
        setContinueShoreUp(false);
        super.move(tile); // To change body of generated methods, choose Tools | Templates.
        
    }
    
    
    @Override
    public void endTurn() {
        setContinueShoreUp(false);
        super.endTurn(); // To change body of generated methods, choose Tools | Templates.
    }
    
    
    /**
     * @return the continueShoreUp
     */
    private boolean isContinueShoreUp() {
        return continueShoreUp;
    }
    
    
    /**
     * @param continueShoreUp
     * the continueShoreUp to set
     */
    private void setContinueShoreUp(boolean continueShoreUp) {
        this.continueShoreUp = continueShoreUp;
    }
}