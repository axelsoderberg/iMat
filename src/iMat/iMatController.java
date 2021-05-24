package iMat;

import java.io.IOException;
import java.net.URL;
import java.util.*;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.layout.AnchorPane;
import se.chalmers.cse.dat216.project.ProductCategory;


/**
 *
 */
public class iMatController extends AnchorPane/*, ShoppingCartListener*/ {

    // FXML items

    @FXML private AnchorPane iMatPane;

    // Other variables
    private final Model model = Model.getInstance();

    private static final List<AnchorPane> panes = new ArrayList<>(); // Här har vi alla fxml-sidor

    static List<String> categoriesList = Arrays.asList("Frukt", "Grönsaker", "Kött & Fisk", "Mejeri", "Basvaror", "Godis & Läsk");

    static List<ProductCategory> fruit = Arrays.asList(ProductCategory.FRUIT, ProductCategory.CITRUS_FRUIT,
            ProductCategory.EXOTIC_FRUIT, ProductCategory.BERRY, ProductCategory.VEGETABLE_FRUIT, ProductCategory.MELONS);
    static List<ProductCategory> greens = Arrays.asList(ProductCategory.POD, ProductCategory.CABBAGE, ProductCategory.HERB,
            ProductCategory.ROOT_VEGETABLE);
    static List<ProductCategory> meat = Arrays.asList(ProductCategory.FISH, ProductCategory.MEAT);
    static List<ProductCategory> dairy = Arrays.asList(ProductCategory.DAIRIES);
    static List<ProductCategory> base = Arrays.asList(ProductCategory.BREAD, ProductCategory.FLOUR_SUGAR_SALT,
            ProductCategory.POTATO_RICE, ProductCategory.PASTA, ProductCategory.NUTS_AND_SEEDS);
    static List<ProductCategory> sweets = Arrays.asList(ProductCategory.HOT_DRINKS, ProductCategory.COLD_DRINKS,
            ProductCategory.SWEET);

    public iMatController() {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("iMat.fxml"));
        fxmlLoader.setRoot(this);
        fxmlLoader.setController(this);

        try {
            fxmlLoader.load();
        } catch (IOException exception) {
            throw new RuntimeException(exception);
        }

        checkoutController checkoutCtrl = new checkoutController();
        storeController storeCtrl = new storeController();
        mypageController mypageCtrl = new mypageController();
        shoppinglistsController shoppinglistsCtrl = new shoppinglistsController();
        helpController helpCtrl = new helpController();
        headerController headerCtrl = new headerController(checkoutCtrl, storeCtrl, mypageCtrl, shoppinglistsCtrl, helpCtrl);
        StartController startCtrl = new StartController(storeCtrl, helpCtrl);

        panes.add(startCtrl);
        panes.add(storeCtrl);
        panes.add(helpCtrl);
        panes.add(mypageCtrl);
        panes.add(shoppinglistsCtrl);
        panes.add(headerCtrl);
        panes.add(checkoutCtrl);

        // Alla AnchorPanes från andra fxml-filer hamnar "under" iMatPane
        iMatPane.getChildren().clear(); // Så först, se till att den är tom
        iMatPane.getChildren().addAll(panes); // Lägg till alla

        startCtrl.toFront(); // Sätt Start att vara längst fram
    }

    public static void setPage(String page) {
        panes.get(getIndexOfPage(page)).toFront();
        AnchorPane.setBottomAnchor(panes.get(getIndexOfPage(page)), 0.0);
        if (!page.equals("Start"))
            panes.get(getIndexOfPage("header")).toFront();
    }

    // Inte jättesnygg lösning, men använd denna för att få fram den önskade sidan
    private static int getIndexOfPage(String fileName) {
        return switch (fileName) {
            case "Start" -> 0;
            case "store" -> 1;
            case "help" -> 2;
            case "mypage" -> 3;
            case "header" -> 4;
            case "shoppinglists" -> 5;
            case "checkouts" -> 6;
            default -> -1;
        };
    }

    public static List<ProductCategory> getSubCategories(String category) {
        return switch (category) {
            case "Frukt" -> fruit;
            case "Grönsaker" -> greens;
            case "Kött & Fisk" -> meat;
            case "Mejeri" -> dairy;
            case "Basvaror" -> base;
            case "Godis & Läsk" -> sweets;
            default -> null;
        };
    }

    public static List<String> getCategories() {
        return categoriesList;
    }

}

