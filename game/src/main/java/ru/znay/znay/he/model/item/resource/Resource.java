package ru.znay.znay.he.model.item.resource;

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
}
