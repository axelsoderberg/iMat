package iMat;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
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

    // @FXML items here:
        // TODO lägga till alla FXML saker vi behöver här
    @FXML private AnchorPane helpPane;
    @FXML private AnchorPane shoppingListsPane;
    @FXML private AnchorPane headerPane;
    @FXML private AnchorPane storePane;

    @FXML private AnchorPane startPane;
    @FXML private Button startHelpButton;
    @FXML private Button startStoreButton;

    // Other variables
    private final Model model = Model.getInstance();


    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO initialisera saker, kolla lab 3 typ
        //model.getShoppingCart().addShoppingCartListener(this);

        //updateProductList(model.getProducts());
        //updateBottomPanel();
        //headerToFront();
        openStartView();
        //setupAccountPane();


    }
    // Init

    public void headerToFront() { headerPane.toFront(); }

    public void openStartView() { startPane.toFront(); }


    // Navigation

    public void helpButtonOnClick() { openHelpView(); }

    public void storeButtonOnClick() { openStoreView(); }

    /*


    public void accountButtonOnClick() { openAccountView(); }

    public void shoppingListsButtonOnClick() { openAccountView(); } */




    private void openShoppingList() {
        shoppingListsPane.toFront();
    }

    @FXML
    private void openHelpView() { helpPane.toFront(); }
    @FXML
    private void openStoreView() { storePane.toFront(); }
/*
    private void openAccountView() {
        // updateAccountPanel();
        accountPane.toFront();
    }

    private void openHomeView() {
        homePane.toFront();
    }

    // Shop pane actions
    @FXML
    private void handleShowAccountAction(ActionEvent event) {
        openAccountView();
    }

    @FXML
    private void handleSearchAction(ActionEvent event) {

        List<Product> matches = model.findProducts(searchField.getText());
        updateProductList(matches);
        System.out.println("# matching products: " + matches.size());

    }

    @FXML
    private void handleClearCartAction(ActionEvent event) {
        model.clearShoppingCart();
    }

    @FXML
    private void handleBuyItemsAction(ActionEvent event) {
        model.placeOrder();
        costLabel.setText("Köpet klart!");
    }

    // Account pane actions
    @FXML
    private void handleDoneAction(ActionEvent event) {
        closeAccountView();
    }


    // Shop pane methods
    @Override
    public void shoppingCartChanged(CartEvent evt) {
        updateBottomPanel();
    }


    private void updateProductList(List<Product> products) {

        productsFlowPane.getChildren().clear();

        for (Product product : products) {

            productsFlowPane.getChildren().add(new ProductPanel(product));
        }

    }

    private void updateBottomPanel() {

        ShoppingCart shoppingCart = model.getShoppingCart();

        itemsLabel.setText("Antal varor: " + shoppingCart.getItems().size());
        costLabel.setText("Kostnad: " + String.format("%.2f",shoppingCart.getTotal()));

    }

    private void updateAccountPanel() {

        CreditCard card = model.getCreditCard();

        numberTextField.setText(card.getCardNumber());
        nameTextField.setText(card.getHoldersName());

        cardTypeCombo.getSelectionModel().select(card.getCardType());
        monthCombo.getSelectionModel().select(""+card.getValidMonth());
        yearCombo.getSelectionModel().select(""+card.getValidYear());

        cvcField.setText(""+card.getVerificationCode());

        purchasesLabel.setText(model.getNumberOfOrders()+ " tidigare inköp hos iMat");

    }

    private void updateCreditCard() {

        CreditCard card = model.getCreditCard();

        card.setCardNumber(numberTextField.getText());
        card.setHoldersName(nameTextField.getText());

        String selectedValue = (String) cardTypeCombo.getSelectionModel().getSelectedItem();
        card.setCardType(selectedValue);

        selectedValue = (String) monthCombo.getSelectionModel().getSelectedItem();
        card.setValidMonth(Integer.parseInt(selectedValue));

        selectedValue = (String) yearCombo.getSelectionModel().getSelectedItem();
        card.setValidYear(Integer.parseInt(selectedValue));

        card.setVerificationCode(Integer.parseInt(cvcField.getText()));

    }

    private void setupAccountPane() {

        cardTypeCombo.getItems().addAll(model.getCardTypes());

        monthCombo.getItems().addAll(model.getMonths());

        yearCombo.getItems().addAll(model.getYears());

    }
    */

    // Help


}

