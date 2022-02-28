package controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.fxml.Initializable;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyEvent;
import javafx.stage.Stage;
import model.Inventory;
import model.Part;
import model.Product;

import java.io.IOException;
import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;

import static controller.NotificationAlert.displayNotification;

/**
 * Controller class that provides code for the main screen of the application.
 *
 * RUNTIME ERROR
 * One runtime error happens if no product is selected when the user clicks the modify button.
 * This runtime error happens due to a null value being forwarded to the initialize method of the
 * ModifyProductController. An example of correcting/preventing the runtime error from happening
 * can be found in the productModify() method of this class.
 *
 * @author Shadab Mustafa
 */
public class MainScreenController implements Initializable {

    /**
     *  part object selected in the table view by the user.
     */
    private static Part chosenPartToModify;

    /**
     *  product object selected in the table view by the user.
     */
    private static Product chosenProductToModify;

    /**
     *  text field for the parts search.
     */
    @FXML
    private TextField partSearchText;

    /**
     * Table view for the parts.
     */
    @FXML
    private TableView<Part> partTableView;

    /**
     *  ID column for the parts table.
     */
    @FXML
    private TableColumn<Part, Integer> partIdColumn;

    /**
     *  part name column for the parts table.
     */
    @FXML
    private TableColumn<Part, String> partNameColumn;

    /**
     *  inventory column for the parts table.
     */
    @FXML
    private TableColumn<Part, Integer> partInventoryColumn;

    /**
     *  price column for parts table.
     */
    @FXML
    private TableColumn<Part, Double> partPriceColumn;

    /**
     * Table text field for the product search.
     */
    @FXML
    private TextField productSearchText;

    /**
     * Table view for the products.
     */
    @FXML
    private TableView<Product> productTableView;

    /**
     *  ID column for the product table.
     */
    @FXML
    private TableColumn<Product, Integer> productIdColumn;

    /**
     *  name column for the product table.
     */
    @FXML
    private TableColumn<Product, String> productNameColumn;

    /**
     *  inventory column for the product table.
     */
    @FXML
    private TableColumn<Product, Integer> productInventoryColumn;

    /**
     *  price column for the product table.
     */
    @FXML
    private TableColumn<Product, Double> productPriceColumn;

    /**
     * Gets the part object chosen by the user in the part table.
     *
     * @return A part object, null if no part chosen.
     */
    public static Part getChosenPartToModify() {
        return chosenPartToModify;
    }

    /**
     * Gets the product object chosen by the user in the product table.
     *
     * @return A product object, null if no product chosen.
     */
    public static Product getChosenProductToModify() {
        return chosenProductToModify;
    }

    /**
     * Exits program.
     *
     * @param event Exit button.
     */
    @FXML
    void exitButton(ActionEvent event) {

        System.exit(0);
    }

    /**
     * Starts controller and populates table views.
     *
     * @param location The location utilized to resolve relative paths for the root object, or null if the location is unknown.
     * @param resources The resources utilized to localize the root object, or null if the root object failed to be or was not localized.
     */
    @Override
    public void initialize(URL location, ResourceBundle resources) {

        //Populate parts table view
        partTableView.setItems(Inventory.getAllParts());
        partIdColumn.setCellValueFactory(new PropertyValueFactory<>("id"));
        partNameColumn.setCellValueFactory(new PropertyValueFactory<>("name"));
        partInventoryColumn.setCellValueFactory(new PropertyValueFactory<>("stock"));
        partPriceColumn.setCellValueFactory(new PropertyValueFactory<>("price"));

        //Populate products table view
        productTableView.setItems(Inventory.getAllProducts());
        productIdColumn.setCellValueFactory(new PropertyValueFactory<>("id"));
        productNameColumn.setCellValueFactory(new PropertyValueFactory<>("name"));
        productInventoryColumn.setCellValueFactory(new PropertyValueFactory<>("stock"));
        productPriceColumn.setCellValueFactory(new PropertyValueFactory<>("price"));
    }

    /**
     * Loads the AddProductController.
     *
     * @param event Add product button.
     * @throws IOException From FXMLLoader.
     */
    @FXML
    void productAdd(ActionEvent event) throws IOException {

        Parent parent = FXMLLoader.load(getClass().getResource("../view/AddProductScreen.fxml"));
        Scene scene = new Scene(parent);
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setScene(scene);
        stage.setResizable(false);
        stage.show();
    }

    /**
     * Loads the AddPartController.
     *
     * @param event Add button.
     * @throws IOException From FXMLLoader.
     */
    @FXML
    void partAdd(ActionEvent event) throws IOException {

        Parent root = FXMLLoader.load(getClass().getResource("../view/AddPartScreen.fxml"));
        Scene scene = new Scene(root);
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setScene(scene);
        stage.setResizable(false);
        stage.show();
    }

