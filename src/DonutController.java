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

public class DonutController {

    private String type;
    private int orderQuantity;
    private String flavor;

    private Donut donut = null;

    @FXML
    private TextField quantity;

    @FXML
    private ComboBox donutTypeSelect;

    @FXML
    private ComboBox donutFlavorSelect;

    @FXML
    private TextArea errorBox;

    @FXML
    private TextArea orderList;

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
        errorBox.setText("Price: $"+orderQuantity * donut.getPrice() + "\n" + donut.getPrice());
    }

    @FXML
    void increaseQuantity(ActionEvent event) {
      
        quantity.setText((Integer.valueOf(quantity.getText()) + 1) + "");
        orderQuantity = Integer.valueOf(quantity.getText());
        errorBox.setText("Price: $"+orderQuantity * donut.getPrice() + "\n" + donut.getPrice());
    }

    @FXML
    void selectFlavor(ActionEvent event) {
        flavor = donutFlavorSelect.getValue().toString();
        donut = new Donut(type,flavor);
        errorBox.setText("Price: $"+orderQuantity * donut.getPrice() + "\n" + donut.getPrice());
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
        errorBox.setText("Price: $"+orderQuantity * donut.getPrice());
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
        errorBox.setText("Price: $"+orderQuantity * donut.getPrice());
    }
    
}