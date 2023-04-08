import java.util.HashMap;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;

public class ticket_listController {

    @FXML
    private Button exit;

    @FXML
    private Button home;

    @FXML
    private ListView listview;
    public static Object data = null;

    @FXML
    private void initialize() {
        HashMap<String, String> ticket = (HashMap<String, String>) data;

        exit.setOnAction(e -> {
            System.exit(0);
        });

        home.setOnAction(e -> {
            data = null;
            Common common = new Common();
            common.switchScene(exit, "main_page.fxml");
        });

        System.out.println("ticket_listController initialize");
        try {
            String takeoff_place = ticket.get("takeoff_place");
            String landing_place = ticket.get("landing_place");
            String takeoff_date = ticket.get("takeoff_date");
            String landing_date = ticket.get("landing_date");
            String person_count = ticket.get("person_count");
            String class_selected = ticket.get("class_selected");
            double distance = Double.parseDouble(ticket.get("distance"));
            String price = ticket.get("price");
            Common common = new Common();
            JSONArray airlines = (JSONArray) common.readJSON("airlines/airlines.json");

            for (int i = 0; i < 10; i++) {
                JSONObject random_airline = (JSONObject) airlines.get((int) (Math.random() *
                        airlines.size()));
                String airline_name = (String) random_airline.get("name");
                double dee_price = Double.parseDouble(price) + (Math.random() * 5000);
                resultsController results = new resultsController();
                results.set_data(takeoff_place, landing_place, airline_name, takeoff_date,
                        landing_date, dee_price, (int) distance, person_count, class_selected);
                listview.getItems().add(results);
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
}
