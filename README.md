# ScotlandYard-Sim
 
## Requirements
[Dokumentation](Dokumentation.md)
- **Java Installation:**
  - Ensure that you have Java installed on your system. You can check this by running the following command in your command prompt or terminal:
    ```bash
    java --version
    ```
    If Java is not installed, you can download it from [Oracle's official website](https://www.oracle.com/de/java/technologies/downloads/#jdk21-windows).
  
## How to Run

1. **Compile the Code:**
   - Open a terminal or command prompt and navigate to the project directory.
   - Compile the Java code using the following command:
     ```bash
     javac *.java
     ```

2. **Run the Game:**
   - After compilation, run the game with the following command:
     ```bash
     java Main
     ```
   - Follow the on-screen instructions to play the game.

## Game Overview

Scotland Yard-Sim is a Java-based simulation of the classic board game "Scotland Yard." The game involves a strategic chase between the elusive Mister X and the detectives. The players take turns making moves, and the game continues until Mister X is captured or manages to escape.

## Features

- **GameMap Generation:**
  - The game map is dynamically generated, providing a varied and challenging playing environment in each session.

- **Player Interaction:**
  - Detectives and Mister X take turns making moves, with different strategies employed by each player type.

- **Game Outcome:**
  - The game can end in either a victory for Mister X (if he successfully evades capture for a set number of rounds) or a victory for the detectives (if they capture Mister X).

- **Graph-Based Movement:**
  - The movement of players is determined by a graph that represents the transportation network of the game. Different modes of transport (taxi, bus, train, ship) are available for travel.

## Future Development

- **Graphical User Interface (GUI):**
  - Consider integrating a graphical interface to enhance the gaming experience. This could include a visual representation of the game map and player positions.

- **Multiplayer Support:**
  - Explore the possibility of adding multiplayer functionality to allow multiple users to play the game simultaneously.

- **Optimization and AI:**
  - Further optimize player strategies and consider implementing more advanced AI for improved gameplay.

## Contributors

- [Your Name]

Feel free to contribute to the project and make it even more exciting!

Happy gaming!
