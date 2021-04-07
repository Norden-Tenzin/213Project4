package source;

import java.lang.Math;
/**
 * Class used to create a Coffee object. Handles adding add-ons, defining size, and pricing
 * @Tenzin Norden, @Vedant Mehta
 */
public class Coffee extends MenuItem implements Customizable {
    private double price;
    private String size;
    private String[] addIn;
    private int addInCount;
    private int GROWSIZE = 5;

    /**
     * Coffee constructor which takes in size and creates default values for addin, price and addin count.
     * @param size of the coffee
     */
    public Coffee(String size) {
        this.setSize(size);
        this.price = 0.0;
        this.addIn = new String[5];
        this.addInCount = 0;
    }

    /**
     * sets the coffee size.
     * @param size of the coffee
     */
    public void setSize(String size) {
        this.size = size;
    }

    /**
     * Adds add-ons to the coffee.
     * @param obj that is to be added
     * @return boolean true if added correctly, false otherwise.
     */
    public boolean add(Object obj) {
        if (this.addIn[this.addIn.length - 1] != null)
            grow();
        if (obj instanceof String) {
            // add string to lst
            addIn[addInCount] = (String) obj;
            addInCount++;
            return true;
        }
        return false;
    }

    /**
     * Handles remove. Not applicable for Coffee. 
     * @param obj that is removed
     * @return boolean always returns true since it is not applicable in Coffee.
     */
    public boolean remove(Object obj) {
        return true;
    }

    /**
     * Grows the data structure to make room for the new values.
     */
    private void grow() {
        String[] temp = new String[this.addIn.length + GROWSIZE];

        for (int i = 0; i < addInCount; i++) {
            temp[i] = this.addIn[i];
        }
        this.addIn = temp;
    }

    /**
     * Handles pricing of the item by taking base cost and adding 20 cents for each additional add-on.
     * @return double which represents the price of the item
     */
    public double itemPrice() {
        // size
        switch (this.size) {
        case "Short":
            this.price = 1.99;
            break;
        case "Tall":
            this.price = 1.99 + 0.50;
            break;
        case "Grande":
            this.price = 1.99 + 1.00;
            break;
        case "Venti":
            this.price = 1.99 + 1.50;
            break;
        }

        // addin
        this.price += this.addInCount * 0.20;
        // decimal
        this.price = Math.floor(this.price * 100) / 100;
        return this.price;
    }

    /**
     * Returns the price of the item
     * @return double which is the price
     */
    public double getPrice() {
        return this.price;
    }

    /**
     * Returns the formatted String
     * @return String which outputs the item as well as the add-ons
     */
    public String toString() {
        String strAddIN = "";
        for (String str : addIn) {
            if (str != null) {
                strAddIN += "+" + str;
            }
        }
        return String.valueOf("$"+this.price + "," + this.size + " " + strAddIN);
    }
    
}
