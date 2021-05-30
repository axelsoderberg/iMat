package iMat;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;
import se.chalmers.cse.dat216.project.Order;
import se.chalmers.cse.dat216.project.ShoppingItem;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class OldOrder extends AnchorPane {

    @FXML
    Button orderButton;
    List<ShoppingItem> order = new ArrayList<>();
    String date;
    mypageController parentController;
    Model model = Model.getInstance();

    public OldOrder(Order order, mypageController parentController){
        this.order = (order.getItems());
        this.date = order.getDate().toString();
        this.parentController = parentController;

        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("OldOrder.fxml"));
        fxmlLoader.setRoot(this);
        fxmlLoader.setController(this);

        try {
            fxmlLoader.load();
        } catch (IOException exception) {
            throw new RuntimeException(exception);
        }

        orderButton.setText(this.date);
    }

    @FXML void openOrderView(){
        parentController.openOrderView(this);
    }




}
