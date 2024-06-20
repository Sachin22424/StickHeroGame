package com.example.stickheroapplication;

import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

import java.util.Random;

public class Pillars extends Rectangle {

    private final Random random = new Random();
    private final double screenWidth = 600.0;
    private static final double PLATFORM_HEIGHT = 100.0;
    private static final double minWidth = 20.0;
    private static final double maxWidth = 100.0;
    private static final double layoutY = 300.0;
    private static final double minLayoutX = 0.0;
    private static final double maxLayoutX = 600.0;


    public Rectangle initRect() {
        Rectangle platform = new Rectangle();
        platform.setLayoutY(layoutY);
        platform.setLayoutX(50.0);
        platform.setWidth(90.0);
        platform.setHeight(PLATFORM_HEIGHT);
        platform.setFill(Color.BLACK);
        return platform;
    }

    public Rectangle generatenewPillar() {
        Rectangle stick = new Rectangle();
        stick.setWidth(2); // Set your desired stick width
        stick.setHeight(7);

        // Position the stick at the end of the current pillar
//        stick.setLayoutX(pillar.getLayoutX() + pillar.getWidth());
//        stick.setLayoutY(pillar.getLayoutY());

//        gamePane.getChildren().add(stick);

        return stick;
    }

    public Rectangle generatePlatform() {
        Rectangle platform = new Rectangle();
        platform.setLayoutY(layoutY);
        platform.setWidth(random.nextDouble() * (maxWidth - minWidth) + minWidth);
        platform.setHeight(PLATFORM_HEIGHT);
        platform.setFill(Color.BLACK);
        return platform;
    }

    public void updatePillar(Rectangle stick) {
        stick.setWidth(2); // Set your desired stick width
        stick.setHeight(6);

        // Position the stick at the end of the current pillar
//        stick.setLayoutX(pillar.getLayoutX() + pillar.getWidth());
//        stick.setLayoutY(pillar.getLayoutY());
    }

    public void removePillar(Rectangle stick) {
        double endY = stick.getLayoutY() + stick.getHeight();

        double stickLength = stick.getHeight();

        double midPointY = (stick.getLayoutY() + endY) / 2;

//        RotateTransition rotateTransition = new RotateTransition(Duration.millis(500), stick);
//        rotateTransition.setByAngle(90);
//
//        TranslateTransition translateTransition = new TranslateTransition(Duration.millis(500), stick);
//        translateTransition.setByY(midPointY - endY);
//        translateTransition.setByX(-(stickLength / 2));
//
//        ParallelTransition parallelTransition = new ParallelTransition(rotateTransition, translateTransition);
//
//        parallelTransition.play();
//        parallelTransition.setOnFinished(event -> {movePlayerOnStick(stick);});
    }
}
