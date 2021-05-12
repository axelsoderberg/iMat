package iMat;

import java.io.IOException;
import java.net.URL;
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
public class iMatController implements Initializable/*, ShoppingCartListener*/ {

    // Other variables
    private final Model model = Model.getInstance();

    @Override
    public void initialize(URL url, ResourceBundle rb) {

    }

}

