package iMat;

import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.layout.AnchorPane;

import java.io.IOException;

public class helpController extends AnchorPane {

    @FXML AnchorPane QAAnchorpane;
    @FXML AnchorPane helpAnchorpane;

    public helpController() {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("help.fxml"));
        fxmlLoader.setRoot(this);
        fxmlLoader.setController(this);

        try {
            fxmlLoader.load();
        } catch (IOException exception) {
            throw new RuntimeException(exception);
        }

    }

    @FXML
    public void mouseTrap(Event event){
        event.consume();
    }

    @FXML
    public void closeQA() {
        helpAnchorpane.toFront();
    }

    @FXML
    public void openQA() {
        QAAnchorpane.toFront();
    }

}
