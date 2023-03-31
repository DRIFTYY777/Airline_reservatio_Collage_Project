import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import javafx.fxml.FXMLLoader;

import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Dialog;
import javafx.scene.control.ButtonBar.ButtonData;
import javafx.stage.Stage;

public final class Common {

    public boolean User = false;
    public JSONObject User_data = null;

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

    public void show_message(Node node, String title, String message) {
        ButtonType loginButtonType = new ButtonType("Login", ButtonData.OK_DONE);
        Dialog<String> dialog = new Dialog<>();
        dialog.getDialogPane().getButtonTypes().add(loginButtonType);
        boolean disabled = false; // computed based on content of text fields, for example
        dialog.getDialogPane().lookupButton(loginButtonType).setDisable(disabled);

        dialog.showAndWait().ifPresent(response -> {
            if (loginButtonType.getButtonData() == ButtonData.OK_DONE) {
                System.out.println("Login button pressed");
            }
        });
    }
}
