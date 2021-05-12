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
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;


public class StartController implements Initializable/*, ShoppingCartListener*/ {

    //FXML items
    @FXML private AnchorPane startPane;
    @FXML private Button startHelpButton;
    @FXML private Button startStoreButton;

    // Other variables
    private final ViewHandler viewHandler = ViewHandler.getInstance();


    @Override
    public void initialize(URL url, ResourceBundle rb) {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("Start.fxml"));
        loader.setController(this);
        try {
            AnchorPane mainPane = loader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }
        openStartView();
    }

    public void openStartView() { startPane.toFront(); }

    @FXML
    private void openHelpView(ActionEvent event) throws IOException {
        List<String> panes = new ArrayList();
        panes.add("help.fxml");
        panes.add("header.fxml");
        viewHandler.loadPanes(panes);
    }

    @FXML
    private void openStoreView(ActionEvent event) throws IOException {
        List<String> panes = new ArrayList();
        panes.add("store.fxml");
        panes.add("header.fxml");
        //viewHandler.loadPanes(panes);
    }


}

