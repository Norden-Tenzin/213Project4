package source;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 * Main class that helps start the scene. It is also where current order is stored.
 * @Tenzin Norden, @Vedant Mehta
 */
public class Main extends Application {
    public static Order currOrder = new Order();
    public static StoreOrders allOrders = new StoreOrders();
    
    
    /** 
     * Handles starting the project and opening Main Menu.
     * @param primaryStage
     * @throws Exception
     */
    @Override
    public void start(Stage primaryStage) throws Exception{
        Parent root = FXMLLoader.load(getClass().getResource("../MainMenu.fxml"));
        primaryStage.setTitle("RU Cafe Main Menu");
        primaryStage.setScene(new Scene(root));
        primaryStage.setResizable(false);
        primaryStage.show();
    }

    /** 
     * Helps launch the views.
     * @param args
     */
    public static void main(String[] args) {
        launch(args);
    }
}
