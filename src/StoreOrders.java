public class StoreOrders implements Customizable{
    private int UniqueOrderNum;
    private Order orders[];
    private int ordersCount;
    private int GROWSIZE = 5;

    public StoreOrders(){
        orders = new Order[GROWSIZE];
        ordersCount = 0;
        UniqueOrderNum = 1;
    }

    public int find(Order item){
        for(int i = 0; i < ordersCount; i++){
            if(orders[i].getOrderNum() == item.getOrderNum()){
                return i;
            }
        }
        return -1;
    };

    public boolean add(Object obj){
        if (this.orders[this.orders.length - 1] != null)
           grow();
        if(obj instanceof Order){
            Order temp = ((Order)obj);
            temp.setOrderNum(UniqueOrderNum);
            orders[ordersCount] = temp;
            ordersCount ++;
            UniqueOrderNum ++;
            return true;
        }
        return false;
    };

    public boolean remove(Object obj){
        if(obj instanceof Order){
            int indexItem = find((Order)obj);
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

    public void grow(){
        Order[] temp = new Order[this.orders.length + GROWSIZE];
        for (int i = 0; i < ordersCount; i++) {
            temp[i] = this.orders[i];
        }
        this.orders = temp;
    }
}
