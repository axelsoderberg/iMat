package iMat;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.layout.AnchorPane;
import se.chalmers.cse.dat216.project.ProductCategory;

import java.awt.*;
import java.io.IOException;

public class subcategories extends AnchorPane {

    @FXML
    private Checkbox checkbox;

    private ProductCategory pc;
    private storeController parentController;

    public subcategories(ProductCategory pc, storeController store){
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("subcategories.fxml"));
        fxmlLoader.setRoot(this);
        fxmlLoader.setController(this);

        try {
            fxmlLoader.load();
        } catch (IOException exception) {
            throw new RuntimeException(exception);
        }

        this.pc = pc;
        this.parentController = store;

        checkbox.setLabel(pc.toString());
    }


}
