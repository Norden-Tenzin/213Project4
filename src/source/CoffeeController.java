package source;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.ListView;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Spinner;
import javafx.scene.control.TextField;
import javafx.scene.control.TextArea;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;
import static source.Main.currOrder;

/**
 * Controller for the Coffee.fxml file. Handles UI logic.
 * @Tenzin Norden, @Vedant Mehta
 */
public class CoffeeController {

    private String addOnString = "";
    private String size;
    private Coffee coffee;
    private int coffeeQuantity;
    private int coffeeAddOnQuantity;
    ObservableList lst = FXCollections.observableArrayList();

    private double cost;

    @FXML
    private ComboBox coffeeSizeSelect;

    @FXML
    private TextField quantity;

    @FXML
    private ComboBox coffeeSelectAddOn;

    @FXML
    private TextField addOnQuantity;

    @FXML
    private ListView orderList;

    @FXML
    private TextArea subtotalValue;

    @FXML
    private TextArea errorBox;

    @FXML
    private TextArea addOnList;

    @FXML
    private ImageView coffeeImg;

    @FXML
    private ImageView milkImg;

    /**
     * Handles when the add to cart button is pressed. 
     * @param event when the add to cart button is pressed.
     */
    @FXML
    void addToCart(ActionEvent event) {
        for (int i = 0; i < coffeeQuantity; i++) {
            currOrder.add(coffee);
        }
        loadData();
        resetOrder(event);
        coffeeAddOnQuantity = 0;
        subtotalValue.setText("$"+String.valueOf(currOrder.getSubtotal()));
    }

    /**
     * Handles the add-to order event which is when the user adds add-ons to the coffee.
     * @param event when the user presses the add to order button.
     */
    @FXML
    void addToOrder(ActionEvent event) {
        if (!coffeeSelectAddOn.getSelectionModel().getSelectedItem().toString().equals("None")
                || !addOnQuantity.getText().equals("0")) {
            addOnString += addOnQuantity.getText() + "-"
                    + coffeeSelectAddOn.getSelectionModel().getSelectedItem().toString() + ", ";
            addOnList.setText(addOnString);
        }

        addAddOn(coffeeAddOnQuantity, coffeeSelectAddOn.getSelectionModel().getSelectedItem().toString());
        cost = Math.floor((coffeeQuantity * coffee.itemPrice()) * 100) / 100;
        errorBox.setText("Price: $" + cost);

        addOnQuantity.setText("0");
        coffeeSelectAddOn.getSelectionModel().select(0);
        coffeeAddOnQuantity = 0;
        subtotalValue.setText("$"+String.valueOf(currOrder.getSubtotal()));
    }

    /**
     * Handles decreasing the quantity of the add-on.
     * @param event when the decrease button is pressed.
     */
    @FXML
    void decreaseAddOnQuantity(ActionEvent event) {
        if (Integer.valueOf(addOnQuantity.getText()) <= 0)
            addOnQuantity.setText("0");
        else
            addOnQuantity.setText((Integer.valueOf(addOnQuantity.getText()) - 1) + "");
        coffeeAddOnQuantity = Integer.valueOf(addOnQuantity.getText());
        // todo: change price
        if (!coffeeSelectAddOn.getSelectionModel().getSelectedItem().toString().equals("None")) {
            cost = Math.floor((coffeeQuantity * coffee.itemPrice() + coffeeAddOnQuantity * .2) * 100) / 100;
            errorBox.setText("Price: $" + cost);
        }
        subtotalValue.setText("$"+String.valueOf(currOrder.getSubtotal()));
    }

    /**
     * Handles decreasing the amount of coffee in the order.
     * @param event is triggered when the quantity is decreased.
     */
    @FXML
    void decreaseQuantity(ActionEvent event) {
        if (Integer.valueOf(quantity.getText()) <= 1)
            quantity.setText("1");
        else
            quantity.setText((Integer.valueOf(quantity.getText()) - 1) + "");
        coffeeQuantity = Integer.valueOf(quantity.getText());
        cost = Math.floor((coffeeQuantity * coffee.itemPrice() + coffeeAddOnQuantity * .2) * 100) / 100;
        errorBox.setText("Price: $" + cost);
        subtotalValue.setText("$"+String.valueOf(currOrder.getSubtotal()));
    }

    /**
     * @param event
     */
    @FXML
    void increaseAddOnQuantity(ActionEvent event) {
        addOnQuantity.setText((Integer.valueOf(addOnQuantity.getText()) + 1) + "");
        coffeeAddOnQuantity = Integer.valueOf(addOnQuantity.getText());

        // todo:change price
        if (!coffeeSelectAddOn.getSelectionModel().getSelectedItem().toString().equals("None")) {
            cost = Math.floor((coffeeQuantity * coffee.itemPrice() + coffeeAddOnQuantity * .2) * 100) / 100;
            errorBox.setText("Price: $" + cost);
            subtotalValue.setText("$"+String.valueOf(currOrder.getSubtotal()));
        }

    }

