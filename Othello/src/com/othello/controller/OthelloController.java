package com.othello.controller;

import com.othello.interfaces.IOthelloController;
import com.othello.interfaces.IView;
import com.othello.model.Model;
import com.othello.view.View;

public class OthelloController implements IOthelloController {

    private Model model;
    private View view;

    public OthelloController(Model model, View view) {
	this.model = model;
	this.view = view;
    }

    @Override
    public void setEcran(IView view) {
	// TODO Auto-generated method stub
    }

    @Override
    public void showAbout() {
	// TODO Auto-generated method stub
    }

    @Override
    public void quit() {
	// TODO Auto-generated method stub
    }

    @Override
    public void newGame() {
	// TODO Auto-generated method stub
    }

    @Override
    public void start() {
	// TODO Auto-generated method stub
    }

}
