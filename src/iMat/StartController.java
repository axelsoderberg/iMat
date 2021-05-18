package iMat;

import javafx.fxml.FXML;

public class StartController {

    @FXML
    private void openHelpView() {
        iMatController.setPage("help");
    }

    @FXML
    private void openStoreView() {
        iMatController.setPage("store");
    }
}

