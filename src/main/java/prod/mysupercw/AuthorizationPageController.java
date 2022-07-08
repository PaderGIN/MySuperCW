package prod.mysupercw;

import java.io.IOException;
import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ResourceBundle;

import Models.User;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;


public class AuthorizationPageController implements Userable {

    static User user;
    private Stage stage;
    private Scene scene;
    private Parent root;

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;
    @FXML
    private Button EnterButton;
    @FXML
    private TextField loginField;

    @FXML
    private Label statusLabel;

    @FXML
    private TextField passwordField;

    @FXML
    private void switchToStart(ActionEvent event) throws IOException {
        root = FXMLLoader.load(getClass().getResource("StartPage.fxml"));
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    private void switchToUserPage(ActionEvent event) throws IOException {
        root = FXMLLoader.load(getClass().getResource("UserPage.fxml"));
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    private void EnterCheckLoginPassword(ActionEvent event) {
        String loginStr = loginField.getText().trim();
        String passwordStr = passwordField.getText().trim();

        if (loginStr.trim().equals("") || passwordStr.trim().equals("")) {
            statusLabel.setText("Все поля должны быть заполнены!");

        } else {
            user = new User(loginStr, passwordStr);
            CheckAuthorization(user, event);
        }
    }

    private void CheckAuthorization(User user, ActionEvent event) {
        try {
            Statement statement = DBconnector.getDBConnection().createStatement();
            statement.executeQuery("SELECT Users.login" +
                    " FROM Users" +
                    " WHERE login = '" + user.getLogin() + "';");
            ResultSet resultSet = statement.getResultSet();
            if (resultSet.next()) {
                switchToUserPage(event);
            } else {
                statusLabel.setText("Пользователя с такими данными нет!");
            }

        } catch (SQLException | IOException exception) {}
    }


    @FXML
    void initialize() {

    }

    @Override
    public User getUser() {
        return user;
    }
}
