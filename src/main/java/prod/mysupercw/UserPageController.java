package prod.mysupercw;

import Models.Roles;
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

import java.io.IOException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.regex.Pattern;

public class UserPageController {
    static User authUser;
    private Stage stage;
    private Scene scene;
    private Parent parent;
    @FXML
    private Button ChangeLogin;
    @FXML
    private Button ChangeName;
    @FXML
    private Button ChangePassword;
    @FXML
    private TextField NewLoginField;
    @FXML
    private TextField NewNameField;
    @FXML
    private TextField NewPasswordField;
    @FXML
    private Label countLabel;
    @FXML
    private Label loginLabel;
    @FXML
    private Label nameLabel;
    @FXML
    private Label statusLabel;


    public void initAuthUser(User user) throws Exception { //уже есть логин и пароль//upd name,id,role
        authUser = user;
        loginLabel.setText(authUser.getLogin());
        nameLabel.setText(getNameFromLogin(authUser));
        countLabel.setText(String.valueOf((countQuotesByUser(authUser))));          //тут скл-каунтер;

        authUser.setName(getNameFromLogin(authUser));
        authUser.setId(getIdFromLogin(authUser));
        authUser.setRole(getRoleFromLogin(authUser));
    }

    private Roles getRoleFromLogin(User authUser) throws SQLException {
        Roles ans = null;
        PreparedStatement statement = DBconnector
                .getDBConnection()
                .prepareStatement("SELECT role_level" +
                        " FROM Users" +
                        " WHERE Users.login = ? ;");
        statement.setString(1, authUser.getLogin());
        System.out.println(authUser.getLogin());
        statement.executeQuery();
        ResultSet resultSet = statement.getResultSet();
        if (resultSet.next()) {
            ans = Roles.valueOf(resultSet.getObject(1).toString());
        }
        return ans;
    }

    private int getIdFromLogin(User authUser) throws SQLException {
        int ans=0;
        PreparedStatement statement = DBconnector
                .getDBConnection()
                .prepareStatement("SELECT id" +
                        " FROM Users" +
                        " WHERE Users.login = ? ;");
        statement.setString(1, authUser.getLogin());
        System.out.println(authUser.getLogin());
        statement.executeQuery();
        ResultSet resultSet = statement.getResultSet();
        if (resultSet.next()) {
            ans = resultSet.getInt(1);
        }
        return ans;
    }

    private static int countQuotesByUser(User user) throws SQLException {
        int ans = 0;
        PreparedStatement statement = DBconnector
                .getDBConnection()
                .prepareStatement("SELECT COUNT(*) " +
                        "FROM Users JOIN Quotes on Users.id = Quotes.user_id" +
                        " WHERE Users.login = ? ;");
        statement.setString(1, user.getLogin());
        System.out.println(user.getLogin());
        statement.executeQuery();
        ResultSet resultSet = statement.getResultSet();
        if (resultSet.next()) {
            ans = resultSet.getInt(1);
        }
        return ans;
    }

    private static String getNameFromLogin(User user) throws SQLException {
        String ans = "";
        PreparedStatement statement = DBconnector
                .getDBConnection()
                .prepareStatement("SELECT name" +
                        " FROM Users" +
                        " WHERE Users.login = ? ;");
        statement.setString(1, user.getLogin());
        System.out.println(user.getLogin());
        statement.executeQuery();
        ResultSet resultSet = statement.getResultSet();
        if (resultSet.next()) {
            ans = resultSet.getString(1);
        }
        return ans;
    }

    private static String getPasswordFromLogin(User user) throws SQLException {
        String ans = "";
        PreparedStatement statement = DBconnector
                .getDBConnection()
                .prepareStatement("SELECT password" +
                        " FROM Users" +
                        " WHERE Users.login = ? ;");
        statement.setString(1, user.getLogin());
        System.out.println(user.getLogin());
        statement.executeQuery();
        ResultSet resultSet = statement.getResultSet();
        if (resultSet.next()) {
            ans = resultSet.getString(1);
        }
        return ans;
    }

    @FXML
    private void changeName(ActionEvent event) {
        String nameStr = NewNameField.getText().trim();
        if (nameStr.trim().equals("")) {
            statusLabel.setText("Все поля должны быть заполнены");
        } else if (!test(nameStr)) {
            statusLabel.setText("В поле имя не могут быть числа/спецсимволы!");
        } else {
            try {
                PreparedStatement prStatement;
                prStatement = DBconnector.getDBConnection().prepareStatement("UPDATE Users SET name = ? WHERE id = ? ;");
                prStatement.setString(1, NewNameField.getText());
                prStatement.setInt(2, authUser.getId());
                prStatement.executeUpdate();
                nameLabel.setText(NewNameField.getText());
                statusLabel.setText("Имя изменено на " + NewNameField.getText());
                NewNameField.setText("");
                authUser = new User(authUser.getId(), nameStr,authUser.getLogin(), authUser.getPassword(), authUser.getRole());
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }
    }

    @FXML
    void changeLogin(ActionEvent event) {
        String loginStr = NewLoginField.getText().trim();
        if (loginStr.trim().equals("")) {
            statusLabel.setText("Все поля должны быть заполнены");
        } else {
            try {
                PreparedStatement prStatement;
                prStatement = DBconnector.getDBConnection().prepareStatement("UPDATE Users SET login = ? WHERE id = ? ;");
                prStatement.setString(1, NewLoginField.getText());
                prStatement.setInt(2, authUser.getId());
                prStatement.executeUpdate();
                loginLabel.setText(NewLoginField.getText());
                statusLabel.setText("Логин изменен на " + NewLoginField.getText());
                NewLoginField.setText("");
                authUser = new User(authUser.getId(), authUser.getName(), NewLoginField.getText(), loginStr, authUser.getRole());
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }
    }

    @FXML
    void changePassword(ActionEvent event) {
        String passwordStr = NewPasswordField.getText().trim();
        if (passwordStr.trim().equals("")) {
            statusLabel.setText("Все поля должны быть заполнены");
        } else {
            try {
                PreparedStatement prStatement;
                prStatement = DBconnector.getDBConnection().prepareStatement("UPDATE Users SET password = ? WHERE id = ? ;");
                prStatement.setString(1, NewPasswordField.getText());
                prStatement.setInt(2, authUser.getId());
                prStatement.executeUpdate();
                statusLabel.setText("Пароль изменен на " + NewPasswordField.getText());
                NewPasswordField.setText("");
                authUser = new User(authUser.getId(), authUser.getName(), authUser.getLogin(), passwordStr, authUser.getRole());
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }
    }

    private static boolean test(String str) {
        String text = str;
        if (Pattern.matches("[a-zA-Zа-яА-Я .]+", text)) {
            return true;
        } else {
            return false;
        }
    }

    @FXML
    private void switchToStart(ActionEvent event) throws IOException {
        parent = FXMLLoader.load(getClass().getResource("StartPage.fxml"));
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        scene = new Scene(parent);
        stage.setScene(scene);
        stage.show();
    }

}
