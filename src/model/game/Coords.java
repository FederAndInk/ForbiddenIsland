package model.game;

public class Coords {
    private Integer x;
    private Integer y;
    
    
    public Coords(Integer x, Integer y) {
        this.x = x;
        this.y = y;
    }
    
    
    /**
     * @return the x
     */
    public Integer getX() {
        return x;
    }
    
    
    /**
     * @return the y
     */
    public Integer getY() {
        return y;
    }
    
    
    @Override
    public String toString() {
        return "(" + getX() + "," + getY() + ")";
    }
}