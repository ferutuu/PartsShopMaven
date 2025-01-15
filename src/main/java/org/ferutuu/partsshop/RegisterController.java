package org.ferutuu.partsshop;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
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
        try {
            Parent root = FXMLLoader.load(getClass().getResource("/LoginScreen.fxml"));
            Stage stage = (Stage) usernameField.getScene().getWindow();
            stage.setScene(new Scene(root, 800, 800));
            stage.setTitle("Login");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // Other methods...
}