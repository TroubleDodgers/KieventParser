package com.lance.kieventparser.parsers;

import com.lance.kieventparser.entities.Category;
import com.lance.kieventparser.entities.Event;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.net.URI;
import java.net.URL;
import java.util.ArrayList;
import java.util.Set;
import java.util.TreeSet;

/**
 * Created by Corwin on 14.05.2015.
 */
public class _1001biletParser implements Parser {
    private int idCounter;
    public _1001biletParser() {
        idCounter = 0;
    }
    public ArrayList<Event> parseAll() {
        ArrayList<Event> list = new ArrayList<>();
        list.addAll(parseEstrade());
        list.addAll(parseRock());
        list.addAll(parseBallet());
        list.addAll(parseSpectacle());
        list.addAll(parseClub());
        list.addAll(parseHumour());
        list.addAll(parseDolphin());
        return list;
    }

    public ArrayList<Event> parseEstrade() {
        ArrayList<Event> list = parsePage("http://1001bilet.ua/estrada.html");
        for(Event e: list) {
            e.setCategory(Category.ESTRADE);
        }
        return list;
    }

    public ArrayList<Event> parseRock() {
        ArrayList<Event> list = parsePage("http://1001bilet.ua/rock-metal.html");
        for(Event e: list) {
            e.setCategory(Category.ROCK);
        }
        return list;
    }

    public ArrayList<Event> parseBallet() {
        ArrayList<Event> list = parsePage("http://1001bilet.ua/balet.html");
        for(Event e: list) {
            e.setCategory(Category.BALLET);
        }
        return list;
    }

    public ArrayList<Event> parseSpectacle() {
        ArrayList<Event> list = parsePage("http://1001bilet.ua/teatr.html");
        for(Event e: list) {
            e.setCategory(Category.SPECTACLE);
        }
        return list;
    }

    public ArrayList<Event> parseClub() {
        ArrayList<Event> list = parsePage("http://1001bilet.ua/klubnaya-musika.html");
        for(Event e: list) {
            e.setCategory(Category.CLUB);
        }
        return list;
    }

    public ArrayList<Event> parseHumour() {
        ArrayList<Event> list = parsePage("https://1001bilet.ua/umor-kvn.html");
        for(Event e: list) {
            e.setCategory(Category.HUMOUR);
        }
        return list;
    }

    public ArrayList<Event> parseDolphin() {
        ArrayList<Event> list = parsePage("https://1001bilet.ua/delfinariy_nemo_kiev.html");
        for(Event e: list) {
            e.setCategory(Category.DOLPHIN);
        }
        return list;
    }

    public ArrayList<Event> parsePage(String url) {
        Document doc;
        final String DATEREGEX = "[0-9]{2}\\.[0-9]{2}\\.[0-9]{4}";
        final String TIMEREGEX = "[0-9]{2}\\:[0-9]{2}";
        Event newItem;
        Elements eventBody;
        Elements img;
        Elements title;
        Elements date;
        Elements time;
        Elements address;
        Elements price;
        Elements order;
        Elements id;
        ArrayList<Event> titleList = new ArrayList<>();
        try {
            doc = Jsoup.connect(url).get();
            eventBody = doc.select("div.info");
            title = doc.select("a[title=На страницу события]");
            title.addAll(doc.select("a[class=title]"));
            date = eventBody.select("div.date");
            time = eventBody.select("div[class=time]");
            address = eventBody.select("a.place.fn.org");
            price = eventBody.select("div[class=price]");
            img = doc.select("a[class=photo] img[src]");
            order = doc.select("a.order");


            titleList.clear();
            for (int i = 0; i < eventBody.size(); i++) {
                newItem = new Event();
                String smallImgSrc = "https://1001bilet.ua/"+img.get(i).attr("src");
                String imgSrc = smallImgSrc.replace("s_", "");
                newItem.setId(idCounter);
                newItem.setTitle(title.get(i).text());
                String[] dateSplit = date.get(i).text().split(" ");
                Set<String> dateCollection = new TreeSet<>();
                StringBuilder dateBuilder = new StringBuilder();
                StringBuilder timeBuilder = new StringBuilder();
                Set<String> timeCollection = new TreeSet<>();
                for(String s: dateSplit) {
                    if(s.matches(DATEREGEX))
                        dateCollection.add(s);
                    if(s.matches(TIMEREGEX))
                        timeCollection.add(s);
                }
                timeCollection.add(time.get(i).text());
                for(String s: dateCollection)
                    dateBuilder.append(s).append(" ");
                for(String s: timeCollection)
                    timeBuilder.append(s).append(" ");
                newItem.setDate(dateBuilder.toString());
                newItem.setTime(timeBuilder.toString());
                newItem.setAddress(address.get(i).text());
                newItem.setPrice(price.get(i).text());
                newItem.setImage(imgSrc);
                String orderStr = "https://1001bilet.ua/"+order.get(i).attr("href");
                newItem.setOrder(orderStr);
                titleList.add(newItem);
                idCounter++;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return titleList;
    }
}
