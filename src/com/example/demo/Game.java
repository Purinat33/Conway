package com.example.demo;


public class Game {
    public static void main(String[] args) {
        Board board = new Board(20);
        int iterator = 0;
        do {
            iterator++;
            board.run(iterator);

        } while (iterator < 50);
    }
}
