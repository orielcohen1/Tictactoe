package org.example;

public class Board {

    private char[][] matrix;
    private int[] rowCounters = new int[3];
    private int[] colCounters = new int[3];
    private int mainDiagCounter = 0;
    private int secondaryDiagCounter = 0;

    //getters


    // קבלת ציון של שורה ספציפית
    public int getRowScore(int x) {
        return this.rowCounters[x];
    }

    // קבלת ציון של עמודה ספציפית
    public int getColScore(int y) {
        return this.colcounters[y];
    }

    // קבלת הציון של האלכסון הראשי
    public int getMainDiagScore() {
        return this.mainDiagCounter;
    }

    // קבלת הציון של האלכסון המשני
    public int getSecondaryDiagScore() {
        return this.secondaryDiagCounter;
    }
    public char getSymbolAt(int x, int y) {
        return this.matrix[x][y];
    }



public Board() {
    this.matrix = new char[3][3];
    for(int i=0; i<3; i++){
        for(int j=0; j<3; j++){
            matrix[i][j] = ' ';
        }
    }
}

public boolean isAvailable(int x, int y){
    if (x < 0 || x >= 3 || y < 0 || y >= 3) {
        return false;
    }
    boolean available = false;
    if(matrix[x][y] == ' '){
        available = true;
    }
    return available;
}

public boolean placeSymbol(int x, int y, char symbol, int weight){
    if(isAvailable(x,y)){
        matrix[x][y] = symbol;
        rowCounters[x] = rowCounters[x] + weight;
        colCounters[y] = colCounters[y] + weight;
        if(x == y){
            mainDiagCounter += weight;
        }
        if(x+y == 2){
            secondaryDiagCounter += weight;
        }
        return true;

    }
    return false;

    }



}
