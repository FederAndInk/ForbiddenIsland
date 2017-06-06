/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package util;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

import model.game.Coords;
import model.game.Site;
import model.game.Tile;



/**
 *
 * @author lordofkawaiii
 */
public class BoardGeneration {
    
    /**
     * @author nihil
     *
     * @param boardType
     * @return a board of 24 tiles in terms of a given boardType
     * or return null for an invalid argument
     * 
     * @see BoardType
     */
    public static Tile[][] generateBoard(BoardType boardType) {
        switch (boardType) {
        case DEFAULT:
            return generateDefaultBoard();
        
        default:
            break;
        }// end switch
        return null;
    }
    
    
    private static Tile[][] generateDefaultBoard() {
        ArrayList<Site> l = new ArrayList<>(Arrays.asList(Site.values()));
        Tile[][] tiles = new Tile[6][6];
        Collections.shuffle(l);
        for (int i = 0; i < 6; i++) {
            for (int j = 0; j < 6; j++) {
                if (isBord(i, j)) {
                    tiles[j][i] = null;
                } else {
                    tiles[j][i] = new Tile(new Coords(j, i), l.remove(l.size() - 1));
                } // end if
            }
        }
        return tiles;
    }
    
    
    /**
     * **––**</br>
     * *––––*</br>
     * ––––––</br>
     * *––––*</br>
     * **––**</br>
     * 
     * @author nihil
     * 
     * 
     * 
     * @param i
     * (0-5)
     * the row
     * @param j
     * (0-5)
     * the column
     * @return true if i,j is in the corner (where the star is)
     */
    public static boolean isBord(int i, int j) {
        // @formatter:off
        return (i == 0) && (j == 0 || j == 1) || (i == 1 && j == 0) || (i == 5) && (j == 0 || j == 1)
                || (i == 4 && j == 0) || (i == 0) && (j == 4 || j == 5) || (i == 1 && j == 5)
                || (i == 5) && (j == 4 || j == 5) || (i == 4 && j == 5);
        // @formatter:on
    }// end
    
}
