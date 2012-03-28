package ru.znay.znay.he.gfx.helper;

import ru.znay.znay.he.gfx.model.Bitmap;

import java.util.HashMap;

/**
 * Created by IntelliJ IDEA.
 * User: denus
 * Date: 28.03.12
 * Time: 20:29
 * To change this template use File | Settings | File Templates.
 */
public class AnimationManager {
    private HashMap<String, Bitmap> animations = new HashMap<String, Bitmap>();

    public AnimationManager(String filename) {

    }

    public Bitmap getAnimation(String Key) {
        return animations.get(Key);
    }
}
