package iMat;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.FlowPane;
import javafx.scene.text.Text;
import se.chalmers.cse.dat216.project.Order;
import se.chalmers.cse.dat216.project.ShoppingItem;

import java.io.IOException;
import java.util.List;
import java.util.Objects;

public class mypageController extends AnchorPane {

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

    @FXML AnchorPane orderView;
    @FXML FlowPane orderFlowPane;
    @FXML Text orderViewName;
    @FXML Label orderSumText;
    @FXML FlowPane orderListFlowPane;

    private final Model model = Model.getInstance();
    OldOrder currentOrder;

    public mypageController() {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("mypage.fxml"));
        fxmlLoader.setRoot(this);
        fxmlLoader.setController(this);

        try {
            fxmlLoader.load();
        } catch (IOException exception) {
            throw new RuntimeException(exception);
        }

        initFields();
        addListeners();

        initComboBoxCardtype();

        checkApprovals();
        cardtypeApprovedImageView.setImage(getApprovedImage());

        populateOrderFlow();
    }

    void openUpdate() {
        initFields();
        cardTypeComboBox.getSelectionModel().select(model.getCreditCard().getCardType());
        checkApprovals();
    }

    void initFields() {
        firstNameTextField.setText(model.getCustomer().getFirstName());
        surNameTextField.setText(model.getCustomer().getLastName());
        postAdressTextField.setText(model.getCustomer().getPostAddress());
        postCodeTextField.setText(model.getCustomer().getPostCode());
        cityTextField.setText(model.getCustomer().getAddress());
        phoneTextField.setText(model.getCustomer().getPhoneNumber());
        mailTextField.setText(model.getCustomer().getEmail());
        holdersNameTextField.setText(model.getCreditCard().getHoldersName());
        verificationCodeTextField.setText(Integer.toString(model.getCreditCard().getVerificationCode()));
        cardNrTextField.setText(model.getCreditCard().getCardNumber());
        validMonthTextField.setText(Integer.toString(model.getCreditCard().getValidMonth()));
        validYearTextField.setText(Integer.toString(model.getCreditCard().getValidYear()));
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
        } else {
            postcodeApprovedImageView.setImage(getNotApprovedImage());
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
        } else {
            phoneApprovedImageView.setImage(getNotApprovedImage());
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
        } else {
            verificationCodeApprovedImageView.setImage(getNotApprovedImage());
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
        } else {
            cardNrApprovedImageView.setImage(getNotApprovedImage());
        }
    }

    @FXML
    private void checkFirstName() {
        if (!isAlpha(firstNameTextField.getText())) {
            firstNameTextField.deletePreviousChar();
        }

        if (firstNameTextField.getText() == "") {
            firstNameApprovedImageView.setImage(getNotApprovedImage());
        } else {
            firstNameApprovedImageView.setImage(getApprovedImage());
        }
    }

    @FXML
    private void checkLastName() {
        if (!isAlpha(surNameTextField.getText())) {
            surNameTextField.deletePreviousChar();
        }

        if (surNameTextField.getText().equals("")) {
            surNameApprovedImageView.setImage(getNotApprovedImage());
        } else {
            surNameApprovedImageView.setImage(getApprovedImage());
        }
    }

    @FXML
    private void checkAdress() {
        if (postAdressTextField.getText().equals("")) {
            postadressApprovedImageView.setImage(getNotApprovedImage());
        } else {
            postadressApprovedImageView.setImage(getApprovedImage());
        }
    }

    @FXML
    private void checkCity() {
        if (!isAlpha(cityTextField.getText())) {
            cityTextField.deletePreviousChar();
        }

        if (cityTextField.getText().equals("")) {
            cityApprovedImageView.setImage(getNotApprovedImage());
        } else {
            cityApprovedImageView.setImage(getApprovedImage());
        }
    }

    @FXML
    private void checkEmail() {
        if (iMat.emailValidator.isValid(mailTextField.getText())) {
            mailApprovedImageView.setImage(getApprovedImage());
        } else {
            mailApprovedImageView.setImage(getNotApprovedImage());
        }
    }

    @FXML
    private void checkHoldersName() {
        if (holdersNameTextField.getText().equals("")) {
            holdersNameApprovedImageView.setImage(getNotApprovedImage());
        } else if (!holdersNameTextField.getText().contains(" ")) {
            holdersNameApprovedImageView.setImage(getNotApprovedImage());
        } else {
            holdersNameApprovedImageView.setImage(getApprovedImage());
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
        } else {
            validDateApprovedImageView.setImage(getNotApprovedImage());
        }
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

    void populateOrderFlow(){
        orderFlowPane.getChildren().clear();
        for(Order order : model.getImatDataHandler().getOrders()) {
            orderFlowPane.getChildren().add(new OldOrder(order, this));
        }
    }

    void openOrderView(OldOrder order){
        orderView.toFront();
        orderListFlowPane.getChildren().clear();
        currentOrder = order;
        double totalPrice = 0;
        for (ShoppingItem shoppingItem : order.order){
            orderListFlowPane.getChildren().add(new OrderListItem(shoppingItem));
            totalPrice += shoppingItem.getTotal();
        }
        orderSumText.setText("Summa: " + String.format("%.2f", totalPrice) + " kr");
        orderViewName.setText(order.date);
    }

    @FXML void buyOrder(){
        for (ShoppingItem shoppingItem : currentOrder.order) {
            model.getShoppingCart().addItem(shoppingItem);
            model.getShoppingCart().fireShoppingCartChanged(shoppingItem, true);
        }
        orderView.toBack();
    }

    @FXML void closeOrderView(){
        orderView.toBack();
    }

}
