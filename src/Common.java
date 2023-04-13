import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;

import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Dialog;
import javafx.scene.control.ButtonBar.ButtonData;
import javafx.stage.Stage;

/**
 * This class is used to store the common methods and variables for
 * the application
 * 
 * @author karan saroha
 * @version 1.0.0
 * @since 2023-03-31
 * 
 */
public final class Common {

    /**
     * Variable for the user login status
     */
    public boolean User = false;

    /**
     * Variable for the user data in the form of JSON object (About ticket, flight
     * details, etc.)
     */
    public JSONObject User_data = null;

    /**
     * This method is used to switch the scene (Change the window/page)
     * 
     * @param node - The node of the current window
     * @param page - The page to be loaded
     */
    public void switchScene(Node node, String page) {
        try {
            Stage stage = (Stage) node.getScene().getWindow();
            Parent root = FXMLLoader.load(getClass().getResource(page));
            Scene scene = new Scene(root);

            stage.setScene(scene);
            stage.show();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    /**
     * This method is used to switch the scene (Change the window/page)
     * 
     * @param node - The node of the current window
     * @param page - The page to be loaded
     * @param data - The data to be passed to the next window
     */
    public void switchScene(Node node, String page, Object data) {
        try {
            Stage stage = (Stage) node.getScene().getWindow();
            Parent root = FXMLLoader.load(getClass().getResource(page));
            Scene scene = new Scene(root);
            stage.setUserData(data);
            stage.setScene(scene);
            stage.show();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    /**
     * Function is used for writing the JSON file
     * 
     * @param file_name - The name of the file
     * @param data      in a form of object
     */
    public void writeJSON(String file_name, String object) {
        try {
            FileWriter file = new FileWriter(file_name);
            file.write(object);
            file.close();
        } catch (IOException e) {
            System.out.println("Json fialed     " + e);
        }
        System.out.println("JSON file created: " + object);
    }

    /**
     * Function is used for reading the JSON file
     * 
     * @param file_name - The name of the file
     * @return data in the form of object
     */
    public Object readJSON(String file_name) {
        JSONParser parser = new JSONParser();
        Object object = null;
        try {
            object = parser.parse(new FileReader(file_name));
        } catch (Exception e) {
            System.out.println("Json fialed     " + e);
        }
        return object;
    }

    /**
     * Function is used for showing the message
     * 
     * @param node    - The node of the current window
     * 
     * @param title   - The title of the message
     * @param message - The message to be shown
     * @param button  - The button message
     */
    public void show_message(String title, String message, String button_msg) {
        ButtonType loginButtonType = new ButtonType(button_msg, ButtonData.OK_DONE);
        Dialog<String> dialog = new Dialog<>();
        dialog.setTitle(title);
        dialog.setHeaderText(message);
        dialog.getDialogPane().getButtonTypes().add(loginButtonType);
        boolean disabled = false; // computed based on content of text fields, for example
        dialog.getDialogPane().lookupButton(loginButtonType).setDisable(disabled);

        // print message using loginButtonType button
        dialog.setResultConverter(dialogButton -> {
            if (dialogButton == loginButtonType) {
                System.out.println("button_msg clicked");
            }
            return null;
        });
        dialog.showAndWait();
    }

    /**
     * Function is used for showing the message
     * 
     * @param node    - The node of the current window
     * 
     * @param title   - The title of the message
     * @param message - The message to be shown
     * @param button  - The button message
     * @param button2 - The button message
     * @summary This method is used to show the message with two buttons
     */
    public void show_message(String title, String message, String button_msg, String button2_msg) {
        ButtonType button1 = new ButtonType(button_msg, ButtonData.OK_DONE);
        ButtonType button2 = new ButtonType(button2_msg, ButtonData.OK_DONE);

        Dialog<String> dialog = new Dialog<>();
        dialog.setTitle(title);
        dialog.setHeaderText(message);
        dialog.getDialogPane().getButtonTypes().add(button1);
        dialog.getDialogPane().getButtonTypes().add(button2);
        boolean disabled = false; // computed based on content of text fields, for example
        dialog.getDialogPane().lookupButton(button1).setDisable(disabled);
        dialog.getDialogPane().lookupButton(button2).setDisable(disabled);

        dialog.setResultConverter(dialogButton -> {
            if (dialogButton == button1) {
                System.out.println("button_msg clicked from common");
            } else if (dialogButton == button2) {
                System.out.println("button_msg clicked from common");
                return null;
            }
            return null;
        });

        dialog.showAndWait();
    }

    public void sign_out() {
        User = false;
        User_data = null;
    }

    /**
     * @param from_lat  - The latitude of the source
     * @param from_long - The longitude of the source
     * @param to_lat    - The latitude of the destination
     * @param to_long   - The longitude of the destination
     * @return distance in km
     */
    public double get_distance(double from_lat, double from_long, double to_lat, double to_long) {
        // Haversine formula
        double R = 6371e3; // metres
        double φ1 = from_lat * Math.PI / 180; // φ, λ in radians
        double φ2 = to_lat * Math.PI / 180;
        double Δφ = (to_lat - from_lat) * Math.PI / 180;
        double Δλ = (to_long - from_long) * Math.PI / 180;
        double a = Math.sin(Δφ / 2) * Math.sin(Δφ / 2)
                + Math.cos(φ1) * Math.cos(φ2) * Math.sin(Δλ / 2) * Math.sin(Δλ / 2);
        double c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1 - a));
        double d = R * c; // in metres
        return d / 1000; // returning in km
    }
}
