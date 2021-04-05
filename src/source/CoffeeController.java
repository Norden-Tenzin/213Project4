package source;

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
   private String size;
   private Coffee coffee;
   private int coffeeQuantity;
   private int coffeeAddOnQuantity;

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
      coffeeAddOnQuantity = 0;

    }

    @FXML
    void addToOrder(ActionEvent event) {
      if(!coffeeSelectAddOn.getSelectionModel().getSelectedItem().toString().equals("None") || !addOnQuantity.getText().equals("0")){
         addOnString += addOnQuantity.getText() + "-"+coffeeSelectAddOn.getSelectionModel().getSelectedItem().toString()+", ";
         addOnList.setText(addOnString);
      }
      addOnQuantity.setText("0");
      coffeeSelectAddOn.getSelectionModel().select(0);

      addAddOn(coffeeAddOnQuantity, coffeeSelectAddOn.getSelectionModel().getSelectedItem().toString());
      cost = Math.floor((coffeeQuantity*coffee.itemPrice())*100)/100;
      errorBox.setText("Price: $" + cost);
      coffeeAddOnQuantity = 0;
    }

    @FXML
    void decreaseAddOnQuantity(ActionEvent event) {
      if(Integer.valueOf(addOnQuantity.getText())<=0)
         addOnQuantity.setText("0");
      else
         addOnQuantity.setText((Integer.valueOf(addOnQuantity.getText()) - 1) + "");
         coffeeAddOnQuantity = Integer.valueOf(addOnQuantity.getText());
         //todo: change price
        if(!coffeeSelectAddOn.getSelectionModel().getSelectedItem().toString().equals("None")){
          cost = Math.floor((coffeeQuantity*coffee.itemPrice() + coffeeAddOnQuantity*.2)*100)/100;
          errorBox.setText("Price: $" + cost);
        }
    }

    @FXML
    void decreaseQuantity(ActionEvent event) {
      if(Integer.valueOf(quantity.getText())<=1)
         quantity.setText("1");
      else
         quantity.setText((Integer.valueOf(quantity.getText()) - 1) + "");
      coffeeQuantity=Integer.valueOf(quantity.getText());
      cost = Math.floor((coffeeQuantity*coffee.itemPrice() + coffeeAddOnQuantity*.2)*100)/100;
          errorBox.setText("Price: $" + cost);
    }

    @FXML
    void increaseAddOnQuantity(ActionEvent event) {
      addOnQuantity.setText((Integer.valueOf(addOnQuantity.getText()) + 1) + "");
      coffeeAddOnQuantity = Integer.valueOf(addOnQuantity.getText());

      //todo:change price
      if(!coffeeSelectAddOn.getSelectionModel().getSelectedItem().toString().equals("None")){
          cost = Math.floor((coffeeQuantity*coffee.itemPrice() + coffeeAddOnQuantity*.2)*100)/100;
          errorBox.setText("Price: $" + cost);
      }

    }

    @FXML
    void increaseQuantity(ActionEvent event) {
      quantity.setText((Integer.valueOf(quantity.getText()) + 1) + "");
      coffeeQuantity=Integer.valueOf(quantity.getText());
      cost = Math.floor((coffeeQuantity*coffee.itemPrice() + coffeeAddOnQuantity*.2)*100)/100;
          errorBox.setText("Price: $" + cost);
    }

    @FXML
    void removeFromCart(ActionEvent event) {
      
    }

    @FXML
    void selectAddOn(ActionEvent event) {
      if(!coffeeSelectAddOn.getSelectionModel().getSelectedItem().toString().equals("None")){
        cost = Math.floor((coffeeQuantity*coffee.itemPrice() + coffeeAddOnQuantity*.2)*100)/100;
          errorBox.setText("Price: $" + cost);
      }
    }

    @FXML
    void selectSize(ActionEvent event) {
      size = coffeeSizeSelect.getValue().toString();
      coffee.setSize(size);
      cost = Math.floor((coffeeQuantity*coffee.itemPrice() + coffeeAddOnQuantity*.2)*100)/100;
          errorBox.setText("Price: $" + cost);
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

      size = coffeeSizeSelect.getSelectionModel().getSelectedItem().toString();
      coffee = new Coffee(size);
      coffeeQuantity = 1;
      coffeeAddOnQuantity=0;
      errorBox.setText("Price: $" + coffeeQuantity*coffee.itemPrice());

    }


    @FXML
    void initialize(){
      ObservableList<String> sizes = FXCollections.observableArrayList("Short","Tall", "Grande","Venti");
      coffeeSizeSelect.setItems(sizes);

      coffeeSizeSelect.getSelectionModel().select(0);

      ObservableList<String> addOns = FXCollections.observableArrayList("None","Syrup","Cream","Milk","Caramel","Whipped Cream");
      coffeeSelectAddOn.setItems(addOns);

      coffeeSelectAddOn.getSelectionModel().select(0);
      size = coffeeSizeSelect.getSelectionModel().getSelectedItem().toString();
      coffee = new Coffee(size);
      coffeeAddOnQuantity=0;
      coffeeQuantity = 1;
      cost = Math.floor((coffeeQuantity*coffee.itemPrice() + coffeeAddOnQuantity*.2)*100)/100;
          errorBox.setText("Price: $" + cost);
    }

    public void addAddOn(int quantity, String addOn){
      for(int i=0;i<quantity;i++){
          coffee.add(addOn);
      }
      System.out.println(coffee.getPrice());
  }

}
