package iMat;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Text;
import se.chalmers.cse.dat216.project.Product;
import se.chalmers.cse.dat216.project.ShoppingItem;

import javax.print.DocFlavor;
import java.io.IOException;
import java.util.*;

public class oneShoppingList extends AnchorPane {

    @FXML private Label nameText;
    private List<ShoppingItem> productList = new ArrayList<ShoppingItem>();
    private final Model model = Model.getInstance();
    private iMatController controller;
    private shoppinglists parentController;


    private Map<Product, shoppingListItem> shoppingListProductMap;

    public oneShoppingList(shoppinglists parentController) {
        this.parentController = parentController;
        productListInit();

        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("oneshoppinglist.fxml"));
        fxmlLoader.setRoot(this);
        fxmlLoader.setController(this);

        try {
            fxmlLoader.load();
        } catch (IOException exception) {
            throw new RuntimeException(exception);
        }
    }

    public void setListName(String listName){
        nameText.setText(listName);
    }

    public String getListName(){
        return nameText.getText();
    }

    public List<ShoppingItem> getProductList() {
        return productList;
    }

    public Map<Product, shoppingListItem> getShoppingListProductMap() {
        return shoppingListProductMap;
    }

    @FXML private void editButton(){
        parentController.editShoppingList(this);
    }

    @FXML private void addButton(){
        parentController.openShoppingList(this);
    }

    private void productListInit() {
        shoppingListProductMap = new HashMap<Product, shoppingListItem>();
        for (ShoppingItem item : getProductList()) {
            shoppingListItem shoppingListItem = new shoppingListItem(item, this);
            shoppingListProductMap.put(item.getProduct(), shoppingListItem);
        }

        for (Product product : model.getProducts()) {
            if (!shoppingListProductMap.containsKey(product)) {
                shoppingListItem shoppingListItem = new shoppingListItem(new ShoppingItem(product, 0), this);
                shoppingListProductMap.put(product, shoppingListItem);
            }
        }
    }

    public void addProducts(List<ShoppingItem> items) {
        for (ShoppingItem item : items) {
            shoppingListItem shoppingListItem = new shoppingListItem(item, this);
            productList.add(shoppingListItem.shoppingItem);
        }
    }

    shoppinglists getParentController(){
        return parentController;
    }
}
