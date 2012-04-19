package ru.znay.znay.he.gfx.gui;

import ru.znay.znay.he.gfx.helper.BitmapHelper;
import ru.znay.znay.he.gfx.helper.PaletteHelper;
import ru.znay.znay.he.gfx.model.Bitmap;
import ru.znay.znay.he.gfx.model.Font;
import ru.znay.znay.he.gfx.model.Screen;
import ru.znay.znay.he.model.item.Item;
import ru.znay.znay.he.model.level.tile.Tile;

import java.util.LinkedList;
import java.util.List;

/**
 * Created by IntelliJ IDEA.
 * User: Денис Сергеевич
 * Date: 18.04.12
 * Time: 21:28
 * To change this template use File | Settings | File Templates.
 */
public class Inventory extends Panel {
    private int str = 0;
    private int sta = 0;
    private int def = 0;
    private int speed = 0;

    private TextPanel statPanel;

    private Item weapon = null;
    private Item armor = null;
    private Item boots = null;
    private Item apple = null;
    private Item berry = null;

    public Inventory(int x, int y) {
        this.x = x;
        this.y = y;

        List<String> messages = new LinkedList<String>();

        messages.add("Сила");
        messages.add("Выносливость");
        messages.add("Защита");
        messages.add("Скорость");

        str = 99;
        sta = 5;
        def = 33;
        speed = 10;

        statPanel = new TextPanel(messages, x-1, y + 143, 15);

        this.image = BitmapHelper.loadBitmapFromResources("/testinventory.png");
        visible = false;
        changed = true;
    }

    @Override
    public void render(Screen screen) {
        if (!visible) {
            return;
        }

        BitmapHelper.copy(this.image, 0, 0, x, y, this.image.getWidth(), this.image.getHeight(), screen.getViewPort(), 0xFFFF00FF);

        String val = "0%";
        if (speed < 3)
            val = "30%";

        if (speed < 10)
            val = "60%";

        if (speed < 40)
            val = "90%";

        if (speed < 60)
            val = "120%";

        int posX = 12 * Tile.HALF_SIZE;
        int posY = 4 * Tile.HALF_SIZE;

        Font.drawToBitmap(val, screen, posX, posY, PaletteHelper.getColor(5, 555, 555, 555), statPanel.getImage());

        posX = 14 * Tile.HALF_SIZE + ((str < 10) ? Tile.HALF_SIZE : 0);
        posY = Tile.HALF_SIZE;

        Font.drawToBitmap("" + str, screen, posX, posY, PaletteHelper.getColor(5, 555, 555, 555), statPanel.getImage());

        posX = 14 * Tile.HALF_SIZE + ((sta < 10) ? Tile.HALF_SIZE : 0);
        posY += Tile.HALF_SIZE;

        Font.drawToBitmap("" + sta, screen, posX, posY, PaletteHelper.getColor(5, 555, 555, 555), statPanel.getImage());

        posX = 14 * Tile.HALF_SIZE + ((def < 10) ? Tile.HALF_SIZE : 0);
        posY += Tile.HALF_SIZE;

        Font.drawToBitmap("" + def, screen, posX, posY, PaletteHelper.getColor(5, 555, 555, 555), statPanel.getImage());

        statPanel.render(screen);

        if (weapon != null)
            weapon.renderInventory(screen, x + 3, y + 3);

        if (armor != null)
            armor.renderInventory(screen, x + 8, y + 3);

        if (boots != null)
            boots.renderInventory(screen, x + 8, y + 25);

        if (apple != null)
            apple.renderInventory(screen, x + 3, y + 28);

        if (berry != null)
            berry.renderInventory(screen, x + 3, y + 32);

        posX =x+ 5 * Tile.HALF_SIZE + ((apple.count() < 10) ? Tile.HALF_SIZE : 0);
        posY =y+28;

        Font.drawToBitmap(""+apple.count(), screen, posX, posY, PaletteHelper.getColor(5, 555, 555, 555),screen.getViewPort());

        posX =x+ 5 * Tile.HALF_SIZE + ((berry.count() < 10) ? Tile.HALF_SIZE : 0);
        posY =y+32;

        Font.drawToBitmap(""+apple.count(), screen, posX, posY, PaletteHelper.getColor(5, 555, 555, 555),screen.getViewPort());
    }

    public void setStr(int str) {
        if (this.str == str) return;
        this.str = str;
        changed = true;
    }

    public void setSta(int sta) {
        if (this.sta == sta) return;
        this.sta = sta;
        changed = true;
    }

    public void setDef(int def) {
        if (this.def == def) return;
        this.def = def;
        changed = true;
    }

    public void setSpeed(int speed) {
        if (this.speed == speed) return;
        this.speed = speed;
        changed = true;
    }
}
