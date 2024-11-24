package com.example.proj;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.fragment.app.Fragment;

import com.bumptech.glide.Glide;

public class ProductDetailFragment extends Fragment {

    private ImageView productImageView;
    private TextView productNameTextView, productDescriptionTextView, productPriceTextView;
    private RatingBar productRatingBar; // Reference for RatingBar
    private Button addToCartButton;

    private Product selectedProduct;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_product_detail, container, false);

        // Initialize views
        productImageView = view.findViewById(R.id.productImageView);
        productNameTextView = view.findViewById(R.id.productNameTextView);
        productDescriptionTextView = view.findViewById(R.id.productDescriptionTextView);
        productPriceTextView = view.findViewById(R.id.productPriceTextView);
        productRatingBar = view.findViewById(R.id.productRatingBar); // Initialize RatingBar
        addToCartButton = view.findViewById(R.id.addToCartButton);

        // Get the product data from the bundle (passed from the ProductsFragment)
        Bundle bundle = getArguments();
        if (bundle != null) {
            String productName = bundle.getString("productName");
            String productDescription = bundle.getString("productDescription");
            double productPrice = bundle.getDouble("productPrice");
            String productLogo = bundle.getString("productLogo");
            float productRating = bundle.getFloat("productRating", -1.0f);  // Default to -1.0 to see if it's missing
            if (productRating == -1.0f) {
                Log.e("ProductDetailFragment", "No rating passed to fragment!");
            } else {
                Log.d("ProductDetailFragment", "Rating received: " + productRating);
            }


            // Create a Product object to represent the selected product (with rating)
            selectedProduct = new Product(productName, productDescription, productPrice, productLogo, productRating);

            // Set data in views
            productNameTextView.setText(productName);
            productDescriptionTextView.setText(productDescription);
            productPriceTextView.setText("$" + productPrice);

            // Set the rating in the RatingBar
            productRatingBar.setRating(productRating); // Set the product's rating in RatingBar

            // Load the product image using Glide
            int imageResource = getResources().getIdentifier(productLogo, "drawable", getContext().getPackageName());
            Glide.with(getContext()).load(imageResource).into(productImageView);
        }

        // Set the click listener for the "Add to Cart" button
        addToCartButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Add the product to the cart using CartManager
                CartManager.getInstance().addToCart(selectedProduct);

                // Show confirmation message
                Toast.makeText(getContext(), "Added to Cart", Toast.LENGTH_SHORT).show();
            }
        });

        return view;
    }
}
