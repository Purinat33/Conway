package com.example.demo;

//    Any live cell with fewer than two live neighbors dies, as if by underpopulation.
//    Any live cell with two or three live neighbors lives on to the next generation.
//    Any live cell with more than three live neighbors dies, as if by overpopulation.
//    Any dead cell with exactly three live neighbors becomes a live cell, as if by reproduction.
import java.util.Random;
public class Board {
    public Cell[][] getBoard() {
        return board;
    }

    private Cell[][] board;
    private int boardSize;

    public Board(int boardSize) {
        this.boardSize = boardSize;
        this.board = new Cell[boardSize][boardSize];
        this.initBoard();
    }

    public void initBoard(){
        for(int i = 0 ; i < boardSize; i++){
            for(int j = 0 ; j < boardSize; j++){
                board[i][j] = new Cell('D');
            }
        }

        System.out.println("Initial State: ");
        randomBoard();
        System.out.println("--------------------");

        this.printBoard();
        System.out.println("--------------------");

    }

    public void randomBoard(){
        Random r = new Random();
        boolean living;
        for(int i = 0 ; i < boardSize; i++){
            for(int j = 0 ; j < boardSize; j++){
                living = r.nextBoolean();
                if(living) board[i][j].setState('L');
            }
        }
    }

    public void setBlink(int row, int col){

        if(col == 0){
            this.board[row][col].setState('L');
            this.board[row][col+2].setState('L');
            this.board[row][col+1].setState('L');
        }
        else if(col == this.boardSize-1){
            this.board[row][col].setState('L');
            this.board[row][col-1].setState('L');
            this.board[row][col-2].setState('L');
        }else{
            this.board[row][col].setState('L');
            this.board[row][col-1].setState('L');
            this.board[row][col+1].setState('L');
        }

    }

    public void setArch(int initial){
        // Arch wave
        this.board[initial][initial+1].setState('L');
        this.board[initial][initial+2].setState('L');
        this.board[initial][initial+3].setState('L');

        this.board[initial+1][initial+1].setState('L');
        this.board[initial+1][initial+3].setState('L');

        this.board[initial+2][initial+1].setState('L');
        this.board[initial+2][initial+3].setState('L');

        // 2nd set
        initial += 5;
        this.board[initial][initial+1].setState('L');
        this.board[initial][initial+2].setState('L');
        this.board[initial][initial+3].setState('L');

        this.board[initial+1][initial+1].setState('L');
        this.board[initial+1][initial+3].setState('L');

        this.board[initial+2][initial+1].setState('L');
        this.board[initial+2][initial+3].setState('L');
    }

    public void printBoard(){
        for(int i = 0 ; i < boardSize; i++){
            for(int j = 0 ; j < boardSize; j++){
                if(board[i][j].getState() == 'D'){
                    System.out.print(". ");
                }else System.out.print("O ");
            }
            System.out.println();
        }
    }

    // Given location row, col check:
    // row - 1,   col - 1
    // row,       col - 1
    // row + 1,   col - 1

    // row - 1,   col
    // row,       col
    // row + 1,   col

    // row - 1,   col + 1
    // row,       col + 1
    // row + 1,   col + 1

    // Special Case:
    // col 0
    // col 49
    // row 0
    // row 49

    // row == 0, row == 0 Top left
    // row == 0, col == 49 Top right

