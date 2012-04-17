package ru.znay.znay.he.model.item.resource;

import ru.znay.znay.he.gfx.helper.PaletteHelper;
import ru.znay.znay.he.gfx.model.Font;
import ru.znay.znay.he.gfx.model.Screen;
import ru.znay.znay.he.model.Player;
import ru.znay.znay.he.model.item.Item;
import ru.znay.znay.he.model.level.Level;
import ru.znay.znay.he.model.level.tile.Tile;

/**
 * ========================================
 * ItCorp v. 1.0 class library
 * ========================================
 * <p/>
 * http://www.it.ru
 * <p/>
 * (C) Copyright 1990-2011, by ItCorp.
 * <p/>
 * --------------------
 * <Filename>.java
 * --------------------
 * Created on 17.04.12
 * <p/>
 * $Revision: $
 * $Author: ASTarasov $
 * $Source:
 * $Id: $
 * <p/>
 * 17.04.12: Original version (WHo)
 * 15:25: Time
 */
public class ResourceItem extends Item {
    private Resource resource;
    private int count = 1;

    public ResourceItem(Resource resource) {
        this.resource = resource;
    }

    public ResourceItem(Resource resource, int count) {
        this.resource = resource;
        this.count = count;
    }


    public int getColor() {
        return resource.getColor();
    }

    @Override
    public int getxSprite() {
        return resource.getxSprite();
    }

    @Override
    public int getySprite() {
        return resource.getySprite();
    }

    public void renderIcon(Screen screen, int x, int y) {
        screen.render(x, y, resource.getxSprite() * Tile.HALF_SIZE, resource.getySprite() * Tile.HALF_SIZE, resource.getColor(), 0);
    }

    public void renderInventory(Screen screen, int x, int y) {
        screen.render(x, y, resource.getxSprite() * Tile.HALF_SIZE, resource.getySprite() * Tile.HALF_SIZE, resource.getColor(), 0);
        Font.draw(resource.getName(), screen, x + 32, y, PaletteHelper.getColor(-1, 555, 555, 555));
        int cc = count;
        if (cc > 999) cc = 999;
        Font.draw("" + cc, screen, x + 8, y, PaletteHelper.getColor(-1, 444, 444, 444));
    }

    public String getName() {
        return resource.getName();
    }

    public void onTake(ItemEntity itemEntity) {
    }

    public boolean interactOn(Tile tile, Level level, int xt, int yt, Player player, int attackDir) {
        if (resource.interactOn(tile, level, xt, yt, player, attackDir)) {
            count--;
            return true;
        }
        return false;
    }

    public boolean isDepleted() {
        return count <= 0;
    }

    public Resource getResource() {
        return resource;
    }

    public int getCount() {
        return count;
    }

    public void addCount(int count) {
        this.count += count;
    }
}
