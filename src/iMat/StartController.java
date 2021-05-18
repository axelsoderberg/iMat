package iMat;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;


public class StartController /*, ShoppingCartListener*/ {

    //FXML items
    @FXML private AnchorPane startPane;
    @FXML private Button startHelpButton;
    @FXML private Button startStoreButton;

    // Other variables
    iMatController controller = new iMatController();

    @FXML
    private void openHelpView(ActionEvent event) {
        iMatController.setPage("help");
    }

    @FXML
    private void openStoreView(ActionEvent event) {
        iMatController.setPage("store");
    }


}

