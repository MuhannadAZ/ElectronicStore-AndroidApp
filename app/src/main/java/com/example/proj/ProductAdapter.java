package com.example.proj;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import java.util.List;

public class ProductAdapter extends RecyclerView.Adapter<ProductAdapter.ProductViewHolder> {

    private Context context;
    private List<Product> productList;
    private OnProductClickListener productClickListener;

    public ProductAdapter(Context context, List<Product> productList, OnProductClickListener listener) {
        this.context = context;
        this.productList = productList;
        this.productClickListener = listener;
    }

    @NonNull
    @Override
    public ProductViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.product_item, parent, false);
        return new ProductViewHolder(view, productClickListener);
    }

    @Override
    public void onBindViewHolder(@NonNull ProductViewHolder holder, int position) {
        Product product = productList.get(position);
        holder.productName.setText(product.getName());
        holder.productPrice.setText("$" + product.getPrice());

        // Load image from drawable folder using Glide
        int imageResource = context.getResources().getIdentifier(product.getLogo(), "drawable", context.getPackageName());
        Glide.with(context).load(imageResource).into(holder.productImage);

        // Set product rating (assuming product.getRating() returns a float value)
        holder.productRatingBar.setRating(product.getRating()); // Bind rating to RatingBar
    }

    @Override
    public int getItemCount() {
        return productList.size();
    }

    public static class ProductViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        TextView productName, productPrice;
        ImageView productImage;
        RatingBar productRatingBar; // Add RatingBar reference
        OnProductClickListener productClickListener;

        public ProductViewHolder(@NonNull View itemView, OnProductClickListener listener) {
            super(itemView);
            productName = itemView.findViewById(R.id.productName);
            productPrice = itemView.findViewById(R.id.productPrice);
            productImage = itemView.findViewById(R.id.productImage);
            productRatingBar = itemView.findViewById(R.id.productRating); // Initialize RatingBar
            this.productClickListener = listener;

            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            productClickListener.onProductClick(getAdapterPosition());
        }
    }

    public interface OnProductClickListener {
        void onProductClick(int position);
    }
}
