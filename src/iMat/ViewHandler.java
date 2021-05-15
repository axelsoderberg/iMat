package iMat;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.layout.AnchorPane;

import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

public class ViewHandler {


    @FXML private AnchorPane startPane;
    @FXML private AnchorPane helpPane;
    @FXML private AnchorPane shoppingListsPane;
    @FXML private AnchorPane headerPane;
    @FXML private AnchorPane storePane;

    AnchorPane mainPane;
    /**
     * Constructor that should never be called, use getInstance() instead.
     */
    public ViewHandler() {
        // Exists only to defeat instantiation.
    }

    public void setController() throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("Start.fxml"));
        loader.setController(this);
        mainPane = loader.load();
    }


    @FXML
    public void loadPanes(List<String> fileNames) throws IOException {
        setController();
        for (String fileName : fileNames) {
            AnchorPane pane = FXMLLoader.load(getClass().getResource(fileName));
            mainPane.getChildren().add(pane);
        }
    }

    @FXML
    public void clearPanes() {
        mainPane.getChildren().removeAll();
    }

    public AnchorPane getPane() {
        return startPane;
    }
}
