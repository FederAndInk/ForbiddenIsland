/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package util;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import model.game.Site;
import model.game.Tile;



/**
 *
 * @author lordofkawaiii
 */
public class BoardGeneration {
    
    public static Tile[][] generateBoard(BoardType boardType) {
        if (boardType == BoardType.DEFAULT) {
            return generateDefaultBoard();
        }
    }
    
    
    private static Tile[][] generateDefaultBoard() {
        List l = Arrays.asList(Site.values());
        Collections.shuffle(l);
        for (int w = 0; w < 24; w++) {
            System.out.println(l.get(w));
        }
        for (int i = 0; i < 6; i++) {
            for (int j = 0; j < 6; j++) {
                
            }
            
        }
    }
    
}
