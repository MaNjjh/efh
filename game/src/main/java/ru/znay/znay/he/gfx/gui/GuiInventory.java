package ru.znay.znay.he.gfx.gui;

import ru.znay.znay.he.InputHandler;
import ru.znay.znay.he.gfx.helper.PaletteHelper;
import ru.znay.znay.he.gfx.model.Font;
import ru.znay.znay.he.gfx.model.Screen;
import ru.znay.znay.he.model.item.Item;
import ru.znay.znay.he.model.item.resource.ResourceItem;
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
public class GuiInventory extends GuiPanel {
    private int str = 0;
    private int sta = 0;
    private int def = 0;
    private int speed = 55;

    GuiPanel[] panels = new GuiPanel[7];

    private Item weapon = null;
    private Item armor = null;
    private Item boots = null;
    private ResourceItem apple = null;
    private ResourceItem berry = null;

    public GuiInventory(int x, int y) {
        this.x = x;
        this.y = y;

        List<String> messages = new LinkedList<String>();

        messages.add("Сила");
        messages.add("Выносливость");
        messages.add("Защита");
        messages.add("Скорость");

        int x1 = x - 1;

        panels[0] = new GuiPanel(x1, y, 14, 15);//main inventory
        panels[1] = new GuiTextPanel(messages, x - 1, y + 135, 15);//stat panel
        panels[2] = new GuiPanel(x1 + Tile.HALF_SIZE, y + Tile.HALF_SIZE, 5, 6, PaletteHelper.getColor(-1, 1, 111, 445));//weapon
        panels[3] = new GuiPanel(x1 + 8 * Tile.HALF_SIZE, y + Tile.HALF_SIZE, 5, 6, PaletteHelper.getColor(-1, 1, 111, 445));//armor
        panels[4] = new GuiPanel(x1 + 8 * Tile.HALF_SIZE, y + 9 * Tile.HALF_SIZE, 5, 5, PaletteHelper.getColor(-1, 1, 111, 445));//boots
        panels[5] = new GuiPanel(x1 + Tile.HALF_SIZE, y + 9 * Tile.HALF_SIZE, 1, 1, PaletteHelper.getColor(-1, 1, 111, 445));//apple
        panels[6] = new GuiPanel(x1 + Tile.HALF_SIZE, y + 12 * Tile.HALF_SIZE, 1, 1, PaletteHelper.getColor(-1, 1, 111, 445));//berry

        visible = false;
        changed = true;


    }

    @Override
    public void render(Screen screen) {
        if (!visible) {
            return;
        }

        for (GuiPanel panel : panels)
            panel.render(screen);

        String val = "0%";
        if (speed < 3)
            val = "30%";
        else if (speed < 10)
            val = "60%";
        else if (speed < 40)
            val = "90%";
        else if (speed < 60)
            val = "120%";

        int posX = panels[1].getX() + 12 * Tile.HALF_SIZE + ((speed < 60) ? Tile.HALF_SIZE : 0);
        int posY = panels[1].getY() + 4 * Tile.HALF_SIZE;

        Font.drawToBitmap(val, screen, posX, posY, PaletteHelper.getColor(5, 555, 555, 555), screen.getViewPort());

        posX = panels[1].getX() + 14 * Tile.HALF_SIZE + ((str < 10) ? Tile.HALF_SIZE : 0);
        posY = panels[1].getY() + Tile.HALF_SIZE;

        Font.drawToBitmap("" + str, screen, posX, posY, PaletteHelper.getColor(5, 555, 555, 555), screen.getViewPort());

        posX = panels[1].getX() + 14 * Tile.HALF_SIZE + ((sta < 10) ? Tile.HALF_SIZE : 0);
        posY += Tile.HALF_SIZE;

        Font.drawToBitmap("" + sta, screen, posX, posY, PaletteHelper.getColor(5, 555, 555, 555), screen.getViewPort());

        posX = panels[1].getX() + 14 * Tile.HALF_SIZE + ((def < 10) ? Tile.HALF_SIZE : 0);
        posY += Tile.HALF_SIZE;

        Font.drawToBitmap("" + def, screen, posX, posY, PaletteHelper.getColor(5, 555, 555, 555), screen.getViewPort());


        if (weapon != null)
            weapon.renderInventory(screen, x + 3, y + 3);

        if (armor != null)
            armor.renderInventory(screen, x + 8, y + 3);

        if (boots != null)
            boots.renderInventory(screen, x + 8, y + 25);

        if (apple != null) {
            apple.renderInventory(screen, x + 10, y + 77);
            posX = x + 4 * Tile.HALF_SIZE + ((11 < 10) ? Tile.HALF_SIZE : 0);
            posY = y + 10 * Tile.HALF_SIZE;

            Font.drawToBitmap("" + apple.getCount(), screen, posX, posY, PaletteHelper.getColor(5, 555, 555, 555), screen.getViewPort());
        }

        if (berry != null) {
            berry.renderInventory(screen, x + 3, y + 32);
            posX = x + 4 * Tile.HALF_SIZE + ((11 < 10) ? Tile.HALF_SIZE : 0);
            posY = y + 13 * Tile.HALF_SIZE;

            Font.drawToBitmap("" + apple.getCount(), screen, posX, posY, PaletteHelper.getColor(5, 555, 555, 555), screen.getViewPort());
        }
    }

    @Override
    public void tick() {
        if (InputHandler.getInstance().inventory.clicked)
            visible = !visible;
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

    public void setWeapon(Item weapon) {
        this.weapon = weapon;
    }

    public void setArmor(Item armor) {
        this.armor = armor;
    }

    public void setBoots(Item boots) {
        this.boots = boots;
    }

    public void setApple(ResourceItem apple) {
        this.apple = apple;
    }

    public void setBerry(ResourceItem berry) {
        this.berry = berry;
    }
}
