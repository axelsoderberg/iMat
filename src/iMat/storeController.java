package iMat;

import javafx.beans.property.BooleanProperty;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.Label;
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
    @FXML public Label category;
    @FXML public Label categoriesLabel;
    @FXML public ComboBox sortCombo;
    @FXML FlowPane shoppingcartFlowpaneStore;
    @FXML Label storeShoppingcartTotalPrize;

    Map<Product, productCard> productCardMap;
    public List<ProductCategory> selectedCategories = new ArrayList();
    public List<subcategories> currentSubCategories = new ArrayList();
    private final Model model = Model.getInstance();
    public String currentCategory = "";

    checkoutController checkoutCtrl;

    public storeController(checkoutController checkoutCtrl) {
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

        sortCombo.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<String>() {

            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
                model.sortBy = (String) sortCombo.getValue();
                if (selectedCategories.isEmpty() && currentCategory.equals(""))
                    updateProductList(model.getProducts());
                else if (selectedCategories.isEmpty()) {
                    updateProductList(model.getList(currentCategory));
                } else {
                    List<Product> lp = new ArrayList();
                    for (ProductCategory productCat : selectedCategories) {
                        lp.addAll(model.getProducts(productCat));
                    }
                    model.sort(lp, model.sortBy);
                    updateProductList(lp);
                }
            }
        });

        sortCombo.getItems().removeAll(sortCombo.getItems());
        sortCombo.getItems().addAll("A - Ö", "Pris fallande", "Pris stigande");

        this.checkoutCtrl = checkoutCtrl;
    }

    void updateProductCard(Product product, Boolean clear) {
        productCardMap.get(product).update(clear);
    }

    void updateProductList(List<Product> products) {

        productsFlowPaneStore.getChildren().clear();

        for (Product product : products) {
            productsFlowPaneStore.getChildren().add(productCardMap.get(product));
        }

    }

    /*
    void updateProductList(List<Product> products) {

        productsFlowPaneStore.getChildren().clear();

        for (Product product : products) {
            productsFlowPaneStore.getChildren().add(productCardMap.get(product));
        }

    }*/

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

    public void sortAlphabethic() {

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
        cleaCurrentSubCategories();
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
        subcategories sub = new subcategories(pc, this);
        currentSubCategories.add(sub);
        return sub;
    }

    public void cleaCurrentSubCategories() {
        currentSubCategories.clear();
    }

    @FXML
    public void removeSelectionFromAllSubCats() {
        for (subcategories subcat : currentSubCategories) {
            subcat.checkToBack();
        }
    }

    @FXML
    public void addSelectionToAllSubCats() {
        for (subcategories subcat : currentSubCategories) {
            subcat.checkToFront();
        }
    }

    public categories categoryCard(String c) {
        return new categories(c, this);
    }

    @FXML
    void backArrowClicked() {
        clearSubcategories();
        placeCategories();
        updateProductList(model.getProducts());
        category.setText("Produkter");
        categoriesLabel.setText("Kategorier");
        currentCategory = "";
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
            case DAIRIES -> "Mejeriprodukter";
            case POTATO_RICE -> "Potatis & Ris";
            case EXOTIC_FRUIT -> "Exotisk";
            case FLOUR_SUGAR_SALT -> "Skafferi";
            case NUTS_AND_SEEDS -> "Nötter & Frön";
            case ROOT_VEGETABLE -> "Rotfrukter";
            case VEGETABLE_FRUIT -> "Grönsaksfrukter";
        };
    }

    public String getParentCategory(ProductCategory pc) {
        return switch (pc) {
            case POD, HERB, ROOT_VEGETABLE, CABBAGE, VEGETABLE_FRUIT -> "Grönsaker";
            case FISH, MEAT -> "Kött & Fisk";
            case BERRY, CITRUS_FRUIT, MELONS, FRUIT, EXOTIC_FRUIT -> "Frukt";
            case BREAD, PASTA, FLOUR_SUGAR_SALT, POTATO_RICE, NUTS_AND_SEEDS -> "Basvaror";
            case SWEET, COLD_DRINKS, HOT_DRINKS -> "Godis & Läsk";
            case DAIRIES -> "Mejeri";
        };
    }

    public void addSubcategory(ProductCategory pc) {
        selectedCategories.add(pc);
        List<Product> lp = new ArrayList();
        for (ProductCategory productCat : selectedCategories) {
            lp.addAll(model.getProducts(productCat));
        }
        model.sort(lp, model.sortBy);
        updateProductList(lp);
        setCategoriesLabel();

    }

    public void removeSubcategory(ProductCategory pc) {
        selectedCategories.remove(pc);
        List<Product> lp = new ArrayList();
        for (ProductCategory productCat : selectedCategories) {
            lp.addAll(model.getProducts(productCat));
        }
        model.sort(lp, model.sortBy);
        updateProductList(lp);
        if (selectedCategories.isEmpty()) {
            addSelectionToAllSubCats();
            updateProductList(model.getList(getParentCategory(pc)));
            category.setText(getParentCategory(pc));
        } else
            setCategoriesLabel();
    }

    public void setCategoriesLabel() {
        StringBuilder labelText = new StringBuilder();
        for (ProductCategory productCat : selectedCategories) {
            labelText.append(subCatConverter(productCat)).append(", ");
        }
        labelText.delete(labelText.length() - 2, labelText.length());
        category.setText(labelText.toString());
    }

    public void clearSubcategories() {
        selectedCategories.clear();
    }

    public void openDetailView(ShoppingItem shoppingItem) {
        detailPane.getChildren().clear();
        detailView productDetailCard = new detailView(shoppingItem, this);
        detailPane.getChildren().add(productDetailCard);
        detailPane.toFront();
        storePane.toFront();
    }

    public void closeDetailView() {
        detailPane.getChildren().clear();
        detailPane.toBack();
    }

    public void viewFavorites() {
        List<Product> list = model.getProducts();
        productsFlowPaneStore.getChildren().clear();
        for (Product product : list) {
            if (model.isFavorite(product))
                productsFlowPaneStore.getChildren().add(productCardMap.get(product));
        }
    }

    public void viewFeatured() {
        List<Product> list = new ArrayList();
        list.add(model.getProduct(1));
        list.add(model.getProduct(20));
        list.add(model.getProduct(55));
        list.add(model.getProduct(65));
        productsFlowPaneStore.getChildren().clear();
        for (Product product : list) {
            productsFlowPaneStore.getChildren().add(productCardMap.get(product));
        }
    }

    @FXML
    void openCheckoutView() {
        checkoutCtrl.toFront();
        checkoutCtrl.updateShoppingcartList(model.getShoppingCart().getItems());
    }

}
