package com.zumepizza.interview.models;

import android.support.annotation.NonNull;

import com.zumepizza.interview.models.Item;

public class CartItem<K extends Item>  {
    private K item;
    private String url;
    private String name;
    private int quantity;
    private String price;
    private int id;

    public CartItem(@NonNull final K item) {
        this.item = item;
        this.url = item.getImageUrl();
        this.name = item.getName();
        this.price = item.getPrice();
        this.id = item.getId();
    }
    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public K getItem() {
        return item;
    }

    public void setItem(K item) {
        this.item = item;
    }

    public int getId() { return id; }

    public void setId(int id) {
        this.id = id;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    @Override
    public String toString() {
        return "CartItem{" +
                "item=" + item +
                ", url='" + url + '\'' +
                ", name='" + name + '\'' +
                ", quantity=" + quantity +
                ", price=" + price +
                ", id=" + id +
                '}';
    }
}
