package ru.znay.znay.he.model.item.equipment;

import ru.znay.znay.he.gfx.helper.PaletteHelper;
import ru.znay.znay.he.model.CharacterState;

/**
 * Created by IntelliJ IDEA.
 * User: Александр Сергеевич
 * Date: 24.04.12
 * Time: 21:16
 * To change this template use File | Settings | File Templates.
 */
public class Equipment {

    public enum EQUIP_TYPE {
        WEAPON,
        SHOES,
        ARMOR,
    }

    public static final Equipment simpleBow = new Equipment("Bow", 0, 12, PaletteHelper.getColor(-1, 222, 0, 555), new CharacterState(0, 0, 8, 0, 0, 30), EQUIP_TYPE.WEAPON);
    public static final Equipment strongBow = new Equipment("S.Bow", 1, 12, PaletteHelper.getColor(-1, 500, 0, 555), new CharacterState(0, 0, 12, 0, 0, 30), EQUIP_TYPE.WEAPON);
    public static final Equipment rareBow = new Equipment("R.Bow", 2, 12, PaletteHelper.getColor(-1, 0, 0, 555), new CharacterState(0, 0, 10, 0, 0, 15), EQUIP_TYPE.WEAPON);

    private final EQUIP_TYPE equipType;
    private final CharacterState bonusState;
    private final String name;
    private final int xSprite;
    private final int ySprite;
    private final int color;

    public Equipment(String name, int xSprite, int ySprite, int color, CharacterState bonusState, EQUIP_TYPE equipType) {
        this.bonusState = bonusState;
        this.color = color;
        this.equipType = equipType;
        this.name = name;
        this.xSprite = xSprite;
        this.ySprite = ySprite;
    }

    public CharacterState getBonusState() {
        return bonusState;
    }

    public int getColor() {
        return color;
    }

    public String getName() {
        return name;
    }

    public EQUIP_TYPE getEquipType() {
        return equipType;
    }

    public int getxSprite() {
        return xSprite;
    }

    public int getySprite() {
        return ySprite;
    }
}
