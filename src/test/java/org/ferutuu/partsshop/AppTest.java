package org.ferutuu.partsshop;

import static org.junit.jupiter.api.Assertions.assertNotNull;

import javafx.stage.Stage;
import org.junit.jupiter.api.Test;

public class AppTest {

    private App app;

    public void start(Stage stage) throws Exception {
        app = new App();
        app.start(stage);
    }


}