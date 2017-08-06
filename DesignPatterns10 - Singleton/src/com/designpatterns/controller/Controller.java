package com.designpatterns.controller;

import com.designpatterns.model.Model;
import com.designpatterns.view.LoginFormEvent;
import com.designpatterns.view.LoginListener;
import com.designpatterns.view.View;

public class Controller implements LoginListener {
	private View view;
	private Model model;
	
	public Controller(View view, Model model) {
		this.view = view;
		this.model = model;
	}

	@Override
	public void loginPerformed(LoginFormEvent event) {
		System.out.println("Login event received: " + event.getName() + "; " + event.getPassword());
		
	}
	
	
}
