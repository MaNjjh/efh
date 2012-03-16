package ru.znay.znay.he.gfx.model;

/**
 * Created by IntelliJ IDEA.
 * User: Alexander Tarasov
 * Date: 24.12.11
 * Time: 16:03
 */
public class Font {

    private final static int yStart = 30;
    public final static int FONT_SIZE = 8;

    private static String chars = "" + //
            "ABCDEFGHIJKLMNOPQRSTUVWXYZ      " + //
            "0123456789.,!?'\"-+=/\\%()<>:;     " + //
            "";

    public static void draw(String msg, Screen screen, int x, int y, int colors) {
        msg = msg.toUpperCase();
        for (int i = 0; i < msg.length(); i++) {
            int ix = chars.indexOf(msg.charAt(i));
            if (ix >= 0) {
                int xx = ix % 32;
                int yy = ix / 32;
                screen.render(x + i * FONT_SIZE, y, xx * FONT_SIZE, (yStart + yy) * FONT_SIZE, FONT_SIZE, FONT_SIZE, colors, 0);
            }
        }
    }
}
