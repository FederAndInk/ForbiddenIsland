package model.card;

import model.game.Game;
import model.game.Tile;
import model.game.TileState;
import util.exception.EndGameException;
import util.exception.TileException;



public class FloodCard extends Card {
    
    private Tile appliedTile;
    
    
    /**
     * @author nihil
     *
     * @param type
     */
    protected FloodCard(Tile tile) {
        super(CardType.FLOOD_CARD);
        setAppliedTile(tile);
    }
    
    
    @Override
    public void applyAction(Tile destTile, Object applied) throws TileException, EndGameException {
        if (applied instanceof Game) {
            Game game = (Game) applied;
            switch (appliedTile.getState()) {
            case DRIED:
                appliedTile.setState(TileState.FLOODED);
                game.getFloodDeck().discard(this);
                break;
            
            case FLOODED:
                appliedTile.setState(TileState.SINKED);
                break;
            
            case SINKED:
                throw new TileException(appliedTile, TileState.SINKED);
            }
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