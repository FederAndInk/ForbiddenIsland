package model.card;

import model.game.Tile;
import model.game.TreasureType;



public class TreasureCard extends Card {
    private CardType treasureType;
    
    
    /**
     * @author nihil
     *
     * @param type
     */
    public TreasureCard(CardType treasureType) {
        super(CardType.TREASURE_CARD);
        this.treasureType = treasureType;
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
     */
    public CardType getTreasureCard() {
        return treasureType;
    }
    
    
    /**
     * @author nihil
     *
     * @return
     */
    public TreasureType getTreasureType() {
        return treasureType.getTreasureType();
    }
}