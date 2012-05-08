package ru.znay.znay.he.model;

import ru.znay.znay.he.Game;
import ru.znay.znay.he.InputHandler;
import ru.znay.znay.he.gfx.gui.GuiInventory;
import ru.znay.znay.he.gfx.gui.GuiManager;
import ru.znay.znay.he.gfx.helper.PaletteHelper;
import ru.znay.znay.he.gfx.model.Font;
import ru.znay.znay.he.gfx.model.Screen;
import ru.znay.znay.he.model.item.FurnitureItem;
import ru.znay.znay.he.model.item.Inventory;
import ru.znay.znay.he.model.item.Item;
import ru.znay.znay.he.model.item.ItemEntity;
import ru.znay.znay.he.model.item.equipment.Equipment;
import ru.znay.znay.he.model.item.equipment.EquipmentItem;
import ru.znay.znay.he.model.item.furniture.Furniture;
import ru.znay.znay.he.model.item.resource.Resource;
import ru.znay.znay.he.model.item.resource.ResourceItem;
import ru.znay.znay.he.model.level.Level;
import ru.znay.znay.he.model.level.tile.Tile;
import ru.znay.znay.he.model.particle.FlowText;
import ru.znay.znay.he.model.particle.FlowTextIcon;
import ru.znay.znay.he.model.weapon.Weapon;
import ru.znay.znay.he.model.weapon.arrow.EArrowType;

import java.awt.*;
import java.util.List;

/**
 * Created by IntelliJ IDEA.
 * User: Александр Сергеевич
 * Date: 03.03.12
 * Time: 18:13
 * To change this template use File | Settings | File Templates.
 */

public class Player extends Mob {

    private Game game;
    private Item activeItem;
    private int clearFogRadius = 5;
    private Point respPoint = null;
    private EArrowType arrowType = EArrowType.FIRE;
    private int fireDelay = 10;
    private EquipmentItem weapon;
    private EquipmentItem shoes;
    private EquipmentItem armor;
    private Inventory inventory = new Inventory();
    private Inventory averagedPickups = new Inventory();
    private long collectTime;

    public Player(Game game) {
        this.team = ETeam.PLAYER_TEAM;
        this.game = game;
        this.bloodColor = 0xcc00cc;

    }

    @Override
    public void init(Level level) {
        super.init(level);
        touchItem(new ItemEntity(new ResourceItem(Resource.apple, 10), x, y));
        /*
        touchItem(new ItemEntity(new EquipmentItem(Equipment.simpleBow), x, y));
        touchItem(new ItemEntity(new EquipmentItem(Equipment.rareArmor), x, y));
        touchItem(new ItemEntity(new EquipmentItem(Equipment.strongShoes), x, y));
        */
    }

    @Override
    public void tick() {

        int xa = 0;
        int ya = 0;

        InputHandler inputHandler = InputHandler.getInstance(null);

        if (!GuiManager.isOpenedMenu) {
            if (inputHandler.up.down) {
                ya--;
            }
            if (inputHandler.down.down) {
                ya++;
            }
            if (inputHandler.left.down) {
                xa--;
            }
            if (inputHandler.right.down) {
                xa++;
            }

            if (inputHandler.berryUse.clicked) {
                if (health < currentState.getEndurance() && inventory.removeResource(Resource.berry, 1)) {
                    health = Math.min(currentState.getEndurance(), health + 1);
                    level.add(new FlowText("+1", x, y, Font.greenColor));
                }
            }

            if (inputHandler.appleUse.clicked) {
                if (health < currentState.getEndurance() && inventory.removeResource(Resource.apple, 1)) {
                    health = Math.min(currentState.getEndurance(), health + 1);
                    level.add(new FlowText("+1", x, y, Font.greenColor));
                }
            }

            if (inputHandler.mouse.clicked || inputHandler.action.clicked) {
                take();

                /*if (level.getEntities(x - Tile.HALF_SIZE, y - Tile.HALF_SIZE, x + Tile.HALF_SIZE, y + Tile.HALF_SIZE, null).size() == 1) {
                    if (score >= Mushroom.cost) {
                        if (level != null) {
                            level.add(new Mushroom(x, y));
                            score -= Mushroom.cost;
                        }
                    }
                }*/
            } else if (inputHandler.mouse.down && activeItem == null && weapon != null) {
                int xDiff = inputHandler.getXMousePos() - x;
                int yDiff = inputHandler.getYMousePos() - y;

                double m = Math.sqrt(xDiff * xDiff + yDiff * yDiff);

                double vx = xDiff / m;
                double vy = yDiff / m;

                if (tickTime % currentState.getAttackDelay() == 0) {
                    Weapon.fire(this.arrowType, this.team, x, y, vx, vy, currentState.getForce(), level);
                }

            }
        }

        move(xa, ya);

        if (System.currentTimeMillis() - collectTime > 300) {
            updateAveragedNotify();
            collectTime = System.currentTimeMillis();
        }

        super.tick();
    }

