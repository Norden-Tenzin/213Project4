package source;

/**
 * Class for creating the store orders object. Handles deleting, adding and
 * pricing orders.
 * 
 * @Tenzin Norden, @Vedant Mehta
 */

public class StoreOrders implements Customizable {
    private int UniqueOrderNum;
    private Order orders[];
    private int ordersCount;
    private int GROWSIZE = 5;

    /**
     * Store order constructor which sets default values for orders,ordercount and
     * uniqueordernumber.
     */
    public StoreOrders() {
        orders = new Order[GROWSIZE];
        ordersCount = 0;
        UniqueOrderNum = 1;
    }

    /**
     * @param item
     * @return int
     */
    public int find(Order item) {
        for (int i = 0; i < ordersCount; i++) {
            if (orders[i].getOrderNum() == item.getOrderNum()) {
                return i;
            }
        }
        return -1;
    };

    /**
     * Handles finding an order by order number
     * 
     * @param orderNum which is the unique order number.
     * @return the order which was found.
     */
    public Order findAndReturnOrder(int orderNum) {
        for (int i = 0; i < ordersCount; i++) {
            if (orders[i].getOrderNum() == orderNum) {
                return orders[i];
            }
        }
        return null;
    };

    /**
     * Handles adding an order to the data structure.
     * 
     * @param obj which is an order to be added.
     * @return boolean true if added, false otherwise.
     */
    public boolean add(Object obj) {
        if (this.orders[this.orders.length - 1] != null)
            grow();
        if (obj instanceof Order) {
            Order temp = ((Order) obj);
            temp.setOrderNum(UniqueOrderNum);
            orders[ordersCount] = temp;
            ordersCount++;
            UniqueOrderNum++;
            return true;
        }
        return false;
    };

    /**
     * Handles removing an order from the database.
     * 
     * @param obj which is the order to be removed.
     * @return boolean true if removed, false otherwise.
     */
    public boolean remove(Object obj) {
        if (obj instanceof Order) {
            int indexItem = find((Order) obj);
            if (indexItem == -1) {
                return false;
            }
            for (int i = indexItem + 1; i < this.orders.length; i++) {
                this.orders[i - 1] = this.orders[i];
            }
            if (this.ordersCount >= 0) {
                this.ordersCount--;
            }
            return true;
        }
        return false;
    };

    /**
     * Helper method for growing the data structure
     */
    public void grow() {
        Order[] temp = new Order[this.orders.length + GROWSIZE];
        for (int i = 0; i < ordersCount; i++) {
            temp[i] = this.orders[i];
        }
        this.orders = temp;
    }

    /**
     * @return the formatted string of the store order.
     */
    public String toString() {
        String output = "";
        for (Order mi : orders) {
            if (mi != null) {
                output += "Order# " + mi.getOrderNum() + "\n";
            }
        }
        return output;
    }

    /**
     * Helper method for getting the number of orders in the data structure.
     * 
     * @return int the number of orders in the list.
     */
    public String getOrderString() {
        String output = "";
        for (Order mi : orders) {
            if (mi != null) {
                output += "Order#" + mi.getOrderNum() + ":\n" + mi.toString() + "\n";
            }
        }
        return output;
    }
}
