package ru.znay.znay.he.gfx.helper;

import ru.znay.znay.he.gfx.model.Bitmap;
import ru.znay.znay.he.model.level.tile.Tile;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;

/**
 * Created by IntelliJ IDEA.
 * User: Alexander Tarasov
 * Date: 04.01.12
 * Time: 21:57
 */
public class BitmapHelper {
    public final static int BIT_MIRROR_X = 0x01;
    public final static int BIT_MIRROR_Y = 0x02;

    public static void fill(Bitmap bitmap, int color) {
        for (int i = 0; i < bitmap.getHeight() * bitmap.getWidth(); i++) {
            bitmap.getPixels()[i] = color;
        }
    }

    public static Bitmap loadTextureFromResources(String fileName) {
        try {
            BufferedImage bufferedImage = ImageIO.read(BitmapHelper.class.getResource(fileName));

            int width = bufferedImage.getWidth();
            int height = bufferedImage.getHeight();

            Bitmap result = new Bitmap(width, height);
            bufferedImage.getRGB(0, 0, width, height, result.getPixels(), 0, width);
            for (int i = 0; i < result.getPixels().length; i++) {
                result.getPixels()[i] = (result.getPixels()[i] & 0xff) / 64;
            }
            return result;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public static Bitmap loadBitmapFromResources(String fileName) {
        try {
            BufferedImage bufferedImage = ImageIO.read(BitmapHelper.class.getResource(fileName));

            int width = bufferedImage.getWidth();
            int height = bufferedImage.getHeight();

            Bitmap result = new Bitmap(width, height);
            bufferedImage.getRGB(0, 0, width, height, result.getPixels(), 0, width);

            return result;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public static void copy(Bitmap src, int xOffs, int yOffs, int xo, int yo, int w, int h, Bitmap dst) {
        xo = Math.max(0, Math.min(xo, dst.getWidth() - 1));
        yo = Math.max(0, Math.min(yo, dst.getWidth() - 1));
        w = Math.min(dst.getWidth() - 1, xo + w);
        h = Math.min(dst.getHeight() - 1, yo + h);
        xOffs = Math.min(src.getWidth() - w, xOffs - xo);
        yOffs = Math.min(src.getHeight() - h, yOffs - yo);
        for (int j = yo; j < h; j++) {
            for (int i = xo; i < w; i++) {
                dst.getPixels()[i + j * dst.getWidth()] = src.getPixels()[xOffs + i + (yOffs + j) * src.getWidth()];
            }
        }

    }

    public static void drawPoint(int xOffs, int yOffs, int color, Bitmap dst) {
        if (xOffs > 0 && xOffs < dst.getWidth() - 1 && yOffs > 0 && yOffs < dst.getHeight() - 1) {
            dst.getPixels()[xOffs + yOffs * dst.getWidth()] = color;
        }
    }

    public static void drawLine(int x0, int y0, int x1, int y1, int color, Bitmap dst) {
        Graphics2D graphics2D = dst.getImage().createGraphics();
        graphics2D.setColor(new Color(color));
        graphics2D.drawLine(x0, y0, x1, y1);
    }

    public static void drawLine1(int x0, int y0, int x1, int y1, int color, Bitmap dst) {

        int dx, dy, dx2, dy2, x_inc, y_inc, error, index;

        int vbStart = x0 + y0 * dst.getWidth();

        dx = x1 - x0;
        dy = y1 - y0;

        if (dx >= 0) {
            x_inc = 1;

        } else {
            x_inc = -1;
            dx = -dx;
        }

        if (dy >= 0) {
            y_inc = dst.getWidth();
        } else {
            y_inc = -dst.getWidth();
            dy = -dy;
        }

        dx2 = dx << 1;
        dy2 = dy << 1;

        if (dx > dy) {

            error = dy2 - dx;

            for (index = 0; index <= dx; index++) {
                if (vbStart < dst.getPixels().length && vbStart >= 0) {
                    dst.getPixels()[vbStart] = color;
                }

                if (error >= 0) {
                    error -= dx2;
                    vbStart += y_inc;
                }
                error += dy2;
                vbStart += x_inc;
            }

        } else {

            error = dx2 - dy;

            for (index = 0; index <= dy; index++) {

                if (vbStart < dst.getPixels().length && vbStart >= 0) {
                    dst.getPixels()[vbStart] = color;
                }

                if (error >= 0) {
                    error -= dy2;
                    vbStart += x_inc;
                }
                error += dx2;
                vbStart += y_inc;

            }
        }
    }

    public static void drawTriangle(int x1, int y1, int x2, int y2, int x3, int y3, int color, Bitmap dst) {
        Polygon polygon = new Polygon(new int[]{x1, x2, x3}, new int[]{y1, y2, y3}, 3);
        Graphics2D graphics2D = dst.getImage().createGraphics();
        graphics2D.setColor(new Color(color));
        graphics2D.fillPolygon(polygon);
    }

    public static void drawHalfTile(Bitmap src,int xOffs, int yOffs, int xo, int yo,int colors, int bits, Bitmap dst)
    {
        BitmapHelper.scaleDraw(src,1,xOffs,yOffs,xo,yo, Tile.HALF_SIZE,Tile.HALF_SIZE,colors,bits,dst);
    }

    public static void scaleDraw(Bitmap src, int scale, int xOffs, int yOffs, int xo, int yo, int w, int h, int colors, int bits, Bitmap dst) {
        boolean mirrorX = (bits & BIT_MIRROR_X) > 0;
        boolean mirrorY = (bits & BIT_MIRROR_Y) > 0;

        for (int y = 0; y < h * scale; y++) {
            int yPix = y + yOffs;
            if (yPix < 0 || yPix >= dst.getHeight()) continue;
            int ys = y;
            if (mirrorY) ys = (h*scale - 1) - y;

            for (int x = 0; x < w * scale; x++) {
                int xPix = x + xOffs;
                if (xPix < 0 || xPix >= dst.getWidth()) continue;

                int xs = x;
                if (mirrorX) xs = (w*scale - 1) - x;

                int color = (colors >> (src.getPixels()[(xs / scale + xo) + (ys / scale + yo) * src.getWidth()] * 8)) & 255;
                if (color < 255) {
                    dst.getPixels()[xPix + yPix * dst.getWidth()] = color;
                }
            }
        }
    }

    public static void smoothRGBBitmap(Bitmap bitmap, int count) {
        for (int i = 0; i < count; i++) {
            for (int y = 1; y < bitmap.getHeight() - 1; y++) {
                for (int x = 1; x < bitmap.getWidth() - 1; x++) {

                    int colorC = (bitmap.getPixels()[x + y * bitmap.getWidth()]);
                    int colorL = (bitmap.getPixels()[(x - 1) + y * bitmap.getWidth()]);
                    int colorR = (bitmap.getPixels()[(x + 1) + y * bitmap.getWidth()]);
                    int colorU = (bitmap.getPixels()[x + (y - 1) * bitmap.getWidth()]);
                    int colorD = (bitmap.getPixels()[x + (y + 1) * bitmap.getWidth()]);
                    int colorLU = (bitmap.getPixels()[(x - 1) + (y - 1) * bitmap.getWidth()]);
                    int colorRU = (bitmap.getPixels()[(x + 1) + (y - 1) * bitmap.getWidth()]);
                    int colorLD = (bitmap.getPixels()[(x - 1) + (y + 1) * bitmap.getWidth()]);
                    int colorRD = (bitmap.getPixels()[(x + 1) + (y + 1) * bitmap.getWidth()]);

                    int midRed = (
                            (colorC & 0xff) +
                                    (colorD & 0xff) +
                                    (colorL & 0xff) +
                                    (colorR & 0xff) +
                                    (colorU & 0xff) +
                                    (colorLD & 0xff) +
                                    (colorLU & 0xff) +
                                    (colorRD & 0xff) +
                                    (colorRU & 0xff)
                    ) / 9;

                    int midGreen = (
                            ((colorC >> 8) & 0xff) +
                                    ((colorD >> 8) & 0xff) +
                                    ((colorL >> 8) & 0xff) +
                                    ((colorR >> 8) & 0xff) +
                                    ((colorU >> 8) & 0xff) +
                                    ((colorLD >> 8) & 0xff) +
                                    ((colorLU >> 8) & 0xff) +
                                    ((colorRD >> 8) & 0xff) +
                                    ((colorRU >> 8) & 0xff)
                    ) / 9;

                    int midBlue = (
                            ((colorD >> 16) & 0xff) +
                                    ((colorL >> 16) & 0xff) +
                                    ((colorR >> 16) & 0xff) +
                                    ((colorU >> 16) & 0xff) +
                                    ((colorLD >> 16) & 0xff) +
                                    ((colorLU >> 16) & 0xff) +
                                    ((colorRD >> 16) & 0xff) +
                                    ((colorRU >> 16) & 0xff)
                    ) / 9;

                    bitmap.getPixels()[x + y * bitmap.getWidth()] = midBlue << 16 | midGreen << 8 | midRed;
                }
            }
        }

    }


}
