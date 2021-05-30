package iMat;

import javafx.beans.property.BooleanProperty;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.CheckBox;
import javafx.scene.effect.BlendMode;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import se.chalmers.cse.dat216.project.ProductCategory;

import java.io.IOException;

public class subcategories extends AnchorPane {

    @FXML private CheckBox checkbox;
    @FXML private ImageView check;

    public ProductCategory pc;
    private storeController parentController;

    public subcategories(ProductCategory pc, storeController parentController) {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("subcategories.fxml"));
        fxmlLoader.setRoot(this);
        fxmlLoader.setController(this);

        try {
            fxmlLoader.load();
        } catch (IOException exception) {
            throw new RuntimeException(exception);
        }

        this.pc = pc;
        this.parentController = parentController;

        checkbox.setText(parentController.subCatConverter(pc));
    }

    public CheckBox getCheckbox() {
        return checkbox;
    }

    @FXML
    public void checkToBack() {
        check.toBack();
    }

    @FXML
    public void checkToFront() {
        check.toFront();
    }

    @FXML
    public void onClick() {
        if (checkbox.isSelected()) {
            parentController.addSubcategory(pc);
            parentController.removeSelectionFromAllSubCats();
        } else
            parentController.removeSubcategory(pc);
    }
}
