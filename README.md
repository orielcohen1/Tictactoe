# Tic-Tac-Toe

A simple desktop Tic-Tac-Toe game built in Java with a Swing GUI.

## Features

- Two-player local gameplay (X vs O) with custom player names
- Mouse and keyboard controls (arrow keys to move the cursor, Enter to play a cell)
- Win and tie detection with an end-of-game dialog
- Clean separation between game logic (`Board`, `Player`, `Game`) and UI (`TicTacToeGUI`)

## Requirements

- JDK 22+
- Maven

## Getting Started

Clone the repository and build with Maven:

```bash
git clone https://github.com/orielcohen1/Tictactoe.git
cd Tictactoe
mvn clean package
```

Run the game:

```bash
mvn exec:java -Dexec.mainClass="org.example.TicTacToeGUI"
```

Or run `TicTacToeGUI.main()` directly from your IDE.

## How to Play

1. On launch, enter names for Player 1 (X) and Player 2 (O).
2. Click a cell, or use the arrow keys to move the highlighted cell and press Enter, to place your mark.
3. The game announces a winner once three marks line up in a row, column, or diagonal, or declares a tie if the board fills up.

## Project Structure

```
src/main/java/org/example/
├── Board.java          # Board state and win-condition scoring
├── Player.java         # Player data (name, symbol, weight)
├── Game.java           # Turn management and win/tie logic
└── TicTacToeGUI.java   # Swing UI and input handling
```

## License

No license specified.
