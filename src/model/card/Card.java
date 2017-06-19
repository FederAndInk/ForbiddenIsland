package model.card;

import model.game.Tile;
import util.exception.EndGameException;
import util.exception.MoveException;
import util.exception.TileException;



public abstract class Card {
    private CardType type;
    
    
    /**
     * @author nihil
     *
     */
    protected Card(CardType type) {
        this.setType(type);
    }
    
    
    public abstract void applyAction(Tile destTile, Object applied)
            throws IllegalAccessException, EndGameException, MoveException, TileException;
    
    
    /**
     * @return the type
     */
    public CardType getType() {
        return type;
    }
    
    
    /**
     * @param type
     * the type to set
     */
    public void setType(CardType type) {
        this.type = type;
    }
    
}