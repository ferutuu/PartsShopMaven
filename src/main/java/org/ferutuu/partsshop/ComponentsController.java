package org.ferutuu.partsshop;

import Components.Component;
import Components.CPU;
import Components.GPU;
import Components.RAM;
import Components.MB;

import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class ComponentsController {
    @FXML
    private ComboBox<String> componentTypeComboBox;
    @FXML
    private ComboBox<String> componentNameComboBox;
    @FXML
    private Label componentDetails;

    private ObservableList<Component> components = FXCollections.observableArrayList();
    private CartController cartController = CartController.getInstance();

    @FXML
    public void initialize() {
        componentTypeComboBox.getSelectionModel().selectFirst();
        handleComponentTypeChange();
    }

    @FXML
    private void handleComponentTypeChange() {
        String selectedType = componentTypeComboBox.getValue();
        loadComponentNamesFromDatabase(selectedType);
        componentDetails.setText("");
    }

    private void loadComponentNamesFromDatabase(String type) {
        components.clear();
        componentNameComboBox.getItems().clear();
        String query = "";

        switch (type) {
            case "CPU":
                query = "SELECT * FROM CPU";
                break;
            case "GPU":
                query = "SELECT * FROM GPU";
                break;
            case "RAM":
                query = "SELECT * FROM RAM";
                break;
            case "MB":
                query = "SELECT * FROM MB";
                break;
        }

        try (Connection connection = DriverManager.getConnection("jdbc:sqlite:C:\\Users\\campi\\IdeaProjects\\PartsShopMaven\\parts.sqlite");
             Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery(query)) {

            while (resultSet.next()) {
                int id = resultSet.getInt("id");
                String name = resultSet.getString("name");
                BigDecimal price = resultSet.getBigDecimal("price");

                switch (type) {
                    case "CPU":
                        components.add(new CPU(id, name, type, price, resultSet.getInt("cores"), resultSet.getBigDecimal("clock_speed")));
                        break;
                    case "GPU":
                        components.add(new GPU(id, name, type, price, resultSet.getInt("vram"), resultSet.getBigDecimal("clock_speed")));
                        break;
                    case "RAM":
                        components.add(new RAM(id, name, type, price, resultSet.getInt("capacity"), resultSet.getInt("speed"), resultSet.getString("generation")));
                        break;
                    case "MB":
                        components.add(new MB(id, name, type, price, resultSet.getString("form_factor"), resultSet.getString("socket_type")));
                        break;
                }

                componentNameComboBox.getItems().add(name);
            }
        } catch (Exception e) {
            e.printStackTrace();
            showAlert("Error", "Failed to load components from database.");
        }
    }

    @FXML
    private void handleComponentNameChange() {
        String selectedName = componentNameComboBox.getValue();
        if (selectedName != null) {
            for (Component component : components) {
                if (component.getName().equals(selectedName)) {
                    displayComponentDetails(component);
                    break;
                }
            }
        }
    }

    private void displayComponentDetails(Component component) {
        StringBuilder details = new StringBuilder();
        details.append("Name: ").append(component.getName()).append("\n");

        if (component instanceof CPU) {
            CPU cpu = (CPU) component;
            details.append("Cores: ").append(cpu.getCores()).append("\n");
            details.append("Clock Speed: ").append(cpu.getClockSpeed()).append(" MHz\n");
        } else if (component instanceof GPU) {
            GPU gpu = (GPU) component;
            details.append("VRAM: ").append(gpu.getVram()).append(" GB\n");
            details.append("Clock Speed: ").append(gpu.getClockSpeed()).append(" MHz\n");
        } else if (component instanceof RAM) {
            RAM ram = (RAM) component;
            details.append("Capacity: ").append(ram.getCapacity()).append(" GB\n");
            details.append("Speed: ").append(ram.getSpeed()).append(" MHz\n");
            details.append("Generation: ").append(ram.getGeneration()).append("\n");
        } else if (component instanceof MB) {
            MB mb = (MB) component;
            details.append("Form Factor: ").append(mb.getFormFactor()).append("\n");
            details.append("Socket Type: ").append(mb.getSocketType()).append("\n");
        }

        details.append("Price: $").append(component.getPrice()).append("\n");

        componentDetails.setText(details.toString());
    }

    @FXML
    private void handleAddToCart() {
        String selectedName = componentNameComboBox.getValue();
        if (selectedName != null) {
            for (Component component : components) {
                if (component.getName().equals(selectedName)) {
                    cartController.addToCart(component);
                    showAlert("Success", "Added " + component.getName() + " to cart.");
                    break;
                }
            }
        }
    }

    @FXML
    private void handleViewCart() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/CartScreen.fxml"));
            // Cast to BorderPane since we know the root is a BorderPane (a Region)
            BorderPane root = loader.load();

            // Get the stage from any node in the current scene (example: componentTypeComboBox)
            Stage stage = (Stage) componentTypeComboBox.getScene().getWindow();

            // Bind the root’s preferred width and height to the stage’s dimensions.
            root.prefWidthProperty().bind(stage.widthProperty());
            root.prefHeightProperty().bind(stage.heightProperty());

            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.setTitle("Cart");
            stage.setMaximized(true);
        } catch (Exception e) {
            e.printStackTrace();
            showAlert("Error", "Failed to load cart screen.");
        }
    }



    private void showAlert(String title, String message) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }

}