package ru.znay.znay.he.model.weapon.arrow;

import ru.znay.znay.he.model.ETeam;

/**
 * Created by IntelliJ IDEA.
 * User: Александр Сергеевич
 * Date: 04.03.12
 * Time: 17:47
 * To change this template use File | Settings | File Templates.
 */
public class SimpleArrow extends Arrow {

    public SimpleArrow(ETeam ownerTeam, int x, int y, double vx, double vy, int damageBonus) {
        super(ownerTeam, x, y, vx, vy, damageBonus);
    }
}

