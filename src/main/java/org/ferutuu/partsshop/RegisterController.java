package org.ferutuu.partsshop;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;

public class RegisterController {
    @FXML
    private TextField usernameField;

    @FXML
    private PasswordField passwordField;

    @FXML
    private void handleRegisterButtonAction() {
        String username = usernameField.getText();
        String password = passwordField.getText();

        LoginController.registerUser(username, password);

        try {
            Parent root = FXMLLoader.load(getClass().getResource("/LoginScreen.fxml"));
            Stage stage = (Stage) usernameField.getScene().getWindow();
            stage.setScene(new Scene(root, 800, 800));
            stage.setTitle("Login Screen");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}