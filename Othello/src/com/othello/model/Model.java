package com.othello.model;

import java.util.ArrayList;
import com.othello.util.OthelloConstants;

public class Model {
	
	//scope errors when I don't use final arrays
	//what can I do about it?
	private final int board[][] = new int[OthelloConstants.WIDTH][OthelloConstants.HEIGHT];
	private int turn;
	private final ArrayList<int[][]> boardStates = new ArrayList<int[][]>();

	public Model() {
		
	}

	public Model(Model another) {
		for (int i = 0; i < OthelloConstants.HEIGHT; i++) {
			for (int j = 0; j < OthelloConstants.WIDTH; j++) {
				getBoard()[i][j] = another.getBoard()[i][j];
			}
		}
	}

	public void initGame(Model game) {

		setTurn(OthelloConstants.BLACK);

		// Initialize game board to be empty except for initial setup
		for (int i = 0; i < OthelloConstants.HEIGHT; i++) {
			for (int j = 0; j < OthelloConstants.WIDTH; j++) {
				game.getBoard()[i][j] = OthelloConstants.EMPTY;
			}
		}

		game.getBoard()[OthelloConstants.HEIGHT / 2 - 1][OthelloConstants.WIDTH / 2 - 1] = OthelloConstants.WHITE;
		game.getBoard()[OthelloConstants.HEIGHT / 2][OthelloConstants.WIDTH / 2 - 1] = OthelloConstants.BLACK;
		game.getBoard()[OthelloConstants.HEIGHT / 2 - 1][OthelloConstants.WIDTH / 2] = OthelloConstants.BLACK;
		game.getBoard()[OthelloConstants.HEIGHT / 2][OthelloConstants.WIDTH / 2] = OthelloConstants.WHITE;

		//boardStates.add(board.clone()); clone doesn't work because
		//it seems to create a new reference for the same content?
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

	public boolean legalMove(int v, int h, int color, boolean flip) {
		boolean legal = false;

		if (board[v][h] == OthelloConstants.EMPTY) {
			int posX;
			int posY;
			boolean found;
			int current;

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
					if (current == OthelloConstants.EMPTY || current == color) {
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
								while (current != OthelloConstants.EMPTY) {
									board[posY][posX] = color;
									posX -= x;
									posY -= y;
									current = board[posY][posX];
								}
							}
						} else if (current == OthelloConstants.EMPTY || (posX == 0 && x < 0) || (posX == 7 && x > 0)
								|| (posY == 0 && y < 0) || (posY == 7 && y > 0)) {
							found = true;
						}
					}
				}
			}
		}
		if (legal && flip) {
			board[v][h] = color;
		}
		return legal;
	}

	public String computeScore(Model game) {

		int blackScore = 0;
		int whiteScore = 0;

		for (int i = 0; i < OthelloConstants.HEIGHT; i++) {
			for (int j = 0; j < OthelloConstants.WIDTH; j++) {
				if (game.getBoard()[i][j] == OthelloConstants.BLACK) {
					blackScore++;
				} else if (game.getBoard()[i][j] == OthelloConstants.WHITE) {
					whiteScore++;
				}
			}
		}
		String result = (blackScore + "-" + whiteScore);
		return result;
	}

	public boolean checkEndGame(Model game) {
		// if we can't find a legal move, we return true
		boolean result = true;

		for (int i = 0; i < OthelloConstants.HEIGHT; i++) {
			for (int j = 0; j < OthelloConstants.WIDTH; j++) {
				if (legalMove(i, j, getTurn(), false)) {
					return false;
				}
			}
		}
		return result;
	}

	public void saveBoard() {
		//there must be a better way to do that?
		int tmpBoard[][] = new int[OthelloConstants.WIDTH][OthelloConstants.HEIGHT];
		for (int i = 0; i < OthelloConstants.HEIGHT; i++) {
			for (int j = 0; j < OthelloConstants.WIDTH; j++) {
					tmpBoard[i][j]=board[i][j];
			}
		}
		boardStates.add(tmpBoard);
	}
	
	public void rollBackMove() throws ArrayIndexOutOfBoundsException {
		//si annulation impossible (tableau vide) => exception
		int tmpBoard[][] = new int[OthelloConstants.WIDTH][OthelloConstants.HEIGHT];
		tmpBoard = boardStates.get(boardStates.size() - 2);
		for (int i = 0; i < OthelloConstants.HEIGHT; i++) {
			for (int j = 0; j < OthelloConstants.WIDTH; j++) {
				board[i][j]=tmpBoard[i][j];
			}
		}
		boardStates.remove(boardStates.size() - 1);
	}
}
