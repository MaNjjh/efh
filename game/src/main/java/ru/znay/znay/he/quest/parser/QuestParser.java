package ru.znay.znay.he.quest.parser;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;
import org.xmlpull.v1.XmlPullParserFactory;

import java.io.IOException;
import java.io.InputStream;

/**
 * Created by IntelliJ IDEA.
 * User: Александр Сергеевич
 * Date: 06.04.12
 * Time: 22:09
 * To change this template use File | Settings | File Templates.
 */
public class QuestParser {

    private static QuestParser instance = new QuestParser();

    private XmlPullParserFactory xmlPullParserFactory;

    public static QuestParser getInstance() {
        return instance;
    }

    private QuestParser() {
        try {
            this.xmlPullParserFactory = XmlPullParserFactory.newInstance();
        } catch (XmlPullParserException e) {
            throw new RuntimeException(e);
        }
    }

    public QuestWrapper parseQuest(InputStream inputStream) {
        try {
            XmlPullParser parser = xmlPullParserFactory.newPullParser();
            parser.setInput(inputStream, "UTF-8");
            int eventType = parser.getEventType();

            do {
                String tagName = parser.getName();
                if (eventType == XmlPullParser.START_TAG) {

                    if ("song".equals(tagName)) {

                    }
                }
                eventType = parser.next();
            } while (eventType != XmlPullParser.END_DOCUMENT);
            return new QuestWrapper();
        } catch (XmlPullParserException e) {
            return null;

        } catch (IOException e) {
            return null;
        }

    }

    public static class QuestWrapper {
        private String name;
        private String description;
        private String template;
        private String promotion;

    }
}
