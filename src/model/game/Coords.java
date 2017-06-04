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
     * @param x
     * the x to set
     */
    public void setX(Integer x) {
        this.x = x;
    }
    
    
    /**
     * @return the y
     */
    public Integer getY() {
        return y;
    }
    
    
    /**
     * @param y
     * the y to set
     */
    public void setY(Integer y) {
        this.y = y;
    }
    
    
    @Override
    public String toString() {
        return "(" + getX() + "," + getY() + ")";
    }
    
}