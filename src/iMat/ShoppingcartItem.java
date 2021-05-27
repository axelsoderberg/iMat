package iMat;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Text;
import se.chalmers.cse.dat216.project.Product;
import se.chalmers.cse.dat216.project.ShoppingItem;

import java.io.IOException;
import java.util.Objects;

public class ShoppingcartItem extends AnchorPane {

    private Model model = Model.getInstance();

    @FXML ImageView shoppingcartItemImageview;
    @FXML Text shoppingcartItemNameText;
    @FXML Text shoppingcartItemPrizeText;
    @FXML Label shoppingcartItemAmountLabel;

    private final static double kImageWidth = 82;
    private final static double kImageRatio = 0.75;

    ShoppingItem item;

    public ShoppingcartItem(ShoppingItem item) {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("shoppingcartItem.fxml"));
        fxmlLoader.setRoot(this);
        fxmlLoader.setController(this);

        try {
            fxmlLoader.load();
        } catch (IOException exception) {
            throw new RuntimeException(exception);
        }

        this.item = item;
        shoppingcartItemNameText.setText(item.getProduct().getName());
        shoppingcartItemPrizeText.setText(String.format("%.2f", item.getProduct().getPrice()) + " " + item.getProduct().getUnit());
        shoppingcartItemImageview.setImage(model.getImage(item.getProduct(), kImageWidth, kImageWidth*kImageRatio));
        shoppingcartItemAmountLabel.setText((int) item.getAmount() + " " + item.getProduct().getUnitSuffix());
    }

    @FXML
    private void handleAddAction() {
        item.setAmount(item.getAmount() + 1);
        model.addToShoppingCart(item);
        shoppingcartItemAmountLabel.setText((int) item.getAmount() + " " + item.getProduct().getUnitSuffix());
        model.getShoppingCart().fireShoppingCartChanged(item, true);
    }

    @FXML
    private void handleRemoveAction() {
        item.setAmount(item.getAmount() - 1);
        if (item.getAmount() == 0) { //Om man tog bort den sista i kundvagnen
            //radera item från listan
            model.getShoppingCart().removeItem(item);
        } else {
            shoppingcartItemAmountLabel.setText((int) item.getAmount() + " " + item.getProduct().getUnitSuffix());
        }
        model.getShoppingCart().fireShoppingCartChanged(item, false);
    }
}
