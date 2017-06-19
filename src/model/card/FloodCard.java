package model.card;

import model.game.Site;



public class FloodCard extends Card {
    private Site site;
    
    
    /**
     * @author nihil
     *
     * @param type
     */
    public FloodCard(Site site) {
        super(CardType.FLOOD_CARD);
        this.site = site;
    }
    
    
    public Site getSite() {
        return site;
    }
}