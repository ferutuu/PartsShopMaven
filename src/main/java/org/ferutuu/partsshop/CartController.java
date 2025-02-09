package org.ferutuu.partsshop;

import Components.Component;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.Alert;
import javafx.scene.control.ListView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Region;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.util.Callback;


import java.math.BigDecimal;

public class CartController {
    @FXML
    private ListView<Component> cartListView;

    // Shared cart items list (static so it's shared across instances)
    private static final ObservableList<Component> cartItems = FXCollections.observableArrayList();

    // Static instance for getInstance() usage
    private static CartController instance;

    public static CartController getInstance() {
        return instance;
    }

    @FXML
    public void initialize() {
        // Set this instance for getInstance() usage (if needed)
        instance = this;
        // Set the shared cart items list
        cartListView.setItems(cartItems);

        // Set a custom cell factory to display each Component using CartItem.fxml
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
                                // Load the custom cell layout from CartItem.fxml
                                FXMLLoader loader = new FXMLLoader(getClass().getResource("/CartItem.fxml"));
                                Parent cellRoot = loader.load();

                                // Get the controller from the loaded FXML and pass the data
                                CartItemController controller = loader.getController();
                                controller.setData(component);

                                // Use the loaded layout as the graphic for this cell
                                setGraphic(cellRoot);
                            } catch (Exception e) {
                                e.printStackTrace();
                                // Fallback: if loading fails, show the default toString() value
                                setText(component.toString());
                            }
                        }
                    }
                };
            }
        });
    }

    // Use this static method to add components to the cart.
    public static void addToCart(Component component) {
        cartItems.add(component);
    }

    @FXML
    private void handleCheckout() {
        BigDecimal totalPrice = cartItems.stream()
                .map(Component::getPrice)
                .reduce(BigDecimal.ZERO, BigDecimal::add);
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/CheckoutScreen.fxml"));
            Parent root = loader.load();
            CheckoutController checkoutController = loader.getController();
            checkoutController.setCartItems(cartItems);
            checkoutController.setTotalPrice(totalPrice);

            Stage stage = (Stage) cartListView.getScene().getWindow();
            // Bind the new root's size to the stage's size if it's a Region
            if (root instanceof Region) {
                ((Region) root).prefWidthProperty().bind(stage.widthProperty());
                ((Region) root).prefHeightProperty().bind(stage.heightProperty());
            }
            Scene scene = new Scene(root, stage.getWidth(), stage.getHeight());
            stage.setScene(scene);
            stage.setTitle("Checkout");
            stage.setMaximized(true);
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
            if (root instanceof Region) {
                ((Region) root).prefWidthProperty().bind(stage.widthProperty());
                ((Region) root).prefHeightProperty().bind(stage.heightProperty());
            }
            Scene scene = new Scene(root, stage.getWidth(), stage.getHeight());
            stage.setScene(scene);
            stage.setTitle("Components");
            stage.setMaximized(true);
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
