import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

public class Controller {

    @FXML
    private TextField email_feild;

    @FXML
    private TextField password_feild;

    @FXML
    private Button signIn_btn;

    @FXML
    private void initialize() {
        System.out.println("Controller initialized!");
        signIn_btn.setOnAction(this::handleSignIn);
    }

    private void handleSignIn(ActionEvent actionevent1) {
        System.out.println("Sign in button clicked!");
        System.out.println("Email: " + email_feild.getText());
        System.out.println("Password: " + password_feild.getText());
    }

}
