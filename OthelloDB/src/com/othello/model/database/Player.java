package com.othello.model.database;

public class Player {
	private int id;
	private String username;
	private String password;
	
	public Player() {
		
	}
	
	public Player(String name, String password) {
		this.username = name;
		this.password = password;
		System.out.println("from normal constructor : " + this.password);
		System.out.println(this.password.length());
	}
	
	public Player(int id, String name, String password) {
		this.id = id;
		this.username = name;
		this.password = password;
		System.out.println("from id constructor : " + this.password);
	}

	public Player(int id, String username) {
		this.id = id;
		this.username = username;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return username;
	}

	public void setName(String name) {
		this.username = name;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
	
	
}
