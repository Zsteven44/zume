package com.zumepizza.interview.adapters;

import android.app.Activity;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;
import com.zumepizza.interview.models.CartItem;
import com.zumepizza.interview.R;
import com.zumepizza.interview.models.ShoppingCart;

import java.text.NumberFormat;
import java.util.List;
import java.util.Locale;

public class CartAdapter extends RecyclerView.Adapter<CartAdapter.CartViewHolder> {
    private final Activity activity;

    public CartAdapter(@NonNull final Activity activity) {
        this.activity = activity;
    }

    @Override
    public CartViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.row_cart_item, parent, false);
        return new CartViewHolder(view);
    }

    @Override
    public void onBindViewHolder(CartViewHolder holder, int position) {
        final List<CartItem> cartItems = ShoppingCart.getInstance().getCartItems();
        final CartItem cartItem = cartItems.get(position);
        Picasso.get().load(cartItem.getUrl()).into(holder.imagePizza);
        holder.textName.setText(cartItem.getName());
        holder.textQuantity.setText("x"+Integer.toString(cartItem.getQuantity()));
        holder.textPrice.setText(priceToString(cartItem.getQuantity()*Float.parseFloat(cartItem.getPrice())));
        holder.buttonAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ShoppingCart.getInstance().addToCart(cartItem);
                notifyDataSetChanged();
            }
        });
        holder.buttonSubtract.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ShoppingCart.getInstance().decrementCartItem(cartItem);
                notifyDataSetChanged();
            }
        });
        holder.buttonRemove.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ShoppingCart.getInstance().removeCartItem(cartItem);
                notifyDataSetChanged();
            }
        });

    }

    @Override
    public int getItemCount() {
        return ShoppingCart.getInstance().getCartItems().size();
    }

    public static class CartViewHolder extends RecyclerView.ViewHolder {
        ImageView imagePizza;
        TextView textName;
        TextView textPrice;
        TextView textQuantity;
        Button buttonSubtract;
        Button buttonAdd;
        Button buttonRemove;

        public CartViewHolder(View itemView) {
            super(itemView);
            this.imagePizza = itemView.findViewById(R.id.imageItemImage);
            this.textName = itemView.findViewById(R.id.textItemName);
            this.textPrice = itemView.findViewById(R.id.textPrice);
            this.textQuantity = itemView.findViewById(R.id.textQuantity);
            this.buttonSubtract = itemView.findViewById(R.id.buttonSubtractButton);
            this.buttonAdd = itemView.findViewById(R.id.buttonAddButton);
            this.buttonRemove = itemView.findViewById(R.id.buttonRemoveItem);
        }
    }


    // Move this to utils
    public String priceToString(float value) {
        NumberFormat numberFormat = NumberFormat.getCurrencyInstance(Locale.US);
        return numberFormat.format(value);
    }

}
