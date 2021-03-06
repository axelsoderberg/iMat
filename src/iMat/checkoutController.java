package iMat;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.FlowPane;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;
import se.chalmers.cse.dat216.project.Product;
import se.chalmers.cse.dat216.project.ShoppingItem;

import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.Objects;
import java.util.ResourceBundle;

import static iMat.iMatController.setPage;


public class checkoutController extends AnchorPane {

    private ToggleGroup paymentTypeToggleGroup;
    private ToggleGroup deliveryTypeToggleGroup;

    private final Model model = Model.getInstance();

    @FXML private AnchorPane checkoutShoppingcart;
    @FXML private AnchorPane checkoutDeliverydetails;
    @FXML private AnchorPane checkoutPaymentdetails;
    @FXML private AnchorPane checkoutThanks;
    @FXML private FlowPane productsFlowPaneCheckout;
    @FXML private Label checkoutTotalLabel;

    @FXML TextField firstNameTextField;
    @FXML TextField surNameTextField;
    @FXML TextField postAdressTextField;
    @FXML TextField postCodeTextField;
    @FXML TextField cityTextField;
    @FXML TextField phoneTextField;
    @FXML TextField mailTextField;
    @FXML ComboBox cardTypeComboBox;
    @FXML TextField validMonthTextField;
    @FXML TextField validYearTextField;
    @FXML TextField holdersNameTextField;
    @FXML TextField verificationCodeTextField;
    @FXML TextField cardNrTextField;

    @FXML ImageView firstNameApprovedImageView;
    @FXML ImageView surNameApprovedImageView;
    @FXML ImageView postadressApprovedImageView;
    @FXML ImageView postcodeApprovedImageView;
    @FXML ImageView cityApprovedImageView;
    @FXML ImageView phoneApprovedImageView;
    @FXML ImageView mailApprovedImageView;
    @FXML ImageView cardtypeApprovedImageView;
    @FXML ImageView validDateApprovedImageView;
    @FXML ImageView holdersNameApprovedImageView;
    @FXML ImageView verificationCodeApprovedImageView;
    @FXML ImageView cardNrApprovedImageView;

    @FXML Button deliveryOkButton;
    @FXML Button notDoneDeliveryButton;
    @FXML Button paymentOkButton;
    @FXML Button notDonePayButton;

    @FXML Rectangle helpDelivery;
    @FXML Rectangle helpPayment;

    @FXML ImageView thanksClose;
    @FXML ImageView payClose;
    @FXML ImageView deliveryClose;

    @FXML Button saveListButton;
    @FXML Text text1;
    @FXML Text text2;

    Boolean firstNameOk = false;
    Boolean surNameOk = false;
    Boolean postadressOk = false;
    Boolean postcodeOk = false;
    Boolean cityOk = false;
    Boolean phoneOk = false;
    Boolean mailOk = false;

    Boolean cardtypeOk = false;
    Boolean validDateOk = false;
    Boolean holdersNameOk = false;
    Boolean verificationCodeOk = false;
    Boolean cardnrOk = false;

    public checkoutController () {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("checkouts.fxml"));
        fxmlLoader.setRoot(this);
        fxmlLoader.setController(this);

        try {
            fxmlLoader.load();
        } catch (IOException exception) {
            throw new RuntimeException(exception);
        }

        updateShoppingcartList(model.getShoppingCart().getItems());
        updateCheckoutTotal();

        setPaymentInfo();
        setDeliveryInfo();

        addListeners();

        initComboBoxCardtype();

        checkApprovals();
        cardtypeApprovedImageView.setImage(getApprovedImage());
        cardtypeOk = true; //always ok...

