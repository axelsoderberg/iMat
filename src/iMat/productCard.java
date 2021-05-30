package iMat;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Group;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;
import se.chalmers.cse.dat216.project.IMatDataHandler;
import se.chalmers.cse.dat216.project.Product;
import se.chalmers.cse.dat216.project.ShoppingItem;

import java.io.IOException;
import java.util.Objects;

public class productCard extends AnchorPane {

    @FXML ImageView productCardImageView;
    @FXML Label productCardNameLabel;
    @FXML Label productCardPrizeLabel;
    @FXML ImageView productCardFavoriteImageView;
    @FXML Button addButton;
    @FXML Group addOrRemoveButton;
    @FXML Label productCardAmountLabel;
    @FXML
    Rectangle shadowBox;
    @FXML
    Circle prizeCircle;

    private final Model model = Model.getInstance();

    private final Product product;
    ShoppingItem shoppingItem;
    storeController parentController;

    private final static double kImageWidth = 82;
    private final static double kImageRatio = 0.75;

    public productCard(ShoppingItem item, storeController parentController) {

        this.shoppingItem = item;

        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("productCard.fxml"));
        fxmlLoader.setRoot(this);
        fxmlLoader.setController(this);

        try {
            fxmlLoader.load();
        } catch (IOException exception) {
            throw new RuntimeException(exception);
        }

        this.product = shoppingItem.getProduct();
        productCardNameLabel.setText(product.getName());
        productCardPrizeLabel.setText(String.format("%.2f", product.getPrice()) + "\n" + product.getUnit());
        productCardImageView.setImage(model.getImage(product, kImageWidth, kImageWidth*kImageRatio));
        // if favorite....
        if (model.isFavorite(product)) {
            productCardFavoriteImageView.setImage(new Image(Objects.requireNonNull(getClass().getClassLoader().getResourceAsStream("resources/heartFilled.png"))));
        } //annars ej ifyllt hjärta

            this.parentController = parentController;

        if (shoppingItem.getAmount() > 0) {
            addOrRemoveButton.toFront();
            productCardAmountLabel.setText((int) shoppingItem.getAmount() + " " + shoppingItem.getProduct().getUnitSuffix());
        }

    }

    void update(boolean clear) {
        if (clear) {
            shoppingItem.setAmount(0);
            addButton.toFront();
        } else {
            if (shoppingItem.getAmount() == 0) { //Om man tog bort den sista i kundvagnen
                addButton.toFront();
            } else {
                productCardAmountLabel.setText((int) shoppingItem.getAmount() + " " + shoppingItem.getProduct().getUnitSuffix());
            }
        }
    }

    @FXML
    void handleAddAction() {
        shoppingItem.setAmount(shoppingItem.getAmount() + 1);
        model.addToShoppingCart(shoppingItem);
        addOrRemoveButton.toFront();
        productCardAmountLabel.setText((int) shoppingItem.getAmount() + " " + shoppingItem.getProduct().getUnitSuffix());
        model.getShoppingCart().fireShoppingCartChanged(shoppingItem, true);
    }

    @FXML
    void handleRemoveAction() {
        shoppingItem.setAmount(shoppingItem.getAmount() - 1);
        if (shoppingItem.getAmount() == 0) { //Om man tog bort den sista i kundvagnen
            addButton.toFront();
            model.getShoppingCart().removeItem(shoppingItem);
        } else {
            productCardAmountLabel.setText((int) shoppingItem.getAmount() + " " + shoppingItem.getProduct().getUnitSuffix());
        }
        model.getShoppingCart().fireShoppingCartChanged(shoppingItem, false);
    }

    @FXML
    void handleFavoriteAction() {
        if (model.isFavorite(product)) {
            model.removeFavorite(product);
            productCardFavoriteImageView.setImage(new Image(Objects.requireNonNull(getClass().getClassLoader().getResourceAsStream("resources/heart.png"))));
        } else {
            model.addFavorite(product);
            productCardFavoriteImageView.setImage(new Image(Objects.requireNonNull(getClass().getClassLoader().getResourceAsStream("resources/heartFilled.png"))));
        }
    }

    public ShoppingItem getShoppingItem() {
        return this.shoppingItem;
    }

    @FXML private void hover(){
        shadowBox.toFront();
        prizeCircle.toFront();
        productCardPrizeLabel.toFront();
    }

    @FXML private void hoverEnd(){
        shadowBox.toBack();
    }

    @FXML
    private void openDetailView() {
        parentController.openDetailView(shoppingItem);
    }
}

