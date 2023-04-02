
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import javafx.fxml.FXML;

import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.RadioButton;
import javafx.scene.control.ToggleButton;
import javafx.scene.control.ToggleGroup;

/**
 * This class is used to control the main page of the application
 * and to handle the events of the main page
 * 
 * @author karan saroha
 * @version 1.0.0
 * @since 2023-03-31
 * 
 */
public class Main_PageController {

    /**
     * Array list for the classes
     */
    ArrayList<String> Classes = new ArrayList<String>(
            new HashMap<String, String>() {
                {
                    put("Economy", "Economy");
                    put("Premimum economy", "Premimum economy");
                    put("Business", "Business");
                    put("First Class", "First Class");
                }
            }.keySet());

    /**
     * Array list for the Persons count
     */
    ArrayList<String> Persons = new ArrayList<String>(
            new HashMap<String, String>() {
                {
                    put("1", "500");
                    put("2", "1000");
                    put("3", "1100");
                    put("4", "1200");
                }
            }.keySet());

    /**
     * Variable for the initial gui
     */
    @FXML
    private ChoiceBox classes;

    @FXML
    private DatePicker depart_date;

    @FXML
    private ToggleButton direct_flight;

    @FXML
    private RadioButton one_way;

    @FXML
    private ChoiceBox persons;

    @FXML
    private DatePicker return_date;

    @FXML
    private RadioButton round_trip;

    @FXML
    private Button search_btn;

    @FXML
    private Button test;

    @FXML
    private ChoiceBox where_from;

    @FXML
    private ChoiceBox where_to;

    /**
     * This method is used to initialize Where from and Where to choice boxes
     */
    void directions() {
        // parse data.json file
        // get data from data.json file
        List data = new ArrayList();

        Object obj = null;

        try {
            obj = new JSONParser().parse(new FileReader("airports/data.json"));
        } catch (FileNotFoundException e1) {
            // TODO Auto-generated catch block
            e1.printStackTrace();
        } catch (IOException e1) {
            // TODO Auto-generated catch block
            e1.printStackTrace();
        } catch (ParseException e1) {
            // TODO Auto-generated catch block
            e1.printStackTrace();
        }

        JSONArray jo = (JSONArray) obj;

        // JSONArray ja = (JSONArray) jo.get("Algeria");

        for (int i = 0; i < jo.size(); i++) {
            JSONObject jo2 = (JSONObject) jo.get(i);
            data.add(jo2.get("name"));
        }
        where_from.setItems(new javafx.collections.ObservableListBase<String>() {
            @Override
            public String get(int index) {
                return data.get(index).toString();
            }

            @Override
            public int size() {
                return data.size();
            }
        });

        where_to.setItems(new javafx.collections.ObservableListBase<String>() {
            @Override
            public String get(int index) {
                return data.get(index).toString();
            }

            @Override
            public int size() {
                return data.size();
            }
        });
    }

    /**
     * This method is used to initialize the classes choice box
     */
    void comfort() {
        classes.setItems(new javafx.collections.ObservableListBase<String>() {
            @Override
            public String get(int index) {
                return Classes.get(index);
            }

            @Override
            public int size() {
                return Classes.size();
            }
        });
        classes.setValue(Classes.get(0));
    }

    /**
     * This method is used to initialize the persons choice box
     */
    void person_count() {
        persons.setItems(new javafx.collections.ObservableListBase<String>() {
            @Override
            public String get(int index) {
                return Persons.get(index);
            }

            @Override
            public int size() {
                return Persons.size();
            }
        });
        persons.setValue(Persons.get(0));
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

    /**
     * This method is used to initialize the main page
     * and to handle the events of the main page
     */
    @FXML
    private void initialize() {
        System.out.println("initialized");

        depart_date();
        return_date();

        comfort(); // init
        directions(); // init
        person_count(); // init

        search_btn.setOnAction(e -> {

        });

        ToggleGroup group = new ToggleGroup(); // init
        one_way.setToggleGroup(group); // init
        round_trip.setToggleGroup(group); // init

        group.getToggles().forEach(toggle -> {
            toggle.selectedProperty().addListener((observable, oldValue, newValue) -> {
                if (newValue) {
                    if (toggle == one_way) {
                        return_date.setDisable(true);
                    } else {
                        return_date.setDisable(false);
                    }
                }
            });
        });

        direct_flight.setOnAction(e -> {
            System.out.println(direct_flight.isSelected());
        });

        test.setOnAction(e -> {
            Common common = new Common();
            // common.User = false;
            // common.switchScene(classes, "Login/login.fxml");

            common.show_message(classes, "test", "test", "ok");
        });
    }

    /**
     * This method is used to search for a flight
     * 
     * @return flights list
     */
    private boolean search_flight() {

        String takeoff_place = where_from.getValue().toString(); // place from flight takeoff
        String landing_place = where_to.getValue().toString(); // place of flight landing

        String class_selected = Classes.get(classes.getSelectionModel().getSelectedIndex()); // which class selected
        String person_count = Persons.get(persons.getSelectionModel().getSelectedIndex()); // how many person

        String takeoff_date = depart_date.getValue().toString(); // date of flight takeoff
        String landing_date = return_date.getValue().toString(); // date of flight landing

        if (one_way.isSelected()) {
            System.out.println("one way");
        } else if (round_trip.isSelected()) {
            System.out.println("round trip");
        }

        direct_flight.setOnAction(e -> {
            System.out.println(direct_flight.isSelected());
        });

        return false;

    }
}
