
import java.text.DecimalFormat;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Dialog;
import javafx.scene.control.ButtonBar.ButtonData;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;

/**
 * 
 * @author Karan
 * @summary Controller of the results.fxml
 *          This class is used to set the data of the results
 *          and to show the results
 * 
 */
public class resultsController extends AnchorPane {

    @FXML
    private Text airline_name;

    @FXML
    private Text landing_place;

    @FXML
    private Text landing_time;

    @FXML
    private Text killometers;

    @FXML
    private Text person_count;

    @FXML
    private Text price;

    @FXML
    private Text traveling_class;

    @FXML
    private Text starting_time;

    @FXML
    private Text takeoff_place;

    @FXML
    private Text traveling_time;

    @FXML
    void see_flight(ActionEvent event) {

    }

    @FXML
    private Button buy;

    Common common = new Common();

    /**
     * @summary Initialize the resultsController
     */
    @FXML
    private void initialize() {
        System.out.println("resultsController initialize");
        buy.setOnAction(e -> {
            System.out.println("Buy button clicked");
            show_message("Confirm", "Are you sure you want to buy this ticket?", "Yes", "Cancel");
        });
    }

    public void show_message(String title, String message, String button_msg, String button2_msg) {
        ButtonType button1 = new ButtonType(button_msg, ButtonData.OK_DONE);
        ButtonType button2 = new ButtonType(button2_msg, ButtonData.OK_DONE);

        Dialog<String> dialog = new Dialog<>();
        dialog.setTitle(title);
        dialog.setHeaderText(message);
        dialog.getDialogPane().getButtonTypes().add(button1);
        dialog.getDialogPane().getButtonTypes().add(button2);
        boolean disabled = false; // computed based on content of text fields, for example
        dialog.getDialogPane().lookupButton(button1).setDisable(disabled);
        dialog.getDialogPane().lookupButton(button2).setDisable(disabled);

        dialog.setResultConverter(dialogButton -> {
            if (dialogButton == button1) {
                System.out.println("button_msg clicked from common");
                common.show_message("Ticket", "Thanks for Purchasing", "ok");
            } else if (dialogButton == button2) {
                System.out.println("button_msg clicked from common");
                return null;
            }
            return null;
        });

        dialog.showAndWait();
    }

    /**
     * 
     * @param takeoff_place
     * @param landing_place
     * @param airline_
     * @param starting_time
     * @param landing_time
     * @param price
     * @param traveling_time
     * @param person_count
     * @param class_selected
     * @summary Set the data of the results
     */

    public void set_data(String takeoff_place, String landing_place, String airline_, String starting_time,
            String landing_time, double price, int traveling_time, String person_count, String class_selected) {
        try {
            this.airline_name.setText(airline_);
            this.price.setTextAlignment(TextAlignment.CENTER);
            this.price.setText("₹" + new DecimalFormat("##.##").format(price));
            this.starting_time.setText(starting_time.toString());
            this.takeoff_place.setText(takeoff_place);
            this.traveling_time.setTextAlignment(TextAlignment.CENTER);
            this.traveling_time.setText(traveling_time + "Km");
            this.landing_place.setTextAlignment(TextAlignment.RIGHT);
            this.landing_place.setText(landing_place);
            this.landing_time.setTextAlignment(TextAlignment.RIGHT);
            this.landing_time.setText(landing_time.toString());
            this.person_count.setText("Person's " + person_count);
            this.traveling_class.setText(class_selected);
            this.traveling_class.setTextAlignment(TextAlignment.RIGHT);
        } catch (Exception e) {
            // TODO: handle exception
            System.out.println(e);
        }
    }

    /**
     * @summary Initialize the resultsController
     *          This is the constructor of the resultsController
     *          This is used to load the results.fxml
     *          and to set the controller of the results.fxml
     *          to this class
     * 
     */
    public resultsController() {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("Results/results.fxml"));
        loader.setController(this);
        loader.setRoot(this);
        try {
            loader.load();
        } catch (Exception e) {
            // TODO: handle exception
            // print exception
            System.out.println(e);
        }
    }
}
