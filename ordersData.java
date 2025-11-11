
import java.io.File;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;

/**
 *
 * @author Manal Alhihi
 */
public class ordersData {
    
    public static Scanner input = new Scanner (System.in);
    public static LinkedList<Order> orders = new LinkedList<Order> ();
    
//==============================================================
    public LinkedList<Order>  getordersData ( )
    {
        return orders;
    }
    
//==============================================================
    public ordersData ( String fileName)
    {
        try{
                File docsfile = new File(fileName);
                Scanner reader = new Scanner (docsfile);
                String line = reader.nextLine();
                
                while(reader.hasNext())
                {
                    line = reader.nextLine();
                    String [] data = line.split(","); 
                    int oid = Integer.parseInt(data[0]);
                    int cid = Integer.parseInt(data[1]);
                    
                    String  pp =  data[2].replaceAll("\"", "");
                    String [] p = pp.split(";");
                    Integer [] pids = new Integer [p.length];
                    for ( int i = 0 ; i< pids.length ; i++)
                        pids[i] = Integer.parseInt(p[i].trim());

                    double price = Double.parseDouble(data[3]);
                    String date = data[4];
                    String status = data[5];
                            
                    Order order = new Order(oid, cid, pids, price, date, status );
                    orders.insert(order);
                    
                }
                reader.close();
            }
            catch (Exception ex) {
                System.out.println(ex.getMessage());
            }
     }

    //==============================================================
    public int cancelOrder (int oid)
    {
        orders.findFirst();
        for ( int i = 0 ; i < orders.size() ; i++)
        {
            if ( orders.retrieve().getOrderId() == oid )
            {
                if (orders.retrieve().status.compareToIgnoreCase("cancelled") == 0)
                {
                    System.out.println("Order " + orders.retrieve().getOrderId() + " was cancelled before");
                    return 2; // canceled before 
                }
                    
                Order ordera = orders.retrieve(); 
                ordera.status = "cancelled";
                orders.insert(ordera);
                return 1; //cancelled // should update the customer and stock of products
            }
            else
                orders.findNext();
        }
        return 0; // not found order
    }
    
    //==============================================================
    public boolean UpdateOrder( int orderID)
    {
         boolean found = false;
        orders.findFirst();
        while (!orders.last())
        {
            if (orders.retrieve().getOrderId()== orderID)
            {
                found = true;
                break;
            }
            orders.findNext();
        }
        if (orders.retrieve().getOrderId()== orderID)
            found = true;

        if (found )
        {
            if ( orders.retrieve().getStatus().compareToIgnoreCase("cancelled") == 0)
            {
                System.out.println("could not change status for cancelled order");
                return false;
            }
             else
            {
                Order obj = orders.retrieve();

                System.out.println("Status of order is " + orders.retrieve().getStatus() );
                System.out.println("Enter new status  (pending, shipped, delivered, cancelled)....");

                String str = input.next();
                orders.remove();
                obj.status = str;
                orders.insert(obj);
                return true;
            }
        }
        System.out.println("No such Order");
        return false;
    }
    
    //==================================================================    
    public Order searchOrderID(int orderID)
    {
        boolean found = false;

        orders.findFirst();
        while (!orders.last())
        {
            if (orders.retrieve().getOrderId()== orderID)
            {
                found = true;
                break;
            }
            orders.findNext();
        }
        if (orders.retrieve().getOrderId()== orderID)
            found = true;

        if (found )
            return orders.retrieve();

        System.out.println("No such Order");
        return null;
    }

    //==================================================================    
    public LinkedList<Order> BetweenTwoDates(String date1, String date2)
    {
        LinkedList<Order> ordersbetweenDates =  new LinkedList<Order>();
        
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        LocalDate Ldate1 = LocalDate.parse(date1, formatter);
        LocalDate Ldate2 = LocalDate.parse(date2, formatter);
        
        if (! orders.empty())
        {
            orders.findFirst();
            
            for ( int i = 0 ; i < orders.size() ; i++)
            {
                if ( orders.retrieve().getDate().isAfter(Ldate1) && orders.retrieve().getDate().isBefore(Ldate2))
                {
                    ordersbetweenDates.insert(orders.retrieve());
                    System.out.println(orders.retrieve());
                }
               orders.findNext();
            }
        }
        return ordersbetweenDates;
   }
    //==================================================================    
    public boolean checkOrderID(int oid)
    {
        if (!orders.empty())
        {
            orders.findFirst();
            while (!orders.last())
            {
                if (orders.retrieve().getOrderId()== oid)
                    return true;
                orders.findNext();
            }
            if (orders.retrieve().getOrderId()== oid)
                return true;
        }
        return false;
    }
}
