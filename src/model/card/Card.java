package model.card;

import java.util.ArrayList;

import model.game.Island;
import model.game.Tile;
import util.exception.EndGameException;
import util.exception.MoveException;
import util.exception.PlayerOutOfIslandException;
import util.exception.TileException;



public abstract class Card {
    private CardType type;
    
    
    /**
     * @author nihil
     *
     */
    protected Card(CardType type) {
        setType(type);
    }
    
    
    public ArrayList<Tile> getTilesDest(Island island) throws IllegalAccessException {
        throw new IllegalAccessException(getType() + " Cannot be used");
    }
    
    
    public void applyAction(Tile destTile, Object applied)
            throws IllegalAccessException, EndGameException, MoveException, TileException, PlayerOutOfIslandException {
        throw new IllegalAccessException();
    }
    
    
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
    
    
    @Override
    public String toString() {
        return getType().toString();
    }
}