package com.example.demo;


import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintStream;
import java.util.Date;

public class Game {
    public static void main(String[] args) throws FileNotFoundException {
        // Create a file object to redirect output
        Date date = new Date();
        long now = date.getTime();
        PrintStream f = new PrintStream(new File("text/" + now + ".txt"));
        System.setOut(f);

        Board board = new Board(20);
        int iterator = 0;
        do {
            iterator++;
            board.run(iterator);

        } while (iterator < 100);
    }
}
