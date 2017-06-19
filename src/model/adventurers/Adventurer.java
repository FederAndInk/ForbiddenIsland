package model.adventurers;

import java.util.ArrayList;

import model.card.Card;
import model.card.TreasureCard;
import model.game.Coords;
import model.game.Island;
import model.game.Tile;
import model.game.TileState;
import model.player.Inventory;
import model.player.Player;
import util.LogType;
import util.Parameters;
import util.exception.InadequateUseOfCapacity;
import util.exception.MoveException;
import util.message.InGameAction;



/**
 * Can be
 * a {@link Diver}<br>
 * an {@link Engineer}<br>
 * an {@link Explorer}<br>
 * a {@link Messenger}<br>
 * a {@link Navigator}<br>
 * a {@link Pilot}
 * 
 * @author nihil
 *
 */
public abstract class Adventurer {
    private static final int     MAX_ACTION_POINTS = 3;
    private final AdventurerType ADVENTURER_TYPE;
    private Player               player;
    private Inventory            inventory;
    private Tile                 currentTile;
    private int                  actionPoints;
    
    
    protected Adventurer(Player player, AdventurerType adventurerType) {
        this.ADVENTURER_TYPE = adventurerType;
        
        setActionPoints(MAX_ACTION_POINTS);
        setPlayer(player);
        player.setCurrentAdventurer(this);
        setInventory(new Inventory());
    }
    
    
    protected ArrayList<Tile> getShoreUpTiles(ArrayList<Tile> reachableTiles) {
        ArrayList<Tile> sUTiles = reachableTiles;
        sUTiles.add(getCurrentTile());
        
        for (int i = 0; i < sUTiles.size(); i++) {
            if (!sUTiles.get(i).getState().equals(TileState.FLOODED)) {
                sUTiles.remove(sUTiles.get(i));
                i--;
            }
        } // end for
        return sUTiles;
        
    }
    
    
    public ArrayList<Tile> getShoreUpTiles() {
        return getShoreUpTiles(getReachableTiles());
    }
    
    
    public void shoreUp(Tile tile) {
        if (getActionPoints() >= 1 && getShoreUpTiles().contains(tile)) {
            tile.setState(TileState.DRIED);
            finishAction();
        } else {
            // FIXME : add throws
        }
    }
    
    
    /**
     * @author nihil
     *
     */
    protected void finishAction() {
        if (getActionPoints() > 0) {
            setActionPoints(getActionPoints() - 1);
        } else {
            // TODO : throw new
        } // end if
    }
    
    
    /**
     * 
     * 
     *
     * @param tile
     * @return true if the move done
     * @throws MoveException
     */
    public void move(Tile tile) throws MoveException {
        if (getActionPoints() >= 1 && getReachableTiles().contains(tile)) {
            setCurrentTile(tile);
            Parameters.printLog("le deplacement a été effectué", LogType.INFO);
            finishAction();
        } else {
            if (getActionPoints() <= 0) {
                throw new MoveException(tile);
            } else {
                throw new MoveException(getActionPoints());
            } // end if
        }
        
    }
    
    
    /**
     * get the adjacent tiles<br>
     * .*.<br>
     * *.*<br>
     * .*.
     * 
     * @author nihil
     *
     * @return
     */
    public ArrayList<Tile> getReachableTiles() {
        
        ArrayList<Tile> reachable = new ArrayList<>();
        Coords coords = getCurrentTile().getCoords();
        
        Island island = getPlayer().getCurrentGame().getIsland();
        Tile tileTmp;
        // we will apply a sweet function to get through -1,0,1,0 and meanwhile 0,1,0,-1 (uses of modulo is awesome)
        int j = 2;
        int effI;
        int effJ;
        for (int i = -1; i <= 2; i += 1) {
            effI = i % 2;
            effJ = j % 2;
            tileTmp = island.getTile(coords.getCol() + effI, coords.getRow() + effJ);
            if ((tileTmp != null) && (tileTmp.getState() != TileState.SINKED)) {
                reachable.add(tileTmp);
            }
            j--;
        } // end for
        return reachable;
        
    }
    
    
    /**
     * @author nihil
     *
     * @param tile
     * @throws InadequateUseOfCapacity
     */
    public void useCapacity(Object o) throws InadequateUseOfCapacity {
        throw new InadequateUseOfCapacity();
    }// end useCapacity
    
    
    /**
     * @author nihil
     *
     * @return the objects where a capacity can be applied
     * @throws InadequateUseOfCapacity
     */
    public ArrayList<Object> getPotentialUse() throws InadequateUseOfCapacity {
        throw new InadequateUseOfCapacity();
    }
    
    
    /**
     * @author nihil
     *
     */
    public void endTurn() {
        setActionPoints(MAX_ACTION_POINTS);
    }
    
    
    /**
     * @author nihil
     *
     * @param tile
     * the spawn to set
     */
    public void setSpawn(Tile tile) {
        if (getCurrentTile() == null) {
            setCurrentTile(tile);
            Parameters.printLog("set Spawn for " + getADVENTURER_TYPE(), LogType.INFO);
        } else {
            Parameters.printLog("Spawn already set for " + getADVENTURER_TYPE(), LogType.ERROR);
        } // end if
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
    
    
    /**
     * @return the adventurerType
     */
    public AdventurerType getADVENTURER_TYPE() {
        return ADVENTURER_TYPE;
    }
    
    
    /**
     * @author nihil
     *
     * @return
     */
    public ArrayList<InGameAction> getPossibleActions() {
        ArrayList<InGameAction> list = new ArrayList<>();
        if (getActionPoints() > 0) {
            list.add(InGameAction.GET_TREASURE);
            list.add(InGameAction.GIVE_CARD);
            list.add(InGameAction.MOVE);
            list.add(InGameAction.SHORE_UP_TILE);
        } // end if
        if (inventory.hasCardUsable()) {
            list.add(InGameAction.USE_CARD);
        } // end if
        return list;
    }
    
    
    public void giveCard(TreasureCard card, Player player) {
        if (card.getTreasureType().equals(getCurrentTile().getSite().geTreasureType())) {
            if (player.getCurrentAdventurer().getCurrentTile().equals(getCurrentTile())) {
                if (player.getCurrentAdventurer().getInventory().isFull()) {
                    if (getInventory().removeCard(card)) {
                        player.getCurrentAdventurer().recieveCard(card);
                    } else {
                        // FIXME : add throws
                        Parameters.printLog("il a pas la carte " + card + " dans l'inventaire de " + this,
                                LogType.ACCESS);
                    }
                }
            }
        }
    }
    
    
    public void recieveCard(Card card) {
        getInventory().addCard(card);
    }
    
    
    public void drawCard() {
        
    }
    
}