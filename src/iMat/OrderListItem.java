package iMat;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Text;
import se.chalmers.cse.dat216.project.ShoppingItem;

import java.io.IOException;

public class OrderListItem extends AnchorPane {

    @FXML ImageView orderListItemImage;
    @FXML Text orderListItemName;
    @FXML Text orderListItemAmount;
    @FXML Text orderListItemTotalPrice;
    @FXML Text orderListItemPrice;

    Model model = Model.getInstance();

    public OrderListItem(ShoppingItem shoppingItem){
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("orderListItem.fxml"));
        fxmlLoader.setRoot(this);
        fxmlLoader.setController(this);

        try {
            fxmlLoader.load();
        } catch (IOException exception) {
            throw new RuntimeException(exception);
        }

        orderListItemImage.setImage(model.getImage(shoppingItem.getProduct()));
        orderListItemName.setText(shoppingItem.getProduct().getName());
        orderListItemAmount.setText("" + shoppingItem.getAmount());
        orderListItemTotalPrice.setText(String.format("%.2f", shoppingItem.getTotal()) + " kr");
        orderListItemPrice.setText(String.format("%.2f", shoppingItem.getProduct().getPrice()) + " kr/" + shoppingItem.getProduct().getUnitSuffix());
    }

}
