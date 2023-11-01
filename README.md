# ScotlandYard-Sim
 
## Requirements
- **Java Installation:**
  - Ensure that you have Java installed on your system. You can check this by running the following command in your command prompt or terminal:
    ```bash
    java --version
    ```
    If Java is not installed, you can download it from [Oracle's official website](https://www.oracle.com/de/java/technologies/downloads/#jdk21-windows).
  
## How to Run

### 1. Terminal
   **Compile the Code:**
   - Open a terminal or command prompt and navigate to the project directory. 
   - Compile the Java code using the following command:
      ```bash
      javac *.java
      ```
      
   **Run the Game:**
   - After compilation, run the game with the following command:
      ```bash
      java Main
      ```
   - Follow the on-screen instructions to play the game.

### 2. IDE
  - Download the project and open the project in your choosen IDE
  - Go to SCOTLAND-YARD/src/Main and run the code

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
 
## Making your Bot!

- Go try to make your own bot. There are several tactics in the game, which can be deflected.
  There are different tactical approaches that you can try. To do so, you have to edit the getMove() in MisterXMoveStrategy.java/DetectiveMoveStrategy.java in the src.
  (If you change any state of the gameBoard during the getMove() - the programm won't run - look at the Error printing in your console). Look at the [Dokumentation](Dokumentation.md) for more
  information about the board and it's behavior. 


Happy gaming!
