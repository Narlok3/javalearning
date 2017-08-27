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

		this.uniqueGameID = UUID.randomUUID().toString();

		gameDAO.createGameRecord(uniqueGameID, player,turnManager);

		// Nous déclarons nos objets en dehors du bloc try/catch
		ObjectOutputStream oos;
		try {
			oos = new ObjectOutputStream(
					new BufferedOutputStream(new FileOutputStream(new File("./gamerecords/" + uniqueGameID + ".txt"))));

			// Nous allons écrire chaque objet Game dans le fichier
			oos.writeObject(boardManager.getBoardStates());

			oos.close();

		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void loadGame(Player player) throws SQLException {

		uniqueGameID = (gameDAO.retrieveGameRecord(player,turnManager));
		System.out.println("Unique game id trouvé : " + uniqueGameID);
		// On récupère maintenant les données !
		ObjectInputStream ois;
		try {
			ois = new ObjectInputStream(new BufferedInputStream(new FileInputStream(new File("./gamerecords/" + uniqueGameID + ".txt"))));
			try {
				ArrayList<CellStatus[][]> test =
						(ArrayList<CellStatus[][]>) ois.readObject();
				System.out.println("Setting new board");
				boardManager.setBoardStates(test);
			} catch (ClassNotFoundException e) {
				e.printStackTrace();
			}
			ois.close();
		} catch (FileNotFoundException e1) {
			e1.printStackTrace();
		} catch (IOException e1) {
			e1.printStackTrace();
		}

	}
}
