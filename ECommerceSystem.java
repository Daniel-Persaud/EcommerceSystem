import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Random;
import java.util.Scanner;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Map;
import java.util.*;

// Daniel Persaud 501083191
/*
 * Models a simple ECommerce system. Keeps track of products for sale, registered customers, product orders, stats, ratings, customer cart and
 * orders that have been shipped to a customer
 */
public class ECommerceSystem {
  private Map<Integer, Product> products = new HashMap<>(); //stores products
  private ArrayList<Customer> customers = new ArrayList<Customer>();
  private Map<Product, Integer> stats = new HashMap<>(); //stores stats
  private ArrayList<ProductOrder> orders = new ArrayList<ProductOrder>();
  private ArrayList<ProductOrder> shippedOrders = new ArrayList<ProductOrder>();
  private ArrayList<Rating> ratings = new ArrayList<Rating>(); // stores ratings

  // These variables are used to generate order numbers, customer id's, product
  // id's
  private int orderNumber = 500;
  private int customerId = 900;
  private int productId = 700;
  private int count =0; // used as the value in the products hashmap

  // Random number generator
  Random random = new Random();

  public ECommerceSystem() {
    // NOTE: do not modify or add to these objects!! - the TAs will use for testing
    // If you do the class Shoes bonus, you may add shoe products

    

      generateProducts(); // calls file IO method

    // Create some customers. Notice how generateCustomerId() method is used
    customers.add(new Customer(generateCustomerId(), "Inigo Montoya", "1 SwordMaker Lane, Florin"));
    customers.add(new Customer(generateCustomerId(), "Prince Humperdinck", "The Castle, Florin"));
    customers.add(new Customer(generateCustomerId(), "Andy Dufresne", "Shawshank Prison, Maine"));
    customers.add(new Customer(generateCustomerId(), "Ferris Bueller", "4160 Country Club Drive, Long Beach"));
  }

  // creates product objects based on data in products.txt
  private void  generateProducts()
  {
    try 
    {
      File inputFile = new File("/Users/danielpersaud/AssignmentTwo/products.txt");
      Scanner in = new Scanner(inputFile);
      ArrayList<String> lines = new ArrayList<String>();
      String name ="";
      double price =0;
      int stock=0;
      String bookStock="";
      String category = "";
      int paperBackStock =0;
      int hardCoverStock=0;
      String title ="";
      String author ="";
      int year = 0;
    

      // starts reading file
      while (in.hasNextLine())
      {

        category = in.nextLine();

        if (category.equals("BOOKS"))
        {
          name =in.nextLine();
          price = Double.parseDouble(in.nextLine());
          bookStock = in.nextLine();
          paperBackStock = Character.getNumericValue(bookStock.charAt(0));
          hardCoverStock = Character.getNumericValue(bookStock.charAt(2));
          String [] options= in.nextLine().split(":");
          title = options[0];
          author= options[1];
          year =Integer.parseInt(options[2]);

          products.put(count +=1, (new Book(name, generateProductId(), price, paperBackStock, hardCoverStock, title, author, year)));
          
        }

        else
        {
          name =in.nextLine();
          price = Double.parseDouble(in.nextLine());
          stock = Integer.parseInt(in.nextLine());
          in.nextLine();

          products.put(count +=1, (new Product(name, generateProductId(), price, stock, Product.Category.valueOf(category))));

        }
        

        
      }


      in.close();

    }

    catch (FileNotFoundException e)
    {
      System.out.print(e.getMessage());
    }
    
    

  }

  private String generateOrderNumber() {
    return "" + orderNumber++;
  }

  private String generateCustomerId() {
    return "" + customerId++;
  }

  private String generateProductId() {
    return "" + productId++;
  }

  public void printAllProducts() {

    for (Product p : products.values())
      p.print();
  }

  // Print all products that are books. See getCategory() method in class Product
  public void printAllBooks() {
    for (Product p : products.values())
      if (p.getCategory() == Product.Category.BOOKS) {
        p.print();
      }

  }

  // Print all current orders
  public void printAllOrders() {
    for (ProductOrder p : orders)
      p.print();
  }

  // Print all shipped orders
  public void printAllShippedOrders() {
    for (ProductOrder p : shippedOrders)
      p.print();
  }

  // Print all customers
  public void printCustomers() {
    for (Customer c : customers)
      c.print();
  }

