package source;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.collections.ObservableList;
import javafx.collections.FXCollections;

import static source.Main.currOrder;
import static source.Main.allOrders;;

public class CurrentOrderController {
    ObservableList lst = FXCollections.observableArrayList();

    @FXML
    private ListView<?> orderList;

    @FXML
    private ImageView cartImg;

    @FXML
    private TextField subtotalValue;

    @FXML
    private TextField taxValue;

    @FXML
    private TextField totalValue;

    /**
     * @param event
     */
    @FXML
    void removeItem(ActionEvent event) {
        MenuItem tmpItem;
        if(orderList.getSelectionModel().getSelectedIndex()!=-1){
            Object obj = orderList.getSelectionModel().getSelectedItem();
            
            String [] donutString = obj.toString().split(",");
            if(donutString.length==2){
                tmpItem = new Coffee(donutString[1].split(" ")[0]);
                if(donutString[1].split(" ").length>=2){
                    String[]addons = donutString[1].split(" ")[1].split("\\+");
                    for(String item : addons){
                        if(!item.equals(""))
                        ((Coffee)tmpItem).add(item);
                    }
                }
            }
            else{
                tmpItem = new Donut(donutString[2],donutString[1]);
            }
            
            tmpItem.itemPrice();
            System.out.println(currOrder.toString());
            //deleting from current order
            currOrder.remove(tmpItem);
            System.out.println(currOrder.toString());
        }
        loadData();
        // ObservableList
        subtotalValue.setText("$"+String.valueOf(currOrder.getSubtotal()));
        taxValue.setText("$"+String.valueOf(currOrder.getSalesTax()));
        totalValue.setText("$"+String.valueOf(currOrder.getTotal()));
    }
    
    /** 
     * @param event
     */
    @FXML
    void submitOrder(ActionEvent event) {
        allOrders.add(currOrder);
        currOrder = new Order();

        loadData();
        subtotalValue.setText("$"+String.valueOf(currOrder.getSubtotal()));
        taxValue.setText("$"+String.valueOf(currOrder.getSalesTax()));
        totalValue.setText("$"+String.valueOf(currOrder.getTotal()));
    }

    @FXML
    void initialize(){
        FileInputStream input;
        try {
            input = new FileInputStream("assets/cart.png");
            Image img = new Image(input);
            cartImg.setImage(img);
        } catch (FileNotFoundException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        
        loadData();
        subtotalValue.setText("$"+String.valueOf(currOrder.getSubtotal()));
        taxValue.setText("$"+String.valueOf(currOrder.getSalesTax()));
        totalValue.setText("$"+String.valueOf(currOrder.getTotal()));
    }

    private void loadData() {
        orderList.getItems().clear();
        lst.removeAll(lst);

        String temp[] = currOrder.toString().split("\n");
        lst.addAll(temp);

        orderList.getItems().addAll(lst);
    }
}
