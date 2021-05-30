package iMat;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Group;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.FlowPane;
import javafx.scene.text.Text;
import se.chalmers.cse.dat216.project.Product;
import se.chalmers.cse.dat216.project.ShoppingItem;

import java.io.IOException;
import java.util.*;

public class shoppinglists extends AnchorPane{

    @FXML private AnchorPane createView;
    @FXML AnchorPane listView;
    @FXML
    Text listViewName;
    @FXML FlowPane listViewFlowPane;
    @FXML private TextField createShoppingListName;
    @FXML private FlowPane shoppingListListPane;
    @FXML private AnchorPane abortPane;
    @FXML private FlowPane createListList;
    @FXML private Button createButton;
    @FXML private Button addButton;
    @FXML private AnchorPane searchPane;
    @FXML private TextField searchField;
    @FXML private FlowPane searchFlow;
    @FXML private Group searchBox;
    @FXML private Button addProductsButton;
    @FXML private Label listAmountText;
    @FXML private Label listSumText;
    @FXML private Label createAmountText;
    @FXML private Label createNumberOfItems;

    private boolean close = false;
    private boolean save = false;
    private boolean addedProduct = false;
    int numberOfItems = 0;
    double totalPrice = 0;
    private List<oneShoppingList> shoppingListList = new ArrayList<>();
    private Model model = Model.getInstance();
    private int listNumber;
    private oneShoppingList listViewShoppingItems;

    @FXML
    ImageView closeButton;

    public shoppinglists() {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("shoppinglists.fxml"));
        fxmlLoader.setRoot(this);
        fxmlLoader.setController(this);

        try {
            fxmlLoader.load();
        } catch (IOException exception) {
            throw new RuntimeException(exception);
        }
    }

    void updateShoppingListList(){
        shoppingListListPane.getChildren().clear();
        for (oneShoppingList oneShoppingList : shoppingListList){
            shoppingListListPane.getChildren().add(oneShoppingList);
        }
    }


    void openShoppingList(oneShoppingList listItem){
        listViewName.setText(listItem.getListName());
        listNumber = shoppingListList.indexOf(listItem);
        populateListFlow();
        listView.toFront();
        listViewShoppingItems = new oneShoppingList(this);
        for (ShoppingItem shoppingItem : listItem.getProductList()) {
            listViewShoppingItems.getProductList().add(new ShoppingItem(shoppingItem.getProduct(), shoppingItem.getAmount()));
        }
    }

    @FXML private void closeShoppingList(){
        listView.toBack();
        listViewFlowPane.getChildren().clear();
        listViewShoppingItems = null;
    }

    @FXML private void buyShoppingList(){
        for (ShoppingItem shoppingItem : listViewShoppingItems.getProductList()) {
            model.getShoppingCart().addItem(shoppingItem);
        }
        closeShoppingList();
    }

    void editShoppingList(oneShoppingList listItem){
        createView.toFront();
        listNumber = shoppingListList.indexOf(listItem);
        updateCreateFlow();
        createShoppingListName.setText(listItem.getListName());
    }

    public void updateCreateFlow(){
        createListList.getChildren().clear();
        populateCreateFlow();
        createListList.getChildren().add(addButton);
    }

    void updateListFlow(){
        listViewFlowPane.getChildren().clear();
        populateListFlow();
    }

    @FXML private void createShoppingList(){
        createButton.setText("Skapa lista");
        oneShoppingList listItem = new oneShoppingList(this);
        shoppingListList.add(listItem);
        editShoppingList(listItem);
    }

    @FXML private void completeCreate(){
        if(!createShoppingListName.getText().equals("")) {
            shoppingListList.get(listNumber).setListName(createShoppingListName.getText());
        }
        else {
            shoppingListList.get(listNumber).setListName("Inköpslista " + (listNumber + 1));
        }
        updateShoppingListList();
        if(!shoppingListList.contains(shoppingListList.get(listNumber))) {
            shoppingListList.add(shoppingListList.get(listNumber));
        }
        closeCreateView();
        createButton.setText("Klar");
        createListList.getChildren().clear();
    }

    @FXML public void createListFromCheckout(List<ShoppingItem> items) {
        oneShoppingList listItem = new oneShoppingList(this);
        Date date = new Date();
        listItem.setListName(date.toString());
        listItem.addProducts(items);

        shoppingListList.add(listItem);
        updateShoppingListList();
    }

    @FXML private void abortCreate(){
        abortPane.toFront();
    }

    @FXML private void addProduct(){
        searchPane.toFront();
        searchBox.toFront();
        addProductsButton.toFront();
        populateSearchFlow();
    }

    @FXML private void doneAdding(){
        searchPane.toBack();
        searchBox.toBack();
        addProductsButton.toBack();
        searchField.clear();
        searchFlow.getChildren().clear();
        updateCreateFlow();
    }

    private void populateCreateFlow(){
        numberOfItems = 0;
        totalPrice = 0;
        for (ShoppingItem shoppingItems : shoppingListList.get(listNumber).getProductList()){
            createListList.getChildren().add(shoppingListList.get(listNumber).getShoppingListProductMap().get(shoppingItems.getProduct()).setAddView());
            totalPrice += shoppingItems.getTotal();
            numberOfItems += shoppingItems.getAmount();
        }
        createAmountText.setText("Summa: " + String.format("%.2f", totalPrice) + " kr");
        createNumberOfItems.setText("" + numberOfItems + " st");
    }

    private void populateListFlow(){
        numberOfItems = 0;
        totalPrice = 0;
        for (ShoppingItem shoppingItems : shoppingListList.get(listNumber).getProductList()){
            listViewFlowPane.getChildren().add(shoppingListList.get(listNumber).getShoppingListProductMap().get(shoppingItems.getProduct()).setListView());
            totalPrice += shoppingItems.getTotal();
            numberOfItems += shoppingItems.getAmount();
        }
        listAmountText.setText("" + numberOfItems + " st");
        listSumText.setText(String.format("%.2f", totalPrice) + " kr");
    }

    private void populateSearchFlow(){
        for (Product products : model.getProducts()){
            searchFlow.getChildren().add(shoppingListList.get(listNumber).getShoppingListProductMap().get(products).setAddView());
        }
    }

    @FXML private void handleSearchAction(){
        List<Product> matches = model.findProducts(searchField.getText());
        searchFlow.getChildren().clear();
        for (Product products : matches){
            searchFlow.getChildren().add(shoppingListList.get(listNumber).getShoppingListProductMap().get(products));
        }
    }

    private Product getProduct(){
        return null;
    }

    @FXML private void closeCreateView(){
        createView.toBack();
        abortPane.toBack();
        searchPane.toBack();
        searchBox.toBack();
        addProductsButton.toBack();
    }

    public void setListNumber(int setter){
        listNumber = setter;
    }

    void setShoppingItem(ShoppingItem item){
        if(listViewShoppingItems.getProductList().contains(item)) {
            listViewShoppingItems.getProductList().remove(item);
        }
        else{
            listViewShoppingItems.getProductList().add(item);
        }
    }

    @FXML
    void hoverCloseShoppinglist() {
        closeButton.setImage(new Image(getClass().getClassLoader().getResourceAsStream(
                "resources/midgreenClose.png")));
    }

    @FXML
    void hoverDoneCloseShoppinglist() {
        closeButton.setImage(new Image(getClass().getClassLoader().getResourceAsStream(
                "resources/greenclose.png")));
    }

    @FXML
    void pressedCloseShoppinglist() {
        closeButton.setImage(new Image(getClass().getClassLoader().getResourceAsStream(
                "resources/lightgreenClose.png")));
    }



}
