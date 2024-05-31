package com.example.demo;

public class Cell {
    private char state; //Dead or Live (D/L)
    public Cell(char state){
        this.state = state;
    }

    public void setState(char state) {
        this.state = state;
    }
    public char getState() {
        return state;
    }
}
