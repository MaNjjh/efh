package ru.znay.znay.he.gfx.gui;

import ru.znay.znay.he.cfg.Constants;
import ru.znay.znay.he.gfx.helper.BitmapHelper;
import ru.znay.znay.he.gfx.helper.PaletteHelper;
import ru.znay.znay.he.gfx.model.Bitmap;
import ru.znay.znay.he.gfx.model.Font;
import ru.znay.znay.he.gfx.model.Screen;
import ru.znay.znay.he.model.level.tile.Tile;

import java.awt.font.FontRenderContext;

/**
 * Created by IntelliJ IDEA.
 * User: Денис Сергеевич
 * Date: 01.04.12
 * Time: 14:39
 * To change this template use File | Settings | File Templates.
 */
public class StatusPanel extends Panel {
    private String text;
    int pal;
    int xOff;
    int yOff;

    public StatusPanel(int x,int y,int xOff, int yOff, int val, int col) {
        this.x = x;
        this.y = y;

        setText(val);
        this.sizeY = 16;
        pal = col;

        this.xOff = xOff*Tile.HALF_SIZE;
        this.yOff = yOff*Tile.HALF_SIZE;
        this.visible = true;
    }

    private void setText2(String t) {
        text = t;
        sizeX = 16 + text.length() * Tile.HALF_SIZE;
    }

    public void setText(int t) {
        setText2("" + t);
        changed = true;
    }

    @Override
    protected void paintF(Screen screen) {
        Bitmap temp = new Bitmap(sizeX * Tile.HALF_SIZE, sizeY * Tile.HALF_SIZE);

        BitmapHelper.fill(temp,0xFF00FF);
        
        BitmapHelper.scaleDraw(screen.getSprites(), 1, xOff, yOff, 0, 0, Tile.HALF_SIZE << 1, Tile.HALF_SIZE << 1, pal, 0, temp);

        Font.drawToBitmap(text, screen, 18, 6, PaletteHelper.getColor(-1, -1, -1, 555), temp);

        this.image = null;
        this.image = temp;
        changed = false;
    }
}
