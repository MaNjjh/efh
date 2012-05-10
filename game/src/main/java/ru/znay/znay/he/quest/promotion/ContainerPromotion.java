package ru.znay.znay.he.quest.promotion;

import ru.znay.znay.he.model.Player;
import ru.znay.znay.he.model.builds.utensils.ContainerFactory;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by IntelliJ IDEA.
 * User: Денис Сергеевич
 * Date: 02.05.12
 * Time: 15:11
 * To change this template use File | Settings | File Templates.
 */
@Deprecated
public class ContainerPromotion implements QuestPromotion {
    private String name;
    private List<String> items = new ArrayList<String>();
    ;
    boolean flag;
    private int x;
    private int y;

    @Override
    public void promotion(Player player) {
        player.getLevel().add(ContainerFactory.getInstance().createContainer(name, items, flag, x << 4, y << 4, player.getLevel().getSpriteCollector()));
    }

    @Override
    public void setParam(String param) {
        String[] params = param.split(":");
        name = params[0];
        x = Integer.decode(params[1]);
        y = Integer.decode(params[2]);
        flag = Boolean.parseBoolean(params[3]);
    }

    @Override
    public void setCount(String count) {
        items.addAll(Arrays.asList(count.split(":")));
    }
}
