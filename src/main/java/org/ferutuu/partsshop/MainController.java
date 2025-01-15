package org.ferutuu.partsshop;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
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
        }
    }
}