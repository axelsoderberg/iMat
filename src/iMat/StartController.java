package iMat;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;


public class StartController implements Initializable/*, ShoppingCartListener*/ {

    //FXML items
    @FXML private AnchorPane startPane;
    @FXML private Button startHelpButton;
    @FXML private Button startStoreButton;

    // Other variables
    iMatController controller = new iMatController();

    @Override
    public void initialize(URL url, ResourceBundle rb) {

    }

    @FXML
    private void openHelpView(ActionEvent event) {
        iMatController.setPage("help");
    }

    @FXML
    private void openStoreView(ActionEvent event) {
        iMatController.setPage("store");
    }


}

