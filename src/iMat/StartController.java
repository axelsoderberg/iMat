package iMat;

import javafx.fxml.FXML;

public class StartController {

    private static final Model model = Model.getInstance();

    @FXML
    private void openHelpView() {
        iMatController.setPage("help");
    }

    @FXML
    private void openStoreView() {
        //storeController.updateStore(model.getProducts());
        iMatController.setPage("store");
    }
}

