package com.othello.controller;

import com.othello.interfaces.IOthelloController;
import com.othello.interfaces.IView;
import com.othello.model.Model;
import com.othello.util.OthelloConstants;

public class OthelloController implements IOthelloController {

    private Model model;
    private IView view;
    private String score;
    private boolean showLegalMoves;

    public OthelloController(Model model) {
    	this.model = model;
    }

    @Override
    public void setView(IView view) {
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
    	score=model.computeScore(this.model);
    	view.refreshView();
    }
    
    public void toggleShowMoves(){
    	showLegalMoves=!showLegalMoves;
    	view.refreshView();
    }
    
    public void endGame(){
    	view.printEndGame();
    }

    @Override
    public void start() {
    	model.initGame(model);
    	view.print();
    }

    public Model getModel() {
    	return model;
    }

	@Override
	public void playTurn(Integer i, Integer j) {
		if(model.legalMove(i, j, model.getTurn(), true)){
			if(model.getTurn()==OthelloConstants.BLACK)
				model.setTurn(OthelloConstants.WHITE);
			else
				model.setTurn(OthelloConstants.BLACK);
			score = model.computeScore(this.model);
			String[] individualScores = score.split("-");
			System.out.println("Black score : " + individualScores[0] + " White score : " + individualScores[1]);
			this.view.refreshView();
			//this.view.refreshView(String score) // prevent weird call
			if(model.checkEndGame(this.model)==true){
				System.out.println("FIN DU JEU");
				endGame();
			}	
		}
	}

	public String getScore() {
		return score;
	}

	public void setScore(String score) {
		this.score = score;
	}

	public boolean isShowLegalMoves() {
		return showLegalMoves;
	}
}
