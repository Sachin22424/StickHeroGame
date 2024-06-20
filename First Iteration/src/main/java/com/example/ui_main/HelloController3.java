package com.example.ui_main;

import javafx.event.ActionEvent;

import java.io.IOException;

public class HelloController3 {
    public void retry(ActionEvent event) throws IOException {
        HelloApplication h = new HelloApplication();
        h.changeScene("afterscene.fxml");
    }

    public void exit_back(ActionEvent event) throws IOException {
        HelloApplication h = new HelloApplication();
        h.changeScene("hello-view.fxml");
    }
}
