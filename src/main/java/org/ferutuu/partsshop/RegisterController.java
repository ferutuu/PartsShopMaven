package org.ferutuu.partsshop;

import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

public class RegisterController {
    @FXML
    private TextField usernameField;

    @FXML
    private PasswordField passwordField;

    private static final String USERS_FILE = "users.txt";

    @FXML
    private void handleRegisterButtonAction() {
        String username = usernameField.getText();
        String password = passwordField.getText();

        if (!username.isEmpty() && !password.isEmpty()) {
            try (BufferedWriter writer = new BufferedWriter(new FileWriter(USERS_FILE, true))) {
                writer.write(username + "," + password);
                writer.newLine();
                showAlert("Success", "User registered successfully!");
            } catch (IOException e) {
                e.printStackTrace();
                showAlert("Error", "An error occurred while registering user.");
            }
        } else {
            showAlert("Error", "All fields must be filled out.");
        }
    }

    @FXML
    private void handleBackToLoginButtonAction() {
        MainController mainController = (MainController) usernameField.getScene().getWindow().getUserData();
        mainController.loadScreen("LoginScreen.fxml");
        Stage stage = (Stage) usernameField.getScene().getWindow();
        stage.setMaximized(true);
    }

    private void showAlert(String title, String message) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }

}