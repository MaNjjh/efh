package ru.znay.znay.he.gfx.helper;

import ru.znay.znay.he.Game;
import ru.znay.znay.he.gfx.model.Bitmap;
import ru.znay.znay.he.gfx.model.Screen;
import ru.znay.znay.he.model.level.Level;
import ru.znay.znay.he.model.level.tile.Tile;

import java.util.LinkedList;
import java.util.List;

/**
 * Created by IntelliJ IDEA.
 * User: denus
 * Date: 27.03.12
 * Time: 15:57
 * To change this template use File | Settings | File Templates.
 */
public class SpriteManager {
    Bitmap bitmaps[] = new Bitmap[64];
    Bitmap sprites = null;

    public SpriteManager(Bitmap spr) {
        this.sprites = spr;
    }

    public Bitmap getSprite(StaticModel type) {
        if (bitmaps[type.ordinal()] == null)
            bitmaps[type.ordinal()] = create(type);
        return bitmaps[type.ordinal()];
    }

    private Bitmap create(StaticModel SM) {
        Bitmap image = null;
        switch (SM) {
            default:
            case Dummy:
                break;
            case Apple:
            case Apple2:
                final int scale = 2;
                final int Tx4 = 4 * Tile.HALF_SIZE;
                image = new Bitmap(Tx4 * scale, Tx4 * scale);

                BitmapHelper.fill(image, 0xFF00FF);

                int col = PaletteHelper.getColor(20, 40, 30, -1);
                BitmapHelper.scaleDraw(sprites, scale, 0, 0, 17 * Tile.HALF_SIZE, Tx4, Tx4, Tx4, col, 0, image);

                col = PaletteHelper.getColor(10, 10, 20, -1);
                BitmapHelper.scaleDraw(sprites, scale, 0, 0, 21 * Tile.HALF_SIZE, Tx4, Tx4, Tx4, col, 0, image);

                col = PaletteHelper.getColor(100, 210, 320, -1);
                BitmapHelper.scaleDraw(sprites, scale, 0, 0, 25 * Tile.HALF_SIZE, Tx4, Tx4, Tx4, col, 0, image);
                if (SM != StaticModel.Apple2) break;
                col = PaletteHelper.getColor(-1, -1, 510, -1);
                BitmapHelper.scaleDraw(sprites, scale, 0, 0, 17 * Tile.HALF_SIZE, 0, Tx4, Tx4, col, 0, image);
                break;
            case Fir:
                //добавить тут
                break;
        }

        return image;
    }
}
