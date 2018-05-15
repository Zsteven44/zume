package com.zumepizza.interview.models;

import java.util.Arrays;

public abstract class Item {
    protected String name;
    protected String price;
    protected String imageUrl;
    protected int id;

    public Item() {

    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getImageUrl() {
        return imageUrl != null ? imageUrl : "";
    }

    public String getName() {
        return name != null ? name : "";
    }

    public String getPrice() {
        return price != null ? price: "";
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setPrice(String price) {
        this.price = price;
    }


    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }


    @Override
    public String toString() {
        return "PizzaItem{" +
                "name='" + name + '\'' +
                ", price='" + price + '\'' +
                ", imageUrl='" + imageUrl + '\'' +
                ", id=" + id +
                '}';
    }
}
