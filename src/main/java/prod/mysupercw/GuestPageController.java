package prod.mysupercw;

import Models.Quote;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class GuestPageController {
    private Stage stage;
    private Scene scene;
    private Parent root;

    @FXML
    private TableView<Quote> GuestTable;
    @FXML
    private TableColumn<Quote, String> Date_Col;

    @FXML
    private TableColumn<Quote, String> Quote_Col;

    @FXML
    private TableColumn<Quote, String> Subject_Col;

    @FXML
    private TableColumn<Quote, String> Teacher_Col;
    @FXML
    private TableColumn<Quote, String> UserName_Col;
    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private void switchToStart(ActionEvent event) throws IOException {
        root = FXMLLoader.load(getClass().getResource("StartPage.fxml"));
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }



    @FXML
    public void initialize() {
        UserName_Col.setCellValueFactory(new PropertyValueFactory<Quote, String>("user_name"));
        Quote_Col.setCellValueFactory(new PropertyValueFactory<Quote, String>("q_text"));
        Teacher_Col.setCellValueFactory(new PropertyValueFactory<Quote, String>("teacher_name"));
        Subject_Col.setCellValueFactory(new PropertyValueFactory<Quote, String>("subject_name"));
        Date_Col.setCellValueFactory(new PropertyValueFactory<Quote, String>("date_of_said"));

        ObservableList<Quote> initList = getQuoteFromDB();
        GuestTable.setItems(initList);

    }

    public static ObservableList<Quote> getQuoteFromDB() {
        Connection connection = DBconnector.getDBConnection();
        ObservableList<Quote> list = FXCollections.observableArrayList();

        try {
            PreparedStatement pr = connection.prepareStatement("SELECT Quotes.id as id, Users.name as user_name," +
                    " q_text as q_text, Teacher.name as teacher_name," +
                    " Subjects.name as subject_name, date_of_said as date_of_said" +
                    " FROM Users JOIN Quotes on Users.id = Quotes.user_id" +
                    " JOIN Subjects on Quotes.subject_id = Subjects.id" +
                    " JOIN Teacher on Quotes.teacher_id = Teacher.id;");
            ResultSet rs = pr.executeQuery();
            System.out.println("ok");

            while (rs.next()) {
                Quote item = new Quote(
                        rs.getInt("id"),
                        rs.getString("user_name"),
                        rs.getString("q_text"),
                        rs.getString("teacher_name"),
                        rs.getString("subject_name"),
                        rs.getString("date_of_said"));
                System.out.println(item);
                list.add(item);

            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return list;
    }

}
