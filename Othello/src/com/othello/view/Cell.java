package com.othello.view;

import java.awt.BorderLayout;
import java.awt.Color;

import javax.swing.ImageIcon;
import javax.swing.JPanel;
import javax.swing.border.LineBorder;

public class Cell extends JPanel {

    private int GridRow; // Indicate the row and column of this cell in the
			 // board
    private int GridColumn;
    private ImageLabel pieceLabel;
    private OthelloPanel parent;

    public Cell(int GridRow, int GridColumn, OthelloPanel GUI) {

	super();
	this.GridRow = GridRow;
	this.GridColumn = GridColumn;
	parent = GUI;
	// GridLayout: les bouttons dans ce layout prennent toute la taille
	// disponible
	setLayout(new BorderLayout()); // => boutons fonctionnent tjs (?) + full
				       // size label
	setBorder(new LineBorder(Color.black, 1)); // Set cell's border
	setBackground(Color.green);
	pieceLabel = new ImageLabel("");
	this.add(pieceLabel);
	// this.addMouseListener(new PlayMouseListener(controller));
    }

    public void setPieceIcon(ImageIcon pieceIcon) {
	pieceLabel.setIcon(pieceIcon);
    }

    public int getGridRow() {
	return GridRow;
    }

    public int getGridColumn() {
	return GridColumn;
    }
}