    /**
     * Handles increasing the quantity of coffee.
     * @param event is triggered when the user increases the quantity of coffee.
     */
    @FXML
    void increaseQuantity(ActionEvent event) {
        quantity.setText((Integer.valueOf(quantity.getText()) + 1) + "");
        coffeeQuantity = Integer.valueOf(quantity.getText());
        cost = Math.floor((coffeeQuantity * coffee.itemPrice() + coffeeAddOnQuantity * .2) * 100) / 100;
        errorBox.setText("Price: $" + cost);
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
        subtotalValue.setText("$"+String.valueOf(currOrder.getSubtotal()));
    }

    /**
     * Handles selecting the add-on from the drop-down menu
     * @param event is triggered when the user clicks the dropdown menu and selects a choice.
     */
    @FXML
    void selectAddOn(ActionEvent event) {
        if (!coffeeSelectAddOn.getSelectionModel().getSelectedItem().toString().equals("None")) {
            cost = Math.floor((coffeeQuantity * coffee.itemPrice() + coffeeAddOnQuantity * .2) * 100) / 100;
            errorBox.setText("Price: $" + cost);
        }
        subtotalValue.setText("$"+String.valueOf(currOrder.getSubtotal()));
    }

    /**
     * Handles selecting the size of the coffee from the drop-down menu.
     * @param event is triggered when the user clicks the dropdown menu and selects a size.
     */
    @FXML
    void selectSize(ActionEvent event) {
        size = coffeeSizeSelect.getValue().toString();
        coffee.setSize(size);
        cost = Math.floor((coffeeQuantity * coffee.itemPrice() + coffeeAddOnQuantity * .2) * 100) / 100;
        errorBox.setText("Price: $" + cost);
        subtotalValue.setText("$"+String.valueOf(currOrder.getSubtotal()));
    }

    /**
     * Handles submitting the order, thus closing the current scene.
     * @param event is triggered when the user clicks the Submit button.
     */
    @FXML
    void submitOrder(ActionEvent event) {
        Stage stage = (Stage)orderList.getScene().getWindow();
        stage.close();
    }

    /**
     * Handles resetting all the values in the order page.
     * @param event is triggered when the user clicks the Reset Order button.
     */
    @FXML
    void resetOrder(ActionEvent event) {
        addOnList.setText("");
        addOnString = "";
        quantity.setText("1");
        coffeeSelectAddOn.getSelectionModel().select(0);
        addOnQuantity.setText("0");
        coffeeSizeSelect.getSelectionModel().select(0);

        size = coffeeSizeSelect.getSelectionModel().getSelectedItem().toString();
        coffee = new Coffee(size);
        coffeeQuantity = 1;
        coffeeAddOnQuantity = 0;
        errorBox.setText("Price: $" + coffeeQuantity * coffee.itemPrice());
        subtotalValue.setText("$"+String.valueOf(currOrder.getSubtotal()));
    }

    /**
     * Helper method for loading data into the listView
     */
    private void loadData() {
        orderList.getItems().clear();
        lst.removeAll(lst);

        String temp[] = currOrder.toString().split("\n");
        lst.addAll(temp);

        orderList.getItems().addAll(lst);
    }

    /**
     * Initialize values and set default values for variables.
     */
    @FXML
    void initialize() {

        FileInputStream coffeeInput;
        FileInputStream milkInput;

        try {
            coffeeInput = new FileInputStream("assets/coffee.png");
            milkInput = new FileInputStream("assets/milk.png");

            Image coffeeImage = new Image(coffeeInput);
            Image milkImage = new Image(milkInput);

            coffeeImg.setImage(coffeeImage);
            milkImg.setImage(milkImage);

        } catch (FileNotFoundException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        ObservableList<String> sizes = FXCollections.observableArrayList("Short", "Tall", "Grande", "Venti");
        coffeeSizeSelect.setItems(sizes);

        coffeeSizeSelect.getSelectionModel().select(0);

        ObservableList<String> addOns = FXCollections.observableArrayList("None", "Syrup", "Cream", "Milk", "Caramel",
                "Whipped Cream");
        coffeeSelectAddOn.setItems(addOns);

        coffeeSelectAddOn.getSelectionModel().select(0);
        size = coffeeSizeSelect.getSelectionModel().getSelectedItem().toString();
        coffee = new Coffee(size);
        coffeeAddOnQuantity = 0;
        coffeeQuantity = 1;
        cost = Math.floor((coffeeQuantity * coffee.itemPrice() + coffeeAddOnQuantity * .2) * 100) / 100;
        errorBox.setText("Price: $" + cost);

        loadData();
    }

    /**
     * Helper method for adding add-ons to a coffee
     * @param quantity is the quantity of add-ons
     * @param addOn is the name of the add-on
     */
    public void addAddOn(int quantity, String addOn) {
        for (int i = 0; i < quantity; i++) {
            coffee.add(addOn);
        }
    }
}
