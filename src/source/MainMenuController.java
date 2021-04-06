package source;

import java.io.IOException;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Modality;
import javafx.stage.Stage;
import java.io.IOException;

public class MainMenuController {

   //  @FXML
    public void OrderCoffee(ActionEvent event) throws IOException {
      FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("../Coffee.fxml"));
      Parent root1 = (Parent) fxmlLoader.load();
      Stage stage = new Stage();
      stage.setTitle("Order Coffee");
      stage.setResizable(false);
      stage.setScene(new Scene(root1));

      //After the window is open, make main menu disabled
      stage.initModality(Modality.APPLICATION_MODAL);
      stage.showAndWait();
    }

   //  @FXML
    public void OrderDoughnuts(ActionEvent event) throws IOException {
      FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("../Donut.fxml"));
      Parent root1 = (Parent) fxmlLoader.load();
      Stage stage = new Stage();
      stage.setTitle("Order Donuts");
      stage.setResizable(false);
      stage.setScene(new Scene(root1));

      //After the window is open, make main menu disabled
      stage.initModality(Modality.APPLICATION_MODAL);
      stage.showAndWait();
    }
    
    // @FXML
    public void OpenCurrentOrders(ActionEvent event) throws IOException {
      FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("../CurrentOrders.fxml"));
      Parent root1 = (Parent) fxmlLoader.load();
      Stage stage = new Stage();
      stage.setTitle("Current Order");
      stage.setResizable(false);
      stage.setScene(new Scene(root1));

      //After the window is open, make main menu disabled
      stage.initModality(Modality.APPLICATION_MODAL);
      stage.showAndWait();
    }

    // @FXML
    public void OpenStoreOrder(ActionEvent event) {

    }


}