    /**
     * Deletes part chosen by the user in the part table.
     *
     * The method shows an error message if no part chosen selected and a confirmation
     * alert before deleting the chosen part.
     *
     * @param event Part delete button.
     */
    @FXML
    void partDelete(ActionEvent event) {

        Part selectedPart = partTableView.getSelectionModel().getSelectedItem();

        if (selectedPart == null) {
            displayNotification(14);

        } else {

            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Confirmation Alert");
            alert.setContentText("Confirm deletion of the chosen part?");
            Optional<ButtonType> result = alert.showAndWait();

            if (result.isPresent() && result.get() == ButtonType.OK) {
                Inventory.deletePart(selectedPart);
            }
        }
    }

    /**
     * Loads the ModifyPartController.
     *
     * The method shows an error message if no part is chosen.
     *
     * @param event Part modify button.
     * @throws IOException From FXMLLoader.
     */
    @FXML
    void partModify(ActionEvent event) throws IOException {

        chosenPartToModify = partTableView.getSelectionModel().getSelectedItem();

        if (chosenPartToModify == null) {
            displayNotification(14);
        } else {
            Parent root = FXMLLoader.load(getClass().getResource("../view/ModifyPartScreen.fxml"));
            Scene scene = new Scene(root);
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            stage.setScene(scene);
            stage.setResizable(false);
            stage.show();
        }
    }

    /**
     * Starts a search based on value in parts search text field and refreshes the parts
     * table view with the search results.
     *
     * Parts can be searched by ID or name.
     *
     * @param event Part search button.
     */
    @FXML
    void partSearchButton(ActionEvent event) {

        ObservableList<Part> allParts = Inventory.getAllParts();
        ObservableList<Part> partsFound = FXCollections.observableArrayList();
        String searchedString = partSearchText.getText();

        for (Part part : allParts) {
            if (String.valueOf(part.getId()).contains(searchedString) ||
                    part.getName().contains(searchedString)) {
                partsFound.add(part);
            }
        }

        partTableView.setItems(partsFound);

        if (partsFound.size() == 0) {
            displayNotification(12);
        }
    }

    /**
     * Refreshes part table view to dispaly all parts when parts search text field is empty.
     *
     * @param event Parts search text field key pressed.
     */
    @FXML
    void partSearchTextKey(KeyEvent event) {

        if (partSearchText.getText().isEmpty()) {
            partTableView.setItems(Inventory.getAllParts());
        }

    }

    /**
     * Deletes the product chosen by the user in the product table.
     *
     * The method displays an error message if no product is chosen and a confirmation
     * alert before deleting the chosen product. Also prevents user from deleting
     * a product with one or more associated parts.
     *
     * @param event Product delete button.
     */
    @FXML
    void productDelete(ActionEvent event) {

        Product chosenProduct = productTableView.getSelectionModel().getSelectedItem();

        if (chosenProduct == null) {
            displayNotification(15);
        } else {

            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Delete Confirmation");
            alert.setContentText("Confirm deletion of the chosen product?");
            Optional<ButtonType> result = alert.showAndWait();

            if (result.isPresent() && result.get() == ButtonType.OK) {

                ObservableList<Part> assocParts = chosenProduct.getAllAssociatedParts();

                if (assocParts.size() >= 1) {
                    displayNotification(16);
                } else {
                    Inventory.deleteProduct(chosenProduct);
                }
            }
        }
    }

    /**
     * Loads the ModifyProductController.
     *
     * The method shows an error message if no product is chosen.
     *
     * @param event Product modify button.
     * @throws IOException From FXMLLoader.
     */
    @FXML
    void productModify(ActionEvent event) throws IOException {

        chosenProductToModify = productTableView.getSelectionModel().getSelectedItem();

        //An example of correcting a runtime error by preventing null from being passed
        // to the ModifyProductController.

        if (chosenProductToModify == null) {
            displayNotification(15);
        } else {
            Parent parent = FXMLLoader.load(getClass().getResource("../view/ModifyProductScreen.fxml"));
            Scene scene = new Scene(parent);
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            stage.setScene(scene);
            stage.setResizable(false);
            stage.show();
        }
    }

    /**
     * Starts a search based on value in products search text field and refreshes the products
     * table view with search results.
     *
     * Products can be searched by ID or name.
     *
     * @param event Part search button.
     */
    @FXML
    void productSearchButton(ActionEvent event) {

        ObservableList<Product> allProducts = Inventory.getAllProducts();
        ObservableList<Product> productsFound = FXCollections.observableArrayList();
        String searchedString = productSearchText.getText();

        for (Product product : allProducts) {
            if (String.valueOf(product.getId()).contains(searchedString) ||
                    product.getName().contains(searchedString)) {
                productsFound.add(product);
            }
        }

        productTableView.setItems(productsFound);

        if (productsFound.size() == 0) {
            displayNotification(13);
        }
    }

    /**
     * Refreshes the product table view to show all products when products search text field is empty.
     *
     * @param event Products search text field key has been pressed.
     */
    @FXML
    void productSearchTextKey(KeyEvent event) {

        if (productSearchText.getText().isEmpty()) {
            productTableView.setItems(Inventory.getAllProducts());
        }
    }

}
