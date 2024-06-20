package com.example.stickheroapplication;

public class SaveGame {
    private String playerName;
    private int score;

    // Constructors, getters, and setters can be added as needed

    // Example constructor
    public SaveGame(String playerName, int score) {
        this.playerName = playerName;
        this.score = score;
    }

    // Example getter for playerName
    public String getPlayerName() {
        return playerName;
    }

    // Example setter for playerName
    public void setPlayerName(String playerName) {
        this.playerName = playerName;
    }

    // Example getter for score
    public int getScore() {
        return score;
    }

    // Example setter for score
    public void setScore(int score) {
        this.score = score;
    }
}
