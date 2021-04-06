package source;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;

public class StoreOrdersController {

    @FXML
    private ListView orderList;

    @FXML
    private TextField totalAmount;

    @FXML
    private CheckBox deleteCheckbox;

    @FXML
    private CheckBox exportCheckbox;

    @FXML
    private ListView orderDetailsList;
   
    @FXML
    void showOrder(ActionEvent event) {

    }

    @FXML
    void selectDelete(ActionEvent event) {
       exportCheckbox.setSelected(false);

    }

    @FXML
    void selectExport(ActionEvent event) {
      deleteCheckbox.setSelected(false);

    }

    @FXML
    void submitAction(ActionEvent event) {

    }

}
