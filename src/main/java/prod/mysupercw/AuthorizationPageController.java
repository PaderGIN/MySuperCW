package prod.mysupercw;

import java.io.IOException;
import java.net.URL;
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

public class AuthorizationPageController implements Userable{

    User user;
    private Stage stage;
    private Scene scene;
    private Parent root;

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

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

    @FXML
    private void EnterCheckLoginPassword(ActionEvent event) {
        String loginStr = loginField.getText().trim();
        String password = passwordField.getText().trim();

    }



    @FXML
    void initialize() {

    }

    @Override
    public User getUser() {
        return user;
    }
}