    @Override
    public boolean canRegenerate() {
        return true;
    }

    @Override
    public void touchedBy(Entity entity) {
        if ((entity.getTeam() != this.team)) {
            entity.touchedBy(this);
        }
    }

    @Override
    public void render(Screen screen) {

        int xt = 8;
        int yt = 14;

        int flip1 = (walkDist >> 3) & 1;
        int flip2 = (walkDist >> 3) & 1;

        if (dir == 1) {
            xt += 2;
        }
        if (dir > 1) {

            flip1 = 0;
            flip2 = ((walkDist >> 4) & 1);
            if (dir == 2) {
                flip1 = 1;
            }
            xt += 4 + ((walkDist >> 3) & 1) * 2;
        }

        int xo = x - 8;
        int yo = y - 11;
        if (isSwimming()) {
            yo += 4;
            int waterColor = PaletteHelper.getColor(-1, -1, -1, 444);
            if (tickTime / 8 % 2 == 0) {
                waterColor = PaletteHelper.getColor(-1, 444, -1, -1);
            }
            screen.render(xo + 0, yo + 3, 5 * Tile.HALF_SIZE, 13 * Tile.HALF_SIZE, waterColor, 0);
            screen.render(xo + Tile.HALF_SIZE, yo + 3, 5 * Tile.HALF_SIZE, 13 * Tile.HALF_SIZE, waterColor, 1);
        }

        int col1 = PaletteHelper.getColor(-1, 100, 500, 555);
        int col2 = PaletteHelper.getColor(-1, 100, 500, 532);

        if (hurtTime > 0) {
            col1 = PaletteHelper.getColor(-1, 555, 555, 555);
            col2 = PaletteHelper.getColor(-1, 555, 555, 555);
        }

        if (activeItem instanceof FurnitureItem) {
            yt += 2;
        }

        screen.render(xo + Tile.HALF_SIZE * flip1, yo + 0, xt * Tile.HALF_SIZE, yt * Tile.HALF_SIZE, col1, flip1);
        screen.render(xo + Tile.HALF_SIZE - Tile.HALF_SIZE * flip1, yo + 0, (xt + 1) * Tile.HALF_SIZE, yt * Tile.HALF_SIZE, col1, flip1);
        if (!isSwimming()) {
            screen.render(xo + Tile.HALF_SIZE * flip2, yo + Tile.HALF_SIZE, xt * Tile.HALF_SIZE, (yt + 1) * Tile.HALF_SIZE, col2, flip2);
            screen.render(xo + Tile.HALF_SIZE - Tile.HALF_SIZE * flip2, yo + Tile.HALF_SIZE, (xt + 1) * Tile.HALF_SIZE, (yt + 1) * Tile.HALF_SIZE, col2, flip2);
        }

        if (activeItem instanceof FurnitureItem) {
            Furniture furniture = ((FurnitureItem) activeItem).getFurniture();
            furniture.x = x;
            furniture.y = yo;
            furniture.render(screen);
        }
    }

    public void updateEquip() {
        currentState = defaultState.mergeStates(new CharacterState());
        weapon = inventory.findEquipmentByType(Equipment.EQUIP_TYPE.WEAPON);
        if (weapon != null) {
            System.out.println("found weapon " + weapon.getEquipment().getName());
            currentState = currentState.mergeStates(weapon.getBonusState());
        }
        shoes = inventory.findEquipmentByType(Equipment.EQUIP_TYPE.SHOES);
        if (shoes != null) {
            System.out.println("found shoes " + shoes.getEquipment().getName());
            currentState = currentState.mergeStates(shoes.getBonusState());
        }
        armor = inventory.findEquipmentByType(Equipment.EQUIP_TYPE.ARMOR);
        if (armor != null) {
            System.out.println("found armor " + armor.getEquipment().getName());
            currentState = currentState.mergeStates(armor.getBonusState());
        }
    }

    @Override
    public void updateState() {
        GuiInventory guiInventory = (GuiInventory) GuiManager.getInstance().get("inventory");
        if (guiInventory != null) {

            int speed = 0;
            int slowPeriod = currentState.getSlowPeriod();
            if (slowPeriod < 3) {
                speed = 30;
            } else if (slowPeriod < 10) {
                speed = 60;
            } else if (slowPeriod < 40) {
                speed = 90;
            } else if (slowPeriod < 60) {
                speed = 120;
            }

            guiInventory.setSpeed(speed);
            guiInventory.setDef(currentState.getDefense());
            guiInventory.setSta(currentState.getEndurance());
            guiInventory.setStr(currentState.getForce());
        }
    }