  // checks customerId and returns customer object
  public Customer checkCustomer(String customerId) {
    boolean foundC = false;

    for (int i = 0; i < customers.size(); i++) {
      if (customerId.equals(customers.get(i).getId())) {
        return customers.get(i);
      }

    }

    // if cust not found throw exception
    if (foundC == false) {
      throw new UnknownCustomerException("Customer " + customerId + " not found");
    }

    return null;

  }


  // checks product id and returns product
  public Product checkProductId(String productId) {
    boolean foundP = false;

    Collection<Product> values =products.values();

    ArrayList<Product> listOfProducts = new ArrayList<>(values);

    for (int i = 0; i < listOfProducts.size(); i++) {
      if (productId.equals(listOfProducts.get(i).getId())) {
        return listOfProducts.get(i);
      }

    }

    // if product not found throw exception
    if (foundP == false) {
      throw new UnknownProductException("Product " + productId + " not found");
    }

    return null;

  }

  //check product options
  public void checkProductOptions(String productOptions, Product p) {

    if (!(p.validOptions(productOptions))) // maybes type case product as subclass book here
    {

      //if optios not valid throw exception
      throw new InvalidProductOptions(
          "Product " + p.getCategory() + " Product Id: " + productId + " Invalid Options: " + productOptions);
    }

  }

  //checks stock count
  public void checkStockCount(String productOptions, Product p) {

    if ((p.getStockCount(productOptions) <= 0)) {

      //if out of stoc throw exception
      throw new InvalidStockCount("Product not in stock");
    }

  }

    // checks cust name
  public void checkCustName(String name) {
    if (name == null || name.equals("")) {

      //if cust not found throw exception
      throw new InvalidCustName("Invalid Customer Name");
    }
  }

  // checks cust address
  public void checkAdress(String address) {
    if (address == null || address.equals("")) {

      // if address not found throw exception
      throw new InvalidAdress("Invalid adress");
    }
  }

  // checks ordernum returns ordernumber object
  public ProductOrder checkOrderNum(String orderNumber) {

    boolean found = false;

    ProductOrder prodOrd = null;

    for (int i = 0; i < orders.size(); i++) {
      if (orders.get(i).getOrderNumber().equals(orderNumber)) {
        found = true;
        prodOrd = orders.get(i);
        break;

      }
    }

    if (!found) {

      // if ordernumber not found throw exception
      throw new InvalidOrderNumber("Order " + orderNumber + " Not Found");
    }

    return prodOrd;

  }

  /*
   * Given a customer id, print all the current orders and shipped orders for them
   * (if any)
   */
  public void printOrderHistory(String customerId) {
    // Make sure customer exists - check using customerId
    // If customer does not exist, set errMsg String and return false
    // see video for an appropriate error message string
    // ... code here

    Customer c = checkCustomer(customerId);

    // Print current orders of this customer
    System.out.println("Current Orders of Customer " + customerId);
    // enter code here

    for (int i = 0; i < orders.size(); i++) {
      if (orders.get(i).getCustomer().getId().equals(customerId)) {
        orders.get(i).print();
      }
    }

    // Print shipped orders of this customer
    System.out.println("\nShipped Orders of Customer " + customerId);
    // enter code here

    for (int i = 0; i < shippedOrders.size(); i++) {
      if (shippedOrders.get(i).getCustomer().getId().equals(customerId)) {
        shippedOrders.get(i).print();
      }
    }

  }

  public String orderProduct(String productId, String customerId, String productOptions) {
    // First check to see if customer object with customerId exists in array list
    // customers
    // if it does not, set errMsg and return null (see video for appropriate error
    // message string)
    // else get the Customer object

    Customer c = checkCustomer(customerId);

    // Check to see if product object with productId exists in array list of
    // products
    // if it does not, set errMsg and return null (see video for appropriate error
    // message string)
    // else get the Product object

    Product p = checkProductId(productId);

    // Check if the options are valid for this product (e.g. Paperback or Hardcover
    // or EBook for Book product)
    // See class Product and class Book for the method vaidOptions()
    // If options are not valid, set errMsg string and return null;

    if (p.getCategory() == Product.Category.BOOKS) {

      checkProductOptions(productOptions, p);
    }

    else if (p.getCategory() == Product.Category.SHOES) {

      checkProductOptions(productOptions, p);

    }

    // Check if the product has stock available (i.e. not 0)
    // See class Product and class Book for the method getStockCount()
    // If no stock available, set errMsg string and return null

    checkStockCount(productOptions, p);

    // Create a ProductOrder, (make use of generateOrderNumber() method above)
    // reduce stock count of product by 1 (see class Product and class Book)
    // Add to orders list and return order number string

    ProductOrder order = new ProductOrder(generateOrderNumber(), p, c, productOptions);
    p.reduceStockCount(productOptions);
    orders.add(order);
    calculateStatistics(p);
    

    return "#" + order.getOrderNumber();

  }

