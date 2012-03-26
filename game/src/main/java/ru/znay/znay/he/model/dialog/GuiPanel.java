package ru.znay.znay.he.model.dialog;


import ru.znay.znay.he.cfg.Constants;
import ru.znay.znay.he.gfx.helper.BitmapHelper;
import ru.znay.znay.he.gfx.helper.PaletteHelper;
import ru.znay.znay.he.gfx.model.Bitmap;
import ru.znay.znay.he.gfx.model.Screen;
import ru.znay.znay.he.model.level.tile.Tile;

import java.awt.image.BufferedImage;

/**
 * Created by IntelliJ IDEA.
 * User: denus
 * Date: 26.03.12
 * Time: 12:23
 * To change this template use File | Settings | File Templates.
 */
public class GuiPanel {
    protected boolean Visible;
    protected int x = 0;
    protected int y = 0;
    protected int SizeX = 2;
    protected int SizeY = 2;
    private Bitmap image;
    protected boolean Changed = true;
    protected boolean closed = false;

    public GuiPanel(int posX, int posY, int sizeX, int sizeY) {
        x = posX;
        y = posY;
        this.SizeX = sizeX + 2;
        this.SizeY = sizeY + 2;

        image = new Bitmap(SizeX * Tile.HALF_SIZE, SizeY * Tile.HALF_SIZE);

        Changed = true;
        Visible = true;
    }

    public void Show() {
        if (Visible != true) {
            Visible = true;
            Changed = true;
        }
    }

    public void Hide() {
        if (Visible != false)
            Visible = false;
    }

    public boolean GetVisible() {
        return Visible;
    }

    public void SetVisible(boolean v) {
        if (Visible != v) {
            Visible = v;
            if (Visible)
                Changed = true;
        }
    }

    public void Put(int x, int y) {
        this.x = x;
        this.y = y;

        Changed = true;
    }

    public void SetSize(int newX, int newY) {
        this.x = (newX < 1) ? 1 : newX;
        this.y = (newY < 1) ? 1 : newY;
        Changed = true;
    }

    protected void SetChanged() {
        if (Changed == false)
            Changed = true;
    }

    public void close() {
        closed = true;
        Visible = false;
        Changed = true;
    }

    public void Paint(Screen screen) {
        if (Changed == false)
            return;

        PaintF(screen);
    }

    public void PaintF(Screen screen) {
        Bitmap temp = new Bitmap(SizeX * Tile.HALF_SIZE, SizeY * Tile.HALF_SIZE);
        int col = PaletteHelper.getColor(-1, 1, 5, 445);
        int tx = (SizeX - 1) * Tile.HALF_SIZE;
        int ty = (SizeY - 1) * Tile.HALF_SIZE;

        BitmapHelper.fill(temp,0xFFFFFF);
        //top left
        BitmapHelper.drawHalfTile(screen.getSprites(), 0, 0, 0, 13 * Tile.HALF_SIZE, col, 0, temp);
        //top right
        BitmapHelper.drawHalfTile(screen.getSprites(), tx, 0, 0, 13 * Tile.HALF_SIZE, col, 1, temp);
        //bottom left
        BitmapHelper.drawHalfTile(screen.getSprites(), 0, ty, 0, 13 * Tile.HALF_SIZE, col, 2, temp);
        //bottom right
        BitmapHelper.drawHalfTile(screen.getSprites(), tx, ty, 0, 13 * Tile.HALF_SIZE, col, 3, temp);

        for (int x = 1; x < SizeX - 1; x++) {
            //top
            BitmapHelper.drawHalfTile(screen.getSprites(), x * Tile.HALF_SIZE, 0, Tile.HALF_SIZE, 13 * Tile.HALF_SIZE, col, 0, temp);
            //bottom
            BitmapHelper.drawHalfTile(screen.getSprites(), x * Tile.HALF_SIZE, ty, Tile.HALF_SIZE, 13 * Tile.HALF_SIZE, col, 2, temp);
        }
        for (int y = 1; y < SizeY - 1; y++) {
            //left
            BitmapHelper.drawHalfTile(screen.getSprites(), 0, y * Tile.HALF_SIZE, 2 * Tile.HALF_SIZE, 13 * Tile.HALF_SIZE, col, 0, temp);
            //right
            BitmapHelper.drawHalfTile(screen.getSprites(), tx, y * Tile.HALF_SIZE, 2 * Tile.HALF_SIZE, 13 * Tile.HALF_SIZE, col, 1, temp);
        }

        for (int x = 1;x<SizeX-1;x++)
            for(int y = 1;y<SizeY-1;y++)
                BitmapHelper.drawHalfTile(screen.getSprites(), x * Tile.HALF_SIZE, y * Tile.HALF_SIZE, 3* Tile.HALF_SIZE, 13 * Tile.HALF_SIZE, col, 0, temp);

        this.image = null;
        this.image = temp;
        Changed = false;
    }

    public void render(Screen screen) {
        if (Visible == false)
            return;

        this.Paint(screen);

        BitmapHelper.copy(this.image, 0, 0, x, y, SizeX * Tile.HALF_SIZE, SizeY * Tile.HALF_SIZE, screen.getViewPort());
    }
}
