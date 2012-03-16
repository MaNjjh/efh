package ru.znay.znay.he.gfx.model;

import ru.znay.znay.he.gfx.helper.BitmapHelper;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.awt.image.DataBufferInt;

/**
 * Created by IntelliJ IDEA.
 * User: Alexander Tarasov
 * Date: 04.01.12
 * Time: 21:53
 */
public class Bitmap {
    private final int width;
    private final int height;
    private int[] pixels;
    private BufferedImage image;

    public Bitmap(int width, int height) {
        this.width = width;
        this.height = height;

        this.image = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
        this.pixels = ((DataBufferInt) image.getRaster().getDataBuffer()).getData();
    }

    public void drawPoint(int xOffs, int yOffs, int color) {
        BitmapHelper.drawPoint(xOffs, yOffs, color, this);
    }

    public void drawLine(int x0, int y0, int x1, int y1, int color) {
        BitmapHelper.drawLine(x0, y0, x1, y1, color, this);
    }

    public void drawTriangle(int x1, int y1, int x2, int y2, int x3, int y3, int color) {
        BitmapHelper.drawTriangle(x1, y1, x2, y2, x3, y3, color, this);
    }


    public void draw(Bitmap bitmap, int xOffs, int yOffs, int xo, int yo, int w, int h, int colors, int bits) {
        BitmapHelper.scaleDraw(bitmap, 1, xOffs, yOffs, xo, yo, w, h, colors, bits, this);
    }

    public void draw(Bitmap bitmap, int scale, int xOffs, int yOffs, int xo, int yo, int w, int h, int colors, int bits) {
        BitmapHelper.scaleDraw(bitmap, scale, xOffs, yOffs, xo, yo, w, h, colors, bits, this);
    }


    public static GraphicsConfiguration getDefaultConfiguration() {
        GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
        GraphicsDevice gd = ge.getDefaultScreenDevice();
        return gd.getDefaultConfiguration();
    }

    public BufferedImage tilt(BufferedImage image, double angle, GraphicsConfiguration gc) {
        int transparency = image.getColorModel().getTransparency();
        BufferedImage result = gc.createCompatibleImage(width, height, transparency);
        Graphics2D g = result.createGraphics();
        g.rotate(angle, width / 2, height / 2);
        g.drawRenderedImage(image, null);
        return result;
    }

    public void rotate(double theta) {

        this.image = tilt(image, Math.toRadians(theta), getDefaultConfiguration());

        this.pixels = ((DataBufferInt) image.getRaster().getDataBuffer()).getData();
    }

    public void draw(int scale, double angle, Bitmap bitmap, int xOffs, int yOffs, int xo, int yo, int w, int h, int colors, int bits) {
        Bitmap tempBitmap = new Bitmap(w, h);

        BitmapHelper.copy(bitmap, xo, yo, 0, 0, w, h, tempBitmap);

        tempBitmap.rotate(angle);

        BitmapHelper.scaleDraw(tempBitmap, scale, xOffs, yOffs, 0, 0, w, h, colors, bits, this);
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

    public int[] getPixels() {
        return pixels;
    }

    public BufferedImage getImage() {
        return image;
    }
}
