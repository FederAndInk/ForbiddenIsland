package model.card;

import model.game.Tile;



public class TreasureCard extends Card {
    
    /**
     * @author nihil
     *
     * @param type
     */
    protected TreasureCard() {
        super(CardType.TREASURE_CARD);
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
    
}