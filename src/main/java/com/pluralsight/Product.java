package com.pluralsight;

public class Product
{
    private String sku;
    private String productName;
    private double price;
    private String department;


    // constructor

    public Product(String sku, String productName, double price, String department)
    {
        this.sku = sku;
        this.productName = productName;
        this.price = price;
        this.department = department;
    }

    // getters
    public String getSku() {
        return sku;
    }

    public String getProductName() {
        return productName;
    }

    public double getPrice() {
        return price;
    }

    public String getDepartment() {
        return department;
    }
}
