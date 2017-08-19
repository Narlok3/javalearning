package com.othello.view;

import java.awt.Color;
import java.awt.GridLayout;

import javax.swing.JPanel;
import javax.swing.border.LineBorder;

public class Cell extends JPanel {

	// Indicate the row and column of this cell in the board
	private int GridRow;
	private int GridColumn;

	private OthelloPanel parent;

	public Cell(int GridRow, int GridColumn, OthelloPanel GUI) {

		super();
		this.GridRow = GridRow;
		this.GridColumn = GridColumn;
		parent = GUI;
		//idea for go : no line border, try to draw lines in the panel
		setLayout(new GridLayout()); // => les bouttons dans ce layout prennent
										// toute la taille disponible
		setBorder(new LineBorder(Color.black, 1)); // Set cell's border
		setBackground(Color.green);
	}
}
