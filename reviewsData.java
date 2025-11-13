import java.io.File;
import java.util.Scanner;


public class reviewsData {
    public static Scanner input = new Scanner (System.in);
    public static LinkedList<Review> reviews = new LinkedList<Review> ();


    public LinkedList<Review>  getreviewsData ( )
    {
        return reviews;
    }
 

    public reviewsData ( String fileName)
    {
        
        try{
                File docsfile = new File(fileName);
                Scanner reader = new Scanner (docsfile);
                String line = reader.nextLine();
                
                while(reader.hasNext())
                {
                    line = reader.nextLine();
                    String [] data = line.split(","); 
                    int reviewId = Integer.parseInt(data[0]);
                    int productId = Integer.parseInt(data[1]);
                    int customerId = Integer.parseInt(data[2]);
                    int rating =  Integer.parseInt(data[3]);
                    String comment =  data[4];
                    
                    Review reviewObj = new Review(reviewId, productId, customerId, rating, comment );
                    reviews.insert(reviewObj);
                }
                reader.close();
            }
            catch (Exception ex) {
                System.out.println(ex.getMessage());
            }
    }
    
    
    public Review AddReview(int cID, int pID)
    {
        System.out.println("Please enter the review ID:");
        int reviewID = input.nextInt();
        
        while (checkReviewID(reviewID))
        {
            System.out.println("This review ID already exists. Please enter a different one:");
            reviewID = input.nextInt();
        }        
        
        System.out.println("Enter a rating between 1 and 5:");
        int rate = input.nextInt();
        while ( rate >5 || rate <1)
        {
            System.out.println("Invalid rating. Please enter a value from 1 to 5:");
            rate = input.nextInt();
        }

        System.out.println("Enter your comment:");
        String comment = input.nextLine();
        comment = input.nextLine();

        Review review = new Review (reviewID, pID, cID, rate,  comment );
        reviews.findFirst();
        reviews.insert(review);
        return review;
    }

   
    public void updateReview()
   {
       if  ( reviews.empty() )
       {
           System.out.println("No reviews data ");
           return;
       }
       
       Review review = new Review();
       
       System.out.println("Enter Review ID to update:");
        int reviewID = input.nextInt();
        
        while (! checkReviewID(reviewID))
        {
            System.out.println("Review ID not found. Please re-enter");
            reviewID = input.nextInt();
        }        
       
        reviews.findFirst();
        while (! reviews.last())
        {
            if (reviews.retrieve().getReviewId() == reviewID)
                break;
           reviews.findNext();
        }
        if (reviews.retrieve().getReviewId() == reviewID)
        {
            review = reviews.retrieve();
            reviews.remove();
            
            System.out.println("1. update rate");
            System.out.println("2. update comment");
            System.out.println("Enter choice ");
            int choice = input.nextInt();

            switch(choice)
            {
                case 1:
                {
                    System.out.println("Enter new rating between 1 to 5: ");
                    int rate = input.nextInt();
                    while ( rate >5 || rate <1)
                    {
                        System.out.println("Re-Enter rating between 1 to 5: ");
                        rate = input.nextInt();
                    }
                    review.setRating(rate);
                }
                break;

                case 2:
                {
                    System.out.println("Enter comment: ");
                    String comment = input.nextLine();
                    comment = input.nextLine();
                    review.setComment(comment);
                }
                break;
            }
            reviews.insert(review);
            System.out.println("Review " + review.getReviewId() + " had been updated");
            System.out.println(reviews.retrieve());
        }
   }
   
    public boolean checkReviewID( int rID )
    {
        if (! reviews.empty())
        {
            reviews.findFirst();
            for (int i = 0 ; i< reviews.size() ; i++)
            {
                if (reviews.retrieve().getReviewId()== rID)
                    return true;
                reviews.findNext();
            }
        }
        return false ;
    }
}
