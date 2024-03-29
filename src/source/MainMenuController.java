package source;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Modality;
import javafx.stage.Stage;
import java.io.IOException;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

/**
 * Main menu controller used for the logic in the Main Menu fxml file.
 * 
 * @Tenzin Norden, @Vedant Mehta
 */
public class MainMenuController {

  @FXML
  private ImageView coffeeImg;

  @FXML
  private ImageView donutImg;

  @FXML
  private ImageView storeImg;

  @FXML
  private ImageView orderImg;

  /**
   * Handles opening the order coffee scene.
   * 
   * @param event
   * @throws IOException
   */
  public void OrderCoffee(ActionEvent event) throws IOException {
    FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("../Coffee.fxml"));
    Parent root1 = (Parent) fxmlLoader.load();
    Stage stage = new Stage();
    stage.setTitle("Order Coffee");
    stage.setResizable(false);
    stage.setScene(new Scene(root1));

    // After the window is open, make main menu disabled
    stage.initModality(Modality.APPLICATION_MODAL);
    stage.showAndWait();
  }

  /**
   * Handles opening the order donuts page
   * 
   * @param event
   * @throws IOException
   */
  public void OrderDoughnuts(ActionEvent event) throws IOException {
    FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("../Donut.fxml"));
    Parent root1 = (Parent) fxmlLoader.load();
    Stage stage = new Stage();
    stage.setTitle("Order Donuts");
    stage.setResizable(false);
    stage.setScene(new Scene(root1));

    // After the window is open, make main menu disabled
    stage.initModality(Modality.APPLICATION_MODAL);
    stage.showAndWait();
  }

  /**
   * Handles opening the current orders page.
   * 
   * @param event
   * @throws IOException
   */
  public void OpenCurrentOrders(ActionEvent event) throws IOException {
    FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("../CurrentOrders.fxml"));
    Parent root1 = (Parent) fxmlLoader.load();
    Stage stage = new Stage();
    stage.setTitle("Current Order");
    stage.setResizable(false);
    stage.setScene(new Scene(root1));

    // After the window is open, make main menu disabled
    stage.initModality(Modality.APPLICATION_MODAL);
    stage.showAndWait();
  }

  /**
   * Handles opening the store orders page.
   * 
   * @param event
   * @throws IOException
   */
  public void OpenStoreOrder(ActionEvent event) throws IOException {
    FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("../StoreOrders.fxml"));
    Parent root1 = (Parent) fxmlLoader.load();
    Stage stage = new Stage();
    stage.setTitle("Current Order");
    stage.setResizable(false);
    stage.setScene(new Scene(root1));

    // After the window is open, make main menu disabled
    stage.initModality(Modality.APPLICATION_MODAL);
    stage.showAndWait();

  }

  /**
   * Initializes default values and images.
   */
  @FXML
  void initialize() {
    FileInputStream coffeeInput;
    FileInputStream donutInput;
    FileInputStream cartInput;
    FileInputStream storeInput;

    try {
      coffeeInput = new FileInputStream("assets/coffee.png");
      donutInput = new FileInputStream("assets/donut.png");
      cartInput = new FileInputStream("assets/cart.png");
      storeInput = new FileInputStream("assets/ruCafe.jpeg");

      Image coffeeImage = new Image(coffeeInput);
      Image donutImage = new Image(donutInput);
      Image cartImage = new Image(cartInput);
      Image storeImage = new Image(storeInput);

      coffeeImg.setImage(coffeeImage);
      donutImg.setImage(donutImage);
      orderImg.setImage(cartImage);
      storeImg.setImage(storeImage);

    } catch (FileNotFoundException e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
    }

  }

}
