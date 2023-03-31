
import java.io.Console;

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

        common.User = true;

        signin_btn.setOnAction(e -> {
            login();

            if (common.User) {
                common.switchScene(signin_btn, "main_page.fxml");
            } else {
                System.out.println("Login First");
            }
        });

        register_account_btn.setOnAction(e -> {
            common.switchScene(email, "Register_Acc/register_acc_page.fxml");
        });
    }

    private void login() {
        String email_string = email.getText().toString().toLowerCase();
        String passw_string = password.getText().toString();
        System.out.println(email_string + "   " + passw_string);
    }

}
