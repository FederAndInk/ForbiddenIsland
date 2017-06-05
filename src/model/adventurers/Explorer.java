package model.adventurers;

import java.util.ArrayList;

import model.game.Coords;
import model.game.Island;
import model.game.Tile;
import model.game.TileState;
import model.player.Player;



/**
 * the Explorer can move and shore tile up in diagonal in addition to the {@link Adventurer}
 * 
 * @author nihil
 */
public class Explorer extends Adventurer {
    
    public Explorer(Player player) {
        super(player);
    }
    
    
    @Override
    public ArrayList<Tile> getReachableTiles() {
        ArrayList<Tile> reachable = new ArrayList<>();
        Coords coords = getCurrentTile().getCoords();
        
        Island island = getPlayer().getCurrentGame().getIsland();
        Tile tileTmp;
        
        for (int i = -1; i <= 1; i++) {
            for (int j = -1; j <= 1; j++) {
                if (i != 0 || j != 0) { // we do not want the current tile
                    tileTmp = (island.getTile(coords.getX() + i, coords.getY() + j));
                    if ((tileTmp != null) && (tileTmp.getState() != TileState.SINKED)) {
                        reachable.add(tileTmp);
                    }
                } // end if
            } // end for
        }
        
        return reachable;
    }
    
}