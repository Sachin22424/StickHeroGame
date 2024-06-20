package com.example.stickheroapplication;

import javafx.animation.*;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Line;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Objects;
import java.util.Random;

public class Controller {

    public Label gameOverLabel;
    @FXML
    private Pane gameScreen;

    @FXML
    private Label score;
    @FXML
    private ImageView stickHero;

    private boolean charRight = true;
    private final double maxDist = 150.0;
    private ArrayList<Rectangle> platforms = new ArrayList<>();
    private Rectangle prevPlatform;
    private Rectangle startRect;
    private double angle = 270.0;

    private boolean isGrowing = false;
    private boolean translate = false;
    private Line stick;
    private double stickLength = 0.0;
    private final double charfixing = 55.0;
    private final double minDist = 30.0;
   

    private AnimationTimer stickExtension;

    private Timeline timeline;

    private Pillars platformGenerator = new Pillars();

    Random random = new Random();

    @FXML
    public void initialize() {
        Rectangle initPlatform = platformGenerator.initRect();
        gameScreen.getChildren().add(initPlatform);
        prevPlatform = initPlatform;
        startRect = initPlatform;
        platforms.add(initPlatform);
        startRect();
        gameScreen.setOnMousePressed(event -> {
            if (!translate && !isGrowing) {
                isGrowing = true;
                handleMousePressed();
            }
        });
        gameScreen.setOnMouseReleased(event -> {
            if (isGrowing) {
                isGrowing = false;
                handleMouseReleased();
            }
        });
    }

    private void movePlayerOnStick(Rectangle stick) {
        double stickEndX = stick.getLayoutX() + stick.getWidth();

        // Check if the character is within the bounds of the stick
//        if (Hero.getLayoutX() < stickEndX) {
//            double newCharacterX = Hero.getLayoutX() + stick.getWidth() - 10;
            double characterY = 0;

            // Translate the character to the end of the stick
        Node Hero = null;
        TranslateTransition translateCharacter = new TranslateTransition(Duration.millis(500), Hero);
        double newCharacterX = 0;
        translateCharacter.setToX(newCharacterX);
            translateCharacter.setToY(characterY);

            translateCharacter.setOnFinished(event -> {
                // You can add any logic here that should happen after the character reaches the end of the stick
                // For example, checking collision with the next pillar and generating the next pillar
                checkAndGenerateNextPillar(stick);
            });

            translateCharacter.play();
        }

    private void checkAndGenerateNextPillar(Rectangle stick) {
    }

    private void startRect() {
        javafx.animation.KeyFrame keyFrame = new javafx.animation.KeyFrame(
                javafx.util.Duration.seconds(0.1),
                event -> generatePlatform()
        );

        timeline = new javafx.animation.Timeline(keyFrame);
        timeline.setCycleCount(javafx.animation.Animation.INDEFINITE);
        timeline.play();
    }

    private void stopRect() {
        //stop the platform generator
        timeline.stop();
    }

    private void generatePlatform() {
        Rectangle platform = platformGenerator.generatePlatform();
        double minLayoutX = prevPlatform.getLayoutX() + prevPlatform.getWidth() + minDist;
        double maxLayoutX = prevPlatform.getLayoutX() + prevPlatform.getWidth() + maxDist;
        double layoutX = random.nextDouble() * (maxLayoutX - minLayoutX) + minLayoutX;
        platform.setLayoutX(layoutX);
        gameScreen.getChildren().add(platform);
        platforms.add(platform);
        prevPlatform = platform;
    }

    private void extendStick(Rectangle stick) {
        // Add logic to extend the stick while the mouse is pressed
//        if (extendingStick) {
            // Increase the stick width based on the extension speed
//            stick.setWidth(stick.getWidth() + EXTEND_SPEED);
        
    }

    public void handleMousePressed() {
        double pivotX = startRect.getLayoutX() + startRect.getWidth();
        double pivotY = startRect.getLayoutY();
        stick = new Line(pivotX, pivotY, pivotX, pivotY);
        stick.setStroke(Color.BLACK);
        stick.setStrokeWidth(4.0);
        gameScreen.getChildren().add(stick);

        final long[] startTime = { System.nanoTime() };

        stickExtension = new AnimationTimer() {
            @Override
            public void handle(long now) {
                double elapsedTime = (now - startTime[0]) / 1e9;
                if (elapsedTime > 0.1) {
                    stickLength += 10;
                    stick.setEndY(stick.getStartY() - stickLength);
                    startTime[0] = System.nanoTime();
                }
            }
        };

        // Toggle character orientation when the stick is growing
        charRight = !charRight;
//        setCharacterOrientation();
        stickExtension.start();
    }



    public void handleMouseReleased() {
        //stop the animation timer
        stickExtension.stop();
        rotateStick();
    }


    private void rotateStick() {
        //rotate stick by 90 degrees by adding setX and setY using timeline
        double centerX = stick.getStartX();
        double centerY = stick.getStartY();

        Timeline timeline = new Timeline(new javafx.animation.KeyFrame(Duration.millis(40), e -> {
            angle += 5;

            double angleInRadians = Math.toRadians(angle);
            double endX = centerX + stickLength * Math.cos(angleInRadians);
            double endY = centerY + stickLength * Math.sin(angleInRadians);

            stick.setStartX(centerX);
            stick.setStartY(centerY);
            stick.setEndX(endX);
            stick.setEndY(endY);

        }));
        timeline.setCycleCount(18);
        timeline.play();
        //pause till stick animation completes
        timeline.setOnFinished(event -> {
            checkCollide();
        });
    }

