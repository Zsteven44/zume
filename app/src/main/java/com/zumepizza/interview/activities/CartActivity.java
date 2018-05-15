package com.zumepizza.interview.activities;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;

import com.zumepizza.interview.R;
import com.zumepizza.interview.models.ShoppingCart;
import com.zumepizza.interview.adapters.CartAdapter;

public class CartActivity extends AppCompatActivity {
    private final String TAG = getClass().getSimpleName();
    private RecyclerView recyclerView;
    private CartAdapter adapter;
    private RecyclerView.LayoutManager manager;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cart);
    }
    @Override
    public View onCreateView(View parent, String name, Context context, AttributeSet attrs) {
        return super.onCreateView(parent, name, context, attrs);

    }

    @Override
    protected void onStart() {
        super.onStart();
        Log.e(TAG, "The cart contents are " + ShoppingCart.getInstance().toString());
        recyclerView = findViewById(R.id.cartRecyclerView);
        manager = new LinearLayoutManager(this);
        adapter= new CartAdapter(this);
        recyclerView.setLayoutManager(manager);
        recyclerView.setAdapter(adapter);
    }
}
