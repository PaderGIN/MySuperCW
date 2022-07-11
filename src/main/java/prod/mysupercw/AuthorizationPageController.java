package prod.mysupercw;

import java.io.IOException;
import java.net.URL;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
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

import static Models.User.getHashPassFromPass;


public class AuthorizationPageController {

    private static User user;
    private Stage stage;
    private Scene scene;
    private Parent parent;

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
        parent = FXMLLoader.load(getClass().getResource("StartPage.fxml"));
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        scene = new Scene(parent);
        stage.setScene(scene);
        stage.show();
    }

    private void switchToUserPageWithUser(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("UserPage.fxml"));
        parent = loader.load();
        scene = new Scene(parent);
        UserPageController controller = loader.getController();
        try {
            controller.initAuthUser(user);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();

        stage.setScene(scene);
        stage.show();
    }

    @FXML
    private void EnterCheckLoginPassword(ActionEvent event)  {
        String loginStr = loginField.getText().trim();
        String passwordStr = passwordField.getText().trim();

        if (loginStr.trim().equals("") || passwordStr.trim().equals("")) {
            statusLabel.setText("Все поля должны быть заполнены!");

        } else {
            user = new User(loginStr, passwordStr);
            CheckAuthorization(user, event);
        }
    }

    private void CheckAuthorization(User user, ActionEvent event)  {
        try {
            PreparedStatement statement = DBconnector.getDBConnection().prepareStatement("SELECT Users.login" +
                    " FROM Users" +
                    " WHERE login = ? and Users.password = ?;");
            statement.setString(1, user.getLogin());
            statement.setString(2, getHashPassFromPass(user.getPassword()));
            statement.executeQuery();
            ResultSet resultSet = statement.getResultSet();
            if (resultSet.next()) {
                switchToUserPageWithUser(event);
            } else {
                statusLabel.setText("Пользователя с такими данными нет!");
            }

        } catch (SQLException | IOException exception) {
            System.out.println(exception);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
    @FXML
    void initialize() {}
}
