package model.card;

import model.game.Game;
import model.game.Site;
import model.game.Tile;
import model.game.TileState;
import util.exception.EndGameException;
import util.exception.TileException;



public class FloodCard extends Card {
    private Site site;
    
    private Tile appliedTile;
    
    
    /**
     * @author nihil
     *
     * @param type
     */
    public FloodCard(Tile tile) {
        super(CardType.FLOOD_CARD);
        setAppliedTile(tile);
    }
    
    
    public Site getSite() {
        return site;
    }
    
    
    /**
     * 
     * @param applied
     * = {@link Game}
     * @see model.card.Card#applyAction(model.game.Tile, java.lang.Object)
     */
    @Override
    public void applyAction(Tile destTile, Object applied) throws TileException, EndGameException {
        switch (appliedTile.getState()) {
        case DRIED:
            if (applied instanceof Game) {
                Game game = (Game) applied;
                appliedTile.setState(TileState.FLOODED);
                game.getFloodDeck().discard(this);
                game.getFloodDeck().discard(this);
            } else {
                throw new IllegalArgumentException("Not a game");
            } // end if
            break;
        
        case FLOODED:
            appliedTile.setState(TileState.SINKED);
            break;
        
        case SINKED:
            throw new TileException(appliedTile, TileState.SINKED);
        }
    }
    
    
    /**
     * @return the appliedTile
     */
    public Tile getAppliedTile() {
        return appliedTile;
    }
    
    
    /**
     * @param appliedTile
     * the appliedTile to set
     */
    public void setAppliedTile(Tile appliedTile) {
        this.appliedTile = appliedTile;
    }
    
}