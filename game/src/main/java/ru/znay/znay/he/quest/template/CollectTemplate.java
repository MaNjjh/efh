package ru.znay.znay.he.quest.template;

/**
 * Created by IntelliJ IDEA.
 * User: Денис Сергеевич
 * Date: 01.05.12
 * Time: 20:32
 * To change this template use File | Settings | File Templates.
 */
@Deprecated
public class CollectTemplate extends DefaultTemplate {
    private int countToCollect;
    private int currentCount  = 0;
    private String itemName;

    public CollectTemplate(){
        super(TemplateType.COLLECT);
    }

    @Override
    public void setParam1(String param) {
        itemName = param;
    }

    @Override
    public void setParam2(String param) {
        countToCollect = Integer.decode(param);
    }

    public void incCount(String itemName){
        if (complete) return;
        if (this.itemName.equalsIgnoreCase(itemName)){
            currentCount++;
            checkStatus();
        }
    }

    public void checkStatus(){
        if (countToCollect <= currentCount)
            this.complete();
    }
}
