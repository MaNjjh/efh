package ru.znay.znay.he.quest.template;

import java.util.LinkedList;
import java.util.List;

/**
 * Created by IntelliJ IDEA.
 * User: Денис Сергеевич
 * Date: 25.04.12
 * Time: 16:04
 * To change this template use File | Settings | File Templates.
 */
public class MergedTemplate {
    private List<DefaultTemplate> list = new LinkedList<DefaultTemplate>();

    public void add(DefaultTemplate defaultTemplate) {
        list.add(defaultTemplate);
    }

    public boolean isCompleted() {
        for(DefaultTemplate quest:list)
            if(!quest.isComplete()){
                return false;
            }
        return true;
    }
    
    public void incKills(String name)
    {
        for(DefaultTemplate quest:list)
            if(quest instanceof KillTemplate2)
                ((KillTemplate2)quest).incKill(name);
    }
    
    public int calcQuestType()
    {
        int result = 0;
        for(DefaultTemplate quest:list)
           result |= quest.getType();

        return result;
    }
}
