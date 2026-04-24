package com.pluralsight;


import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Scanner;

public class OnlineStoreApp
{
    static Scanner userInput = new Scanner(System.in);
    static ArrayList<Product> products;
    static ArrayList<Product> shoppingCart;

    static void main()
    {
        products = loadProducts();

        displayHomeScreen();
    }



    static void displayHomeScreen()
    {
        System.out.println();
        System.out.println("Welcome to my top secret store...");
        System.out.println("---------------------------------");
        System.out.println();
        System.out.println("D) Display Products");
        System.out.println("C) Display Cart");
        System.out.println("X) Exit");
        System.out.println();
        System.out.print("Make a selection: ");
        String choice = userInput.nextLine().toUpperCase().strip();

        System.out.println();

        switch (choice)
        {
            case "D":
                System.out.println("test");
                break;

            case "C":
                System.out.println("test 2");
                break;

            case "X":
                System.out.println("test 3");
                return;
            default:
                System.out.println("error");

        }
    }



    static ArrayList<Product> loadProducts()
    {
      // created new arraylist
      ArrayList<Product> products = new ArrayList<>();

      try
      {
          // call filereader and buffedreader
          FileReader fileReader = new FileReader("products.csv");
          BufferedReader bufferedReader = new BufferedReader(fileReader);

          // skips the header
          String line = bufferedReader.readLine();


          while ((line = bufferedReader.readLine()) != null)
          {
              String[] columns = line.split("\\|");
              String sku = columns[0];
              String productName = columns[1];
              double price = Double.parseDouble(columns[2]);
              String department = columns[3];

              // creates product object
              Product product = new Product(sku, productName, price, department);

              // adds the product into the array list
              products.add(product);

          }

          // close the file
          bufferedReader.close();

      }
      catch (Exception e)
      {
          System.out.println("Error reading products.csv");
          System.out.println(e.getMessage());
      }

       // create array to read .csv


       return products;
    }
}
