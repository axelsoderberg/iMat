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

    public String sortBy = "";

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
        List<Product> list = iMatDataHandler.getProducts();
        if (sortBy != "")
            sort(list, sortBy);
        return list;
    }

    public List<Product> getProducts(ProductCategory pc) {
        List<Product> list = iMatDataHandler.getProducts(pc);
        if (sortBy != "")
            sort(list, sortBy);
        return list;
    }

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
        switch (c) {
            case "Godis & Läsk" -> {
                sort(sweets, sortBy);
                return sweets;
            }
            case "Frukt" -> {
                sort(fruits, sortBy);
                return fruits;
            }
            case "Mejeri" -> {
                sort(dairy, sortBy);
                return dairy;
            }
            case "Kött & Fisk" -> {
                sort(meat, sortBy);
                return meat;
            }
            case "Basvaror" -> {
                sort(base, sortBy);
                return base;
            }
            case "Grönsaker" -> {
                sort(greens, sortBy);
                return greens;
            }
        }
        return null;
    }

    public void sort(List<Product> list, String sortBy) {
        switch (sortBy) {
            case "Pris stigande":
                quickSort(list, 0, list.size() - 1);
                break;
            case "Pris fallande":
                quickSort(list,0, list.size() - 1);
                for (int i = 0, j = list.size() - 1; i < j; i++) {
                    list.add(i, list.remove(j));
                }
                break;
            case "A - Ö":
                quickSortAlphabetic(list, 0, list.size() - 1);
                break;
        }
    }

    public static void quickSort(List<Product> list, int begin, int end) {
        if (end <= begin) return;
        int pivot = partition(list, begin, end);
        quickSort(list, begin, pivot-1);
        quickSort(list, pivot+1, end);
    }

    static int partition(List<Product> list, int begin, int end) {

        int counter = begin;
        for (int i = begin; i < end; i++) {
            if (list.get(i).getPrice() < list.get(end).getPrice()) {
                Product temp = list.get(counter);
                list.set(counter, list.get(i));
                list.set(i, temp);
                counter++;
            }
        }
        Product temp = list.get(end);
        list.set(end, list.get(counter));
        list.set(counter, temp);

        return counter;
    }

    public static void quickSortAlphabetic(List<Product> list, int begin, int end) {
        if (end <= begin) return;
        int pivot = partitionAlphabetic(list, begin, end);
        quickSortAlphabetic(list, begin, pivot-1);
        quickSortAlphabetic(list, pivot+1, end);
    }

    static int partitionAlphabetic(List<Product> list, int begin, int end) {

        int counter = begin;
        for (int i = begin; i < end; i++) {
            if (isBeforeInAlphabet(list.get(i), list.get(end - 1))) {
                Product temp = list.get(counter);
                list.set(counter, list.get(i));
                list.set(i, temp);
                counter++;
            }
        }
        Product temp = list.get(end);
        list.set(end, list.get(counter));
        list.set(counter, temp);

        return counter;
    }

    private static boolean isBeforeInAlphabet(Product p1, Product p2) {
        int i = 0;
        /*while (p1.getName().charAt(i) == p2.getName().charAt(i))
            i++;*/
        return p1.getName().charAt(0) - p2.getName().charAt(0) <= 0;
    }

}
