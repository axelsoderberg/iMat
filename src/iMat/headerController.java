package iMat;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
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
    @FXML private ImageView searchButton;
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
    public void openHelpView() {
        helpCtrl.toFront();
        this.toFront();
    }

    @FXML
    public void openStoreView() {
        storeCtrl.updateProductList(model.getProducts());
        storeCtrl.toFront();
        storeCtrl.backArrowClicked();
        this.toFront();
    }

    @FXML
    public void headerToFront() {
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
        mypageCtrl.populateOrderFlow();
        mypageCtrl.openUpdate();
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
        storeCtrl.toFront();
        storeCtrl.backArrowClicked();
        storeCtrl.updateProductList(matches);
        this.toFront();

    }

    public void updateCheckoutButton() {
        checkoutButton.setText("Kassan " + String.format("%.2f", model.getShoppingCart().getTotal()) + " kr");

    }

    @FXML
    void hoverCloseDelivery() {
        searchButton.setImage(new Image(getClass().getClassLoader().getResourceAsStream(
                "resources/Group 113.png")));
    }

    @FXML
    void hoverDoneCloseDelivery() {
        searchButton.setImage(new Image(getClass().getClassLoader().getResourceAsStream(
                "resources/search_icon.png")));
    }

}
