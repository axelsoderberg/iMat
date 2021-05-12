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
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.FlowPane;
import se.chalmers.cse.dat216.project.CartEvent;
import se.chalmers.cse.dat216.project.CreditCard;
import se.chalmers.cse.dat216.project.Product;
import se.chalmers.cse.dat216.project.ShoppingCart;
import se.chalmers.cse.dat216.project.ShoppingCartListener;


/**
 * Kommer vara skitmycket fel innan vi lagt in alla FXML-saker vi behöver, men det mesta kommer vi nog kunna använda på
 * ett eller annat sätt.
 *
 */
public class StartController implements Initializable/*, ShoppingCartListener*/ {

    //FXML items
    @FXML private AnchorPane startPane;
    @FXML private Button startHelpButton;
    @FXML private Button startStoreButton;

    // Other variables
    private final ViewHandler viewHandler = ViewHandler.getInstance();


    @Override
    public void initialize(URL url, ResourceBundle rb) {
        openStartView();
    }

    public void openStartView() { startPane.toFront(); }


    private void openHelpView(ActionEvent event) throws IOException {
        List<String> panes = new ArrayList();
        panes.add("help.fxml");
        panes.add("header.fxml");
        viewHandler.loadPanes(panes);
    }

    private void openStoreView(ActionEvent event) throws IOException {
        List<String> panes = new ArrayList();
        panes.add("store.fxml");
        panes.add("header.fxml");
        viewHandler.loadPanes(panes);
    }


}

