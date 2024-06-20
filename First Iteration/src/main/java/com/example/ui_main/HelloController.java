package com.example.ui_main;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;

import java.io.IOException;

public class HelloController {
    @FXML
    private Label welcomeText;

    @FXML
    private Button button;
    @FXML
    private ImageView Hero;



    public void play(ActionEvent event) throws IOException {
    HelloApplication h = new HelloApplication();
    h.changeScene("afterscene.fxml");
    }



    @FXML
    protected void onHelloButtonClick() {
        welcomeText.setText("Welcome to JavaFX Application!");
    }
}