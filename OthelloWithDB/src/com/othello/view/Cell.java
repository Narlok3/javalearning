package com.othello.view;

import java.awt.BorderLayout;
import java.awt.Color;

import javax.swing.ImageIcon;
import javax.swing.JPanel;
import javax.swing.border.LineBorder;

import com.othello.util.OthelloConstants;

public class Cell extends JPanel {

    private int GridRow;
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
	setBackground(OthelloConstants.backGround);
	pieceLabel = new ImageLabel("");
	this.add(pieceLabel);
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
