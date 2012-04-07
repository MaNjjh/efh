package ru.znay.znay.he.model.item;

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
public class Item {

    public boolean interactOn(Tile tile, Level level, int xt, int yt, Player player, int attackDir) {
        return false;
    }

    public boolean isDepleted() {
        return false;
    }


}
