package ru.znay.znay.he.gfx.helper;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.ArrayList;
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
        try {
            BufferedReader reader = new BufferedReader(new FileReader(new File(TextFileHelper.class.getResource("/messages/" + level + ".txt").getFile())));
            List<String> messages = new ArrayList<String>();
            String buff;
            while ((buff = reader.readLine()) != null) {
                buff = buff.substring(1);
                int index = buff.indexOf("//");
                messages.add((index == -1) ? buff : buff.substring(0, index));
            }
            reader.close();
            return messages;
        } catch (Exception e) {
            return null;
        }
    }
}
