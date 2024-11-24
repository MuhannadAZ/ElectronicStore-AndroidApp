package com.example.proj;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import java.util.List;

public class CartAdapter extends RecyclerView.Adapter<CartAdapter.CartViewHolder> {

    private Context context;
    private List<Product> cartItems;
    private CartUpdateListener cartUpdateListener;

    public CartAdapter(Context context, List<Product> cartItems, CartUpdateListener listener) {
        this.context = context;
        this.cartItems = cartItems;
        this.cartUpdateListener = listener;
    }

    @NonNull
    @Override
    public CartViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.cart_item, parent, false);
        return new CartViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CartViewHolder holder, @SuppressLint("RecyclerView") int position) {
        Product product = cartItems.get(position);
        holder.productName.setText(product.getName());
        holder.productPrice.setText("$" + product.getTotalPrice());
        holder.productQuantity.setText(String.valueOf(product.getQuantity()));

        // Load image from drawable folder using Glide
        int imageResource = context.getResources().getIdentifier(product.getLogo(), "drawable", context.getPackageName());
        Glide.with(context).load(imageResource).into(holder.productImage);

        // Set click listener for "+" button
        holder.increaseQuantityButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                product.setQuantity(product.getQuantity() + 1);
                holder.productQuantity.setText(String.valueOf(product.getQuantity()));
                holder.productPrice.setText("$" + product.getTotalPrice());

                // Notify fragment to update total cost
                cartUpdateListener.onCartUpdated();
            }
        });

        // Set click listener for "-" button
        holder.decreaseQuantityButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (product.getQuantity() > 1) {
                    product.setQuantity(product.getQuantity() - 1);
                    holder.productQuantity.setText(String.valueOf(product.getQuantity()));
                    holder.productPrice.setText("$" + product.getTotalPrice());

                    // Notify fragment to update total cost
                    cartUpdateListener.onCartUpdated();
                } else {
                    // Remove item when quantity is 1 and "-" is pressed
                    CartManager.getInstance().removeFromCart(product);
                    notifyItemRemoved(position);
                    notifyItemRangeChanged(position, cartItems.size());

                    // Notify fragment to update total cost
                    cartUpdateListener.onCartUpdated();
                }
            }
        });
    }



    @Override
    public int getItemCount() {
        return cartItems.size();
    }

    public static class CartViewHolder extends RecyclerView.ViewHolder {

        TextView productName, productPrice, productQuantity;
        ImageView productImage;
        Button increaseQuantityButton, decreaseQuantityButton;

        public CartViewHolder(@NonNull View itemView) {
            super(itemView);
            productName = itemView.findViewById(R.id.cartProductName);
            productPrice = itemView.findViewById(R.id.cartProductPrice);
            productImage = itemView.findViewById(R.id.cartProductImage);
            productQuantity = itemView.findViewById(R.id.productQuantity);
            increaseQuantityButton = itemView.findViewById(R.id.increaseQuantityButton);
            decreaseQuantityButton = itemView.findViewById(R.id.decreaseQuantityButton);
        }
    }

    public interface CartUpdateListener {
        void onCartUpdated();
    }
}
