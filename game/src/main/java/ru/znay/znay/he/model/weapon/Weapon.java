package ru.znay.znay.he.model.weapon;

import ru.znay.znay.he.model.ETeam;
import ru.znay.znay.he.model.item.Item;
import ru.znay.znay.he.model.level.Level;
import ru.znay.znay.he.model.weapon.arrow.EArrowType;
import ru.znay.znay.he.model.weapon.arrow.FireArrow;
import ru.znay.znay.he.model.weapon.arrow.SilverArrow;
import ru.znay.znay.he.model.weapon.arrow.SimpleArrow;

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
 * Created on 16.04.12
 * <p/>
 * $Revision: $
 * $Author: ASTarasov $
 * $Source:
 * $Id: $
 * <p/>
 * 16.04.12: Original version (WHo)
 * 16:46: Time
 */
public class Weapon extends Item {

    public static void fire(EArrowType arrowType, ETeam ownerTeam, int x, int y, double vx, double vy, int damageBonus, Level level) {
        switch (arrowType) {
            case FIRE: {
                level.add(new FireArrow(ownerTeam, x, y, vx, vy, damageBonus));
                break;
            }
            case SILVER: {
                level.add(new SilverArrow(ownerTeam, x, y, vx, vy, damageBonus));
                break;
            }
            case SIMPLE: {
                level.add(new SimpleArrow(ownerTeam, x, y, vx, vy, damageBonus));
                break;
            }
        }
    }

}
