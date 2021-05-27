package iMat;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Text;
import se.chalmers.cse.dat216.project.Product;
import se.chalmers.cse.dat216.project.ShoppingItem;

import javax.print.DocFlavor;
import java.io.IOException;
import java.util.List;
import java.util.Objects;

public class oneShoppingList extends AnchorPane {

    @FXML private Text nameText;
    private List<Product> productList;
    private final Model model = Model.getInstance();
    private iMatController controller;
    private shoppinglists parentController;

    public oneShoppingList(shoppinglists parentController) {
        this.parentController = parentController;

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

    public List<Product> getProductList() {
        return productList;
    }

    @FXML private void editButton(){
        parentController.editShoppingList(this);
    }

    @FXML private void addButton(){

    }
}
