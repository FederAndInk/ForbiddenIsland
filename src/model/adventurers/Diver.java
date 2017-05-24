package model.adventurers;

import java.util.ArrayList;
import model.game.Site;
import model.game.Tile;
import model.player.Inventory;
import model.player.Player;



public class Diver extends Adventurer {
    
    public ArrayList<Tile> getReachableTiles() {
        // TODO - implement Diver.getReachableTiles
        throw new UnsupportedOperationException();
    }
    
    
    public Diver(Player player, Inventory inventory) {
        super(player, inventory);
        setCurrentTile(Site.IRON_GATE);
    }
    
}