package ru.znay.znay.he.model.item;

import ru.znay.znay.he.gfx.model.Screen;
import ru.znay.znay.he.model.Entity;
import ru.znay.znay.he.model.Player;
import ru.znay.znay.he.model.level.Level;
import ru.znay.znay.he.model.level.tile.Tile;

/**
 * Created by IntelliJ IDEA.
 * User: Александр Сергеевич
 * Date: 07.04.12
 * Time: 12:19
 * To change this template use File | Settings | File Templates.
 */
public class Item implements ListItem {

    public boolean interactOn(Tile tile, Level level, int xt, int yt, Player player, int attackDir) {
        return false;
    }

    public void renderInventory(Screen screen, int x, int y) {
    }

    public boolean isDepleted() {
        return false;
    }

    public int getColor() {
        return 0;
    }

    public int getxSprite() {
        return 0;
    }

    public int getySprite() {
        return 0;
    }

    public int getScale() {
        return 1;
    }

    public int getMaxTime() {
        return 600;
    }

    public void onTake(ItemEntity itemEntity) {
    }

    public boolean interact(Player player, Entity entity, int attackDir) {
        return false;
    }

    public void renderIcon(Screen screen, int x, int y) {
    }


    public boolean canAttack() {
        return false;
    }

    public int getAttackDamageBonus(Entity e) {
        return 0;
    }

    public String getName() {
        return "";
    }

    public boolean matches(Item item) {
        return item.getClass() == getClass();
    }
}
