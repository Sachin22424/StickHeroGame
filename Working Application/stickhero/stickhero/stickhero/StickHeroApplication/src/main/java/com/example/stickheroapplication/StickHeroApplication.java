package com.example.stickheroapplication;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import java.io.IOException;
import java.util.Objects;

public class StickHeroApplication extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        Parent root = FXMLLoader.load(Objects.requireNonNull(StickHeroApplication.class.getResource("home-screen.fxml")));
        Scene scene = new Scene(root);
        stage.setTitle("Stick Hero");
        stage.getIcons().add(new Image(Objects.requireNonNull(StickHeroApplication.class.getResourceAsStream("images/character.png"))));
        stage.setScene(scene);
        stage.show();
        stage.setResizable(false);

        stage.setOnCloseRequest(e -> {
            e.consume();
            stage.close();
        });
    }

    public void start2(Stage stage) throws IOException {
//        stg = stage;
//        stage.setResizable(false);
//        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("hello-view.fxml"));
//        Scene scene = new Scene(fxmlLoader.load(), 600, 400);
//        stage.setTitle("STICK HERO");
//        stage.setScene(scene);
//        stage.show();
    }

    public void changeScene(String fxml) throws IOException{
        Parent pane = FXMLLoader.load(getClass().getResource(fxml));
//        stg.getScene().setRoot(pane);
    }

    public static void main(String[] args) {
        launch(args);
    }
}