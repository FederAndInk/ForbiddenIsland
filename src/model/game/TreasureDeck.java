package model.game;

import model.card.Helicopter;
import model.card.SandBag;
import model.card.TreasureCard;
import model.card.WatersRise;



public class TreasureDeck extends Deck {
    private static final int NB_TREASURE_CARD = 5;
    private static final int NB_WATERS_RISE   = 3;
    private static final int NB_HELICOP_CARD  = 3;
    private static final int NB_SANDBAGS_CARD = 2;
    
    
    public TreasureDeck(Island island) {
        super(island);
    }
    
    
    @Override
    public void initDeck(Island island) {
        for (int i = 0; i < NB_HELICOP_CARD; i++) {
            addCardToDeck(new Helicopter());
        }
        for (int i = 0; i < NB_SANDBAGS_CARD; i++) {
            addCardToDeck(new SandBag());
        }
        for (TreasureType treasure : TreasureType.values()) {
            for (int i = 0; i < NB_TREASURE_CARD; i++) {
                addCardToDeck(new TreasureCard(treasure));
            }
        }
        for (int i = 0; i < NB_WATERS_RISE; i++) {
            addCardToDeck(new WatersRise());
        }
    }
    
}
