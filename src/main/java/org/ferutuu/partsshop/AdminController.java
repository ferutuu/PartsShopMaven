package org.ferutuu.partsshop;

import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.stage.Stage;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.util.Arrays;
import java.util.List;

public class AdminController {
    @FXML
    private TextField nameField;
    @FXML
    private TextField typeField;
    @FXML
    private TextField priceField;
    @FXML
    private TextField manufacturerField;

    private static final List<String> VALID_TYPES = Arrays.asList("CPU", "GPU", "RAM", "MB");

    @FXML
    private void handleAddComponent() {
        String name = nameField.getText();
        String type = typeField.getText();
        String priceText = priceField.getText();
        String manufacturer = manufacturerField.getText();

        if (name.isEmpty() || type.isEmpty() || priceText.isEmpty() || manufacturer.isEmpty()) {
            showAlert("Error", "All fields must be filled out.");
            return;
        }

        if (!VALID_TYPES.contains(type)) {
            showAlert("Error", "Type must be one of the following: CPU, GPU, RAM, MB.");
            return;
        }

        if (!manufacturer.matches("[a-zA-Z\\s]+")) {
            showAlert("Error", "Manufacturer must contain only letters and spaces.");
            return;
        }

        BigDecimal price;
        try {
            price = new BigDecimal(priceText);
            if (price.compareTo(BigDecimal.ZERO) <= 0) {
                showAlert("Error", "Price must be a positive number.");
                return;
            }
        } catch (NumberFormatException e) {
            showAlert("Error", "Invalid price format.");
            return;
        }

        try (Connection connection = DriverManager.getConnection("jdbc:sqlite:C:\\Users\\Alex\\IdeaProjects\\PartsShopMaven\\parts.sqlite")) {
            String query = "INSERT INTO Components (name, type, price, manufacturer) VALUES (?, ?, ?, ?)";
            try (PreparedStatement statement = connection.prepareStatement(query)) {
                statement.setString(1, name);
                statement.setString(2, type);
                statement.setBigDecimal(3, price);
                statement.setString(4, manufacturer);
                statement.executeUpdate();
                showAlert("Success", "Component added successfully.");
            }
        } catch (Exception e) {
            e.printStackTrace();
            showAlert("Error", "Failed to add component.");
        }
    }

    @FXML
    private void handleBackToLogin() {
        MainController mainController = (MainController) ((Stage) nameField.getScene().getWindow()).getUserData();
        mainController.loadScreen("LoginScreen.fxml");
        Stage stage = (Stage) nameField.getScene().getWindow();
        stage.setMaximized(true);
    }

    private void showAlert(String title, String message) {
        Alert alert = new Alert(AlertType.INFORMATION);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }
}