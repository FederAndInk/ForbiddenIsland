/**
 * 
 */
package controller;

import java.util.ArrayList;
import java.util.Observable;
import java.util.Observer;
import java.util.Stack;

import javax.swing.JLayeredPane;

import model.adventurers.*;
import model.card.*;
import model.game.*;
import model.player.Inventory;
import model.player.Player;
import util.FIGraphics;
import util.LogType;
import util.Parameters;
import util.Temporary;
import util.exception.*;
import util.message.InGameAction;
import util.message.InGameMessage;
import util.message.MainMessage;
import view.Cards.PlayerCard;
import view.board.GameView;
import view.board.TilePanel;



/**
 * @author nihil
 *
 */
public class GameController implements Observer {
    private MainController mainController;
    private PlayerCard     cardSelected;
    private Stack<Player>  playersChain;
    private Game           currentGame;
    private GameView       gameView;
    
    
    /**
     * @author nihil
     *
     * @param mainController
     */
    public GameController(MainController mainController) {
        this.mainController = mainController;
        gameView = mainController.getView().getGameView();
        
        playersChain = new Stack<>();
    }
    
    
    /**
     * @author nihil
     * @param player
     *
     */
    private void chainPlayer(Player player) {
        playersChain.push(getCurrentGame().getCurrentPlayer());
        getCurrentGame().setCurrentPlayer(player);
    }
    
    
    /**
     * @author nihil
     *
     */
    private void chainPlayers(ArrayList<Player> players) {
        for (Player player : players) {
            chainPlayer(player);
        } // end for
    }
    
    
    /**
     * @author nihil
     * @param player
     *
     */
    private void unChainPlayer() {
        getCurrentGame().setCurrentPlayer(playersChain.pop());
    }
    
    
    @Temporary
    private void tmpGameStart() {
        Player p1 = new Player("p1");
        Player p2 = new Player("p2");
        Player p3 = new Player("p3");
        Player p4 = new Player("p4");
        getCurrentGame().addPlayer(new Diver(p1));
        getCurrentGame().addPlayer(new Explorer(p2));
        getCurrentGame().addPlayer(new Navigator(p3));
        getCurrentGame().addPlayer(new Engineer(p4));
    }// end tmpGameStart
    
    
    /**
     * @author nihil
     *
     */
    public void StartGame(SeaLevel seaLevel) {
        // tmpGameStart();
        getCurrentGame().initGame(seaLevel);
        
        FIGraphics.init(this);
        
        gameView.setBoard(getCurrentGame().getIsland().getSites(), this);
        gameView.initPlayerState(getCurrentGame().getPawns());
        gameView.addObs(this);
        gameView.addObs(getMainController());
        
        gameView.getFloodCursor().moveCursor(seaLevel);
        
        setSpawns();
        
        refreshBoard();
        defaultAction();
        for (Player player : currentGame.getPlayers()) {
            Inventory inv = player.getCurrentAdventurer().getInventory();
            for (Card card : inv.getCards()) {
                if (card instanceof FloodCard) {
                    refreshBoard();
                } else {
                    gameView.getPInventory(player.getCurrentAdventurer().getADVENTURER_TYPE())
                            .addCard(card.getType() == CardType.TREASURE_CARD ? ((TreasureCard) card).getTreasureCard()
                                    : card.getType());
                } // end if
            } // end for
        } // end for
        
        mainController.getView().switchToGame();
    }
    
    
    /**
     * @author nihil
     *
     */
    private void setSpawns() {
        for (Player player : getCurrentGame().getPlayers()) {
            JLayeredPane panel = gameView.getTileG(player.getCurrentAdventurer().getCurrentTile().getCoords());
            if (panel instanceof TilePanel) {
                ((TilePanel) panel).addPawn(player.getCurrentAdventurer().getADVENTURER_TYPE());
            } // end if
        } // end for
    }// end setSpawns
    
    
    /**
     * @author nihil
     *
     * @param coords
     */
    private void movePawn(Tile tile) {
        try {
            Game g = getCurrentGame();
            Adventurer adv = g.getCurrentPlayer().getCurrentAdventurer();
            Tile cTile = adv.getCurrentTile();
            
            adv.move(tile);
            Parameters.printLog("Move to " + tile, LogType.INFO);
            
            // to update the view
            gameView.movePawn(adv.getADVENTURER_TYPE(), cTile.getCoords(), tile.getCoords());
        } catch (MoveException e) {
            e.printStackTrace();
        } catch (ActionException e) {
            e.printStackTrace();
        } finally {
            defaultAction();
        }
        
    }
    
    
    /**
     * @author nihil
     *
     */
    private void swimPawn(Tile tile) {
        Game g = getCurrentGame();
        Adventurer adv = g.getCurrentPlayer().getCurrentAdventurer();
        Tile cTile = adv.getCurrentTile();
        
        try {
            adv.swim(tile);
            Parameters.printLog("Move to " + tile, LogType.INFO);
            // to update the view
            gameView.movePawn(adv.getADVENTURER_TYPE(), cTile.getCoords(), tile.getCoords());
        } catch (MoveException | ActionException e) {
            e.printStackTrace();
        } catch (EndGameException e) {
            mainController.getView().switchToEnd(e.getEndType());
        } finally {
            unChainPlayer();
            defaultAction();
        }
        if (!playersChain.isEmpty()) {
            setSwim();
        } // end if
        
    }
    
    
    /**
     * @author nihil
     *
     * @param tile
     */
    private void shoreUp(Tile tile) {
        Game g = getCurrentGame();
        Adventurer adv = g.getCurrentPlayer().getCurrentAdventurer();
        
        try {
            adv.shoreUp(tile);
            Parameters.printLog("Shore Up " + tile, LogType.INFO);
            
            // to update the view
            gameView.shoreUp(tile.getCoords());
        } catch (TileException | ActionException e) {
            e.printStackTrace();
        } finally {
            defaultAction();
        }
    }
    
    
    /**
     * @author nihil
     *
     * @param tile
     */
    private void useCapacity(Tile tile) {
        Adventurer adv = getCurrentGame().getCurrentPlayer().getCurrentAdventurer();
        Tile cTile = adv.getCurrentTile();
        ArrayList<Player> pSelects = getCurrentGame().getSelectedPlayers();
        
        try {
            Player pSelect = pSelects.isEmpty() ? adv.getPlayer() : pSelects.get(0);
            adv.useCapacity(tile, pSelect);
            gameView.movePawn(pSelect.getCurrentAdventurer().getADVENTURER_TYPE(), cTile.getCoords(), tile.getCoords());
        } catch (InadequateUseOfCapacityException | MoveException | ActionException e) {
            e.printStackTrace();
        } catch (NavigatorCannotMoveHimselfException e) {
            e.printStackTrace();
        } finally {
            defaultAction();
        }
    }
    
    
    /**
     * @author nihil
     *
     * @param coords
     */
    private void useCardSB(SandBag sandBag, Tile tile) {
        try {
            sandBag.applyAction(tile, null);
            Parameters.printLog("Shore Up " + tile, LogType.INFO);
            
            gameView.shoreUp(tile.getCoords());
        } catch (TileException e) {
            e.printStackTrace();
        } finally {
            defaultAction();
        }
    }
    
    
    /**
     * @author nihil
     *
     * @param helicopter
     * @param tile
     */
    private void useCardHC(Helicopter helicopter, Tile tile, ArrayList<Player> players) {
        try {
            Coords current = players.get(0).getCurrentAdventurer().getCurrentTile().getCoords();
            helicopter.applyAction(tile, players);
            Parameters.printLog("Use helicopter " + tile, LogType.INFO);
            
            for (Player player : players) {
                Adventurer adv = player.getCurrentAdventurer();
                gameView.movePawn(adv.getADVENTURER_TYPE(), current, adv.getCurrentTile().getCoords());
            } // end for
        } catch (IllegalAccessException | MoveException | TileException e) {
            e.printStackTrace();
        } catch (EndGameException e) {
            mainController.getView().switchToEnd(e.getEndType());
        } finally {
            getCurrentGame().deselectPlayers();
            defaultAction();
        }
    }
    
    
    /**
     * @author nihil
     *
     */
    private void getTreasure() {
        try {
            // SEENOW : Treasure
            getCurrentGame().addTreasureToPlayer(null);
            Parameters.printLog("Get treasure", LogType.INFO);
        } catch (ActionException e) {
            e.printStackTrace();
        } catch (NotEnoughCardsException e) {
            e.printStackTrace();
        } catch (WrongTileTreasureException e) {
            e.printStackTrace();
        } finally {
            defaultAction();
        }
    }
    
    
    /**
     * For debug
     * 
     * @author nihil
     *
     */
    private void changeTileState(Tile tile, TileState state) {
        TilePanel tileG = (TilePanel) gameView.getTileG(tile.getCoords());
        Parameters.printLog("Change state of " + tile.getSite() + " to " + state, LogType.INFO);
        tileG.setState(state);
        try {
            currentGame.setTileState(tile, state);
        } catch (PlayerOutOfIslandException e) {
            chainPlayers(getCurrentGame().getPlayersOnTile(tile));
            setSwim();
        } catch (EndGameException ex) {
            mainController.getView().switchToEnd(ex.getEndType());
        }
        defaultAction();
    }
    
    
    private void setSwim() {
        Player p = getCurrentGame().getCurrentPlayer();
        Parameters.printLog("set swim for " + p.getCurrentAdventurer(), LogType.INFO);
        getCurrentGame().setCurrentAction(InGameAction.SWIM);
        try {
            refreshBoard(p.getCurrentAdventurer().getSwimmableTiles(), InGameAction.SWIM);
        } catch (EndGameException e) {
            mainController.getView().switchToEnd(e.getEndType());
        }
    }// end swim
    
    
    /**
     * to refresh the tile states
     * 
     * @author nihil
     *
     */
    private void refreshBoard() {
        reInitBoard();
        Tile[][] grid = getCurrentGame().getIsland().getGrid();
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[i].length; j++) {
                if (gameView.getTileG(new Coords(j, i)) instanceof TilePanel) {
                    TilePanel tileG = (TilePanel) gameView.getTileG(new Coords(j, i));
                    tileG.setState(grid[j][i].getState());
                } // end if
            } // end for
        } // end
    } // end for
    
    
    /**
     * @author nihil
     *
     */
    private void refreshBoard(ArrayList<Tile> selectedTiles, InGameAction action) {
        reInitBoard();
        for (Tile tile : selectedTiles) {
            gameView.setEnabled(true, tile.getCoords(), action);
        } // end for
    }
    
    
    /**
     * set all tiles disable
     * 
     * @author nihil
     *
     */
    private void reInitBoard() {
        selectAllPawns(false);
        selectAllCards(false);
        for (Player player : getCurrentGame().getSelectedPlayers()) {
            Adventurer adv = player.getCurrentAdventurer();
            gameView.setSelectPawn(true, adv.getADVENTURER_TYPE(), adv.getCurrentTile().getCoords());
        } // end for
        for (int i = 0; i < Island.GRID_SIZE.getRow(); i++) {
            for (int j = 0; j < Island.GRID_SIZE.getCol(); j++) {
                JLayeredPane tile = gameView.getTileG(new Coords(j, i));
                if (tile instanceof TilePanel) {
                    ((TilePanel) tile).setEnabled(false);
                } // end if
            } // end for
        } // end for
        gameView.doLayout();
    }
    
    
    /**
     * to update the view for move action of the current player
     * 
     * @author nihil
     *
     */
    private void setMoveAction() {
        resetActions();
        if (getCurrentGame().getCurrentPlayer().getCurrentAdventurer().getActionPoints() > 0) {
            gameView.notifyPlayers("Cliquer sur une partie de l'ile en vert pour vous y rendre");
            getCurrentGame().setCurrentAction(InGameAction.MOVE);
            Parameters.printLog("get Move", LogType.INFO);
            refreshBoard(getCurrentGame().getCurrentPlayer().getCurrentAdventurer().getReachableTiles(),
                    InGameAction.MOVE);
        } else {
            defaultAction();
        } // end if
    }
    
    
    /**
     * @author nihil
     *
     */
    private void resetActions() {
        getCurrentGame().deselectPlayers();
        reInitBoard();
    }
    
    
    /**
     * to update the view for using capacity action of the current player
     * 
     * @author nihil
     *
     */
    private void setCapacityActionT() {
        resetActions();
        if (getCurrentGame().getCurrentPlayer().getCurrentAdventurer().getActionPoints() > 0) {
            
            gameView.notifyPlayers("Cliquer sur une partie de l'ile en vert pour vous y rendre");
            getCurrentGame().setCurrentAction(InGameAction.USE_CAPACITY);
            Parameters.printLog("get Capacity", LogType.INFO);
            
            ArrayList<Object> objs;
            ArrayList<Tile> tiles = new ArrayList<>();
            try {
                objs = getCurrentGame().getCurrentPlayer().getCurrentAdventurer().getPotentialUse(null);
                for (int i = 0; i < objs.size(); i++) {
                    tiles.add(i, (Tile) objs.get(i));
                } // end for
                refreshBoard(tiles, InGameAction.USE_CAPACITY);
            } catch (InadequateUseOfCapacityException e) {
                e.printStackTrace();
            }
        } else {
            verifyEndTurn();
        } // end if
    }
    
    
    /***
     * @author nihil
     * for the {@link Navigator} special action
     */
    private void setCapacityActionPT() {
        if (getCurrentGame().getCurrentPlayer().getCurrentAdventurer().getActionPoints() > 0) {
            ArrayList<Player> pSelects = getCurrentGame().getSelectedPlayers();
            reInitBoard();
            if (pSelects.isEmpty()) {
                gameView.notifyPlayers("Cliquer sur un joueur à déplacer");
                getCurrentGame().setCurrentAction(InGameAction.USE_CAPACITY);
                Parameters.printLog("get Capacity Navigator", LogType.INFO);
                
                for (Player player : getCurrentGame().getPlayers()) {
                    Adventurer adv = player.getCurrentAdventurer();
                    if (!adv.getADVENTURER_TYPE().equals(AdventurerType.NAVIGATOR)) {
                        setActivePawn(true, adv);
                    } // end if
                } // end for
            } else if (pSelects.size() == 1) {
                setActivePawns(false);
                setActivePawn(true, pSelects.get(0).getCurrentAdventurer());
                gameView.notifyPlayers("Cliquer sur un endroit où déplacer le joueur");
                getCurrentGame().setCurrentAction(InGameAction.USE_CAPACITY);
                Parameters.printLog("get Capacity Navigator", LogType.INFO);
                
                try {
                    ArrayList<Object> objs;
                    ArrayList<Tile> tiles = new ArrayList<>();
                    objs = getCurrentGame().getCurrentPlayer().getCurrentAdventurer().getPotentialUse(pSelects.get(0));
                    for (int i = 0; i < objs.size(); i++) {
                        tiles.add(i, (Tile) objs.get(i));
                    } // end for
                    refreshBoard(tiles, InGameAction.USE_CAPACITY);
                } catch (InadequateUseOfCapacityException e) {
                    e.printStackTrace();
                }
            } // end if
        } else {
            verifyEndTurn();
        } // end if
    }
    
    
    /**
     * call when the turn is up
     * 
     * @author nihil
     *
     */
    private void endTurn() {
        getCurrentGame().endTurn();
        if (getCurrentGame().getCurrentAction() == InGameAction.DRAW_FLOOD) {
            gameView.notifyPlayers("Piocher une carte dans le deck des cartes inondations");
        } else if (getCurrentGame().getCurrentAction() == InGameAction.DRAW_TREASURE) {
            gameView.notifyPlayers("Piocher une carte dans le deck des cartes Tresores");
        } else {
            gameView.notifyPlayers("C'est la fin de votre tour");
        } // end if
        defaultAction();
    }// end endTurn
    
    
    /**
     * @author nihil
     *
     */
    private void setUseSandBag(SandBag sandBag) {
        resetActions();
        // FIXME : change by player inventory card
        ArrayList<Tile> tiles = sandBag.getTilesDest(getCurrentGame().getIsland());
        if (tiles.isEmpty()) {
            gameView.notifyPlayers("Aucune partie de l'ile à assécher");
            defaultAction();
        } else {
            getCurrentGame().setCurrentAction(InGameAction.USE_CARD_SAND_BAG);
            gameView.notifyPlayers("Cliquer sur une partie de l'ile en beige pour l'assécher");
            Parameters.printLog("get Shore Up tiles", LogType.INFO);
            refreshBoard(tiles, InGameAction.SHORE_UP_TILE);
        } // end if
    }
    
    
    /**
     * @author nihil
     *
     * @param helicopter
     */
    private void setUseHelicopter(Helicopter helicopter) {
        ArrayList<Tile> tiles = helicopter.getTilesDest(getCurrentGame().getIsland());
        // to remove the current tile
        tiles.remove(getCurrentGame().getSelectedPlayers().get(0).getCurrentAdventurer().getCurrentTile());
        if (tiles.isEmpty()) {
            gameView.notifyPlayers("Impossible :/");
            defaultAction();
        } else {
            getCurrentGame().setCurrentAction(InGameAction.USE_CARD_HELICOPTER);
            gameView.notifyPlayers("Cliquer sur une partie de l'ile pour vous y rendre");
            Parameters.printLog("get Helicopter tiles", LogType.INFO);
            refreshBoard(tiles, InGameAction.USE_CARD_HELICOPTER);
        } // end if
    }
    
    
    /**
     * @author nihil
     *
     */
    private void setShoreUpAction() {
        resetActions();
        ArrayList<Tile> tiles = getCurrentGame().getCurrentPlayer().getCurrentAdventurer().getShoreUpTiles();
        if (tiles.isEmpty()) {
            gameView.notifyPlayers("Aucune partie de l'ile proche de vous à assécher");
            defaultAction();
        } else {
            getCurrentGame().setCurrentAction(InGameAction.SHORE_UP_TILE);
            gameView.notifyPlayers("Cliquer sur une partie de l'ile en beige pour l'assécher");
            Parameters.printLog("get Shore Up tiles", LogType.INFO);
            refreshBoard(tiles, InGameAction.SHORE_UP_TILE);
        } // end if
    }
    
    
    /**
     * set smart action (the most probable action)
     * 
     * @author nihil
     *
     */
    private void defaultAction() {
        ArrayList<InGameAction> acts = getCurrentGame().getPossibleActions();
        resetActions();
        
        if (acts.contains(InGameAction.GET_TREASURE)) {
            // TODO : setGetTreasure
        } else if (acts.contains(InGameAction.MOVE)) {
            setMoveAction();
        } else if (acts.contains(InGameAction.SHORE_UP_TILE)) {
            setShoreUpAction();
        }
        turnGestion();
    }
    
    
    /**
     * @author nihil
     * @param helicopter
     *
     */
    private void discardCard(Card card) {
        gameView.getPInventory(getCurrentGame().getCurrentPlayer().getCurrentAdventurer().getADVENTURER_TYPE())
                .removeCard(cardSelected.getCardPlace());
        getCurrentGame().getCurrentPlayer().getCurrentAdventurer().getInventory().removeCard(card);
    }
    
    
    /**
     * @author nihil
     * @param ctype
     *
     */
    private void discardCard(CardType ctype) {
        if (cardSelected.getCard().getTreasureType() != null) {
            try {
                discardCard(getCurrentGame().getCurrentPlayer().getCurrentAdventurer().getInventory()
                        .getCard(ctype.getTreasureType()));
            } catch (MissingCardException e) {
                e.printStackTrace();
            }
        } else {
            discardCard(getCurrentGame().getCurrentPlayer().getCurrentAdventurer().getInventory().getCard(ctype));
        } // end if
        gameView.doLayout();
        defaultAction();
    }
    
    
    /**
     * @author nihil
     *
     */
    private void drawCard() {
        try {
            Card card = getCurrentGame().drawEndTurnCard(getCurrentGame().getCurrentAction());
            Parameters.printLog("Draw " + card.getType(), LogType.INFO);
            if (card instanceof WatersRise) {
                gameView.getFloodCursor().moveCursor(getCurrentGame().getSeaLevel());
                gameView.getTreasureDeck().discard(CardType.WATERSRISE_CARD);
            } else if (card instanceof FloodCard) {
                refreshBoard();
            } else {
                gameView.getPInventory(getCurrentGame().getCurrentPlayer().getCurrentAdventurer().getADVENTURER_TYPE())
                        .addCard(card.getType() == CardType.TREASURE_CARD ? ((TreasureCard) card).getTreasureCard()
                                : card.getType());
            } // end if
        } catch (IllegalAccessException | MoveException | TileException | CardException e1) {
            e1.printStackTrace();
        } catch (EndGameException e) {
            mainController.getView().switchToEnd(e.getEndType());
            
        } catch (PlayerOutOfIslandException e) {
            chainPlayers(getCurrentGame().getPlayersOnTile(e.getTile()));
            setSwim();
        }
    }
    
    
    /**
     * to set the view correctly after an action
     * 
     * @author nihil
     *
     */
    private void turnGestion() {
        verifyEndTurn();
        Player pl = getCurrentGame().getCurrentPlayer();
        gameView.setCurrentP(pl.getCurrentAdventurer().getADVENTURER_TYPE(), pl.getName(),
                pl.getCurrentAdventurer().getActionPoints());
        ArrayList<InGameAction> acts = getCurrentGame().getPossibleActions();
        InGameAction act = getCurrentGame().getCurrentAction();
        gameView.setActions(acts);
    }// end
     // turnGestion
    
    
    /**
     * change the state of a player (select or deselect)
     * 
     * @author nihil
     * @param player
     */
    private void togglePawnSelection(AdventurerType advT) {
        reInitBoard();
        Player p = getCurrentGame().getPlayer(advT);
        getCurrentGame().toggleSelectionPlayer(p);
        setActivePawns(true);
        for (Player player : getCurrentGame().getSelectedPlayers()) {
            gameView.setSelectPawn(true, player.getCurrentAdventurer().getADVENTURER_TYPE(),
                    player.getCurrentAdventurer().getCurrentTile().getCoords());
        } // end for
    }
    
    
    /**
     * @author nihil
     *
     */
    private void selectCard(CardType cType, boolean active) {
        AdventurerType adv = getCurrentGame().getCurrentPlayer().getCurrentAdventurer().getADVENTURER_TYPE();
        if (cType == CardType.TREASURE_CARD) {
            for (PlayerCard card : gameView.getPInventory(adv).getCards()) {
                if (!card.getCard().isActivable()) {
                    card.setEnabled(active);
                } // end if
            } // end for
        } else {
            for (PlayerCard card : gameView.getPInventory(adv).getCards()) {
                if (card.getCard().isActivable() && card.getCard().isCanAddToInventory()) {
                    card.setEnabled(active);
                } // end if
            } // end for
            
        } // end if
        
    }
    
    
    /**
     * @author nihil
     *
     */
    private void selectAllCards(boolean active) {
        AdventurerType adv = getCurrentGame().getCurrentPlayer().getCurrentAdventurer().getADVENTURER_TYPE();
        for (PlayerCard card : gameView.getPInventory(adv).getCards()) {
            card.setEnabled(active);
        } // end for
    }
    
    
    private void setActivePawns(boolean active) {
        if (getCurrentGame().getSelectedPlayers().isEmpty()) {
            selectAllPawns(active);
        } else {
            selectNearPawns(active);
        } // end if
    }// end activePlayers
    
    
    private void setActivePawn(boolean active, Adventurer adv) {
        gameView.setActivePawn(active, adv.getADVENTURER_TYPE(), adv.getCurrentTile().getCoords());
    }
    
    
    /**
     * @author nihil
     *
     * @param active
     */
    private void selectNearPawns(boolean active) {
        Tile tile = getCurrentGame().getSelectedPlayers().get(0).getCurrentAdventurer().getCurrentTile();
        for (Player player : getCurrentGame().getPlayersOnTile(tile)) {
            Adventurer adv = player.getCurrentAdventurer();
            gameView.setActivePawn(active, adv.getADVENTURER_TYPE(), adv.getCurrentTile().getCoords());
        } // end for
    }
    
    
    private void selectAllPawns(boolean active) {
        for (Player player : getCurrentGame().getPlayers()) {
            Adventurer adv = player.getCurrentAdventurer();
            gameView.setActivePawn(active, adv.getADVENTURER_TYPE(), adv.getCurrentTile().getCoords());
        } // end for
    }
    
    
    /**
     * @author nihil
     * @param object
     *
     */
    private void doAction(Object object) {
        Adventurer adv = getCurrentGame().getCurrentPlayer().getCurrentAdventurer();
        
        if (object instanceof Coords) {
            Tile tile = getCurrentGame().getIsland().getTile((Coords) object);
            
            switch (getCurrentGame().getCurrentAction()) {
            case MOVE:
                movePawn(tile);
                break;
            case SWIM:
                swimPawn(tile);
                break;
            case SHORE_UP_TILE:
                shoreUp(tile);
                break;
            case USE_CAPACITY:
                useCapacity(tile);
                break;
            case USE_CARD_SAND_BAG:
                if (Parameters.debugAction) {
                    useCardSB(new SandBag(), tile);
                } else {
                    // FIXME : act with player inventory
                } // end if
                break;
            case USE_CARD_HELICOPTER:
                if (Parameters.debugAction) {
                    useCardHC(new Helicopter(), tile, getCurrentGame().getSelectedPlayers());
                } else {
                    useCardHC((Helicopter) adv.getInventory().getCard(CardType.HELICOPTER_CARD), tile,
                            getCurrentGame().getSelectedPlayers());
                    discardCard(adv.getInventory().getCard(CardType.HELICOPTER_CARD));
                } // end if
                break;
            default:
                throw new UnsupportedOperationException();
            }// end switch
        } else if (object instanceof AdventurerType) {
            AdventurerType advGet = (AdventurerType) object;
            switch (getCurrentGame().getCurrentAction()) {
            case USE_CARD_HELICOPTER:
                togglePawnSelection(advGet);
                break;
            case USE_CAPACITY:
                setActivePawns(false);
                getCurrentGame().toggleSelectionPlayer(getCurrentGame().getPlayer(advGet));
                setCapacityActionPT();
                break;
            case GIVE_CARD:
                reInitBoard();
                selectAllPawns(false);
                getCurrentGame().toggleSelectionPlayer(getCurrentGame().getPlayer(advGet));
                selectCard(CardType.TREASURE_CARD, true);
                break;
            
            default:
                throw new UnsupportedOperationException();
            }// end switch
        } else if (object instanceof PlayerCard) {
            reInitBoard();
            cardSelected = (PlayerCard) object;
            CardType ctype = cardSelected.getCard();
            switch (getCurrentGame().getCurrentAction()) {
            case USE_CARD:
                getCurrentGame().setCurrentAction(ctype.getType());
                if (ctype.getType().equals(CardType.SANDBAG_CARD)) {
                    setUseSandBag((SandBag) adv.getInventory().getCard(ctype));
                } else if (cardSelected.getCard().getType().equals(CardType.HELICOPTER_CARD)) {
                    setUseHelicopter((Helicopter) adv.getInventory().getCard(ctype));
                }
                
                break;
            case GIVE_CARD:
                try {
                    Parameters.printLog("Give card ", LogType.INFO);
                    discardCard(adv.giveCard(getCurrentGame().getSelectedPlayers().get(0), ctype));
                    gameView.getPInventory(
                            getCurrentGame().getSelectedPlayers().get(0).getCurrentAdventurer().getADVENTURER_TYPE())
                            .addCard(ctype);
                } catch (CardException e) {
                    e.printStackTrace();
                } catch (GiveCardException e) {
                    e.printStackTrace();
                } catch (MissingCardException e) {
                    e.printStackTrace();
                } finally {
                    defaultAction();
                }
                break;
            case DISCARD:
                discardCard(ctype);
                break;
            default:
            }
        }
        
        if (Parameters.debugAction) {
            Parameters.debugAction = false;
        } // end if
    }
    
    
    /**
     * @see java.util.Observer#update(java.util.Observable, java.lang.Object)
     */
    @Override
    public void update(Observable arg0, Object arg1) {
        if (arg1 instanceof InGameMessage) {
            InGameMessage m = (InGameMessage) arg1;
            
            switch ((InGameAction) m.getType()) {
            case SELECT_TILE:
                doAction(m.getContent());
                break;
            case SELECT_PAWN:
                if (m.getContent() instanceof AdventurerType) {
                    doAction(m.getContent());
                } else {
                    throw new IllegalArgumentException("not an adventurerType");
                } // end if
                break;
            case SELECT_CARD:
                doAction(m.getContent());
                break;
            case MOVE:
                setMoveAction();
                
                break;
            case GET_TREASURE:
                getTreasure();
                break;
            case GIVE_CARD:
                reInitBoard();
                if (getCurrentGame().getCurrentPlayer().getCurrentAdventurer() instanceof Messenger) {
                    selectAllPawns(true);
                    
                } else {
                    getCurrentGame().toggleSelectionPlayer(getCurrentGame().getCurrentPlayer());
                    selectNearPawns(true);
                } // end if
                setActivePawn(false, getCurrentGame().getCurrentPlayer().getCurrentAdventurer());
                getCurrentGame().setCurrentAction(InGameAction.GIVE_CARD);
                break;
            case SHORE_UP_TILE:
                setShoreUpAction();
                break;
            case USE_CAPACITY:
                if (getCurrentGame().getCurrentPlayer().getCurrentAdventurer() instanceof Navigator) {
                    setCapacityActionPT();
                } else {
                    setCapacityActionT();
                } // end if
                break;
            case USE_CARD:
                if (m.getContent().equals(CardType.SANDBAG_CARD)) {
                    if (Parameters.debugAction) {
                        setUseSandBag(new SandBag());
                    }
                } else if (m.getContent().equals(CardType.HELICOPTER_CARD)) {
                    if (Parameters.debugAction) {
                        setUseHelicopter(new Helicopter());
                    }
                }
                break;
            case USE_CARD_HELICOPTER:
                reInitBoard();
                getCurrentGame().setCurrentAction(InGameAction.USE_CARD_HELICOPTER);
                setActivePawns(true);
                break;
            case INTERRUPT:
                
                break;
            case CHANGE_STATE_OF_TILE:
                Coords coords = (Coords) m.getContent();
                Tile tile = getCurrentGame().getIsland().getTile(coords);
                changeTileState(tile, tile.getState().next());
                break;
            case DISCARD:
                selectAllCards(true);
                getCurrentGame().setCurrentAction(InGameAction.DISCARD);
                break;
            case DRAW:
                if (!getCurrentGame().getCurrentAction().equals(InGameAction.END_TURN)) {
                    drawCard();
                } // end if
                getCurrentGame().setDrawCard();
                turnGestion();
                break;
            case END_TURN:
                endTurn();
                break;
            
            default:
                break;
            }// end switch
            
            turnGestion();
        } else if (arg1 instanceof MainMessage) {
            Parameters.printLog("Main action Message", LogType.INFO);
        } else {
            throw new IllegalArgumentException("The class " + arg0.getClass().getName() + " was going to send "
                    + arg1.getClass() + " Object, but a " + InGameMessage.class.getName() + " is expected");
        } // end if
    }
    
    
    /**
     * verify if the turn can be end
     * 
     * @author nihil
     * @return true if this is the end of turn (no actions remaining)
     *
     */
    private boolean verifyEndTurn() {
        ArrayList<InGameAction> acts = getCurrentGame().getPossibleActions();
        if (acts.size() == 1 && acts.contains(InGameAction.END_TURN)) {
            gameView.setEndTurn(true);
            gameView.notifyPlayers("C'est la fin du tour pour " + getCurrentGame().getCurrentPlayer().getName());
            reInitBoard();
            Parameters.printLog("SetEndTurn", LogType.INFO);
            return true;
        } else {
            gameView.setEndTurn(false);
            return false;
        } // end if
    }
    
    
    /**
     * @return the mainController
     */
    public MainController getMainController() {
        return mainController;
    }
    
    
    public Game getCurrentGame() {
        return currentGame;
    }
    
    
    public void setCurrentGame(Game currentGame) {
        this.currentGame = currentGame;
    }
}
