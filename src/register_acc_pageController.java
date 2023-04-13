
/**
 * imports
 */
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

/**
 * This class is used to control the register account page
 * 
 * @author Karan Saroha
 *
 */
public class register_acc_pageController {

    /**
     * Calling the common class for the user login status and methods
     */
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

    /**
     * This method is used to initialize the register account page
     */
    @FXML
    private void initialize() {

        /**
         * This method is used to switch the scene to the login page
         */
        already_have_acc.setOnAction(v -> {
            common.switchScene(email, "Login/login.fxml");
        });

        /**
         * This method is used to register the new account
         */
        register_new_acc_btn.setOnAction(v -> {
            register_new_acc();
        });
    }

    /**
     * This method is used to register the new account
     * Taking the email and password from the user and saving it in the json file
     * 
     */
    private void register_new_acc() {
        String email_string = email.getText().toLowerCase().toString();
        String pass_string = password.getText().toString();
        String pass_string1 = password1.getText().toString();

        if (email_string.length() == 0) {
            System.out.println("Enter Email");
        } else if (pass_string.length() == 0 || pass_string1.length() == 0) {
            System.out.println("Enter Password");
        } else if (pass_string.length() != pass_string1.length()) {
            common.show_message("Error", "Password does not match", "okay");
            System.out.println("Enter Correct Password");
        } else {
            System.out.println("Acccount Registered");

            common.show_message("Successfully registered", "Your account successfully registered",
                    "okay");
            save_data_local(email_string, pass_string);
            common.switchScene(email, "Login/login.fxml");
        }
    }

    /**
     * This method is used to save the data locally
     * 
     * @param email    - email of the user to be saved locally in the json file
     * @param password - password of the user to be saved locally in the json file
     */
    private void save_data_local(String email, String password) {
        String enc_pass = Crypto.crypto.encrypt(password);
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("Email", email);
        jsonObject.put("Password", enc_pass);
        JSONArray temp_save = (JSONArray) common.readJSON("users/output.json");
        temp_save.add(jsonObject);
        common.writeJSON("users/output.json", temp_save.toJSONString());
        System.out.println("JSON file created: " + jsonObject);
    }
}
