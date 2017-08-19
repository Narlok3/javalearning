package com.othello.model;

public class Model {

    final static int BLACK = 1; // Declare state of each square
    final static int WHITE = 2;
    final static int EMPTY = 0;
    private final static int WIDTH = 10;
    private final static int HEIGHT = 10;
    private final int board[][] = new int[getWidth()][getHeight()];
    final static int OFFBOARD = -1;

    private int turn;

    public Model() {

    }

    public Model(Model another) {
	for (int i = 0; i < getHeight(); i++) {
	    for (int j = 0; j < getWidth(); j++) {
		getBoard()[i][j] = another.getBoard()[i][j];
	    }
	}
    }

    public void initGame(Model game) {

	setTurn(BLACK);
	// System.out.println("Turn is: " + turn);

	// Initialize off-board squares
	for (int i = 0; i < game.getWidth(); i++) {
	    game.getBoard()[i][0] = OFFBOARD;
	    game.getBoard()[i][game.getWidth() - 1] = OFFBOARD;
	    game.getBoard()[0][i] = OFFBOARD;
	    game.getBoard()[game.getHeight() - 1][i] = OFFBOARD;
	}

	// Initialize game board to be empty except for initial setup
	for (int i = 1; i < game.getHeight() - 1; i++) {
	    for (int j = 1; j < game.getWidth() - 1; j++) {
		game.getBoard()[i][j] = EMPTY;
	    }
	}

	game.getBoard()[game.getHeight() / 2 - 1][game.getWidth() / 2 - 1] = WHITE;
	game.getBoard()[game.getHeight() / 2][game.getWidth() / 2 - 1] = BLACK;
	game.getBoard()[game.getHeight() / 2 - 1][game.getWidth() / 2] = BLACK;
	game.getBoard()[game.getHeight() / 2][game.getWidth() / 2] = WHITE;
    }

    /**
     * Return width.
     * @return width
     */
    public static int getWidth() {
	return WIDTH;
    }

    public static int getHeight() {
	return HEIGHT;
    }

    public int[][] getBoard() {
	return board;
    }

    public int getTurn() {
	return turn;
    }

    public void setTurn(int turn) {
	this.turn = turn;
    }
}
