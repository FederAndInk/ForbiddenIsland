package model.card;

import model.game.TreasureType;



public class TreasureCard extends Card {
    private TreasureType treasureType;
    
    
    /**
     * @author nihil
     *
     * @param type
     */
    protected TreasureCard(TreasureType treasureType) {
        super(CardType.TREASURE_CARD);
        this.treasureType = treasureType;
    }
    
    
    public TreasureType getTreasureType() {
        return treasureType;
    }
    
}