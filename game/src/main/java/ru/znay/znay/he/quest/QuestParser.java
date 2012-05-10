package ru.znay.znay.he.quest;

import ru.znay.znay.he.messages.Messages;
import ru.znay.znay.he.model.level.Level;
import ru.znay.znay.he.quest.promotion.MergedPromotion;
import ru.znay.znay.he.quest.promotion.PromotionFactory;
import ru.znay.znay.he.quest.template.MergedTemplate;
import ru.znay.znay.he.quest.template.TemplateFactory;
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
@Deprecated
public class QuestParser {

    public List<AbsQuest> parseQuests(List<String> strings) {
        List<AbsQuest> list = new LinkedList<AbsQuest>();

        if (strings == null || strings.isEmpty()) {
            System.out.println("ParseQuest - list is null or empty");
            return list;
        }

        for (String str : strings) {
            if (str.isEmpty()) continue;
            //str = ","+str;
            AbsQuest temp = parseQuest(str.split(","));
            if (temp == null) continue;

            list.add(temp);
        }

        return list;
    }

    private AbsQuest parseQuest(String[] str) {
        if (str == null || str.length == 0) {
            System.out.println("ParseQuest - str is null or empty");
            return null;
        }

        AbsQuest quest = new AbsQuest();
        quest.setId(str[0]);

        quest.setType(str[1].equalsIgnoreCase("true")?0: TemplateType.SHOW_COMPLETE);

        quest.setName(Messages.getInstance().getMessage(Integer.decode(str[2])));
        quest.setDescription(Messages.getInstance().getMessage(Integer.decode(str[3])));

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
            mergedPromotion.add(PromotionFactory.getInstance().createPromotion(str[i], str[i + 1], str[i + 2]));
        }

        quest.setQuestPromotion(mergedPromotion);


        quest.setNextQuestID(Integer.decode(str[4]));

        return quest;
    }
}