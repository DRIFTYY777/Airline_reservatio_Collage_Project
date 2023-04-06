import java.util.Date;
import java.util.HashMap;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import javafx.fxml.FXML;

import javafx.scene.control.ListView;
import javafx.stage.Stage;

public class ticket_listController {

    @FXML
    private ListView listview;

    @FXML
    private void initialize() {

        try {

            Stage stage = (Stage) listview.getScene().getWindow();
            HashMap<String, String> ticket = (HashMap<String, String>) stage.getUserData();

            String takeoff_place = ticket.get("takeoff_place");
            String landing_place = ticket.get("landing_place");
            String takeoff_date = ticket.get("takeoff_date");
            String landing_date = ticket.get("landing_date");
            String person_count = ticket.get("person_count");
            String class_selected = ticket.get("class_selected");
            String price = ticket.get("price");

            Common common = new Common();

            JSONArray airlines = (JSONArray) common.readJSON("airlines/airlines.json");

            for (int i = 0; i < 10; i++) {

                JSONObject random_airline = (JSONObject) airlines.get((int) (Math.random() * airlines.size()));

                String airline_name = (String) random_airline.get("name");

                resultsController results = new resultsController();

                results.set_data(takeoff_place, landing_place, airline_name, new Date(2019, 12, 12),
                        new Date(2019, 12, 12),
                        Double.parseDouble(price), 2);

                listview.getItems().add(results);
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
}
