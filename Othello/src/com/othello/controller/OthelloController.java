package com.othello.controller;

import com.othello.interfaces.IOthelloController;
import com.othello.interfaces.IOthelloView;
import com.othello.model.Model;
import com.othello.util.OthelloConstants;

public class OthelloController implements IOthelloController {

	private Model model;
	private IOthelloView view;
	private String score;
	private boolean showLegalMoves;

	public OthelloController(Model model) {
		this.model = model;
	}

	@Override
	public void setView(IOthelloView view) {
		this.view = view;
	}

	@Override
	public void showAbout() {
		view.printAbout();
	}

	@Override
	public void quit() {
		view.close();
	}

	@Override
	public void newGame() {
		model.initGame(this.model);
		model.saveBoard();
		score = model.computeScore(this.model);
		view.refreshView();
	}

	public void toggleShowMoves() {
		showLegalMoves = !showLegalMoves;
		view.refreshView();
	}

	public void endGame() {
		view.printEndGame();
	}

	@Override
	public void start() {
		view.print();
	}

	public Model getModel() {
		return model;
	}

	@Override
	public void playTurn(int i, int j) {
		if (model.legalMove(i, j, model.getTurn(), true)) {
			if (model.getTurn() == OthelloConstants.BLACK)
				model.setTurn(OthelloConstants.WHITE);
			else
				model.setTurn(OthelloConstants.BLACK);
			score = model.computeScore(this.model);
			model.saveBoard(); // should the model manage that himself?
			this.view.refreshView();
			if (model.checkEndGame(this.model) == true) {
				System.out.println("FIN DU JEU");
				endGame();
			}
		}
	}

	public String getScore() {
		String[] individualScores = score.split("-");
		System.out.println("Black score : " + individualScores[0] + " White score : " + individualScores[1]);
		return score;
	}

	public boolean isShowLegalMoves() {
		return showLegalMoves;
	}

	public void cancelMove() {
		try {
			model.rollBackMove();
			if (model.getTurn() == OthelloConstants.BLACK)
				model.setTurn(OthelloConstants.WHITE);
			else
				model.setTurn(OthelloConstants.BLACK);
			score = model.computeScore(this.model);
			this.view.refreshView();
		} catch (ArrayIndexOutOfBoundsException e) {
			System.out.println("Impossible d'annuler le coup");
		}
	}
}
