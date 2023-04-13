import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Dialog;
import javafx.scene.control.ButtonBar.ButtonData;

public class settings_pageController {

    @FXML
    private Button exit;

    @FXML
    private Button home;

    @FXML
    private Button logout;

    Common common = new Common();

    public String logout_message(String title, String message, String button_msg, String button2_msg) {
        ButtonType button1 = new ButtonType(button_msg, ButtonData.OK_DONE);
        ButtonType button2 = new ButtonType(button2_msg, ButtonData.OK_DONE);

        Dialog<String> dialog = new Dialog<>();
        dialog.setTitle(title);
        dialog.setHeaderText(message);
        dialog.getDialogPane().getButtonTypes().add(button1);
        dialog.getDialogPane().getButtonTypes().add(button2);
        dialog.getDialogPane().lookupButton(button1);
        dialog.getDialogPane().lookupButton(button2);

        dialog.setResultConverter(dialogButton -> {
            if (dialogButton == button1) {
                System.out.println("button_msg clicked ");
                // logout
                common.switchScene(exit, "Login/login.fxml");
                common.sign_out();
            } else if (dialogButton == button2) {
                dialog.close();
                System.out.println("button2_msg clicked");
                return null;
                // kill dialog box
            }
            return null;
        });
        dialog.show();
        return null;
    }

    @FXML
    private void initialize() {
        exit.setOnAction(event -> {
            System.exit(0);
        });

        home.setOnAction(event -> {
            common.switchScene(exit, "main_page.fxml");
        });

        logout.setOnAction(event -> {
            logout_message("Exit", "Do you want to logout", "Yes", "No");
        });
    }

}
