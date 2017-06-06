package util.message;

import model.adventurers.AdventurerType;



/**
 * @give nothing else for all of them
 *
 * @author nihil
 */
public enum MainAction {
    CREATE_GAME,
    /**
     * @give an {@link AdventurerType} to select an adventurer
     */
    SELECT_ADVENTURER,
    /**
     * @give the player wich select an adventurer
     */
    SELECT_PLAYER,
    
    /**
     * use for basic UI
     */
    ADD_PLAYER,
    /**
     * use for basic UI
     */
    ADD_PLAYER_TO_GAME,
    GET_HELP,
    SCORES,
    PARAMETERS,
    /**
     * use for basic UI
     */
    BEGIN_GAME,
    SAVE_GAME,
    LOAD_GAME,
    REMOVE_GAME,
    QUIT;
}
