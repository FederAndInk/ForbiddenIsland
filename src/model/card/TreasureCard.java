package model.card;

import model.game.TreasureType;



public class TreasureCard extends Card {
    private TreasureType treasureType;
    
    
    /**
     * @author nihil
     *
     * @param type
     */
    public TreasureCard(TreasureType treasureType) {
        super(CardType.TREASURE_CARD);
        this.treasureType = treasureType;
    }
    
    
    public TreasureType getTreasureType() {
        return treasureType;
    }
    
    
    @Override
    public String toString() {
        // TODO Auto-generated method stub
        return super.toString() + " " + treasureType;
    }
    
}