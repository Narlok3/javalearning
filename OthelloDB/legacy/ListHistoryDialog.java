package com.othello.view;

import java.awt.GridLayout;

import javax.swing.DefaultListModel;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.ListSelectionModel;

import com.othello.controller.OthelloController;

public class ListHistoryDialog extends JDialog {

    private OthelloController controller;
    private JLabel testLabel;

    public ListHistoryDialog(OthelloController controller, JFrame ecran) {

	super(ecran);
	this.controller = controller;
	setLocationRelativeTo(ecran);

	JPanel panelTest = new JPanel();
	panelTest.setLayout(new GridLayout());

	JList<DefaultListModel<String>> list;
	DefaultListModel<String> listModel;
	listModel = new DefaultListModel<String>();
	listModel.addElement("word1");
	listModel.addElement("word2");
	listModel.addElement("word3");

	// Create the list and put it in a scroll pane.
	list = new JList(listModel);
	list.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
	list.setSelectedIndex(0);
	list.addListSelectionListener(event -> controller.isShowLegalMoves());
	list.setVisibleRowCount(5);
	JScrollPane listScrollPane = new JScrollPane(list);
	panelTest.add(listScrollPane);

	testLabel = new JLabel("Coucou panel ");
	panelTest.add(testLabel);

	setContentPane(panelTest);
	pack();
	setLocation(ecran.getWidth() / 2 - getWidth() / 2, ecran.getHeight()
		/ 2 - getHeight() / 2);

    }
}
