package iMat;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.CheckBox;
import javafx.scene.layout.AnchorPane;
import se.chalmers.cse.dat216.project.ProductCategory;

import java.io.IOException;

public class subcategories extends AnchorPane {

    @FXML private CheckBox checkbox;

    private String pc;

    public subcategories(String pc) {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("subcategories.fxml"));
        fxmlLoader.setRoot(this);
        fxmlLoader.setController(this);

        try {
            fxmlLoader.load();
        } catch (IOException exception) {
            throw new RuntimeException(exception);
        }

        this.pc = pc;

        checkbox.setText(pc);
    }

    public CheckBox getCheckbox() {
        return checkbox;
    }
}
