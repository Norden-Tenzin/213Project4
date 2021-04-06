package source;

import java.io.FileNotFoundException;
import java.io.PrintWriter;
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

/**
 * StoreOrdersController is used for the logic of the StoreOrders.fxml file
 * 
 * @Tenzin Norden, @Vedant Mehta
 */
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

    /**
     * Handles the showing of an order when clicking on the listView of orders.
     * @param event
     */
    @FXML
    void showOrder(MouseEvent event) {
        if (orderList.getSelectionModel().getSelectedIndex() != -1) {
            Object obj = orderList.getSelectionModel().getSelectedItem();
            String[] donutString = obj.toString().split(",");
            String orderNum = donutString[0].split(" ")[1];
            Order temp = allOrders.findAndReturnOrder(Integer.parseInt(orderNum));
            if (temp != null) {
                orderDetailsList.setText(temp.toString());
                subtotalValue.setText(String.valueOf(temp.getSubtotal()));
                taxValue.setText(String.valueOf(temp.getSalesTax()));
                totalValue.setText(String.valueOf(temp.getTotal()));
            }
        }
    }

    /** 
    * Handles the checkbox selectDelete such that when true it sets the value of selectExport to False
    * @param event
    */
    @FXML
    void selectDelete(ActionEvent event) {
        exportCheckbox.setSelected(false);
    }

    /** 
    * Handles the checkbox selectExport such that when true it sets the value of selectDelete to False
    * @param event
    */
    @FXML
    void selectExport(ActionEvent event) {
        deleteCheckbox.setSelected(false);
    }

    /** 
    * Handles the event for the submit button. if deleteCheckbox is true its deletes the selected order. 
    * Otherwise if exportCheckbox is true then exports the orders as a text file.
    * @param event
    * @throws FileNotFoundException
    */
    @FXML
    void submitAction(ActionEvent event) throws FileNotFoundException {
        if (deleteCheckbox.isSelected()) {
            if (orderList.getSelectionModel().getSelectedIndex() != -1) {
                Object obj = orderList.getSelectionModel().getSelectedItem();
                String[] donutString = obj.toString().split(",");
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
        if (exportCheckbox.isSelected()) {
            String filename = "orders.txt";
            String output = allOrders.getOrderString();
            try (PrintWriter out = new PrintWriter(filename)) {
                out.println(output);
            }
        }
    }

    /** 
    * Initializes default values.
    */
    @FXML
    void initialize() {
        loadData();
    }

    
    /** 
    * loadDate Handles inputting the data from StoreOrders to the ListView.
    */
    private void loadData() {
        orderList.getItems().clear();
        lst.removeAll(lst);

        String temp[] = allOrders.toString().split("\n");
        lst.addAll(temp);

        orderList.getItems().addAll(lst);
    }

}
