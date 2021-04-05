import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Spinner;
import javafx.scene.control.TextField;
import javafx.scene.control.TextArea;

public class CoffeeController {

   private String addOnString ="";

    @FXML
    private ComboBox coffeeSizeSelect;

    @FXML
    private TextField quantity;

    @FXML
    private ComboBox coffeeSelectAddOn;

    @FXML
    private TextField addOnQuantity;

    @FXML
    private TextArea orderList;

    @FXML
    private TextArea subtotalValue;

    @FXML
    private TextArea errorBox;

    @FXML
    private TextArea addOnList;

    @FXML
    void addToCart(ActionEvent event) {
      resetOrder(event);

    }

    @FXML
    void addToOrder(ActionEvent event) {
      if(!coffeeSelectAddOn.getSelectionModel().getSelectedItem().toString().equals("None") && !addOnQuantity.getText().equals("0")){
         addOnString += addOnQuantity.getText() + "-"+coffeeSelectAddOn.getSelectionModel().getSelectedItem().toString()+", ";
         addOnList.setText(addOnString);
      }
      addOnQuantity.setText("0");
      coffeeSelectAddOn.getSelectionModel().select(0);
    }

    @FXML
    void decreaseAddOnQuantity(ActionEvent event) {
      if(Integer.valueOf(addOnQuantity.getText())<=0)
         addOnQuantity.setText("1");
      else
         addOnQuantity.setText((Integer.valueOf(addOnQuantity.getText()) - 1) + "");
    }

    @FXML
    void decreaseQuantity(ActionEvent event) {
      if(Integer.valueOf(quantity.getText())<=1)
         quantity.setText("1");
      else
         quantity.setText((Integer.valueOf(quantity.getText()) - 1) + "");
    }

    @FXML
    void increaseAddOnQuantity(ActionEvent event) {
      addOnQuantity.setText((Integer.valueOf(addOnQuantity.getText()) + 1) + "");
    }

    @FXML
    void increaseQuantity(ActionEvent event) {
      quantity.setText((Integer.valueOf(quantity.getText()) + 1) + "");
    }

    @FXML
    void removeFromCart(ActionEvent event) {

    }

    @FXML
    void selectAddOn(ActionEvent event) {

    }

    @FXML
    void selectSize(ActionEvent event) {
      
    }

    @FXML
    void submitOrder(ActionEvent event) {

    }

   @FXML
    void resetOrder(ActionEvent event) {
      addOnList.setText("");
      addOnString="";
      quantity.setText("1");
      coffeeSelectAddOn.getSelectionModel().select(0);
      addOnQuantity.setText("0");
      coffeeSizeSelect.getSelectionModel().select(0);
    }


    @FXML
    void initialize(){
      ObservableList<String> sizes = FXCollections.observableArrayList("Short","Tall", "Grande","Venti");
      coffeeSizeSelect.setItems(sizes);

      coffeeSizeSelect.getSelectionModel().select(0);

      ObservableList<String> addOns = FXCollections.observableArrayList("None","Syrup","Cream","Milk","Caramel","Whipped Cream");
      coffeeSelectAddOn.setItems(addOns);

      coffeeSelectAddOn.getSelectionModel().select(0);
    }

}