    private boolean checkCollisions2(Rectangle stick, Rectangle nextPillar) {

        double stickEndX = stick.getLayoutX() + stick.getWidth();
        double nextPillarStartX = nextPillar.getLayoutX();

        // Check if the stick collides with the next pillar
        return stickEndX >= nextPillarStartX && stickEndX <= nextPillarStartX;
    }

    public void exit(ActionEvent event) throws IOException {
//        HelloApplication h = new HelloApplication();
//        h.changeScene("exit.fxml");
    }

    private void checkCollide() {
        //check if stick is long enough to reach the next platform
        //if it is, move the character to the next platform
        //if it is not, end the game
        double startRectEndX = startRect.getLayoutX() + startRect.getWidth();
        double nextPlatformStartX = platforms.get(platforms.indexOf(startRect) + 1).getLayoutX();
        double nextPlatformEndX = nextPlatformStartX + platforms.get(platforms.indexOf(startRect) + 1).getWidth();
        if (stickLength >= nextPlatformStartX - startRectEndX && stickLength <= nextPlatformEndX - startRectEndX) {
            nextRect();
        } else {
            endGame();
        }
    }

    private void nextRect() {
        translate = true;
        Rectangle previousPlatform = startRect;
        int nextPlatformIndex = platforms.indexOf(startRect) + 1;
        startRect = platforms.get(nextPlatformIndex);
        //Animate the character to move to the next platform
        double characterX = stickHero.getLayoutX();
        double characterEndX = startRect.getLayoutX() + startRect.getWidth() - charfixing;
        TranslateTransition tt = new TranslateTransition(Duration.millis(2000), stickHero);
        tt.setToX(characterEndX - characterX);
        tt.play();
        tt.setOnFinished(event -> {
            translate = false;
            if (score.getText().equals("00")){
                score.setText("01");
            } //check if score is less than or equal to 09
            else if (Integer.parseInt(score.getText()) <= 9) {
                int currScore = Integer.parseInt(score.getText());
                currScore++;
                score.setText(String.format("%02d", currScore)); // 2 digits
            } else{
                int currScore = Integer.parseInt(score.getText());
                currScore++;
                score.setText(currScore + "");
            }
            gameScreen.getChildren().remove(stick);
            stickLength = 0.0;
            angle = 270.0;
            double shiftAmount = startRect.getLayoutX() - previousPlatform.getLayoutX();
            stickHero.setLayoutX(stickHero.getLayoutX() - shiftAmount);
            for (Rectangle platform : platforms) {
                platform.setLayoutX(platform.getLayoutX() - shiftAmount);
            }
        });
    }
    private void endGame() {
        stopRect();
        //move player till end of stick and then fall
        double characterX = stickHero.getLayoutX();
        TranslateTransition tt = new TranslateTransition(Duration.millis(500), stickHero);
        tt.setToX(stick.getEndX() - characterX - 30);
        tt.play();
        tt.setOnFinished(event -> {
            gameScreen.getChildren().remove(stick);
            stickLength = 0.0;
            angle = 270.0;
            falls();
            displayGameOverMessage();
        });
    }
    private void displayGameOverMessage() {
//        soundManager.playGameOverSound();
        FadeTransition fadeTransition = new FadeTransition(Duration.seconds(1), gameOverLabel);
        fadeTransition.setFromValue(0.0);
        fadeTransition.setToValue(1.0);
        fadeTransition.play();

        PauseTransition pause = new PauseTransition(Duration.seconds(2)); // Adjust the duration as needed
        pause.setOnFinished(event -> switchToMainScreen());
        pause.play();
    }


    private void switchToMainScreen() {
        try {
            // Load the main screen from FXML
            Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("home-screen.fxml")));

            // Get the current stage
            Stage stage = (Stage) gameScreen.getScene().getWindow();

            // Set the main screen scene
            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.show();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private void generateRandomPillar2() {
        Random random = new Random();
        double minDistance = 70; // Set your minimum desired distance
        double maxDistance = 400; // Set your maximum desired distance
        double minPillarWidth = 50;
        double maxPillarWidth = 100;
        double distance = minDistance + (maxDistance - minDistance) * random.nextDouble();

        Node pillar = null;
        double newX = pillar.getLayoutX() + distance;

        Rectangle newPillar = new Rectangle();
        newPillar.setWidth(minPillarWidth + (maxPillarWidth - minPillarWidth) * random.nextDouble());
//        newPillar.setHeight(pillar.getHeight());
        newPillar.setLayoutX(newX);
        newPillar.setLayoutY(283);

//        gamePane.getChildren().add(newPillar);

        // Generate a stick after adding the new pillar
//        Rectangle stick = generateStick();

        // Update and drop the stick
//        updateStick(stick);
//        dropStick(stick);
    }


    private void falls() {
        double characterY = stickHero.getLayoutY();
        TranslateTransition tt = new TranslateTransition(Duration.millis(500), stickHero);
        tt.setToY(characterY + 250);
        tt.play();
        tt.setOnFinished(event -> {
//            soundManager.playPlayerFallSound();
        });
    }


}
