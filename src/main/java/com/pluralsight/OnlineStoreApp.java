package com.pluralsight;


import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
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
        System.out.println("Welcome to my Store");
        System.out.println("---------------------------------");
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
                displayCart();
                break;

            case "X":
                System.out.println("Goodbye");
                break;

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
            System.out.println("3) Check out");
            System.out.println("C) View Shopping Cart");
            System.out.println("X) Go back to home screen");
            System.out.println();
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

                case "3":
                    System.out.println();
                    System.out.println("Enjoy your goods!!!!!!!!!!!");
                    // clears the hashmap
                    shoppingCart.clear();
                    continue;

                case "C":
                    displayCart();
                    continue;

                case "X":
                    displayHomeScreen();
                    continue;

                default:
                    System.out.println("Error, please try again");
                    continue;
            }
            updateCart(product, 1);
        }

    }



    static void displayCart()
    {
        System.out.println();
        System.out.println("Your Shopping Cart");
        System.out.println("-------------------------------------------");

        // no clue what this for loop does, thank you Gregor
        for (Map.Entry<Product, Integer> row : shoppingCart.entrySet())
        {
            Product lineItem = row.getKey();
            int quantity = row.getValue();
            System.out.println(lineItem.getProductName() + " x" + quantity + " " + lineItem.getPrice() + " each");
        }

        while (true)
        {
            // new menu features for the shopping cart
            System.out.println();
            System.out.println("Would you like to do anything with your shopping cart?");
            System.out.println("1) Remove product");
            System.out.println("2) Check out");
            System.out.println("P) Go back to product search");
            System.out.println();


            System.out.print("Make your selection: ");

            String selection2 = userInput.nextLine().toUpperCase().strip();
            switch (selection2)
            {
                case "1":
                    System.out.print("Enter the product you want to remove from your cart: ");
                    String userRemove = userInput.nextLine().strip();

                    // calls Product back into scope using a new string variable
                    Product productToRemove = null;

                    // p is a temporary variable that only exists inside this loop to make the shoppingCart hashmap work
                    for (Product p : shoppingCart.keySet())
                    {
                        if (p.getProductName().equalsIgnoreCase(userRemove)) {
                            productToRemove = p;
                            break;
                        }
                    }

                    if (productToRemove != null)
                    {
                        updateCart(productToRemove, -1);
                        System.out.println("Removed product " + productToRemove.getProductName());
                    }
                    else
                    {
                        System.out.println("Error, product doesn't exist in your cart");
                    }
                    break;

                case "2":
                    System.out.println();
                    System.out.println("Enjoy your goods!!!!!!!!!!!");
                    // clears the hashmap
                    shoppingCart.clear();
                    return;

                case "P":
                    displayProductSearch();
                    break;

                default:
                    System.out.println("Error, please try again");
            }
        }
    }



    // helper method that handles adding and removing products from the cart
    static void updateCart(Product product, int productQuantity)
    {
        if (shoppingCart.containsKey(product))
        {
            int newQuantity = shoppingCart.get(product) + productQuantity;

            if (newQuantity <= 0)
            {
                shoppingCart.remove(product);
            }
            else
            {
                shoppingCart.put(product, newQuantity);
            }
        }
        else if (productQuantity > 0)
        {
            shoppingCart.put(product, productQuantity);
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
