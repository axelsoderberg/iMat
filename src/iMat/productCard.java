package iMat;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import se.chalmers.cse.dat216.project.IMatDataHandler;
import se.chalmers.cse.dat216.project.Product;

import java.io.IOException;
import java.util.Objects;

public class productCard extends AnchorPane {

    @FXML ImageView productCardImageView;
    @FXML Label productCardNameLabel;
    @FXML Label productCardPrizeLabel;
    @FXML ImageView productCardFavoriteImageView;

    private Model model = Model.getInstance();
    private IMatDataHandler iMatDataHandler;

    private Product product;

    private final static double kImageWidth = 82;
    private final static double kImageRatio = 0.75;

    public productCard(Product product) {

        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("productCard.fxml"));
        fxmlLoader.setRoot(this);
        fxmlLoader.setController(this);

        try {
            fxmlLoader.load();
        } catch (IOException exception) {
            throw new RuntimeException(exception);
        }

        this.product = product;
        productCardNameLabel.setText(product.getName());
        productCardPrizeLabel.setText(String.format("%.2f", product.getPrice()) + " " + product.getUnit());
        productCardImageView.setImage(model.getImage(product, kImageWidth, kImageWidth*kImageRatio));
        // if favorite....
        if (iMatDataHandler.isFavorite(product)) {
            productCardFavoriteImageView.setImage(new Image(Objects.requireNonNull(getClass().getClassLoader().getResourceAsStream("iMat/resources/heartFilled.png"))));
        } //annars ej ifyllt hjärta
    }

    @FXML
    private void handleAddAction(ActionEvent event) {
        model.addToShoppingCart(product);
    }
}

