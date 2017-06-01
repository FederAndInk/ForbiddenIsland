package model.adventurers;

import java.util.ArrayList;

import model.game.Coords;
import model.game.Tile;
import model.game.TileState;
import model.player.Inventory;
import model.player.Player;



/**
 * Can be
 * a {@link Diver}<br>
 * an {@link Engineer}<br>
 * an {@link Explorater}<br>
 * a {@link Messenger}<br>
 * a {@link Navigator}<br>
 * a {@link Pilot}
 * 
 * @author nihil
 *
 */
public abstract class Adventurer {
    private static final int MAX_ACTION_POINTS = 3;
    private Player           player;
    private Inventory        inventory;
    private Tile             currentTile;
    private int              actionPoints;
    
    
    public Adventurer(Player player) {
        setActionPoints(MAX_ACTION_POINTS);
        setPlayer(player);
        setInventory(new Inventory());
    }
    
    
    public void move(Tile tile) {
        if (getActionPoints() >= 1 && getReachableTiles().contains(tile)) {
            setCurrentTile(tile);
            setActionPoints(getActionPoints() - 1);
            System.out.println("le deplacement a été effectué");
        } else {
            System.out.println(getActionPoints() <= 0 ? "not enough action point" : "tile note reachable");
        }
        
    }
    
    
    public ArrayList<Tile> getReachableTiles() {
        
        ArrayList<Tile> reachable = new ArrayList<>();
        Coords coords = getCurrentTile().getCoords();
        
        Tile[][] grid = getPlayer().getCurrentGame().getIsland().getGrid();
        Tile tileTmp;
        // we will apply a sweet function to get through -1,0,1,0 and meanwhile 0,1,0,-1 (uses of modulo is awesome)
        int j = 2;
        for (int i = -1; i <= 2; i += 1) {
            int effI = i % 2;
            int effJ = j % 2;
            System.out.println(effI + "," + effJ);
            tileTmp = (grid[coords.getX() + effI][coords.getY() + effJ]);
            if ((tileTmp != null) && (tileTmp.getState() != TileState.SINKED)) {
                reachable.add(tileTmp);
            }
            j--;
        } // end for
        return reachable;
        
    }
    
    
    /**
     * @see java.lang.Object#toString()
     */
    @Override
    public String toString() {
        return getClass().getSimpleName();
    }
    
    
    /**
     * @return the player
     */
    public Player getPlayer() {
        return player;
    }
    
    
    /**
     * @param player
     * the player to set
     */
    public void setPlayer(Player player) {
        this.player = player;
    }
    
    
    /**
     * @return the inventory
     */
    public Inventory getInventory() {
        return inventory;
    }
    
    
    /**
     * @param inventory
     * the inventory to set
     */
    public void setInventory(Inventory inventory) {
        this.inventory = inventory;
    }
    
    
    /**
     * @return the currentTile
     */
    public Tile getCurrentTile() {
        return currentTile;
    }
    
    
    /**
     * @param currentTile
     * the currentTile to set
     */
    public void setCurrentTile(Tile currentTile) {
        this.currentTile = currentTile;
    }
    
    
    /**
     * @return the MAX_ACTION_POINTS
     */
    public int getMAX_ACTION_POINTS() {
        return MAX_ACTION_POINTS;
    }
    
    
    /**
     * @return the actionPoints
     */
    public int getActionPoints() {
        return actionPoints;
    }
    
    
    /**
     * @param actionPoints
     * the actionPoints to set
     */
    public void setActionPoints(int actionPoints) {
        this.actionPoints = actionPoints;
    }
    
}