package ru.znay.znay.he.quest.template;

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

    public DefaultTemplate createTemplate(String name, String param1, String param2) {

        if (name.isEmpty()) return null;

        final StringBuilder result = new StringBuilder(name.length());

        name = name.toLowerCase();

        result.append(Character.toUpperCase(name.charAt(0))).append(name.substring(1));

        String className = TemplateFactory.class.getPackage().getName() + "." + result + "Template";

        try {
            DefaultTemplate template = (DefaultTemplate) Class.forName(className).newInstance();
            template.setParam1(param1);
            template.setParam2(param2);
            return template;
        } catch (InstantiationException e) {
            return null;
        } catch (IllegalAccessException e) {
            return null;
        } catch (ClassNotFoundException e) {
            return null;
        }

    }
}
