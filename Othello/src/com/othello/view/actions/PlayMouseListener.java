package com.othello.view.actions;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import com.othello.controller.OthelloController;
import com.othello.view.Cell;

public class PlayMouseListener implements MouseListener {

	private OthelloController controller;
	
	public PlayMouseListener(OthelloController controller){
		this.controller = controller;
	}
	@Override
	public void mouseClicked(MouseEvent me) {
		Cell c = (Cell) me.getSource();
		controller.playTurn(c.getGridRow(), c.getGridColumn());
	}

	@Override
	public void mouseEntered(MouseEvent arg0) {

	}

	@Override
	public void mouseExited(MouseEvent arg0) {

	}

	@Override
	public void mousePressed(MouseEvent arg0) {
	
	}

	@Override
	public void mouseReleased(MouseEvent arg0) {
		
	}

}
