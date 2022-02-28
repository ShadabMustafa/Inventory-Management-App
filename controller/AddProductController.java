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
 * Controller class that provides code for the add product screen of the application.
 *
 * @author Shadab Mustafa
 */
public class AddProductController implements Initializable {

    /**
     * A list which contains parts associated with the product.
     */
    private ObservableList<Part> assocParts = FXCollections.observableArrayList();

    /**
     *  associated parts table view.
     */
    @FXML
    private TableView<Part> assocPartTableView;

    /**
     *  part ID column for the associated parts table.
     */
    @FXML
    private TableColumn<Part, Integer> assocPartIdColumn;

    /**
     *  part name column for the associated parts table.
     */
    @FXML
    private TableColumn<Part, String> assocPartNameColumn;

    /**
     *  inventory level column for the associated parts table.
     */
    @FXML
    private TableColumn<Part, Integer> assocPartInventoryColumn;

    /**
     *  price column for the associated parts table.
     */
    @FXML
    private TableColumn<Part, Double> assocPartPriceColumn;

    /**
     *  all parts table view.
     */
    @FXML
    private TableView<Part> partTableView;

    /**
     *  part ID column for the all parts table.
     */
    @FXML
    private TableColumn<Part, Integer> partIdColumn;

    /**
     *  name column for the all parts table.
     */
    @FXML
    private TableColumn<Part, String> partNameColumn;

    /**
     *  inventory level column for the all parts table.
     */
    @FXML
    private TableColumn<Part, Integer> partInventoryColumn;

    /**
     *  price column for the all parts table.
     */
    @FXML
    private TableColumn<Part, Double> partPriceColumn;

    /**
     *  part search text field.
     */
    @FXML
    private TextField partSearchText;

    /**
     *  product ID text field.
     */
    @FXML
    private TextField productIdText;

    /**
     *  product name text field.
     */
    @FXML
    private TextField productNameText;

    /**
     *  product inventory level text field.
     */
    @FXML
    private TextField productInventoryText;

    /**
     *  product price text field.
     */
    @FXML
    private TextField productPriceText;

    /**
     *  product maximum level text field.
     */
    @FXML
    private TextField productMaxText;

    /**
     *  product minimum level text field.
     */
    @FXML
    private TextField productMinText;

    /**
     * Starts controller and then populates table views.
     *
     * @param location The location utilized to resolve relative paths for the root object, or null if the location is unknown.
     * @param resources The resources utilized to localize the root object, or null if the root object failed to be or was not localized.
     */
    @Override
    public void initialize(URL location, ResourceBundle resources) {

        partIdColumn.setCellValueFactory(new PropertyValueFactory<>("id"));
        partNameColumn.setCellValueFactory(new PropertyValueFactory<>("name"));
        partInventoryColumn.setCellValueFactory(new PropertyValueFactory<>("stock"));
        partPriceColumn.setCellValueFactory(new PropertyValueFactory<>("price"));
        partTableView.setItems(Inventory.getAllParts());

        assocPartIdColumn.setCellValueFactory(new PropertyValueFactory<>("id"));
        assocPartNameColumn.setCellValueFactory(new PropertyValueFactory<>("name"));
        assocPartInventoryColumn.setCellValueFactory(new PropertyValueFactory<>("stock"));
        assocPartPriceColumn.setCellValueFactory(new PropertyValueFactory<>("price"));

    }

    /**
     * Adds part object chosen in the all parts table to the associated parts table.
     *
     * Shows an error message if no part is chosen.
     *
     * @param event Add button.
     */
    @FXML
    void addButton(ActionEvent event) {

        Part selectedPart = partTableView.getSelectionModel().getSelectedItem();

        if (selectedPart == null) {
            displayNotification(10);
        } else {
            assocParts.add(selectedPart);
            assocPartTableView.setItems(assocParts);
        }
    }

    /**
     * Show confirmation message and loads MainScreenController.
     *
     * @param event Cancel button.
     * @throws IOException From FXMLLoader.
     */
    @FXML
    void cancelButton(ActionEvent event) throws IOException {

        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Confirmation Alert");
        alert.setContentText("Confirm cancellation of changes and return to main screen?");
        Optional<ButtonType> result = alert.showAndWait();

        if (result.isPresent() && result.get() == ButtonType.OK) {
            toMainScreen(event);
        }
    }

