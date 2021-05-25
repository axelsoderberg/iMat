package iMat;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.FlowPane;
import se.chalmers.cse.dat216.project.Product;
import se.chalmers.cse.dat216.project.ShoppingItem;

import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

import static iMat.iMatController.setPage;


public class checkoutController extends AnchorPane {

    private ToggleGroup paymentTypeToggleGroup;
    private ToggleGroup deliveryTypeToggleGroup;

    private final Model model = Model.getInstance();

    @FXML private AnchorPane checkoutShoppingcart;
    @FXML private AnchorPane checkoutPaymentdetails;
    @FXML private AnchorPane checkoutThanks;
    @FXML private RadioButton savedCardRadioButton;
    @FXML private RadioButton savedInvoiceDetailsRadioButton;
    @FXML private RadioButton otherPaymentRadioButton;
    @FXML private RadioButton savedDeliveryAdressRadioButton;
    @FXML private RadioButton otherDeliveryRadioButton;
    @FXML private FlowPane productsFlowPaneCheckout;
    @FXML private Label checkoutTotalLabel;

    public checkoutController () {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("checkouts.fxml"));
        fxmlLoader.setRoot(this);
        fxmlLoader.setController(this);

        try {
            fxmlLoader.load();
        } catch (IOException exception) {
            throw new RuntimeException(exception);
        }

        initRadioButtons();
        updateShoppingcartList(model.getShoppingCart().getItems());

    }

    private void initRadioButtons() {
        //paymentType
        paymentTypeToggleGroup = new ToggleGroup();
        savedCardRadioButton.setToggleGroup(paymentTypeToggleGroup);
        savedInvoiceDetailsRadioButton.setToggleGroup(paymentTypeToggleGroup);
        otherPaymentRadioButton.setToggleGroup(paymentTypeToggleGroup);
        savedCardRadioButton.setSelected(true);   //default
        paymentTypeToggleGroup.selectedToggleProperty().addListener(new ChangeListener<Toggle>() {
            @Override
            public void changed(ObservableValue<? extends Toggle> observable, Toggle oldValue, Toggle newValue) {
                if (paymentTypeToggleGroup.getSelectedToggle() != null) {
                    RadioButton selected = (RadioButton) paymentTypeToggleGroup.getSelectedToggle();
                }
            }
        });

        //deliveryType
        deliveryTypeToggleGroup = new ToggleGroup();
        savedDeliveryAdressRadioButton.setToggleGroup(deliveryTypeToggleGroup);
        otherDeliveryRadioButton.setToggleGroup(deliveryTypeToggleGroup);
        savedDeliveryAdressRadioButton.setSelected(true);   //default
        deliveryTypeToggleGroup.selectedToggleProperty().addListener(new ChangeListener<Toggle>() {
            @Override
            public void changed(ObservableValue<? extends Toggle> observable, Toggle oldValue, Toggle newValue) {
                if (deliveryTypeToggleGroup.getSelectedToggle() != null) {
                    RadioButton selected = (RadioButton) deliveryTypeToggleGroup.getSelectedToggle();
                }
            }
        });
    }

    public void updateShoppingcartList(List<ShoppingItem> items) {

        productsFlowPaneCheckout.getChildren().clear();

        for (ShoppingItem item : items) {
            productsFlowPaneCheckout.getChildren().add(new Checkoutitem(item));
        }

    }

    @FXML private void openPaymentDetails() {
        checkoutPaymentdetails.toFront();
    }

    @FXML private void openCheckoutThanks() {
        model.placeOrder();
        checkoutThanks.toFront();
    }

    @FXML private void openShoppingcart() {
        checkoutShoppingcart.toFront();
    }

    @FXML private void doneWithOrder() {
        this.toBack();
    }

    @FXML
    public void mouseTrap(Event event){
        event.consume();
    }

    void updateCheckoutTotal() {
        checkoutTotalLabel.setText("Summa: " + String.format("%.2f",model.getShoppingCart().getTotal()) + " kr");
    }
}
