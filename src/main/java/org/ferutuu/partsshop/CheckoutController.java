package org.ferutuu.partsshop;

import Components.Component;

import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.List;

public class CheckoutController {
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
    @FXML
    private Label totalPriceLabel;

    private BigDecimal totalPrice;

    public void setCartItems(ObservableList<Component> cartItems) {
        cartTable.setItems(cartItems);
    }

    public void setTotalPrice(BigDecimal totalPrice) {
        this.totalPrice = totalPrice;
        totalPriceLabel.setText("Total Price: $" + totalPrice);
    }

    @FXML
    public void initialize() {

        idColumn.setCellValueFactory(new PropertyValueFactory<>("id"));
        nameColumn.setCellValueFactory(new PropertyValueFactory<>("name"));
        typeColumn.setCellValueFactory(new PropertyValueFactory<>("type"));
        priceColumn.setCellValueFactory(new PropertyValueFactory<>("price"));

        cartTable.widthProperty().addListener((obs, oldWidth, newWidth) -> {
            double tableWidth = newWidth.doubleValue();
            idColumn.setPrefWidth(tableWidth * 0.1);
            nameColumn.setPrefWidth(tableWidth * 0.4);
            typeColumn.setPrefWidth(tableWidth * 0.2);
            priceColumn.setPrefWidth(tableWidth * 0.3);
        });
    }

    @FXML
    private void handleConfirmPurchase() {
        showAlert("Success", "Order placed successfully.");

        String username = LoginController.getCurrentUser();
        saveOrderDetails(username, cartTable.getItems(), totalPrice);
    }

    private void saveOrderDetails(String username, List<Component> cartItems, BigDecimal totalPrice) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter("orders.txt", true))) {
            writer.write("User: " + username);
            writer.newLine();
            writer.write("Items:");
            writer.newLine();
            for (Component item : cartItems) {
                writer.write("ID: " + item.getId() + ", Name: " + item.getName() + ", Type: " + item.getType() + ", Price: $" + item.getPrice());
                writer.newLine();
            }
            writer.write("Total Price: $" + totalPrice);
            writer.newLine();
            writer.write("-------------------");
            writer.newLine();
        } catch (IOException e) {
            e.printStackTrace();
            showAlert("Error", "Failed to save order details.");
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
