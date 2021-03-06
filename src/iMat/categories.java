package iMat;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.layout.AnchorPane;
import se.chalmers.cse.dat216.project.ProductCategory;

import java.io.IOException;

public class categories extends AnchorPane {

    @FXML private Label label;
    @FXML private AnchorPane categoryPane;

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

        label.setText(c.toString());
    }


    @FXML
    private void onClick() {
        if (c.equals("Favoriter")) {
            parentController.viewFavorites();
        } else if (c.equals("Erbjudanden")) {
            parentController.viewFeatured();
        } else {
            parentController.updateSubcategories(c);
            parentController.updateProductList(Model.getInstance().getList(c));
            parentController.currentCategory = c;
        }
        parentController.category.setText(c);
        parentController.categoriesLabel.setText(c);
    }

    @FXML
    private void hover() {
        categoryPane.setStyle("-fx-background-color: #629543;");
        label.setStyle("-fx-font-weight: bold;");
    }

    @FXML
    private void exitHover() {
        categoryPane.setStyle("-fx-background-color: #C3D5BD;");
        label.setStyle("-fx-font-weight: normal");
    }
}
