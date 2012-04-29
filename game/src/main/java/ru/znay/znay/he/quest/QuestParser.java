package ru.znay.znay.he.quest;

import ru.znay.znay.he.model.level.Level;
import ru.znay.znay.he.quest.promotion.GoldPromotion;
import ru.znay.znay.he.quest.promotion.ItemPromotion;
import ru.znay.znay.he.quest.promotion.LifePromotion;
import ru.znay.znay.he.quest.promotion.MergedPromotion;
import ru.znay.znay.he.quest.promotion.PromotionType;
import ru.znay.znay.he.quest.template.MergedTemplate;
import ru.znay.znay.he.quest.template.TemplateFactory;

import java.util.LinkedList;
import java.util.List;

/**
 * Created by IntelliJ IDEA.
 * User: Денис Сергеевич
 * Date: 26.04.12
 * Time: 12:08
 * To change this template use File | Settings | File Templates.
 */
public class QuestParser {

    public List<AbsQuest> parseQuests(List<String> strings, Level level) {
        List<AbsQuest> list = new LinkedList<AbsQuest>();

        if (strings == null || strings.isEmpty()) {
            System.out.println("ParseQuest - list is null or empty");
            return list;
        }

        for (String str : strings) {
            if (str.isEmpty()) continue;
            //str = ","+str;
            AbsQuest temp = parseQuest(str.split(","), level);
            if (temp == null) continue;

            list.add(temp);
        }

        return list;
    }

    private AbsQuest parseQuest(String[] str, Level level) {
        if (str == null || str.length == 0) {
            System.out.println("ParseQuest - str is null or empty");
            return null;
        }

        AbsQuest quest = new AbsQuest();
        quest.setId(str[0]);

        quest.setName(level.getMessage(Integer.decode(str[2])));
        quest.setDescription(level.getMessage(Integer.decode(str[3])));

        int count = Integer.decode(str[5]);
        MergedTemplate templates = new MergedTemplate();
        quest.setMergedTemplate(templates);

        int templateOffset = 6;

        for (int i = templateOffset; i <= templateOffset * count; i += 3) {
            templates.add(TemplateFactory.getInstance().createTemplate(str[i], str[i + 1], str[i + 2]));
        }

        quest.calcQuestType();

        int promotionOffset = count * 3 + templateOffset;

        count = Integer.decode(str[promotionOffset]);

        promotionOffset++;

        MergedPromotion mergedPromotion = new MergedPromotion();

        for (int i = promotionOffset; i < ((promotionOffset) + count * 3); i += 3) {
            int type = Integer.decode(str[i]);
            if (type == PromotionType.LIFE) {
                LifePromotion promotion = new LifePromotion();
                promotion.setLife(Integer.decode(str[i + 2]));
                mergedPromotion.add(promotion);
                continue;
            }
            if (type == PromotionType.GOLD) {
                GoldPromotion promotion = new GoldPromotion();
                promotion.setGold(Integer.decode(str[i + 2]));
                mergedPromotion.add(promotion);
                continue;
            }

            if (type == PromotionType.ITEM) {
                ItemPromotion promotion = new ItemPromotion();
                promotion.setItemName(str[i + 1]);
                promotion.setCount(Integer.decode(str[i + 2]));
            }
        }

        quest.setQuestPromotion(mergedPromotion);


        quest.setNextQuestID(Integer.decode(str[4]));

        return quest;
    }
}
