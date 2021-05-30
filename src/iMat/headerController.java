package iMat;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import se.chalmers.cse.dat216.project.Product;

import java.io.IOException;
import java.util.List;

import static iMat.iMatController.setPage;

public class headerController extends AnchorPane {

    private static final Model model = Model.getInstance();
    @FXML private TextField searchField;
    @FXML private Button checkoutButton;
    @FXML private Button storeButton;
    @FXML private Button shoppingListButton;
    @FXML private Button myPageButton;
    @FXML private Button helpButton;
    checkoutController checkoutCtrl;
    storeController storeCtrl;
    mypageController mypageCtrl;
    shoppinglists shoppinglistsCtrl;
    helpController helpCtrl;

    public headerController(checkoutController checkoutCtrl, storeController storeCtrl, mypageController mypageCtrl,
                            shoppinglists shoppinglistsCtrl, helpController helpCtrl) {
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

        updateCheckoutButton();

    }

    @FXML
    private void openHelpView() {
        helpCtrl.toFront();
        this.toFront();
    }

    @FXML
    public void openStoreView() {
        storeCtrl.updateProductList(model.getProducts());
        storeCtrl.toFront();
        this.toFront();
    }

    @FXML
    void openCheckoutView() {
        checkoutCtrl.toFront();
        this.toFront();
        checkoutCtrl.updateShoppingcartList(model.getShoppingCart().getItems());
    }

    @FXML
    private void openMypageView() {
        mypageCtrl.toFront();
        this.toFront();
    }

    @FXML
    private void openShoppingListView() {
        shoppinglistsCtrl.toFront();
        this.toFront();
    }

    @FXML
    private void handleSearchAction() {

        List<Product> matches = model.findProducts(searchField.getText());
        searchField.clear();
        storeCtrl.updateProductList(matches);
        storeCtrl.backArrowClicked();
        storeCtrl.toFront();
        this.toFront();

    }

    public void updateCheckoutButton() {
        checkoutButton.setText("Kassan " + String.format("%.2f", model.getShoppingCart().getTotal()) + " kr");

    }

    @FXML
    private void hoverOnHome() {
        storeButton.setStyle("-fx-background-color: #d1e6ca;");
    }

    @FXML
    private void exitHoverOnHome() {
        storeButton.setStyle("-fx-background-color: #ECEBEB;");
    }

    @FXML
    private void hoverOnShoppingLists() {
        shoppingListButton.setStyle("-fx-background-color: #d1e6ca;");
    }

    @FXML
    private void exitHoverOnShoppingLists() {
        shoppingListButton.setStyle("-fx-background-color: #ECEBEB;");
    }

    @FXML
    private void hoverOnCheckout() {
        checkoutButton.setStyle("-fx-background-color: #d1e6ca;");
    }

    @FXML
    private void exitHoverOnCheckout() {
        checkoutButton.setStyle("-fx-background-color: #ECEBEB;");
    }


    @FXML
    private void hoverOnMyPage() {
        myPageButton.setStyle("-fx-background-color: #71ad4c;");
    }

    @FXML
    private void exitHoverOnMyPage() {
        myPageButton.setStyle("-fx-background-color: #629543;");
    }

    @FXML
    private void hoverOnHelp() {
        helpButton.setStyle("-fx-background-color: #71ad4c;");
    }

    @FXML
    private void exitHoverOnHelp() {
        helpButton.setStyle("-fx-background-color: #629543;");
    }
}
