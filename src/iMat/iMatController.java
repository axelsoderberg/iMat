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
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.Pane;
import se.chalmers.cse.dat216.project.CartEvent;
import se.chalmers.cse.dat216.project.CreditCard;
import se.chalmers.cse.dat216.project.Product;
import se.chalmers.cse.dat216.project.ShoppingCart;
import se.chalmers.cse.dat216.project.ShoppingCartListener;


/**
 *
 */
public class iMatController implements Initializable/*, ShoppingCartListener*/ {

    // FXML items

    @FXML private AnchorPane iMatPane;

    // Other variables
    private final Model model = Model.getInstance();
    private List<AnchorPane> panes = new ArrayList<>(); // Här har vi alla fxml-sidor

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        try {
            panes.add(FXMLLoader.load(getClass().getResource("Start.fxml")));
            panes.add(FXMLLoader.load(getClass().getResource("store.fxml")));
            panes.add(FXMLLoader.load(getClass().getResource("help.fxml")));
            panes.add(FXMLLoader.load(getClass().getResource("mypage.fxml")));
            panes.add(FXMLLoader.load(getClass().getResource("header.fxml")));
        } catch (IOException e) {
            e.printStackTrace();
        }

        // Alla AnchorPanes från andra fxml-filer hamnar "under" iMatPane
        iMatPane.getChildren().clear(); // Så först, se till att den är tom
        iMatPane.getChildren().addAll(panes); // Lägg till alla

        panes.get(getIndexOfPage("Start")).toFront(); // Sätt Start att vara längst fram

    }

    // Inte jättesnygg lösning, men använd denna för att få fram den önskade sidan
    private int getIndexOfPage(String fileName) {
        switch (fileName) {
            case "Start":
                return 0;
            case "store":
                return 1;
            case "help":
                return 2;
            case "mypage":
                return 3;
            case "header":
                return 4;
        }
        return -1;
    }

}

