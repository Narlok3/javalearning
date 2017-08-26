package com.othello.model;

import java.util.ArrayList;
import java.util.Arrays;

import com.othello.util.OthelloConstants;
import com.othello.util.OthelloConstants.CellStatus;

public class BoardManager {

    private Model model;
    private final CellStatus board[][] = new CellStatus[OthelloConstants.WIDTH][OthelloConstants.HEIGHT];
    private final ArrayList<CellStatus[][]> boardStates = new ArrayList<CellStatus[][]>();
    private String score;

    public BoardManager(Model model) {
	this.model = model;
    }

    public void initBoard() {

	boardStates.removeAll(boardStates);

	for (int i = 0; i < OthelloConstants.HEIGHT; i++) {
	    for (int j = 0; j < OthelloConstants.WIDTH; j++) {
		board[i][j] = CellStatus.EMPTY;
	    }
	}
	board[OthelloConstants.HEIGHT / 2 - 1][OthelloConstants.WIDTH / 2 - 1] = CellStatus.WHITE;
	board[OthelloConstants.HEIGHT / 2][OthelloConstants.WIDTH / 2 - 1] = CellStatus.BLACK;
	board[OthelloConstants.HEIGHT / 2 - 1][OthelloConstants.WIDTH / 2] = CellStatus.BLACK;
	board[OthelloConstants.HEIGHT / 2][OthelloConstants.WIDTH / 2] = CellStatus.WHITE;

	computeScore();
	saveBoard();
    }

    public void saveBoard() {

	CellStatus[][] tmpBoard = Arrays.stream(board).map(CellStatus[]::clone)
		.toArray(CellStatus[][]::new);
	boardStates.add(tmpBoard);
	/*
	 * for (int i = 0; i < a.length; i++) { d[i] = a[i].clone(); }
	 */
    }

    public void computeScore() {

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
	score = (blackScore + "-" + whiteScore);
    }

    public void rollBackMove() throws ArrayIndexOutOfBoundsException {
	// si annulation impossible (tableau vide) => exception
	CellStatus tmpBoard[][] = new CellStatus[OthelloConstants.WIDTH][OthelloConstants.HEIGHT];
	tmpBoard = boardStates.get(boardStates.size() - 2);
	for (int i = 0; i < OthelloConstants.HEIGHT; i++) {
	    for (int j = 0; j < OthelloConstants.WIDTH; j++) {
		model.setCellStatus(i, j, tmpBoard[i][j]);
	    }
	}
	boardStates.remove(boardStates.size() - 1);
	computeScore();
    }

    public CellStatus[][] getBoard() {
	return board;
    }

    public void setCellStatus(int posY, int posX, CellStatus color) {
	board[posY][posX] = color;
    }

    public String getScore() {
	return score;
    }
}
