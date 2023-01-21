import java.util.ArrayList;
import java.util.Scanner;
import java.util.StringTokenizer;

// Daniel Persaud 501083191

// Simulation of a Simple E-Commerce System (like Amazon)

public class ECommerceUserInterface {
	public static void main(String[] args) {
		// Create the system
		ECommerceSystem amazon = new ECommerceSystem();

		Scanner scanner = new Scanner(System.in);
		System.out.print(">");

		// Process keyboard actions
		while (scanner.hasNextLine()) {
			try {
				String action = scanner.nextLine();

				if (action == null || action.equals("")) {
					System.out.print("\n>");
					continue;
				} else if (action.equalsIgnoreCase("Q") || action.equalsIgnoreCase("QUIT"))
					return;

				else if (action.equalsIgnoreCase("PRODS")) // List all products for sale
				{
					amazon.printAllProducts();
				} else if (action.equalsIgnoreCase("BOOKS")) // List all books for sale
				{
					amazon.printAllBooks();
				} else if (action.equalsIgnoreCase("CUSTS")) // List all registered customers
				{
					amazon.printCustomers();
				} else if (action.equalsIgnoreCase("ORDERS")) // List all current product orders
				{
					amazon.printAllOrders();
				} else if (action.equalsIgnoreCase("SHIPPED")) // List all orders that have been shipped
				{
					amazon.printAllShippedOrders();
				} else if (action.equalsIgnoreCase("NEWCUST")) // Create a new registered customer
				{
					String name = "";
					String address = "";

					System.out.print("Name: ");
					if (scanner.hasNextLine())
						name = scanner.nextLine();

					System.out.print("\nAddress: ");
					if (scanner.hasNextLine())
						address = scanner.nextLine();

					amazon.createCustomer(name, address);
					
					
				} else if (action.equalsIgnoreCase("SHIP")) // ship an order to a customer
				{
					String orderNumber = "";

					System.out.print("Order Number: ");
					// Get order number from scanner
					if (scanner.hasNextLine()) {
						orderNumber = scanner.nextLine();
					}

					ProductOrder o = amazon.shipOrder(orderNumber);
					
					

					// Ship order to customer (see ECommerceSystem for the correct method to use

					o.print();

				} else if (action.equalsIgnoreCase("CUSTORDERS")) // List all the current orders and shipped orders for
																// this customer id
				{
					String customerId = "";

					System.out.print("Customer Id: ");

					// Get customer Id from scanner
					if (scanner.hasNextLine()) {
						customerId = scanner.nextLine();
					}

					// Print all current orders and all shipped orders for this customer

					amazon.printOrderHistory(customerId); 

				

				} else if (action.equalsIgnoreCase("ORDER")) // order a product for a certain customer
				{
					String productId = "";
					String customerId = "";
					String orderNum = "";

					System.out.print("Product Id: ");
					// Get product Id from scanner
					if (scanner.hasNextLine()) {
						productId = scanner.nextLine();
					}

					System.out.print("\nCustomer Id: ");
					// Get customer Id from scanner

					if (scanner.hasNextLine()) {
						customerId = scanner.nextLine();
					}

					// Order the product. Check for valid orderNumber string return and for error
					// message set in ECommerceSystem

					orderNum = amazon.orderProduct(productId, customerId, " ");

					System.out.print(orderNum);

				} else if (action.equalsIgnoreCase("ORDERBOOK")) // order a book for a customer, provide a format
																	// (Paperback, Hardcover or EBook)
				{
					String productId = "";
					String customerId = "";
					String options = "";
					String orderNum = "";

					System.out.print("Product Id: ");
					// get product id

					if (scanner.hasNextLine()) {
						productId = scanner.nextLine();
					}

					System.out.print("\nCustomer Id: ");
					// get customer id

					if (scanner.hasNextLine()) {
						customerId = scanner.nextLine();
					}

					System.out.print("\nFormat [Paperback Hardcover EBook]: ");
					// get book forma and store in options string

					if (scanner.hasNextLine()) {
						options = scanner.nextLine();
					}

					// Order product. Check for error mesage set in ECommerceSystem

					orderNum = amazon.orderProduct(productId, customerId, options);

					System.out.println(orderNum);
					

				} else if (action.equalsIgnoreCase("ORDERSHOES")) // order shoes for a customer, provide size and color
				{
					String productId = "";
					String customerId = "";
					String options = "";
					String orderNum = "";

					System.out.print("Product Id: ");
					// get product id

					if (scanner.hasNextLine()) {
						productId = scanner.nextLine();
					}

					System.out.print("\nCustomer Id: ");
					// get customer id

					if (scanner.hasNextLine()) {
						customerId = scanner.nextLine();
					}

					System.out.print("\nColor: \"Black\" \"Brown\": ");

					// get shoe color and store in options

					if (scanner.hasNextLine()) {
						options = scanner.nextLine();
					}

					System.out.print("\nSize: \"6\" \"7\" \"8\" \"9\" \"10\": ");
					// get shoe size and append to options

					if (scanner.hasNextLine()) {
						options += " " + scanner.nextLine();
					}

					// order shoes
					orderNum = amazon.orderProduct(productId, customerId, options);

					System.out.println(orderNum);
					
				}

				else if (action.equalsIgnoreCase("CANCEL")) // Cancel an existing order
				{
					String orderNumber = "";

					System.out.print("Order Number: ");
					// get order number from scanner

					if (scanner.hasNextLine()) {
						orderNumber = scanner.nextLine();
					}

					// cancel order. Check for error

					amazon.cancelOrder(orderNumber);

					

				} else if (action.equalsIgnoreCase("PRINTBYPRICE")) // sort products by price
				{
					amazon.sortByPrice();
				} else if (action.equalsIgnoreCase("PRINTBYNAME")) // sort products by name (alphabetic)
				{
					amazon.sortByName();
				} else if (action.equalsIgnoreCase("SORTCUSTS")) // sort products by name (alphabetic)
				{
					amazon.sortCustomersByName();
				}

				else if (action.equalsIgnoreCase("BOOKSBYAUTHOR")) // sort products by name (alphabetic)
				{
					String name = "";

					System.out.print("Author Name: ");
					if (scanner.hasNextLine()) {
						name = scanner.nextLine();
					}

					amazon.sortByYear(name);
				}

				else if (action.equalsIgnoreCase("ADDTOCART")) // adds product to cart
				{
					String productId = "";
					String type = "";
					String customerId = "";
					String options = "";

					System.out.print("Product Id: ");
					// get product id

					if (scanner.hasNextLine()) {
						productId = scanner.nextLine();
					}

					System.out.print("\nCustomer Id: ");
					// get customer id

					if (scanner.hasNextLine()) {
						customerId = scanner.nextLine();
					}

					System.out.print("\n[SHOE BOOK OTHER]: ");
					// get customer id

					if (scanner.hasNextLine()) {
						type = scanner.nextLine();
					}

					if (type.toUpperCase().equals("SHOE")) {
						System.out.print("\nColor: \"Black\" \"Brown\": ");

						// get shoe color and store in options

						if (scanner.hasNextLine()) {
							options = scanner.nextLine();
						}

						System.out.print("\nSize: \"6\" \"7\" \"8\" \"9\" \"10\": ");
						// get shoe size and append to options

						if (scanner.hasNextLine()) {
							options += " " + scanner.nextLine();
						}

					}

					else if (type.toUpperCase().equals("BOOK")) {
						System.out.print("\nFormat [Paperback Hardcover EBook]: ");
						// get book format and store in options string

						if (scanner.hasNextLine()) {
							options = scanner.nextLine();
						}

					}

					// need to add call to ecomercesystem here !!!!!

					String added = amazon.addToCart(productId, customerId, options);

					// Print order number string if order number i

					
					System.out.println(added);
					

				}

				else if (action.equalsIgnoreCase("REMCARTITEM")) {
					String productId = "";
					String customerId = "";

					System.out.print("Product Id: ");
					// get product id

					if (scanner.hasNextLine()) {
						productId = scanner.nextLine();
					}

					System.out.print("\nCustomer Id: ");
					// get customer id

					if (scanner.hasNextLine()) {
						customerId = scanner.nextLine();
					}

					System.out.println(amazon.removeItem(productId, customerId));

				}

				else if (action.equalsIgnoreCase("PRINTCART")) {
					String customerId = "";

					System.out.print("\nCustomer Id: ");
					// get customer id

					if (scanner.hasNextLine()) {
						customerId = scanner.nextLine();
					}

					// need to add call to ecomercesystem here !!!!!

					amazon.printCart(customerId);

					

				}

				else if (action.equalsIgnoreCase("ORDERITEMS")) {
					String customerId = "";

					System.out.print("\nCustomer Id: ");
					// get customer id

					if (scanner.hasNextLine()) {
						customerId = scanner.nextLine();
					}

					// Order products. Check for error mesage set in ECommerceSystem

					String result = amazon.orderItems(customerId);

					System.out.println(result);
					

				}

				// prints stats 

				else if (action.equalsIgnoreCase("STATS")) {

					amazon.printStatistics();

				}

				//lets user write review

				else if (action.equalsIgnoreCase("WRITERATING")) {
					String productId = "";
					String rating ="";

					System.out.print("\nProduct Id: ");
					// get product id

					if (scanner.hasNextLine()) {
						productId = scanner.nextLine();
					}

					System.out.print("\nRating [1-5]: ");
					// get rating

					if (scanner.hasNextLine()) {
						rating = scanner.nextLine();
					}


					amazon.addRating(rating, productId);

					
					

				}

				// prints ratings

				else if (action.equalsIgnoreCase("PRINTRATINGS")) {

					String productId = "";

					System.out.print("\nProduct Id: ");
					// get product id

					if (scanner.hasNextLine()) {
						productId = scanner.nextLine();
					}


					amazon.printRating(productId);

				}

				// prints products that within rating range
				else if (action.equalsIgnoreCase("RATINGRANGE")) {

					String ratingRange = "";
					String category ="";

					System.out.print("\nProduct category [GENERAL, CLOTHING, BOOKS, FURNITURE, COMPUTERS]: ");
					// get rating range

					if (scanner.hasNextLine()) {
						category = scanner.nextLine();
					}

					System.out.print("\nRating range [1-5]: ");
					// get rating range

					if (scanner.hasNextLine()) {
						ratingRange = scanner.nextLine();
					}


					amazon.printRatingRange(category, ratingRange);

				}



				System.out.print("\n>");
			}

			/**code below catches exceptions which are thrown in ECommerceSystem
			catch (UnknownCustomerException exception) 
			{
				System.out.println(exception.getMessage());
			}

			catch (UnknownProductException exception) 
			{
				System.out.println(exception.getMessage());
			}

			catch (InvalidProductOptions exception) 
			{
				System.out.println(exception.getMessage());
			}

			catch (InvalidStockCount exception) 
			{
				System.out.println(exception.getMessage());
			}

			catch (InvalidCustName exception) 
			{
				System.out.println(exception.getMessage());
			}

			catch (InvalidAdress exception) 
			{
				System.out.println(exception.getMessage());
			}

			catch (InvalidOrderNumber exception) 
			{
				System.out.println(exception.getMessage());
			}

			*/

		}

	}
}
