package ru.znay.znay.he.model.item.resource;

import ru.znay.znay.he.gfx.helper.PaletteHelper;
import ru.znay.znay.he.gfx.model.Screen;
import ru.znay.znay.he.model.Entity;
import ru.znay.znay.he.model.Player;
import ru.znay.znay.he.model.item.Item;
import ru.znay.znay.he.model.level.tile.Tile;
import ru.znay.znay.he.model.particle.Particle;
import ru.znay.znay.he.model.particle.SmashParticle;
import ru.znay.znay.he.sound.Sound;

/**
 * Created by IntelliJ IDEA.
 * User: Александр Сергеевич
 * Date: 24.03.12
 * Time: 15:49
 * To change this template use File | Settings | File Templates.
 */
public class ItemEntity extends SmashParticle {
    public Item item;

    public ItemEntity(Item item, int x, int y) {
        super(x, y, 0);
        this.item = item;
        this.time = 600;
    }

    @Override
    public boolean canFly() {
        return true;
    }

    @Override
    public void tick() {
        super.tick();

        if ((zz == 0) && level.getPlayer() != null) {
            int xd = level.getPlayer().getX() - x;
            int yd = level.getPlayer().getY() - y;
            //int mag = 0*level.getPlayer().getScore() / 200;
            if (xd * xd + yd * yd < /*mag * mag +*/ 20 * 20) {
                if (xd < 0) xa = -1;
                if (xd > 0) xa = +1;
                if (yd < 0) ya = -1;
                if (yd > 0) ya = +1;
            }
            move((int) xa, (int) ya);
        }

    }


    public void render(Screen screen) {
        if (time < 200) {
            if (time / 6 % 2 == 0) return;
        }
        screen.render(x - 4, y - 4, item.getxSprite() * Tile.HALF_SIZE, item.getySprite() * Tile.HALF_SIZE, PaletteHelper.getColor(-1, 0, 0, 0), 0);
        screen.render(x - 4, y - 4 - (int) (zz), item.getxSprite() * Tile.HALF_SIZE, item.getySprite() * Tile.HALF_SIZE, item.getColor(), 0);
    }

    public void touchedBy(Entity entity) {
        entity.touchItem(this);
    }

    public void take(Entity entity) {
        if (entity instanceof Player) {
            Sound.pickup.play();
        }
        item.onTake(this);
        removed = true;
    }
}
