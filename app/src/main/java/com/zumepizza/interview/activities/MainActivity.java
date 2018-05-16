package com.zumepizza.interview.activities;

import android.os.Bundle;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;

import com.zumepizza.interview.fragments.PizzasFragment;
import com.zumepizza.interview.R;
import com.zumepizza.interview.fragments.BaseFragment;

/*
 Exercise instructions

 1) Fetch menu data using the URL given in API.java.
 2) Use fetched data to instantiate product model objects.
 2) Using product models, populate a list view using designs found in mocks/menu-mock-up.png
 3) Implement cart functionality, to allow users to add/remove items to their cart.
 4) Add cart activity to display items added to cart. Use designs found in mocks/cart-mock-up.png

    MainActivity features basic fragment inflation.  I started off with fragment for this activity,
    but in reality it is overkill for the features asked for.
 */

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        if (findViewById(R.id.main_container) != null) {
            if (savedInstanceState != null) {
                return;
            }
            PizzasFragment frag = PizzasFragment.newInstance();
            frag.setArguments(getIntent().getExtras());
            changeFragment(frag, false);
        }
    }



    public void changeFragment(BaseFragment fragment, boolean addToBackStack) {
        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        fragmentTransaction.replace(R.id.main_container, fragment);
        if (addToBackStack) fragmentTransaction.addToBackStack(null);
        fragmentTransaction.commit();
    }


}
