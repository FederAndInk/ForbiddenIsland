package model.game;

import util.BoardGeneration;
import util.LogType;
import util.Parameters;
import util.exception.EndGameException;
import util.exception.ExceptionType;



public class Tile {
    
    private Coords    coords;
    private Site      site;
    private TileState state;
    
    
    public Tile(Coords coords, Site site) {
        setCoords(coords);
        setSite(site);
        try {
            setState(TileState.DRIED);
        } catch (EndGameException ex) {
        }
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
     * @throws util.exception.EndGameException
     */
    public void setState(TileState state) throws EndGameException {
        Parameters.printLog("The tile " + site + " change in " + state, LogType.INFO);
        this.state = state;
        if (getSite() == Site.FOOLS_LANDING && this.state == TileState.SINKED) {
            throw new EndGameException(ExceptionType.END_GAME_HELI);
        }
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