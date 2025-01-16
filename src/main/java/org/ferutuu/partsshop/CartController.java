package org.ferutuu.partsshop;

import Components.Component;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.math.BigDecimal;

public class CartController {
    @FXML
    private TableView<Component> cartTable;
    @FXML
    private TableColumn<Component, Integer> idColumn;
    @FXML
    private TableColumn<Component, String> nameColumn;
    @FXML
    private TableColumn<Component, String> typeColumn;
    @FXML
    private TableColumn<Component, BigDecimal> priceColumn;

    private ObservableList<Component> cartItems = FXCollections.observableArrayList();

    private static CartController instance;

    public static CartController getInstance() {
        if (instance == null) {
            instance = new CartController();
        }
        return instance;
    }

    @FXML
    public void initialize() {
        idColumn.setCellValueFactory(new PropertyValueFactory<>("id"));
        nameColumn.setCellValueFactory(new PropertyValueFactory<>("name"));
        typeColumn.setCellValueFactory(new PropertyValueFactory<>("type"));
        priceColumn.setCellValueFactory(new PropertyValueFactory<>("price"));

        cartTable.setItems(cartItems);
    }

    public void addToCart(Component component) {
        cartItems.add(component);
    }

    public ObservableList<Component> getCartItems() {
        return cartItems;
    }

    @FXML
    private void handleCheckout() {
        BigDecimal totalPrice = cartItems.stream().map(Component::getPrice).reduce(BigDecimal.ZERO, BigDecimal::add);

        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/CheckoutScreen.fxml"));
            Parent root = loader.load();
            CheckoutController checkoutController = loader.getController();
            checkoutController.setCartItems(cartItems);
            checkoutController.setTotalPrice(totalPrice);
            Stage stage = (Stage) cartTable.getScene().getWindow();
            stage.setScene(new Scene(root, 800, 800));
            stage.setTitle("Checkout");

        } catch (Exception e) {
            e.printStackTrace();
            showAlert("Error", "Failed to load Checkout screen.");
        }
    }

    @FXML
    private void handleBackToComponents() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/ComponentsScreen.fxml"));
            Parent root = loader.load();
            Stage stage = (Stage) cartTable.getScene().getWindow();
            stage.setScene(new Scene(root, 800, 800));
            stage.setTitle("Components");
        } catch (Exception e) {
            e.printStackTrace();
            showAlert("Error", "Failed to load Components screen.");
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