package model.adventurers;

import java.util.ArrayList;

import model.game.Coords;
import model.game.Tile;
import model.game.TileState;
import model.player.Inventory;
import model.player.Player;
import util.exception.MoveException;



public abstract class Adventurer {
    
    private Player           player;
    private Inventory        inventory;
    private Tile             currentTile;
    private static final int MAX_ACTION_POINTS = 3;
    private int              actionPoints;
    
    
    public Adventurer(Player player) {
        setActionPoints(MAX_ACTION_POINTS);
        setPlayer(player);
        setInventory(new Inventory());
    }
    
    
    /**
     * 
     * 
     *
     * @param tile
     * @return true if the move done
     * @throws MoveException
     */
    public void move(Coords coords) throws MoveException {
        Tile tile = getPlayer().getCurrentGame().getIsland().getTile(coords);
        if (getActionPoints() >= 1 && getReachableTiles().contains(tile)) {
            setCurrentTile(tile);
            setActionPoints(getActionPoints() - 1);
            System.out.println("le deplacement a été effectué");
        } else {
            if (getActionPoints() <= 0) {
                throw new MoveException(tile);
            } else {
                throw new MoveException(getActionPoints());
            } // end if
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
     * @return the player
     */
    public Player getPlayer() {
        return player;
    }
    
    
    /**
     * @param player
     * the player to set
     */
    private void setPlayer(Player player) {
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
    private void setInventory(Inventory inventory) {
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
    protected void setCurrentTile(Tile currentTile) {
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
    protected void setActionPoints(int actionPoints) {
        this.actionPoints = actionPoints;
    }
    
}