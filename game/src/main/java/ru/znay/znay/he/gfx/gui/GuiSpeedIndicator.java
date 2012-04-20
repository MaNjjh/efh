package ru.znay.znay.he.gfx.gui;

import ru.znay.znay.he.gfx.helper.BitmapHelper;
import ru.znay.znay.he.gfx.model.Bitmap;
import ru.znay.znay.he.gfx.model.Screen;
import ru.znay.znay.he.model.level.tile.Tile;

/**
 * Created by IntelliJ IDEA.
 * User: Денис Сергеевич
 * Date: 08.04.12
 * Time: 15:12
 * To change this template use File | Settings | File Templates.
 */
public class GuiSpeedIndicator extends GuiPanel {
    private Bitmap slow;
    private Bitmap walk;
    private Bitmap run;
    private Bitmap sprint;
    private int color;
    private int speed;

    public GuiSpeedIndicator(int x, int y, int col, Screen screen) {
        this.x = x;
        this.y = y;
        this.color = col;
        drawIcons(9, 3, screen);
        visible = true;

    }

    private void drawIcons(int xOff, int yOff, Screen screen) {
        slow = new Bitmap(16, 18);
        walk = new Bitmap(16, 18);
        run = new Bitmap(16, 18);
        sprint = new Bitmap(16, 18);

        xOff = xOff * Tile.HALF_SIZE;
        yOff = yOff * Tile.HALF_SIZE;

        BitmapHelper.fill(slow, 0xFF00FF);
        BitmapHelper.fill(walk, 0xFF00FF);
        BitmapHelper.fill(run, 0xFF00FF);
        BitmapHelper.fill(sprint, 0xFF00FF);

        BitmapHelper.scaleDraw(screen.getSprites(), 1, 0, 0, xOff, yOff, Tile.HALF_SIZE << 1, Tile.HALF_SIZE << 1, color, 0, slow);
        BitmapHelper.scaleDraw(screen.getSprites(), 1, 0, 0, xOff + 16, yOff, Tile.HALF_SIZE << 1, Tile.HALF_SIZE << 1, color, 0, walk);
        BitmapHelper.scaleDraw(screen.getSprites(), 1, 0, 0, xOff + 32, yOff, Tile.HALF_SIZE << 1, Tile.HALF_SIZE << 1, color, 0, run);
        BitmapHelper.scaleDraw(screen.getSprites(), 1, 0, 0, xOff + 48, yOff, Tile.HALF_SIZE << 1, Tile.HALF_SIZE << 1, color, 0, sprint);

        BitmapHelper.drawAura(slow, 0xFF00FF, 0x444444);
        BitmapHelper.drawAura(walk, 0xFF00FF, 0x444444);
        BitmapHelper.drawAura(run, 0xFF00FF, 0x444444);
        BitmapHelper.drawAura(sprint, 0xFF00FF, 0x444444);

    }

    public void changeSpeed(int val) {
        if (speed == val) return;
        else speed = val;

        changed = true;
    }

    @Override
    protected void paintF(Screen screen) {
        if (speed < 3) {
            image = slow;
            return;
        }
        if (speed < 10) {
            image = walk;
            return;
        }
        if (speed < 40) {
            image = run;
            return;
        }
        if (speed < 60) {
            image = sprint;
            return;
        }
    }

}
