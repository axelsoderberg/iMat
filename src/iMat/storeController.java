package iMat;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.layout.FlowPane;
import se.chalmers.cse.dat216.project.Product;
import se.chalmers.cse.dat216.project.ProductCategory;

import java.awt.*;
import java.net.URL;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.ResourceBundle;

public class storeController implements Initializable {
    private final Model model = Model.getInstance();
    private Map<String, productCard> productListItemMap;
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        productListInit();
        updateProductList(productListItemMap);
        // kategorier
        // varukorg
    }


    @FXML private FlowPane productsFlowPaneStore;

    private void updateProductList(Map<String, productCard> products) {

        productsFlowPaneStore.getChildren().clear();

        for (Product product : model.getProducts()) {
            productsFlowPaneStore.getChildren().add(products.get(product.getName()));
        }

    }
    private void productListInit() {
        productListItemMap = new HashMap<String, productCard>();
        for (Product products : model.getProducts()) {
            productCard productCard = new productCard(products);
            productListItemMap.put(products.getName(), productCard);
        }
    }

    /*@FXML private FlowPane categoriesFlowPane;
    @FXML private FlowPane subcategoriesFlowPane;

    public void placeCategories() {
        categoriesFlowPane.getChildren().clear();
        List<String> categoriesList = iMatController.getCategories();
        for (String C : categoriesList) {
            categoriesFlowPane.getChildren().add(categoryCard(C));
        }
    }

    @FXML
    public void updateSubcategories(String category) {
        subcategoriesFlowPane.getChildren().clear();
        List<ProductCategory> subCategoriesList = iMatController.getSubCategories(category);
        for (ProductCategory C : subCategoriesList) {
            subcategoriesFlowPane.getChildren().add(subCategoryCard(C));
        }
    }

    public void addSubcatToView(ProductCategory subcat) {

    }

    public subcategories subCategoryCard(ProductCategory pc) {
        return new subcategories(pc,this);
    }

    public categories categoryCard(String c) {
        return new categories(c,this);
    }


    public void viewCategory(ProductCategory pc) {
    }*/
}
