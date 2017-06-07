package model.adventurers;

import java.util.ArrayList;

import model.game.Tile;
import model.game.TileState;
import model.player.Player;
import util.exception.MoveException;
import util.message.InGameAction;



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
        setContinueShoreUp(false);
    }
    
    
    /**
     * 
     * @return true if a ShoreUp tile is remaining
     */
    public boolean isSUTileRemaining() {
        return !getShoreUpTiles().isEmpty();
    }
    
    
    public boolean actionsRemains() {
        return continueShoreUp || getActionPoints() > 0;
    }// end actionsRemains
    
    
    /**
     * @author nihil
     *
     */
    @Override
    protected void finishAction() {
        if (continueShoreUp) {
            setActionPoints(getActionPoints() - 1);
            setContinueShoreUp(false);
        } else if (getActionPoints() > 0) {
            setActionPoints(getActionPoints() - 1);
        } else {
            // TODO : throw new
        } // end if
    }// end finishAction
    
    
    @Override
    public void shoreUp(Tile tile) {
        if (getShoreUpTiles().contains(tile)) {
            if (isContinueShoreUp()) {
                tile.setState(TileState.DRIED);
                finishAction();
            } else if (getActionPoints() > 0) {
                setContinueShoreUp(true);
                tile.setState(TileState.DRIED);
            } else {
                // FIXME : add throws
            }
        } else {
            // FIXME : add throws
        }
    }
    
    
    /**
     * @see model.adventurers.Adventurer#getPossibleActions()
     */
    @Override
    public ArrayList<InGameAction> getPossibleActions() {
        ArrayList<InGameAction> arrayList = new ArrayList<>();
        if (getActionPoints() > 1 || (getActionPoints() == 1 && !continueShoreUp)) {
            return super.getPossibleActions();
        }
        if (continueShoreUp && getActionPoints() == 1) {
            arrayList.add(InGameAction.SHORE_UP_TILE);
        }
        return arrayList;
    }
    
    
    @Override
    public void move(Tile tile) throws MoveException {
        if (continueShoreUp) {
            finishAction();
        } // end if
        super.move(tile);
    }
    
    
    @Override
    public void endTurn() {
        setContinueShoreUp(false);
        super.endTurn();
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