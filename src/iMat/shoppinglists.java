package iMat;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;

import java.io.IOException;
import java.util.Map;

public class shoppinglists extends AnchorPane {

    @FXML private AnchorPane createView;
    @FXML private AnchorPane listView;
    private Map<String, oneShoppingList> shoppingListMap;

    public shoppinglists() {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("shoppinglists.fxml"));
        fxmlLoader.setRoot(this);
        fxmlLoader.setController(this);

        try {
            fxmlLoader.load();
        } catch (IOException exception) {
            throw new RuntimeException(exception);
        }
    }
    private void openShoppingList(){
        listView.toFront();
    }

    private void buyShoppingList(){
        listView.toBack();
    }

    private void editShoppingList(){

    }

    private void createShoppingList(){
        createView.toFront();
    }

    private void completeCreate(){
        createView.toBack();
    }
}
