package source;

import javafx.scene.control.Menu;
import java.lang.Math;

/**
 * Class for creating an order object. Implements customizable and helps price
 * and organize orders.
 * 
 * @Tenzin Norden, @Vedant Mehta
 */
public class Order implements Customizable {
    private int orderNum;
    private MenuItem menuItems[];
    private int menuItemsCount;
    private int GROWSIZE = 5;

    /**
     * Constructor for order.
     */
    public Order() {
        this.menuItems = new MenuItem[GROWSIZE];
        this.menuItemsCount = 0;
    }

    /**
     * Finds any given order in the data structure.
     * 
     * @param item which is the menu item
     * @return int which is the index of the menu item, -1 if not found.
     */
    public int find(MenuItem item) {
        for (int i = 0; i < menuItemsCount; i++) {
            if (menuItems[i].toString().equals(item.toString())) {
                return i;
            }
        }
        return -1;
    };

    /**
     * Adds menu items to the data structure
     * 
     * @param obj which is to be added
     * @return boolean true if added successfully, false otherwise
     */
    public boolean add(Object obj) {
        if (this.menuItems[this.menuItems.length - 1] != null)
            grow();
        if (obj instanceof MenuItem) {
            menuItems[menuItemsCount] = (MenuItem) obj;
            menuItemsCount++;
            return true;
        }
        return false;
    };

    /**
     * Handles removing an item from the data structure.
     * 
     * @param obj which is to be removed.
     * @return boolean true if removed successfully, false otherwise.
     */
    public boolean remove(Object obj) {
        if (obj instanceof MenuItem) {
            int indexItem = find((MenuItem) obj);
            if (indexItem == -1) {
                return false;
            }
            for (int i = indexItem + 1; i < this.menuItems.length; i++) {
                this.menuItems[i - 1] = this.menuItems[i];
            }
            if (this.menuItemsCount >= 0) {
                this.menuItemsCount--;
            }
            return true;
        }
        return false;
    };

    /**
     * Helper method for growing the data structure.
     */
    public void grow() {
        MenuItem[] temp = new MenuItem[this.menuItems.length + GROWSIZE];
        for (int i = 0; i < menuItemsCount; i++) {
            temp[i] = this.menuItems[i];
        }
        this.menuItems = temp;
    }

    /**
     * Sets the order number of the order.
     * 
     * @param orderNum which is the order number.
     */
    public void setOrderNum(int orderNum) {
        this.orderNum = orderNum;
    }

    /**
     * Helper method for getting the order number of the order.
     * 
     * @return int which is the order number.
     */
    public int getOrderNum() {
        return orderNum;
    }

    /**
     * Calculates the subtotal of the order
     * 
     * @return double which is the price of the order to 2 decimal places.
     */
    public double getSubtotal() {
        double subTotal = 0.0;
        for (MenuItem mi : menuItems) {
            if (mi != null) {
                if (mi instanceof Coffee) {
                    subTotal += ((Coffee) mi).getPrice();
                } else if (mi instanceof Donut) {
                    subTotal += ((Donut) mi).getPrice();
                }
            }
        }
        return Math.floor((subTotal) * 100) / 100;
    }

    /**
     * Calculates the sales tax of the given order.
     * 
     * @return double the sales tax.
     */
    public double getSalesTax() {
        double subTotal = getSubtotal();
        double percentage = 6.625;
        return Math.floor(((percentage / 100.0) * subTotal) * 100) / 100;
    }

    /**
     * Gets the total cost of the order which is subtotal + salestax.
     * 
     * @return double the total cost.
     */
    public double getTotal() {
        double subTotal = getSubtotal();
        double tax = getSalesTax();
        return Math.floor((subTotal + tax) * 100) / 100;
    }

    /**
     * Formatted string of the order separated by new lines.
     * 
     * @return String which is the formatted string of the order
     */
    public String toString() {
        String output = "";
        for (MenuItem mi : menuItems) {
            if (mi != null) {
                output += mi.toString() + "\n";
            }
        }
        return output;
    }

}
