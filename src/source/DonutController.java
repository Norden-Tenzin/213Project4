package source;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.net.URL;
import java.util.ResourceBundle;
import javax.swing.event.MenuKeyEvent;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
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

import static source.Main.currOrder;

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
     * @param event
     */
    @FXML
    void addToCart(ActionEvent event) {
        if(Integer.valueOf(quantity.getText())<=0)
            errorBox.setText("Quantity cannot be less than or equal to 0");
        else{
            errorBox.setText("Added to cart");
            System.out.println(orderQuantity);
            System.out.println(flavor);
            System.out.println(type);
            for(int i = 0; i < orderQuantity; i++){
                Donut temp = new Donut(type, flavor);
                temp.itemPrice();
                currOrder.add(temp);
            }
        }
        loadData();
        subtotalValue.setText(String.valueOf(currOrder.getSubtotal()));
    }

    /** 
     * @param event
     */
    @FXML
    void removeFromCart(ActionEvent event) {
        if(Integer.valueOf(quantity.getText())<=0)
            errorBox.setText("Quantity cannot be less than or equal to 0");
        else
            errorBox.setText("Removed from cart");


        // ObservableList
        subtotalValue.setText(String.valueOf(currOrder.getSubtotal()));
    }

    
    /** 
     * @param event
     */
    @FXML
    void decreaseQuantity(ActionEvent event) {
        if(Integer.valueOf(quantity.getText())<=1)
            quantity.setText("1");
        else
            quantity.setText((Integer.valueOf(quantity.getText()) - 1) + "");
        orderQuantity = Integer.valueOf(quantity.getText());
        double price = orderQuantity * donut.itemPrice();
        errorBox.setText("Price: $"+ Math.floor(price * 100) / 100);
        subtotalValue.setText(String.valueOf(currOrder.getSubtotal()));
    }

    
    /** 
     * @param event
     */
    @FXML
    void increaseQuantity(ActionEvent event) {
        quantity.setText((Integer.valueOf(quantity.getText()) + 1) + "");
        orderQuantity = Integer.valueOf(quantity.getText());
        double price = orderQuantity * donut.itemPrice();
        errorBox.setText("Price: $"+ Math.floor(price * 100) / 100);
        subtotalValue.setText(String.valueOf(currOrder.getSubtotal()));
    }

    
    /** 
     * @param event
     */
    @FXML
    void selectFlavor(ActionEvent event) {
        flavor = donutFlavorSelect.getValue().toString();
        donut = new Donut(type,flavor);
        double price = orderQuantity * donut.itemPrice();
        errorBox.setText("Price: $"+ Math.floor(price * 100) / 100);
        subtotalValue.setText(String.valueOf(currOrder.getSubtotal()));
    }

    
    /** 
     * @param event
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
        donut = new Donut(type,flavor);
        double price = orderQuantity * donut.itemPrice();
        errorBox.setText("Price: $"+ Math.floor(price * 100) / 100);
    }

    
    /** 
     * @param event
     */
    @FXML
    void submitOrder(ActionEvent event) {

    }

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

        loadData();
        errorBox.setText("Price: $"+orderQuantity * donut.itemPrice());
        subtotalValue.setText(String.valueOf(currOrder.getSubtotal()));
    }

    private void loadData(){
        orderList.getItems().clear();
        lst.removeAll(lst);

        String temp[] = currOrder.toString().split("\n");
        lst.addAll(temp);

        orderList.getItems().addAll(lst);
    }

    /** 
     * @param e
     */
    private void displaySelected(MouseEvent e){
        MenuItem item = orderList.getSelectionModel().getSelectedItem();
        if(item != null){
            System.out.println(item.toString());
        }
    }
}