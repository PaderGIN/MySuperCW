package prod.mysupercw;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Label;
import javafx.scene.*;
import javafx.stage.Stage;

import java.io.IOException;

public class StartPageController {

    private Stage stage;
    private Scene scene;
    private Parent root;
    @FXML
    private Label welcomeText;


    @FXML
    private void switchToAuthorization(ActionEvent event) throws IOException {
        root = FXMLLoader.load(getClass().getResource("AuthorizationPage.fxml"));
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    private void switchToGuestMode(ActionEvent event) throws IOException {
        root = FXMLLoader.load(getClass().getResource("GuestPage.fxml"));
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    private void switchToRegistration(ActionEvent event) throws IOException {
        root = FXMLLoader.load(getClass().getResource("RegistrationPage.fxml"));
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }
}