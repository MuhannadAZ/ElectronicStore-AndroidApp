package com.example.proj;

public class Product {

    private String name;
    private String description;
    private double price;
    private String logo;
    private float rating;
    private int quantity;

    public Product(String name, String description, double price, String logo, float rating) {
        this.name = name;
        this.description = description;
        this.price = price;
        this.logo = logo;
        this.quantity = 1; // Default quantity is 1
        this.rating = rating;
    }

    // Getters and Setters for each field
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public double getPrice() {
        return price;
    }

    public float getRating() {
        return rating;
    }

    public void setRating(float rating) {
        this.rating = rating;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getLogo() {
        return logo;
    }

    public void setLogo(String logo) {
        this.logo = logo;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public double getTotalPrice() {
        return price * quantity;
    }
}
