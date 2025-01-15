package org.ferutuu.partsshop;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.*;
import java.util.HashMap;
import java.util.Map;

public class LoginController {
    @FXML
    private TextField usernameField;

    @FXML
    private PasswordField passwordField;

    private static final String USERS_FILE = "users.txt";
    private static Map<String, String> users = new HashMap<>();
    private static String currentUser;

    public LoginController() {
        loadUsers();
    }

    @FXML
    private void handleLoginButtonAction() {
        String username = usernameField.getText();
        String password = passwordField.getText();

        if (users.containsKey(username) && users.get(username).equals(password)) {
            currentUser = username; // Set the current logged-in user
            try {
                Parent root = FXMLLoader.load(getClass().getResource("/ComponentsScreen.fxml"));
                Stage stage = (Stage) usernameField.getScene().getWindow();
                stage.setScene(new Scene(root, 800, 800));
                stage.setTitle("Components");
            } catch (IOException e) {
                e.printStackTrace();
            }
        } else {
            System.out.println("Login failed!");
        }
    }

    @FXML
    private void handleRegisterButtonAction() {
        try {
            Parent root = FXMLLoader.load(getClass().getResource("/RegisterScreen.fxml"));
            Stage stage = (Stage) usernameField.getScene().getWindow();
            stage.setScene(new Scene(root, 800, 800));
            stage.setTitle("Register");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static String getCurrentUser() {
        return currentUser;
    }

    private void loadUsers() {
        try (BufferedReader reader = new BufferedReader(new FileReader(USERS_FILE))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(",");
                if (parts.length == 2) {
                    users.put(parts[0], parts[1]);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // Other methods...
}