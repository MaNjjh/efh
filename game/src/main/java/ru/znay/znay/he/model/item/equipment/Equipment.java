package ru.znay.znay.he.model.item.equipment;

import ru.znay.znay.he.gfx.helper.PaletteHelper;
import ru.znay.znay.he.model.CharacterState;

import java.util.HashMap;
import java.util.Map;

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

    public static final Map<String, Equipment> equips = new HashMap<String, Equipment>();

    public static final Equipment simpleBow = new Equipment("Bow", 0, 12, PaletteHelper.getColor(-1, 100, 220, 555), new CharacterState(0, 0, 8, 0, 0, 20), EQUIP_TYPE.WEAPON);
    public static final Equipment strongBow = new Equipment("S.Bow", 1, 12, PaletteHelper.getColor(-1, 100, 500, 555), new CharacterState(0, 0, 12, 0, 0, 15), EQUIP_TYPE.WEAPON);
    public static final Equipment rareBow = new Equipment("R.Bow", 2, 12, PaletteHelper.getColor(-1, 100, 0, 555), new CharacterState(0, 0, 15, 0, 0, 10), EQUIP_TYPE.WEAPON);
    public static final Equipment ultraBow = new Equipment("U.Bow", 2, 12, PaletteHelper.getColor(-1, 100, 541, 555), new CharacterState(0, 0, 20, 0, 0, 5), EQUIP_TYPE.WEAPON);


    public static final Equipment simpleArmor = new Equipment("Armor", 3, 12, PaletteHelper.getColor(-1, 100, 220, 555), new CharacterState(0, 0, 0, 0, 0), EQUIP_TYPE.ARMOR);
    public static final Equipment secondArmor = new Equipment("Sec.Armor", 4, 12, PaletteHelper.getColor(-1, 100, 220, 555), new CharacterState(1, 0, 0, 0, 0), EQUIP_TYPE.ARMOR);
    public static final Equipment secondStrongArmor = new Equipment("S.Armor", 5, 12, PaletteHelper.getColor(-1, 100, 500, 555), new CharacterState(2, 0, 0, 0, 0), EQUIP_TYPE.ARMOR);
    public static final Equipment thirdArmor = new Equipment("Thi.Armor", 6, 12, PaletteHelper.getColor(-1, 100, 0, 555), new CharacterState(3, 0, 0, 0, 0), EQUIP_TYPE.ARMOR);
    public static final Equipment thirdStrongArmor = new Equipment("T.Armor", 7, 12, PaletteHelper.getColor(-1, 100, 220, 555), new CharacterState(5, 0, 0, 0, 0), EQUIP_TYPE.ARMOR);
    public static final Equipment rareArmor = new Equipment("R.Armor", 8, 12, PaletteHelper.getColor(-1, 100, 0, 555), new CharacterState(7, 0, 0, 5, 0), EQUIP_TYPE.ARMOR);
    public static final Equipment ultraArmor = new Equipment("U.Armor", 8, 12, PaletteHelper.getColor(-1, 100, 541, 555), new CharacterState(10, 0, 0, 10, 0), EQUIP_TYPE.ARMOR);


    public static final Equipment simpleShoes = new Equipment("Shoes", 9, 12, PaletteHelper.getColor(-1, 100, 220, 555), new CharacterState(0, 0, 0, 0, 1), EQUIP_TYPE.SHOES);
    public static final Equipment strongShoes = new Equipment("S.Shoes", 10, 12, PaletteHelper.getColor(-1, 100, 500, 555), new CharacterState(0, 0, 0, 0, 2), EQUIP_TYPE.SHOES);
    public static final Equipment rareShoes = new Equipment("R.Shoes", 11, 12, PaletteHelper.getColor(-1, 100, 0, 555), new CharacterState(0, 0, 0, 0, 5), EQUIP_TYPE.SHOES);
    public static final Equipment ultraShoes = new Equipment("U.Shoes", 11, 12, PaletteHelper.getColor(-1, 100, 541, 555), new CharacterState(0, 0, 0, 0, 20), EQUIP_TYPE.SHOES);


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
        equips.put(name.toLowerCase(), this);
    }

    public static Equipment getEquipmentByName(String name) {
        return equips.get(name);
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
