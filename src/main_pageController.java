
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.Date;
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
     * Object of the common class
     */
    Common common = new Common();

    /**
     * Array list for the classes
     */
    ArrayList<String> Classes = new ArrayList<String>(new HashMap<String, String>() {
        {
            put("Economy", "Economy");
            put("Premium Economy", "Premium Economy");
            put("Business", "Business");
            put("First Class", "First Class");
        }
    }.keySet());

    /**
     * Array list for the Persons count
     */
    ArrayList<String> Persons = new ArrayList<String>(new HashMap<String, String>() {
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
            // Common common = new Common();
            // common.User = false;
            // common.switchScene(classes, "Login/login.fxml");
            common.show_message(classes, "test", "test", "ok");

        });

        search_btn.setOnAction(e -> {
            search_flight();
        });
    }

    /**
     * This method is used to search for a flight
     * 
     * @return flights list
     */
    private boolean search_flight() {

        String person_count = Persons.get(persons.getSelectionModel().getSelectedIndex()); // how many person
        String class_selected = Classes.get(classes.getSelectionModel().getSelectedIndex()); // which class selected

        String takeoff_place = "", takeoff_lat = null, takeoff_lon = null,
                takeoff_name,
                takeoff_city, takeoff_state, takeoff_country;

        LocalDate takeoff_date = null;

        String landing_place = "", landing_lat = "", landing_date = "", landing_lon = null, landing_name, landing_city,
                landing_state,
                landing_country;

        try {
            takeoff_place = where_from.getValue().toString(); // place from flight takeoff
            landing_place = where_to.getValue().toString(); // place of flight landing
        } catch (Exception e) {
            if (e.toString().contains(takeoff_place)) {
                common.show_message(classes, "Error", "Please select Tekeoff place", "ok");
            } else {
                common.show_message(classes, "Error", "Please select Landing place", "ok");
            }
        }

        try {

            takeoff_date = depart_date.getValue(); // date of flight takeoff
            if (one_way.isSelected()) {
                landing_date = "0";
            } else {

                landing_date = return_date.getValue().toString(); // date of flight landing
            }
        } catch (

        Exception e) {
            common.show_message(classes, "Error", "Please select a date", "ok");
        }

        if (one_way.isSelected()) {
            System.out.println("one way");
        } else if (round_trip.isSelected()) {
            System.out.println("round trip");
        }

        direct_flight.setOnAction(e -> {
            System.out.println(direct_flight.isSelected());
        });

        JSONArray airpots_data = (JSONArray) common.readJSON("airports/data.json");

        if (takeoff_place.length() != 0 || landing_place.length() != 0 || takeoff_date.toString().length() != 0) {

            for (Object object : airpots_data) {
                JSONObject user = (JSONObject) object;

                if (user.get("name").toString().equals(takeoff_place)) {
                    takeoff_lat = (String) user.get("lat");
                    takeoff_lon = (String) user.get("lon");
                    takeoff_name = (String) user.get("name");
                    takeoff_city = (String) user.get("city");
                    takeoff_state = (String) user.get("state");
                    takeoff_country = (String) user.get("country");
                }

                if (user.get("name").toString().equals(landing_place)) {
                    landing_lat = (String) user.get("lat");
                    landing_lon = (String) user.get("lon");
                    landing_name = (String) user.get("name");
                    landing_city = (String) user.get("city");
                    landing_state = (String) user.get("state");
                    landing_country = (String) user.get("country");
                }

            }

            double distance = common.get_distance(Double.parseDouble(takeoff_lat), Double.parseDouble(takeoff_lon),
                    Double.parseDouble(landing_lat), Double.parseDouble(landing_lon));

            double price = distance;
            // price based on classes
            if (class_selected.equals("Economy")) {
                price = price * 10;
            } else if (class_selected.equals("Premium Economy")) {
                price = price * 15;
            } else if (class_selected.equals("Business")) {
                price = price * 20;
            } else if (class_selected.equals("First Class")) {
                price = price * 50;
            }

            // set the price based on current date and selected date
            LocalDate now = LocalDate.now();
            long days = ChronoUnit.DAYS.between(now, takeoff_date);

            double discount = 0;
            if (days > 30) {
                discount = 0.3;
            } else if (days > 15) {
                discount = 0.1;
            } else if (days > 7) {
                discount = 0.08;
            } else if (days > 3) {
                discount = 0.05;
            } else if (days > 1) {
                discount = 0.02;
            }

            price = price - (price * discount);
            int person_count_int = Integer.parseInt(person_count);
            price = price * person_count_int;

            System.out.println("distance    " + distance + "        " + "price    " + price);
        }

        return false;
    }
}
