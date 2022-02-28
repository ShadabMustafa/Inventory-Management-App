package controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;
import model.InHouse;
import model.Inventory;
import model.Outsourced;

import java.io.IOException;
import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;

import static controller.NotificationAlert.displayNotification;

/**
 * The Controller class that provides code for the add part screen of the Inventory Management application.
 *
 * @author Shadab Mustafa
 */
public class AddPartController implements Initializable {

    /**
     * machine ID/company name label for respective part.
     */
    @FXML
    private Label partIdNameLabel;

    /**
     * in-house radio button.
     */
    @FXML
    private RadioButton inHouseRadioButton;

    /**
     * toggle group for respective radio buttons.
     */
    @FXML
    private ToggleGroup tgPartType;

    /**
     * outsourced radio button.
     */
    @FXML
    private RadioButton outsourcedRadioButton;

    /**
     * part ID text field.
     */
    @FXML
    private TextField partIdText;

    /**
     * part name text field.
     */
    @FXML
    private TextField partNameText;

    /**
     *  part inventory text field.
     */
    @FXML
    private TextField partInventoryText;

    /**
     *  part price text field.
     */
    @FXML
    private TextField partPriceText;

    /**
     *  part maximum level text field.
     */
    @FXML
    private TextField partMaxText;

    /**
     *  machine ID text field for the part.
     */
    @FXML
    private TextField partIdNameText;

    /**
     *  part minimum level text field.
     */
    @FXML
    private TextField partMinText;

    /**
     * Starts the controller and subsequently sets the in-house radio button status to true.
     *
     * @param location The location utilized to resolve relative paths for the root object, or null if the location is unknown.
     * @param resources The resources utilized to localize the root object, or null if the root object failed to be or was not localized.
     */
    @Override
    public void initialize(URL location, ResourceBundle resources) {

        inHouseRadioButton.setSelected(true);
    }

    /**
     * Sets the machine id label to "Machine ID".
     *
     * @param event in-house radio button.
     */
    @FXML
    void inHouseRadioButton(ActionEvent event) {

        partIdNameLabel.setText("Machine ID");
    }

    /**
     * Sets the company name label to "Company Name".
     *
     * @param event Outsourced radio button.
     */
    @FXML
    void outsourcedRadioButton(ActionEvent event) {

        partIdNameLabel.setText("Company Name");
    }

    /**
     * Shows the confirmation alert and loads the MainScreenController file.
     *
     * @param event Cancel button.
     * @throws IOException From FXMLLoader.
     */
    @FXML
    void cancelButton(ActionEvent event) throws IOException {

        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Confirmation Alert");
        alert.setContentText("Do you wish to confirm cancelling changes and return to the main screen?");
        Optional<ButtonType> result = alert.showAndWait();

        if (result.isPresent() && result.get() == ButtonType.OK) {
            toMainScreen(event);
        }
    }

    /**
     * Adds a new part to inventory and loads the MainScreenController file.
     * <p>
     * Text fields are tested and validated via error messages displayed preventing the input of empty and/or
     * invalid values.
     *
     * @param event Save button.
     * @throws IOException From FXMLLoader.
     */
    @FXML
    void saveButton(ActionEvent event) throws IOException {

        try {
            int id = 0;
            String name = partNameText.getText();
            Double price = Double.parseDouble(partPriceText.getText());
            int stock = Integer.parseInt(partInventoryText.getText());
            int min = Integer.parseInt(partMinText.getText());
            int max = Integer.parseInt(partMaxText.getText());
            int machineId;
            String companyName;
            boolean partAddState = false;

            if (name.isEmpty()) {
                 displayNotification(5);
            } else {
                if ( inventoryValid(min, max, stock) && minValid(min, max) ) {

                    if (inHouseRadioButton.isSelected()  && !outsourcedRadioButton.isSelected()) {
                        try {
                            machineId = Integer.parseInt(partIdNameText.getText());
                            InHouse newInHousePart = new InHouse(id, name, price, stock, min, max, machineId);
                            newInHousePart.setId(Inventory.getNewPartId());
                            Inventory.addPart(newInHousePart);
                            partAddState = true;
                        } catch (Exception e) {
                            displayNotification(2);
                        }
                    }

                    if (outsourcedRadioButton.isSelected() && !inHouseRadioButton.isSelected()) {
                        companyName = partIdNameText.getText();
                        Outsourced newOutsourcedPart = new Outsourced(id, name, price, stock, min, max,
                                companyName);
                        newOutsourcedPart.setId(Inventory.getNewPartId());
                        Inventory.addPart(newOutsourcedPart);
                        partAddState = true;
                    }

                    if (partAddState) {
                        toMainScreen(event);
                    }
                }
            }
        } catch (Exception e) {
            displayNotification(1);
        }
    }

    /**
     * Loads the MainScreenController.
     *
     * @param event Passed from parent method.
     * @throws IOException From FXMLLoader.
     */
    private void toMainScreen(ActionEvent event) throws IOException {

        Parent root = FXMLLoader.load(getClass().getResource("../view/MainScreen.fxml"));
        Scene scene = new Scene(root);
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setScene(scene);
        stage.setResizable(false);
        stage.show();
    }

    /**
     * Confirms that min value is greater than 0 and less than max value.
     *
     * @param min The minimum value for the part.
     * @param max The maximum value for the part.
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
     * Confirms that the inventory level is equal too or between min values and max.
     *
     * @param min    minimum value for the part.
     * @param max    maximum value for the part.
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
