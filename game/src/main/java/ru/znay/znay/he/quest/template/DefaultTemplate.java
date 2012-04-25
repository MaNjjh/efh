package ru.znay.znay.he.quest.template;

import ru.znay.znay.he.quest.AbsQuest;

/**
 * Created by IntelliJ IDEA.
 * User: Денис Сергеевич
 * Date: 25.04.12
 * Time: 15:08
 * To change this template use File | Settings | File Templates.
 */
public abstract class DefaultTemplate {
    private int type;
    protected boolean complete;
    protected AbsQuest parent;

    protected DefaultTemplate(int type) {
        this.type = type;
    }

    public int getType() {
        return type;
    }

    public boolean isComplete() {
        return complete;
    }

    public void complete() {
        complete = true;
        if (parent != null) {
            parent.isCompleted();
        }
    }

    public AbsQuest getParent() {
        return parent;
    }

    public void setParent(AbsQuest parent) {
        this.parent = parent;
        parent.isCompleted();
    }
}
