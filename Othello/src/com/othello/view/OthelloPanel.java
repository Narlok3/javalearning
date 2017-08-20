package com.othello.view;

import java.awt.Color;
import java.awt.GridLayout;

import javax.swing.JButton;
import javax.swing.JPanel;

import com.othello.controller.OthelloController;
import com.othello.util.OthelloConstants;
import com.othello.view.actions.PlayActionListener;

public class OthelloPanel extends JPanel {

    private static final long serialVersionUID = 1L;
    private OthelloController controller;
    private Cell[][] cell;

    public OthelloPanel(OthelloController controller) {
    	this.controller = controller;
    	controller.getModel().initGame(controller.getModel());
    	setLayout(new GridLayout(8, 8));

    	Cell[][] cell = new Cell[8][8];
    	
    	// Add Cells to Grid
    	for (Integer v = 0; v < OthelloConstants.HEIGHT ; v++) {
    		for (Integer h = 0; h < OthelloConstants.WIDTH ; h++) {
    			cell[v][h] = new Cell(v, h, this);
    			JButton b = new JButton();
    			//concatenate the button coordinates to pass them to the eventlistener
    			//surely there is a better way to do that?
    			b.setActionCommand(v.toString()+h.toString());
    			//rend le boutton invisible, mais clickable
    			b.setOpaque(false);
    			b.setContentAreaFilled(false);
    			b.setBorderPainted(false);
    			b.addActionListener(new PlayActionListener(controller));
    			cell[v][h].add(b);
    			this.add(cell[v][h]);
    		}
    	}
    	System.out.println("test");
    	setCell(cell); //there is something I don't understand about array scopes...
    }

    public void updatePanels(Cell[][] cell, boolean showLegalMoves) {
    	for (int i = 0; i < OthelloConstants.HEIGHT ; i++) {
    		for (int j = 0; j < OthelloConstants.WIDTH ; j++) {
    			// Draw the discs
    			if (controller.getModel().getBoard()[i][j] == OthelloConstants.BLACK) {
    				cell[i][j].setBackground(Color.black); //to replace with an image
    			} else if (controller.getModel().getBoard()[i][j] == OthelloConstants.WHITE) {
    				cell[i][j].setBackground(Color.white); //to replace with an image
    			} else if (controller.getModel().getBoard()[i][j] == OthelloConstants.EMPTY){
    				cell[i][j].setBackground(Color.green);
    				if(showLegalMoves && controller.getModel().legalMove(i, j, controller.getModel().getTurn(), false)){
    					cell[i][j].setBackground(Color.gray);
    				}
    			}
    		}
    	}
    }

	public Cell[][] getCell() {
		return this.cell;
	}

	public void setCell(Cell[][] cell) {
		this.cell = cell;
	}
}
