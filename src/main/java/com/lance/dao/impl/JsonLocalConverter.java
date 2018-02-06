package com.lance.dao.impl;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.lance.dao.JsonConverter;
import com.lance.entities.Category;
import com.lance.entities.EventList;

import java.io.File;
import java.io.IOException;

/**
 * Created by Corwin on 21.05.2015.
 */
public class JsonLocalConverter implements JsonConverter {
    private String category;
    private final static String BASEFOLDER =
            "C:\\WebServers\\home\\192.168.1.82\\www\\";

    public EventList toJavaObject(Category category) throws IOException {
        ObjectMapper mapper = new ObjectMapper();
            return mapper.readValue(new File(BASEFOLDER.concat(category.toString()).concat(".json")), new TypeReference<EventList>() {
                });
    }

    public void toJson(EventList list, Category category) {
        ObjectMapper mapper = new ObjectMapper();
        try {
            ObjectWriter writer = mapper.writerWithDefaultPrettyPrinter();
            writer.writeValue(new File(BASEFOLDER.concat(category.toString()).concat(".json")), list);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
