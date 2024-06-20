//package com.example.ui_main;
//
//import javafx.event.ActionEvent;
//import javafx.fxml.FXML;
//import javafx.scene.control.Button;
//import javafx.scene.image.ImageView;
//import javafx.scene.layout.Pane;
//import javafx.scene.shape.Rectangle;
//
//import java.io.IOException;
//import java.util.Random;
//
//public class HelloController2 {
//    @FXML
//    private ImageView Hero;
//    @FXML
//    private Button pause_button;
//    @FXML
//    private Pane gamePane;
//
//    @FXML
//    private Rectangle pillar;
//
//    @FXML
//    public void initialize() {
//        double initialX = 50; // Set your desired initial X position
//        double initialY = 283; // Set your desired initial Y position
//        pillar.setLayoutX(initialX);
//        pillar.setLayoutY(initialY);
//
//        // Generate another pillar at a variable distance
//        generateRandomPillar();
//        // Generate a stick at the base of the character
//
//    }
//
//    private void generateRandomPillar() {
//        Random random = new Random();
//        double minDistance = 50; // Set your minimum desired distance
//        double maxDistance = 400;// Set your maximum desired distance
//        double minPillar = 50;
//        double maxPillar = 100;
//        double distance = minDistance + (maxDistance - minDistance) * random.nextDouble();
//
//        double newX = pillar.getLayoutX() + distance;
//
//        Rectangle newPillar = new Rectangle();
//        newPillar.setWidth(random.nextDouble()*50); // Set the width to be the same as the existing pillar
//        newPillar.setHeight(pillar.getHeight()); // Set the height to be the same as the existing pillar
//        newPillar.setLayoutX(newX);
//        newPillar.setLayoutY(283); // Set your desired Y position
//
//        gamePane.getChildren().add(newPillar);
//    }
//
//
//
//    public void exit(ActionEvent event) throws IOException {
//        HelloApplication h = new HelloApplication();
//        h.changeScene("exit.fxml");
//    }
//}



package com.example.ui_main;

import javafx.animation.*;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.shape.Rectangle;
import javafx.util.Duration;

import java.io.IOException;
import java.util.Random;

public class HelloController2 {
    @FXML
    private ImageView Hero;
    @FXML
    private Button pause_button;
    @FXML
    private Pane gamePane;

    @FXML
    private Rectangle pillar;

    private  boolean extendingStick = false;
    private double characterBaseY = 300; // Adjust this value based on your character's base position
    private Timeline extendingTimeline;
    private static final double EXTEND_SPEED = 2.0; // Adjust the speed of stick extension

    // ...
    @FXML
    public void initialize() {
        double initialX = 50; // Set your desired initial X position
        double initialY = 283; // Set your desired initial Y position
        pillar.setLayoutX(initialX);
        pillar.setLayoutY(initialY);

        // Generate another pillar at a variable distance
        generateRandomPillar();

        // Generate a stick at the base of the character
        Rectangle stick = generateStick();

        // Update and drop the stick
        updateStick(stick);
        dropStick(stick);

        gamePane.setOnMousePressed(event -> startExtendingStick(stick));
        gamePane.setOnMouseReleased(event -> stopExtendingStick(stick));


    }

    private void generateRandomPillar() {
        Random random = new Random();
        double minDistance = 50; // Set your minimum desired distance
        double maxDistance = 400; // Set your maximum desired distance
        double minPillarWidth = 50;
        double maxPillarWidth = 100;
        double distance = minDistance + (maxDistance - minDistance) * random.nextDouble();

        double newX = pillar.getLayoutX() + distance;

        Rectangle newPillar = new Rectangle();
        newPillar.setWidth(minPillarWidth + (maxPillarWidth - minPillarWidth) * random.nextDouble());
        newPillar.setHeight(pillar.getHeight());
        newPillar.setLayoutX(newX);
        newPillar.setLayoutY(283);

        gamePane.getChildren().add(newPillar);

        // Generate a stick after adding the new pillar
        Rectangle stick = generateStick();

        // Update and drop the stick
        updateStick(stick);
        dropStick(stick);
    }

    private Rectangle generateStick() {
        Rectangle stick = new Rectangle();
        stick.setWidth(2); // Set your desired stick width
        stick.setHeight(characterBaseY - pillar.getLayoutY());

        // Position the stick at the end of the current pillar
        stick.setLayoutX(pillar.getLayoutX() + pillar.getWidth());
        stick.setLayoutY(pillar.getLayoutY());

        gamePane.getChildren().add(stick);

        return stick;
    }

    private void updateStick(Rectangle stick) {
        stick.setWidth(2); // Set your desired stick width
        stick.setHeight(characterBaseY - pillar.getLayoutY());

        // Position the stick at the end of the current pillar
        stick.setLayoutX(pillar.getLayoutX() + pillar.getWidth());
        stick.setLayoutY(pillar.getLayoutY());
    }

    private void dropStick(Rectangle stick) {
        double endY = stick.getLayoutY() + stick.getHeight();

        double stickLength = stick.getHeight();

        double midPointY = (stick.getLayoutY() + endY) / 2;

        RotateTransition rotateTransition = new RotateTransition(Duration.millis(500), stick);
        rotateTransition.setByAngle(90);

        TranslateTransition translateTransition = new TranslateTransition(Duration.millis(500), stick);
        translateTransition.setByY(midPointY - endY);
        translateTransition.setByX(-(stickLength / 2));

        ParallelTransition parallelTransition = new ParallelTransition(rotateTransition, translateTransition);

        parallelTransition.play();
        parallelTransition.setOnFinished(event -> {movePlayerOnStick(stick);});
    }

    private void startExtendingStick(Rectangle stick) {
        extendingStick = true;

        extendingTimeline = new Timeline(new KeyFrame(Duration.millis(16), e -> extendStick(stick)));
        extendingTimeline.setCycleCount(Timeline.INDEFINITE);
        extendingTimeline.play();
    }


    private void extendStick(Rectangle stick) {
        // Add logic to extend the stick while the mouse is pressed
        if (extendingStick) {
            // Increase the stick width based on the extension speed
            stick.setWidth(stick.getWidth() + EXTEND_SPEED);
        }
    }

    private void stopExtendingStick(Rectangle stick) {
        extendingStick = false;

        if (extendingTimeline != null) {
            extendingTimeline.stop();
        }
        // Add logic to stop extending the stick when the mouse is released
        // For example, initiate the dropStick method
        dropStick(stick);
    }

    private void movePlayerOnStick(Rectangle stick) {
        // Implement the logic to move the player on the stick after it drops
        // You can add your game logic here
        double stickEndX = stick.getLayoutX() + stick.getWidth();

        // Check if the character is within the bounds of the stick
//        if (Hero.getLayoutX() > stick.getLayoutX() && Hero.getLayoutX() < stickEndX) {
            double newCharacterX = Hero.getLayoutX() + stick.getWidth();
            double characterY = 0;

            // Translate the character to the end of the stick
            TranslateTransition translateCharacter = new TranslateTransition(Duration.millis(500), Hero);
            translateCharacter.setToX(newCharacterX);
            translateCharacter.setToY(characterY);

            translateCharacter.setOnFinished(event -> {
                // Call the method to generate the next pillar and stick
//                generateRandomPillar();
            });

            translateCharacter.play();
//        } else {
            // The character missed the stick, handle game over logic here
            // You can reset the game or show a game over screen
//        }
    }

    public void exit(ActionEvent event) throws IOException {
        HelloApplication h = new HelloApplication();
        h.changeScene("exit.fxml");
    }
}
