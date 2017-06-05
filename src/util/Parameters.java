/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package util;

import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Calendar;



/**
 *
 * @author Eric
 */
public class Parameters {
    
    // ====================================================================================
    // Paramètres NF
    public static final Boolean LOGS  = true; // Afficher des traces par System.out.println()
    public static final Boolean ALEAS = true; // Attribuer les aventuriers aléatoirement ou non, mélanger les défausses et les pioches
    // Fonts
    public static Font DEFAULT_FONT;
    // screen
    public static Dimension appSize;
    public static Boolean   fullscreen;
    // path
    public static final String RESOURCES  = "./resources/";
    public static final String CARDS      = RESOURCES + "cards/";
    public static final String LOGO       = RESOURCES + "logo/";
    public static final String TILES      = RESOURCES + "tiles/";
    public static final String TREASURES  = RESOURCES + "treasures/";
    public static final String ADVENTURER = RESOURCES + "adventurer/";
    
    
    /**
     * @author nihil
     *
     */
    public Parameters() {
    }
    
    
    /**
     * @author nihil
     *
     */
    public static void initialParameters() {
        initFont();
        fullscreen = true;
        appSize = Toolkit.getDefaultToolkit().getScreenSize();
    }
    
    
    /**
     * @author nihil
     *
     */
    private static void initFont() {
        GraphicsEnvironment genv = GraphicsEnvironment.getLocalGraphicsEnvironment();
        try {
            DEFAULT_FONT = Font.createFont(Font.TRUETYPE_FONT, new File("./resources/Treamd.ttf"));
            printLog(DEFAULT_FONT.getFontName() + "is set for the default font");
            genv.registerFont(DEFAULT_FONT);
        } catch (FontFormatException | IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }
    
    
    /**
     * this function allow the log printing management
     * 
     * @author nihil
     *
     */
    public static void printLog(Object text) {
        if (LOGS) {
            Calendar cal = Calendar.getInstance();
            SimpleDateFormat format1 = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");
            
            System.out.println(format1.format(cal.getTime()) + " : " + text.toString());
        } // end if
    }
}
