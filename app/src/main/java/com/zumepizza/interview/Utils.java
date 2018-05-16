package com.zumepizza.interview;

import android.util.Log;

import com.zumepizza.interview.models.PizzaItem;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.text.NumberFormat;
import java.util.Locale;

public class Utils {

    public static String priceToString(float value) {
        NumberFormat numberFormat = NumberFormat.getCurrencyInstance(Locale.US);
        return numberFormat.format(value);
    }

    public static PizzaItem mapPizza(JSONObject object) {
        PizzaItem pizza = new PizzaItem();
        try{
            pizza.setId(object.optInt("id", 0));
            pizza.setName(object.optString("name", "N.A."));
            pizza.setPrice(object.optString("price", "N.A."));
            pizza.setImageUrl(object.getJSONObject("menuAsset").optString("url"));
            JSONArray toppings = object.optJSONArray("toppings");
            String[] toppingsArray = new String[toppings.length()];
            for (int i = 0 ; i < toppings.length(); i++) {
                toppingsArray[i] = toppings.getJSONObject(i).optString("name");
            }
            pizza.setToppings(toppingsArray);
            pizza.setVegetarian(object.optInt("vegetarian", 0) == 1);
        } catch(JSONException e) {
            e.printStackTrace();
        }
        return pizza;
    }
}
