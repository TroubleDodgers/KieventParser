package com.lance.logic;

import com.lance.dao.JsonConverter;
import com.lance.dao.impl.JsonRemoteConverter;
import com.lance.entities.Category;
import com.lance.entities.EventList;
import com.lance.parsers._1001biletParser;

import java.io.IOException;

/**
 * Created by Corwin on 19.05.2015.
 */
public class Main {
    public static void main(String[] args) throws IOException {
        htmlToJson();
    }
	
	private static void htmlToJson() {
		_1001biletParser parser = new _1001biletParser();
		JsonConverter jsonConverter = new JsonRemoteConverter();
        EventList events = new EventList();
        events.events = parser.parseEstrade();
        jsonConverter.toJson(events, Category.ESTRADE);
        events.events = parser.parseRock();
        jsonConverter.toJson(events, Category.ROCK);
        events.events = parser.parseBallet();
        jsonConverter.toJson(events, Category.BALLET);
        events.events = parser.parseClub();
        jsonConverter.toJson(events, Category.CLUB);
        events.events = parser.parseDolphin();
        jsonConverter.toJson(events, Category.DOLPHIN);
        events.events = parser.parseHumour();
        jsonConverter.toJson(events, Category.HUMOUR);
        events.events = parser.parseSpectacle();
        jsonConverter.toJson(events, Category.SPECTACLE);
	}
}
