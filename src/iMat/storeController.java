package iMat;

import javafx.beans.property.BooleanProperty;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.ToggleGroup;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.FlowPane;
import se.chalmers.cse.dat216.project.Product;
import se.chalmers.cse.dat216.project.ProductCategory;
import se.chalmers.cse.dat216.project.ShoppingCart;
import se.chalmers.cse.dat216.project.ShoppingItem;

import java.io.IOException;
import java.awt.*;
import java.net.URL;
import java.util.*;
import java.util.List;

public class storeController extends AnchorPane {

    @FXML private FlowPane productsFlowPaneStore;
    @FXML private FlowPane categoriesFlowPane;
    @FXML private ImageView backArrow;
    @FXML private AnchorPane detailPane;
    @FXML private AnchorPane storePane;
    @FXML private final ToggleGroup categoriesGroup = new ToggleGroup();
    @FXML FlowPane shoppingcartFlowpaneStore;
    @FXML Label storeShoppingcartTotalPrize;

    private Map<Product, productCard> productCardMap;
    private List<ProductCategory> selectedCategories = new ArrayList();
    private final Model model = Model.getInstance();

    public storeController() {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("store.fxml"));
        fxmlLoader.setRoot(this);
        fxmlLoader.setController(this);

        try {
            fxmlLoader.load();
        } catch (IOException exception) {
            throw new RuntimeException(exception);
        }

        productListInit();
        placeCategories();
        updateProductList(model.getProducts());
        updateShoppingcartview(model.getShoppingCart().getItems());
    }

    void updateProductCard(Product product) {
        productCardMap.get(product).update();
    }

    void updateProductList(List<Product> products) {

        productsFlowPaneStore.getChildren().clear();

        for (Product product : products) {
            productsFlowPaneStore.getChildren().add(productCardMap.get(product));
        }

    }

    private void productListInit() {
        productCardMap = new HashMap<Product, productCard>();
        for (ShoppingItem item : model.getShoppingCart().getItems()) {
            productCard productCard = new productCard(item, this);
            productCardMap.put(item.getProduct(), productCard);
        }

        for (Product product : model.getProducts()) {
            if (!productCardMap.containsKey(product)) {
                productCard productCard = new productCard(new ShoppingItem(product, 0), this);
                productCardMap.put(product, productCard);
            }
        }
    }

    public void sortAlfabethic() {

    }

    void updateShoppingcartview(List<ShoppingItem> items) {

        shoppingcartFlowpaneStore.getChildren().clear();

        for (ShoppingItem item : items) {
            shoppingcartFlowpaneStore.getChildren().add(new ShoppingcartItem(item));
        }

        storeShoppingcartTotalPrize.setText("Summa: " + String.format("%.2f",model.getShoppingCart().getTotal()) + " kr");

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

    public subcategories subCategoryCard(ProductCategory pc) {
        return new subcategories(pc, this);
    }

    public categories categoryCard(String c) {
        return new categories(c, this);
    }

    @FXML
    void backArrowClicked() {
        clearSubcategories();
        placeCategories();
        updateProductList(model.getProducts());
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
        List<Product> lp = new ArrayList();
        for (ProductCategory productCat : selectedCategories) {
            lp.addAll(model.getProducts(productCat));
        }
        updateProductList(lp);
    }

    public void removeSubcategory(ProductCategory pc) {
        selectedCategories.remove(pc);
        List<Product> lp = new ArrayList();
        for (ProductCategory productCat : selectedCategories) {
            lp.addAll(model.getProducts(productCat));
        }
        updateProductList(lp);
    }

    public void clearSubcategories() {
        selectedCategories.clear();
    }

    public void openDetailView(Product product) {
        detailPane.getChildren().clear();
        detailView productDetailCard = new detailView(product, this);
        detailPane.getChildren().add(productDetailCard);
        detailPane.toFront();
        storePane.toFront();
    }

    public void closeDetailView() {
        detailPane.getChildren().clear();
        detailPane.toBack();
        storePane.toBack();
        iMatController.getHeader().openStoreView();
    }

}
