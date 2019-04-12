package com.lance.kieventparser.logic;

import com.lance.kieventparser.dao.JsonConverter;
import com.lance.kieventparser.dao.impl.JsonRemoteConverter;
import com.lance.kieventparser.entities.Category;
import com.lance.kieventparser.entities.Event;
import com.lance.kieventparser.entities.EventList;
import com.lance.kieventparser.parsers._1001biletParser;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

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
        List<Event> events = parser.parseEstrade();
        jsonConverter.toJson(events, Category.ESTRADE);
        events = parser.parseRock();
        jsonConverter.toJson(events, Category.ROCK);
        events = parser.parseBallet();
        jsonConverter.toJson(events, Category.BALLET);
        events = parser.parseClub();
        jsonConverter.toJson(events, Category.CLUB);
        events = parser.parseDolphin();
        jsonConverter.toJson(events, Category.DOLPHIN);
        events = parser.parseHumour();
        jsonConverter.toJson(events, Category.HUMOUR);
        events = parser.parseSpectacle();
        jsonConverter.toJson(events, Category.SPECTACLE);
	}
}
