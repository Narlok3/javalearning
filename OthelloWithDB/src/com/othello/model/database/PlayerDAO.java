package com.othello.model.database;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

/*
 * One DAO class person table or view
 * CRUD - Create, retrieve, update, delete
 */


public class PlayerDAO {

	public void addPerson(Player person) throws SQLException {
		
		Connection conn = Database.getInstance().getConnection();
		
		PreparedStatement p = conn.prepareStatement("insert into users (username, password) values (?,?)");
		
		p.setString(1, person.getName());
		p.setString(2, person.getPassword());
		
		p.executeUpdate();
		
		p.close();
		
	}
	
	public Player getPerson(int id) throws SQLException {
		
		Connection conn = Database.getInstance().getConnection();
		
		PreparedStatement p = conn.prepareStatement("select * from users where (username, password) values (?,?)");


		return null;
	}
	
	// returns AuthenticatedUser object if authentication is successful, otherwise null
	public Player authenticatePlayer(String username, String password) throws SQLException {   
		
		Connection conn = Database.getInstance().getConnection();
		
		PreparedStatement st = conn.prepareStatement("select * from users where username = ? and password = ?");
	    st.setString(1, username);
	    st.setString(2, password);

	    ResultSet rs = st.executeQuery();

	    Player user = null;

	    //login valid because there is something from the result set, then create user object
	    if (rs.next() ) {
	        // set all the useful user information in this POJO
	        user = new Player(rs.getInt(1), username);
	        System.out.println("User found, data: id : " + user.getId() + " name : " +  user.getName());
	    }
	    
	    st.close(); // close resultset, preparedStatement, connection, clean up, etc.
	    rs.close();

	    return user;  
	}
	
	public List<Player> getPeople() throws SQLException {
		
		List<Player> people = new ArrayList<Player>();
		
		Connection conn = Database.getInstance().getConnection();
		
		String sql = "select id, username, password from users order by id";
		Statement selectStatement = conn.createStatement();
		
		ResultSet results = selectStatement.executeQuery(sql);
		
		while(results.next()) {
			int id = results.getInt("id");
			String name = results.getString("name");
			String password = results.getString("password");
			
			Player person = new Player(id, name, password);
			people.add(person);
		}
		
		results.close();
		selectStatement.close();
		
		return people;
	}
	
	public void updatePerson(Player person) {
		
	}
	
	public void deletePerson(int id) {
		
	}
}
