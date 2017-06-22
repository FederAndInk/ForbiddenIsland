/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package util.exception;

/**
 *
 * @author chatonreveur
 */
public class NotEnoughCardsException extends ForbiddenIslandException {
    
    /**
     *
     * @param nbCards
     */
    public NotEnoughCardsException(int nbCards) {
        super("Vous n'avez que " + nbCards + " de ce type : action impossible", ExceptionType.NOT_ENOUGH_CARD);
    }
    
}
