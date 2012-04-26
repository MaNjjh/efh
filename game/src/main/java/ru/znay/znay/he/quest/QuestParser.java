package ru.znay.znay.he.quest;

import ru.znay.znay.he.model.level.Level;
import ru.znay.znay.he.quest.promotion.ItemPromotion;
import ru.znay.znay.he.quest.promotion.LifePromotion;
import ru.znay.znay.he.quest.promotion.MergedPromotion;
import ru.znay.znay.he.quest.promotion.PricePromotion;
import ru.znay.znay.he.quest.promotion.PromotionType;
import ru.znay.znay.he.quest.template.KillTemplate2;
import ru.znay.znay.he.quest.template.MergedTemplate;
import ru.znay.znay.he.quest.template.MoveTemplate2;
import ru.znay.znay.he.quest.template.TemplateType;

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
    
    public List<AbsQuest> parseQuests(List<String> strings, Level level)
    {
        if (strings == null || strings.isEmpty()) {
            System.out.println("ParseQuest - list is null or empty");
            return null;
        }

        List<AbsQuest> list = new LinkedList<AbsQuest>();

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

        for (int i = 0; i < count; i += 1) {
            int type = Integer.decode(str[i]);
            if (type == TemplateType.KILL) {
                KillTemplate2 temp = new KillTemplate2(Integer.decode(str[i + 6 + 1]), str[i + 6 + 2]);
                templates.add(temp);
                temp.setParent(quest);
            }

            if (type == TemplateType.MOVE) {
                MoveTemplate2 temp = new MoveTemplate2();
                templates.add(temp);
                temp.setParent(quest);
            }
        }

        quest.calcQuestType();

        int pos = count * 3 + 6;

        count = Integer.decode(str[pos]);

        MergedPromotion mergedPromotion = new MergedPromotion();

        for (int i = pos; i < count; i += 3) {
            int type = Integer.decode(str[i]);
            if (type == PromotionType.LIFE) {
                LifePromotion promotion = new LifePromotion();
                promotion.setLife(Integer.decode(str[i + 2]));
                mergedPromotion.add(promotion);
            }
            if (type == PromotionType.GOLD) {
                PricePromotion promotion = new PricePromotion();
                promotion.setPrice(Integer.decode(str[i + 2]));
                mergedPromotion.add(promotion);
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
