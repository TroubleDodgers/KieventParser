package com.lance.entities;

import com.fasterxml.jackson.annotation.JsonCreator;

import java.util.ArrayList;

/**
 * Created by Corwin on 24.05.2015.
 */
public class EventList {
    @JsonCreator
    public EventList() {}


    public ArrayList<Event> events = new ArrayList<>();
}