  //Keeps track of product stats
  public void calculateStatistics(Product p)
  {

    if(stats.containsKey(p)) // checks to see if product has been ordered before
    {
      stats.replace(p, stats.get(p), stats.get(p)+1) ;// if it has add an order to the already existing value
    }

    else
    {
      stats.put(p, 1);
    }
      


  }

  //prints the statics of each product
  public void printStatistics()
  {

    for (Product p : products.values())
    {
      if (stats.containsKey(p))
      {
        System.out.printf("\nName: %-20s Id: %-9s Stats: %-20s ", p.getName(), p.getId(), stats.get(p));
      }

      else
      {
        System.out.printf("\nName: %-20s Id: %-9s Stats: %-20s ", p.getName(), p.getId(), 0);

      }

    }

    
  }

  /*
   * Create a new Customer object and add it to the list of customers
   */

  public void createCustomer(String name, String address) {
    // Check name parameter to make sure it is not null or ""
    // If it is not a valid name, set errMsg (see video) and return false
    // Repeat this check for address parameter

    checkCustName(name);

    checkAdress(address);

    // Create a Customer object and add to array list
    customers.add(new Customer(generateCustomerId(), name, address));

    System.out.print("New customer succesfully added");
  }

  public ProductOrder shipOrder(String orderNumber) {
    // Check if order number exists first. If it doesn't, set errMsg to a message
    // (see video)
    // and return null
    // Retrieve the order from the orders array list, remove it, then add it to the
    // shippedOrders array list
    // return a reference to the order

    ProductOrder validOrderNum = checkOrderNum(orderNumber);

    shippedOrders.add(validOrderNum);
    orders.remove(validOrderNum);

    return validOrderNum;

  }

  /*
   * Cancel a specific order based on order number
   */
  public void cancelOrder(String orderNumber) {
    // Check if order number exists first. If it doesn't, set errMsg to a message
    // (see video)
    // and return false
    ProductOrder validOrderNum = checkOrderNum(orderNumber);
    orders.remove(validOrderNum);

    System.out.println("Order: #" + orderNumber + " has been cancelled ");
  }

  // Sort products by increasing price using bubble sort
  public void sortByPrice() {

    Collection<Product> values =products.values();

    ArrayList<Product> listOfProducts = new ArrayList<>(values);

    for (int i = 0; i < listOfProducts.size(); i++) {
      for (int j = listOfProducts.size() - 1; j > i; j--) {

        if (listOfProducts.get(i).getPrice() > listOfProducts.get(j).getPrice()) {

          Product temp = listOfProducts.get(i);

          listOfProducts.set(i, listOfProducts.get(j));

          listOfProducts.set(j, temp);

        }

      }

    }

    for (Product p : listOfProducts)
    {
      p.print();
    }

  }

  // Sort products alphabetically by product name using comparable
  public void sortByName() {

    Collection<Product> values =products.values();

    ArrayList<Product> listOfProducts = new ArrayList<>(values);

    Collections.sort(listOfProducts);

    for (Product p : listOfProducts)
    {
      p.print();
    }
  }

  // Sort products alphabetically by customer name using comparable
  public void sortCustomersByName() {

    Collections.sort(customers);
  }

