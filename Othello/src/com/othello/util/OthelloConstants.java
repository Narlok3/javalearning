package com.othello.util;

public class OthelloConstants {
    // replace with enums
    public final static int BLACK = 1; // Declare state of each square
    public final static int WHITE = 2;
    public final static int EMPTY = 0;
    public final static int WIDTH = 8;
    public final static int HEIGHT = 8;

    public enum Turn {
	BLACK, WHITE;
    }

    public enum BoardStatus {
	BLACK, WHITE, EMPTY, OFFBOARD
    }
}
