package iMat;

import javafx.beans.property.BooleanProperty;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.CheckBox;
import javafx.scene.layout.AnchorPane;
import se.chalmers.cse.dat216.project.ProductCategory;

import java.io.IOException;

public class subcategories extends AnchorPane {

    @FXML private CheckBox checkbox;

    private ProductCategory pc;
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
    public void onClick() {
        if (checkbox.isSelected())
            parentController.addSubcategory(pc);
        else
            parentController.removeSubcategory(pc);
    }
}
