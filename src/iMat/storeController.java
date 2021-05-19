package iMat;

import javafx.fxml.FXML;
import javafx.scene.layout.FlowPane;
import se.chalmers.cse.dat216.project.Product;

import java.util.List;

public class storeController {

    @FXML private static FlowPane productsFlowPaneStore;

    private static final Model model = Model.getInstance();

    static void updateStore(List<Product> products) {
        updateProductList(products);
    }

    static void updateProductList(List<Product> products) {

        productsFlowPaneStore.getChildren().clear();

        for (Product product : products) {
           productsFlowPaneStore.getChildren().add(new productCard(product));
        }

    }
}
