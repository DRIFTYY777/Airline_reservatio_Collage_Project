
import java.util.Date;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;

import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;

public class resultsController extends AnchorPane {

    @FXML
    private Text airline_name;

    @FXML
    private Text landing_place;

    @FXML
    private Text landing_time;

    @FXML
    private Text price;

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
    private void initialize() {

    }

    /**
     * @param takeoff_place
     * @param landing_place
     * @param airline_
     * @param starting_time
     * @param landing_time
     * @param price
     * @param traveling_time
     */

    public void set_data(String takeoff_place, String landing_place, String airline_, Date starting_time,
            Date landing_time, double price, int traveling_time) {

        try {

            this.airline_name.setText(airline_);
            this.price.setTextAlignment(TextAlignment.CENTER);
            this.price.setText("$" + price);
            this.starting_time.setText(starting_time.toString());
            this.takeoff_place.setText(takeoff_place);

            this.traveling_time.setTextAlignment(TextAlignment.CENTER);
            this.traveling_time.setText(traveling_time + " hours");

            this.landing_place.setTextAlignment(TextAlignment.RIGHT);
            this.landing_place.setText(landing_place);
            this.landing_time.setTextAlignment(TextAlignment.RIGHT);
            this.landing_time.setText(landing_time.toString());

        } catch (Exception e) {
            // TODO: handle exception
            System.out.println(e);
        }
    }

}
