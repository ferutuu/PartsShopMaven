package org.ferutuu.partsshop;

import Components.Component;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class CartItemController {
    @FXML
    private ImageView componentImage;
    @FXML
    private Label nameLabel;
    @FXML
    private Label typeLabel;
    @FXML
    private Label priceLabel;

    public void setData(Component component) {

        nameLabel.setText(component.getName());
        typeLabel.setText(component.getType());
        priceLabel.setText("$" + component.getPrice().toString());

        String imagePath = getImagePathForType(component.getType());
        if (imagePath != null) {
            componentImage.setImage(new Image("file:" + imagePath));
        } else {
            componentImage.setVisible(false);
        }
    }

    private String getImagePathForType(String type) {
        switch (type.toLowerCase()) {
            case "cpu":
                return "src/main/resources/cpu.png";
            case "gpu":
                return "src/main/resources/gpu.png";
            case "ram":
                return "src/main/resources/ram.png";
            case "mb":
                return "src/main/resources/mb.png";
            default:
                return "src/main/resources/cpu.png";
        }
    }
}
