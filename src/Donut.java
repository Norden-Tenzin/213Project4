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
    
    public void setType(String type){
        this.type = type;
    }

    public void setFlavour(String flavour){
        this.flavour = flavour;
    }

    public boolean add(Object obj){
        return false;
    }

    public boolean remove(Object obj){
        return false;
    }

    public void itemPrice(){
        //size
        switch(this.type){
            case "YeastDonut": this.price = 1.39;
            break;
            case "CakeDonut": this.price += 1.59;
            break;
            case "DonutHoles": this.price += 0.33;
        }
        //decimal 
        this.price =  Math.floor(this.price * 100) / 100;
    }

    public double getPrice(){
        return this.price;
    }

    // tostring.
    public String toString(){
        return String.valueOf(this.flavour + ((this.flavour.equals("")) ?  "" : "  ") + this.type + "  " + this.price);
    }

    // public static void main(String[] args) {
    //     Donut cf = new Donut("YeastDonut", "BLue");
    //     // cf.setFlavour("Blue");
    //     cf.itemPrice();
    //     System.out.println(cf.toString());
    // }
}

