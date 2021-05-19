package iMat;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.RadioButton;
import javafx.scene.layout.AnchorPane;
import se.chalmers.cse.dat216.project.ProductCategory;

import java.awt.*;
import java.io.IOException;

public class categories extends AnchorPane {

    @FXML
    private RadioButton radioButton;

    private final String c;
    private final storeController parentController;

    public categories(String c, storeController store){
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("categories.fxml"));
        fxmlLoader.setRoot(this);
        fxmlLoader.setController(this);

        try {
            fxmlLoader.load();
        } catch (IOException exception) {
            throw new RuntimeException(exception);
        }

        this.c = c;
        this.parentController = store;

        radioButton.setText(c);
    }

    @FXML
    protected void onClick(Event event){
        parentController.updateSubcategories(c.toString());
    }
}