    // row == 49, col == 0 Bottom Left
    // row == 49, col == 49 Bottom Right
    public int getLivingNeighbours(Cell[][] board, int row, int col){
        // Given row, col index, count among the 8 neighbors the one with state == 'L'
        int livingNeighbours = 0;

        // Case: row = 0 : Top Row
        if(row == 0){
            // Case: row == 0, col == 0 Top left corner
            if(col == 0){
                if(board[row][col+1].getState() == 'L') livingNeighbours += 1;
                if(board[row+1][col].getState() == 'L') livingNeighbours += 1;
                if(board[row+1][col+1].getState() == 'L') livingNeighbours += 1;
            }
            // Case: row == 0, col == 49 Top Right corner
            else if(col == boardSize - 1){
                if(board[row][col-1].getState() == 'L') livingNeighbours += 1;
                if(board[row+1][col].getState() == 'L') livingNeighbours += 1;
                if(board[row+1][col-1].getState() == 'L') livingNeighbours += 1;
            }
            // Case row == 0
            else{
                if(board[row][col-1].getState() == 'L') livingNeighbours += 1;
                if(board[row][col+1].getState() == 'L') livingNeighbours += 1;
                if(board[row+1][col-1].getState() == 'L') livingNeighbours += 1;
                if(board[row+1][col].getState() == 'L') livingNeighbours += 1;
                if(board[row+1][col+1].getState() == 'L') livingNeighbours += 1;
            }
        }
        // Case: row = 49 : Bottom Row
        else if(row == boardSize - 1){
            // Case: row == 49, col == 0 Bottom left corner
            if(col == 0){
                if(board[row][col+1].getState() == 'L') livingNeighbours += 1;
                if(board[row-1][col].getState() == 'L') livingNeighbours += 1;
                if(board[row-1][col+1].getState() == 'L') livingNeighbours += 1;
            }
            // Case: row == 49, col == 49 Bottom Right corner
            else if(col == boardSize - 1){
                if(board[row][col-1].getState() == 'L') livingNeighbours += 1;
                if(board[row-1][col].getState() == 'L') livingNeighbours += 1;
                if(board[row-1][col-1].getState() == 'L') livingNeighbours += 1;
            }
            // Case Row == 49
            else{
                if(board[row-1][col-1].getState() == 'L') livingNeighbours += 1;
                if(board[row-1][col].getState() == 'L') livingNeighbours += 1;
                if(board[row-1][col+1].getState() == 'L') livingNeighbours += 1;
                if(board[row][col-1].getState() == 'L') livingNeighbours += 1;
                if(board[row][col+1].getState() == 'L') livingNeighbours += 1;
            }
        }
        else{
            if(col == 0){
                if(board[row-1][col].getState() == 'L') livingNeighbours += 1;
                if(board[row-1][col+1].getState() == 'L') livingNeighbours += 1;
                if(board[row][col+1].getState() == 'L') livingNeighbours += 1;
                if(board[row+1][col].getState() == 'L') livingNeighbours += 1;
                if(board[row+1][col+1].getState() == 'L') livingNeighbours += 1;
            }
            else if(col == boardSize - 1){
                if(board[row-1][col-1].getState() == 'L') livingNeighbours += 1;
                if(board[row-1][col].getState() == 'L') livingNeighbours += 1;
                if(board[row][col-1].getState() == 'L') livingNeighbours += 1;
                if(board[row+1][col-1].getState() == 'L') livingNeighbours += 1;
                if(board[row+1][col].getState() == 'L') livingNeighbours += 1;
            }
            else{
                if(board[row-1][col-1].getState() == 'L') livingNeighbours += 1;
                if(board[row-1][col].getState() == 'L') livingNeighbours += 1;
                if(board[row-1][col+1].getState() == 'L') livingNeighbours += 1;
                if(board[row][col-1].getState() == 'L') livingNeighbours += 1;
                if(board[row][col+1].getState() == 'L') livingNeighbours += 1;
                if(board[row+1][col-1].getState() == 'L') livingNeighbours += 1;
                if(board[row+1][col].getState() == 'L') livingNeighbours += 1;
                if(board[row+1][col+1].getState() == 'L') livingNeighbours += 1;
            }
        }

        return livingNeighbours;
    }

    public char getNextState(int neighbour, char currentState){
        if(currentState == 'L'){
            // Any live cell with fewer than two live neighbors dies, as if by underpopulation.
            // Any live cell with more than three live neighbors dies, as if by overpopulation.
            if(neighbour < 2 || neighbour > 3){
                return 'D';
            }
            // Any live cell with two or three live neighbors lives on to the next generation.
            else {
                return 'L';
            }
        }
        else{
            // Any dead cell with exactly three live neighbors becomes a live cell, as if by reproduction.
            if(neighbour == 3){
                return 'L';
            }
            else return 'D';
        }
    }

    public void run(int iteration) {

        Cell[][] temp = new Cell[boardSize][boardSize];
        for (int i = 0; i < boardSize; i++) {
            for (int j = 0; j < boardSize; j++) {
                temp[i][j] = new Cell('D');
            }
        }

        System.out.println("Iteration: " + iteration);
        System.out.println("----------------");
        for(int row = 0; row < boardSize; row++){
            for(int col = 0; col < boardSize; col++){
                int neighbor = this.getLivingNeighbours(this.getBoard(), row, col);
                temp[row][col].setState(this.getNextState(neighbor, board[row][col].getState()));
            }
        }

        this.board = temp;

        printBoard();
        System.out.println("----------------");
    }
}
