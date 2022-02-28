package controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.fxml.Initializable;
import javafx.stage.Stage;
import model.InHouse;
import model.Inventory;
import model.Outsourced;
import model.Part;

import java.io.IOException;
import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;

import static controller.NotificationAlert.displayNotification;

/**
 * Controller class that provides code for the modify part screen of the application.
 *
 * @author Shadab Mustafa
 */
public class ModifyPartController implements Initializable {

    /**
     *  part object selected in the MainScreenController.
     */
    private Part chosenPart;

    /**
     *  machine ID/company name lable for the part.
     */
    @FXML
    private Label partIdNameLabel;

    /**
     *  in-house radio button.
     */
    @FXML
    private RadioButton inHouseRadioButton;

    /**
     *  toggle group for the radio buttons.
     */
    @FXML
    private ToggleGroup tgPartType;

    /**
     *  outsourced radio button.
     */
    @FXML
    private RadioButton outsourcedRadioButton;

    /**
     *  part ID text field.
     */
    @FXML
    private TextField partIdText;

    /**
     *  part name text field.
     */
    @FXML
    private TextField partNameText;

    /**
     *  inventory level text field.
     */
    @FXML
    private TextField partInventoryText;

    /**
     *  part price text field.
     */
    @FXML
    private TextField partPriceText;

    /**
     *  maximum level text field.
     */
    @FXML
    private TextField partMaxText;

    /**
     *  machine ID/company name text field.
     */
    @FXML
    private TextField partIdNameText;

    /**
     *  minimum level text field.
     */
    @FXML
    private TextField partMinText;

    /**
     * Starts controller and populates table views with part selected in MainScreenController.
     *
     * @param location The location utilized to resolve relative paths for the root object, or null if the location is unknown.
     * @param resources The resources utilized to localize the root object, or null if the root object failed to be or was not localized.
     */
    @Override
    public void initialize(URL location, ResourceBundle resources) {

        chosenPart = MainScreenController.getChosenPartToModify();

        if (chosenPart instanceof InHouse) {
            inHouseRadioButton.setSelected(true);
            partIdNameLabel.setText("Machine ID");
            partIdNameText.setText(String.valueOf(((InHouse) chosenPart).getMachineId()));
        }

        if (chosenPart instanceof Outsourced){
            outsourcedRadioButton.setSelected(true);
            partIdNameLabel.setText("Company Name");
            partIdNameText.setText(((Outsourced) chosenPart).getCompanyName());
        }

        partIdText.setText(String.valueOf(chosenPart.getId()));
        partNameText.setText(chosenPart.getName());
        partInventoryText.setText(String.valueOf(chosenPart.getStock()));
        partPriceText.setText(String.valueOf(chosenPart.getPrice()));
        partMaxText.setText(String.valueOf(chosenPart.getMax()));
        partMinText.setText(String.valueOf(chosenPart.getMin()));
    }

    /**
     * Sets machine ID label to "Machine ID".
     *
     * @param event In-house raido button action.
     */
    @FXML
    void inHouseRadioButton(ActionEvent event) {

        partIdNameLabel.setText("Machine ID");
    }

    /**
     * Sets company name label to "Company Name".
     *
     * @param event Outsourced radio button.
     */
    @FXML
    void outsourcedRadioButton(ActionEvent event) {

        partIdNameLabel.setText("Company Name");
    }

    /**
     * Displays confirmation alert and loads MainScreenController.
     *
     * @param event Cancel button action.
     * @throws IOException From FXMLLoader.
     */
    @FXML
    void cancelButtonAction(ActionEvent event) throws IOException {

        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Confirmation Alert");
        alert.setContentText("Confirm cancellation of changes and return to main screen?");
        Optional<ButtonType> result = alert.showAndWait();

        if (result.isPresent() && result.get() == ButtonType.OK) {
            toMainScreen(event);
        }
    }

    /**
     * Replaces part in inventory and loads MainScreenController.
     *
     * Text fields are tested and validated with error messages displayed preventing input of empty and/or
     * invalid values.
     *
     * @param event Save button.
     * @throws IOException From FXMLLoader.
     */
    @FXML
    void saveButton(ActionEvent event) throws IOException {

        try {
            int id = chosenPart.getId();
            String name = partNameText.getText();
            Double price = Double.parseDouble(partPriceText.getText());
            int stock = Integer.parseInt(partInventoryText.getText());
            int min = Integer.parseInt(partMinText.getText());
            int max = Integer.parseInt(partMaxText.getText());
            int machineId;
            String companyName;
            boolean partAddState = false;

            if (name.isEmpty()) {
                displayNotification(21);}

            if (minValid(min, max) && inventoryValid(min, max, stock)) {

                if (inHouseRadioButton.isSelected()  && !outsourcedRadioButton.isSelected()) {
                    try {
                        machineId = Integer.parseInt(partIdNameText.getText());
                        InHouse newInHousePart = new InHouse(id, name, price, stock, min, max, machineId);
                        Inventory.addPart(newInHousePart);
                        partAddState = true;
                    } catch (Exception e) {
                        displayNotification(18);
                    }
                }

                if (outsourcedRadioButton.isSelected() && !inHouseRadioButton.isSelected()) {
                    companyName = partIdNameText.getText();
                    Outsourced newOutsourcedPart = new Outsourced(id, name, price, stock, min, max,
                            companyName);
                    Inventory.addPart(newOutsourcedPart);
                    partAddState = true;
                }

                if (partAddState) {
                    Inventory.deletePart(chosenPart);
                    toMainScreen(event);
                }
            }
        } catch(Exception e) {
            displayNotification(17);
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
     * Confirms that min value is greater than 0 and less than max value.
     *
     * @param min minimum value for the part.
     * @param max maximum value for the part.
     * @return Boolean indicating if min is valid.
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
     * @param min minimum value for the part.
     * @param max maximum value for the part.
     * @param stock inventory level for the part.
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
