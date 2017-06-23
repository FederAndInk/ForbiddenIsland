package model.card;

import model.game.Tile;
import model.game.TreasureType;



public class TreasureCard extends Card {
    
    /**
     * @author nihil
     *
     * @param type
     */
    public TreasureCard(CardType treasureType) {
        super(treasureType);
    }
    
    
    /**
     *
     * @param destTile
     * @param applied
     * @throws IllegalAccessException
     */
    @Override
    public void applyAction(Tile destTile, Object applied) throws IllegalAccessException {
        throw new IllegalAccessException("Card not usable");
    }
    
    
    /**
     * @author nihil
     *
     * @return
     */
    public TreasureType getTreasureType() {
        return getType().getTreasureType();
    }
}