package source;

import java.lang.Math;

public class Donut extends MenuItem implements Customizable{
    private double price;
    private String type;
    private String flavour;

    public Donut (String type, String flavour){
        this.setType(type);
        this.price = 0.0;
        this.flavour = flavour;
    }
    
    
    /** 
     * @param type
     */
    public void setType(String type){
        this.type = type;
    }

    
    /** 
     * @param flavour
     */
    public void setFlavour(String flavour){
        this.flavour = flavour;
    }

    
    /** 
     * @param obj
     * @return boolean
     */
    public boolean add(Object obj){
        return false;
    }

    
    /** 
     * @param obj
     * @return boolean
     */
    public boolean remove(Object obj){
        return false;
    }

    
    /** 
     * @return double
     */
    public double itemPrice(){
        //size
        switch(this.type){
            case "Yeast Donut": this.price = 1.39;
            break;
            case "Cake Donut": this.price = 1.59;
            break;
            case "Donut Holes": this.price = 0.33;
        }
        //decimal 
        this.price =  Math.floor(this.price * 100) / 100;
        return this.price;
    }

    
    /** 
     * @return double
     */
    public double getPrice(){
        return this.price;
    }

    
    /** 
     * @return String
     */
    // tostring.
    public String toString(){
        return String.valueOf("$"+this.price+"," + this.flavour +","+ this.type );
    }

    // public static void main(String[] args) {
    //     Donut cf = new Donut("YeastDonut", "BLue");
    //     // cf.setFlavour("Blue");
    //     cf.itemPrice();
    //     System.out.println(cf.toString());
    // }
}

