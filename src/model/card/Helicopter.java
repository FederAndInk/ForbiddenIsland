package model.card;

import java.util.ArrayList;
import model.game.Tile;
import model.game.TileState;
import model.player.Player;



public class Helicopter extends Card {
    /**
     * @author nihil
     *
     * @param type
     */
    protected Helicopter() {
        super(CardType.HELICOPTER_CARD);
    }
    
    
    @Override
    public void applyAction(Tile destTile, Object applied) {
        if (applied instanceof ArrayList) {
            ArrayList<Player> playersToMove = (ArrayList) (applied);
            if (destTile.getState() != TileState.SINKED) {
                for (Player p : playersToMove) {
                    p.getCurrentAdventurer().setCurrentTile(destTile);
                }
            } else {
                // TODO add exceptions
            }
        } else {
            throw new IllegalArgumentException("Don't have list of player");
        }
    }
}