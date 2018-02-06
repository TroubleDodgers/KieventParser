package com.lance.dao;

import com.lance.entities.Category;
import com.lance.entities.EventList;

import java.io.IOException;

/**
 * Created by Corwin on 05.02.2018.
 */

public interface JsonConverter {
    EventList toJavaObject(Category category) throws IOException;
    void toJson(EventList list, Category category);
}
