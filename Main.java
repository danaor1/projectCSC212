 
import java.io.File;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;

public class Main {
    
    public static Scanner input = new Scanner (System.in);
    
    public static productsData productdata = new productsData("prodcuts.csv");
    public static LinkedList<Product>  products;
    
    public static customersData customerdata = new customersData("customers.csv");
    public  static LinkedList<Customer> customers;
    
    public static ordersData orderdata = new ordersData("orders.csv");
    public static LinkedList<Order>  orders;
    
    public static reviewsData revdata = new reviewsData("reviews.csv");
    public static LinkedList<Review>  reviews;
    

    // read data files for all the 4 data structures يقرا البيانات
    public static void loadData ()
    {
        System.out.println("loading data from CVSs files");
        products = productdata.getproductsData();
        customers = customerdata.getcustomersData();
        orders = orderdata.getordersData();
        reviews = revdata.getreviewsData();
        
        ///add Orders To Customers
        customers.findFirst();
        for(int i = 0 ; i < customers.size(); i++)
        {
            orders.findFirst();
            for ( int j = 0 ; j < orders.size() ; j ++)
            {
                if (customers.retrieve().getCustomerId() == orders.retrieve().getCustomerRef())
                {
                    int orderid =orders.retrieve().getOrderId();
                    customers.retrieve().addOrder(orderid);
                }
                orders.findNext();
            }
            customers.findNext();
        }
        
        ///add Rviews To products
        products.findFirst();
        for(int i = 0 ; i < products.size(); i++)
        {
            reviews.findFirst();
            for ( int j = 0 ; j < reviews.size() ; j ++)
            {
                if (products.retrieve().getProductId() == reviews.retrieve().getProduct())
                {
                    int rid =reviews.retrieve().getReviewId();
                    products.retrieve().addReview(rid);
                }
                reviews.findNext();
            }
            products.findNext();
        }
        
    }
    
    public static int menu1()
    {
        
        System.out.println("\n==============================");
        System.out.println("            Main Menu           ");
        System.out.println("==============================");
        System.out.println("1. Products");
        System.out.println("2. Customers");
        System.out.println("3. Orders");
        System.out.println("4. Reviews");
        System.out.println("5. Exist");
        System.out.println("Enter your choice: ");
        return input.nextInt();
    }
    
    
    public static void productsMenu()
    {
        int choice;
        
        System.out.println("\n==============================");
        System.out.println("            Product Menu        ");
        System.out.println("==============================");
        System.out.println("1. Add new product");
        System.out.println("2. Remove product");
        System.out.println("3. Update product ");
        System.out.println("4. Search By ID");
        System.out.println("5. Search products by name");
        System.out.println("6. All Out of stock products");
        System.out.println("7. Return to Main menu");
        System.out.println("Enter your choice: ");
        choice = input.nextInt();
        switch (choice)
        {
            case 1:
                productdata.addProduct();
                break;
            case 2:
                System.out.println("Note: Products cannot be removed permanently, stock will be set to ZERO. ");
                productdata.removeProduct();
                break;
            case 3:
                productdata.updateProduct();
                break;
            case 4:
            {
                Product pro = productdata.searchProducID();
                if (pro != null)
                    System.out.println(pro);
            }
            break;
            case 5:
            {
                Product pro = productdata.searchProducName();
                if (pro != null)
                    System.out.println(pro);
            }
            break;
            case 6:
                productdata.OutOfStockProducts();
                break;
            default:
                System.out.println("Invalid choice! Returning to Main Menu...");
        }
        
    }
    
    public static void CustomersMenu()
    {
        int choice;
        System.out.println("\n==============================");
        System.out.println("            Customer Menu        ");
        System.out.println("==============================");
        System.out.println("1. Register new customer");
        System.out.println("2. Place New Order for a customer");
        System.out.println("3. View Order history  for customer");
        System.out.println("4. Return to Main menu");
        System.out.println("Enter your choice: ");
        choice = input.nextInt();
        switch (choice)
        {
            case 1:
                customerdata.RegisterCustomer();
                break;
            case 2:
                PlaceOrder();
                break;
            case 3:
                customerdata.OrderHistory();
                break;
            case 4:
                break;
            default:
                System.out.println("Invalid choice! Returning to Main Menu...");
        }
    }

