/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package util.exception;

import model.game.Tile;



/**
 *
 * @author chatonreveur
 */
public class WrongTileTreasureException extends ForbiddenIslandException {
    
    public WrongTileTreasureException(Tile tile, String nmTreasure) {
        super("Vous êtes sur la tuile " + tile + " et elle ne permet pas la creation du tresor" + nmTreasure,
                ExceptionType.WRONG_TILE_TREASURE);
    }
}
