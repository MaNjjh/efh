package ru.znay.znay.he.gfx.helper;

import java.awt.*;

/**
 * Created by IntelliJ IDEA.
 * User: denus
 * Date: 28.03.12
 * Time: 22:08
 * To change this template use File | Settings | File Templates.
 */
public enum Orientation {
    LEFT_STAY,
    LEFT_MOVE_1,
    LEFT_MOVE_2,
    RIGHT_STAY,
    RIGHT_MOVE_1,
    RIGHT_MOVE_2,
    UP_STAY,
    UP_MOVE_1,
    UP_MOVE_2,
    DOWN_STAY,
    DOWN_MOVE_1,
    DOWN_MOVE_2;

    public static Point getOff(Orientation orientation) {
        switch (orientation) {
            case LEFT_STAY:
                return new Point(33, 33);
            case LEFT_MOVE_1:
                return new Point(0, 33);
            case LEFT_MOVE_2:
                return new Point(65, 33);
            case RIGHT_STAY:
                return new Point(33, 65);
            case RIGHT_MOVE_1:
                return new Point(0, 65);
            case RIGHT_MOVE_2:
                return new Point(65, 65);
            case UP_STAY:
                return new Point(33, 97);
            case UP_MOVE_1:
                return new Point(0, 97);
            case UP_MOVE_2:
                return new Point(65, 97);
            default:
            case DOWN_STAY:
                return new Point(33, 0);
            case DOWN_MOVE_1:
                return new Point(0, 0);
            case DOWN_MOVE_2:
                return new Point(65, 0);
        }
    }
}
