package ru.znay.znay.he.gfx.helper;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.InputStreamReader;
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
            InputStreamReader in = new InputStreamReader(TextFileHelper.class.getResourceAsStream("/messages/" + level + ".txt"));
            BufferedReader reader = new BufferedReader(in);
            List<String> messages = new ArrayList<String>();
            String buff;
            while ((buff = reader.readLine()) != null) {
                buff = buff.substring(1);
                int index = buff.indexOf("//");
                messages.add((index == -1) ? buff : buff.substring(0, index));
            }
            reader.close();
            in.close();
            return messages;
        } catch (Exception e) {
            return null;
        }
    }
}
