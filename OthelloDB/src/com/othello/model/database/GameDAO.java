package com.othello.model.database;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.othello.model.TurnManager;

public class GameDAO {

	public void createGameRecord(String uniqueGameID, Player player,TurnManager turnManager) throws SQLException {

		Connection conn = Database.getInstance().getConnection();

		PreparedStatement p = conn.prepareStatement("insert into games (reference, turn, user_id) values (?,?,?)");

		p.setString(1, uniqueGameID);
		p.setString(2, turnManager.getTurn().toString());
		p.setInt(3, player.getId());

		p.executeUpdate();

		p.close();
	}

	public String retrieveGameRecord(Player player, TurnManager turnManager) throws SQLException {

		String result = null;
		Connection conn = Database.getInstance().getConnection();

		PreparedStatement st = conn.prepareStatement("select * from games where user_id = ? order by timestamp desc");
		st.setInt(1, player.getId());

		ResultSet rs = st.executeQuery();


		if (rs.next()) {
			// set all the useful user information in this POJO
			result=rs.getString("reference");
			System.out.println("User found, data: id : " + result);
			turnManager.setTurn(rs.getString("turn"));
		}

		st.close(); // close resultset, preparedStatement, connection, clean up,
					// etc.
		rs.close();
		return result;
	}
}
