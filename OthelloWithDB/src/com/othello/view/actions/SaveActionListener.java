package com.othello.view.actions;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import com.othello.controller.OthelloController;

public class SaveActionListener implements ActionListener {

	private OthelloController controller;
	
	public SaveActionListener(OthelloController controller){
		this.controller = controller;
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		controller.saveCurrentGame();
	}

}
