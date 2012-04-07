package ru.znay.znay.he.quest.template;

import ru.znay.znay.he.quest.AbsQuest;

/**
 * Created by IntelliJ IDEA.
 * User: Александр Сергеевич
 * Date: 06.04.12
 * Time: 21:29
 * To change this template use File | Settings | File Templates.
 */
public class TemplateFactory {
    private static TemplateFactory instance = new TemplateFactory();

    public static TemplateFactory getInstance() {
        return instance;
    }

    private TemplateFactory() {

    }

    public AbsQuest createPromotion(String name) {

        if (name.isEmpty()) return null;

        final StringBuilder result = new StringBuilder(name.length());

        name = name.toLowerCase();

        result.append(Character.toUpperCase(name.charAt(0))).append(name.substring(1));

        String className = TemplateFactory.class.getPackage().getName() + "." + result + "Template";

        try {
            return (AbsQuest) Class.forName(className).newInstance();
        } catch (InstantiationException e) {
            return null;
        } catch (IllegalAccessException e) {
            return null;
        } catch (ClassNotFoundException e) {
            return null;
        }

    }
}
