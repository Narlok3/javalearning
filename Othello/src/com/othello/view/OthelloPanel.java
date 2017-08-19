package com.othello.view;

import java.awt.Color;
import java.awt.GridLayout;

import javax.swing.JLabel;
import javax.swing.JPanel;

import com.othello.controller.OthelloController;

public class OthelloPanel extends JPanel {

    final static int BLACK = 1; // Declare state of each square
    final static int WHITE = 2;
    private final static int WIDTH = 8;
    private final static int HEIGHT = 8;
    final static int EMPTY = 0;
    private static final long serialVersionUID = 1L;
    private OthelloController controller;
    private JLabel test;
    private Cell[][] cell;

    public OthelloPanel(OthelloController controller) {
	this.controller = controller;
	controller.getModel().initGame(controller.getModel());

	Cell[][] cell = new Cell[8][8];
	setLayout(new GridLayout(8, 8));
	// Add Cells to Grid
	for (int v = 0; v < 8; v++) {
	    for (int h = 0; h < 8; h++) {
		cell[v][h] = new Cell(v, h, this);
		this.add(cell[v][h]);
	    }
	}
	System.out.println("test");
	updatePanels(cell);
    }

    public void updatePanels(Cell[][] cell) {
	for (int i = 1; i < HEIGHT + 1; i++) {
	    for (int j = 1; j < WIDTH + 1; j++) {
		// Draw the discs
		if (controller.getModel().getBoard()[i][j] == BLACK) {
		    System.out.println("Coucou black");
		    cell[i - 1][j - 1].setBackground(Color.black);

		} else if (controller.getModel().getBoard()[i][j] == WHITE) {
		    System.out.println("Coucou white");
		    cell[i - 1][j - 1].setBackground(Color.white);
		}
	    }
	}
    }
}
