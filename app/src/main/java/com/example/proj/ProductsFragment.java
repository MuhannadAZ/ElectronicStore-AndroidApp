package com.example.proj;

import android.annotation.SuppressLint;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Spinner;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class ProductsFragment extends Fragment implements ProductAdapter.OnProductClickListener {

    private DB_sqlite dbHelper;
    private RecyclerView recyclerView;
    private ProductAdapter productAdapter;
    private List<Product> productList;
    private Spinner categorySpinner;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_products, container, false);

        // Initialize RecyclerView and Spinner
        recyclerView = view.findViewById(R.id.recyclerViewProducts);
        GridLayoutManager gridLayoutManager = new GridLayoutManager(getContext(), 2);
        recyclerView.setLayoutManager(gridLayoutManager);
        categorySpinner = view.findViewById(R.id.categorySpinner);

        productList = new ArrayList<>();
        productList = new ArrayList<>();

        // Initialize database helper
        dbHelper = new DB_sqlite(getContext());

        // Load all products by default
        loadAllProducts();

        // Set Spinner listener for category selection
        categorySpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                // Get the selected category from the Spinner
                String selectedCategory = parent.getItemAtPosition(position).toString();

                if (selectedCategory.equals("All Categories")) {
                    // Display all products if "All Categories" is selected
                    loadAllProducts();
                } else {
                    // Filter by selected category
                    loadProductsByCategory(selectedCategory);
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                // Do nothing
            }
        });

        return view;
    }

    // Load all products from the database
    private void loadAllProducts() {
        productList.clear();

        SQLiteDatabase db = dbHelper.getReadableDatabase();
        String query = "SELECT item.*, IFNULL(AVG(review.rating), 0) as avg_rating " +
                "FROM item LEFT JOIN review ON item.ID = review.item_id " +
                "GROUP BY item.ID";

        Cursor cursor = db.rawQuery(query, null);

        if (cursor.moveToFirst()) {
            do {
                @SuppressLint("Range") String productName = cursor.getString(cursor.getColumnIndex("name"));
                @SuppressLint("Range") String productDescription = cursor.getString(cursor.getColumnIndex("description"));
                @SuppressLint("Range") double productPrice = cursor.getDouble(cursor.getColumnIndex("price"));
                @SuppressLint("Range") String productLogo = cursor.getString(cursor.getColumnIndex("logo"));
                @SuppressLint("Range") float avgRating = cursor.getFloat(cursor.getColumnIndex("avg_rating")); // Get average rating

                // Add product to list with the average rating
                productList.add(new Product(productName, productDescription, productPrice, productLogo, avgRating));

            } while (cursor.moveToNext());
        }

        cursor.close();

        // Set the adapter with the full product list
        productAdapter = new ProductAdapter(getContext(), productList, this); // Pass the click listener
        recyclerView.setAdapter(productAdapter);
    }

    // Load products based on the selected category
    private void loadProductsByCategory(String categoryName) {
        productList.clear();

        int categoryID = getCategoryIDByName(categoryName);

        SQLiteDatabase db = dbHelper.getReadableDatabase();
        String query = "SELECT item.*, IFNULL(AVG(review.rating), 0) as avg_rating " +
                "FROM item LEFT JOIN review ON item.ID = review.item_id " +
                "WHERE categoryID = ? GROUP BY item.ID";

        Cursor cursor = db.rawQuery(query, new String[]{String.valueOf(categoryID)});

        if (cursor.moveToFirst()) {
            do {
                @SuppressLint("Range") String productName = cursor.getString(cursor.getColumnIndex("name"));
                @SuppressLint("Range") String productDescription = cursor.getString(cursor.getColumnIndex("description"));
                @SuppressLint("Range") double productPrice = cursor.getDouble(cursor.getColumnIndex("price"));
                @SuppressLint("Range") String productLogo = cursor.getString(cursor.getColumnIndex("logo"));
                @SuppressLint("Range") float avgRating = cursor.getFloat(cursor.getColumnIndex("avg_rating"));

                // Add product to list with the average rating
                productList.add(new Product(productName, productDescription, productPrice, productLogo, avgRating));

            } while (cursor.moveToNext());
        }

        cursor.close();

        // Set the adapter with the filtered product list
        productAdapter = new ProductAdapter(getContext(), productList, this); // Pass the click listener
        recyclerView.setAdapter(productAdapter);
    }

    // Helper method to get category ID by name
    @SuppressLint("Range")
    private int getCategoryIDByName(String categoryName) {
        SQLiteDatabase db = dbHelper.getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT ID FROM category WHERE name = ?", new String[]{categoryName});

        int categoryID = -1;
        if (cursor.moveToFirst()) {
            categoryID = cursor.getInt(cursor.getColumnIndex("ID"));
        }

        cursor.close();
        return categoryID;
    }

    @Override
    public void onProductClick(int position) {
        // Get the clicked product
        Product clickedProduct = productList.get(position);

        // Create the ProductDetailFragment
        ProductDetailFragment productDetailFragment = new ProductDetailFragment();

        // Pass the product data using a Bundle
        Bundle bundle = new Bundle();
        bundle.putString("productName", clickedProduct.getName());
        bundle.putString("productDescription", clickedProduct.getDescription());
        bundle.putDouble("productPrice", clickedProduct.getPrice());
        bundle.putString("productLogo", clickedProduct.getLogo());
        bundle.putFloat("productRating", clickedProduct.getRating()); // Pass the rating
        productDetailFragment.setArguments(bundle);

        // Replace the current fragment with the ProductDetailFragment
        getActivity().getSupportFragmentManager().beginTransaction()
                .replace(R.id.fragment_container, productDetailFragment)
                .addToBackStack(null)
                .commit();
    }
}