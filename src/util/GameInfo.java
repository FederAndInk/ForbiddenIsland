package util;

import java.util.Calendar;
import java.util.Date;

import model.player.Player;



public class GameInfo {
    
    private Integer score;
    private Date    date;
    private Boolean win;
    
    
    public GameInfo(boolean win, Player player) {
        date = Calendar.getInstance().getTime();
        this.win = win;
        this.score = getScore();
    }
    
    
    /**
     * @author nihil
     *
     * @return
     */
    private Integer getScore() {
        return null;
    }
    
}