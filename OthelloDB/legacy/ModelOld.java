package com.othello.model;

import java.util.ArrayList;

import com.othello.util.OthelloConstants;
import com.othello.util.OthelloConstants.CellStatus;
import com.othello.util.OthelloConstants.Turn;

//Observer pattern: if I chose to use that, we would have
//an observer interface with the refreshview methods
//that would be called directly from the model
public class ModelOld {

    private String score;
    private BoardManager boardManager;
    private MoveManager moveManager;
    private TurnManager turnManager;
    // final array : the reference to that array can never change
    // one final array vs a new array for each new game ?
    private final CellStatus board[][] = new CellStatus[OthelloConstants.WIDTH][OthelloConstants.HEIGHT];
    private final ArrayList<CellStatus[][]> boardStates = new ArrayList<CellStatus[][]>();

    public Model() {
	boardManager = new BoardManager(this);
	moveManager = new MoveManager(this);
	turnManager = new TurnManager();
    }

    public void initGame() {

	turnManager.setTurn(Turn.BLACK);

	boardStates.removeAll(boardStates);
	// Initialize game board : should have a BoardManager
	for (int i = 0; i < OthelloConstants.HEIGHT; i++) {
	    for (int j = 0; j < OthelloConstants.WIDTH; j++) {
		board[i][j] = CellStatus.EMPTY;
	    }
	}
	board[OthelloConstants.HEIGHT / 2 - 1][OthelloConstants.WIDTH / 2 - 1] = CellStatus.WHITE;
	board[OthelloConstants.HEIGHT / 2][OthelloConstants.WIDTH / 2 - 1] = CellStatus.BLACK;
	board[OthelloConstants.HEIGHT / 2 - 1][OthelloConstants.WIDTH / 2] = CellStatus.BLACK;
	board[OthelloConstants.HEIGHT / 2][OthelloConstants.WIDTH / 2] = CellStatus.WHITE;

	score = computeScore();
	saveBoard();
    }

    public void newInitGame() {
	boardManager.initBoard();
	boardManager.saveBoard(); // a gerer par le boardManager?
	score = boardManager.computeScore();
	turnManager.setTurn(Turn.BLACK);
    }

    public CellStatus[][] newGetBoard() {
	return boardManager.getBoard();
    }

    public CellStatus[][] getBoard() {
	return board;
    }

    public Turn getTurn() {
	return turnManager.getTurn();
    }

    public boolean newLegalMove(int v, int h, boolean flip) {
	boolean result = moveManager.legalMove(v, h, flip);
	if (result && flip) {
	    boardManager.saveBoard();
	    boardManager.computeScore();
	    turnManager.changeTurn();
	}
	return result;
    }

    public boolean legalMove(int v, int h, boolean flip) {
	boolean legal = false;

	if (board[v][h] == CellStatus.EMPTY) {
	    int posX;
	    int posY;
	    boolean found;
	    CellStatus current;
	    CellStatus color;

	    // color = (turn == Turn.BLACK) ? CellStatus.BLACK :
	    // CellStatus.WHITE;
	    color = (getTurn() == Turn.BLACK) ? CellStatus.BLACK
		    : CellStatus.WHITE;

	    for (int x = -1; x <= 1; x++) {
		for (int y = -1; y <= 1; y++) {
		    found = false;
		    // Variables to keep track of where the algorithm is and
		    // whether it has found a valid move
		    posX = h + x;
		    posY = v + y;

		    try {
			current = board[posY][posX];
		    } catch (ArrayIndexOutOfBoundsException e) {
			continue;
		    }

		    // Check the first cell in the direction specified by x and
		    // y
		    // If the cell is empty or contains the same color
		    // skip the rest of the algorithm to begin checking another
		    // direction
		    if (current == CellStatus.EMPTY || current == color) {
			continue;
		    }

		    // Otherwise, check along that direction
		    while (!found) {
			posX += x;
			posY += y;
			try {
			    current = board[posY][posX];
			} catch (ArrayIndexOutOfBoundsException e) {
			    break;
			}

			if (current == color) {
			    found = true;
			    legal = true;

			    // If flip is true, reverse the directions and start
			    // flipping until
			    // the algorithm reaches the original location
			    if (flip) {
				posX -= x;
				posY -= y;
				current = board[posY][posX];
				while (current != CellStatus.EMPTY) {
				    board[posY][posX] = color;
				    posX -= x;
				    posY -= y;
				    current = board[posY][posX];
				}
			    }
			} else if (current == CellStatus.EMPTY
				|| (posX == 0 && x < 0) || (posX == 7 && x > 0)
				|| (posY == 0 && y < 0) || (posY == 7 && y > 0)) {
			    found = true;
			}
		    }
		}
	    }
	    if (legal && flip) {
		board[v][h] = color;
		saveBoard();
		score = computeScore();
		turnManager.changeTurn();
	    }
	}
	return legal;
    }

    public String newComputeScore() {
	return boardManager.computeScore();
    }

    public String computeScore() { // => BoardManager

	int blackScore = 0;
	int whiteScore = 0;

	for (int i = 0; i < OthelloConstants.HEIGHT; i++) {
	    for (int j = 0; j < OthelloConstants.WIDTH; j++) {
		if (board[i][j] == CellStatus.BLACK) {
		    blackScore++;
		} else if (board[i][j] == CellStatus.WHITE) {
		    whiteScore++;
		}
	    }
	}
	String result = (blackScore + "-" + whiteScore);
	return result;
    }

    public boolean checkEndGame() { // => MoveManager
	// if we can't find a legal move, we return true
	boolean result = true;

	for (int i = 0; i < OthelloConstants.HEIGHT; i++) {
	    for (int j = 0; j < OthelloConstants.WIDTH; j++) {
		if (legalMove(i, j, false)) {
		    return false;
		}
	    }
	}
	return result;
    }

    public void saveBoard() { // => BoardManager
	// there must be a better way to do that?
	CellStatus tmpBoard[][] = new CellStatus[OthelloConstants.WIDTH][OthelloConstants.HEIGHT];
	for (int i = 0; i < OthelloConstants.HEIGHT; i++) {
	    for (int j = 0; j < OthelloConstants.WIDTH; j++) {
		tmpBoard[i][j] = board[i][j];
	    }
	}
	boardStates.add(tmpBoard);
    }

    public void rollBackMove() throws ArrayIndexOutOfBoundsException {
	// refactor :
	// MoveManager.rollBackMove() //try:catch ou if pour gerer l'erreur
	// if success
	// TurnManager.changeTurn();
	// si annulation impossible (tableau vide) => exception
	CellStatus tmpBoard[][] = new CellStatus[OthelloConstants.WIDTH][OthelloConstants.HEIGHT];
	tmpBoard = boardStates.get(boardStates.size() - 2);
	for (int i = 0; i < OthelloConstants.HEIGHT; i++) {
	    for (int j = 0; j < OthelloConstants.WIDTH; j++) {
		board[i][j] = tmpBoard[i][j];
	    }
	}
	boardStates.remove(boardStates.size() - 1);
	turnManager.changeTurn();
    }

    public void newRollBackMove() {
	boardManager.rollBackMove();
	turnManager.changeTurn();
    }

    public void setCellStatus(int posY, int posX, CellStatus color) { // =>
								      // BoardManager
	board[posY][posX] = color;
    }

    public ArrayList<CellStatus[][]> getBoardStates() { // => BoardManager
	return boardStates;
    }
}
