package org.ferutuu.partsshop;

import javafx.fxml.FXML;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

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
                System.out.println("User registered successfully!");
            } catch (IOException e) {
                e.printStackTrace();
            }
        } else {
            System.out.println("Username or password cannot be empty!");
        }
    }

    @FXML
    private void handleBackToLoginButtonAction() {
        MainController mainController = (MainController) usernameField.getScene().getWindow().getUserData();
        mainController.loadScreen("LoginScreen.fxml");
    }

    // Other methods...
}