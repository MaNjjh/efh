package ru.znay.znay.he.quest.template;

/**
 * Created by IntelliJ IDEA.
 * User: Денис Сергеевич
 * Date: 25.04.12
 * Time: 15:46
 * To change this template use File | Settings | File Templates.
 */
public class MoveTemplate extends DefaultTemplate {
    private int npcID;

    public MoveTemplate() {
        super(TemplateType.MOVE);
    }

    public int getNpcID() {
        return npcID;
    }

    public void setNpcID(int npcID) {
        this.npcID = npcID;
    }
}
