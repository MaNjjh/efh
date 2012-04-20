package ru.znay.znay.he.model.level;

import ru.znay.znay.he.gfx.helper.BitmapHelper;
import ru.znay.znay.he.gfx.model.Bitmap;
import ru.znay.znay.he.gfx.model.Screen;
import ru.znay.znay.he.model.Player;
import ru.znay.znay.he.model.level.tile.Tile;

/**
 * Created by IntelliJ IDEA.
 * User: Денис Сергеевич
 * Date: 20.04.12
 * Time: 18:22
 * To change this template use File | Settings | File Templates.
 */
public class NewFog {
    private boolean[] fog;
    private int h;
    private int w;
    private int c;
    private Bitmap black;

    public NewFog(Level level, int coefficient) {

        this.c = (coefficient < 1) ? 1 : coefficient;
        this.w = level.getWidth();
        this.h = level.getHeight();

        fog = new boolean[w * h];

        for (int i = 0; i < fog.length; i++)
            fog[i] = true;

        black = new Bitmap(Tile.HALF_SIZE<<1, Tile.HALF_SIZE<<1);

        BitmapHelper.fill(black, 0x000000);
    }

    public void render(Screen screen) {
        int localXOffset = screen.getXOffset()>>4 ;
        int localYOffset = screen.getYOffset()>>4 ;

        int xOff;
        int yOff;
        int dstW = screen.getViewPort().getWidth() >> 4;
        int dstH = screen.getViewPort().getHeight() >> 4;

        for (int x = 0; x < dstW; x++)
            for (int y = 0; y < dstH; y++) {
                xOff = x + localXOffset;
                yOff = y + localYOffset;
                if (fog[xOff + yOff * xOff]) {
                    BitmapHelper.drawNormal(black, x << 4, y << 4, screen.getViewPort(), 0xFFFFFF);
                }
            }

    }

    public void tick(Player player) {
        int r = player.getClearFogRadius();
        int x1 = player.getX() >> 4;
        int y1 = player.getY() >> 4;

        int x = 0;
        int y = r;
        int delta = (2 - (r << 1));
        int error;

        while (y >= 0) {
            //System.out.println((x1 + x) + " " +(y1 + y));
            fog[x1 + x + (x1 + x) * (y1 + y)] = false;
            fog[x1 + x + (x1 + x) * (y1 - y)] = false;
            fog[x1 - x + (x1 - x) * (y1 + y)] = false;
            fog[x1 - x + (x1 - x) * (y1 - y)] = false;
            error = 2 * (delta + y) - 1;
            if(delta < 0 && error <= 0) {
                ++x;
                delta += 2 * x + 1;
                continue;
            }
            error = 2 * (delta - x) - 1;
            if(delta > 0 && error > 0) {
                --y;
                delta += 1 - 2 * y;
                continue;
            }
            ++x;
            delta += 2 * (x - y);
            --y;
        }
    }

}
