package com.lance.kieventparser.tests;

import com.lance.kieventparser.entities.Event;
import com.lance.kieventparser.parsers.Parser;
import com.lance.kieventparser.parsers._1001biletParser;

import java.util.ArrayList;

/**
 * Created by Corwin on 14.05.2015.
 */
public class Test1001Bilet {
    public static void main(String[] args) {
        Parser parser = new _1001biletParser();
        ArrayList<Event> list = parser.parseAll();
        //JsonConverter.toJson(list);
    }
}
