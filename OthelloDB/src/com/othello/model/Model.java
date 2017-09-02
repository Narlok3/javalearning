package com.othello.model;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import com.othello.model.database.GameDAO;
import com.othello.model.database.Player;
import com.othello.util.OthelloConstants.CellStatus;
import com.othello.util.OthelloConstants.Turn;

//Observer pattern: if I chose to use that, we would have
//an observer interface with the refreshview methods
//that would be called directly from the model
public class Model {

    private BoardManager boardManager;
    private MoveManager moveManager;
    private TurnManager turnManager;

    private String uniqueGameID;
    public GameDAO gameDAO;

    public Model() {
	boardManager = new BoardManager(this);
	moveManager = new MoveManager(this);
	turnManager = new TurnManager();
	gameDAO = new GameDAO();
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

    public void saveGame(Player player) throws SQLException {

	uniqueGameID = UUID.randomUUID().toString();

	gameDAO.createGameRecord(uniqueGameID, player, turnManager,
		boardManager);

	// Nous déclarons nos objets en dehors du bloc try/catch
	ObjectOutputStream oos;
	try {
	    oos = new ObjectOutputStream(new BufferedOutputStream(
		    new FileOutputStream(new File("./gamerecords/"
			    + uniqueGameID + ".txt"))));

	    // Nous allons écrire chaque objet Game dans le fichier
	    oos.writeObject(boardManager.getBoardStates());

	    oos.close();

	} catch (FileNotFoundException e) {
	    e.printStackTrace();
	} catch (IOException e) {
	    e.printStackTrace();
	}
    }

    public void loadLastGame(Player player) throws SQLException {

	String gameReference = (gameDAO.retrieveGameRecord(player, turnManager));
	System.out.println("Unique game id trouvé : " + gameReference);
	// On récupère maintenant les données !
	try {
	    loadGame(gameReference);
	    uniqueGameID = gameReference;
	} catch (Exception e) {
	    e.printStackTrace();
	    System.err.println("Impossible de charger la partie");
	}

    }

    public void loadGame(String gameReference) throws Exception {
	ObjectInputStream ois;
	try {
	    ois = new ObjectInputStream(new BufferedInputStream(
		    new FileInputStream(new File("./gamerecords/"
			    + gameReference + ".txt"))));
	    try {
		ArrayList<CellStatus[][]> test = (ArrayList<CellStatus[][]>) ois
			.readObject();
		System.out.println("Setting new board");
		boardManager.setBoardStates(test);
		uniqueGameID = gameReference;
	    } catch (ClassNotFoundException e) {
		e.printStackTrace();
		throw new Exception("exception1");
	    } finally {
		ois.close();
	    }
	} catch (FileNotFoundException e1) {
	    e1.printStackTrace();
	    throw new Exception("exception2");
	} catch (IOException e1) {
	    e1.printStackTrace();
	    throw new Exception("exception3");
	}
    }

    public List<Map<String, String>> retrieveHistory(Player player)
	    throws SQLException {

	List<Map<String, String>> result = gameDAO.retrieveListGameRecords(
		player, 10);
	return result;
    }
}
