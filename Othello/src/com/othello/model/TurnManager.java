package com.othello.model;

import com.othello.util.OthelloConstants.Turn;

public class TurnManager {
    private Turn turn;

    public TurnManager(Turn turn) {
	this.turn = turn;
    }

    public TurnManager() {
	this(Turn.BLACK);
    }

    public void setTurn(Turn turn) {
	this.turn = turn;
    }

    public Turn getTurn() {
	return turn;
    }

    public void changeTurn() {
	turn = (turn == Turn.BLACK) ? Turn.WHITE : Turn.BLACK; // ternaire
    }
}
