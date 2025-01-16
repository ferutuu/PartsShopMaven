package org.ferutuu.partsshop;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Alert;
import javafx.scene.layout.StackPane;
import java.io.IOException;
import java.util.Collections;

public class MainController {
    @FXML
    private StackPane mainPane;

    @FXML
    public void initialize() {
        loadScreen("LoginScreen.fxml");
    }

    public void loadScreen(String fxmlFile) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/" + fxmlFile));
            mainPane.getChildren().setAll(Collections.singletonList(loader.load()));
        } catch (IOException e) {
            e.printStackTrace();
            showAlert("Error", "An error occurred while loading the screen.");

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