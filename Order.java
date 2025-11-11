
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Date;

public class Order {
    int orderId;
    int customerRefrence;
    LinkedList <Integer> products = new LinkedList <Integer> ();  
    double total_price;
    LocalDate date;
    String status; // (pending, shipped, delivered, cancelled)

    public Order() {
        this.orderId = 0;
        this.customerRefrence = 0;
        this.total_price = 0;
        this.status = "";
    }

    public Order(int orderId, int customerRefrence, Integer [] pids, double total_price, String date, String status) {
        this.orderId = orderId;
        this.customerRefrence = customerRefrence;
        this.total_price = total_price;
        this.date = LocalDate.parse(date);
        this.status = status;
        
        for (int i = 0 ; i < pids.length ; i++)
          products.insert(pids[i]);
    }

    public int getOrderId() {
        return orderId;
    }

    public int getCustomerRefrence() {
        return customerRefrence;
    }

    public LinkedList<Integer> getProducts() {
        return products;
    }

    public double getTotal_price() {
        return total_price;
    }

    public LocalDate getDate() {
        return date;
    }

    public String getStatus() {
        return status;
    }

    public void setOrderId(int orderId) {
        this.orderId = orderId;
    }

    public void setCustomerRefrence(int customerRefrence) {
        this.customerRefrence = customerRefrence;
    }

    public void setProducts(int pid) {
        this.products.insert(pid);
    }

    public void setTotal_price(double total_price) {
        this.total_price = total_price;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public void addProduct (Integer product )
    {
        products.insert(product);
    }

    public boolean removeProduct( Integer P)
    {
        if ( ! products.empty())
        {
            products.findFirst();
            while(! products.last())
            {
                if (products.retrieve() == P)
                {
                    products.remove();
                    return true;
                }
                else
                    products.findNext();
            }
            if (products.retrieve() == P)
            {
                products.remove();
                return true;
            }
        }
        return false;
    }
   
    @Override
    public String toString() {
        String str =  "\nOrder{" + "orderId=" + orderId + ", customer Refrence=" + customerRefrence 
                + ",total price=" + total_price 
                + " , status =" + status
                + ", date =" + date;
        if ( ! products.empty())
        {
            str += "(products List" ;
            products.findFirst();
            while(! products.last())
            {
                str += products.retrieve() + " ";
                products.findNext();
            }
            str += products.retrieve() + " )";
        }
        str +=  " }";
        return str;        
    }

}
