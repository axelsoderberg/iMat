package iMat;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ToggleGroup;
import javafx.scene.image.ImageView;
import javafx.scene.layout.FlowPane;
import se.chalmers.cse.dat216.project.Product;
import se.chalmers.cse.dat216.project.ProductCategory;

import java.net.URL;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.ResourceBundle;

public class storeController implements Initializable {

    @FXML private FlowPane productsFlowPaneStore;
    @FXML private FlowPane categoriesFlowPane;
    @FXML private ImageView backArrow;
    @FXML private final ToggleGroup categoriesGroup = new ToggleGroup();

    private Map<String, productCard> productCardMap;
    private final Model model = Model.getInstance();

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        placeCategories();
        updateProductList(model.getProducts());
        // varukorg
    }

    private void updateProductList(List<Product> products) {

        productsFlowPaneStore.getChildren().clear();

        for (Product product : products) {
            System.out.println(product.getName());
            productsFlowPaneStore.getChildren().add(new productCard(product));
        }


    }

    public void placeCategories() {
        backArrow.toBack();
        categoriesFlowPane.getChildren().clear();
        List<String> categoriesList = iMatController.getCategories();
        for (String C : categoriesList) {
            categoriesFlowPane.getChildren().add(categoryCard(C));
        }
    }

    @FXML
    public void updateSubcategories(String category) {
        backArrow.toFront();
        categoriesFlowPane.getChildren().clear();
        List<ProductCategory> subCategoriesList = iMatController.getSubCategories(category);
        for (ProductCategory C : subCategoriesList) {
            categoriesFlowPane.getChildren().add(subCategoryCard(C));
        }
    }

    private void productListInit() {
        productCardMap = new HashMap<String, productCard>();
        for (Product products : model.getProducts()) {
            productCard recipeListItem = new productCard(products);
            productCardMap.put(products.getName(), recipeListItem);
        }
    }

    public void addSubcatToView(ProductCategory subcat) {

    }

    public subcategories subCategoryCard(ProductCategory pc) {
        return new subcategories(pc);
    }

    public categories categoryCard(String c) {
        return new categories(c, this);
    }


    public void viewCategory(ProductCategory pc) {
    }

    @FXML
    private void backArrowClicked() {
        placeCategories();
    }

}
