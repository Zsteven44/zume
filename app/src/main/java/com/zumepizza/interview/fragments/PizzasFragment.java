package com.zumepizza.interview.fragments;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.zumepizza.interview.R;
import com.zumepizza.interview.adapters.PizzasAdapter;
import com.zumepizza.interview.activities.CartActivity;
import com.zumepizza.interview.models.PizzaItem;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class PizzasFragment extends BaseFragment implements API.ResponseHandler {
    private final String TAG = getClass().getSimpleName();
    private PizzasAdapter adapter;
    private List<PizzaItem> pizzaList = new ArrayList<>();

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_pizzas, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        API api = new API(getActivity());
        final RecyclerView recyclerView = (RecyclerView) view.findViewById(R.id.pizzaRecyclerView);
        final RecyclerView.LayoutManager manager = new LinearLayoutManager(getActivity());
        adapter = new PizzasAdapter(getActivity(), pizzaList);
        ImageView shoppingCart = (ImageView) view.findViewById(R.id.cartImageView);
        shoppingCart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(), CartActivity.class);
                startActivity(intent);
            }
        });
        recyclerView.setLayoutManager(manager);
        recyclerView.setAdapter(adapter);
        api.fetchMenu(this);


    }

    public static PizzasFragment newInstance() {
        Bundle args = new Bundle();
        PizzasFragment fragment = new PizzasFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void completion(JSONArray response) {
        Log.e(TAG, response.toString());
        List<PizzaItem> pizzaList = new ArrayList<>();
        for (int i =0 ; i< response.length(); i++){
            try {
                JSONObject object = response.getJSONObject(i);
                Log.e(TAG, object.toString());
                PizzaItem pizza = mapPizza(object);
                pizzaList.add(pizza);
                updatePizzaViews(pizzaList);
            } catch( JSONException e) {
                e.printStackTrace();
            }
        }
    }

    public void updatePizzaViews(List<PizzaItem> pizzaList) {
        adapter.updatePizzaList(pizzaList);
    }

    public PizzaItem mapPizza(JSONObject object) {
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
        Log.e(TAG, pizza.toString());
        return pizza;
    }

}
