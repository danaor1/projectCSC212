/**
 *
 * @author Manal Alhihi
 */
public class Product {
    int productId;
    String name;
    double price;
    int stock;
    LinkedList <Integer> reviews = new LinkedList <Integer> ();

    public Product() {
        this.productId = 0;
        this.name = "";
        this.price = 0;
        this.stock = 0;
    }

    public Product(int pid, String n, double p, int s) {
        this.productId = pid;
        this.name = n;
        this.price = p;
        this.stock = s;
    }

    public int getProductId() {
        return productId;
    }

    public void setProductId(int productId) {
        this.productId = productId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public int getStock() {
        return stock;
    }

    public void setStock(int stock) {
        this.stock = stock;
    }

    public void addStock ( int stock)
    {
        this.stock += stock;
    }
    
    public void removeStock ( int stock)
    {
        this.stock -= stock;
    }
    
    
    public void addReview( Integer R)
    {
        reviews.insert(R);
    }
    
    public boolean removeReview( Integer R)
    {
        if ( ! reviews.empty())
        {
            reviews.findFirst();
            while(!reviews.last())
            {
                if (reviews.retrieve() == R)
                {
                    reviews.remove();
                    return true;
                }
                else
                    reviews.findNext();
            }
            if (reviews.retrieve() == R)
            {
                reviews.remove();
                return true;
            }
        }
        return false;
    }
    
    public LinkedList<Integer> getReviews ()
    {
        return reviews;
    }
    
    @Override
    public String toString() {
        String str =  "\nproductId=" + productId + ", name=" + name + ", price=" + price + ", stock=" + stock ;
        if ( ! reviews.empty())
        {
            str += "(reviews List" ;
            reviews.findFirst();
            while(! reviews.last())
            {
                str += reviews.retrieve() + " ";
                reviews.findNext();
            }
            str += reviews.retrieve() + " )";
        }
        return str;        
    }
    
}
