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

    }

}
