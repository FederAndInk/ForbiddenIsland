package model.game;

import util.BoardGeneration;



public class Tile {
    
    private Coords    coords;
    private Site      site;
    private TileState state;
    
    
    public Tile(Coords coords, Site site) {
        setCoords(coords);
        setSite(site);
        setState(TileState.DRIED);
    }
    
    
    public void shoreUp() {
        // FIXME - implement Tile.shoreUp
        throw new UnsupportedOperationException();
    }
    
    
    public boolean canBeShoredUp() {
        // TODO - implement Tile.canBeShoredUp
        throw new UnsupportedOperationException();
    }
    
    
    /**
     * @return the coords
     */
    public Coords getCoords() {
        return coords;
    }
    
    
    /**
     * @param coords
     * the coords to set
     */
    public void setCoords(Coords coords) {
        this.coords = coords;
    }
    
    
    /**
     * @return the site
     */
    public Site getSite() {
        return site;
    }
    
    
    /**
     * @param site
     * the site to set
     */
    public void setSite(Site site) {
        this.site = site;
    }
    
    
    /**
     * @return the state
     */
    public TileState getState() {
        return state;
    }
    
    
    /**
     * Used only by {@link BoardGeneration} and {@link Game#setTileState(Tile, TileState)}
     * 
     * @param state
     * the state to set
     */
    public void setState(TileState state) {
        this.state = state;
    }
    
    
    /**
     * @see java.lang.Object#toString()
     */
    @Override
    public String toString() {
        return getCoords().toString() + " : " + getState() + " : " + getSite();
    }
    
    
    /**
     * @author nihil
     *
     * @return
     */
    public boolean notSinked() {
        return !getState().equals(TileState.SINKED);
    }
    
}