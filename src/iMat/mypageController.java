package iMat;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.AnchorPane;

import java.io.IOException;

public class mypageController extends AnchorPane {

    @FXML TextField firstNameTextField;
    @FXML TextField surNameTextField;
    @FXML TextField postAdressTextField;
    @FXML TextField postCodeTextField;
    @FXML TextField cityTextField;
    @FXML TextField phoneTextField;
    @FXML TextField mailTextField;

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

        addListeners();
    }

    void addListeners() {

        // Denna metod körs när textfältet är fokuserat och man trycker Enter
        firstNameTextField.setOnKeyPressed(e -> {
            if (e.getCode() == KeyCode.ENTER) {
                // Spara metoden här
                model.getCustomer().setLastName(firstNameTextField.getText());

            }
        });

        firstNameTextField.focusedProperty().addListener(new ChangeListener<Boolean>()
        {
            @Override
            public void changed(ObservableValue<? extends Boolean> arg0, Boolean oldPropertyValue, Boolean newPropertyValue)
            {
                if (newPropertyValue)
                {

                }
                else
                {
                    // Denna körs när fältet inte längre är fokuserat, så vill ha spara metod här också
                    model.getCustomer().setLastName(firstNameTextField.getText());
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
                if (newPropertyValue)
                {

                }
                else
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
                model.getCustomer().setLastName(postAdressTextField.getText());

            }
        });

        postAdressTextField.focusedProperty().addListener(new ChangeListener<Boolean>()
        {
            @Override
            public void changed(ObservableValue<? extends Boolean> arg0, Boolean oldPropertyValue, Boolean newPropertyValue)
            {
                if (newPropertyValue)
                {

                }
                else
                {
                    // Denna körs när fältet inte längre är fokuserat, så vill ha spara metod här också
                    model.getCustomer().setLastName(postAdressTextField.getText());
                }
            }
        });

        // Denna metod körs när textfältet är fokuserat och man trycker Enter
        postCodeTextField.setOnKeyPressed(e -> {
            if (e.getCode() == KeyCode.ENTER) {
                // Spara metoden här
                model.getCustomer().setLastName(postCodeTextField.getText());

            }
        });

        postCodeTextField.focusedProperty().addListener(new ChangeListener<Boolean>()
        {
            @Override
            public void changed(ObservableValue<? extends Boolean> arg0, Boolean oldPropertyValue, Boolean newPropertyValue)
            {
                if (newPropertyValue)
                {

                }
                else
                {
                    // Denna körs när fältet inte längre är fokuserat, så vill ha spara metod här också
                    model.getCustomer().setLastName(postCodeTextField.getText());
                }
            }
        });

        // Denna metod körs när textfältet är fokuserat och man trycker Enter
        cityTextField.setOnKeyPressed(e -> {
            if (e.getCode() == KeyCode.ENTER) {
                // Spara metoden här
                model.getCustomer().setLastName(cityTextField.getText());

            }
        });

        cityTextField.focusedProperty().addListener(new ChangeListener<Boolean>()
        {
            @Override
            public void changed(ObservableValue<? extends Boolean> arg0, Boolean oldPropertyValue, Boolean newPropertyValue)
            {
                if (newPropertyValue)
                {

                }
                else
                {
                    // Denna körs när fältet inte längre är fokuserat, så vill ha spara metod här också
                    model.getCustomer().setLastName(cityTextField.getText());
                }
            }
        });

        // Denna metod körs när textfältet är fokuserat och man trycker Enter
        phoneTextField.setOnKeyPressed(e -> {
            if (e.getCode() == KeyCode.ENTER) {
                // Spara metoden här
                model.getCustomer().setLastName(phoneTextField.getText());

            }
        });

        phoneTextField.focusedProperty().addListener(new ChangeListener<Boolean>()
        {
            @Override
            public void changed(ObservableValue<? extends Boolean> arg0, Boolean oldPropertyValue, Boolean newPropertyValue)
            {
                if (newPropertyValue)
                {

                }
                else
                {
                    // Denna körs när fältet inte längre är fokuserat, så vill ha spara metod här också
                    model.getCustomer().setLastName(phoneTextField.getText());
                }
            }
        });

        // Denna metod körs när textfältet är fokuserat och man trycker Enter
        mailTextField.setOnKeyPressed(e -> {
            if (e.getCode() == KeyCode.ENTER) {
                // Spara metoden här
                model.getCustomer().setLastName(mailTextField.getText());

            }
        });

        mailTextField.focusedProperty().addListener(new ChangeListener<Boolean>()
        {
            @Override
            public void changed(ObservableValue<? extends Boolean> arg0, Boolean oldPropertyValue, Boolean newPropertyValue)
            {
                if (newPropertyValue)
                {

                }
                else
                {
                    // Denna körs när fältet inte längre är fokuserat, så vill ha spara metod här också
                    model.getCustomer().setLastName(mailTextField.getText());
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
    }

    @FXML
    private void checkPhoneNumber() {
        if (phoneTextField.getLength() > 10) {
            phoneTextField.deletePreviousChar();
        } else if (!phoneTextField.getText().matches("\\d+")) {
            //given text does not include digits
            phoneTextField.deletePreviousChar();
        }
    }


}
