package fitnesstracker.core;

import javafx.fxml.FXML;
import javafx.scene.control.Label;

public class HelloController {
    @FXML
    private Label welcomeText;

    @FXML
    protected void buttonClick() {
        welcomeText.setText("Press the button in fitness tracker");
    }
}