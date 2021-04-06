package source;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.net.URL;
import java.util.ResourceBundle;
import javax.swing.event.MenuKeyEvent;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Spinner;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.control.TextArea;
import javafx.scene.control.ListView;
import javafx.scene.control.ListCell;
import javafx.scene.control.Label;
import javafx.scene.control.Button;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.Priority;
import javafx.stage.Stage;
import static source.Main.currOrder;

/**
 * Donut Controller class for the Donut.fxml. Handles UI logic.
 * @Tenzin Norden, @Vedant Mehta
 */
public class DonutController {
    private String type;
    private int orderQuantity;
    private String flavor;
    private Donut donut;
    ObservableList lst = FXCollections.observableArrayList();

    @FXML
    private TextField quantity;

    @FXML
    private ComboBox donutTypeSelect;

    @FXML
    private ComboBox donutFlavorSelect;

    @FXML
    private TextArea errorBox;

    @FXML
    private ListView<MenuItem> orderList;

    @FXML
    private TextArea subtotalValue;

    @FXML
    private ImageView donutImg;

     /**
     * Handles when the add to cart button is pressed. 
     * @param event when the add to cart button is pressed.
     */
    @FXML
    void addToCart(ActionEvent event) {
        if (Integer.valueOf(quantity.getText()) <= 0)
            errorBox.setText("Quantity cannot be less than or equal to 0");
        else {
            errorBox.setText("Added to cart");
            for (int i = 0; i < orderQuantity; i++) {
                Donut temp = new Donut(type, flavor);
                temp.itemPrice();
                currOrder.add(temp);
            }
        }
        loadData();
        subtotalValue.setText("$"+String.valueOf(currOrder.getSubtotal()));
    }

    /**
     * Handles removing an item from the cart.
     * @param event is triggered when the user clicks the Remove From Cart button.
     */
    @FXML
    void removeFromCart(ActionEvent event) {
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

            //deleting from current order
            currOrder.remove(tmpItem);

        }
        else
            errorBox.setText("Unable to remove from cart");
        
       


        loadData();
        // ObservableList
        subtotalValue.setText("$"+String.valueOf(currOrder.getSubtotal()));
    }

    /**
     * Handles decreasing the amount of donut in the order.
     * @param event is triggered when the quantity is decreased.
     */
    @FXML
    void decreaseQuantity(ActionEvent event) {
        if (Integer.valueOf(quantity.getText()) <= 1)
            quantity.setText("1");
        else
            quantity.setText((Integer.valueOf(quantity.getText()) - 1) + "");
        orderQuantity = Integer.valueOf(quantity.getText());
        double price = orderQuantity * donut.itemPrice();
        errorBox.setText("Price: $" + Math.floor(price * 100) / 100);
        subtotalValue.setText("$"+String.valueOf(currOrder.getSubtotal()));
    }

    /**
     * Handles increasing the quantity of donut.
     * @param event is triggered when the user increases the quantity of donut.
     */
    @FXML
    void increaseQuantity(ActionEvent event) {
        quantity.setText((Integer.valueOf(quantity.getText()) + 1) + "");
        orderQuantity = Integer.valueOf(quantity.getText());
        double price = orderQuantity * donut.itemPrice();
        errorBox.setText("Price: $" + Math.floor(price * 100) / 100);
        subtotalValue.setText("$"+String.valueOf(currOrder.getSubtotal()));
    }

    /**
     * Handles selecting a flavor of donut when the dropdown is pressed
     * @param event is triggered when the user selects a drop down item.
     */
    @FXML
    void selectFlavor(ActionEvent event) {
        if(donutFlavorSelect.getValue()!=null){
            flavor = donutFlavorSelect.getValue().toString();
            donut = new Donut(type, flavor);
            double price = orderQuantity * donut.itemPrice();
            errorBox.setText("Price: $" + Math.floor(price * 100) / 100);
            subtotalValue.setText("$"+String.valueOf(currOrder.getSubtotal()));
        }
    }

    /**
     * Handles selecting the type of donut and changing the flavor types accordingly.
     * @param event is triggered when the user selects the type drop down.
     */
    @FXML
    void selectType(ActionEvent event) {
        if (donutTypeSelect.getValue().toString().equals("Yeast Donut")) {
            ObservableList<String> yeastTypes = FXCollections.observableArrayList("Glazed", "Sugar", "Boston Creme");
            donutFlavorSelect.setItems(yeastTypes);
            donutFlavorSelect.getSelectionModel().select(0);
        } else if (donutTypeSelect.getValue().toString().equals("Cake Donut")) {
            ObservableList<String> cakeTypes = FXCollections.observableArrayList("Chocolate", "Strawberry", "Vanilla");
            donutFlavorSelect.setItems(cakeTypes);
            donutFlavorSelect.getSelectionModel().select(0);
        } else if (donutTypeSelect.getValue().toString().equals("Donut Holes")) {
            ObservableList<String> holeTypes = FXCollections.observableArrayList("Powder", "Chocolate Glazed", "Jelly");
            donutFlavorSelect.setItems(holeTypes);
            donutFlavorSelect.getSelectionModel().select(0);
        }
        type = donutTypeSelect.getSelectionModel().getSelectedItem().toString();
        donut = new Donut(type, flavor);
        double price = orderQuantity * donut.itemPrice();
        errorBox.setText("Price: $" + Math.floor(price * 100) / 100);
    }

    /**
     * Handles submitting the order and thus closing the scene.
     * @param event is triggered when the user selects the Submit button.
     */
    @FXML
    void submitOrder(ActionEvent event) {
        Stage stage = (Stage)orderList.getScene().getWindow();
        stage.close();
    }

    /**
     * Used to initialize default values and images.
     */
    @FXML
    void initialize() {
        FileInputStream input;
        try {
            input = new FileInputStream("assets/donut.png");
            Image img = new Image(input);
            donutImg.setImage(img);
        } catch (FileNotFoundException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        orderQuantity = Integer.valueOf(quantity.getText());
        ObservableList<String> donutTypes = FXCollections.observableArrayList("Yeast Donut", "Cake Donut",
                "Donut Holes");
        donutTypeSelect.setItems(donutTypes);
        ObservableList<String> yeastTypes = FXCollections.observableArrayList("Glazed", "Sugar", "Boston Creme");
        donutFlavorSelect.setItems(yeastTypes);

        donutTypeSelect.getSelectionModel().select(0);
        donutFlavorSelect.getSelectionModel().select(0);

        type = donutTypeSelect.getSelectionModel().getSelectedItem().toString();
        flavor = donutFlavorSelect.getSelectionModel().getSelectedItem().toString();
        orderQuantity = 1;

        donut = new Donut(type, flavor);
        // orders = new Order();

        loadData();
        errorBox.setText("Price: $" + orderQuantity * donut.itemPrice());
        subtotalValue.setText("$"+String.valueOf(currOrder.getSubtotal()));
    }

    /**
     * Helper method for loading data into the list view.
     */
    private void loadData() {
        orderList.getItems().clear();
        lst.removeAll(lst);

        String temp[] = currOrder.toString().split("\n");
        lst.addAll(temp);

        orderList.getItems().addAll(lst);
    }
}