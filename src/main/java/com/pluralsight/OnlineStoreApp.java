package com.pluralsight;


import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

public class OnlineStoreApp
{
    static Scanner userInput = new Scanner(System.in);
    static ArrayList<Product> products;
    static HashMap<Product, Integer> shoppingCart = new HashMap<>();



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
        System.out.print("Make a choice: ");
        String choice = userInput.nextLine().toUpperCase().strip();

        System.out.println();

        switch (choice)
        {
            case "D":
                displayProductSearch();
                break;

            case "C":
                System.out.println("test 2");
                break;

            case "X":
                System.out.println("Goodbye");
                return;

            default:
                System.out.println("Error, please try again");

        }
    }



    static void displayProductSearch()
    {
        while (true)
        {
            System.out.println();
            System.out.println("What do you want to do? ");
            System.out.println("1) Search product by name");
            System.out.println("2) Search product SKU");
            // System.out.println("3) check out");
            System.out.println("X) Exit");
            System.out.print("Make your selection: ");
            String selection = userInput.nextLine().toUpperCase().strip();

            System.out.println();


            Product product;

            switch (selection)
            {
                case "1":
                    System.out.print("Enter product name to search for: ");
                    String userSearch = userInput.nextLine().strip();

                    product = findProductByName(userSearch);
                    System.out.printf("%-20s | $%,.2f%n", product.getProductName(), product.getPrice());
                    System.out.println();
                    break;

                case "2":
                    System.out.print("Enter SKU to search for: ");
                    String userSearch2 = userInput.nextLine().strip();

                    product = findProductBySKU(userSearch2);

                    System.out.printf("%-20s | $%,.2f%n", product.getProductName(), product.getPrice());
                    System.out.println();
                    break;

                case "X":
                    System.out.print("Goodbye");
                    return;

                default:
                    System.out.println("Error, please try again");
                    continue;
            }
        }

    }



    public static Product findProductByName(String productName)
    {
      for (Product product : products)
      {
          if (product.getProductName().toLowerCase().contains(productName.toLowerCase()))
          {
              System.out.println();
              return product;
          }
      }
      return null;
    }



    public static Product findProductBySKU(String sku)
    {
        for (Product product : products)
        {
            if (product.getSku().toLowerCase().contains(sku.toLowerCase()))
            {
                System.out.println();
                return product;
            }
        }
        return null;
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

       return products;
    }
}
