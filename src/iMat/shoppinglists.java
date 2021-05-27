package iMat;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.FlowPane;
import se.chalmers.cse.dat216.project.Product;
import se.chalmers.cse.dat216.project.ShoppingItem;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class shoppinglists extends AnchorPane {

    @FXML private AnchorPane createView;
    @FXML private AnchorPane listView;
    @FXML private TextField createShoppingListName;
    @FXML private FlowPane shoppingListListPane;
    @FXML private AnchorPane abortPane;
    private boolean close = false;
    private boolean save = false;
    private boolean addedProduct = false;
    private List<oneShoppingList> shoppingListList = new ArrayList<>();
    private Model model = Model.getInstance();
    private int listNumber;

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

    void updateShoppingListList(){
        shoppingListListPane.getChildren().clear();
        for (oneShoppingList oneShoppingList : shoppingListList){
            shoppingListListPane.getChildren().add(oneShoppingList);
        }
    }


    @FXML private void openShoppingList(){
        listView.toFront();
    }

    @FXML private void closeShoppingList(){
        listView.toBack();
    }

    @FXML private void buyShoppingList(){
        closeShoppingList();
    }

    void editShoppingList(oneShoppingList listItem){
        List<Product> productList = new ArrayList<>();
        createView.toFront();
        createShoppingListName.setText(listItem.getListName());
    }

    @FXML private void createShoppingList(){
        oneShoppingList listItem = new oneShoppingList(this);
        shoppingListList.add(listItem);
        listNumber = shoppingListList.size() - 1;
        editShoppingList(shoppingListList.get(listNumber));
    }

    @FXML private void completeCreate(){
        if(createShoppingListName.getText() != null) {
            shoppingListList.get(listNumber).setListName(createShoppingListName.getText());
        }
        else {
            shoppingListList.get(listNumber).setListName("Inköpslista");
        }
        updateShoppingListList();
        if(!shoppingListList.contains(shoppingListList.get(listNumber))) {
            shoppingListList.add(shoppingListList.get(listNumber));
        }
        closeCreateView();
    }

    @FXML private void abortCreate(){
        abortPane.toFront();
    }

    private Product getProduct(){
        return null;
    }

    @FXML private void add(){
        addedProduct = true;
    }

    @FXML private void closeCreateView(){
        createView.toBack();
        abortPane.toBack();
    }

    public void setListNumber(int setter){
        listNumber = setter;
    }
}
