package ru.znay.znay.he.quest.promotion;

import ru.znay.znay.he.model.Mob;
import ru.znay.znay.he.model.Player;
import ru.znay.znay.he.model.mob.MobFactory;


/**
 * Created by IntelliJ IDEA.
 * User: Денис Сергеевич
 * Date: 29.04.12
 * Time: 18:51
 * To change this template use File | Settings | File Templates.
 */
@Deprecated
public class SpawnPromotion implements QuestPromotion {
    private String mobName;
    private int count;


    @Override
    public void promotion(Player player) {
        for (int i = 0; i < count; i++) {
            Mob mob = MobFactory.getInstance().createMob(mobName, false);
            mob.setX(player.getX() - 20);
            mob.setY(player.getY() - 20);
            player.getLevel().add(mob);
        }

    }

    @Override
    public void setParam(String param) {
        mobName = param;
    }

    @Override
    public void setCount(String count) {
        this.count = Integer.decode(count);
    }
}
