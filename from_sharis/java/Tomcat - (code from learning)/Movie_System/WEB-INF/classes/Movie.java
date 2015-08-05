import java.io.*;
/*
 * This class is a single movie from the DB
 */
public class Movie implements Serializable {
    
    private int title_id = -1;
    private String name = new String("");
    private double price = 0.0;
    private int quantity = 0;
    // Map to the category table
    private int category = 0;
    
    /** Creates a new instance of Movie */
    public Movie() {
    }
    public void setTitleId(int value){
        this.title_id = value;
    }
    public int getTitleId(){
        return this.title_id;
    }
    public void setName(String value){
        if(value != null)
            this.name=value;
    }
    public String getName(){
        return this.name;
    }
    public void setPrice(double value){
        this.price = value;
    }
    public double getPrice(){
        return this.price;
    }
    public void setQuantity(int value){
        this.quantity = value;
    }
    public int getQuantity(){
        return this.quantity;
    }
    public void setCategory(int value){
        this.category = value;
    }
    public int getCategory(){
        return this.category;
    }
}
