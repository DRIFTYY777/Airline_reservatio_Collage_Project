import java.io.FileWriter;
import java.io.IOException;

import org.json.simple.JSONObject;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

public class register_acc_pageController {

    Common common = new Common();
    @FXML
    private Button already_have_acc;

    @FXML
    private TextField email;

    @FXML
    private TextField password;

    @FXML
    private TextField password1;

    @FXML
    private Button register_new_acc_btn;

    @FXML
    private void initialize() {
        already_have_acc.setOnAction(v -> {
            common.switchScene(email, "Login/login.fxml");
        });

        register_new_acc_btn.setOnAction(v -> {
            register_new_acc();
        });

    }

    private void register_new_acc() {

        String email_string = email.getText().toLowerCase().toString();
        String pass_string = password.getText().toString();
        String pass_string1 = password1.getText().toString();

        if (email_string.length() == 0) {
            System.out.println("Enter Email");
        } else if (pass_string.length() == 0 || pass_string1.length() == 0) {
            System.out.println("Enter Password");
        } else if (pass_string.length() != pass_string1.length()) {
            System.out.println("Enter Correct Password");
        } else {
            System.out.println("AccRegistered");
            save_data_local(email_string, pass_string);
        }
    }

    private void save_data_local(String email, String password) {
        // Creating a JSONObject object
        JSONObject jsonObject = new JSONObject();
        // Inserting key-value pairs into the json object
        jsonObject.put("Email", email);
        jsonObject.put("Password", password);

        try {
            FileWriter file = new FileWriter("users/output.json");
            file.write(jsonObject.toJSONString());
            file.close();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        System.out.println("JSON file created: " + jsonObject);
    }
}
