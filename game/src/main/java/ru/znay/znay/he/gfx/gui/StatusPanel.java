package ru.znay.znay.he.gfx.gui;

import ru.znay.znay.he.gfx.helper.BitmapHelper;
import ru.znay.znay.he.gfx.helper.PaletteHelper;
import ru.znay.znay.he.gfx.model.Bitmap;
import ru.znay.znay.he.gfx.model.Font;
import ru.znay.znay.he.gfx.model.Screen;
import ru.znay.znay.he.model.level.tile.Tile;

/**
 * Created by IntelliJ IDEA.
 * User: Денис Сергеевич
 * Date: 01.04.12
 * Time: 14:39
 * To change this template use File | Settings | File Templates.
 */
public class StatusPanel extends Panel {
    private final int negativeColor = PaletteHelper.getColor(-1, -1, -1, 500);
    private final int positiveColor = PaletteHelper.getColor(-1, -1, -1, 50);
    private final int neutralColor = PaletteHelper.getColor(-1, -1, -1, 555);

    private String text;
    private int val;
    private int pal;
    private int xOff;
    private int yOff;
    private int currentColor = neutralColor;
    private long time;

    public StatusPanel(int x, int y, int xOff, int yOff, int val, int col) {
        this.x = x;
        this.y = y;

        setText(val);
        this.sizeY = 2;
        pal = col;

        this.xOff = xOff * Tile.HALF_SIZE;
        this.yOff = yOff * Tile.HALF_SIZE;

        this.visible = true;
    }

    private void setText2(String t) {
        text = t;
        sizeX = 2 + 1 + text.length();
    }

    public void setText(int t) {
        if (t == val) return;

        time = System.currentTimeMillis() + 1000;

        if (t > val) {
            currentColor = positiveColor;
        } else {
            currentColor = negativeColor;
        }

        val = t;
        setText2("" + t);
        changed = true;
    }

    @Override
    public void tick() {
        if (currentColor == neutralColor) return;

        if (System.currentTimeMillis() > time)
            currentColor = neutralColor;

        changed = true;
    }

    @Override
    protected void paintF(Screen screen) {
        Bitmap temp = new Bitmap(sizeX * Tile.HALF_SIZE, sizeY * Tile.HALF_SIZE);

        BitmapHelper.fill(temp, 0xFF00FF);

        BitmapHelper.scaleDraw(screen.getSprites(), 1, 0, 0, xOff, yOff, Tile.HALF_SIZE << 1, Tile.HALF_SIZE << 1, pal, 0, temp);

        //BitmapHelper.drawAura(temp,0xFF00FF,0x000000);

        Font.drawToBitmap(text, screen, 17, 4, currentColor, temp);
        BitmapHelper.drawShadow(temp,0xFF00FF,0x000000);

        this.image = null;
        this.image = temp;
        changed = false;
    }
}
