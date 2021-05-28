package iMat;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;
import se.chalmers.cse.dat216.project.Product;
import se.chalmers.cse.dat216.project.ShoppingItem;

import java.io.IOException;

public class shoppingListItem extends AnchorPane{
    @FXML
    Text shoppingListItemNameText;
    @FXML
    Label shoppingListItemAmountLabel;
    @FXML Text shoppingListItemPrizePerItemText;
    @FXML Text shoppingListItemTotalItemPrizeText;
    @FXML
    ImageView shoppingListItemProductImage;
    @FXML
    Button removeButton;
    @FXML
    Rectangle removeBackground;

    private final Product product;
    ShoppingItem shoppingItem;
    oneShoppingList parentController;
    private Model model = Model.getInstance();

    public shoppingListItem(ShoppingItem item, oneShoppingList parentController){

        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("shoppingListItem.fxml"));
        fxmlLoader.setRoot(this);
        fxmlLoader.setController(this);

        try {
            fxmlLoader.load();
        } catch (IOException exception) {
            throw new RuntimeException(exception);
        }

        this.parentController = parentController;
        this.product = item.getProduct();
        this.shoppingItem = item;
        shoppingListItemNameText.setText(item.getProduct().getName());
        shoppingListItemPrizePerItemText.setText(String.format("%.2f", item.getProduct().getPrice()) + " " + item.getProduct().getUnit());
        shoppingListItemTotalItemPrizeText.setText("" + shoppingItem.getTotal() + " kr");
        shoppingListItemProductImage.setImage(model.getImage(item.getProduct()));
        shoppingListItemAmountLabel.setText((int) item.getAmount() + " st");
    }

    public void setAddView(){
        removeBackground.toFront();
        if(shoppingItem.getAmount() == 0) {
            removeButton.toFront();
        }
    }

    public void setListView(){
        removeBackground.toBack();
        removeButton.toBack();
    }

    @FXML private void addProduct(){
        if(shoppingItem.getAmount() == 0){
            parentController.getProductList().add(shoppingItem);
        }
        shoppingItem.setAmount(shoppingItem.getAmount() + 1);
        updateListItem();
    }

    @FXML private void removeOneProduct(){
        if (shoppingItem.getAmount() != 0){
            shoppingItem.setAmount(shoppingItem.getAmount() - 1);
            if(shoppingItem.getAmount() == 0){
                removeButton.toBack();
                parentController.getProductList().remove(shoppingItem);
            }
        }
        updateListItem();
    }

    private void updateListItem(){
        shoppingListItemAmountLabel.setText("" + shoppingItem.getAmount());
        shoppingListItemPrizePerItemText.setText(String.format("%.2f", shoppingItem.getProduct().getPrice()) + " " + shoppingItem.getProduct().getUnit());
        shoppingListItemTotalItemPrizeText.setText("" + shoppingItem.getTotal() + " kr");
    }

    @FXML private void removeAllProducts(){
        shoppingItem.setAmount(0);
        parentController.getProductList().remove(shoppingItem);
        removeButton.toBack();
    }
}
