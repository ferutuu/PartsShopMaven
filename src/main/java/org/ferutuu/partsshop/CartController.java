package org.ferutuu.partsshop;

import Components.Component;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.Alert;
import javafx.scene.control.ListView;
import javafx.scene.layout.Region;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.scene.control.ListCell;
import javafx.util.Callback;


import java.math.BigDecimal;

public class CartController {
    @FXML
    private ListView<Component> cartListView;
    private static final ObservableList<Component> cartItems = FXCollections.observableArrayList();
    private static CartController instance;

    public static CartController getInstance() {
        return instance;
    }

    @FXML
    public void initialize() {
        instance = this;
        cartListView.setItems(cartItems);

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
                                Parent cellRoot = loader.load();
                                CartItemController controller = loader.getController();
                                controller.setData(component);
                                setGraphic(cellRoot);

                            } catch (Exception e) {
                                e.printStackTrace();
                                setText(component.toString());
                            }
                        }
                    }
                };
            }
        });
    }

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
