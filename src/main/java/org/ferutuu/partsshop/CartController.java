package org.ferutuu.partsshop;

import Components.Component;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.stage.Stage;
import javafx.util.Callback;

import java.math.BigDecimal;

public class CartController {
    @FXML
    private ListView<Component> cartListView;

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
        // Set the items in the ListView
        cartListView.setItems(cartItems);

        // Set a custom cell factory to load our CartItem.fxml for each cell.
        cartListView.setCellFactory(new Callback<ListView<Component>, ListCell<Component>>() {
            @Override
            public ListCell<Component> call(ListView<Component> listView) {
                return new ListCell<Component>() {
                    @Override
                    protected void updateItem(Component component, boolean empty) {
                        super.updateItem(component, empty);
                        if (empty || component == null) {
                            setText(null);
                            setGraphic(null);
                        } else {
                            try {
                                FXMLLoader loader = new FXMLLoader(getClass().getResource("/CartItem.fxml"));
                                Parent cartItemRoot = loader.load();
                                CartItemController controller = loader.getController();
                                controller.setData(component);
                                setGraphic(cartItemRoot);
                            } catch (Exception e) {
                                e.printStackTrace();
                            }
                        }
                    }
                };
            }
        });
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
            Stage stage = (Stage) cartListView.getScene().getWindow();
            stage.setScene(new Scene(root));
            stage.setTitle("Checkout");
            Platform.runLater(() -> stage.setMaximized(true));
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
            Stage stage = (Stage) cartListView.getScene().getWindow();
            stage.setScene(new Scene(root));
            stage.setTitle("Components");
            Platform.runLater(() -> stage.setMaximized(true));
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
