import javafx.fxml.FXML;
import javafx.scene.control.Button;

public class settings_pageController {

    @FXML
    private Button exit;

    @FXML
    private Button home;

    @FXML
    private Button logout;

    Common common = new Common();

    @FXML
    private void initialize() {
        exit.setOnAction(event -> {
            System.exit(0);
        });

        home.setOnAction(event -> {
            common.switchScene(exit, "main_page.fxml");
        });

        logout.setOnAction(event -> {
            common.show_message(exit, "Exit", "Do you want to logout", "Yes", "No");
        });
    }

}
