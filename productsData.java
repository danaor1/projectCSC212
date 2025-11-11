
import java.io.File;
import java.util.Scanner;

/**
 *
 * @author Manal Alhihi
 */
public class productsData {
    public static Scanner input = new Scanner (System.in);
    public static LinkedList<Product> products = new LinkedList<Product> ();

//==============================================================
    public LinkedList<Product>  getproductsData ( )
    {
        return products;
    }
    
//==============================================================
    public productsData ( String fileName)
    {
        
       try{
                File docsfile = new File(fileName);
                Scanner reader = new Scanner (docsfile);
                String line = reader.nextLine();
                
                while(reader.hasNext())
                {
                    line = reader.nextLine();
                    String [] data = line.split(","); 
                    
                    int pid = Integer.parseInt(data[0]);
                    String name =  data[1].trim();
                    double price = Double.parseDouble(data[2]);
                    int stock = Integer.parseInt(data[3]);

                    Product product = new Product(pid, name, price, stock );
                    products.insert(product);
                }
                reader.close();
            }
            catch (Exception ex) {
                System.out.println(ex.getMessage());
            }
    }
 
//==================================================================    
    public void addProduct()
    {
        System.out.println("Enter data productID:");
        int pId = input.nextInt();
        while (checkProductID(pId))
        {
            System.out.println("Re-Enter productID again , number already taken:");
            pId = input.nextInt();
        }
        
        System.out.println("product Name:");
        String name =input.next();
        
        System.out.println("price:");
        double price = input.nextDouble();
        
        System.out.println("stock:");
        int stock = input.nextInt();
        
        Product product = new Product(pId, name, price, stock);
        products.findFirst();
        products.insert(product);
    }
    
//==================================================================    
    public Product searchProducID()
    {
        if (products.empty())
        {
            System.out.println("empty Products data");
        }
        else
        {
            System.out.println("Enter product ID: ");
            int productID = input.nextInt();
            
            boolean found = false;
            
            products.findFirst();
            while (!products.last())
            {
                if (products.retrieve().getProductId()== productID)
                {
                    found = true;
                    break;
                }
                products.findNext();
            }
            if (products.retrieve().getProductId()== productID)
                found = true;
        
            if (found )
                return products.retrieve();
        }
        System.out.println("No such product ID");
        return null;
    }

//==================================================================    
    public Product removeProduct()
    {
        if (products.empty())
        {
            System.out.println("empty Products data");
        }
        else
        {
            System.out.println("Enter product ID: ");
            int productID = input.nextInt();
            
            boolean found = false;
            
            products.findFirst();
            while (!products.last())
            {
                if (products.retrieve().getProductId()== productID)
                {
                    found = true;
                    break;
                }
                products.findNext();
            }
            if (products.retrieve().getProductId()== productID)
                found = true;
        
            if (found )
            {
                
                Product p = products.retrieve();
                products.remove();
                p.setStock(0);
                products.insert(p);
                return p;
            }
        }
        System.out.println("No such product ID");
        return null;
    }

//==================================================================    
    public Product updateProduct()
    {
        if (products.empty())
        {
            System.out.println("empty Products data");
        }
        else
        {
            System.out.println("Enter product ID: ");
            int productID = input.nextInt();
            
            boolean found = false;
            
            products.findFirst();
            while (!products.last())
            {
                if (products.retrieve().getProductId()== productID)
                {
                    found = true;
                    break;
                }
                products.findNext();
            }
            if (products.retrieve().getProductId()== productID)
                found = true;
        
            if (found )
            {
                Product p = products.retrieve();
                products.remove();
                
                System.out.println("1. Update Name");
                System.out.println("2. Update price");
                System.out.println("3. Update stock");
                System.out.println("Enter your choice");
                int choice = input.nextInt();
                
                switch ( choice )
                {
                    case 1:
                    {
                        System.out.println("product Name:");
                        p.setName(input.next());
                        products.insert(p);
                    }
                    break;
                    case 2:
                    {
                        System.out.println("price:");
                        p.setPrice( input.nextDouble());
                        products.insert(p);
                    }
                    break;
                    case 3:
                    {
                        System.out.println("stock:");
                        int stock = input.nextInt();
                        p.setStock(stock);
                        products.insert(p);
                    }
                    break;
                    default:
                        System.out.println("Bad Choice");
                }
                return p;
            }
        }
        System.out.println("No such product ID");
        return null;
    }

//==================================================================    
    public Product searchProducName()
    {
        if (products.empty())
            System.out.println("empty Products data");
        else
        {
            System.out.println("Enter product Name: ");
            String name = input.nextLine();
            name = input.nextLine();
            
            products.findFirst();
            for ( int i = 0 ; i < products.size() ; i++)
            {
                if (products.retrieve().getName().compareToIgnoreCase(name)== 0)
                    return products.retrieve();
                products.findNext();
            }
        }

        System.out.println("No such product Name");
        return null;
    }

//==================================================================    
    public void Out_Stock_Products()
    {
        if (products.empty())
        {
            System.out.println("empty Products data");
        }
        else
        {
            products.findFirst();
            for ( int i = 0 ; i < products.size() ; i++)
            {
                if (products.retrieve().getStock() == 0)
                    System.out.println(products.retrieve());    
                products.findNext();
            }
        }
    }

//==============================================================
    public boolean checkProductID( int PID )
    {
        if (! products.empty())
        {
            products.findFirst();
            for ( int i = 0 ; i< products.size() ; i++)
            {
                if (products.retrieve().getProductId() == PID)
                    return true;
                products.findNext();
            }
        }
        return false ;
    }

    //==============================================================
    public Product getProductData ( int PID )
    {
        if (! products.empty())
        {
            products.findFirst();
            while (!products.last())
            {
                if (products.retrieve().getProductId() == PID)
                    return products.retrieve();
                products.findNext();
            }
            if (products.retrieve().getProductId()== PID)
                return products.retrieve();
        }
        return null;
    }


}
