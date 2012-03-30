package ru.znay.znay.he.gfx.helper;

import java.awt.*;

/**
 * Created by IntelliJ IDEA.
 * User: Денис Сергеевич
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

    public static final int MIRROR_NONE = 0;
    public static final int MIRROR_X = 1;
    public static final int MIRROR_Y = 2;
    public static final int MIRROR_XY = 3;
    
    public static Point get(Orientation orientation) {
        switch (orientation) {
            case LEFT_STAY:
                return new Point(0, 16);
            case LEFT_MOVE_1:
                return new Point(16, 16);
            case LEFT_MOVE_2:
                return new Point(32, 16);
            case RIGHT_STAY:
                return new Point(0, 32);
            case RIGHT_MOVE_1:
                return new Point(16, 32);
            case RIGHT_MOVE_2:
                return new Point(32, 32);
            case UP_STAY:
                return new Point(0, 0);
            case UP_MOVE_1:
                return new Point(16, 0);
            case UP_MOVE_2:
                return new Point(32, 0);
            default:
            case DOWN_STAY:
                return new Point(0, 48);
            case DOWN_MOVE_1:
                return new Point(16, 48);
            case DOWN_MOVE_2:
                return new Point(32, 48);
        }
    }
}
