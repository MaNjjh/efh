package ru.znay.znay.he.cfg;

/**
 * Created by IntelliJ IDEA.
 * User: Александр Сергеевич
 * Date: 03.03.12
 * Time: 12:48
 * To change this template use File | Settings | File Templates.
 */
public class Constants {
    public static final int SCREEN_WIDTH = 320;
    public static final int SCREEN_HEIGHT = 240;
    public static final int SCREEN_SCALE = 2;

    public static final String GAME_TITLE = "Escape from hell";

    public static final String SPRITES_FILE = "/sprites.png";

    private static final String FOREST = "Лес";
    private static final String VILLAGE = "Деревня";
    private static final String DARK_WOOD = "Темный лес";
    private static final String RIVER = "Река";
    private static final String SWAMP = "Болото";

    public static String getLevelName(int i) {
        switch (i) {
            case 0:
                return FOREST;
            case 1:
                return VILLAGE;
            case 2:
                return DARK_WOOD;
            case 3:
                return RIVER;
            case 4:
                return SWAMP;
            default: return "Неизвестно";
        }
    }
}
