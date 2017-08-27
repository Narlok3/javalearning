package com.othello.model;

import com.othello.util.OthelloConstants;
import com.othello.util.OthelloConstants.CellStatus;
import com.othello.util.OthelloConstants.Turn;
//update the board when a move is made
//another way of doing could be splitting the duty
//between a validation and an update

public class MoveManager {
    private Model model;

    public MoveManager(Model model) {
	this.model = model;
	// un gestionnaire de mouvement n'est pas composé d'un board, alors
	// qu'un jeu de l'othello oui.
	// this.board = board; => pas de board
	// this.boardStates = boardStates;
    }

    public boolean legalMove(int v, int h, boolean flip) {
	boolean legal = false;

	if (model.getBoard()[v][h] == CellStatus.EMPTY) {
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
			current = model.getBoard()[posY][posX];
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
			    current = model.getBoard()[posY][posX];
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
				current = model.getBoard()[posY][posX];
				while (current != CellStatus.EMPTY) {
				    model.setCellStatus(posY, posX, color);
				    posX -= x;
				    posY -= y;
				    current = model.getBoard()[posY][posX];
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
		model.setCellStatus(v, h, color);
	    }
	}
	return legal;
    }

    public boolean checkEndGame() {
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
}
