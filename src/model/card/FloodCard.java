package model.card;

import model.game.Game;
import model.game.Site;
import model.game.Tile;
import model.game.TileState;
import util.exception.EndGameException;
import util.exception.PlayerOutOfIslandException;
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
     * @throws PlayerOutOfIslandException
     * @see model.card.Card#applyAction(model.game.Tile, java.lang.Object)
     */
    @Override
    public void applyAction(Tile destTile, Object applied)
            throws TileException, EndGameException, PlayerOutOfIslandException {
        if (applied instanceof Game) {
            Game game = (Game) applied;
            switch (appliedTile.getState()) {
            case DRIED:
                appliedTile.setState(TileState.FLOODED);
                game.getFloodDeck().discard(this);
                game.getFloodDeck().discard(this);
                break;
            
            case FLOODED:
                appliedTile.setState(TileState.SINKED);
                game.verifyDrawn(appliedTile);
                break;
            
            case SINKED:
                throw new TileException(appliedTile, TileState.SINKED);
            }
        } else {
            throw new IllegalArgumentException("Not a game");
        } // end if
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