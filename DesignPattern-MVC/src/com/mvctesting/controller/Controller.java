package com.mvctesting.controller;

import com.mvctesting.model.Model;
import com.mvctesting.view.View;

public class Controller {

    private Model model;
    private View view;

    public Controller(Model model, View view) {
	this.model = model;
	this.view = view;
    }

}
