package iMat;

import javafx.fxml.FXML;
import javafx.scene.layout.FlowPane;
import se.chalmers.cse.dat216.project.ProductCategory;

import java.util.List;

public class storeController {

    @FXML private FlowPane categoriesFlowPane;
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
    }
}
