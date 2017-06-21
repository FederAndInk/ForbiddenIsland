/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package util.exception;

import model.adventurers.AdventurerType;



/**
 *
 * @author chatonreveur
 */
public class NavigatorCannotMoveHimselfException extends ForbiddenIslandException {
    
    public NavigatorCannotMoveHimselfException(AdventurerType type) {
        super("L'aventurier " + type.toString() + " ne peut se deplacer lui même",
                ExceptionType.NAVIGATOR_CANNOT_MOVE_HIMSELF);
    }
    
}
