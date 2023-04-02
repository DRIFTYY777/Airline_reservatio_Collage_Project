
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 * This class is used to start the application
 * 
 * @author Karan Saroha
 */
public class App extends Application {

    /**
     * Calling the common class for the user login status and methods
     */
    Common common = new Common();

    /**
     * This method is used to start the application,
     * 
     * At start, the application will check the user login status then take decision
     * to load the login page or the main page of the application
     * 
     * @param primaryStage - The primary stage of the application
     */
    @Override
    public void start(Stage primaryStage) throws Exception {
        /**
         * The root variable is used to store the page to be loaded
         */
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

    /**
     * This method is used to start the application
     * 
     * @param args - The arguments passed to the application
     */
    public static void main(String[] args) throws Exception {
        /**
         * Calling the crypto class for the encryption and decryption of the data
         */
        Crypto crypto = new Crypto();
        Crypto.crypto = crypto;
        launch(args);
    }
}