    private void updateAveragedNotify() {
        if (averagedPickups.getItems().size() == 0) return;
        Item item = averagedPickups.getItems().get(0);
        if (item != null) {
            int count = item instanceof ResourceItem ? ((ResourceItem) item).getCount() : 1;
            level.add(new FlowTextIcon("+" + count, x - Tile.HALF_SIZE, y - Tile.HALF_SIZE, Font.yellowColor, item.getxSprite(), item.getySprite(), item.getColor()));
            averagedPickups.getItems().remove(item);
        }
    }

    public void touchItem(ItemEntity itemEntity) {
        if (itemEntity.isRemoved()) return;

        level.getQuestHandler().updateItems(itemEntity.getItem().getName());

        itemEntity.take(this);
        inventory.add(itemEntity.getItem());
        averagedPickups.add(itemEntity.getItem());

        if (itemEntity.getItem() instanceof EquipmentItem) {
            updateEquip();
        }

        if (itemEntity.getItem() instanceof ResourceItem) {
            ResourceItem resourceItem = (ResourceItem) itemEntity.getItem();

            if (resourceItem.getResource() == Resource.life) {
                CharacterState lifeState = new CharacterState(0, resourceItem.getCount(), 0, 0, 0);

                this.defaultState = this.defaultState.mergeStates(lifeState);
                this.currentState = this.currentState.mergeStates(lifeState);
            }
        }
    }

    public boolean take() {
        boolean done = false;

        int yo = -2;
        int range = 12;
        if (dir == 0 && interact(x - 8, y + 4 + yo, x + 8, y + range + yo)) done = true;
        if (dir == 1 && interact(x - 8, y - range + yo, x + 8, y - 4 + yo)) done = true;
        if (dir == 3 && interact(x + 4, y - 8 + yo, x + range, y + 8 + yo)) done = true;
        if (dir == 2 && interact(x - range, y - 8 + yo, x - 4, y + 8 + yo)) done = true;
        if (activeItem != null) {
            int xt = x >> 4;
            int yt = (y + yo) >> 4;
            int r = 12;
            if (dir == 0) yt = (y + r + yo) >> 4;
            if (dir == 1) yt = (y - r + yo) >> 4;
            if (dir == 2) xt = (x - r) >> 4;
            if (dir == 3) xt = (x + r) >> 4;

            if (xt >= 0 && yt >= 0 && xt < level.getWidth() && yt < level.getHeight()) {
                level.getTile(xt, yt).interact(level, xt, yt, this, activeItem, dir);

                activeItem.interactOn(level.getTile(xt, yt), level, xt, yt, this, dir);

                if (activeItem.isDepleted()) {
                    activeItem = null;
                }
            }
        }
        return done;

    }

    private boolean interact(int x0, int y0, int x1, int y1) {
        List<Entity> entities = level.getEntities(x0, y0, x1, y1, null);
        for (Entity entity : entities) {
            entity.interact(activeItem, this, dir);
        }
        return false;
    }

    @Override
    public boolean canSwim() {
        return true;
    }

    public boolean findStartPos(Level level) {
        while (true) {
            int x = random.nextInt(level.getWidth());
            int y = random.nextInt(level.getWidth());
            if (level.getTile(x, y) == Tile.grass) {
                this.x = x * Tile.SIZE + Tile.HALF_SIZE;
                this.y = y * Tile.SIZE + Tile.HALF_SIZE;
                return true;
            }
        }
    }


    public int getClearFogRadius() {
        return Math.min(clearFogRadius, 10);
    }

    public Item getActiveItem() {
        return activeItem;
    }

    public void setActiveItem(Item activeItem) {
        this.activeItem = activeItem;
    }

    public void moveToXY(int x, int y) {
        this.x = x;
        this.y = y;
        InputHandler.getInstance(null).releaseAll();
    }

    public void moveLevel(int newLevel, int x, int y) {
        respPoint = new Point(x, y);
        this.game.loadLevel(newLevel);
        InputHandler.getInstance(null).releaseAll();
    }

    public Point getRespPoint() {
        return respPoint;
    }

    public void setRespPoint(int x, int y) {
        respPoint = new Point(x, y);
    }

    public int getFireDelay() {
        return fireDelay;
    }

    public void setFireDelay(int fireDelay) {
        this.fireDelay = fireDelay;
    }

    public Inventory getInventory() {
        return inventory;
    }
}
