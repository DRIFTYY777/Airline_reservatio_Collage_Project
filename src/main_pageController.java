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
    private ChoiceBox where_from;

    @FXML
    private ChoiceBox where_to;

    static String[] Letters = { "abc", "def", "ghi", "jkl", "mno", "pqrs", "tuv", "wxyz" }; // Array of String

    private void directions() {

        where_from.setItems(new javafx.collections.ObservableListBase<String>() {
            @Override
            public String get(int index) {
                return Letters[index];
            }

            @Override
            public int size() {
                return Letters.length;
            }
        });

        where_to.setItems(new javafx.collections.ObservableListBase<String>() {
            @Override
            public String get(int index) {
                return Letters[index];
            }

            @Override
            public int size() {
                return Letters.length;
            }
        });
    }

    void depart_date() {
        depart_date.setOnAction(e -> {
            System.out.println(depart_date.getValue());
        });
    }

    void return_date() {
        return_date.setOnAction(e -> {
            System.out.println(return_date.getValue());
        });
    }

    @FXML
    private void initialize() {
        System.out.println("initialized");

        depart_date();
        return_date();

        directions();

        search_btn.setOnAction(e -> {
            System.out.println(where_from.getValue());
        });
    }
}
