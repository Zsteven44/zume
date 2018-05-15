package com.zumepizza.interview.adapters;

import android.app.Activity;
import android.graphics.drawable.Drawable;
import android.support.annotation.NonNull;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.RecyclerView;
import android.text.Spannable;
import android.text.SpannableString;
import android.text.style.ImageSpan;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.squareup.picasso.Picasso;
import com.zumepizza.interview.models.CartItem;
import com.zumepizza.interview.R;
import com.zumepizza.interview.models.ShoppingCart;
import com.zumepizza.interview.models.Item;
import com.zumepizza.interview.models.PizzaItem;

import java.util.List;

public class PizzasAdapter extends RecyclerView.Adapter<PizzasAdapter.PizzaViewHolder> {
    private final Activity activity;
    private List<PizzaItem> pizzaList;

    public void updatePizzaList(List<PizzaItem> pizzaList) {
        this.pizzaList = pizzaList;
        notifyDataSetChanged();
    }

    public PizzasAdapter(Activity activity, @NonNull final List<PizzaItem> pizzaList) {
        this.activity = activity;
        this.pizzaList = pizzaList;
    }

    @Override
    public PizzaViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.row_pizza, parent, false);
        return new PizzaViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final PizzaViewHolder holder, int position) {
        final PizzaItem pizza = pizzaList.get(position);
        //  Set item name + vegetarian icon
        if (pizza.isVegetarian()) {
            SpannableString ss = new SpannableString(pizza.getName()+ " ");
            Drawable d = ContextCompat.getDrawable(activity, R.drawable.leaf);
            d.setBounds(0,0, holder.textName.getLineHeight(),holder.textName.getLineHeight());
            ImageSpan span = new ImageSpan(d, ImageSpan.ALIGN_BOTTOM);
            ss.setSpan(span, ss.length()-1, ss.length(), Spannable.SPAN_INCLUSIVE_EXCLUSIVE);
            holder.textName.setText(ss);
        } else {
            holder.textName.setText(pizza.getName());
        }
        // Set item price
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("$").append(pizza.getPrice());
        holder.textPrice.setText(stringBuilder.toString());
        stringBuilder.delete(0, stringBuilder.length());
        // Set item toppings
        if (pizza.getToppings().length > 0) {
            for (String topping : pizza.getToppings()) {
                stringBuilder.append(topping).append(", ");
            }
            stringBuilder.delete(stringBuilder.length() - 2,stringBuilder.length() - 1);
            holder.textToppings.setText(stringBuilder.toString());
        } else {
            holder.textToppings.setText("");
        }
        Picasso.get().load(pizza.getImageUrl()).into(holder.imagePizza);
        holder.buttonAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                CartItem<Item> item = new CartItem<Item>(pizza);
                ShoppingCart.getInstance().addToCart(item);
                Toast.makeText(activity, "Adding 1 " + pizza.getName() + " to your cart.", Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public int getItemCount() {
        return pizzaList.size();
    }

    public static class PizzaViewHolder extends RecyclerView.ViewHolder {
        ImageView imagePizza;
        TextView textName;
        TextView textToppings;
        TextView textPrice;
        Button buttonAdd;

        public PizzaViewHolder(View itemView) {
            super(itemView);
            this.imagePizza = itemView.findViewById(R.id.imagePizza);
            this.textName= itemView.findViewById(R.id.textName);
            this.textPrice = itemView.findViewById(R.id.textPrice);
            this.textToppings = itemView.findViewById(R.id.textToppings);
            this.buttonAdd = itemView.findViewById(R.id.buttonAdd);
        }
    }
}
