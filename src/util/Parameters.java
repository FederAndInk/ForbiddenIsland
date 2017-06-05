/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package util;

import java.awt.*;
import java.io.File;
import java.io.IOException;



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
    public static final String DEFAULTFONT = "Treasure Map Deadhand";
    public static Dimension    appSize;
    public static Boolean      fullscreen;
    
    
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
        Font font;
        try {
            font = Font.createFont(Font.TRUETYPE_FONT, new File("./resources/Treamd.ttf"));
            System.out.println(font.getFontName());
            genv.registerFont(font);
        } catch (FontFormatException | IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }
}
