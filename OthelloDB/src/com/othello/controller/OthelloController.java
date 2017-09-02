package com.othello.controller;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import com.othello.interfaces.IOthelloController;
import com.othello.interfaces.IOthelloView;
import com.othello.model.Model;
import com.othello.model.database.Database;
import com.othello.model.database.PasswordHashingDemo;
import com.othello.model.database.Player;
import com.othello.model.database.PlayerDAO;

public class OthelloController implements IOthelloController {

    private Model model;
    private IOthelloView view;
    private String score;
    private PlayerDAO playerDAO;
    private Player currentPlayer;
    private boolean showLegalMoves;

    public OthelloController(Model model) {
	this.model = model;
	playerDAO = new PlayerDAO();
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
	Database.getInstance().disconnect();
	view.close();
    }

    @Override
    public void newGame() {
	model.initGame();
	score = model.getScore(); // model should notify that?
	view.refreshView();
    }

    public void toggleShowMoves() {
	showLegalMoves = !showLegalMoves;
	view.refreshView();
    }

    @Override
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
	if (model.legalMove(i, j, true)) {
	    score = model.getScore();
	    view.refreshView();
	    if (model.checkEndGame() == true) {
		System.out.println("FIN DU JEU");
		endGame();
	    }
	}
    }

    public String getScore() {
	score = model.getScore();
	String[] individualScores = score.split("-");
	System.out.println("Black score : " + individualScores[0]
		+ " White score : " + individualScores[1]);
	return score;
    }

    public boolean isShowLegalMoves() {
	return showLegalMoves;
    }

    public void cancelMove() {
	try {
	    model.rollBackMove();
	    score = model.getScore(); // model should notify about it?
	    view.refreshView();
	} catch (ArrayIndexOutOfBoundsException e) {
	    System.out.println("Impossible d'annuler le coup");
	}
    }

    public void login(String username, String password) {
	try {
	    password = PasswordHashingDemo.generateHash(password);
	    currentPlayer = playerDAO.authenticatePlayer(username, password);
	    if (currentPlayer == null) {
		System.out
			.println("Not found in database, trying to create the user");
		currentPlayer = new Player(username, password);
		playerDAO.addPerson(currentPlayer);
	    }
	} catch (SQLException e) {
	    System.err
		    .println("Impossible de se connecter ou de créer le user");
	    e.printStackTrace();
	}
	view.refreshView();
    }

    public Player getCurrentPlayer() {
	return currentPlayer;
    }

    public void saveCurrentGame() {
	if (currentPlayer == null) {
	    System.out.println("You must log in to save a game!");
	} else {
	    System.out.println("Attempting to save the game");
	    try {
		model.saveGame(currentPlayer);
	    } catch (SQLException e) {
		System.err.println("Saving game failed");
		e.printStackTrace();
	    }
	}
    }

    public void loadLastGame() {
	if (currentPlayer == null) {
	    System.out.println("You must log in to save a game!");
	} else {
	    System.out.println("Attempting to load the last game");
	    try {
		model.loadLastGame(currentPlayer);
	    } catch (SQLException e) {
		System.err.println("Loading game failed");
		e.printStackTrace();
	    }
	}
	view.refreshView();
    }

    public void loadGame(String gameReference) {
	if (currentPlayer == null) {
	    System.out.println("You must log in to save a game!");
	} else {
	    System.out.println("Attempting to load the last game");
	    try {
		System.out.println("Loading game : " + gameReference);
		model.loadGame(gameReference);
	    } catch (SQLException e) {
		System.err.println("Loading game failed");
		e.printStackTrace();
	    } catch (Exception e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	    }
	}
	view.refreshView();
    }

    public void retrieveHistory() {
	// add a return type to return the gamesHistory
	// to the view directly ?
	List<Map<String, String>> gamesHistory;
	System.out.println("Attempting to retrieve history");
	try {
	    gamesHistory = model.retrieveHistory(currentPlayer);
	    view.printHistory(gamesHistory);
	} catch (SQLException e) {
	    e.printStackTrace();
	    // return null;
	}
	// return gamesHistory;
    }
}
