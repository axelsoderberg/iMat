package iMat;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Group;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import se.chalmers.cse.dat216.project.IMatDataHandler;
import se.chalmers.cse.dat216.project.Product;
import se.chalmers.cse.dat216.project.ShoppingItem;

import java.io.IOException;
import java.util.Objects;

public class detailView extends AnchorPane {

    @FXML private ImageView detailViewImage;
    @FXML private Label detailViewProductName;
    @FXML private Label detailViewPriceLabel;
    @FXML private ImageView detailViewFavoriteImage;
    @FXML private Button addButton;
    @FXML private Group addOrRemoveButton;
    @FXML private Label productCardAmountLabel;
    @FXML private Button contentsButton;
    @FXML private Button nutritionButton;
    @FXML private Button productInformationButton;
    @FXML private Button moreInformationButton;
    @FXML private Label detailViewAmountLabel;

    private final Model model = Model.getInstance();

    ShoppingItem shoppingItem;
    storeController parentController;

    private final static double kImageWidth = 82;
    private final static double kImageRatio = 0.75;

    public detailView(ShoppingItem shoppingItem, storeController parentController) {

        this.shoppingItem = shoppingItem;
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("detailView.fxml"));
        fxmlLoader.setRoot(this);
        fxmlLoader.setController(this);

        try {
            fxmlLoader.load();
        } catch (IOException exception) {
            throw new RuntimeException(exception);
        }

        if (shoppingItem.getAmount() != 0) {
            addButton.toBack();
        }
        detailViewProductName.setText(shoppingItem.getProduct().getName());
        detailViewPriceLabel.setText(String.format("%.2f", shoppingItem.getProduct().getPrice()) + " " + shoppingItem.getProduct().getUnit());
        detailViewImage.setImage(model.getImage(shoppingItem.getProduct(), kImageWidth, kImageWidth*kImageRatio));
        detailViewAmountLabel.setText((int) shoppingItem.getAmount() + " " + shoppingItem.getProduct().getUnitSuffix());
        // if favorite....
        if (model.isFavorite(shoppingItem.getProduct())) {
            detailViewFavoriteImage.setImage(new Image(Objects.requireNonNull(getClass().getClassLoader().getResourceAsStream("resources/heartFilled.png"))));
        } //annars ej ifyllt hjärta
        this.parentController = parentController;
    }

    @FXML
    private void handleAddAction() {
        parentController.productCardMap.get(shoppingItem.getProduct()).handleAddAction();
        addOrRemoveButton.toFront();
        detailViewAmountLabel.setText((int) shoppingItem.getAmount() + " " + shoppingItem.getProduct().getUnitSuffix());
    }

    @FXML
    private void handleRemoveAction() {
        parentController.productCardMap.get(shoppingItem.getProduct()).handleRemoveAction();
        if (shoppingItem.getAmount() == 0) { //Om man tog bort den sista i kundvagnen
            addButton.toFront();
        } else {
            detailViewAmountLabel.setText((int) shoppingItem.getAmount() + " " + shoppingItem.getProduct().getUnitSuffix());
        }
    }

    @FXML
    private void handleFavoriteAction() {
        if (model.isFavorite(shoppingItem.getProduct())) {
            parentController.productCardMap.get(shoppingItem.getProduct()).handleFavoriteAction();
            detailViewFavoriteImage.setImage(new Image(Objects.requireNonNull(getClass().getClassLoader().getResourceAsStream("resources/heart.png"))));
        } else {
            parentController.productCardMap.get(shoppingItem.getProduct()).handleFavoriteAction();
            detailViewFavoriteImage.setImage(new Image(Objects.requireNonNull(getClass().getClassLoader().getResourceAsStream("resources/heartFilled.png"))));
        }
    }

    public ShoppingItem getShoppingItem() {
        return this.shoppingItem;
    }

    @FXML
    private void nutritionSelected() {
        contentsButton.setStyle("-fx-background-color: #C3D5BD;");
        nutritionButton.setStyle("-fx-background-color: #ECEBEB;");
        productInformationButton.setStyle("set-fx-background-color: #C3D5BD;");
        moreInformationButton.setStyle("-fx-background-color: #C3D5BD;");
    }

    @FXML
    private void contentsSelected() {
        contentsButton.setStyle("-fx-background-color: #ECEBEB;");
        nutritionButton.setStyle("-fx-background-color: #C3D5BD;");
        productInformationButton.setStyle("-fx-background-color: #C3D5BD;");
        moreInformationButton.setStyle("-fx-background-color: #C3D5BD;");
    }

    @FXML
    private void productInformationSelected() {
        contentsButton.setStyle("-fx-background-color: #C3D5BD;");
        nutritionButton.setStyle("-fx-background-color: #C3D5BD;");
        productInformationButton.setStyle("-fx-background-color: #ECEBEB;");
        moreInformationButton.setStyle("-fx-background-color: #C3D5BD;");
    }

    @FXML
    private void moreInformationSelected() {
        contentsButton.setStyle("-fx-background-color: #C3D5BD;");
        nutritionButton.setStyle("-fx-background-color: #C3D5BD;");
        productInformationButton.setStyle("-fx-background-color: #C3D5BD;");
        moreInformationButton.setStyle("-fx-background-color: #ECEBEB;");
    }

    @FXML
    public void closeDetailView() {
        parentController.closeDetailView();
    }

    @FXML private void mouseStrap(){

    }
}

