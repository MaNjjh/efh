package ru.znay.znay.he.quest.template;

import ru.znay.znay.he.quest.AbsQuest;
import ru.znay.znay.he.quest.NextQuest;

/**
 * Created by IntelliJ IDEA.
 * User: Денис Сергеевич
 * Date: 23.04.12
 * Time: 15:05
 * To change this template use File | Settings | File Templates.
 */
public class MoveTemplate extends AbsQuest {

    public MoveTemplate() {

    }

    public MoveTemplate(NextQuest nextQuest) {
        this.nextQuest = nextQuest;
    }

    @Override
    public boolean isCompleted() {
        return false;  //To change body of implemented methods use File | Settings | File Templates.
    }

}
