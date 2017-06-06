package model.game;

/**
 * a class to define a position
 * x : col
 * y : row
 * 
 * @author nihil
 *
 */
public class Coords {
    private Integer col;
    private Integer row;
    
    
    public Coords(Integer col, Integer row) {
        this.col = col;
        this.row = row;
    }
    
    
    /**
     * @return the x
     */
    public Integer getCol() {
        return col;
    }
    
    
    /**
     * @return the y
     */
    public Integer getRow() {
        return row;
    }
    
    
    @Override
    public String toString() {
        return "(" + getCol() + "," + getRow() + ")";
    }
}