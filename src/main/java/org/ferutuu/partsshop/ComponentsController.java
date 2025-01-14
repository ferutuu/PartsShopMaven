package org.ferutuu.partsshop;

import Components.Component;
import Components.CPU;
import Components.GPU;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;

import java.math.BigDecimal;

public class ComponentsController {
    @FXML
    private TableView<Component> componentsTable;

    @FXML
    private TableColumn<Component, Integer> idColumn;

    @FXML
    private TableColumn<Component, String> nameColumn;

    @FXML
    private TableColumn<Component, String> typeColumn;

    @FXML
    private TableColumn<Component, BigDecimal> priceColumn;

    @FXML
    public void initialize() {
        // Initialize the table columns and load data
        idColumn.setCellValueFactory(cellData -> cellData.getValue().getId().asObject());
        nameColumn.setCellValueFactory(cellData -> cellData.getValue().getName());
        typeColumn.setCellValueFactory(cellData -> cellData.getValue().getType());
        priceColumn.setCellValueFactory(cellData -> cellData.getValue().getPrice());

        // Load data into the table (this is just a placeholder, replace with actual data loading logic)
        componentsTable.setItems(FXCollections.observableArrayList(
                new CPU(1, "Intel i7", "CPU", new BigDecimal("300.00"), 8, new BigDecimal("3.6")),
                new GPU(2, "NVIDIA GTX 1080", "GPU", new BigDecimal("500.00"), 8, new BigDecimal("1.6"))
        ));
    }
}