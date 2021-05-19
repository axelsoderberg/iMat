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
public class iMatController implements Initializable/*, ShoppingCartListener*/ {

    // FXML items

    @FXML private AnchorPane iMatPane;

    // Other variables
    // private final Model model = Model.getInstance();
    private static final List<AnchorPane> panes = new ArrayList<>(); // Här har vi alla fxml-sidor

    static List<String> categoriesList = Arrays.asList("Frukt", "Grönsaker", "Kött & Fisk", "Mejeri", "Basvaror", "Godis & Läsk");

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        try {
            panes.add(FXMLLoader.load(Objects.requireNonNull(getClass().getResource("Start.fxml"))));
            panes.add(FXMLLoader.load(Objects.requireNonNull(getClass().getResource("store.fxml"))));
            panes.add(FXMLLoader.load(Objects.requireNonNull(getClass().getResource("help.fxml"))));
            panes.add(FXMLLoader.load(Objects.requireNonNull(getClass().getResource("mypage.fxml"))));
            panes.add(FXMLLoader.load(Objects.requireNonNull(getClass().getResource("header.fxml"))));
            panes.add(FXMLLoader.load(Objects.requireNonNull(getClass().getResource("shoppinglists.fxml"))));
            panes.add(FXMLLoader.load(Objects.requireNonNull(getClass().getResource("checkouts.fxml"))));
        } catch (IOException e) {
            e.printStackTrace();
        }

        // Alla AnchorPanes från andra fxml-filer hamnar "under" iMatPane
        iMatPane.getChildren().clear(); // Så först, se till att den är tom
        iMatPane.getChildren().addAll(panes); // Lägg till alla

        panes.get(getIndexOfPage("Start")).toFront(); // Sätt Start att vara längst fram

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



    public static List<String> getCategories() {
        return categoriesList;
    }
}

