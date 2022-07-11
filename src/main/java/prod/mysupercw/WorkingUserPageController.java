package prod.mysupercw;

import java.io.IOException;
import java.net.URL;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ResourceBundle;

import Models.Quote;
import Models.User;
import javafx.collections.ObservableList;
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
    private static Quote selectedQuote;
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
    private Label statusLabel;
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
    private TableColumn<Quote, String> UserName_Col;
    @FXML
    private DatePicker dateField;
    @FXML
    private TextField quoteField;
    @FXML
    private TextField subjectField;
    @FXML
    private TextField teacherField;
    @FXML
    private Button updateButton;
    @FXML
    private Button addButton;
    @FXML
    private Button deleteButton;
    @FXML
    private Button takeButton;

    @FXML
    void takeQuote(ActionEvent event)  {
        try {
            ObservableList<Quote> quote;
            quote = super.GuestTable.getSelectionModel().getSelectedItems();
            System.out.println(quote.get(0).getSubject_name());
            quoteField.setText(quote.get(0).getQ_text());
            teacherField.setText(quote.get(0).getTeacher_name());
            subjectField.setText(quote.get(0).getSubject_name());
            dateField.setValue(LocalDate.parse((quote.get(0).getDate_of_said())));
            selectedQuote = quote.get(0);
            statusLabel.setText("");
        } catch (Exception e){
            statusLabel.setText("Вы ничего не выбрали!");
        }


    }


    @FXML
    private void switchToStartPage(ActionEvent event) throws IOException {
        parent = FXMLLoader.load(getClass().getResource("StartPage.fxml"));
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        scene = new Scene(parent);
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    private void AddQuote() { //буфферы создания
//        String userName = workUser.getName().trim();
//        String quoteText = quoteField.getText().trim();
//        String teacherName = teacherField.getText().trim();
//        String subjectName = subjectField.getText().trim();
//        String date = dateField.getValue().format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));
//
//        if (userName.trim().equals("") ||
//                quoteText.trim().equals("") ||
//                teacherName.trim().equals("") ||
//                subjectName.trim().equals("") ||
//                date.trim().equals("")) {
//            statusLabel.setText("Все поля должны быть заполнены");
//        } else if (!isQuote(quoteText)) {
//            statusLabel.setText("Пользователя с таким именем нет!");
//        } else {
//            createNewQuote();
//        }
        RealAddQuote();
    }


    @FXML
    private void updateQuote() { //буфферы апдейта
        RealUpdateQuote();
    }

    @FXML
    private void deleteQuote() { //буфферы удаления
        realDeleteQuote();
    }
    private void RealAddQuote(){
        try {
            PreparedStatement prStatement;
            prStatement = DBconnector.getDBConnection().prepareStatement("INSERT Quotes(user_id, q_text,teacher_id," +
                    " subject_id, date_of_said)" +
                    " VALUES (?,?,(SELECT id FROM Teacher where name = ?)," +
                    " (SELECT id FROM Subjects where name = ?),?);");
            prStatement.setInt(1, workUser.getId());
            prStatement.setString(2, quoteField.getText().trim());
            prStatement.setString(3, teacherField.getText().trim());
            prStatement.setString(4, subjectField.getText().trim());
            prStatement.setString(5, dateField.getValue().toString().trim());
            prStatement.execute();
            selectedQuote = null;
            clearFields();
            loadData();
        } catch (Exception e) {
            statusLabel.setText("Ошибка ввода!");
            System.out.println("Ошибка ввода!");
            System.out.println(e);
        }

    }
    private void RealUpdateQuote(){
        try {
            PreparedStatement prStatement;
            prStatement = DBconnector.getDBConnection().prepareStatement("UPDATE Quotes SET Quotes.q_text = ?," +
                    " Quotes.teacher_id = (SELECT id FROM Teacher WHERE name = ?)," +
                    " Quotes.subject_id = (SELECT id FROM Subjects WHERE name = ?)," +
                    " Quotes.date_of_said = ?" +
                    " WHERE id =  ?;");
            prStatement.setString(1, quoteField.getText().trim());
            prStatement.setString(2, teacherField.getText().trim());
            prStatement.setString(3, subjectField.getText().trim());
            prStatement.setString(4, dateField.getValue().toString().trim());
            prStatement.setInt(5, selectedQuote.getId());
            prStatement.executeUpdate();
            selectedQuote = null;
            clearFields();
            loadData();
        } catch (Exception e) {
            System.out.println("Wrong!");
            statusLabel.setText("Ошибка ввода данных!");
        }
    }

    private void realDeleteQuote(){
        try {
            PreparedStatement prStatement;
            prStatement = DBconnector.getDBConnection().prepareStatement("DELETE FROM Quotes WHERE id = ?;");
            prStatement.setInt(1, selectedQuote.getId());
            prStatement.execute();
            selectedQuote = null;
            loadData();
            clearFields();
        } catch (Exception e) {
            statusLabel.setText("Ошибка ввода данных!");
        }
    }

    private void clearFields() {
        quoteField.setText("");
        teacherField.setText("");
        subjectField.setText("");
        dateField.setValue(null);
        statusLabel.setText("");
    }

    public void initWork(User authUser) throws SQLException {
        workUser = authUser;
        HelloLabel.setText("Привет, " + workUser.getName() + "!");
        loadData();
    }

    private void loadData() throws SQLException {
        super.initialize();
    }

    @FXML
    public void initialize() {
    }

}
