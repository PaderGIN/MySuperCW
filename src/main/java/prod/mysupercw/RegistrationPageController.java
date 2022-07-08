package prod.mysupercw;

import java.io.IOException;
import java.net.URL;
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

public class RegistrationPageController implements Userable{

    static User user;

    private Stage stage;
    private Scene scene;
    private Parent root;
    @FXML
    private TextField nameField;

    @FXML
    private Button EnterButtonToUserPage;

    @FXML
    private TextField loginField;

    @FXML
    private Label notifycationLabel;

    @FXML
    private TextField passwordField1;

    @FXML
    private TextField passwordField2;

    @FXML
    private void switchToStart(ActionEvent event) throws IOException {
        root = FXMLLoader.load(getClass().getResource("StartPage.fxml"));
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    private void switchToAuthorization(ActionEvent event) throws IOException {
        root = FXMLLoader.load(getClass().getResource("AuthorizationPage.fxml"));
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    private void SignUpButton(ActionEvent actionEvent) throws Exception {
        String nameStr = nameField.getText().trim();
        String loginStr = loginField.getText().trim();
        String passwordStr_1 = passwordField1.getText().trim();
        String passwordStr_2 = passwordField2.getText().trim();

        if (!passwordStr_1.equals(passwordStr_2)) {
            notifycationLabel.setText("Пароли должны совпадать!");
        } else if (nameStr.trim().equals("") ||
                loginStr.trim().equals("") ||
                passwordStr_1.trim().equals("") ||
                passwordStr_2.trim().equals("")) {
            notifycationLabel.setText("Все поля должны быть заполнены");
        } else {

            user = new User(nameStr, loginStr, passwordStr_1);
            SignUpUser(user);

        }
    }

    private void SignUpUser(User user) throws Exception {
        try {
            Statement statement = DBconnector.getDBConnection().createStatement();
            statement.executeUpdate("INSERT Users(name, password, role_level, login) VALUES" +
                    "('" + user.getName() + "','" + user.getPassword() + "','simple','" + user.getLogin() + "');");
            notifycationLabel.setText("Пользователь успешно зарегистрирован!");
            EnterButtonToUserPage.setVisible(true);

        } catch (SQLException exception) {
            notifycationLabel.setText("Пользователь с таким логином уже есть! Введите другой!");
        }

    }

    @Override
    public User getUser() {
        return user;
    }
}
