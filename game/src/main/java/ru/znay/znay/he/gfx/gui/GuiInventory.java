package ru.znay.znay.he.gfx.gui;

import ru.znay.znay.he.InputHandler;
import ru.znay.znay.he.gfx.helper.PaletteHelper;
import ru.znay.znay.he.gfx.model.Font;
import ru.znay.znay.he.gfx.model.Screen;
import ru.znay.znay.he.model.Player;
import ru.znay.znay.he.model.item.Inventory;
import ru.znay.znay.he.model.item.Item;
import ru.znay.znay.he.model.item.equipment.Equipment;
import ru.znay.znay.he.model.item.equipment.EquipmentItem;
import ru.znay.znay.he.model.item.resource.Resource;
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
    private int speed = 0;

    GuiPanel[] panels = new GuiPanel[7];

    private Item weapon = null;
    private Item armor = null;
    private Item boots = null;
    private ResourceItem apple = null;
    private ResourceItem berry = null;

    private Player player = null;

    public GuiInventory(int x, int y, Player player) {
        this.x = x;
        this.y = y;
        this.player = player;

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

        for (GuiPanel panel : panels) {
            panel.render(screen);
        }

        int posX = panels[1].getX() + 12 * Tile.HALF_SIZE + ((speed < 100) ? Tile.HALF_SIZE : 0);
        int posY = panels[1].getY() + 4 * Tile.HALF_SIZE;

        Font.drawToBitmap("" + speed + "%", screen, posX, posY, GuiManager.FONT_COLOR, screen.getViewPort());

        posX = panels[1].getX() + 14 * Tile.HALF_SIZE + ((str < 10) ? Tile.HALF_SIZE : 0);
        posY = panels[1].getY() + Tile.HALF_SIZE;

        Font.drawToBitmap("" + str, screen, posX, posY, GuiManager.FONT_COLOR, screen.getViewPort());

        posX = panels[1].getX() + 14 * Tile.HALF_SIZE + ((sta < 10) ? Tile.HALF_SIZE : 0);
        posY += Tile.HALF_SIZE;

        Font.drawToBitmap("" + sta, screen, posX, posY, GuiManager.FONT_COLOR, screen.getViewPort());

        posX = panels[1].getX() + 14 * Tile.HALF_SIZE + ((def < 10) ? Tile.HALF_SIZE : 0);
        posY += Tile.HALF_SIZE;

        Font.drawToBitmap("" + def, screen, posX, posY, GuiManager.FONT_COLOR, screen.getViewPort());

        Inventory inventory = player.getInventory();

        EquipmentItem item;

        if ((item = inventory.findEquipmentByType(Equipment.EQUIP_TYPE.WEAPON)) != null) {
            item.renderInventory(screen, x + Tile.SIZE, y + Tile.SIZE);
        }

        if ((item = inventory.findEquipmentByType(Equipment.EQUIP_TYPE.ARMOR)) != null) {
            item.renderInventory(screen, x + 5 * Tile.SIZE, y + Tile.SIZE);
        }

        if ((item = inventory.findEquipmentByType(Equipment.EQUIP_TYPE.SHOES)) != null) {
            item.renderInventory(screen, x + 5 * Tile.SIZE, y + 77);
        }

        ResourceItem resourceItem;

        if ((resourceItem = inventory.findResource(Resource.getResourceByName("apple"))) != null) {
            resourceItem.renderInventory(screen, x + 10, y + 77, x + 4 * Tile.HALF_SIZE, y + 10 * Tile.HALF_SIZE);
        }

        if ((resourceItem = inventory.findResource(Resource.getResourceByName("berry"))) != null) {
            resourceItem.renderInventory(screen, x + 10, y + 87, x + 4 * Tile.HALF_SIZE, y + 13 * Tile.HALF_SIZE);
        }
    }

    @Override
    public void tick() {
        if (InputHandler.getInstance().inventory.clicked) {
            visible = !visible;
        }
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
