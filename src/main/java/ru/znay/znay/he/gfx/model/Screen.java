package ru.znay.znay.he.gfx.model;

import ru.znay.znay.he.gfx.helper.BitmapHelper;

/**
 * Created by IntelliJ IDEA.
 * User: Александр Сергеевич
 * Date: 03.03.12
 * Time: 19:20
 * To change this template use File | Settings | File Templates.
 */
public class Screen {
    private Bitmap viewPort;
    private Bitmap sprites;
    private int xOffset;
    private int yOffset;


    public Screen(int width, int height, String spritesFileName) {
        this.viewPort = new Bitmap(width, height);
        this.sprites = BitmapHelper.loadBitmapFromResources(spritesFileName);
    }

    public void setOffset(int xOffset, int yOffset) {
        this.xOffset = xOffset;
        this.yOffset = yOffset;
    }

    public void render(int xOffs, int yOffs, int xo, int yo, int w, int h, int colors, int bits) {
        this.viewPort.draw(this.sprites, xOffs - this.xOffset, yOffs - this.yOffset, xo, yo, w, h, colors, bits);
    }

    public void render(int scale, int xOffs, int yOffs, int xo, int yo, int w, int h, int colors, int bits) {
        this.viewPort.draw(this.sprites, scale, xOffs - this.xOffset, yOffs - this.yOffset, xo, yo, w, h, colors, bits);
    }

    public Bitmap getViewPort() {
        return viewPort;
    }

    public void setViewPort(Bitmap viewPort) {
        this.viewPort = viewPort;
    }

    public Bitmap getSprites() {
        return sprites;
    }

    public void setSprites(Bitmap sprites) {
        this.sprites = sprites;
    }

    public int getXOffset() {
        return xOffset;
    }

    public int getYOffset() {
        return yOffset;
    }
}
