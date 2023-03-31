
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class App extends Application {
    // public boolean User = false;
    Common common = new Common();

    @Override
    public void start(Stage primaryStage) throws Exception {

        Parent root = null;
        primaryStage.setTitle("Driftyy Airline's");

        if (common.User != false) {
            root = FXMLLoader.load(getClass().getResource("main_page.fxml"));
        } else {
            root = FXMLLoader.load(getClass().getResource("Login/login.fxml"));
        }
        Scene scene = new Scene(root);
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public static void main(String[] args) throws Exception {

        Crypto crypto = new Crypto();
        Crypto.crypto = crypto;

        launch(args);
    }
}
