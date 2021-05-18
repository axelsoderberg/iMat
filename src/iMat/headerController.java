package iMat;

import javafx.fxml.FXML;


public class headerController {

    @FXML
    private void openHelpView() {
        iMatController.setPage("help");
    }

    @FXML
    private void openStoreView() {
        iMatController.setPage("store");
    }

    @FXML
    private void openCheckoutView() {
        iMatController.setPage("checkout");
    }

    @FXML
    private void openMypageView() {
        iMatController.setPage("mypage");
    }

    @FXML
    private void openShoppingListView() {
        iMatController.setPage("shoppinglists");
    }

}
