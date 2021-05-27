package iMat;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.AnchorPane;

import java.io.IOException;
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
    @FXML ComboBox validMonthComboBox;
    @FXML ComboBox validYearComboBox;
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

    private final Model model = Model.getInstance();

    public mypageController() {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("mypage.fxml"));
        fxmlLoader.setRoot(this);
        fxmlLoader.setController(this);

        try {
            fxmlLoader.load();
        } catch (IOException exception) {
            throw new RuntimeException(exception);
        }

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

        addListeners();

        initComboBoxCardtype();
        initComboBoxValidMonth();
        initComboBoxValidYear();

        checkApprovals();

    }

    void checkApprovals() {
        checkCardNumber();
        checkPhoneNumber();
        checkPostCode();
        checkVerificationCode();
    }

    void initComboBoxCardtype() {
        //det som ska visas i boxen
        cardTypeComboBox.getItems().addAll(model.getCardTypes());
        //bestämmer vad som är valt från början
        cardTypeComboBox.getSelectionModel().select(model.getCreditCard().getCardType());

        cardTypeComboBox.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
                model.getCreditCard().setCardType(newValue);
            }
        });
    }

    void initComboBoxValidMonth() {
        //det som ska visas i boxen
        validMonthComboBox.getItems().addAll(model.getMonths());
        //bestämmer vad som är valt från början
        validMonthComboBox.getSelectionModel().select(model.getCreditCard().getValidMonth());

        validMonthComboBox.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
                model.getCreditCard().setValidMonth(Integer.parseInt(newValue));
            }
        });
    }

    void initComboBoxValidYear() {
        //det som ska visas i boxen
        validYearComboBox.getItems().addAll(model.getYears());
        //bestämmer vad som är valt från början
        validYearComboBox.getSelectionModel().select(model.getCreditCard().getValidYear());

        validYearComboBox.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
                model.getCreditCard().setValidYear(Integer.parseInt(newValue));
            }
        });
    }

    void addListeners() {

        // Denna metod körs när textfältet är fokuserat och man trycker Enter
        firstNameTextField.setOnKeyPressed(e -> {
            if (e.getCode() == KeyCode.ENTER) {
                // Spara metoden här
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
                    // Denna körs när fältet inte längre är fokuserat, så vill ha spara metod här också
                    model.getCustomer().setFirstName(firstNameTextField.getText());
                }
            }
        });

        // Denna metod körs när textfältet är fokuserat och man trycker Enter
        surNameTextField.setOnKeyPressed(e -> {
            if (e.getCode() == KeyCode.ENTER) {
                // Spara metoden här
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
                    // Denna körs när fältet inte längre är fokuserat, så vill ha spara metod här också
                    model.getCustomer().setLastName(surNameTextField.getText());
                }
            }
        });

        // Denna metod körs när textfältet är fokuserat och man trycker Enter
        postAdressTextField.setOnKeyPressed(e -> {
            if (e.getCode() == KeyCode.ENTER) {
                // Spara metoden här
                model.getCustomer().setAddress(postAdressTextField.getText());

            }
        });

        postAdressTextField.focusedProperty().addListener(new ChangeListener<Boolean>()
        {
            @Override
            public void changed(ObservableValue<? extends Boolean> arg0, Boolean oldPropertyValue, Boolean newPropertyValue)
            {
                if (!newPropertyValue)
                {
                    // Denna körs när fältet inte längre är fokuserat, så vill ha spara metod här också
                    model.getCustomer().setAddress(postAdressTextField.getText());
                }
            }
        });

        // Denna metod körs när textfältet är fokuserat och man trycker Enter
        postCodeTextField.setOnKeyPressed(e -> {
            if (e.getCode() == KeyCode.ENTER) {
                // Spara metoden här
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
                    // Denna körs när fältet inte längre är fokuserat, så vill ha spara metod här också
                    model.getCustomer().setPostCode(postCodeTextField.getText());
                }
            }
        });

        // Denna metod körs när textfältet är fokuserat och man trycker Enter
        cityTextField.setOnKeyPressed(e -> {
            if (e.getCode() == KeyCode.ENTER) {
                // Spara metoden här
                model.getCustomer().setPostAddress(cityTextField.getText());

            }
        });

        cityTextField.focusedProperty().addListener(new ChangeListener<Boolean>()
        {
            @Override
            public void changed(ObservableValue<? extends Boolean> arg0, Boolean oldPropertyValue, Boolean newPropertyValue)
            {
                if (!newPropertyValue)
                {
                    // Denna körs när fältet inte längre är fokuserat, så vill ha spara metod här också
                    model.getCustomer().setPostAddress(cityTextField.getText());
                }
            }
        });

        // Denna metod körs när textfältet är fokuserat och man trycker Enter
        phoneTextField.setOnKeyPressed(e -> {
            if (e.getCode() == KeyCode.ENTER) {
                // Spara metoden här
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
                    // Denna körs när fältet inte längre är fokuserat, så vill ha spara metod här också
                    model.getCustomer().setPhoneNumber(phoneTextField.getText());
                }
            }
        });

        // Denna metod körs när textfältet är fokuserat och man trycker Enter
        mailTextField.setOnKeyPressed(e -> {
            if (e.getCode() == KeyCode.ENTER) {
                // Spara metoden här
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
                    // Denna körs när fältet inte längre är fokuserat, så vill ha spara metod här också
                    model.getCustomer().setEmail(mailTextField.getText());
                }
            }
        });

        // Denna metod körs när textfältet är fokuserat och man trycker Enter
        holdersNameTextField.setOnKeyPressed(e -> {
            if (e.getCode() == KeyCode.ENTER) {
                // Spara metoden här
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
                    // Denna körs när fältet inte längre är fokuserat, så vill ha spara metod här också
                    model.getCreditCard().setHoldersName(holdersNameTextField.getText());
                    System.out.println(model.getCreditCard().getHoldersName());
                }
            }
        });

        // Denna metod körs när textfältet är fokuserat och man trycker Enter
        cardNrTextField.setOnKeyPressed(e -> {
            if (e.getCode() == KeyCode.ENTER) {
                // Spara metoden här
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
                    // Denna körs när fältet inte längre är fokuserat, så vill ha spara metod här också
                    model.getCreditCard().setCardNumber(cardNrTextField.getText());
                }
            }
        });

        // Denna metod körs när textfältet är fokuserat och man trycker Enter
        verificationCodeTextField.setOnKeyPressed(e -> {
            if (e.getCode() == KeyCode.ENTER) {
                // Spara metoden här
                model.getCreditCard().setVerificationCode(Integer.parseInt(verificationCodeTextField.getText()));
            }
        });

        verificationCodeTextField.focusedProperty().addListener(new ChangeListener<Boolean>()
        {
            @Override
            public void changed(ObservableValue<? extends Boolean> arg0, Boolean oldPropertyValue, Boolean newPropertyValue)
            {
                if (!newPropertyValue)
                {
                    // Denna körs när fältet inte längre är fokuserat, så vill ha spara metod här också
                    model.getCreditCard().setVerificationCode(Integer.parseInt(verificationCodeTextField.getText()));
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

    Image getApprovedImage() {
        return new Image(Objects.requireNonNull(getClass().getClassLoader().getResourceAsStream("resources/approved.png")));
    }

    Image getNotApprovedImage() {
        return new Image(Objects.requireNonNull(getClass().getClassLoader().getResourceAsStream("resources/notApproved.png")));
    }

}
