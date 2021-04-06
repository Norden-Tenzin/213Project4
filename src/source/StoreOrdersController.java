package source;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.control.TextArea;
import javafx.scene.input.MouseEvent;
import javafx.collections.ObservableList;
import javafx.collections.FXCollections;

import static source.Main.currOrder;
import static source.Main.allOrders;

public class StoreOrdersController {
    ObservableList lst = FXCollections.observableArrayList();

    @FXML
    private ListView<?> orderList;

    @FXML
    private TextField subtotalValue;

    @FXML
    private CheckBox deleteCheckbox;

    @FXML
    private CheckBox exportCheckbox;

    @FXML
    private TextField taxValue;

    @FXML
    private TextField totalValue;

    @FXML
    private TextArea orderDetailsList;

    @FXML
    void showOrder(MouseEvent event) {
        if(orderList.getSelectionModel().getSelectedIndex()!=-1){
            Object obj = orderList.getSelectionModel().getSelectedItem();
            String [] donutString = obj.toString().split(",");
            String orderNum = donutString[0].split(" ")[1];
            Order temp = allOrders.findAndReturnOrder(Integer.parseInt(orderNum));
            if(temp != null){
                orderDetailsList.setText(temp.toString());
                subtotalValue.setText(String.valueOf(temp.getSubtotal()));
                taxValue.setText(String.valueOf(temp.getSalesTax()));
                totalValue.setText(String.valueOf(temp.getTotal()));
            }
        }
    }

    @FXML
    void selectDelete(ActionEvent event) {
        exportCheckbox.setSelected(false);
    }

    @FXML
    void selectExport(ActionEvent event) {
        deleteCheckbox.setSelected(false);
    }

    @FXML
    void submitAction(ActionEvent event) {
        if(deleteCheckbox.isSelected()){
            if(orderList.getSelectionModel().getSelectedIndex()!=-1){
                Object obj = orderList.getSelectionModel().getSelectedItem();
                String [] donutString = obj.toString().split(",");
                String orderNum = donutString[0].split(" ")[1];
                Order temp = allOrders.findAndReturnOrder(Integer.parseInt(orderNum));
                allOrders.remove(temp);
                
                loadData();
                orderDetailsList.setText("");
                subtotalValue.setText(String.valueOf(0.0));
                taxValue.setText(String.valueOf(0.0));
                totalValue.setText(String.valueOf(0.0));
            }
        }
    }

    @FXML
    void initialize(){
        loadData();
    }

    private void loadData() {
        orderList.getItems().clear();
        lst.removeAll(lst);

        String temp[] = allOrders.toString().split("\n");
        lst.addAll(temp);

        orderList.getItems().addAll(lst);
    }
}
