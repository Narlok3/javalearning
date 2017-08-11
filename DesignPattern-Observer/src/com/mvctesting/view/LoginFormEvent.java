package com.mvctesting.view;

//this is a bean class
//a bean is a class that stores data and
//that has getters and setters for it
//in this context this would be an event class
public class LoginFormEvent {
    private String name;
    private String password;

    public LoginFormEvent(String name, String password) {
	this.name = name;
	this.password = password;
    }

    public String getName() {
	return name;
    }

    public void setName(String name) {
	this.name = name;
    }

    public String getPassword() {
	return password;
    }

    public void setPassword(String password) {
	this.password = password;
    }
}
