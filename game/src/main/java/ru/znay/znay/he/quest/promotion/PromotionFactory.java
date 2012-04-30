package ru.znay.znay.he.quest.promotion;

/**
 * Created by IntelliJ IDEA.
 * User: Александр Сергеевич
 * Date: 06.04.12
 * Time: 20:27
 * To change this template use File | Settings | File Templates.
 */
public class PromotionFactory {
    private static PromotionFactory instance = new PromotionFactory();

    public static PromotionFactory getInstance() {
        return instance;
    }

    private PromotionFactory() {

    }

    public QuestPromotion createPromotion(String name, String param, String count) {

        if (name.isEmpty()) return null;

        final StringBuilder result = new StringBuilder(name.length());

        name = name.toLowerCase();

        result.append(Character.toUpperCase(name.charAt(0))).append(name.substring(1));

        String className = QuestPromotion.class.getPackage().getName() + "." + result + "Promotion";

        try {
            QuestPromotion promotion = (QuestPromotion)Class.forName(className).newInstance();
            promotion.setParam(param);
            promotion.setCount(count);
            return  promotion;
        } catch (InstantiationException e) {
            return null;
        } catch (IllegalAccessException e) {
            return null;
        } catch (ClassNotFoundException e) {
            return null;
        }

    }
}
