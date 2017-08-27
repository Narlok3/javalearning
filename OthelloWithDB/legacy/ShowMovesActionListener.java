package com.othello.view.actions;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import com.othello.controller.OthelloController;

public class ShowMovesActionListener implements ActionListener {

	private OthelloController controller;
	
	public ShowMovesActionListener(OthelloController controller){
		this.controller = controller;
	}
	@Override
	public void actionPerformed(ActionEvent ae) {
		this.controller.toggleShowMoves();
	}
}
