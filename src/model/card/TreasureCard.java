package model.card;

import model.game.Tile;
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