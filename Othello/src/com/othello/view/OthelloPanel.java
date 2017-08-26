package com.othello.view;

import java.awt.Color;
import java.awt.GridLayout;

import javax.swing.ImageIcon;
import javax.swing.JPanel;

import com.othello.controller.OthelloController;
import com.othello.util.OthelloConstants;
import com.othello.view.actions.PlayMouseListener;

public class OthelloPanel extends JPanel {

    private static final long serialVersionUID = 1L;
    private OthelloController controller;
    private Cell[][] cell;

    private ImageIcon blackStone;
    private ImageIcon whiteStone;

    public OthelloPanel(OthelloController controller) {

	this.controller = controller;
	setLayout(new GridLayout(8, 8));
	blackStone = new ImageIcon("assets/BlackStone.png");
	whiteStone = new ImageIcon("assets/WhiteStone.png");

	cell = new Cell[8][8];

	// Add Cells to Grid
	for (Integer v = 0; v < OthelloConstants.HEIGHT; v++) {
	    for (Integer h = 0; h < OthelloConstants.WIDTH; h++) {
		cell[v][h] = new Cell(v, h, this);
		cell[v][h].addMouseListener(new PlayMouseListener(controller)); // Cell
		this.add(cell[v][h]);
	    }
	}
    }

    public void updatePanels(boolean showLegalMoves) {
	for (int i = 0; i < OthelloConstants.HEIGHT; i++) {
	    for (int j = 0; j < OthelloConstants.WIDTH; j++) {
		switch (controller.getModel().getBoard()[i][j]) {
		case BLACK: // use an observer instead of fetching the info?
		    cell[i][j].setPieceIcon(blackStone);
		    break;
		case WHITE:
		    cell[i][j].setPieceIcon(whiteStone);
		    break;
		case EMPTY:
		    cell[i][j].setPieceIcon(null);
		    cell[i][j].setBackground(OthelloConstants.backGround);
		    if (showLegalMoves
			    && controller.getModel().legalMove(i, j, false)) {
			cell[i][j].setBackground(Color.gray);
		    }
		    break;
		}
	    }
	}
    }
}
