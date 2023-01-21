// Daniel Persaud 501083191
/*
 *  class CartItem defines a cart item. 
 *  ikeeps track of an items product and product options
 *  
 *  
 */


public class CartItem 
{

    private Product currentProduct;
    private String productOptions;


    public CartItem (Product product, String productOptions)
    {
        currentProduct = product;
        this.productOptions = productOptions;



    }

    public void print()
	{
		currentProduct.print();
	}

    public Product getProduct()
    {
        return currentProduct;
    }

    public String getProductOptions()
    {
        return productOptions;
    }


    
}
