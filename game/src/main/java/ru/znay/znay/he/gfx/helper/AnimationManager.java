package ru.znay.znay.he.gfx.helper;

import ru.znay.znay.he.gfx.model.Bitmap;

import java.awt.*;
import java.util.HashMap;

/**
 * Created by IntelliJ IDEA.
 * User: Денис Сергеевич
 * Date: 28.03.12
 * Time: 20:29
 * To change this template use File | Settings | File Templates.
 */
public class AnimationManager {
    private HashMap<String, Bitmap> animations = new HashMap<String, Bitmap>();
    Bitmap sprite;

    public AnimationManager(Bitmap sprite) {
        this.sprite = sprite;

        animations.put("Hero", makeCharacterSprite());
    }

    public Bitmap getAnimation(String Key) {
        return animations.get(Key);
    }

    private Bitmap makeCharacterSprite() {
        Bitmap temp = new Bitmap(32, 48);
        BitmapHelper.fill(temp,0xFF00FF);
        Point wh = new Point(16, 16);
        int col = PaletteHelper.getColor(-1, 100, 522, 555);

        BitmapHelper.drawAnimation(sprite, Orientation.get(Orientation.UP_STAY), new Point(12, 7), wh, col, Orientation.MIRROR_NONE, temp);

        BitmapHelper.drawAnimation(sprite, Orientation.get(Orientation.UP_MOVE_1), new Point(12, 5), wh, col, Orientation.MIRROR_NONE, temp);

        BitmapHelper.drawAnimation(sprite, Orientation.get(Orientation.UP_MOVE_2), new Point(12, 5), wh, col, Orientation.MIRROR_X, temp);

        BitmapHelper.drawAnimation(sprite, Orientation.get(Orientation.LEFT_STAY), new Point(14, 5), wh, col, Orientation.MIRROR_X, temp);

        BitmapHelper.drawAnimation(sprite, Orientation.get(Orientation.LEFT_MOVE_1), new Point(16, 5), wh, col, Orientation.MIRROR_X, temp);

        BitmapHelper.drawAnimation(sprite, Orientation.get(Orientation.LEFT_MOVE_2), new Point(16, 7), wh, col, Orientation.MIRROR_X, temp);

        BitmapHelper.drawAnimation(sprite, Orientation.get(Orientation.RIGHT_STAY), new Point(14, 5), wh, col, Orientation.MIRROR_NONE, temp);

        BitmapHelper.drawAnimation(sprite, Orientation.get(Orientation.RIGHT_MOVE_1), new Point(16, 5), wh, col, Orientation.MIRROR_NONE, temp);

        BitmapHelper.drawAnimation(sprite, Orientation.get(Orientation.RIGHT_MOVE_2), new Point(16, 7), wh, col, Orientation.MIRROR_NONE, temp);

        BitmapHelper.drawAnimation(sprite, Orientation.get(Orientation.DOWN_STAY), new Point(10, 7), wh, col, Orientation.MIRROR_NONE, temp);

        BitmapHelper.drawAnimation(sprite, Orientation.get(Orientation.DOWN_MOVE_1), new Point(10, 5), wh, col, Orientation.MIRROR_NONE, temp);

        BitmapHelper.drawAnimation(sprite, Orientation.get(Orientation.DOWN_MOVE_2), new Point(10, 5), wh, col, Orientation.MIRROR_X, temp);
        return temp;
    }
}
