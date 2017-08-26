package com.othello.model;

import com.othello.util.OthelloConstants.CellStatus;
import com.othello.util.OthelloConstants.Turn;

//Observer pattern: if I chose to use that, we would have
//an observer interface with the refreshview methods
//that would be called directly from the model
public class Model {

    private BoardManager boardManager;
    private MoveManager moveManager;
    private TurnManager turnManager;

    public Model() {
	boardManager = new BoardManager(this);
	moveManager = new MoveManager(this);
	turnManager = new TurnManager();
    }

    public void initGame() {
	boardManager.initBoard();
	turnManager.setTurn(Turn.BLACK);
    }

    public CellStatus[][] getBoard() {
	return boardManager.getBoard();
    }

    public Turn getTurn() {
	return turnManager.getTurn();
    }

    public boolean legalMove(int v, int h, boolean flip) {
	boolean result = moveManager.legalMove(v, h, flip);
	if (result && flip) {
	    boardManager.saveBoard();
	    boardManager.computeScore();
	    turnManager.changeTurn();
	}
	return result;
    }

    public String getScore() {
	return boardManager.getScore();
    }

    public boolean checkEndGame() {
	return moveManager.checkEndGame();
    }

    public void rollBackMove() {
	boardManager.rollBackMove();
	turnManager.changeTurn();
    }

    public void setCellStatus(int posY, int posX, CellStatus color) { // =>
	boardManager.setCellStatus(posY, posX, color);
    }
}
