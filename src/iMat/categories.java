package iMat;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.RadioButton;
import javafx.scene.layout.AnchorPane;
import se.chalmers.cse.dat216.project.ProductCategory;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.io.IOException;

public class categories extends AnchorPane {

    @FXML
    private RadioButton radioButton;

    private final String c;
    storeController parentController;

    public categories(String c, storeController parentController) {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("categories.fxml"));
        fxmlLoader.setRoot(this);
        fxmlLoader.setController(this);

        try {
            fxmlLoader.load();
        } catch (IOException exception) {
            throw new RuntimeException(exception);
        }

        this.c = c;
        this.parentController = parentController;

        radioButton.setText(c);
    }

    public RadioButton getRadioButton() {
        return radioButton;
    }

    @FXML
    private void onClick() {
        parentController.updateSubcategories(c);
    }
}
