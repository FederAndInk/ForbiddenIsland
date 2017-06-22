package model.card;

import java.util.ArrayList;

import model.game.Island;
import model.game.Tile;
import model.game.TileState;
import model.player.Player;
import util.exception.EndGameException;
import util.exception.MoveException;
import util.exception.TileException;



public class Helicopter extends Card {
    /**
     * @author nihil
     *
     * @param type
     */
    public Helicopter() {
        super(CardType.HELICOPTER_CARD);
    }
    
    
    /**
     * @see model.card.Card#getTilesDest()
     */
    @Override
    public ArrayList<Tile> getTilesDest(Island island) {
        return island.getTileNot(TileState.SINKED);
    }
    
    
    @Override
    public void applyAction(Tile destTile, Object applied)
            throws IllegalAccessException, EndGameException, MoveException, TileException {
        if (applied instanceof ArrayList) {
            ArrayList<Player> playersToMove = (ArrayList) (applied);
            if (destTile.getState() != TileState.SINKED) {
                for (Player p : playersToMove) {
                    p.getCurrentAdventurer().setCurrentTile(destTile);
                }
            } else {
                throw new MoveException(destTile);
            }
        } else {
            throw new IllegalArgumentException("Don't have list of player");
        }
    }
}