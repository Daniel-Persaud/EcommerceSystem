import java.util.ArrayList;

// Daniel Persaud 501083191
/* A shoe IS A product that has additional information - e.g. size, color

 	 
 	 The format is specified as a specific "stock type" in get/set/reduce stockCount methods.

*/
public class Shoe extends Product 
{

  // Array list for stock of each shoe type 
  private ArrayList<Integer>  stock = new ArrayList<Integer>();


  
  
  public Shoe(String name, String id, double price)
  {
  	 // Make use of the constructor in the super class Product. Initialize additional Shoe instance variables. 
  	 // Set category to Shoes

    super(name, id, price, 100000, Product.Category.SHOES);
    // adds shoe size and stock
    stock.add(6);
    stock.add(100000);
    stock.add(7);
    stock.add(100000);
    stock.add(8);
    stock.add(100000);
    stock.add(9);
    stock.add(100000);
    stock.add(10);
    stock.add(100000);
    stock.add(6);
    stock.add(100000);
    stock.add(7);
    stock.add(100000);
    stock.add(8);
    stock.add(100000);
    stock.add(9);
    stock.add(100000);
    stock.add(10);
    stock.add(100000);




  }



  // Check if a valid format  
  public boolean validOptions(String productOptions)
  {
  	// check productOptions for color and size
  	// if it is one of these, return true, else return false

    // splits product options string into string array of size and color
    String [] result = productOptions.split(" ", 2);

  	if(result[0].equals("Brown") || result[0].equals("Black"))
    {
        if (result[1].equals("6")|| result[1].equals("7")|| result[1].equals("8") || result[1].equals("9")||result[1].equals("10"))
        {
            return true;
        }
    }

    return false;

  }

  // Override getStockCount() in super class.
  public int getStockCount(String productOptions)
	{


    //Checks to see if the the stock of each shoe is available
    String [] result = productOptions.split(" ", 2);

    if (result[0].equals("Brown"))
    {
      if (result[1].equals("6"))
      {
        return stock.get(1);
      }

      else if (result[1].equals("7"))
      {
        return stock.get(3);
      }

      else if (result[1].equals("8"))
      {
        return stock.get(5);
      }

      else if (result[1].equals("9"))
      {
        return stock.get(7);
      }

      else if (result[1].equals("10"))
      {
        return stock.get(9);
      }

    }


    else if (result[0].equals("Black"))
    {

      if (result[1].equals("6"))
      {
        return stock.get(11);
      }

      else if (result[1].equals("7"))
      {
        return stock.get(13);
      }

      else if (result[1].equals("8"))
      {
        return stock.get(15);
      }

      else if (result[1].equals("9"))
      {
        return stock.get(17);
      }

      else if (result[1].equals("10"))
      {
        return stock.get(19);
      }

    }


    return 0;


  }

  // sets stock count of each individual shoe option
  public void setStockCount(int stockCount, String productOptions)
	{

    String [] result = productOptions.split(" ", 2);

    if (result[0].equals("Brown"))
    {
      if (result[1].equals("6"))
      {
        stock.set(1, stockCount);
      }

      else if (result[1].equals("7"))
      {
        stock.set(3,stockCount);
      }

      else if (result[1].equals("8"))
      {
        stock.set(5,stockCount);
      }

      else if (result[1].equals("9"))
      {
        stock.set(7,stockCount);
      }

      else if (result[1].equals("10"))
      {
        stock.set(9,stockCount);
      }

    }


    else if (result[0].equals("Black"))
    {
      if (result[1].equals("6"))
      {
        stock.set(11, stockCount);
      }

      else if (result[1].equals("7"))
      {
        stock.set(13,stockCount);
      }

      else if (result[1].equals("8"))
      {
        stock.set(15,stockCount);
      }

      else if (result[1].equals("9"))
      {
        stock.set(17,stockCount);
      }

      else if (result[1].equals("10"))
      {
        stock.set(19,stockCount);
      }

    }

  }


  //reduces the stockcount ineteger held in the stock array list
  public void reduceStockCount(String productOptions)
	{
    String [] result = productOptions.split(" ", 2);

    if (result[0].equals("Brown"))
    {
      if (result[1].equals("6"))
      {
        stock.set(1, getStockCount(productOptions)-1);
      }

      else if (result[1].equals("7"))
      {

        stock.set(3, getStockCount(productOptions)-1);

      }


      else if (result[1].equals("8"))
      {

        stock.set(5, getStockCount(productOptions)-1);

      }

      else if (result[1].equals("9"))
      {

        stock.set(7, getStockCount(productOptions)-1);

      }

      else if (result[1].equals("10"))
      {

        stock.set(9, getStockCount(productOptions)-1);

      }
       

    }


    else if (result[0].equals("Black"))
    {

      if (result[1].equals("6"))
      {
        stock.set(11, getStockCount(productOptions)-1);
      }

      else if (result[1].equals("7"))
      {

        stock.set(13, getStockCount(productOptions)-1);

      }


      else if (result[1].equals("8"))
      {

        stock.set(15, getStockCount(productOptions)-1);

      }

      else if (result[1].equals("9"))
      {

        stock.set(17, getStockCount(productOptions)-1);

      }

      else if (result[1].equals("10"))
      {

        stock.set(19, getStockCount(productOptions)-1);

      }

    }


  
  }

// print method for shoe class
  public void print()
  {

  	super.print();
  }

}







    
 