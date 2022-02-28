package controller;

import javafx.scene.control.Alert;
import java.util.Optional;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TextField;

/**
 * @author Shadab Mustafa
 */

public class NotificationAlert {

    /**
     * Shows various notification or alert messages. Some alerts are left in their own respective controller files rather than this folder.
     * @param alertType Alert message selector.
     */

    protected static void displayNotification(int alertType){

        //AddPartController alerts
        if ( alertType == 1){
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Add Part Error");
            alert.setHeaderText("Error Adding Part");
            alert.setContentText("The Form has either empty fields or holds inapplicable values.");
            alert.showAndWait();
        } else if (alertType == 2){
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Machine ID Error");
            alert.setHeaderText("Inapplicable value for Machine ID");
            alert.setContentText("The Machine ID field only holds numerical values.");
            alert.showAndWait();
        } else if (alertType == 3){
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Min Value Error");
            alert.setHeaderText("Inapplicable value for Min");
            alert.setContentText("Min(minimum) must be a whole numerical value greater than 0 and must also be less than Max(maximum).");
            alert.showAndWait();
        } else if (alertType == 4){
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Inventory Value Error");
            alert.setHeaderText("Inapplicable value for Inventory");
            alert.setContentText("Inventory value must be a numerical value equal to or in between Min and Max values.");
            alert.showAndWait();
        } else if (alertType == 5){
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Add Part Name Error");
            alert.setHeaderText("Name field is Empty");
            alert.setContentText("Name field must not be empty.");
            alert.showAndWait();
        }
        //AddProductController alerts
        if ( alertType == 6){
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Add Product Error");
            alert.setHeaderText("Error Adding Product");
            alert.setContentText("The Form has either empty fields or holds inapplicable values.");
            alert.showAndWait();
        } else if (alertType == 7){
            Alert alertInfo = new Alert(Alert.AlertType.INFORMATION);
            alertInfo.setTitle("Information Notification");
            alertInfo.setHeaderText("Part was not found");
            alertInfo.showAndWait();
        } else if (alertType == 8){
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Min Value Error");
            alert.setHeaderText("Inapplicable value for Min");
            alert.setContentText("Min(minimum) must be a whole numerical value greater than 0 and must also be less than Max(maximum).");
            alert.showAndWait();
        } else if (alertType == 9){
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Inventory Value Error");
            alert.setHeaderText("Inapplicable value for Inventory");
            alert.setContentText("Inventory value must be a numerical value equal to or in between Min and Max values.");
            alert.showAndWait();
        } else if (alertType == 10){
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Part Choosing Error");
            alert.setHeaderText("No Part chosen");
            alert.showAndWait();
        } else if (alertType == 11){
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Name Error");
            alert.setHeaderText("Name field is Empty");
            alert.setContentText("Name field must not be empty.");
            alert.showAndWait();
        }
        //MainScreenController alerts
        if ( alertType == 12){
            Alert alertInfo = new Alert(Alert.AlertType.INFORMATION);
            alertInfo.setTitle("Information Notification");
            alertInfo.setHeaderText("No Part found or does not exist");
            alertInfo.showAndWait();
        } else if (alertType == 13){
            Alert alertInfo = new Alert(Alert.AlertType.INFORMATION);
            alertInfo.setTitle("Information Notification");
            alertInfo.setHeaderText("No Product found or does not exist");
            alertInfo.showAndWait();
        } else if (alertType == 14){
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error Alert");
            alert.setHeaderText("No Part chosen");
            alert.showAndWait();
        } else if (alertType == 15){
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error Alert");
            alert.setHeaderText("No Product chosen");
            alert.showAndWait();
        } else if (alertType == 16){
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error Alert");
            alert.setHeaderText("Parts Associated");
            alert.setContentText("All parts must be unassociated or deleted from product prior to deletion.");
            alert.showAndWait();
        }
        //ModifyPartController alerts
        if ( alertType == 17){
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error Alert");
            alert.setHeaderText("Modifying Part Error");
            alert.setContentText("Form holds empty fields or inapplicable values.");
            alert.showAndWait();
        }  else if (alertType == 18){
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error Alert");
            alert.setHeaderText("Inapplicable value for Machine ID");
            alert.setContentText("Machine ID can only hold numerical values.");
            alert.showAndWait();
        } else if (alertType == 19){
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error Alert");
            alert.setHeaderText("Inapplicable value for Min");
            alert.setContentText("Min(minimum) must be a numerical value greater than 0 and also less than Max(maximum).");
            alert.showAndWait();
        } else if (alertType == 20){
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error Alert");
            alert.setHeaderText("Inapplicable value for Inventory");
            alert.setContentText("Inventory must be a numerical value equal to or in between Min and Max");
            alert.showAndWait();
        }   else if (alertType == 21) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error Alert");
            alert.setHeaderText("Name Empty");
            alert.setContentText("Name cannot be empty");
            alert.showAndWait();
        }
        //ModifyProductController alerts
        if ( alertType == 22){
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error Alert");
            alert.setHeaderText("Modifying Product Error");
            alert.setContentText("Form holds empty fields or inapplicable values.");
            alert.showAndWait();
        }  else if (alertType == 23){
            Alert alertInfo = new Alert(Alert.AlertType.INFORMATION);
            alertInfo.setTitle("Information Notification");
            alertInfo.setHeaderText("Part was not found");
            alertInfo.showAndWait();
        } else if (alertType == 24){
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error Alert");
            alert.setHeaderText("Inapplicable value for Min");
            alert.setContentText("Min(minimum) must be a numerical value greater than 0 and also less than Max(maximum).");
            alert.showAndWait();
        } else if (alertType == 25){
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error Alert");
            alert.setHeaderText("Inapplicable value for Inventory");
            alert.setContentText("Inventory must be a numerical value equal to or in between Min and Max");
            alert.showAndWait();
        }   else if (alertType == 26) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText("No Part chosen");
            alert.showAndWait();
        }   else if (alertType == 27) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error Alert");
            alert.setHeaderText("Name Empty");
            alert.setContentText("Name cannot be empty");
            alert.showAndWait();
        }
    }
}
