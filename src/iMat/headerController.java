package iMat;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;


public class headerController {
    //FXML items
    @FXML private AnchorPane headerPane;
    @FXML private Button headerHelpButton;
    @FXML private Button headerStoreButton;


    @FXML
    private void openHelpView(ActionEvent event) {
        iMatController.setPage("help");
    }

    @FXML
    private void openStoreView(ActionEvent event) {
        iMatController.setPage("store");
    }

}
