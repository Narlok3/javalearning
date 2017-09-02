package com.othello.model.database;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.othello.model.BoardManager;
import com.othello.model.TurnManager;

public class GameDAO {

    public void createGameRecord(String uniqueGameID, Player player,
	    TurnManager turnManager, BoardManager boardManager)
	    throws SQLException {

	Connection conn = Database.getInstance().getConnection();

	PreparedStatement p = conn
		.prepareStatement("insert into games (reference, turn, user_id, score) values (?,?,?,?)");

	p.setString(1, uniqueGameID);
	p.setString(2, turnManager.getTurn().toString());
	p.setInt(3, player.getId());
	p.setString(4, boardManager.getScore());

	p.executeUpdate();

	p.close();
    }

    public String retrieveGameRecord(Player player, TurnManager turnManager)
	    throws SQLException {

	String result = null;
	Connection conn = Database.getInstance().getConnection();

	PreparedStatement st = conn
		.prepareStatement("select * from games where user_id = ? order by timestamp desc");
	st.setInt(1, player.getId());

	ResultSet rs = st.executeQuery();

	if (rs.next()) {
	    // set all the useful user information in this POJO
	    result = rs.getString("reference");
	    System.out.println("User found, data: id : " + result);
	    turnManager.setTurn(rs.getString("turn"));
	}

	st.close(); // close resultset, preparedStatement, connection, clean up,
		    // etc.
	rs.close();
	return result;
    }

    public List<Map<String, String>> retrieveListGameRecords(Player player,
	    int nRecords) throws SQLException {

	List<Map<String, String>> gamesList = new ArrayList<Map<String, String>>();

	Connection conn = Database.getInstance().getConnection();

	PreparedStatement st = conn
		.prepareStatement("select * from games where user_id = ? order by timestamp desc limit ?");
	st.setInt(1, player.getId());
	st.setInt(2, nRecords);

	ResultSet rs = st.executeQuery();

	while (rs.next()) {
	    Map<String, String> gameCaracs = new HashMap<String, String>();
	    // set all the useful user information in this POJO
	    gameCaracs.put("Reference", rs.getString("reference"));
	    gameCaracs.put("Turn", rs.getString("turn"));
	    gameCaracs.put("Score", rs.getString("score"));
	    gameCaracs.put("Date", rs.getString("timestamp"));
	    gamesList.add(gameCaracs);
	}

	st.close(); // close resultset, preparedStatement, connection, clean up,
		    // etc.
	rs.close();
	return gamesList;
    }
}
