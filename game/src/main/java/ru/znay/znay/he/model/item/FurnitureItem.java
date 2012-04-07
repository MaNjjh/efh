package ru.znay.znay.he.model.item;

import ru.znay.znay.he.model.Player;
import ru.znay.znay.he.model.item.furniture.Furniture;
import ru.znay.znay.he.model.level.Level;
import ru.znay.znay.he.model.level.tile.Tile;

/**
 * Created by IntelliJ IDEA.
 * User: Александр Сергеевич
 * Date: 07.04.12
 * Time: 13:32
 * To change this template use File | Settings | File Templates.
 */
public class FurnitureItem extends Item {
    private Furniture furniture;
    private boolean placed = false;

    public FurnitureItem(Furniture furniture) {
        this.furniture = furniture;
    }

    public boolean interactOn(Tile tile, Level level, int xt, int yt, Player player, int attackDir) {
        if (tile.mayPass(level, xt, yt, furniture)) {
            furniture.setX((xt << 4) + 8);
            furniture.setY((yt << 4) + 8);
            level.add(furniture);
            placed = true;
            return true;
        }
        return false;
    }

    public boolean isDepleted() {
        return placed;
    }

    public Furniture getFurniture() {
        return furniture;
    }

    public void setFurniture(Furniture furniture) {
        this.furniture = furniture;
    }
}
