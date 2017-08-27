package com.othello.model;

import java.util.ArrayList;

import com.othello.util.OthelloConstants;
import com.othello.util.OthelloConstants.CellStatus;
import com.othello.util.OthelloConstants.Turn;

//pas propre.
public class MoveManager2 {
    private Model model;
    private CellStatus[][] board;
    private ArrayList<CellStatus[][]> boardStates;

    public MoveManager2(Model model, CellStatus[][] board,
	    ArrayList<CellStatus[][]> boardStates) {
	this.model = model;
	this.board = board;
	this.boardStates = boardStates;
    }

    public boolean legalMove(int v, int h, boolean flip) {
	boolean legal = false;

	if (board[v][h] == CellStatus.EMPTY) {
	    int posX;
	    int posY;
	    boolean found;
	    CellStatus current;

	    CellStatus color; // tmp code to replace turn/color with enum
	    if (model.getTurn() == Turn.BLACK) {
		color = CellStatus.BLACK;
	    } else {
		color = CellStatus.WHITE;
	    }

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
		model.saveBoard();
		// score = computeScore();
		// changeTurn();
	    }
	}
	return legal;
    }

    public void rollBackMove() throws ArrayIndexOutOfBoundsException {
	// si annulation impossible (tableau vide) => exception
	CellStatus[][] tmpBoard = new CellStatus[OthelloConstants.WIDTH][OthelloConstants.HEIGHT];
	tmpBoard = boardStates.get(boardStates.size() - 2);
	for (int i = 0; i < OthelloConstants.HEIGHT; i++) {
	    for (int j = 0; j < OthelloConstants.WIDTH; j++) {
		board[i][j] = tmpBoard[i][j];
	    }
	}
	boardStates.remove(boardStates.size() - 1);
	// changeTurn(); will be the duty of the turn manager
    }

}
