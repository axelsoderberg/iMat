package iMat;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.RadioButton;
import javafx.scene.control.Toggle;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.AnchorPane;

import java.net.URL;
import java.util.ResourceBundle;

import static iMat.iMatController.setPage;


public class checkoutController implements Initializable {

    private ToggleGroup paymentTypeToggleGroup;
    private ToggleGroup deliveryTypeToggleGroup;

    @FXML private AnchorPane checkoutShoppingcart;
    @FXML private AnchorPane checkoutPaymentdetails;
    @FXML private AnchorPane checkoutThanks;
    @FXML private RadioButton savedCardRadioButton;
    @FXML private RadioButton savedInvoiceDetailsRadioButton;
    @FXML private RadioButton otherPaymentRadioButton;
    @FXML private RadioButton savedDeliveryAdressRadioButton;
    @FXML private RadioButton otherDeliveryRadioButton;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        initRadioButtons();
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


    @FXML private void openPaymentDetails() {
        checkoutPaymentdetails.toFront();
    }

    @FXML private void openCheckoutThanks() {
        checkoutThanks.toFront();
    }

    @FXML private void openShoppingcart() {
        checkoutShoppingcart.toFront();
    }

    @FXML private void doneWithOrder() {
        setPage("store");
    }

    @FXML
    public void mouseTrap(Event event){
        event.consume();
    }

}
