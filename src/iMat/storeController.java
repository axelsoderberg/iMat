package iMat;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.layout.FlowPane;
import se.chalmers.cse.dat216.project.ProductCategory;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

public class storeController implements Initializable {

    @FXML private FlowPane categoriesFlowPane;
    //@FXML private ScrollPane categoriesScrollPane;


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        placeCategories();
    }

    public void placeCategories() {
        categoriesFlowPane.getChildren().clear();
        List<String> categoriesList = iMatController.getCategories();
        for (String C : categoriesList) {
            categoriesFlowPane.getChildren().add(categoryCard(C));
        }
    }

    @FXML
    public void updateSubcategories(String category) {
        categoriesFlowPane.getChildren().clear();
        List<ProductCategory> subCategoriesList = iMatController.getSubCategories(category);
        for (ProductCategory C : subCategoriesList) {
            categoriesFlowPane.getChildren().add(subCategoryCard(C));
        }
    }

    public void addSubcatToView(ProductCategory subcat) {

    }

    public subcategories subCategoryCard(ProductCategory pc) {
        return new subcategories(pc);
    }

    public categories categoryCard(String c) {
        return new categories(c);
    }


    public void viewCategory(ProductCategory pc) {
    }


}
