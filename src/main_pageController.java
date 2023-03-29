import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.RadioButton;

public class main_pageController {

    @FXML
    private ChoiceBox<?> classes;

    @FXML
    private DatePicker depart_date;

    @FXML
    private RadioButton direct_flight;

    @FXML
    private RadioButton one_way;

    @FXML
    private ChoiceBox<?> persons;

    @FXML
    private DatePicker return_date;

    @FXML
    private RadioButton round_trip;

    @FXML
    private Button search_btn;

    @FXML
    private ChoiceBox<?> where_from;

    @FXML
    private ChoiceBox<?> where_to;

    @FXML
    private void initialize() {
        System.out.println("initialized");

        search_btn.setOnAction(e -> {
            System.out.println("search button clicked");
        });

        where_from.getItems().removeAll(where_from.getItems());

        where_from.getItems().addAll("New York", "Chicago", "Los Angeles", "SanFrancisco", "Seattle", "Boston",
                "Washington DC", "Miami", "Atlanta", "Dallas", "Houston", "Phoenix",
                "Denver", "Las Vegas",
                "Minneapolis", "Detroit", "San Diego", "St. Louis", "Tampa", "Baltimore",
                "Charlotte", "Portland",
                "Orlando", "Sacramento", "Pittsburgh", "Cleveland", "Cincinnati",
                "Indianapolis", "Kansas City",
                "Nashville", "Raleigh", "Milwaukee", "New Orleans", "Oklahoma City",
                "Buffalo", "Tucson", "Fresno",
                "Columbus", "Virginia Beach", "Jacksonville", "San Jose", "Austin", "San Antonio", "Riverside",
                "Richmond", "Louisville", "Providence", "Salt Lake City", "Hartford",
                "Newark", "Greensboro", "Omaha",
                "Wichita", "Birmingham", "Memphis", "Tulsa", "Winston-Salem", "Harrisburg",
                "Grand Rapids", "Mobile",
                "Little Rock", "Albuquerque", "Tampa", "Baltimore", "Charlotte", "Portland",
                "Orlando", "Sacramento",
                "Pittsburgh", "Cleveland", "Cincinnati", "Indianapolis", "Kansas City",
                "Nashville", "Raleigh",
                "Milwaukee", "New Orleans", "Oklahoma City", "Buffalo", "Tucson", "Fresno",
                "Columbus",
                "Virginia Beach", "Jacksonville", "San Jose", "Austin", "San Antonio",
                "Riverside", "Richmond",
                "Louisville", "Providence", "Salt Lake City", "Hartford", "Newark",
                "Greensboro", "Omaha", "Wichita",
                "Birmingham", "Memphis", "Tulsa", "Winston-Salem", "Harrisburg", "Grand Rapids", "Mobile",
                "Little Rock", "Albuquerque");

        where_from.getSelectionModel().select("New York");

    }
}
