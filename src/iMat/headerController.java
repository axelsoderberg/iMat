package iMat;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import se.chalmers.cse.dat216.project.Product;

import java.io.IOException;
import java.util.List;

import static iMat.iMatController.setPage;

public class headerController extends AnchorPane {

    private static final Model model = Model.getInstance();
    @FXML private TextField searchField;
    checkoutController checkoutCtrl;
    storeController storeCtrl;
    mypageController mypageCtrl;
    shoppinglistsController shoppinglistsCtrl;
    helpController helpCtrl;

    public headerController(checkoutController checkoutCtrl, storeController storeCtrl, mypageController mypageCtrl,
                            shoppinglistsController shoppinglistsCtrl, helpController helpCtrl) {
        this.helpCtrl = helpCtrl;
        this.mypageCtrl = mypageCtrl;
        this.shoppinglistsCtrl = shoppinglistsCtrl;
        this.checkoutCtrl = checkoutCtrl;
        this.storeCtrl = storeCtrl;

        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("header.fxml"));
        fxmlLoader.setRoot(this);
        fxmlLoader.setController(this);

        try {
            fxmlLoader.load();
        } catch (IOException exception) {
            throw new RuntimeException(exception);
        }
    }

    @FXML
    private void openHelpView() {
        helpCtrl.toFront();
    }

    @FXML
    private void openStoreView() {
        storeCtrl.toFront();
    }

    @FXML
    private void openCheckoutView() {
        checkoutCtrl.toFront();
        checkoutCtrl.updateShoppingcartList(model.getShoppingCart().getItems());
        //setPage("checkouts");
    }

    @FXML
    private void openMypageView() {
        mypageCtrl.toFront();
    }

    @FXML
    private void openShoppingListView() {
        shoppinglistsCtrl.toFront();
    }

    @FXML
    private void handleSearchAction(ActionEvent event) {

        List<Product> matches = model.findProducts(searchField.getText());
        //storeController.updateProductlist(matches);
        storeCtrl.toFront();

    }

}
