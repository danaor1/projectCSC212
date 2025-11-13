

public class Review {
    int reviewId;
    int productID;
    int customerID;
    int rating;
    String comment;

    public Review() {
        this.reviewId = 0;
        this.productID = 0;
        this.customerID = 0;
        this.rating = 0;
        this.comment = "";
    }

    public Review(int reviewId, int product, int customer, int rating, String comment) {
        this.reviewId = reviewId;
        this.productID = product;
        this.customerID = customer;
        this.rating = rating;
        this.comment = comment;
    }

    public int getReviewId() {
        return reviewId;
    }

    public void setReviewId(int reviewId) {
        this.reviewId = reviewId;
    }

    public int getProduct() {
        return productID;
    }

    public void setProduct(int product) {
        this.productID = product;
    }

    public int getCustomer() {
        return customerID;
    }

    public void setCustomer(int customer) {
        this.customerID = customer;
    }

    public int getRating() {
        return rating;
    }

    public void setRating(int rating) {
        this.rating = rating;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

@Override
public String toString() {
    return "\nReview {" +
           "\n   Review ID : " + reviewId +
           "\n   Product ID: " + productID +
           "\n   Customer ID: " + customerID +
           "\n   Rating    : " + rating +
           "\n   Comment   : " + comment +
           "\n}";
}

    
    
    
}
