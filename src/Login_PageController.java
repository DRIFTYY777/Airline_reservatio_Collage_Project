
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

public class Login_PageController {

    Common common = new Common();

    @FXML
    private TextField email;

    @FXML
    private TextField password;

    @FXML
    private Button register_account_btn;

    @FXML
    private Button signin_btn;

    @FXML
    private void initialize() {
        System.out.println("LogIn Initilized");

        signin_btn.setOnAction(e -> {
            login();
        });

        register_account_btn.setOnAction(e -> {
            common.switchScene(email, "Register_Acc/register_acc_page.fxml");
        });
    }

    private void login() {
        String email_string = email.getText().toString().toLowerCase();
        String passw_string = password.getText().toString();

        String dec_pass = Crypto.crypto.encrypt(passw_string);
        JSONArray data = (JSONArray) common.readJSON("users/output.json");

        for (Object object : data) {
            JSONObject user = (JSONObject) object;
            String email = (String) user.get("Email");
            String pass = (String) user.get("Password");

            if (email_string.equals(email) && dec_pass.equals(pass)) {
                System.out.println("Login Success");
                common.User = true;
                common.User_data = user;
                common.switchScene(signin_btn, "main_page.fxml");
                break;
            } else {
                common.User = false;
            }
        }
        if (common.User == false) {
            System.out.println("Login Failed");
        }
    }

}
