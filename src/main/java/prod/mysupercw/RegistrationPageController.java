package prod.mysupercw;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class RegistrationPageController {

    private Stage stage;
    private Scene scene;
    private Parent root;
    @FXML
    private TextField nameField;

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
    private void SignUpButton(ActionEvent actionEvent) throws Exception {
        String nameStr = nameField.getText();
        String loginStr = loginField.getText();
        String passwordStr_1 = passwordField1.getText();
        String passwordStr_2 = passwordField2.getText();

        if (!passwordStr_1.equals(passwordStr_2)) {
            notifycationLabel.setText("Пароли должны совпадать!");
        } else if (nameStr.trim().equals("") ||
                loginStr.trim().equals("") ||
                passwordStr_1.trim().equals("") ||
                passwordStr_2.trim().equals("")) {
            notifycationLabel.setText("Все поля должны быть заполнены");
        } else {

            SignUpUser(nameStr, passwordStr_1, loginStr);

        }
    }

    private void SignUpUser(String nameStr, String passwordStr_1, String loginStr) throws SQLException {
        try{
            Statement statement = DBconnector.getDBConnection().createStatement();
            statement.executeUpdate("INSERT Users(name, password, role_level, login) VALUES" +
                    "('" + nameStr + "','" + passwordStr_1 + "','simple','" + loginStr + "');");
            notifycationLabel.setText("Пользователь успешно зарегистрирован!");
        } catch (Exception exception){
            notifycationLabel.setText("Пользователь с таким логином уже есть! Введите другой!");
        }

    }

}
