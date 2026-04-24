package com.pluralsight;


import java.util.ArrayList;
import java.util.Scanner;

public class OnlineStoreApp
{
    static Scanner userInput = new Scanner(System.in);
    static ArrayList<Product> products;
    static ArrayList<Product> shoppingCart;

    static void main()
    {
        displayHomeScreen();
    }


    static void displayHomeScreen()
    {
        System.out.println();
        System.out.println("Welcome to my top secret store...");
    }

    static ArrayList<Product> loadProducts()
    {
        // created new arraylist
       ArrayList<Product> products = new ArrayList<>();

       // call filereader and buffedreader

       // create seperate array to read .csv


       return products;
    }
}
