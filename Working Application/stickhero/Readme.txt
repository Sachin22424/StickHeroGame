
Stick Hero Application
Welcome to the Stick Hero Application! This is a simple JavaFX game where the player controls a character by extending a stick to cross platforms. The goal is to reach the next platform without falling.

Features
Gameplay: Extend the stick by clicking and holding the mouse, and release to drop the stick.
Scoring: Score points for successfully reaching the next platform.
Sound Effects: Enjoy sound effects for various actions in the game.
Exit Confirmation: A confirmation dialog is provided when attempting to exit the game.
Getting Started
Prerequisites: Make sure you have Java installed on your machine.

Run the Application: Execute the StickHeroApplication class to start the game.

Game Controls:

Click and hold the mouse to extend the stick.
Release the mouse to drop the stick and move the character.
Structure of the Project
com.example.stickheroapplication: Main package containing the application code.

StickHeroApplication: Main class to launch the application.
Controller: Controller class for the gameplay screen.
Diamond: Class representing a collectible diamond in the game.
Pillars: Class handling the generation of platforms in the game.
SaveGame: Class representing a saved game state.
SceneController: Controller class for the home screen.
Resources:

images: Folder containing image resources used in the application.
How to Play
Launch the game.
Click and hold the mouse to extend the stick.
Release the mouse to drop the stick and move the character.
Reach the next platform to score points.
Additional Notes
This project uses JavaFX for the graphical user interface.
Sound effects are incorporated to enhance the gaming experience.
The game features a confirmation dialog when attempting to exit.

Controller Class Explanation:

The Controller class is a crucial component in the Stick Hero Application. It serves as the controller for the gameplay screen, managing the game logic, user interactions, and transitions between different game states. Let's break down the key aspects of the Controller 

gameScreen: A Pane representing the main game screen where platforms and characters are displayed.
score: A Label displaying the current score of the player.
stickHero: An ImageView representing the character in the game.
Game State and Parameters

endGame(): Method to handle the end of the game, triggering character fall and displaying the game over message.
displayGameOverMessage(): Method to display the game over message with a fade transition.
switchToMainScreen(): Method to transition to the main screen after the game is over.

Pillars Class Explanation
The Pillars class in the Stick Hero Application is responsible for generating and handling the properties of the platforms (pillars) in the game. Below, I'll provide an explanation of the key aspects of the Pillars class:

Extends Rectangle: The Pillars class extends the Rectangle class, indicating that it inherits properties and methods from Rectangle. This allows Pillars to be visually represented as rectangles.

random: An instance of the Random class for generating random values.
screenWidth: The width of the game screen.
PLATFORM_HEIGHT: The constant height of the platforms.
minWidth and maxWidth: The minimum and maximum width of the platforms.
layoutY: The y-coordinate at which the platforms are placed.
minLayoutX and maxLayoutX: The minimum and maximum x-coordinates for placing the platforms.

Sachin Maurya
2022424

Naman Singh
2022312

Github Repo Link:  https://github.com/NamanSingh24/AP-PROJECT