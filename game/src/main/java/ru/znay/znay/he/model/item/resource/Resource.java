package ru.znay.znay.he.model.item.resource;

import ru.znay.znay.he.model.Entity;
import ru.znay.znay.he.model.particle.Particle;

/**
 * Created by IntelliJ IDEA.
 * User: Александр Сергеевич
 * Date: 24.03.12
 * Time: 15:49
 * To change this template use File | Settings | File Templates.
 */
public class Resource extends Particle {

    public Resource(int x, int y) {
        super(x, y);
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


    public void touchedBy(Entity entity) {
        entity.touchItem(this);
    }
}
