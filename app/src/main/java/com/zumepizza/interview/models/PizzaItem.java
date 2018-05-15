package com.zumepizza.interview.models;

import java.util.Arrays;

public class PizzaItem extends Item{

    private String[] toppings;
    private boolean vegetarian;

    public PizzaItem() {

    }

    public String[] getToppings() {
        return toppings != null ? toppings : new String[0];
    }

    public void setToppings(String[] toppings) {
        this.toppings = toppings;
    }

    public boolean isVegetarian() {
        return vegetarian ;
    }

    public void setVegetarian(boolean vegetarian) {
        this.vegetarian = vegetarian;
    }

    @Override
    public String toString() {
        return "PizzaItem{" +
                "toppings=" + Arrays.toString(toppings) +
                ", vegetarian=" + vegetarian +
                ", name='" + name + '\'' +
                ", price='" + price + '\'' +
                ", imageUrl='" + imageUrl + '\'' +
                ", id=" + id +
                '}';
    }
}
