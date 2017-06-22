package util.message;

import model.adventurers.AdventurerType;
import model.card.CardType;
import model.game.Coords;
import model.player.Player;



/**
 * @author nihil
 * each "give" statement represent content for ONE message<br>
 * if there is 2 gives there is 2 messages as well
 */
/**
 * @author nihil
 *
 */
public enum InGameAction {
    /**
     * @give a {@link Coords}
     */
    SELECT_TILE,
    /**
     * @give a {@link AdventurerType}
     */
    SELECT_PAWN,
    /**
     * @give nothing to get the reachable tiles(to support the basic IHM)
     * @give a {@link Coords} of where the player want to go (x,y) in 0..5
     */
    MOVE,
    /**
     * 
     * @give a {@link CardType} (that is in the player's inventory)<br>
     * and then
     * @give a {@link Coords} of affected tile
     */
    USE_CARD,
    USE_CARD_SAND_BAG,
    USE_CARD_HELICOPTER,
    DISCARD,
    /**
     * @give the type of draw
     */
    DRAW,
    DRAW_FLOOD,
    DRAW_TREASURE,
    /**
     * @give the {@link Player#name} who want to do the interruption
     * 
     * @when another player of the current want to do something (stack them)
     */
    INTERRUPT,
    /**
     * @give nothing to get the driable tiles
     * @give the {@link Coords} of the flooded tile
     */
    SHORE_UP_TILE,
    /**
     * @give a {@link Player#name} to init the trading
     * @give a the {@link CardType} the player want to give
     */
    GIVE_CARD,
    /**
     * @give a {@link Coords} of the tile where the player want to shelter
     * @when a player get into the water, them have to swim
     */
    SWIM,
    /**
     * @give nothing when a player want to get a treasure (they have 4 same treasure card and they are in the correct tile)
     */
    GET_TREASURE,
    /**
     * @give nothing to init the capacity
     * @give the {@link Player#name} in which to apply capacity (can be done as many times as the player want)
     * @give the {@link Coords} where the capacity is apply
     */
    USE_CAPACITY,
    /**
     * @give nothing
     */
    END_TURN,
    CHANGE_STATE_OF_TILE;
}