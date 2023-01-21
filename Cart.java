import java.util.ArrayList;

// Daniel Persaud 501083191
/*Class Cart defines a customer cart
 * it keeps track of cartitems
 */
public class Cart
{

    private ArrayList<CartItem>  cartItems ;
    

    public Cart()
    {
        cartItems = new ArrayList<CartItem>();

    }


    public void addTocart (Product p,String productOptions)
    {

        CartItem item = new CartItem(p, productOptions);
        cartItems.add(item);

    }

    public void printCart()
    {
        System.out.println("Products in cart: ");
        
        for (CartItem i : cartItems)
        i.print();
    }


    public void remCartItem (String productId)
    {   

        for (int index = 0; index < cartItems.size(); index++) 
        {
            if (productId.equals(cartItems.get(index).getProduct().getId()))
            {
                cartItems.remove(index);

            }
            
            
        }

    }

    public ArrayList<CartItem> getCartItems()
    {
        return cartItems;
    }
    

















}