  // sorts books by year based on a specific author name
  public void sortByYear(String name) {
    boolean found = false;
    ArrayList<Book> books = new ArrayList<Book>(); // to store all book objects
    ArrayList<Book> booksByAuthor = new ArrayList<Book>(); // stores all book object of valid author

    // stores all book products in books arraylist
    for (Product p : products.values())

    {

      if (p.getCategory() == Product.Category.BOOKS) {
        books.add((Book) p);
      }

    }

    // checks for valid author name
    for (int i = 0; i < books.size(); i++) {

      {

        if (books.get(i).getAuthor().equals(name)) {
          found = true;

        }

      }

    }

    if (found) {
      // stores all book by specific author
      for (int i = 0; i < books.size(); i++) {

        {

          if (name.equals(books.get(i).getAuthor())) {
            booksByAuthor.add(books.get(i));

          }

        }

      }

      // bubble sort for the years of books in booksbyauthor arraylist

      for (int i = 0; i < booksByAuthor.size(); i++) {
        for (int j = booksByAuthor.size() - 1; j > i; j--) {

          if (booksByAuthor.get(i).getYear() > booksByAuthor.get(j).getYear()) {

            Book temp = booksByAuthor.get(i);

            booksByAuthor.set(i, booksByAuthor.get(j));

            booksByAuthor.set(j, temp);

          }

        }

      }

      // prints contents booksbyauthor array list
      for (Book p : booksByAuthor)
        p.print();

    }

    // prints if author name is not found
    else {
      System.out.println("Not a valid author");
    }

  }

  // adds product to customer cart
  public String addToCart(String productId, String customerId, String productOptions) {
    // First check to see if customer object with customerId exists in array list
    // customers
    // if it does not, set errMsg and return null (see video for appropriate error
    // message string)
    // else get the Customer object

    Customer c = checkCustomer(customerId);

    // Check to see if product object with productId exists in array list of
    // products
    // if it does not, set errMsg and return null (see video for appropriate error
    // message string)
    // else get the Product object

    Product p = checkProductId(productId);

    // Check if the options are valid for this product (e.g. Paperback or Hardcover
    // or EBook for Book product)
    // See class Product and class Book for the method vaidOptions()
    // If options are not valid, set errMsg string and return null;

    if (p.getCategory() == Product.Category.BOOKS) {

      checkProductOptions(productOptions, p);
    }

    else if (p.getCategory() == Product.Category.SHOES) {

      checkProductOptions(productOptions, p);

    }

    // Check if the product has stock available (i.e. not 0)
    // See class Product and class Book for the method getStockCount()
    // If no stock available, set errMsg string and return null
    checkStockCount(productOptions, p);

    // if everything works then will add product to customers cart

    c.getCart().addTocart(p, productOptions);

    return "Product added to cart";

  }

  public void printCart(String customerId) {

    // First check to see if customer object with customerId exists in array list
    // customers
    // if it does not, set errMsg and return null (see video for appropriate error
    // message string)
    // else get the Customer object

    Customer c = checkCustomer(customerId);

    c.getCart().printCart();


  }

  // adds product to customer cart
  public String removeItem(String productId, String customerId) {
    // First check to see if customer object with customerId exists in array list
    // customers
    // if it does not, set errMsg and return null (see video for appropriate error
    // message string)
    // else get the Customer object

    Customer c = checkCustomer(customerId);

    // Check to see if product object with productId exists in array list of
    // products
    // if it does not, set errMsg and return null (see video for appropriate error
    // message string)
    // else get the Product object

    Product p = checkProductId(productId);

    c.getCart().remCartItem(productId);

    return "Product removed";

  }

  public String orderItems(String customerId) {
    // First check to see if customer object with customerId exists in array list
    // customers
    // if it does not, set errMsg and return null (see video for appropriate error
    // message string)
    // else get the Customer object

    Customer c = checkCustomer(customerId);

    ArrayList<CartItem> cartItems = new ArrayList<>();

    cartItems.addAll(c.getCart().getCartItems()); // gets the cartItems array and copies it

    // orders items based on category using orderproduct method
    for (int i = 0; i < cartItems.size(); i++) {
      Product currentProd = cartItems.get(i).getProduct();
      String currentItemOptions = cartItems.get(i).getProductOptions();
      String currentProdId = cartItems.get(i).getProduct().getId();

      if (currentProd.getCategory() == Product.Category.BOOKS) {
        System.out.println(orderProduct(currentProd.getId(), customerId, currentItemOptions));
        removeItem(currentProdId, customerId);

      }

      else if (currentProd.getCategory() == Product.Category.SHOES) {
        System.out.println(orderProduct(currentProd.getId(), customerId, currentItemOptions));
        removeItem(currentProdId, customerId);

      }

      else {
        System.out.println(orderProduct(currentProd.getId(), customerId, ""));
        removeItem(currentProdId, customerId);

      }

    }

    return "All items in cart ordered";

  }