    public static void OrdersMenu()
    {
        int choice;
        System.out.println("\n==============================");
        System.out.println("            Order Menu        ");
        System.out.println("==============================");
        System.out.println("1. Place New Order");
        System.out.println("2. Cancel Order");
        System.out.println("3. Update Order Status");
        System.out.println("4. Search By ID");
        System.out.println("5. List All orders between two dates");
        System.out.println("6. Return to Main menu");
        System.out.println("Enter your choice: ");
        choice = input.nextInt();
        
        switch (choice)
        {
            case 1:
                PlaceOrder();
                break;
            case 2:
                CancelOrder();
                break;
            case 3:
            {
                System.out.println("update new status...");
                if (orders.empty())
                    System.out.println("empty Orders data");
                else
                {
                    System.out.println("Enter order ID: ");
                    int orderID = input.nextInt();
                    orderdata.UpdateOrder(orderID);
                }
            }
            break;
            
            case 4:
            {
                if (orders.empty())
                    System.out.println("empty Orders data");
                else
                {
                    System.out.println("Enter order ID: ");
                    int orderID = input.nextInt();
                    System.out.println(orderdata.searchOrderID(orderID));
                }
            }
            break;
            
            case 5:
            {
                System.out.println("Enter first date [dd/MM/yyyy]");
                String date1 = input.next();
                
                System.out.println("Enter second date [dd/MM/yyyy]");
                String date2 = input.next();
                
                orderdata.BetweenTwoDates(date1, date2);
           }
            break;
            case 6:
                break;

            default:
                System.out.println("Invalid choice! Returning to Main Menu...");
        }
    }

    
    public static void ReviewsMenu()
    {
        System.out.println("\n==============================");
        System.out.println("            Review Menu        ");
        System.out.println("==============================");
        System.out.println("1. Add review");
        System.out.println("2. Edit review");
        System.out.println("3. Get an average rating for product");
        System.out.println("4. Top 3 products");
        System.out.println("5. Common products with an average rating 4 and more between 2 cusomers");
        System.out.println("6. Return to Main menu");
        System.out.println("Enter your choice: ");
        int choice = input.nextInt();
        switch (choice)
        {
            case 1:
                AddNewReview();
                break;
            case 2:
                revdata.updateReview();
                break;
            case 3:
            {
                System.out.println("Enter product ID to Get an average rating :");
                int pid = input.nextInt();

                while (!productdata.checkProductID(pid))
                {
                    System.out.println("Invalid product ID. Re-enter: ");
                    pid = input.nextInt();
                }
                float AVG = avgRating(pid);
                System.out.println("Average Rating for " + pid + " is " + AVG);
            }
            break;
            case 4:
                top3Products();
                break;
            case 5:
            {
                Customer cid1 =customerdata.getCustomerID();
                Customer cid2 =customerdata.getCustomerID();
                commonProducts(cid1.getCustomerId(), cid2.getCustomerId());
            }
            break;
            case 6:
                break;
            default:
                System.out.println("Returning to Main Menu...");
        }
    }
    
    
    public static void AddNewReview()
    {
        System.out.println("Enter cutomer Id :");
        int cID =input.nextInt();
        while ( ! customerdata.checkCustomerID(cID))
        {
            System.out.println("Customer ID not available, re-enter:");
            cID =input.nextInt();
        }
        
        System.out.println("Enter Product ID :");
        int pID =input.nextInt();
        while ( ! productdata.checkProductID(pID))
        {
            System.out.println("Product ID not available, re-enter:");
            pID =input.nextInt();
        }
        
        Review review = revdata.AddReview(cID, pID);
        System.out.println("New Review( " + review.getReviewId() 
                + ") added for " + review.getProduct() 
                + " by customer (" + review.getCustomer() +"), Rate(" + review.getRating() + ") with comment: " + review.getComment() +"." );
    }
    
