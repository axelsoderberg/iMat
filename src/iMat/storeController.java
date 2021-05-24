package iMat;

import javafx.beans.property.BooleanProperty;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ToggleGroup;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.FlowPane;
import se.chalmers.cse.dat216.project.Product;
import se.chalmers.cse.dat216.project.ProductCategory;

import java.io.IOException;
import java.awt.*;
import java.net.URL;
import java.util.*;
import java.util.List;

public class storeController extends AnchorPane {

    @FXML private FlowPane productsFlowPaneStore;
    @FXML private FlowPane categoriesFlowPane;
    @FXML private ImageView backArrow;
    @FXML private final ToggleGroup categoriesGroup = new ToggleGroup();

    private Map<String, productCard> productCardMap;
    private List<ProductCategory> selectedCategories = new ArrayList();
    private final Model model = Model.getInstance();

    public storeController() {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("iMat.fxml"));
        fxmlLoader.setRoot(this);
        fxmlLoader.setController(this);

        try {
            fxmlLoader.load();
        } catch (IOException exception) {
            throw new RuntimeException(exception);
        }

        placeCategories();
        updateProductList(model.getProducts());
        // varukorg
    }

    private void updateProductList(List<Product> products) {

        productsFlowPaneStore.getChildren().clear();

        for (Product product : products) {
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


    public subcategories subCategoryCard(ProductCategory pc) {
        return new subcategories(pc, this);
    }

    public categories categoryCard(String c) {
        return new categories(c, this);
    }

    @FXML
    private void backArrowClicked() {
        clearSubcategories();
        placeCategories();
    }

    public String subCatConverter(ProductCategory pc) {
        return switch (pc) {
            case POD -> "Baljväxter";
            case CABBAGE -> "Kål";
            case FISH -> "Fisk";
            case MEAT -> "Kött";
            case HERB -> "Ört";
            case BERRY -> "Bär";
            case BREAD -> "Bröd";
            case FRUIT -> "Frukt";
            case PASTA -> "Pasta";
            case SWEET -> "Godis";
            case HOT_DRINKS -> "Varm dryck";
            case COLD_DRINKS -> "Kall dryck";
            case MELONS -> "Melon";
            case CITRUS_FRUIT -> "Citrus";
            case DAIRIES -> "Mejeri";
            case POTATO_RICE -> "Potatis & Ris";
            case EXOTIC_FRUIT -> "Exotisk";
            case FLOUR_SUGAR_SALT -> "Skafferi";
            case NUTS_AND_SEEDS -> "Nötter & Frön";
            case ROOT_VEGETABLE -> "Rotfrukter";
            case VEGETABLE_FRUIT -> "Grönsaksfrukter";
        };
    }

    public void addSubcategory(ProductCategory pc) {
        selectedCategories.add(pc);
    }

    public void removeSubcategory(ProductCategory pc) {
        selectedCategories.remove(pc);
    }

    public void clearSubcategories() {
        selectedCategories.clear();
    }
}