  // adds rating for a product
  public void addRating(String r, String productId)
  {


    int rating = Integer.parseInt(r);
    Product p = checkProductId(productId);
    Rating currentRating = new Rating(p, rating, 0);
    boolean foundR = false;
    int x = 0;

    for (int i = 0; i < ratings.size(); i++) {
      if (ratings.get(i).equals(currentRating))
      {
        foundR = true;
        x = i;
        break;
      }
      
    }

    // if rating already exists
    if (foundR)
    {
      ratings.get(x).increaseCount();
    }

    // if rating doesn't exist
    else 
    {
      ratings.add(currentRating);
      currentRating.increaseCount();
    }

  }

  // prints ratings for a product
  public void printRating(String productId)
  {
    Product p = checkProductId(productId);
    for (Rating r : ratings)
    {

      if (r.getProduct().equals(p))
      {
        r.print();

      }
        


    }


  }

  //calculates average rating
  public double calculateAvgRating(Rating r)
  {
    double average=0;
    int count =0;
    ArrayList<Rating> averageRating = new ArrayList<Rating>();

    
    for (int i = 0; i < ratings.size(); i++) 
    {
      if(r.getProduct().equals(ratings.get(i).getProduct()) && (!averageRating.contains(r.getProduct())))
      {
        average +=r.getRating();
        count += r.getCount();
        averageRating.add(r);

        

      }



      
    }

    return average / count;




  }

  
  

  
    // prints product rating by a range made by user
    public void printRatingRange(String category, String ratingRange)
    {



      int range = Integer.parseInt(ratingRange);

      // checks to see if user entered correct category
      try 
      {
        Product.Category cate = Product.Category.valueOf(category.toUpperCase()); // checks to see if category exists
      

      
      
      double average =0;

      for (int i = 0; i < ratings.size(); i++) 
      {


        if ((ratings.get(i).getProduct().getCategory() == cate))
        {
          average = calculateAvgRating(ratings.get(i));

          if (average > range)
          {
            ratings.get(i).getProduct().print();
            System.out.println("Rating"+" " +average);
          }
        }
        
      }
    }



      catch( IllegalArgumentException e)
      {
        System.out.println(e.getMessage());

      }




    

  }




}

// below are exception classes

class UnknownCustomerException extends RuntimeException {
  public UnknownCustomerException() {
  }

  public UnknownCustomerException(String message) {
    super(message);
  }
}

class UnknownProductException extends RuntimeException {
  public UnknownProductException() {
  }

  public UnknownProductException(String message) {
    super(message);
  }
}

class InvalidProductOptions extends RuntimeException {
  public InvalidProductOptions() {
  }

  public InvalidProductOptions(String message) {
    super(message);
  }
}

class InvalidStockCount extends RuntimeException {
  public InvalidStockCount() {
  }

  public InvalidStockCount(String message) {
    super(message);
  }
}

class InvalidCustName extends RuntimeException {
  public InvalidCustName() {
  }

  public InvalidCustName(String message) {
    super(message);
  }
}

class InvalidAdress extends RuntimeException {
  public InvalidAdress() {
  }

  public InvalidAdress(String message) {
    super(message);
  }
}

class InvalidOrderNumber extends RuntimeException {
  public InvalidOrderNumber() {
  }

  public InvalidOrderNumber(String message) {
    super(message);
  }
}

// Object used for rating
class Rating {
  private int rating;
  private int count;
  private Product p;

  public Rating(Product p, int ratings, int count)
  {
    this.rating = ratings;
    this.count = count;
    this.p = p;
  }

  public int getCount()
  {
    return count;
  }

  public Product getProduct()
  {
    return p;
  }

  public int getRating()
  {
    return rating;
  }

  public void increaseCount()
  {
    this.count+=1;
  }

  public void print()
  {


    System.out.printf("\nProduct name: %-20s Rating: %-9s Count: %-20s ", p.getName(), rating, count);
    
  }

  public boolean equals(Object other)
	{
		Rating otherC = (Rating) other;
    if (this.rating == otherC.rating && this.p.equals(otherC.p))
    {
      return true;
    }

		return false;
	}




}
