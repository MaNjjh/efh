package ru.znay.znay.he.quest;

/**
 * Created by IntelliJ IDEA.
 * User: Денис Сергеевич
 * Date: 03.05.12
 * Time: 13:17
 * To change this template use File | Settings | File Templates.
 */
public class QuestLog {
    private boolean[] completeQuests = new boolean[128];
    private int currentQuest = 1;

    public QuestLog() {
        for (int i = 0; i < completeQuests.length; i++) {
            completeQuests[i] = false;
        }
    }

    public boolean isComplete(int questID){
       return completeQuests[questID];
    }

    public boolean isComplete(String questID){
        return isComplete(Integer.decode(questID));
    }

    public void complete(int questID){
        completeQuests[questID] = true;
    }

    public void complete(String questID){
        complete(Integer.decode(questID));
    }

    public int getCurrentQuest() {
        return currentQuest;
    }

    public void setCurrentQuest(int currentQuest) {
        this.currentQuest = currentQuest;
    }

    public void setCurrentQuest(String currentQuest){
        this.setCurrentQuest(Integer.decode(currentQuest));
    }
}
