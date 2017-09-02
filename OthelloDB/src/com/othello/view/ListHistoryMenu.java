package com.othello.view;

import java.awt.GridLayout;
import java.util.List;
import java.util.Map;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import com.othello.controller.OthelloController;

public class ListHistoryMenu {
    private OthelloController controller;
    private JFrame parent;

    public ListHistoryMenu(OthelloController controller, JFrame parent) {
	this.controller = controller;
	this.parent = parent;

    }

    public void showHistoryMenu(List<Map<String, String>> gamesHistory) {

	Object columns[] = { "Date", "Score", "Turn", "Reference" };
	DefaultTableModel tableModel = new DefaultTableModel(columns,
		gamesHistory.size());
	JTable table = new JTable(tableModel);

	for (int i = 0; i < gamesHistory.size(); i++) {
	    for (int j = 0; j < columns.length; j++) {
		table.setValueAt(gamesHistory.get(i).get(columns[j]), i, j);
	    }
	}

	JPanel listPanel = new JPanel();
	listPanel.setLayout(new GridLayout());
	JScrollPane scrollPane = new JScrollPane(table);
	listPanel.add(scrollPane);

	switch (JOptionPane.showConfirmDialog(parent, listPanel,
		"Historique des parties", JOptionPane.OK_CANCEL_OPTION)) {
	case 0:
	    System.out.println("Retrieving game");
	    controller.loadGame(gamesHistory.get(table.getSelectedRow()).get(
		    "Reference"));
	    break;
	case 1:
	    System.out.println("Cancelled");
	    break;
	}
    }
}
