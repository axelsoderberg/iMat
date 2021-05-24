package iMat;

import javafx.fxml.FXMLLoader;
import javafx.scene.layout.AnchorPane;

import java.io.IOException;

public class shoppinglistsController extends AnchorPane {

    public shoppinglistsController() {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("shoppinglists.fxml"));
        fxmlLoader.setRoot(this);
        fxmlLoader.setController(this);

        try {
            fxmlLoader.load();
        } catch (IOException exception) {
            throw new RuntimeException(exception);
        }
    }

}
