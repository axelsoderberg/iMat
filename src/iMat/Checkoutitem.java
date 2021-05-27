package iMat;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Group;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Text;
import se.chalmers.cse.dat216.project.Product;
import se.chalmers.cse.dat216.project.ShoppingItem;

import java.io.IOException;
import java.util.Objects;

public class Checkoutitem extends AnchorPane {

    @FXML Text checkoutitemNameText;
    @FXML Label checkoutitemAmountLabel;
    @FXML Text checkoutitemPrizePerItemText;
    @FXML Text checkoutitemTotalItemPrizeText;
    @FXML ImageView checkoutitemProductImage;

    private Model model = Model.getInstance();

    private ShoppingItem item;
    private final static double kImageWidth = 82;
    private final static double kImageRatio = 0.75;

    public Checkoutitem(ShoppingItem item) {
        this.item = item;

        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("checkoutitem.fxml"));
        fxmlLoader.setRoot(this);
        fxmlLoader.setController(this);

        try {
            fxmlLoader.load();
        } catch (IOException exception) {
            throw new RuntimeException(exception);
        }

        checkoutitemNameText.setText(item.getProduct().getName());
        checkoutitemPrizePerItemText.setText(String.format("%.2f", item.getProduct().getPrice()) + " " + item.getProduct().getUnit());
        checkoutitemProductImage.setImage(model.getImage(item.getProduct(), kImageWidth, kImageWidth*kImageRatio));
        checkoutitemTotalItemPrizeText.setText(item.getTotal() + " kr");
        checkoutitemAmountLabel.setText((int) item.getAmount() + " " + item.getProduct().getUnitSuffix());
    }

    @FXML
    private void handleAddAction() {
        item.setAmount(item.getAmount() + 1);
        model.addToShoppingCart(item);
        checkoutitemAmountLabel.setText((int) item.getAmount() + " " + item.getProduct().getUnitSuffix());
        checkoutitemTotalItemPrizeText.setText(String.format("%.2f",item.getTotal()) + " kr");
        model.getShoppingCart().fireShoppingCartChanged(item, true);
    }

    @FXML
    private void handleRemoveAction() {
        item.setAmount(item.getAmount() - 1);
        if (item.getAmount() == 0) { //Om man tog bort den sista i kundvagnen
            //radera item från listan
            model.getShoppingCart().removeItem(item);
            model.getShoppingCart().fireShoppingCartChanged(item, false);
        } else {
            checkoutitemAmountLabel.setText((int) item.getAmount() + " " + item.getProduct().getUnitSuffix());
            checkoutitemTotalItemPrizeText.setText(String.format("%.2f",item.getTotal()) + " kr");
            model.getShoppingCart().fireShoppingCartChanged(item, false);
        }
    }
}
