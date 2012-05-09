package ru.znay.znay.he.messages;

import ru.znay.znay.he.gfx.helper.TextFileHelper;

import java.util.LinkedList;
import java.util.List;

/**
 * Created by IntelliJ IDEA.
 * User: Денис Сергеевич
 * Date: 04.05.12
 * Time: 16:34
 * To change this template use File | Settings | File Templates.
 */
public class Messages {
    List<String> messages;

    private static Messages _messages;

    public static Messages getInstance(){
        if (_messages == null){
            _messages = new Messages();
        }

        return _messages;
    }

    private Messages(){
        messages = TextFileHelper.LoadMessages();
    }

    public String getMessage(int index){
        if (messages == null || index < 0 || index >= messages.size()) return "пустое сообщение";

        return messages.get(index);
    }
}
