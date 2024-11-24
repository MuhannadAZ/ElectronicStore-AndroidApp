package com.example.proj;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class CartFragment extends Fragment implements CartAdapter.CartUpdateListener {

    private RecyclerView recyclerView;
    private CartAdapter cartAdapter;
    private TextView totalCostTextView, emptyCartTextView;
    private Button Checkout;
    private Button BackToProducts;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_cart, container, false);

        // Initialize views
        recyclerView = view.findViewById(R.id.recyclerViewCart);
        totalCostTextView = view.findViewById(R.id.totalCostTextView);
        emptyCartTextView = view.findViewById(R.id.tvEmptyCart);
        Checkout = view.findViewById(R.id.Checkout);
        BackToProducts = view.findViewById(R.id.BackToProducts);

        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

        // Get the cart items from CartManager
        List<Product> cartItems = CartManager.getInstance().getCartItems();

        // Check if the cart is empty
        if (cartItems == null || cartItems.isEmpty()) {
            // Show the empty cart message and hide the RecyclerView
            emptyCartTextView.setVisibility(View.VISIBLE);
            recyclerView.setVisibility(View.GONE);
            Checkout.setVisibility(View.GONE);
            totalCostTextView.setVisibility(View.GONE);
            BackToProducts.setVisibility(View.VISIBLE);
        } else {
            // Hide the empty cart message and show the RecyclerView
            emptyCartTextView.setVisibility(View.GONE);
            recyclerView.setVisibility(View.VISIBLE);
            Checkout.setVisibility(View.VISIBLE);
            totalCostTextView.setVisibility(View.VISIBLE);
            BackToProducts.setVisibility(View.GONE);

            // Set up the adapter
            cartAdapter = new CartAdapter(getContext(), cartItems, this);
            recyclerView.setAdapter(cartAdapter);
        }

        // Display total cost
        updateTotalCost();

        BackToProducts.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Navigate to ProductsFragment using MainActivity's fragment manager
                if (getActivity() != null) {
                    getActivity().getSupportFragmentManager().beginTransaction()
                            .replace(R.id.fragment_container, new ProductsFragment())
                            .addToBackStack(null)
                            .commit();
                }
            }
        });

        Checkout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Replace current fragment with the CheckoutFragment
                CheckoutFragment checkoutFragment = new CheckoutFragment();
                getActivity().getSupportFragmentManager().beginTransaction()
                        .replace(R.id.fragment_container, checkoutFragment)
                        .addToBackStack(null)
                        .commit();
            }
        });


        return view;
    }

    @Override
    public void onCartUpdated() {
        // Recalculate and display the updated total cost
        updateTotalCost();

        // Check again if the cart is empty after updates
        List<Product> cartItems = CartManager.getInstance().getCartItems();
        if (cartItems == null || cartItems.isEmpty()) {
            // Show empty message
            emptyCartTextView.setVisibility(View.VISIBLE);
            recyclerView.setVisibility(View.GONE);
            Checkout.setVisibility(View.GONE);
            totalCostTextView.setVisibility(View.GONE);
            BackToProducts.setVisibility(View.VISIBLE);
        } else {
            // Hide empty message and show RecyclerView
            emptyCartTextView.setVisibility(View.GONE);
            recyclerView.setVisibility(View.VISIBLE);
            Checkout.setVisibility(View.VISIBLE);
            totalCostTextView.setVisibility(View.VISIBLE);
            BackToProducts.setVisibility(View.GONE);
        }
    }

    private void updateTotalCost() {
        // Get the total cost from CartManager
        double totalCost = CartManager.getInstance().getTotalCost();
        totalCostTextView.setText("Total Price: $" + totalCost);
    }
}
