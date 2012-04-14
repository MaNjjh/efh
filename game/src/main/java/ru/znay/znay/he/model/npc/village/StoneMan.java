package ru.znay.znay.he.model.npc.village;

import ru.znay.znay.he.gfx.gui.GuiManager;
import ru.znay.znay.he.gfx.gui.TypedTextPanel;
import ru.znay.znay.he.gfx.helper.PaletteHelper;
import ru.znay.znay.he.model.Entity;
import ru.znay.znay.he.model.Player;
import ru.znay.znay.he.model.item.FurnitureItem;
import ru.znay.znay.he.model.item.Item;
import ru.znay.znay.he.model.item.furniture.Bucket;
import ru.znay.znay.he.model.level.Level;
import ru.znay.znay.he.model.npc.NPC;

/**
 * Created by IntelliJ IDEA.
 * User: Александр Сергеевич
 * Date: 14.04.12
 * Time: 21:55
 * To change this template use File | Settings | File Templates.
 */
public class StoneMan extends NPC {

    private boolean stoned = true;
    private boolean stonedTalk = false;

    private String stonedMessage = "Помогите добрый человек. Меня обратили в статую много лет назад и никто не может мне помочь. Только вода из святого источника сможет мне помочь.";
    private String message = "Спасибо!!! Спасибо вам! придет время я вас отблагодарю.";


    public StoneMan(int x, int y) {
        super(x, y);
        this.color1 = PaletteHelper.getColor(-1, 222, 333, 444);
        this.color2 = PaletteHelper.getColor(-1, 222, 333, 444);
        this.xtStart = 8;
        this.ytStart = 16;
    }

    @Override
    public void init(Level level) {
        super.init(level);
        level.add(new Bucket(this.x - 20, this.y));
    }

    @Override
    public void tick() {
        super.tick();
        if (stoned) return;

        if (randomWalkTime > 0) {
            randomWalkTime--;
        }
        if (randomWalkTime == 0) {
            xa = 0;
            ya = 0;
        }

        int speed = (tickTime % 3) == 0 ? 0 : 1;
        if (!move(xa * speed, ya * speed) || random.nextInt(200) == 0 && randomWalkTime == 0) {
            randomWalkTime = 50;
            xa = (random.nextInt(3) - 1);
            ya = (random.nextInt(3) - 1);
        }
    }

    public void touchedBy(Entity entity) {
        if (entity instanceof Player) {
            if (((Player) entity).getActiveItem() != null) {
                checkBucket(((Player) entity).getActiveItem());
            }

            if (stoned && stonedTalk) return;

            GuiManager.getInstance().add(new TypedTextPanel(stoned ? stonedMessage : message, 4, 4, 40), "StoneMan_touchedBy");
            if (stoned) stonedTalk = true;
        }
    }

    @Override
    public boolean interact(Item item, Player player, int dir) {
        return stoned && checkBucket(item);

    }

    private boolean checkBucket(Item item) {
        if (item instanceof FurnitureItem) {
            if (((FurnitureItem) item).getFurniture() instanceof Bucket) {
                Bucket bucket = (Bucket) ((FurnitureItem) item).getFurniture();
                if (bucket.isFull()) {
                    this.ytStart = 14;
                    color1 = PaletteHelper.getColor(-1, 100, 030, 532);
                    color2 = PaletteHelper.getColor(-1, 100, 030, 532);
                    stoned = false;
                    return true;
                }
            }
        }
        return false;
    }
}
