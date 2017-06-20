package model.adventurers;

import java.util.ArrayList;

import model.card.Card;
import model.card.TreasureCard;
import model.game.*;
import model.player.Inventory;
import model.player.Player;
import util.LogType;
import util.Parameters;
import util.exception.*;
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
        setInventory(new Inventory(this));
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
    
    
    public void shoreUp(Tile tile) throws ActionException, TileException {
        if (getActionPoints() >= 1 && getShoreUpTiles().contains(tile)) {
            tile.setState(TileState.DRIED);
            finishAction();
        } else {
            if (getActionPoints() < 1) {
                throw new ActionException(getActionPoints());
            } else {
                throw new TileException(tile, tile.getState());
            } // end if
        }
    }
    
    
    /**
     * @author nihil
     * @throws ActionException
     *
     */
    protected void finishAction() throws ActionException {
        if (getActionPoints() > 0) {
            setActionPoints(getActionPoints() - 1);
        } else {
            throw new ActionException(getActionPoints());
        } // end if
    }
    
    
    /**
     * 
     * 
     *
     * @param tile
     * @return true if the move done
     * @throws MoveException
     * @throws ActionException
     */
    public void move(Tile tile) throws MoveException, ActionException {
        if (getActionPoints() >= 1 && getReachableTiles().contains(tile)) {
            setCurrentTile(tile);
            Parameters.printLog("le deplacement a été effectué", LogType.INFO);
            finishAction();
        } else {
            if (getActionPoints() <= 0) {
                throw new MoveException(tile);
            } else {
                throw new ActionException(getActionPoints());
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
     * swim, (when the adventurer get drown)
     * 
     * @author nihil
     *
     * @param tile
     * @throws MoveException
     * @throws ActionException
     * @throws EndGameException
     */
    public void swim(Tile tile) throws MoveException, ActionException, EndGameException {
        if (getSwimmableTiles().contains(tile)) {
            setActionPoints(1);
            move(tile);
        } else {
            throw new MoveException(tile);
        } // end if
    }
    
    
    /**
     * @author nihil
     *
     * @return the tiles where the adventurer can swim
     * @throws EndGameException
     */
    public ArrayList<Tile> getSwimmableTiles() throws EndGameException {
        if (currentTile.getState().equals(TileState.SINKED)) {
            setActionPoints(0);
        } // end if
        if (getReachableTiles().isEmpty()) {
            throw new EndGameException();
        } // end if
        return getReachableTiles();
    }
    
    
    public ArrayList<Card> createTreasure(Treasure treasure)
            throws ActionException, NotEnoughCardsException, WrongTileTreasureException {
        Tile tile = getCurrentTile();
        ArrayList<Card> removedCards = new ArrayList<>();
        if (tile.getSite().getTreasureType() != null) {
            if (inventory.howManyCards(tile.getSite().getTreasureType()) == 4
                    && treasure.getName() == tile.getSite().getTreasureType()) {
                if (getActionPoints() >= 1) {
                    inventory.addTreasure(treasure);
                    for (int i = 0; i <= 3; i++) {
                        removedCards.add(inventory.removeTreasureCard(tile.getSite().getTreasureType()));
                    }
                    return removedCards;
                } else {
                    throw new ActionException(getActionPoints());
                }
            } else {
                throw new NotEnoughCardsException(inventory.howManyCards(tile.getSite().getTreasureType()));
            }
        } else {
            throw new WrongTileTreasureException(tile, tile.getSite().getTreasureType().toString());
        }
    }
    
    
    /**
     * return the list of possible actions dynamically
     * 
     * @author nihil
     *
     * @return
     */
    public ArrayList<InGameAction> getPossibleActions() {
        ArrayList<InGameAction> list = new ArrayList<>();
        // if an adventurer get drow
        if (currentTile.getState().equals(TileState.SINKED)) {
            list.add(InGameAction.SWIM);
            return list;
        } // end if
        
        // action required
        if (getActionPoints() > 0) {
            list.add(InGameAction.GIVE_CARD);
            list.add(InGameAction.MOVE);
            if (!getShoreUpTiles().isEmpty()) {
                list.add(InGameAction.SHORE_UP_TILE);
            } // end if
        } // end if
          // no action required
        list.add(InGameAction.END_TURN);
        if (inventory.hasCardUsable()) {
            list.add(InGameAction.USE_CARD);
        } // end if
        return list;
    }
    
    
    /**
     * @author nihil
     *
     * @param tile
     * @throws InadequateUseOfCapacityException
     * @throws MoveException
     * @throws ActionException
     */
    public void useCapacity(Object o) throws InadequateUseOfCapacityException, MoveException, ActionException {
        throw new InadequateUseOfCapacityException();
    }// end useCapacity
    
    
    /**
     * @author nihil
     *
     * @return the objects where a capacity can be applied
     * @throws InadequateUseOfCapacityException
     */
    public ArrayList<Object> getPotentialUse() throws InadequateUseOfCapacityException {
        throw new InadequateUseOfCapacityException();
    }
    
    
    /**
     * 
     * @author nihil
     *
     */
    public void beginTurn() {
        setActionPoints(MAX_ACTION_POINTS);
    }
    
    
    /**
     * @author nihil
     *
     */
    public void endTurn() {
        setActionPoints(0);
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
    protected void setActionPoints(int actionPoints) {
        this.actionPoints = actionPoints;
    }
    
    
    /**
     * @return the adventurerType
     */
    public AdventurerType getADVENTURER_TYPE() {
        return ADVENTURER_TYPE;
    }
    
    
    public void giveCard(TreasureCard card, Player player)
            throws CardException, GiveCardException, MissingCardException {
        if (isExchangePossibleHere(card.getTreasureType())) {
            if (reachableExchangePlayer(player)) {
                if (getInventory().removeCard(card)) {
                    Parameters.printLog("\nle joueur " + getPlayer() + " donne la carte " + card, LogType.INFO);
                    player.getCurrentAdventurer().recieveCard(card);
                } else {
                    Parameters.printLog("il n'y a pas la carte " + card + " dans l'inventaire de " + this,
                            LogType.ERROR);
                    throw new MissingCardException(card.getTreasureType(), this);
                }
            } else {
                Parameters.printLog("les joueurs ne sont pas sur la même case", LogType.ERROR);
                throw new GiveCardException(this, player.getCurrentAdventurer());
            }
            
        } else {
            Parameters.printLog("le type de carte ne correspond pas", LogType.ERROR);
            throw new GiveCardException(card.getTreasureType(), this);
        }
    }
    
    
    protected boolean reachableExchangePlayer(Player player) {
        return (player.getCurrentAdventurer().getCurrentTile().equals(getCurrentTile()));
    }
    
    
    /**
     * @author nihil
     * @return true if the exchange of card is possible on the {@link #getCurrentTile()}
     *
     */
    protected boolean isExchangePossibleHere(TreasureType type) {
        return type.equals(getCurrentTile().getSite().getTreasureType());
    }
    
    
    public void recieveCard(TreasureCard card) throws CardException {
        Parameters.printLog("\nle joueur " + getPlayer() + " reçois la carte " + card, LogType.INFO);
        getInventory().addCard(card);
        
    }
}