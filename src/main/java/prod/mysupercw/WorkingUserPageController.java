package prod.mysupercw;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import Models.Quote;
import Models.User;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;

public class WorkingUserPageController extends GuestPageController {
    private static User workUser;
    @FXML
    private Label HelloLabel;
    private Stage stage;
    private Scene scene;
    private Parent parent;
    @FXML
    private ResourceBundle resources;
    @FXML
    private URL location;
    @FXML
    private TableColumn<Quote, String> Date_Col;
    @FXML
    private TableView<Quote> GuestTable;
    @FXML
    private TableColumn<Quote, String> Quote_Col;
    @FXML
    private TableColumn<Quote, String> Subject_Col;
    @FXML
    private TableColumn<Quote, String> Teacher_Col;
    @FXML
    private Button ToStartButton;
    @FXML
    private TableColumn<Quote, String> UserName_Col;
    @FXML
    private Button addButton;
    @FXML
    private DatePicker dateField;
    @FXML
    private Button deleteButton;
    @FXML
    private TextField quoteField;
    @FXML
    private TextField subjectField;
    @FXML
    private TextField teacherField;
    @FXML
    private Button updateButton;

    @FXML
    private void switchToStartPage(ActionEvent event) throws IOException {
        parent = FXMLLoader.load(getClass().getResource("StartPage.fxml"));
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        scene = new Scene(parent);
        stage.setScene(scene);
        stage.show();
    }

    public void initWork(User authUser) {
        workUser = authUser;
        HelloLabel.setText("Привет, " + workUser.getName() + "!");
        loadData();
    }

    private void loadData() {
        super.initialize();
    }

    @FXML
    public void initialize() {
    }
}