    public static void PlaceOrder()
    {
            Order new_order = new Order ();
            int total_price = 0;
            
            System.out.println("Enter order ID: ");
            int oid = input.nextInt();
            while (orderdata.checkOrderID(oid))
            {
                System.out.println("Order ID already exists. Re-enter: ");
                oid = input.nextInt();
            }
            new_order.setOrderId(oid);
            
            System.out.println("Enter customer ID:");
            int cid = input.nextInt();
            while(! customerdata.checkCustomerID(cid))
            {
                System.out.println("Customer ID not found. Re-enter: ");
                cid = input.nextInt();
            }
            new_order.setCustomerRefrence(cid);
            
            char answer = 'y';
            while (answer == 'y' || answer == 'Y')
            {
                System.out.println("Enter product ID to add:");
                int pid = input.nextInt();
               
                boolean found = false;
                
                products.findFirst();
                for ( int i = 0 ;  i < products.size() ;i++)
                {
                    if (products.retrieve().getProductId() == pid)
                    {
                        if (products.retrieve().getStock() == 0)
                            System.out.println("product out of stock. try another time");
                        else
                        {
                            Product pp = products.retrieve();
                            products.remove();
                            pp.setStock(pp.getStock()-1);
                            products.insert(pp);
                            System.out.println("product added Succcefuly");
                            found = true;
                            
                            new_order.addProduct(pp.getProductId());
                            total_price += pp.getPrice();
                        }
                        break;
                    }
                    products.findNext();
                }

                if (!found)
                        System.out.println("  No product found with ID");
                    
                
                System.out.println("Do you want to add another product? (Y/N): ");
                answer = input.next().charAt(0);
            }
            
            new_order.setTotal_price(total_price);
            
            System.out.println("Enter first date (dd/mm/yyyy)");
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
            LocalDate Ldate = LocalDate.parse(input.next(), formatter);
            new_order.setDate(Ldate);
     
            System.out.println("Enter new status  (pending, shipped, delivered, cancelled)....");
            new_order.setStatus(input.next());
            
            orders.insert(new_order);
            
            // add order to customer list
            customers.findFirst();
            for(int i = 0 ; i < customers.size(); i++)
            {
                if (customers.retrieve().getCustomerId() == new_order.getCustomerRef())
                {
                    Customer cust = customers.retrieve();
                    customers.remove();
                    cust.addOrder(oid);
                    customers.insert(cust);
                    break;
                }
                customers.findNext();
            }   
            
            System.out.println("Order had been added successfuly ");
            System.out.println(orders.retrieve());
    }
    
    
    public static void CancelOrder()
    {
            System.out.println("Enter order ID to cancel: ");
            int oid = input.nextInt();
            Order cancel_order = orderdata.searchOrderID(oid);
            while (cancel_order == null)
            {
                System.out.println("Re-enter order id, is not available , try again");
                oid = input.nextInt();
                cancel_order = orderdata.searchOrderID(oid);
            }
        
            if ( orderdata.cancelOrder(oid) == 1)
            {
                customers.findFirst();
                for ( int i = 0 ; i < customers.size ; i++)
                {
                    if (customers.retrieve().getCustomerId() == cancel_order.getCustomerRef() )
                    {
                        Customer cust = customers.retrieve();
                        customers.remove();
                        cust.removeOrder(cancel_order.getOrderId());
                        customers.insert(cust);
                        break;
                    }
                    customers.findNext();
                }
                
                cancel_order.getProducts().findFirst();
                for ( int x = 0 ; x < cancel_order.getProducts().size() ; x++  )
                {
                        products.findFirst();
                        for (int i = 0 ; i < products.size() ; i++)
                        {
                            if (products.retrieve().getProductId() == cancel_order.getProducts().retrieve().byteValue())
                                products.retrieve().addStock(1);
                            products.findNext();
                        }
                        cancel_order.getProducts().findNext();
                }
                
                System.out.println("order (" + cancel_order.getOrderId() + ") had been cancelled") ;
            }    
    }

    
    //Get an average rating for product.
    public static float avgRating(int pid)
    {
        int counter =0;
        float rate = 0;
        
        reviews.findFirst();
        while (! reviews.last())
        {
            if (reviews.retrieve().getProduct() == pid)
            {
                counter ++;
                rate += reviews.retrieve().getRating();
            }
            reviews.findNext();
        }
        if (reviews.retrieve().getProduct() == pid)
        {
            counter ++;
            rate += reviews.retrieve().getRating();
        }
        
        return (rate/counter);
    }

