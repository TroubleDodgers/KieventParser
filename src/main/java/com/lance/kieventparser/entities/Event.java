package com.lance.kieventparser.entities;


import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Created by Corwin on 13.05.2015.
 */

public class Event {
    @JsonIgnore
    private int id;
    @JsonProperty("Title")
    private String title;
    @JsonProperty("Date")
    private String date;
    @JsonProperty("Time")
    private String time;
    @JsonProperty("Address")
    private String address;
    @JsonProperty("Price")
    private String price;
    @JsonProperty("Image")
    private String image;
    @JsonProperty("Category")
    private Category category;
    @JsonProperty("Order")
    private String order;

    public Event() {

    }

    public Event(String title, String date, String time, String address, String price, String image, Category category) {
        this.id = id;
        this.title = title;
        this.date = date;
        this.time = time;
        this.address = address;
        this.price = price;
        this.image = image;
        this.category = category;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public String getOrder() {
        return order;
    }

    public void setOrder(String order) {
        this.order = order;
    }
}
