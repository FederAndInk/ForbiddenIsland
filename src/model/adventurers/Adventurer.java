package model.adventurers;

import java.util.ArrayList;

import model.game.Coords;
import model.game.Tile;
import model.game.TileState;
import model.player.Inventory;
import model.player.Player;



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
        
        for (int i = -1; i <= 1; i += 2) {
            Tile tileTmp = (grid[currentTile.getCoords().getX()][currentTile.getCoords().getY() + i]);
            if ((tileTmp != null) && (tileTmp.getState() != TileState.SINKED)) {
                reachable.add(tileTmp);
            }
            tileTmp = (grid[currentTile.getCoords().getX() + i][currentTile.getCoords().getY()]);
            if ((tileTmp != null) && (tileTmp.getState() != TileState.SINKED)) {
                reachable.add(tileTmp);
            }
        }
        
        return reachable;
    }
    
    
    /**
     * 
     * @param tile
     */
    public void isAccessible(Tile tile) {
        // TODO - implement Adventurer.isAccessible
        throw new UnsupportedOperationException();
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