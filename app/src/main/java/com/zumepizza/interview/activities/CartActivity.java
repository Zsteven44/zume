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

/*
    This activity specifically displays the cart via a recyclerview.  The cart data persists in the
    ShoppingCart singleton.
*/

public class CartActivity extends AppCompatActivity {
    private final String TAG = getClass().getSimpleName();

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
        RecyclerView recyclerView = findViewById(R.id.cartRecyclerView);
        RecyclerView.LayoutManager manager = new LinearLayoutManager(this);
        CartAdapter adapter = new CartAdapter(this);
        recyclerView.setLayoutManager(manager);
        recyclerView.setAdapter(adapter);
    }
}
