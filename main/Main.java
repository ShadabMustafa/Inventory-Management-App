package main;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import model.InHouse;
import model.Inventory;
import model.Outsourced;
import model.Product;
/**
 * The Inventory Management program implements an application for managing
 * an inventory of parts and products consisting of parts.
 *
 * FUTURE ENHANCEMENT
 * A future enhancement for a future update could be to have auto generated part and product fields.
 * For some people, coming up with a name or number can prove difficult, this upgrade would rectify this.
 *
 * @author Shadab Mustafa
 *
 */
public class Main extends Application {

    /**
     * start method creates the FXML stage and loads the initial scene.
     *
     * @param primaryStage
     * @throws Exception
     */
    @Override
    public void start(Stage primaryStage) throws Exception{
        Parent root = FXMLLoader.load(getClass().getResource("../view/MainScreen.fxml"));
        primaryStage.setTitle("Inventory Management System");
        primaryStage.setScene(new Scene(root));
        primaryStage.show();
    }

    /**
     * main method is the start point of the application.
     *
     * main method inputs sample test data and launches the application.
     *
     * @param args
     */
    public static void main(String[] args) {

        //Adds sample parts
        int partId = Inventory.getNewPartId();
        InHouse Body = new InHouse(partId,"GPU Main Body", 600.00, 55, 11, 90,
                104);
        partId = Inventory.getNewPartId();
        InHouse Fan = new InHouse(partId,"GPU Fan", 100.00, 55, 11, 90,
                107);
        partId = Inventory.getNewPartId();
        Outsourced Coolant = new Outsourced(partId, "GPU Coolant",49.99, 55, 11,
                95, "Hyper Cool");
        Inventory.addPart(Body);
        Inventory.addPart(Fan);
        Inventory.addPart(Coolant);

        //Adds sample product
        int productId = Inventory.getNewProductId();
        Product GPU = new Product(productId, "Generic GPU", 999.99, 55, 11,
                94);
        GPU.addAssociatedPart(Body);
        GPU.addAssociatedPart(Fan);
        GPU.addAssociatedPart(Coolant);
        Inventory.addProduct(GPU);

        launch(args);
    }
}