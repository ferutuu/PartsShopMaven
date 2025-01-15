package org.ferutuu.partsshop;

import Components.Component;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

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

    public CartController() {} // Make the constructor public

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
        // Implement checkout logic here
        System.out.println("Checkout clicked!");
    }
}