package ru.znay.znay.he.gfx.model;

import ru.znay.znay.he.gfx.helper.BitmapHelper;
import ru.znay.znay.he.gfx.helper.PaletteHelper;
import ru.znay.znay.he.model.level.tile.Tile;

/**
 * Created by IntelliJ IDEA.
 * User: Alexander Tarasov
 * Date: 24.12.11
 * Time: 16:03
 */
public class Font {

    private final static int yStart = 28;
    public final static int FONT_SIZE = 8;

    public static final int greenColor = PaletteHelper.getColor(-1, 040, 040, 040);
    public static final int redColor = PaletteHelper.getColor(-1, 400, 400, 400);
    public static final int yellowColor = PaletteHelper.getColor(-1, 440, 440, 440);


    private static String chars = "" + //
            "ABCDEFGHIJKLMNOPQRSTUVWXYZ      " + //
            "0123456789.,!?'\"-+=/\\%()<>:;    " + //
            "АБВГДЕЖЗИЙКЛМНОПРСТУФХЦЧШЩЪЫЬЭЮЯ" + //
            "абвгдежзийклмнопрстуфхцчшщъыьэюя" + //
            "";

    public static void draw(String msg, Screen screen, int x, int y, int colors) {
        msg = msg.toUpperCase();
        for (int i = 0; i < msg.length(); i++) {
            int ix = chars.indexOf(msg.charAt(i));
            if (ix >= 0) {
                int xx = ix % 32;
                int yy = ix / 32;
                screen.render(x + i * (FONT_SIZE), y, xx * FONT_SIZE, (yStart + yy) * FONT_SIZE, FONT_SIZE, FONT_SIZE, colors, 0);
            }
        }
    }

    public static void drawToBitmap(String msg, Screen screen, int x, int y, int colors, Bitmap dst) {
        msg = msg.toUpperCase();
        for (int i = 0; i < msg.length(); i++) {
            int ix = chars.indexOf(msg.charAt(i));
            if (ix >= 0) {
                int xx = ix % 32;
                int yy = ix / 32;
                BitmapHelper.drawHalfTile(screen.getSprites(), x + i * (FONT_SIZE), y, xx * FONT_SIZE, (yStart + yy) * FONT_SIZE, colors, 0, dst);
            }
        }
    }

    public static void renderPanel(String msg, Screen screen, int xx, int yy, int colors) {
        int w = msg.length();
        int h = 1;

        int col = PaletteHelper.getColor(-1, 1, 5, 445);
        screen.render(xx - Tile.HALF_SIZE, yy - Tile.HALF_SIZE, 0, 13 * Tile.HALF_SIZE, Tile.HALF_SIZE, Tile.HALF_SIZE, col, 0);
        screen.render(xx + w * Tile.HALF_SIZE, yy - Tile.HALF_SIZE, 0, 13 * Tile.HALF_SIZE, Tile.HALF_SIZE, Tile.HALF_SIZE, col, 1);
        screen.render(xx - Tile.HALF_SIZE, yy + Tile.HALF_SIZE, 0, 13 * Tile.HALF_SIZE, Tile.HALF_SIZE, Tile.HALF_SIZE, col, 2);
        screen.render(xx + w * Tile.HALF_SIZE, yy + Tile.HALF_SIZE, 0, 13 * Tile.HALF_SIZE, Tile.HALF_SIZE, Tile.HALF_SIZE, col, 3);

        for (int x = 0; x < w; x++) {
            screen.render(xx + x * Tile.HALF_SIZE, yy - Tile.HALF_SIZE, 1 * Tile.HALF_SIZE, 13 * Tile.HALF_SIZE, Tile.HALF_SIZE, Tile.HALF_SIZE, col, 0);
            screen.render(xx + x * Tile.HALF_SIZE, yy + Tile.HALF_SIZE, 1 * Tile.HALF_SIZE, 13 * Tile.HALF_SIZE, Tile.HALF_SIZE, Tile.HALF_SIZE, col, 2);
        }
        for (int y = 0; y < h; y++) {
            screen.render(xx - Tile.HALF_SIZE, yy + y * Tile.HALF_SIZE, 2 * Tile.HALF_SIZE, 13 * Tile.HALF_SIZE, Tile.HALF_SIZE, Tile.HALF_SIZE, col, 0);
            screen.render(xx + w * Tile.HALF_SIZE, yy + y * Tile.HALF_SIZE, 2 * Tile.HALF_SIZE, 13 * Tile.HALF_SIZE, Tile.HALF_SIZE, Tile.HALF_SIZE, col, 1);
        }

        Font.draw(msg, screen, xx, yy, colors);
    }

    public static void renderFrame(Screen screen, String title, int x0, int y0, int x1, int y1) {
        int col = PaletteHelper.getColor(-1, 1, 50, 445);
        for (int y = y0; y <= y1; y++) {
            for (int x = x0; x <= x1; x++) {
                if (x == x0 && y == y0)
                    screen.render(x * Tile.HALF_SIZE, y * Tile.HALF_SIZE, 0, 13 * Tile.HALF_SIZE, Tile.HALF_SIZE, Tile.HALF_SIZE, col, 0);
                else if (x == x1 && y == y0)
                    screen.render(x * Tile.HALF_SIZE, y * Tile.HALF_SIZE, 0, 13 * Tile.HALF_SIZE, Tile.HALF_SIZE, Tile.HALF_SIZE, col, 1);
                else if (x == x0 && y == y1)
                    screen.render(x * Tile.HALF_SIZE, y * Tile.HALF_SIZE, 0, 13 * Tile.HALF_SIZE, Tile.HALF_SIZE, Tile.HALF_SIZE, col, 2);
                else if (x == x1 && y == y1)
                    screen.render(x * Tile.HALF_SIZE, y * Tile.HALF_SIZE, 0, 13 * Tile.HALF_SIZE, Tile.HALF_SIZE, Tile.HALF_SIZE, col, 3);
                else if (y == y0)
                    screen.render(x * Tile.HALF_SIZE, y * Tile.HALF_SIZE, 1 * Tile.HALF_SIZE, 13 * Tile.HALF_SIZE, Tile.HALF_SIZE, Tile.HALF_SIZE, col, 0);
                else if (y == y1)
                    screen.render(x * Tile.HALF_SIZE, y * Tile.HALF_SIZE, 1 * Tile.HALF_SIZE, 13 * Tile.HALF_SIZE, Tile.HALF_SIZE, Tile.HALF_SIZE, col, 2);
                else if (x == x0)
                    screen.render(x * Tile.HALF_SIZE, y * Tile.HALF_SIZE, 2 * Tile.HALF_SIZE, 13 * Tile.HALF_SIZE, Tile.HALF_SIZE, Tile.HALF_SIZE, col, 0);
                else if (x == x1)
                    screen.render(x * Tile.HALF_SIZE, y * Tile.HALF_SIZE, 2 * Tile.HALF_SIZE, 13 * Tile.HALF_SIZE, Tile.HALF_SIZE, Tile.HALF_SIZE, col, 1);
                else
                    screen.render(x * Tile.HALF_SIZE, y * Tile.HALF_SIZE, 2 * Tile.HALF_SIZE, 13 * Tile.HALF_SIZE, Tile.HALF_SIZE, Tile.HALF_SIZE, PaletteHelper.getColor(50, 50, 50, 50), 1);
            }
        }

        draw(title, screen, x0 * Tile.HALF_SIZE + Tile.HALF_SIZE, y0 * Tile.HALF_SIZE, PaletteHelper.getColor(50, 50, 50, 550));

    }
}