        chooseDeliveryButton();
        choosePaymentButton();

    }

    void setPaymentInfo() {
        holdersNameTextField.setText(model.getCreditCard().getHoldersName());
        verificationCodeTextField.setText(Integer.toString(model.getCreditCard().getVerificationCode()));
        cardNrTextField.setText(model.getCreditCard().getCardNumber());
        validMonthTextField.setText(Integer.toString(model.getCreditCard().getValidMonth()));
        validYearTextField.setText(Integer.toString(model.getCreditCard().getValidYear()));
    }

    void setDeliveryInfo() {
        firstNameTextField.setText(model.getCustomer().getFirstName());
        surNameTextField.setText(model.getCustomer().getLastName());
        postAdressTextField.setText(model.getCustomer().getPostAddress());
        postCodeTextField.setText(model.getCustomer().getPostCode());
        cityTextField.setText(model.getCustomer().getAddress());
        phoneTextField.setText(model.getCustomer().getPhoneNumber());
        mailTextField.setText(model.getCustomer().getEmail());
    }

    public void updateShoppingcartList(List<ShoppingItem> items) {

        productsFlowPaneCheckout.getChildren().clear();

        for (ShoppingItem item : items) {
            productsFlowPaneCheckout.getChildren().add(new Checkoutitem(item));
        }

    }

    @FXML private void openPaymentDetails() {
        setPaymentInfo();
        checkApprovals();
        choosePaymentButton();
        checkoutPaymentdetails.toFront();
    }

    @FXML private void openDeliveryDetails() {
        setDeliveryInfo();
        chooseDeliveryButton();
        checkApprovals();
        checkoutDeliverydetails.toFront();
    }

    @FXML private void openCheckoutThanks() {
        model.placeOrder();
        checkoutThanks.toFront();
    }

    @FXML private void openShoppingcart() {
        checkoutShoppingcart.toFront();
    }

    @FXML private void doneWithOrder() {
        checkoutShoppingcart.toFront();
        this.toBack();
    }

    @FXML
    public void mouseTrap(Event event){
        event.consume();
    }

    void updateCheckoutTotal() {
        checkoutTotalLabel.setText("Summa: " + String.format("%.2f",model.getShoppingCart().getTotal()) + " kr");
    }

    @FXML private void backAction() {
        this.toBack();
    }

    void checkApprovals() {
        checkCardNumber();
        checkPhoneNumber();
        checkPostCode();
        checkVerificationCode();
        checkLastName();
        checkEmail();
        checkFirstName();
        checkAdress();
        checkCity();
        checkHoldersName();
        checkValidDate();

        //cardtype always valid...
    }

    void initComboBoxCardtype() {
        //det som ska visas i boxen
        cardTypeComboBox.getItems().addAll(model.getCardTypes());
        //best??mmer vad som ??r valt fr??n b??rjan
        cardTypeComboBox.getSelectionModel().select(model.getCreditCard().getCardType());

        cardTypeComboBox.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
                model.getCreditCard().setCardType(newValue);
                cardtypeApprovedImageView.setImage(getApprovedImage());
            }
        });
    }

    void addListeners() {

        // Denna metod k??rs n??r textf??ltet ??r fokuserat och man trycker Enter
        firstNameTextField.setOnKeyPressed(e -> {
            if (e.getCode() == KeyCode.ENTER) {
                // Spara metoden h??r
                model.getCustomer().setFirstName(firstNameTextField.getText());

            }
        });

        firstNameTextField.focusedProperty().addListener(new ChangeListener<Boolean>()
        {
            @Override
            public void changed(ObservableValue<? extends Boolean> arg0, Boolean oldPropertyValue, Boolean newPropertyValue)
            {
                if (!newPropertyValue)
                {
                    // Denna k??rs n??r f??ltet inte l??ngre ??r fokuserat, s?? vill ha spara metod h??r ocks??
                    model.getCustomer().setFirstName(firstNameTextField.getText());
                }
            }
        });

        // Denna metod k??rs n??r textf??ltet ??r fokuserat och man trycker Enter
        surNameTextField.setOnKeyPressed(e -> {
            if (e.getCode() == KeyCode.ENTER) {
                // Spara metoden h??r
                model.getCustomer().setLastName(surNameTextField.getText());

            }
        });

        surNameTextField.focusedProperty().addListener(new ChangeListener<Boolean>()
        {
            @Override
            public void changed(ObservableValue<? extends Boolean> arg0, Boolean oldPropertyValue, Boolean newPropertyValue)
            {
                if (!newPropertyValue)
                {
                    // Denna k??rs n??r f??ltet inte l??ngre ??r fokuserat, s?? vill ha spara metod h??r ocks??
                    model.getCustomer().setLastName(surNameTextField.getText());
                }
            }
        });

        // Denna metod k??rs n??r textf??ltet ??r fokuserat och man trycker Enter
        postAdressTextField.setOnKeyPressed(e -> {
            if (e.getCode() == KeyCode.ENTER) {
                // Spara metoden h??r
                model.getCustomer().setPostAddress(postAdressTextField.getText());

            }
        });

        postAdressTextField.focusedProperty().addListener(new ChangeListener<Boolean>()
        {
            @Override
            public void changed(ObservableValue<? extends Boolean> arg0, Boolean oldPropertyValue, Boolean newPropertyValue)
            {
                if (!newPropertyValue)
                {
                    // Denna k??rs n??r f??ltet inte l??ngre ??r fokuserat, s?? vill ha spara metod h??r ocks??
                    model.getCustomer().setPostAddress(postAdressTextField.getText());
                }
            }
        });

        // Denna metod k??rs n??r textf??ltet ??r fokuserat och man trycker Enter
        postCodeTextField.setOnKeyPressed(e -> {
            if (e.getCode() == KeyCode.ENTER) {
                // Spara metoden h??r
                model.getCustomer().setPostCode(postCodeTextField.getText());

            }
        });

        postCodeTextField.focusedProperty().addListener(new ChangeListener<Boolean>()
        {
            @Override
            public void changed(ObservableValue<? extends Boolean> arg0, Boolean oldPropertyValue, Boolean newPropertyValue)
            {
                if (!newPropertyValue)
                {
                    // Denna k??rs n??r f??ltet inte l??ngre ??r fokuserat, s?? vill ha spara metod h??r ocks??
                    model.getCustomer().setPostCode(postCodeTextField.getText());
                }
            }
        });

        // Denna metod k??rs n??r textf??ltet ??r fokuserat och man trycker Enter
        cityTextField.setOnKeyPressed(e -> {
            if (e.getCode() == KeyCode.ENTER) {
                // Spara metoden h??r
                model.getCustomer().setAddress(cityTextField.getText());

            }
        });

        cityTextField.focusedProperty().addListener(new ChangeListener<Boolean>()
        {
            @Override
            public void changed(ObservableValue<? extends Boolean> arg0, Boolean oldPropertyValue, Boolean newPropertyValue)
            {
                if (!newPropertyValue)
                {
                    // Denna k??rs n??r f??ltet inte l??ngre ??r fokuserat, s?? vill ha spara metod h??r ocks??
                    model.getCustomer().setAddress(cityTextField.getText());
                }
            }
        });

        // Denna metod k??rs n??r textf??ltet ??r fokuserat och man trycker Enter
        phoneTextField.setOnKeyPressed(e -> {
            if (e.getCode() == KeyCode.ENTER) {
                // Spara metoden h??r
                model.getCustomer().setPhoneNumber(phoneTextField.getText());

            }
        });

        phoneTextField.focusedProperty().addListener(new ChangeListener<Boolean>()
        {
            @Override
            public void changed(ObservableValue<? extends Boolean> arg0, Boolean oldPropertyValue, Boolean newPropertyValue)
            {
                if (!newPropertyValue)
                {
                    // Denna k??rs n??r f??ltet inte l??ngre ??r fokuserat, s?? vill ha spara metod h??r ocks??
                    model.getCustomer().setPhoneNumber(phoneTextField.getText());
                }
            }
        });

        // Denna metod k??rs n??r textf??ltet ??r fokuserat och man trycker Enter
        mailTextField.setOnKeyPressed(e -> {
            if (e.getCode() == KeyCode.ENTER) {
                // Spara metoden h??r
                model.getCustomer().setEmail(mailTextField.getText());

            }
        });

        mailTextField.focusedProperty().addListener(new ChangeListener<Boolean>()
        {
            @Override
            public void changed(ObservableValue<? extends Boolean> arg0, Boolean oldPropertyValue, Boolean newPropertyValue)
            {
                if (!newPropertyValue)
                {
                    // Denna k??rs n??r f??ltet inte l??ngre ??r fokuserat, s?? vill ha spara metod h??r ocks??
                    model.getCustomer().setEmail(mailTextField.getText());
                }
            }
        });

        // Denna metod k??rs n??r textf??ltet ??r fokuserat och man trycker Enter
        holdersNameTextField.setOnKeyPressed(e -> {
            if (e.getCode() == KeyCode.ENTER) {
                // Spara metoden h??r
                model.getCreditCard().setHoldersName(holdersNameTextField.getText());

            }
        });

        holdersNameTextField.focusedProperty().addListener(new ChangeListener<Boolean>()
        {
            @Override
            public void changed(ObservableValue<? extends Boolean> arg0, Boolean oldPropertyValue, Boolean newPropertyValue)
            {
                if (!newPropertyValue)
                {
                    // Denna k??rs n??r f??ltet inte l??ngre ??r fokuserat, s?? vill ha spara metod h??r ocks??
                    model.getCreditCard().setHoldersName(holdersNameTextField.getText());
                }
            }
        });

        // Denna metod k??rs n??r textf??ltet ??r fokuserat och man trycker Enter
        cardNrTextField.setOnKeyPressed(e -> {
            if (e.getCode() == KeyCode.ENTER) {
                // Spara metoden h??r
                model.getCreditCard().setCardNumber(cardNrTextField.getText());
            }
        });

        cardNrTextField.focusedProperty().addListener(new ChangeListener<Boolean>()
        {
            @Override
            public void changed(ObservableValue<? extends Boolean> arg0, Boolean oldPropertyValue, Boolean newPropertyValue)
            {
                if (!newPropertyValue)
                {
                    // Denna k??rs n??r f??ltet inte l??ngre ??r fokuserat, s?? vill ha spara metod h??r ocks??
                    model.getCreditCard().setCardNumber(cardNrTextField.getText());
                }
            }
        });

        // Denna metod k??rs n??r textf??ltet ??r fokuserat och man trycker Enter
        validMonthTextField.setOnKeyPressed(e -> {
            if (e.getCode() == KeyCode.ENTER) {
                try {
                    // Spara metoden h??r
                    model.getCreditCard().setValidMonth(Integer.parseInt(validMonthTextField.getText()));
                } catch(NumberFormatException ignored) {

                }
            }
        });

        validMonthTextField.focusedProperty().addListener(new ChangeListener<Boolean>()
        {
            @Override
            public void changed(ObservableValue<? extends Boolean> arg0, Boolean oldPropertyValue, Boolean newPropertyValue)
            {
                if (!newPropertyValue)
                {
                    try {
                        // Denna k??rs n??r f??ltet inte l??ngre ??r fokuserat, s?? vill ha spara metod h??r ocks??
                        model.getCreditCard().setValidMonth(Integer.parseInt(validMonthTextField.getText()));
                    } catch(NumberFormatException ignored) {

                    }

                }
            }
        });

        // Denna metod k??rs n??r textf??ltet ??r fokuserat och man trycker Enter
        validYearTextField.setOnKeyPressed(e -> {
            if (e.getCode() == KeyCode.ENTER) {
                try {
                    // Spara metoden h??r
                    model.getCreditCard().setValidYear(Integer.parseInt(validYearTextField.getText()));
                } catch(NumberFormatException ignored) {

                }
            }
        });

        validYearTextField.focusedProperty().addListener(new ChangeListener<Boolean>()
        {
            @Override
            public void changed(ObservableValue<? extends Boolean> arg0, Boolean oldPropertyValue, Boolean newPropertyValue)
            {
                if (!newPropertyValue)
                {
                    try {
                        // Denna k??rs n??r f??ltet inte l??ngre ??r fokuserat, s?? vill ha spara metod h??r ocks??
                        model.getCreditCard().setValidYear(Integer.parseInt(validYearTextField.getText()));
                    } catch(NumberFormatException ignored) {

                    }

                }
            }
        });

        // Denna metod k??rs n??r textf??ltet ??r fokuserat och man trycker Enter
        verificationCodeTextField.setOnKeyPressed(e -> {
            if (e.getCode() == KeyCode.ENTER) {
                try {
                    // Spara metoden h??r
                    model.getCreditCard().setVerificationCode(Integer.parseInt(verificationCodeTextField.getText()));
                } catch(NumberFormatException ignored) {

                }

            }
        });

        verificationCodeTextField.focusedProperty().addListener(new ChangeListener<Boolean>()
        {
            @Override
            public void changed(ObservableValue<? extends Boolean> arg0, Boolean oldPropertyValue, Boolean newPropertyValue)
            {
                if (!newPropertyValue)
                {
                    try {
                        // Denna k??rs n??r f??ltet inte l??ngre ??r fokuserat, s?? vill ha spara metod h??r ocks??
                        model.getCreditCard().setVerificationCode(Integer.parseInt(verificationCodeTextField.getText()));
                    } catch(NumberFormatException ignored) {

                    }

                }
            }
        });


    }

    @FXML
    private void checkPostCode() {
        if (postCodeTextField.getLength() > 5) {
            postCodeTextField.deletePreviousChar();
        } else if (!postCodeTextField.getText().matches("\\d+")) {
            //given text does not include digits
            postCodeTextField.deletePreviousChar();
        }

        if (postCodeTextField.getLength() == 5) {
            postcodeApprovedImageView.setImage(getApprovedImage());
            postcodeOk = true;
            chooseDeliveryButton();
        } else {
            postcodeApprovedImageView.setImage(getNotApprovedImage());
            postcodeOk = false;
            chooseDeliveryButton();
        }
    }

    @FXML
    private void checkPhoneNumber() {
        if (phoneTextField.getLength() > 10) {
            phoneTextField.deletePreviousChar();
        } else if (!phoneTextField.getText().matches("\\d+")) {
            //given text does not include digits
            phoneTextField.deletePreviousChar();
        }

        if (phoneTextField.getLength() == 10) {
            phoneApprovedImageView.setImage(getApprovedImage());
            phoneOk = true;
            chooseDeliveryButton();
        } else {
            phoneApprovedImageView.setImage(getNotApprovedImage());
            phoneOk = false;
            chooseDeliveryButton();
        }
    }

    @FXML
    private void checkVerificationCode() {
        if (verificationCodeTextField.getLength() > 3) {
            verificationCodeTextField.deletePreviousChar();
        } else if (!verificationCodeTextField.getText().matches("\\d+")) {
            //given text does not include digits
            verificationCodeTextField.deletePreviousChar();
        }

        if (verificationCodeTextField.getLength() == 3) {
            verificationCodeApprovedImageView.setImage(getApprovedImage());
            verificationCodeOk = true;
            choosePaymentButton();
        } else {
            verificationCodeApprovedImageView.setImage(getNotApprovedImage());
            verificationCodeOk = false;
            choosePaymentButton();
        }
    }

    @FXML
    private void checkCardNumber() {
        if (cardNrTextField.getLength() > 16) {
            cardNrTextField.deletePreviousChar();
        } else if (!cardNrTextField.getText().matches("\\d+")) {
            //given text does not include digits
            cardNrTextField.deletePreviousChar();
        } else if ((cardNrTextField.getLength() % 4) == 0) {
            //cardNrTextField.setText(cardNrTextField.getText() + " ");
        }

        if (cardNrTextField.getLength() == 16) {
            cardNrApprovedImageView.setImage(getApprovedImage());
            cardnrOk = true;
            choosePaymentButton();
        } else {
            cardNrApprovedImageView.setImage(getNotApprovedImage());
            cardnrOk = false;
            choosePaymentButton();
        }
    }

    @FXML
    private void checkFirstName() {
        if (!isAlpha(firstNameTextField.getText())) {
            firstNameTextField.deletePreviousChar();
        }

        if (firstNameTextField.getText() == "") {
            firstNameApprovedImageView.setImage(getNotApprovedImage());
            firstNameOk = false;
            chooseDeliveryButton();
        } else {
            firstNameApprovedImageView.setImage(getApprovedImage());
            firstNameOk = true;
            chooseDeliveryButton();
        }
    }

    @FXML
    private void checkLastName() {
        if (!isAlpha(surNameTextField.getText())) {
            surNameTextField.deletePreviousChar();
        }

        if (surNameTextField.getText().equals("")) {
            surNameApprovedImageView.setImage(getNotApprovedImage());
            surNameOk = false;
            chooseDeliveryButton();
        } else {
            surNameApprovedImageView.setImage(getApprovedImage());
            surNameOk = true;
            chooseDeliveryButton();
        }
    }

    @FXML
    private void checkAdress() {
        if (postAdressTextField.getText().equals("")) {
            postadressApprovedImageView.setImage(getNotApprovedImage());
            postadressOk = false;
            chooseDeliveryButton();
        } else {
            postadressApprovedImageView.setImage(getApprovedImage());
            postadressOk = true;
            chooseDeliveryButton();
        }
    }

    @FXML
    private void checkCity() {
        if (!isAlpha(cityTextField.getText())) {
            cityTextField.deletePreviousChar();
        }

        if (cityTextField.getText().equals("")) {
            cityApprovedImageView.setImage(getNotApprovedImage());
            cityOk = false;
            chooseDeliveryButton();
        } else {
            cityApprovedImageView.setImage(getApprovedImage());
            cityOk = true;
            chooseDeliveryButton();
        }
    }

    @FXML
    private void checkEmail() {
        if (iMat.emailValidator.isValid(mailTextField.getText())) {
            mailApprovedImageView.setImage(getApprovedImage());
            mailOk = true;
            chooseDeliveryButton();
        } else {
            mailApprovedImageView.setImage(getNotApprovedImage());
            mailOk = false;
            chooseDeliveryButton();
        }
    }

    @FXML
    private void checkHoldersName() {
        if (holdersNameTextField.getText().equals("")) {
            holdersNameApprovedImageView.setImage(getNotApprovedImage());
            holdersNameOk = false;
            choosePaymentButton();
        } else if (!holdersNameTextField.getText().contains(" ")) {
            holdersNameApprovedImageView.setImage(getNotApprovedImage());
            holdersNameOk = false;
            choosePaymentButton();
        } else {
            holdersNameApprovedImageView.setImage(getApprovedImage());
            holdersNameOk = true;
            choosePaymentButton();
        }
    }

    @FXML
    private void checkValidDate() {
        if (validMonthTextField.getLength() > 2) {
            validMonthTextField.deletePreviousChar();
        } else if (!validMonthTextField.getText().matches("\\d+")) {
            //given text does not include digits
            validMonthTextField.deletePreviousChar();
        }

        if (validYearTextField.getLength() > 2) {
            validYearTextField.deletePreviousChar();
        } else if (!validYearTextField.getText().matches("\\d+")) {
            //given text does not include digits
            validYearTextField.deletePreviousChar();
        }

        if (checkMonth() && checkYear()) {
            validDateApprovedImageView.setImage(getApprovedImage());
            validDateOk = true;
            choosePaymentButton();
        } else {
            validDateApprovedImageView.setImage(getNotApprovedImage());
            validDateOk = false;
            choosePaymentButton();
        }
    }


    @FXML
    private void notDoneDelivery() {
        helpDelivery.setStrokeWidth(3.0);
    }

    @FXML
    private void notDonePay() {
        helpPayment.setStrokeWidth(3.0);
    }

    boolean checkMonth() {
        int month = -1;
        if (!validMonthTextField.getText().equals("")) {
            month = Integer.parseInt(validMonthTextField.getText());
        }

        return  month >= 1 && month <=12 && validMonthTextField.getLength() > 0;
    }

    boolean checkYear() {
        int year = -1;
        if (!validYearTextField.getText().equals("")) {
            year = Integer.parseInt(validYearTextField.getText());
        }
        return  year >= 21 && year <=31 && validYearTextField.getLength() == 2;
    }

    public boolean isAlpha(String name) {
        return name.matches("[a-??A-??]+");
    }

    Image getApprovedImage() {
        return new Image(Objects.requireNonNull(getClass().getClassLoader().getResourceAsStream("resources/approved.png")));
    }

    Image getNotApprovedImage() {
        return new Image(Objects.requireNonNull(getClass().getClassLoader().getResourceAsStream("resources/notApproved.png")));
    }

    void chooseDeliveryButton() {
        if (isDeliveryOk()) {
            deliveryOkButton.toFront();
        } else {
            notDoneDeliveryButton.toFront();
        }
    }

    void choosePaymentButton() {
        if (isPaymentOk()) {
            paymentOkButton.toFront();
        } else {
            notDonePayButton.toFront();
        }
    }

    Boolean isDeliveryOk() {
        return firstNameOk && surNameOk && postadressOk && postcodeOk &&
                cityOk && phoneOk && mailOk;
    }

    Boolean isPaymentOk() {
        return cardtypeOk && validDateOk && holdersNameOk && verificationCodeOk && cardnrOk;
    }

    @FXML
    void hoverCloseThanks() {
        thanksClose.setImage(new Image(getClass().getClassLoader().getResourceAsStream(
                "resources/midgreenClose.png")));
    }

    @FXML
    void hoverDoneCloseThanks() {
        thanksClose.setImage(new Image(getClass().getClassLoader().getResourceAsStream(
                "resources/greenclose.png")));
    }

    @FXML
    void pressedCloseThanks() {
        thanksClose.setImage(new Image(getClass().getClassLoader().getResourceAsStream(
                "resources/lightgreenClose.png")));
    }

    @FXML
    void hoverClosePay() {
        payClose.setImage(new Image(getClass().getClassLoader().getResourceAsStream(
                "resources/midgreenClose.png")));
    }

    @FXML
    void hoverDoneClosePay() {
        payClose.setImage(new Image(getClass().getClassLoader().getResourceAsStream(
                "resources/greenclose.png")));
    }

    @FXML
    void pressedClosePay() {
        payClose.setImage(new Image(getClass().getClassLoader().getResourceAsStream(
                "resources/lightgreenClose.png")));
    }

    @FXML
    void hoverCloseDelivery() {
        deliveryClose.setImage(new Image(getClass().getClassLoader().getResourceAsStream(
                "resources/midgreenClose.png")));
    }

    @FXML
    void hoverDoneCloseDelivery() {
        deliveryClose.setImage(new Image(getClass().getClassLoader().getResourceAsStream(
                "resources/greenclose.png")));
    }

    @FXML
    void pressedCloseDelivery() {
        deliveryClose.setImage(new Image(getClass().getClassLoader().getResourceAsStream(
                "resources/lightgreenClose.png")));
    }

    @FXML
    void saveList() {
        iMatController.getShoppingListController().createListFromCheckout(model.getShoppingCart().getItems());
        text1.setText("Snyggt! ???");
        text1.setStyle("-fx-font-size: 24px;");
        text2.setText("Du hittar din lista under ??Ink??pslistor?? ");
        text2.setStyle("-fx-font-size: 20px;");
        saveListButton.setStyle("-fx-background-color: #878787;");
    }



}
