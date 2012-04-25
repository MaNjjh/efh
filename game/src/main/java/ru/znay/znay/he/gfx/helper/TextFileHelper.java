package ru.znay.znay.he.gfx.helper;

import ru.znay.znay.he.model.level.Level;
import ru.znay.znay.he.quest.AbsQuest;
import ru.znay.znay.he.quest.promotion.ItemPromotion;
import ru.znay.znay.he.quest.promotion.LifePromotion;
import ru.znay.znay.he.quest.promotion.MergedPromotion;
import ru.znay.znay.he.quest.promotion.PricePromotion;
import ru.znay.znay.he.quest.template.KillTemplate2;
import ru.znay.znay.he.quest.template.MergedTemplate;
import ru.znay.znay.he.quest.template.MoveTemplate2;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 * Created by IntelliJ IDEA.
 * User: Денис Сергеевич
 * Date: 15.04.12
 * Time: 19:01
 * To change this template use File | Settings | File Templates.
 */
public class TextFileHelper {

    public static List<String> LoadMessages(int level) {
        InputStreamReader in = null;
        BufferedReader reader = null;
        try {
            in = new InputStreamReader(TextFileHelper.class.getResourceAsStream("/messages/" + level + ".txt"), Charset.defaultCharset());
            reader = new BufferedReader(in);
            List<String> messages = new ArrayList<String>();
            String buff;
            while ((buff = reader.readLine()) != null) {
                buff = buff.substring(1);
                int index = buff.indexOf("//");
                messages.add((index == -1) ? buff : buff.substring(0, index));
            }
            return messages;
        } catch (Exception e) {
            return null;
        } finally {
            if (in != null) {
                try {
                    in.close();
                } catch (IOException e) {
                    //ignored
                }
            }
            if (reader != null) {
                try {
                    reader.close();
                } catch (IOException e) {
                    //ignored
                }
            }
        }
    }

    public static List<String> LoadTextDB(String fileName) {
        List<String> quests = new LinkedList<String>();
        InputStreamReader in = null;
        BufferedReader reader = null;

        try {
            in = new InputStreamReader(TextFileHelper.class.getResourceAsStream("/quests/" + fileName), Charset.defaultCharset());
            reader = new BufferedReader(in);
            String buff;
            while ((buff = reader.readLine()) != null) {
                buff = buff.substring(1);
                int index = buff.indexOf("//");
                quests.add((index == -1) ? buff : buff.substring(0, index));
            }
            return quests;
        } catch (Exception e) {
            return null;
        } finally {
            if (in != null) {
                try {
                    in.close();
                } catch (IOException e) {
                    //ignored
                }
            }
            if (reader != null) {
                try {
                    reader.close();
                } catch (IOException e) {
                    //ignored
                }
            }
        }
    }

    public static List<AbsQuest> ParseQuest(List<String> strings, Level level) {
        if (strings == null || strings.isEmpty()) {
            System.out.println("ParseQuest - list is null or empty");
            return null;
        }

        List<AbsQuest> list = new LinkedList<AbsQuest>();

        for (String str : strings) {
            if (str.isEmpty()) continue;
            //str = ","+str;
            AbsQuest temp = ParseQuest(str.split(","), level);
            if (temp == null) continue;

            list.add(temp);
        }

        return list;
    }

    private static AbsQuest ParseQuest(String[] str, Level level) {
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
            if (str[i].equals("1")) {
                KillTemplate2 temp = new KillTemplate2(Integer.decode(str[i + 6 + 1]), str[i + 6 + 2]);
                templates.add(temp);
                temp.setParent(quest);
            }

            if (str[i].equals("2")) {
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
            if (str[i].equals("1")) {
                LifePromotion promotion = new LifePromotion();
                promotion.setLife(Integer.decode(str[i + 2]));
                mergedPromotion.add(promotion);
            }
            if (str[i].equals("2")) {
                PricePromotion promotion = new PricePromotion();
                promotion.setPrice(Integer.decode(str[i + 2]));
                mergedPromotion.add(promotion);
            }

            if (str[i].equals("4")) {
                ItemPromotion promotion = new ItemPromotion();
                promotion.setItemName(str[i + 1]);
                promotion.setCount(Integer.decode(str[i + 2]));
            }
        }

        quest.setQuestPromotion(mergedPromotion);


        quest.setNextQuestID(Integer.decode(str[3]));

        return quest;
    }
}
