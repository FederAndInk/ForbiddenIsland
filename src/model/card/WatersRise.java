package model.card;

import model.game.Game;
import model.game.Tile;
import util.exception.EndGameException;



public class WatersRise extends Card {
    /**
     * @author nihil
     *
     * @param type
     */
    protected WatersRise() {
        super(CardType.WATERSRISE_CARD);
    }
    
    
    /**
     * 
     * @param destTile
     * @param applied
     * = a {@link Game}
     */
    @Override
    public void applyAction(Tile destTile, Object applied) throws EndGameException {
        if (applied instanceof Game) {
            Game game = (Game) applied;
            game.increaseSeaLevel();
            game.getTreasureDeck().discard(this);
            game.getFloodDeck().shuffleDeck();
            game.getTreasureDeck().shuffleDeck();
        } else {
            throw new IllegalArgumentException("is not a game");
        }
    }
    
}