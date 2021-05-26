package iMat;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Text;

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

    public shoppingListItem(){
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("shoppingListItem.fxml"));
        fxmlLoader.setRoot(this);
        fxmlLoader.setController(this);

        try {
            fxmlLoader.load();
        } catch (IOException exception) {
            throw new RuntimeException(exception);
        }
/*
        this.item = item;
        shoppingListItemNameText.setText(item.getProduct().getName());
        shoppingListItemTotalItemPrizeText.setText(String.format("%.2f", item.getProduct().getPrice()) + " " + item.getProduct().getUnit());
        shoppingListItemProductImage.setImage(model.getImage(item.getProduct(), kImageWidth, kImageWidth*kImageRatio));
        shoppingListItemAmountLabel.setText((int) item.getAmount() + " st");*/
    }
}
