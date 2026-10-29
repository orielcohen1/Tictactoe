package org.example;

public class Game {
    private Player player1;
    private Player player2;
    private Board board;
    private Player currentPlayer;
    private int counter = 0;// represents numbers of turns played

    public Player getCurrentPlayer() {
        return this.currentPlayer;
    }

    public char getSymbolAt(int x, int y) {
        return this.board.getSymbolAt(x, y);
    }

    public Game(Player p1, Player p2) {
        this.player1 = p1;
        this.player2 = p2;
        this.board = new Board();
        this.currentPlayer = player1; //
    }

    private void switchPlayer(){
        if(currentPlayer == player1){
            currentPlayer = player2;
        }
        else{
            currentPlayer = player1;
        }
    }
    public boolean play(int x, int y) {
        if (board.isAvailable(x, y)) {
            // מעבירים ללוח גם את הסימן וגם את המשקל של השחקן הנוכחי!
            board.placeSymbol(x, y, currentPlayer.getSymbol(), currentPlayer.getWeight());
            counter++;
            switchPlayer();
            return true;
        }
        return false;
    }
    public boolean isWin(int x, int y) {
        if (board.getRowScore(x) == 3 || board.getRowScore(x) == -3) return true;
        if (board.getColScore(y) == 3 || board.getColScore(y) == -3) return true;
        if (board.getMainDiagScore() == 3 || board.getMainDiagScore() == -3) return true;
        if (board.getSecondaryDiagScore() == 3 || board.getSecondaryDiagScore() == -3) return true;

        return false;
    }

    public boolean isTie(){
        if(counter == 9){
            return true;
        }
        return false;
    }
}
