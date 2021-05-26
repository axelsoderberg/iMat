package iMat;

import javafx.fxml.FXMLLoader;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import se.chalmers.cse.dat216.project.Product;
import se.chalmers.cse.dat216.project.ShoppingItem;

import java.io.IOException;
import java.util.List;
import java.util.Objects;

public class oneShoppingList extends AnchorPane {

    private String listName;
    private List<Product> productList;
    private final Model model = Model.getInstance();

    public oneShoppingList() {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("oneshoppinglist.fxml"));
        fxmlLoader.setRoot(this);
        fxmlLoader.setController(this);

        try {
            fxmlLoader.load();
        } catch (IOException exception) {
            throw new RuntimeException(exception);
        }
    }
}
