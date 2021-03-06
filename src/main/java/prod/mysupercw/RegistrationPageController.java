package prod.mysupercw;

import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.regex.Pattern;

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

public class RegistrationPageController {

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
            notifycationLabel.setText("???????????? ???????????? ??????????????????!");
        } else if (nameStr.trim().equals("") ||
                loginStr.trim().equals("") ||
                passwordStr_1.trim().equals("") ||
                passwordStr_2.trim().equals("")) {
            notifycationLabel.setText("?????? ???????? ???????????? ???????? ??????????????????");

        } else if (!test(nameStr)){
            notifycationLabel.setText("?? ???????? ?????? ???? ?????????? ???????? ??????????/??????????????????????!");
        } else {
            user = new User(nameStr, loginStr, passwordStr_1);
            SignUpUser(user);
        }
    }
    private static boolean test(String str) {
        String text = str;
        if (Pattern.matches("[a-zA-Z??-????-?? .]+", text)) {
            return true;
        } else {
            return false;
        }
    }

    private void SignUpUser(User user) {
        try {
            PreparedStatement prStatement;
            prStatement = DBconnector.getDBConnection().prepareStatement("INSERT Users(name, password, login) VALUES" +
                    "(?,?,?);");
            prStatement.setString(1, user.getName());
            prStatement.setString(2, getHashPassFromPass(user.getPassword()));
            prStatement.setString(3, user.getLogin());
            prStatement.executeUpdate();
//            statement.executeUpdate("INSERT Users(name, password, role_level, login) VALUES" +
//                    "('" + user.getName() + "','" + user.getPassword() + "','simple','" + user.getLogin() + "');");
            notifycationLabel.setText("???????????????????????? ?????????????? ??????????????????????????????!");
            EnterButtonToUserPage.setVisible(true);

        } catch (SQLException | NoSuchAlgorithmException exception) {
            notifycationLabel.setText("???????????????????????? ?? ?????????? ?????????????? ?????? ????????! ?????????????? ????????????!");
        }
    }

}
