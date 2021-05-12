package iMat;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.layout.AnchorPane;

import java.io.IOException;
import java.util.List;

public class ViewHandler {

    private static ViewHandler instance = null;

    @FXML private AnchorPane helpPane;
    @FXML private AnchorPane shoppingListsPane;
    @FXML private AnchorPane headerPane;
    @FXML private AnchorPane storePane;
    @FXML private AnchorPane startPane;

    /**
     * Constructor that should never be called, use getInstance() instead.
     */
    private ViewHandler() {
        // Exists only to defeat instantiation.
    }

    /**
     * Returns the single instance of the Model class.
     */
    public static ViewHandler getInstance() {
        if (instance == null) {
            instance = new ViewHandler();
        }
        return instance;
    }


    @FXML
    public void loadPanes(List<String> fileNames) throws IOException {
        for (String fileName : fileNames) {
            AnchorPane pane = FXMLLoader.load(getClass().getResource(fileName));
            startPane.getChildren().add(pane);
        }
    }

    @FXML
    public void clearPanes() {
        startPane.getChildren().removeAll();
    }
}
