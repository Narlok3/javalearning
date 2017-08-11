package com.mvctesting.controller;

import com.mvctesting.model.Model;
import com.mvctesting.view.LoginFormEvent;
import com.mvctesting.view.LoginListener;
import com.mvctesting.view.View;

public class Controller implements LoginListener {

    private Model model;
    private View view;

    public Controller(Model model, View view) {
	this.model = model;
	this.view = view;
    }

    @Override
    public void loginPerformed(LoginFormEvent event) {
	System.out.println("Login event received. Name : " + event.getName()
		+ "; password : " + event.getPassword());
    }

}
