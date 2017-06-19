package model.player;

import java.util.ArrayList;

import model.adventurers.Adventurer;
import model.card.Card;
import model.card.CardType;
import model.card.TreasureCard;
import model.game.Treasure;
import model.game.TreasureType;
import util.LogType;
import util.Parameters;
import util.exception.CardException;
import util.exception.MissingCard;



public class Inventory {
    
    private ArrayList<Card>     cards;
    private ArrayList<Treasure> tresures;
    private Adventurer          adventurer;
    private static final int    MAX_CARD = 5;
    
    
    /**
     * @author nihil
     *
     */
    public Inventory(Adventurer adventurer) {
        cards = new ArrayList<>();
        tresures = new ArrayList<>();
        this.adventurer = adventurer;
    }
    
    
    /**
     * 
     * @param card
     */
    public void discard(Card card) {
        // TODO - implement Inventory.discard
        throw new UnsupportedOperationException();
    }
    
    
    /**
     * @author nihil
     *
     * @return true if the inventory has a card standalone usable
     */
    public boolean hasCardUsable() {
        for (Card card : cards) {
            if (card.getType().isUsable()) {
                return true;
            } // end if
        } // end for
        return false;
    }
    
    
    public boolean isFull() {
        return (cards.size() >= MAX_CARD);
    }
    
    
    public void addCard(Card card) throws CardException {
        if (!(getAdventurer().getPlayer().getCurrentAdventurer().getInventory().isFull())) {
            cards.add(card);
        } else {
            Parameters.printLog("l'inventaire est plein", LogType.INFO); // TODO : to move to recieve
            throw new CardException(card, getAdventurer().getADVENTURER_TYPE());
        }
    }
    
    
    public boolean removeCard(Card card) {
        return cards.remove(card);
    }
    
    
    public Card getCard() {
        return cards.get(1);
    }
    
    
    public Adventurer getAdventurer() {
        return adventurer;
    }
    
    
    /**
     * @param treasureType
     * @return the specified card or exception
     * @throws MissingCard
     */
    public Card getCard(TreasureType treasureType) throws MissingCard {
        for (Card i : cards) {
            if (i.getType().equals(CardType.TREASURE_CARD)
                    && ((TreasureCard) i).getTreasureType().equals(treasureType)) {
                return i;
            }
        }
        throw new MissingCard(treasureType, getAdventurer());
    }
    
    
    public Card getCard(CardType cardType) {
        for (Card card : cards) {
            if (card.getType().equals(cardType)) {
                return card;
            }
        }
        return null;
    }
    
}