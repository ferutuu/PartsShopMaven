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

    /**
     * Set the data for the cart item.
     * You can modify this method to load an image based on your component.
     */
    public void setData(Component component) {
        nameLabel.setText(component.getName());
        typeLabel.setText(component.getType());
        priceLabel.setText("$" + component.getPrice().toString());

        // Optionally, set an image if available.
        // For example:
        // componentImage.setImage(new Image(getClass().getResourceAsStream("/images/" + component.getImageName())));
        // Otherwise, if no image is available, you can hide the ImageView:
        // componentImage.setVisible(false);
    }
}