    public static void top3Products()
    {
        PriorityLinkedQueue<Product> top3 = new PriorityLinkedQueue<Product> ();
        
        if (!products.empty())
        {
            products.findFirst();
            for (int i = 0 ; i < products.size() ; i++)
            {
                
                Product p = products.retrieve();
                float AVGrating = avgRating (p.productId);
                top3.enqueue(p, AVGrating);
                
                products.findNext();
            }
            
        }
        
        System.out.println("Top 3 products by average rating: ");
        for ( int j = 1 ; j <= 3 && top3.size() > 0 ; j++)
        {
            QueueElement<Product> top = top3.serve();
            System.out.println("Product " + j + "  " + top.data.getProductId() 
                    + " " + top.data.getName() + " AVG rate (" + top.priority + ")" );
        }
    }
    
    //Given two customers IDs, show a list of common products that have been 
    //reviewed with an average rating of more than 4 out of 5.
    public static void commonProducts( int cid1 , int cid2)
    {
        LinkedList<Integer> pcustomer1 = new LinkedList<Integer> ();
        LinkedList <Integer> pcustomer2 = new LinkedList <Integer> ();
        
        //1. find all products for customer1 1 and customer 2 that are reviewd
        // for each customer in linked list 
        reviews = revdata.getreviewsData();
        
        if (! reviews.empty())
        {
            reviews.findFirst();
            for (int i =1 ;i <= reviews.size() ; i++)
            {
                if (reviews.retrieve().getCustomer() == cid1 )
                {
                    pcustomer1.findFirst();
                    boolean found1 = false;
                    for (int x = 1; x <= pcustomer1.size() ; x++)
                    {
                        if (pcustomer1.retrieve() == reviews.retrieve().getProduct())
                        {
                            found1 = true;
                            break;
                        }
                        pcustomer1.findNext();
                    }
                    pcustomer1.findFirst();
                    if (! found1 )
                        pcustomer1.insert(reviews.retrieve().getProduct());
                }
                
                if (reviews.retrieve().getCustomer() == cid2 )
                {
                    pcustomer2.findFirst();
                    boolean found2 = false;
                    for (int x = 1; x <= pcustomer2.size() ; x++)
                    {
                        if (pcustomer2.retrieve() == reviews.retrieve().getProduct())
                        {
                            found2 = true;
                            break;
                        }
                        pcustomer2.findNext();
                    }
                    
                    pcustomer2.findFirst();
                    if (! found2 )
                        pcustomer2.insert(reviews.retrieve().getProduct());
                }
                reviews.findNext();
            }
            
            pcustomer1.print();
            pcustomer2.print();
            
            // find common products for both lists
            // add common after finding avg rate > 4 in new linked list
            PriorityLinkedQueue<Product> AVGrate45 = new PriorityLinkedQueue<Product> ();
            if (! pcustomer1.empty() && ! pcustomer2.empty())
            {
                pcustomer1.findFirst();
                for ( int m =1; m <= pcustomer1.size() ; m++)
                {
                    int pID = pcustomer1.retrieve();
                    pcustomer2.findFirst();
                    for (int n = 1 ; n <= pcustomer2.size() ; n++)
                    {
                        if ( pID == pcustomer2.retrieve())
                        {
                            float AVGrating = avgRating (pID);
                            if ( AVGrating >= 4)
                            {
                                Product p = productdata.getProductData(pID);
                                AVGrate45.enqueue(p, AVGrating);
                            }
                        }
                        pcustomer2.findNext();
                    }
                    pcustomer1.findNext();
                }                
            
                // printing common products
                System.out.println("Common Products with rate above 4 are ");
                while (AVGrate45.size() > 0)
                {
                    QueueElement<Product> product_rate = AVGrate45.serve();
                    System.out.println(" Product (" + product_rate.data.productId + ") " + product_rate.data.getName() + " with rate " + product_rate.priority );
                    System.out.println(product_rate.data);
                    System.out.println("\n");
                }
            }
            else
                System.out.println("NO common products between the two customers ");
        }
        else
            System.out.println("No reviews available.");
    }

    public static void main(String[] args) {
        
        loadData();
        int choice;
        do {
                choice = menu1();
               switch (choice)
                {
                    case 1:
                        productsMenu();
                        break;
                    case 2:
                        CustomersMenu();
                        break;
                    case 3:
                        OrdersMenu();
                        break;
                    case 4:
                        ReviewsMenu();
                        break;
                    case 5:
                        break;
                    default:
                        System.out.println("Invalid choice! Try again...");
                }
               
               
        }while (choice != 5);
    }
    
}
