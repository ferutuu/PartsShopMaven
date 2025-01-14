package org.ferutuu.partsshop;

import Components.Component;
import Components.CPU;
import Components.GPU;
import Components.MB;
import Components.RAM;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class ComponentsController {
    @FXML
    private ComboBox<String> componentTypeComboBox;

    @FXML
    private TableView<Component> componentsTable;

    @FXML
    public void initialize() {
        // Set default selection
        componentTypeComboBox.getSelectionModel().selectFirst();
        handleComponentTypeChange();
    }

    @FXML
    private void handleComponentTypeChange() {
        String selectedType = componentTypeComboBox.getValue();
        updateTableColumns(selectedType);
        loadComponentsFromDatabase(selectedType);
    }

    private void updateTableColumns(String type) {
        componentsTable.getColumns().clear();

        TableColumn<Component, Integer> idColumn = new TableColumn<>("ID");
        idColumn.setCellValueFactory(new PropertyValueFactory<>("id"));
        componentsTable.getColumns().add(idColumn);

        TableColumn<Component, String> nameColumn = new TableColumn<>("Name");
        nameColumn.setCellValueFactory(new PropertyValueFactory<>("name"));
        componentsTable.getColumns().add(nameColumn);

        TableColumn<Component, BigDecimal> priceColumn = new TableColumn<>("Price");
        priceColumn.setCellValueFactory(new PropertyValueFactory<>("price"));
        componentsTable.getColumns().add(priceColumn);

        switch (type) {
            case "CPU":
                TableColumn<Component, Integer> coresColumn = new TableColumn<>("Cores");
                coresColumn.setCellValueFactory(new PropertyValueFactory<>("cores"));
                componentsTable.getColumns().add(coresColumn);

                TableColumn<Component, BigDecimal> clockSpeedColumn = new TableColumn<>("Clock Speed");
                clockSpeedColumn.setCellValueFactory(new PropertyValueFactory<>("clockSpeed"));
                componentsTable.getColumns().add(clockSpeedColumn);
                break;
            case "GPU":
                TableColumn<Component, Integer> vramColumn = new TableColumn<>("VRAM");
                vramColumn.setCellValueFactory(new PropertyValueFactory<>("vram"));
                componentsTable.getColumns().add(vramColumn);

                TableColumn<Component, BigDecimal> gpuClockSpeedColumn = new TableColumn<>("Clock Speed");
                gpuClockSpeedColumn.setCellValueFactory(new PropertyValueFactory<>("clockSpeed"));
                componentsTable.getColumns().add(gpuClockSpeedColumn);
                break;
            case "RAM":
                TableColumn<Component, Integer> capacityColumn = new TableColumn<>("Capacity");
                capacityColumn.setCellValueFactory(new PropertyValueFactory<>("capacity"));
                componentsTable.getColumns().add(capacityColumn);

                TableColumn<Component, Integer> speedColumn = new TableColumn<>("Speed");
                speedColumn.setCellValueFactory(new PropertyValueFactory<>("speed"));
                componentsTable.getColumns().add(speedColumn);

                TableColumn<Component, String> generationColumn = new TableColumn<>("Generation");
                generationColumn.setCellValueFactory(new PropertyValueFactory<>("generation"));
                componentsTable.getColumns().add(generationColumn);
                break;
            case "MB":
                TableColumn<Component, String> formFactorColumn = new TableColumn<>("Form Factor");
                formFactorColumn.setCellValueFactory(new PropertyValueFactory<>("formFactor"));
                componentsTable.getColumns().add(formFactorColumn);

                TableColumn<Component, String> socketTypeColumn = new TableColumn<>("Socket Type");
                socketTypeColumn.setCellValueFactory(new PropertyValueFactory<>("socketType"));
                componentsTable.getColumns().add(socketTypeColumn);
                break;
        }
    }

    private void loadComponentsFromDatabase(String type) {
        ObservableList<Component> components = FXCollections.observableArrayList();
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

            System.out.println("Query executed: " + query);

            while (resultSet.next()) {
                int id = resultSet.getInt("id");
                String name = resultSet.getString("name");
                BigDecimal price = resultSet.getBigDecimal("price");

                System.out.println("Component found: ID=" + id + ", Name=" + name + ", Price=" + price);

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
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        System.out.println("Components loaded: " + components.size());
        componentsTable.setItems(components);
    }
}