    /**
     * Starts a search based on value in parts search text field and refreshes the parts
     * table view with the search results.
     *
     * Parts can be searched for by ID or name.
     *
     * @param event Part search button.
     */
    @FXML
    void partSearchButton(ActionEvent event) {

        ObservableList<Part> allParts = Inventory.getAllParts();
        ObservableList<Part> partsFound = FXCollections.observableArrayList();
        String searchedString = partSearchText.getText();

        for (Part part : allParts) {
            if (String.valueOf(part.getId()).contains(searchedString)) {
                partsFound.add(part);
            }

            if (part.getName().contains(searchedString)) {
                partsFound.add(part);
            }
        }

        partTableView.setItems(partsFound);

        if (partsFound.size() == 0) {
            displayNotification(7);
        }
    }

    /**
     * Refreshes part table view to display all parts when the parts search text field is empty.
     *
     * @param event Parts search text field key is pressed.
     */
    @FXML
    void partSearchKey(KeyEvent event) {

        if (partSearchText.getText().isEmpty()) {
            partTableView.setItems(Inventory.getAllParts());
        }
    }

    /**
     * Shows confirmation message and removes chosen part from associated parts table.
     *
     * Shows error message if no part is chosen.
     *
     * @param event Remove button action.
     */
    @FXML
    void removeButton(ActionEvent event) {

        Part selectedPart = assocPartTableView.getSelectionModel().getSelectedItem();

        if (selectedPart == null) {
            displayNotification(5);
        } else {

            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Confirmation Alert");
            alert.setContentText("Confirm removal of the selected part?");
            Optional<ButtonType> result = alert.showAndWait();

            if (result.isPresent() && result.get() == ButtonType.OK) {
                assocParts.remove(selectedPart);
                assocPartTableView.setItems(assocParts);
            }
        }
    }

    /**
     * Adds the new product to inventory and loads the MainScreenController.
     *
     * Text fields are tested and validated with error messages displayed preventing input of empty and/or
     * invalid values.
     *
     * @param event Save button.
     * @throws IOException From FXMLLoader.
     */
    @FXML
    void saveButtonAction(ActionEvent event) throws IOException {

        try {
            int id = 0;
            String name = productNameText.getText();
            Double price = Double.parseDouble(productPriceText.getText());
            int stock = Integer.parseInt(productInventoryText.getText());
            int min = Integer.parseInt(productMinText.getText());
            int max = Integer.parseInt(productMaxText.getText());

            if (name.isEmpty()) {
                displayNotification(11);
            } else {
                if ( inventoryValid(min, max, stock) && minValid(min, max)) {

                    Product newProduct = new Product(id, name, price, stock, min, max);

                    for (Part part : assocParts) {
                        newProduct.addAssociatedPart(part);
                    }

                    newProduct.setId(Inventory.getNewProductId());
                    Inventory.addProduct(newProduct);
                    toMainScreen(event);
                }
            }
        } catch (Exception e){
            displayNotification(6);
        }
    }

    /**
     * Loads MainScreenController.
     *
     * @param event Passed from parent method.
     * @throws IOException From FXMLLoader.
     */
    private void toMainScreen(ActionEvent event) throws IOException {

        Parent parent = FXMLLoader.load(getClass().getResource("../view/MainScreen.fxml"));
        Scene scene = new Scene(parent);
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setScene(scene);
        stage.setResizable(false);
        stage.show();
    }

    /**
     * Confirms that min is greater than 0 and less than max.
     *
     * @param min  minimum value for the part.
     * @param max  maximum value for the part.
     * @return Boolean indicating if min value is valid.
     */
    private boolean minValid(int min, int max) {

        boolean valid = true;

        if ( min >= max  ) {
            valid = false;
            displayNotification(3);
        }

        if ( min <= 0 ) {
            valid = false;
            displayNotification(3);
        }

        return valid;
    }

    /**
     * Confirms that inventory level is equal too or between min value and max value.
     *
     * @param min  minimum value for the part.
     * @param max  maximum value for the part.
     * @param stock  inventory level for the part.
     * @return Boolean indicating if inventory is valid.
     */
    private boolean inventoryValid(int min, int max, int stock) {

        boolean valid = true;

        if ( stock > max ) {
            valid = false;
            displayNotification(4);
        }

        if ( stock < min ) {
            valid = false;
            displayNotification(4);
        }

        return valid;
    }

}
