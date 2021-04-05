public class Order implements Customizable{
    private int orderNum;
    private MenuItem menuItems[];
    private int menuItemsCount;
    private int GROWSIZE = 5;

    public Order(int orderNum){
        this.orderNum = orderNum;
        this.menuItems = new MenuItem[GROWSIZE];
        this.menuItemsCount = 0;
    }

    public int find(MenuItem item){
        for(int i = 0; i < menuItemsCount; i++){
            if(menuItems[i].toString().equals(item.toString())){
                return i;
            }
        }
        return -1;
    };

    public boolean add(Object obj){
        if (this.menuItems[this.menuItems.length - 1] != null)
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
        MenuItem[] temp = new MenuItem[this.menuItems.length + GROWSIZE];
        for (int i = 0; i < menuItemsCount; i++) {
            temp[i] = this.menuItems[i];
        }
        this.menuItems = temp;
    }
    
    public String toString(){
        String output = "";
        for(MenuItem mi: menuItems){
            if(mi != null){
                output += mi.toString() + "\n";
            }
        }
        return output;
    }

    // public static void main(String[] args) {
    //     Order o = new Order();
    //     Coffee cf = new Coffee("Venti");
    //     cf.add("cream");
    //     o.add(cf);

    //     Donut d = new Donut("YeastDonut", "BLue");
    //     d.setFlavour("Blue");
    //     d.itemPrice();
    //     o.add(d);
    //     System.out.println(o.toString());

    //     o.remove(cf);
    //     System.out.println(o.toString());

    //     o.remove(d);
    //     System.out.println(o.toString());
    // }
}
