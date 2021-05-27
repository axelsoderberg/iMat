/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package iMat;

/**
 *
 * @author oloft
 */
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import javafx.scene.image.Image;
import se.chalmers.cse.dat216.project.*;


/**
 * Wrapper around the IMatDataHandler. The idea is that it might be useful to
 * add an extra layer that can provide special features
 *
 */
public class Model {

    private static Model instance = null;
    private IMatDataHandler iMatDataHandler;

    private final ArrayList<String> availableCardTypes = new ArrayList<String>(Arrays.asList("MasterCard", "Visa"));
    private final ArrayList<String> months = new ArrayList<String>(Arrays.asList("1", "2","3", "4", "5", "6"));
    private final ArrayList<String> years = new ArrayList<String>(Arrays.asList("19", "20", "21", "22", "23", "24", "25"));

    public List<Product> fruits = new ArrayList();
    public List<Product> greens = new ArrayList();
    public List<Product> meat = new ArrayList();
    public List<Product> dairy = new ArrayList();
    public List<Product> base = new ArrayList();
    public List<Product> sweets = new ArrayList();

    /**
     * Constructor that should never be called, use getInstance() instead.
     */
    protected Model() {
        // Exists only to defeat instantiation.
    }

    /**
     * Returns the single instance of the Model class.
     */
    public static Model getInstance() {
        if (instance == null) {
            instance = new Model();
            instance.init();
        }
        return instance;
    }

    private void init() {

        iMatDataHandler = IMatDataHandler.getInstance();
        for (ProductCategory pc : iMatController.fruit)
            fruits.addAll(getProducts(pc));
        for (ProductCategory pc : iMatController.greens)
            greens.addAll(getProducts(pc));
        for (ProductCategory pc : iMatController.meat)
            meat.addAll(getProducts(pc));
        for (ProductCategory pc : iMatController.dairy)
            dairy.addAll(getProducts(pc));
        for (ProductCategory pc : iMatController.base)
            base.addAll(getProducts(pc));
        for (ProductCategory pc : iMatController.sweets)
            sweets.addAll(getProducts(pc));
    }

    public List<Product> getProducts() {
        return iMatDataHandler.getProducts();
    }

    public List<Product> getProducts(ProductCategory pc) {return iMatDataHandler.getProducts(pc); }

    public Product getProduct(int idNbr) {
        return iMatDataHandler.getProduct(idNbr);
    }

    public List<Product> findProducts(java.lang.String s) {
        return iMatDataHandler.findProducts(s);
    }

    public Image getImage(Product p) {
        return iMatDataHandler.getFXImage(p);
    }

    public Image getImage(Product p, double width, double height) {
        return iMatDataHandler.getFXImage(p, width, height);
    }

    public void addToShoppingCart(ShoppingItem s) {
        ShoppingCart shoppingCart = iMatDataHandler.getShoppingCart();

        //lägg till shoppingitem om den inte redan finns, isf uppdateras det redan i backend
        if (!shoppingCart.getItems().contains(s)) {
            shoppingCart.addItem(s);
        }

        //Model.getInstance().getShoppingCart().addItem(item);
        //shoppingCart.addProduct(p);
    }

    public List<String> getCardTypes() {
        return availableCardTypes;
    }

    public List<String> getMonths() {
        return months;
    }

    public List<String> getYears() {
        return years;
    }

    public CreditCard getCreditCard() {
        return iMatDataHandler.getCreditCard();
    }

    public Customer getCustomer() {
        return iMatDataHandler.getCustomer();
    }

    public ShoppingCart getShoppingCart() {
        return iMatDataHandler.getShoppingCart();
    }

    public void clearShoppingCart() {

        iMatDataHandler.getShoppingCart().clear();

    }

    public void placeOrder() {

        iMatDataHandler.placeOrder();

    }


    public int getNumberOfOrders() {

        return iMatDataHandler.getOrders().size();

    }

    public void shutDown() {
        iMatDataHandler.shutDown();
    }

    public IMatDataHandler getImatDataHandler(){
        return iMatDataHandler;
    }

    public boolean isFavorite(Product product) {
        return iMatDataHandler.isFavorite(product);
    }

    public void addFavorite(Product product) {
        iMatDataHandler.addFavorite(product);
    }

    public void removeProduct(Product product) {
        iMatDataHandler.removeProduct(product);
    }

    public void removeFavorite(Product product) {
        iMatDataHandler.removeFavorite(product);
    }

    public List<Product> getList(String c) {
        return switch (c) {
            case "Godis & Läsk" -> sweets;
            case "Frukt" -> fruits;
            case "Mejeri" -> dairy;
            case "Kött & Fisk" -> meat;
            case "Basvaror" -> base;
            case "Grönsaker" -> greens;
            default -> null;
        };
    }
}
