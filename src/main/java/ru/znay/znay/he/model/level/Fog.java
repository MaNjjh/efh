package ru.znay.znay.he.model.level;

import ru.znay.znay.he.gfx.helper.PaletteHelper;
import ru.znay.znay.he.gfx.model.Screen;
import ru.znay.znay.he.model.level.tile.Tile;

/**
 * Created by IntelliJ IDEA.
 * User: Александр Сергеевич
 * Date: 10.03.12
 * Time: 15:57
 * To change this template use File | Settings | File Templates.
 */
public class Fog {
    private boolean[] fog;
    private int width;
    private int height;

    public Fog(int width, int height) {
        this.width = width;
        this.height = height;
        fog = new boolean[width * height];
        for (int i = 0; i < width * height; i++) {
            fog[i] = true;
        }
    }

    public boolean getFog(int x, int y) {
        return x < 0 || y < 0 || x >= this.width || y >= this.height || fog[x + y * this.width];
    }

    private void copyFromMap(boolean[] src, int xOffs, int yOffs, int w, int h) {
        for (int j = 0; j < h; j++) {
            for (int i = 0; i < w; i++) {
                try {

                    fog[i + xOffs + (j + yOffs) * this.width] = !src[i + j * w];
                } catch (Exception e) {
                    //ignore
                }
            }
        }
    }

    void clearFog2(int xCenter, int yCenter, int radius) {
        CircleMap circleMap = new CircleMap(radius);
        circleMap.drawCircle();
        copyFromMap(circleMap.getCircle(), xCenter - radius, yCenter - radius, radius << 1, radius << 1);

    }


    public void clearFog1(int xCenter, int yCenter, int radius) {
        int x0 = Math.max(xCenter - radius, 0);
        int y0 = Math.max(yCenter - radius, 0);
        int x1 = Math.min(xCenter + radius, this.width - 1);
        int y1 = Math.min(yCenter + radius, this.height - 1);
        for (int j = y0; j < y1; j++) {
            for (int i = x0; i < x1; i++) {
                fog[i + j * this.width] = false;
            }
        }
    }

    public void clearFog(int x, int y) {

        if (x >= 0 && y >= 0 && x < this.width && y < this.height) {
            fog[x + y * this.width] = false;
        }
    }

    public void render(Screen screen, int x, int y) {

        if (getFog(x, y)) {
            boolean u = !getFog(x, y - 1);
            boolean d = !getFog(x, y + 1);
            boolean l = !getFog(x - 1, y);
            boolean r = !getFog(x + 1, y);
            boolean ul = !getFog(x - 1, y - 1);
            boolean dl = !getFog(x - 1, y + 1);
            boolean ur = !getFog(x + 1, y - 1);
            boolean dr = !getFog(x + 1, y + 1);

            int col = PaletteHelper.getColor(0, 0, 0, -1);
            int fogFill = PaletteHelper.getColor(0, 0, 0, 0);

            x <<= 4;
            y <<= 4;

            if (!u && !l) {
                if (!ul)
                    screen.render(x, y, 0, 0, Tile.HALF_SIZE, Tile.HALF_SIZE, fogFill, 0);
                else
                    screen.render(x, y, 5 * Tile.HALF_SIZE, 0 * Tile.HALF_SIZE, Tile.HALF_SIZE, Tile.HALF_SIZE, col, 3);
            } else
                screen.render(x, y, (l ? 9 : 8) * Tile.HALF_SIZE, (u ? 2 : 1) * Tile.HALF_SIZE, Tile.HALF_SIZE, Tile.HALF_SIZE, col, 3);

            if (!u && !r) {
                if (!ur)
                    screen.render(x + Tile.HALF_SIZE, y, 0, 0, Tile.HALF_SIZE, Tile.HALF_SIZE, fogFill, 0);
                else
                    screen.render(x + Tile.HALF_SIZE, y, 6 * Tile.HALF_SIZE, 0 * Tile.HALF_SIZE, Tile.HALF_SIZE, Tile.HALF_SIZE, col, 3);
            } else
                screen.render(x + Tile.HALF_SIZE, y, (r ? 7 : 8) * Tile.HALF_SIZE, (u ? 2 : 1) * Tile.HALF_SIZE, Tile.HALF_SIZE, Tile.HALF_SIZE, col, 3);


            if (!d && !l) {
                if (!dl)
                    screen.render(x, y + Tile.HALF_SIZE, 0, 0, Tile.HALF_SIZE, Tile.HALF_SIZE, fogFill, 0);
                else
                    screen.render(x, y + Tile.HALF_SIZE, 5 * Tile.HALF_SIZE, 1 * Tile.HALF_SIZE, Tile.HALF_SIZE, Tile.HALF_SIZE, col, 3);
            } else
                screen.render(x, y + Tile.HALF_SIZE, (l ? 9 : 8) * Tile.HALF_SIZE, (d ? 0 : 1) * Tile.HALF_SIZE, Tile.HALF_SIZE, Tile.HALF_SIZE, col, 3);
            if (!d && !r) {
                if (!dr)
                    screen.render(x + Tile.HALF_SIZE, y + Tile.HALF_SIZE, 0, 0, Tile.HALF_SIZE, Tile.HALF_SIZE, fogFill, 0);
                else
                    screen.render(x + Tile.HALF_SIZE, y + Tile.HALF_SIZE, 6 * Tile.HALF_SIZE, 1 * Tile.HALF_SIZE, Tile.HALF_SIZE, Tile.HALF_SIZE, col, 3);
            } else
                screen.render(x + Tile.HALF_SIZE, y + Tile.HALF_SIZE, (r ? 7 : 8) * Tile.HALF_SIZE, (d ? 0 : 1) * Tile.HALF_SIZE, Tile.HALF_SIZE, Tile.HALF_SIZE, col, 3);

        }
    }

}
