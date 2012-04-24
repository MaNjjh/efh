package ru.znay.znay.he.gfx.gui;

import ru.znay.znay.he.gfx.model.Bitmap;
import ru.znay.znay.he.gfx.model.Screen;
import ru.znay.znay.he.gfx.sprite.SpriteCollector;
import ru.znay.znay.he.gfx.sprite.SpriteWrapper;
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

    public GuiSpeedIndicator(int x, int y, int col, SpriteCollector spriteCollector) {
        this.x = x;
        this.y = y;
        this.color = col;
        drawIcons(9, 3, spriteCollector);
        visible = true;

    }

    private void drawIcons(int xOff, int yOff, SpriteCollector spriteCollector) {
        slow = new Bitmap(16, 18);
        walk = new Bitmap(16, 18);
        run = new Bitmap(16, 18);
        sprint = new Bitmap(16, 18);

        xOff = xOff * Tile.HALF_SIZE;
        yOff = yOff * Tile.HALF_SIZE;

        spriteCollector.resetWrappers();
        spriteCollector.addWrapper(new SpriteWrapper(xOff, yOff, Tile.SIZE, Tile.SIZE, color));
        this.slow = spriteCollector.mergedWrappers("speed_slow", 1, 0, 0x01444444);

        spriteCollector.resetWrappers();
        spriteCollector.addWrapper(new SpriteWrapper(xOff + 16, yOff, Tile.SIZE, Tile.SIZE, color));
        this.walk = spriteCollector.mergedWrappers("speed_walk", 1, 0, 0x01444444);

        spriteCollector.resetWrappers();
        spriteCollector.addWrapper(new SpriteWrapper(xOff + 32, yOff, Tile.SIZE, Tile.SIZE, color));
        this.run = spriteCollector.mergedWrappers("speed_run", 1, 0, 0x01444444);

        spriteCollector.resetWrappers();
        spriteCollector.addWrapper(new SpriteWrapper(xOff + 48, yOff, Tile.SIZE, Tile.SIZE, color));
        this.sprint = spriteCollector.mergedWrappers("speed_sprint", 1, 0, 0x01444444);
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
