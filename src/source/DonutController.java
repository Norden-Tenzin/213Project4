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
import javafx.scene.control.ListView;
import javafx.scene.control.ListCell;
import javafx.scene.control.Label;
import javafx.scene.control.Button;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.Priority;

import static source.Main.currOrder;


public class DonutController {
    private String type;
    private int orderQuantity;
    private String flavor;
    private Donut donut;

    @FXML
    private TextField quantity;

    @FXML
    private ComboBox donutTypeSelect;

    @FXML
    private ComboBox donutFlavorSelect;

    @FXML
    private TextArea errorBox;

    @FXML
    private ListView orderList;

    @FXML
    private TextArea subtotalValue;

    
    @FXML
    void addToCart(ActionEvent event) {
        if(Integer.valueOf(quantity.getText())<=0)
            errorBox.setText("Quantity cannot be less than or equal to 0");
        else{
            errorBox.setText("Added to cart");
            System.out.println(orderQuantity);
            System.out.println(flavor);
            System.out.println(type);
            for(int i=0;i<orderQuantity;i++){
                currOrder.add(donut);
            }
        }
    }




    @FXML
    void removeFromCart(ActionEvent event) {
        if(Integer.valueOf(quantity.getText())<=0)
            errorBox.setText("Quantity cannot be less than or equal to 0");
        else
            errorBox.setText("Removed from cart");
    }

    @FXML
    void decreaseQuantity(ActionEvent event) {
        if(Integer.valueOf(quantity.getText())<=1)
            quantity.setText("1");
        else
            quantity.setText((Integer.valueOf(quantity.getText()) - 1) + "");
        orderQuantity = Integer.valueOf(quantity.getText());
        double price = orderQuantity * donut.itemPrice();
        errorBox.setText("Price: $"+ Math.floor(price * 100) / 100);
    }

    @FXML
    void increaseQuantity(ActionEvent event) {
      
        quantity.setText((Integer.valueOf(quantity.getText()) + 1) + "");
        orderQuantity = Integer.valueOf(quantity.getText());
        double price = orderQuantity * donut.itemPrice();
        errorBox.setText("Price: $"+ Math.floor(price * 100) / 100);
    }

    @FXML
    void selectFlavor(ActionEvent event) {
        flavor = donutFlavorSelect.getValue().toString();
        donut = new Donut(type,flavor);
        double price = orderQuantity * donut.itemPrice();
        errorBox.setText("Price: $"+ Math.floor(price * 100) / 100);
    }

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
        donut = new Donut(type,flavor);
        double price = orderQuantity * donut.itemPrice();
        errorBox.setText("Price: $"+ Math.floor(price * 100) / 100);
    }

    @FXML
    void submitOrder(ActionEvent event) {

    }

    @FXML
    void initialize() {
        orderQuantity = Integer.valueOf(quantity.getText());
        ObservableList<String> donutTypes = FXCollections.observableArrayList("Yeast Donut", "Cake Donut", "Donut Holes");
        donutTypeSelect.setItems(donutTypes);
        ObservableList<String> yeastTypes = FXCollections.observableArrayList("Glazed", "Sugar", "Boston Creme");
        donutFlavorSelect.setItems(yeastTypes);
        
        donutTypeSelect.getSelectionModel().select(0);
        donutFlavorSelect.getSelectionModel().select(0);
        
        type = donutTypeSelect.getSelectionModel().getSelectedItem().toString();
        flavor = donutFlavorSelect.getSelectionModel().getSelectedItem().toString();
        orderQuantity = 1;

        donut = new Donut(type,flavor);
        // orders = new Order();
        errorBox.setText("Price: $"+orderQuantity * donut.itemPrice());
    }

    // static class XCell extends ListCell<String> {
    //     HBox hbox = new HBox();
    //     Label label = new Label("");
    //     Pane pane = new Pane();
    //     Button button = new Button("Del");

    //     public XCell() {
    //         super();
    //         hbox.getChildren().addAll(label, pane, button);
    //         HBox.setHgrow(pane, Priority.ALWAYS);
    //         button.setOnAction(event -> getListView().getItems().remove(getItem()));
    //     }

    //     @Override
    //     protected void updateItem(String item, boolean empty) {
    //         super.updateItem(item, empty);
    //         setText(null);
    //         setGraphic(null);

    //         if (item != null && !empty) {
    //             label.setText(item);
    //             setGraphic(hbox);
    //         }
    //     }
    // }
}