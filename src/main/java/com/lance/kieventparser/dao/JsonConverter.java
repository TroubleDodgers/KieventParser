package com.lance.kieventparser.dao;

import com.lance.kieventparser.entities.Category;
import com.lance.kieventparser.entities.Event;
import com.lance.kieventparser.entities.EventList;

import java.io.IOException;
import java.util.List;

/**
 * Created by Corwin on 05.02.2018.
 */

public interface JsonConverter {
    void toJson(List<Event> list, Category category);
}
