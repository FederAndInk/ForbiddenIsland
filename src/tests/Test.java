package tests;

import java.util.ArrayList;
import java.util.Observable;

import model.adventurers.Diver;
import model.game.Game;
import model.game.Island;
import model.game.Tile;
import model.game.TileState;
import model.player.Player;



public class Test extends Observable {
    public static final String ANSI_RESET             = "\u001B[0m";
    public static final String ANSI_BLACK             = "\u001B[30m";
    public static final String ANSI_RED               = "\u001B[31m";
    public static final String ANSI_GREEN             = "\u001B[32m";
    public static final String ANSI_YELLOW            = "\u001B[33m";
    public static final String ANSI_BLUE              = "\u001B[34m";
    public static final String ANSI_PURPLE            = "\u001B[35m";
    public static final String ANSI_CYAN              = "\u001B[36m";
    public static final String ANSI_WHITE             = "\u001B[37m";
    public static final String ANSI_BLACK_BACKGROUND  = "\u001B[40m";
    public static final String ANSI_RED_BACKGROUND    = "\u001B[41m";
    public static final String ANSI_GREEN_BACKGROUND  = "\u001B[42m";
    public static final String ANSI_YELLOW_BACKGROUND = "\u001B[43m";
    public static final String ANSI_BLUE_BACKGROUND   = "\u001B[44m";
    public static final String ANSI_PURPLE_BACKGROUND = "\u001B[45m";
    public static final String ANSI_CYAN_BACKGROUND   = "\u001B[46m";
    public static final String ANSI_WHITE_BACKGROUND  = "\u001B[47m";
    
    
    public static void main(String[] args) throws InterruptedException {
        Island island = new Island();
        Game game = new Game();
        Player p1 = new Player("p1");
        Player p2 = new Player("p2");
        game.addPlayer(p1, new Diver(p1));
        game.addPlayer(p2, new Diver(p2));
        p1.setCurrentGame(game);
        p2.setCurrentGame(game);
        game.initGame();
        Tile t = game.getIsland().getGrid()[2][2];
        Tile t2 = game.getIsland().getGrid()[3][4];
        game.getIsland().getGrid()[2][1].setState(TileState.SINKED);
        game.getIsland().getGrid()[2][3].setState(TileState.SINKED);
        game.getIsland().getGrid()[1][1].setState(TileState.SINKED);
        game.getIsland().getGrid()[1][2].setState(TileState.SINKED);
        game.getIsland().getGrid()[1][3].setState(TileState.SINKED);
        game.getIsland().getGrid()[1][4].setState(TileState.SINKED);
        game.getIsland().getGrid()[2][4].setState(TileState.SINKED);
        game.getIsland().getGrid()[3][3].setState(TileState.SINKED);
        Tile grid[][] = game.getIsland().getGrid();
        for (Tile[] tiles : grid) {
            for (Tile tile : tiles) {
                System.out.print(tile == null ? ANSI_BLACK_BACKGROUND + ' ' + ANSI_RESET
                        : tile.getState().equals(TileState.SINKED) ? ANSI_BLUE_BACKGROUND + " " + ANSI_RESET : "*");
            } // end for
            System.out.println();
        } // end for
        p1.getCurrentAdventurer().setCurrentTile(t);
        p2.getCurrentAdventurer().setCurrentTile(t2);
        System.out.println(game.getCurrentPlayer().getName());
        ArrayList<Tile> ts = game.getCurrentPlayer().getCurrentAdventurer().getReachableTiles();
        ts.sort((o1, o2) -> o1.getSite().compareTo(o2.getSite()));
        // FIXME : doublon !!
        int i = 1;
        for (Tile tile : ts) {
            System.out.println(tile.toString() + " " + i);
            i++;
        } // end for
        for (Tile[] tiles : grid) {
            for (Tile tile : tiles) {
                System.out.print(tile == null ? ANSI_BLACK_BACKGROUND + ' ' + ANSI_RESET
                        : tile.getState().equals(TileState.SINKED) ? ANSI_BLUE_BACKGROUND + " " + ANSI_RESET
                                : ts.contains(tile) ? ANSI_GREEN + "*" + ANSI_RESET
                                        : game.getCurrentPlayer().getCurrentAdventurer().getCurrentTile().equals(tile)
                                                ? ANSI_RED + "*" + ANSI_RESET : "*");
            } // end for
            System.out.println();
        } // end for
        System.out.println();
        System.out.println(ANSI_BLACK_BACKGROUND + " " + ANSI_RESET + " : hors map, " + ANSI_BLUE_BACKGROUND + " "
                + ANSI_RESET + " : tile sinked," + ANSI_RED + " *" + ANSI_RESET + " : joueur courrent, " + ANSI_GREEN
                + "*" + ANSI_RESET + " : deplacement possible, * : tile non accessible");
    }
    
}
