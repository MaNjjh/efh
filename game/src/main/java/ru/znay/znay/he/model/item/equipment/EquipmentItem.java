package ru.znay.znay.he.model.item.equipment;

import ru.znay.znay.he.gfx.model.Screen;
import ru.znay.znay.he.model.CharacterState;
import ru.znay.znay.he.model.item.Item;
import ru.znay.znay.he.model.item.ItemEntity;
import ru.znay.znay.he.model.level.tile.Tile;

/**
 * Created by IntelliJ IDEA.
 * User: Александр Сергеевич
 * Date: 24.04.12
 * Time: 21:53
 * To change this template use File | Settings | File Templates.
 */
public class EquipmentItem extends Item {
    public Equipment equipment;

    public EquipmentItem(Equipment equipment) {
        this.equipment = equipment;
    }

    public int getColor() {
        return this.equipment.getColor();
    }

    @Override
    public int getxSprite() {
        return this.equipment.getxSprite();
    }

    @Override
    public int getySprite() {
        return this.equipment.getySprite();
    }

    @Override
    public int getScale() {
        return 3;
    }

    @Override
    public int getMaxTime() {
        return 2000;
    }

    public void renderIcon(Screen screen, int x, int y) {
        screen.render(x, y, equipment.getxSprite() * Tile.HALF_SIZE, equipment.getySprite() * Tile.HALF_SIZE, equipment.getColor(), 0);
    }

    public void renderInventory(Screen screen, int x, int y) {
        screen.render(x, y, equipment.getxSprite() * Tile.HALF_SIZE, equipment.getySprite() * Tile.HALF_SIZE, equipment.getColor(), 0);
    }

    public String getName() {
        return this.equipment.getName();
    }

    public void onTake(ItemEntity itemEntity) {
    }

    public boolean canAttack() {
        return true;
    }

    public CharacterState getBonusState() {
        return this.equipment.getBonusState();
    }

    public Equipment.EQUIP_TYPE getEquipType() {
        return this.equipment.getEquipType();
    }

    public boolean matches(Item item) {
        if (item instanceof EquipmentItem) {
            EquipmentItem other = (EquipmentItem) item;
            return other.getEquipment() == this.equipment;
        }
        return false;
    }

    public Equipment getEquipment() {
        return equipment;
    }
}
