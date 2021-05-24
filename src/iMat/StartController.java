package iMat;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.layout.AnchorPane;

import java.io.IOException;

public class StartController extends AnchorPane {

    storeController storeCtrl;
    helpController helpCtrl;

    public StartController(storeController storeCtrl, helpController helpCtrl) {
        /*FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("Start.fxml"));
        fxmlLoader.setRoot(this);
        fxmlLoader.setController(this);

        try {
            fxmlLoader.load();
        } catch (IOException exception) {
            throw new RuntimeException(exception);
        }*/

        this.helpCtrl = helpCtrl;
        this.storeCtrl = storeCtrl;
    }

    private static final Model model = Model.getInstance();

    @FXML
    private void openHelpView() {
        helpCtrl.toFront();
    }

    @FXML
    private void openStoreView() {
        storeCtrl.toFront();
    }
}

