package source;

import java.lang.Math;

/**
 * Class for creating a donut object which includes price, type and flavor.
 * 
 * @Tenzin Norden, @Vedant Mehta
 */
public class Donut extends MenuItem implements Customizable {
    private double price;
    private String type;
    private String flavour;

    /**
     * Constructor for Donut which takes in a type and a flavor.
     * 
     * @param type    of the donut which can be donut hole, cake and yeast.
     * @param flavour of the donut which can be up to 3 flavors for each type
     */
    public Donut(String type, String flavour) {
        this.setType(type);
        this.price = 0.0;
        this.flavour = flavour;
    }

    /**
     * Handles setting the type of the donut.
     * 
     * @param type of the donut.
     */
    public void setType(String type) {
        this.type = type;
    }

    /**
     * Handles setting the flavor of the donut.
     * 
     * @param flavour of the donut.
     */
    public void setFlavour(String flavour) {
        this.flavour = flavour;
    }

    /**
     * Not applicable for Donut
     * 
     * @param obj which is to be added
     * @return boolean always false.
     */
    public boolean add(Object obj) {
        return false;
    }

    /**
     * Not applicable for Donut
     * 
     * @param obj which is to be added
     * @return boolean always false.
     */
    public boolean remove(Object obj) {
        return false;
    }

    /**
     * Handles the pricing for the item.
     * 
     * @return double which is the price of the item up to 2 decimal places.
     */
    public double itemPrice() {
        // size
        switch (this.type) {
        case "Yeast Donut":
            this.price = 1.39;
            break;
        case "Cake Donut":
            this.price = 1.59;
            break;
        case "Donut Holes":
            this.price = 0.33;
        }
        // decimal
        this.price = Math.floor(this.price * 100) / 100;
        return this.price;
    }

    /**
     * Handles getting the price of the item
     * 
     * @return double which is the price of the item.
     */
    public double getPrice() {
        return this.price;
    }

    /**
     * Returns the formatted string of the Donut which includes price, flavor and
     * type.
     * 
     * @return String which is the formatted string of the object.
     */
    public String toString() {
        return String.valueOf("$" + this.price + "," + this.flavour + "," + this.type);
    }

}
