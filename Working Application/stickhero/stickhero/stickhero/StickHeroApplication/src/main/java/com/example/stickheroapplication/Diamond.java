package com.example.stickheroapplication;


import javafx.scene.image.Image;

public class Diamond {
    private boolean hasBeenCollected;
    private int height;
    private int width;

    // Default constructor
    public Diamond() {
        this.hasBeenCollected = false;
        this.height = 150;
        this.width = 150;
    }

    // Getter for hasBeenCollected
    public boolean isHasBeenCollected() {
        return hasBeenCollected;
    }

    // Setter for hasBeenCollected
    public void setHasBeenCollected(boolean hasBeenCollected) {
        this.hasBeenCollected = hasBeenCollected;
    }

    // Getter for height
    public int getHeight() {
        return height;
    }

    // Setter for height
    public void setHeight(int height) {
        this.height = height;
    }

    // Getter for width
    public int getWidth() {
        return width;
    }

    // Setter for width
    public void setWidth(int width) {
        this.width = width;
    }
}
