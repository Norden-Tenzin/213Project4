package source;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.ListView;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;


public class CurrentOrders {

    @FXML
    private ListView orderList;

    @FXML
    private ImageView cartImg;

    
    /** 
     * @param event
     */
    @FXML
    void removeItem(ActionEvent event) {

    }

    
    /** 
     * @param event
     */
    @FXML
    void submitOrder(ActionEvent event) {

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
       
        
    }

}
