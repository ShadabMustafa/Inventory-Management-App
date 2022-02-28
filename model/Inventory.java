package model;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import javax.crypto.spec.PSource;

/**
 * Models an inventory of Parts and Products.
 *
 * The class provides persistent data for the application.
 *  Code is ordered in a vertical fashion to match the UML.
 *
 * @author Shadab Mustafa
 */
public class Inventory {

    /**
     * An ID for a part. Variable used for distinct part IDs.
     */
    private static int partId = 0;

    /**
     * An ID for a product. Variable used for distinct product IDs.
     */
    private static int productId = 0;

    /**
     * A list of all parts in inventory.
     */
    private static ObservableList<Part> allParts = FXCollections.observableArrayList();

    /**
     * A list of all products in inventory.
     */
    private static ObservableList<Product> allProducts = FXCollections.observableArrayList();


    /**
     * Adds a part to the inventory.
     *
     * @param newPart The part object to add.
     */
    public static void addPart(Part newPart) {
        allParts.add(newPart);
    }

    /**
     * Adds a product to the inventory.
     *
     * @param newProduct The product object to add.
     */
    public static void addProduct(Product newProduct) {
        allProducts.add(newProduct);
    }

    /**
     * Creates a new part ID.
     *
     * @return A distinct part ID.
     */
    public static int getNewPartId() {
        return ++partId;
    }

    /**
     * Creates a new product ID.
     *
     * @return A distinct product ID.
     */
    public static int getNewProductId() {
        return ++productId;
    }

    /**
     * Searches the list of parts by ID.
     *
     * @param partId The part ID.
     * @return The part object if found, null if not found.
     */
    public static Part lookUpPart(int partId) {
        if (!allParts.isEmpty()) {

            int i = 0;
            while (i < allParts.size()){

                if (allParts.get(i).getId() == partId) {
                    return allParts.get(i);
                }
                i++;
            }
        }
        return null;
    }

    /**
     * Searches the list of products by ID.
     *
     * @param productId The product ID.
     * @return The product object if found, null if not found.
     */
    public static Product lookUpProduct(int productId) {
        if (!allProducts.isEmpty()) {

            int i = 0;
            while (i < allProducts.size()){

                if (allProducts.get(i).getId() == productId) {
                    return allProducts.get(i);
                }
                i++;
            }
        }
        return null;
    }

    /**
     * Searches the list of parts by name.
     *
     * @param partName The part name.
     * @return A list of parts found.
     */
    public static ObservableList<Part> lookUpPart(String partName) {
        if (!allParts.isEmpty()) {
            ObservableList<Part> partsFound = FXCollections.observableArrayList();

            for (Part part : allParts) {
                if (part.getName().equals(partName)) {
                    partsFound.add(part);
                }
            }
            return partsFound;
        }
        return null;
    }

    /**
     * Searches the list of products by name.
     *
     * @param productName The product name.
     * @return A list of products found.
     */
    public static ObservableList<Product> lookupProduct(String productName) {
        ObservableList<Product> productsFound = FXCollections.observableArrayList();

        if (!allProducts.isEmpty()) {
            for (Product product : allProducts) {
                if (product.getName().equals(productName)) {
                    productsFound.add(product);
                }
            }
            return productsFound;
        }
        return null;
    }

    /**
     * Replaces a part in the list of parts.
     *
     * @param index Index of the part to be replaced.
     * @param selectedPart The part used for replacement.
     */
    public static void updatePart (int index, Part selectedPart) {

        allParts.set(index, selectedPart);
    }

    /**
     * Replaces a product in the list of products.
     *
     * @param index Index of the product to be replaced.
     * @param selectedProduct The product used for replacement.
     */
    public static void updateProduct (int index, Product selectedProduct) {

        allProducts.set(index, selectedProduct);
    }

    /**
     * Removes a part from the list of parts.
     *
     * @param selectedPart The part to be removed.
     * @return A boolean indicating status of part removal.
     */
    public static boolean deletePart(Part selectedPart) {

       if (allParts.contains(selectedPart)) {
            allParts.remove(selectedPart);
            return true;
       }
       else {
           return false;
       }
    }

    /**
     * Removes a product from the list of parts.
     *
     * @param selectedProduct The product to be removed.
     * @return A boolean indicating status of product removal.
     */
    public static boolean deleteProduct(Product selectedProduct) {

        if (allProducts.contains(selectedProduct)) {
            allProducts.remove(selectedProduct);
            return true;
        }
        else {
            return false;
        }
    }

    /**
     * Gets a list of all parts in inventory.
     *
     * @return A list of part objects.
     */
    public static ObservableList<Part> getAllParts() {
        return allParts;
    }

    /**
     * Gets a list of all products in inventory.
     *
     * @return A list of product objects.
     */
    public static ObservableList<Product> getAllProducts() {
        return allProducts;
    }

}
