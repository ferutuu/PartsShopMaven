package org.ferutuu.partsshop;

import javafx.fxml.FXML;
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

        System.out.println("Attempting login with username: " + username);

        if (users.containsKey(username) && users.get(username).equals(password)) {
            currentUser = username; // Set the current logged-in user
            System.out.println("Login successful for user: " + username);
            MainController mainController = (MainController) ((Stage) usernameField.getScene().getWindow()).getUserData();
            if (mainController != null) {
                mainController.loadScreen("ComponentsScreen.fxml");
            } else {
                System.out.println("MainController is null");
            }
        } else {
            System.out.println("Login failed for user: " + username);
        }
    }

    @FXML
    private void handleRegisterButtonAction() {
        MainController mainController = (MainController) ((Stage) usernameField.getScene().getWindow()).getUserData();
        mainController.loadScreen("RegisterScreen.fxml");
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