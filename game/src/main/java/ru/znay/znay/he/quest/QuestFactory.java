package ru.znay.znay.he.quest;

import java.io.InputStream;

/**
 * Created by IntelliJ IDEA.
 * User: Александр Сергеевич
 * Date: 03.04.12
 * Time: 21:12
 * To change this template use File | Settings | File Templates.
 */
public class QuestFactory {

    private static QuestFactory instance = new QuestFactory();

    public static QuestFactory getInstance() {
        return instance;
    }

    private QuestFactory() {

    }

    public AbsQuest createQuest(InputStream inputStream) {
        return null;
    }
}
