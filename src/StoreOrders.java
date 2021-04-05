public class StoreOrders implements Customizable{
    private int startingOrderNum;
    private Order orders[];
    private int ordersCount;
    private int GROWSIZE = 5;

    public StoreOrders(){
        orders = new Order[GROWSIZE];
        ordersCount = 0;
    }

    // public int find(MenuItem item){
    //     for(int i = 0; i < ordersCount; i++){
    //         if(orders[i].toString().equals(item.toString())){
    //             return i;
    //         }
    //     }
    //     return -1;
    // };

    public boolean add(Object obj){
        if (this.orders[this.orders.length - 1] != null)
           grow();
        if(obj instanceof MenuItem){
            menuItems[menuItemsCount] = (MenuItem)obj;
            menuItemsCount ++;
            return true;
        }
        return false;
    };

    public boolean remove(Object obj){
        if(obj instanceof MenuItem){
            int indexItem = find((MenuItem)obj);
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

    public void grow(){
        Order[] temp = new Order[this.orders.length + GROWSIZE];
        for (int i = 0; i < ordersCount; i++) {
            temp[i] = this.orders[i];
        }
        this.orders = temp;
    }
}
