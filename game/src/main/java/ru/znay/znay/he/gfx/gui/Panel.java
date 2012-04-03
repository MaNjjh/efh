package ru.znay.znay.he.gfx.gui;


import ru.znay.znay.he.gfx.helper.BitmapHelper;
import ru.znay.znay.he.gfx.helper.PaletteHelper;
import ru.znay.znay.he.gfx.model.Bitmap;
import ru.znay.znay.he.gfx.model.Screen;
import ru.znay.znay.he.model.level.tile.Tile;

/**
 * Created by IntelliJ IDEA.
 * User: Денис Сергеевич
 * Date: 26.03.12
 * Time: 12:23
 * To change this template use File | Settings | File Templates.
 */
public class Panel {
    protected boolean visible;
    protected int x = 0;
    protected int y = 0;
    protected int sizeX = 2;
    protected int sizeY = 2;
    protected Bitmap image;
    protected boolean changed = false;
    protected boolean closed = false;
    protected int panelColor;

    public Panel(int posX, int posY, int sizeX, int sizeY) {
        init(posX, posY, sizeX, sizeY, PaletteHelper.getColor(-1, 1, 5, 445));
    }

    public Panel(int posX, int posY, int sizeX, int sizeY, int panelColor) {
        init(posX, posY, sizeX, sizeY, panelColor);
    }

    public Panel(int posX, int posY) {
        init(posX, posY, 0, 0, PaletteHelper.getColor(-1, 1, 5, 445));
    }

    public Panel(int posX, int posY, int panelColor) {
        init(posX, posY, 0, 0, panelColor);
    }

    protected Panel() {
    }

    private void init(int posX, int posY, int sizeX, int sizeY, int panelColor) {
        this.x = posX;
        this.y = posY;
        this.sizeX = sizeX + 2;
        this.sizeY = sizeY + 2;

        this.changed = true;
        this.visible = true;
        this.panelColor = panelColor;

        image = new Bitmap(this.sizeX * Tile.HALF_SIZE, this.sizeY * Tile.HALF_SIZE);
    }

    public void show() {
        if (!visible) {
            visible = true;
            changed = true;
        }
    }

    public void hide() {
        if (visible) {
            visible = false;
        }
    }

    public boolean getVisible() {
        return visible;
    }

    public void setVisible(boolean v) {
        if (visible != v) {
            visible = v;
            if (visible) {
                changed = true;
            }
        }
    }

    public void put(int x, int y) {
        this.x = x;
        this.y = y;

        changed = true;
    }

    public void setSize(int newX, int newY) {
        this.x = (newX < 1) ? 1 : newX;
        this.y = (newY < 1) ? 1 : newY;
        changed = true;
    }

    protected void setChanged() {
        if (!changed) {
            changed = true;
        }
    }

    public void close() {
        closed = true;
        visible = false;
        changed = true;
    }

    public void paint(Screen screen) {
        if (!changed) {
            return;
        }

        paintF(screen);
    }

    protected void paintF(Screen screen) {
        Bitmap temp = new Bitmap(sizeX * Tile.HALF_SIZE, sizeY * Tile.HALF_SIZE);

        int tx = (sizeX - 1) * Tile.HALF_SIZE;
        int ty = (sizeY - 1) * Tile.HALF_SIZE;

        BitmapHelper.fill(temp, 0xFF00FF);
        //top left
        BitmapHelper.drawHalfTile(screen.getSprites(), 0, 0, 0, 13 * Tile.HALF_SIZE, panelColor, 0, temp);
        //top right
        BitmapHelper.drawHalfTile(screen.getSprites(), tx, 0, 0, 13 * Tile.HALF_SIZE, panelColor, 1, temp);
        //bottom left
        BitmapHelper.drawHalfTile(screen.getSprites(), 0, ty, 0, 13 * Tile.HALF_SIZE, panelColor, 2, temp);
        //bottom right
        BitmapHelper.drawHalfTile(screen.getSprites(), tx, ty, 0, 13 * Tile.HALF_SIZE, panelColor, 3, temp);

        for (int x = 1; x < sizeX - 1; x++) {
            //top
            BitmapHelper.drawHalfTile(screen.getSprites(), x * Tile.HALF_SIZE, 0, Tile.HALF_SIZE, 13 * Tile.HALF_SIZE, panelColor, 0, temp);
            //bottom
            BitmapHelper.drawHalfTile(screen.getSprites(), x * Tile.HALF_SIZE, ty, Tile.HALF_SIZE, 13 * Tile.HALF_SIZE, panelColor, 2, temp);
        }
        for (int y = 1; y < sizeY - 1; y++) {
            //left
            BitmapHelper.drawHalfTile(screen.getSprites(), 0, y * Tile.HALF_SIZE, 2 * Tile.HALF_SIZE, 13 * Tile.HALF_SIZE, panelColor, 0, temp);
            //right
            BitmapHelper.drawHalfTile(screen.getSprites(), tx, y * Tile.HALF_SIZE, 2 * Tile.HALF_SIZE, 13 * Tile.HALF_SIZE, panelColor, 1, temp);
        }

        for (int x = 1; x < sizeX - 1; x++) {
            for (int y = 1; y < sizeY - 1; y++) {
                BitmapHelper.drawHalfTile(screen.getSprites(), x * Tile.HALF_SIZE, y * Tile.HALF_SIZE, 3 * Tile.HALF_SIZE, 13 * Tile.HALF_SIZE, panelColor, 0, temp);
            }
        }

        this.image = null;
        this.image = temp;
        changed = false;
    }

    public void render(Screen screen) {
        if (!visible) {
            return;
        }

        this.paint(screen);

        BitmapHelper.copy(this.image, 0, 0, x, y, sizeX * Tile.HALF_SIZE, sizeY * Tile.HALF_SIZE, screen.getViewPort(), 0xFF00FF);
    }

    public void tick() {
        //inherited
    }

    public boolean isClosed() {
        return closed;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }
}
