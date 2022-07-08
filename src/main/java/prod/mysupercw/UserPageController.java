package prod.mysupercw;

import Models.User;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class UserPageController {
    User authUser;

    private Stage stage;
    private Scene scene;
    private Parent parent;
    @FXML
    private Label countLabel;

    @FXML
    private Label loginLabel;

    @FXML
    private Label nameLabel;


    public void initAuthUser(User user) throws Exception {
        authUser = user;
        loginLabel.setText(authUser.getLogin());
        nameLabel.setText(authUser.getName());
        countLabel.setText(String.valueOf((countQuotesByUser(authUser)))); //тут скл-каунтер;
    }

    private static int countQuotesByUser(User user) throws Exception {
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


    @FXML
    private void switchToStart(ActionEvent event) throws IOException {
        parent = FXMLLoader.load(getClass().getResource("StartPage.fxml"));
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        scene = new Scene(parent);
        stage.setScene(scene);
        stage.show();
    }

}
