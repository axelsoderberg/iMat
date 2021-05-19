package iMat;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import se.chalmers.cse.dat216.project.Product;

import java.util.List;

import static iMat.iMatController.setPage;


public class headerController {

    private static final Model model = Model.getInstance();
    @FXML private TextField searchField;

    @FXML
    private void openHelpView() {
        setPage("help");
    }

    @FXML
    private void openStoreView() {
        //storeController.updateStore(model.getProducts());
        setPage("store");
    }

    @FXML
    private void openCheckoutView() {
        setPage("checkouts");
    }

    @FXML
    private void openMypageView() {
        setPage("mypage");
    }

    @FXML
    private void openShoppingListView() {
        setPage("shoppinglists");
    }

    @FXML
    private void handleSearchAction(ActionEvent event) {

        List<Product> matches = model.findProducts(searchField.getText());
        //storeController.updateStore(matches);
        setPage("store");

    }

}
