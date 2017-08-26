package com.othello.view;

import java.awt.BorderLayout;
import java.awt.GridLayout;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import com.othello.controller.OthelloController;
import com.othello.interfaces.IOthelloView;
import com.othello.util.OthelloConstants.Turn;

public class OthelloView implements IOthelloView {

    private JFrame ecran;
    private OthelloPanel othelloPanel;
    private OthelloController controller;
    private JPanel miscPanel;
    private JLabel userTurn;
    private JLabel scoreBoard;

    private final static String TITRE_ECRAN = "Othello";
    private final static int FRAME_WIDTH = 530;
    private final static int FRAME_HEIGHT = 557;
    private final static String ABOUT = "C'est le jeu de l'Othello.";

    public OthelloView(OthelloController controller) {
	this.controller = controller;
	ecran = new JFrame();

	miscPanel = new JPanel();
	userTurn = new JLabel();
	scoreBoard = new JLabel();
	othelloPanel = new OthelloPanel(controller);

	miscPanel.setLayout(new GridLayout(1, 2));
	miscPanel.add(userTurn);
	miscPanel.add(scoreBoard);

	userTurn.setHorizontalAlignment(JLabel.CENTER);
	scoreBoard.setHorizontalAlignment(JLabel.CENTER);

	ecran.setTitle(TITRE_ECRAN);
	ecran.setSize(FRAME_WIDTH, FRAME_HEIGHT);
	ecran.setJMenuBar(new OthelloMenu(controller));

	ecran.add(othelloPanel, BorderLayout.CENTER); // othello at the center
	ecran.add(miscPanel, BorderLayout.PAGE_END); // misc at the bottom
	// ecran.setContentPane(othelloPanel); //previous way of doing
	ecran.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	ecran.setLocation(100, 100);
    }

    @Override
    public void refreshView() {
	othelloPanel.updatePanels(controller.isShowLegalMoves());
	if (controller.getModel().getTurn() == Turn.BLACK) {
	    userTurn.setText("Au tour de Noir");
	}
	if (controller.getModel().getTurn() == Turn.WHITE) {
	    userTurn.setText("Au tour de Blanc");
	}
	scoreBoard
		.setText("Score : (Noir)" + controller.getScore() + "(Blanc)");
    }

    @Override
    public void print() {
	ecran.setVisible(true);
	switch (JOptionPane.showConfirmDialog(ecran,
		"Voulez vous faire une partie ?", "Bienvenue",
		JOptionPane.YES_NO_OPTION)) {
	case 0:
	    controller.newGame();
	    break;
	case 1:
	    controller.quit();
	    break;
	}
    }

    @Override
    public void printNewGame() {
	// currently never called?
	controller.newGame();
    }

    @Override
    public void close() {
	ecran.dispose();
    }

    @Override
    public void printAbout() {
	JOptionPane.showMessageDialog(ecran, ABOUT);
    }

    @Override
    public void printEndGame() {
	userTurn.setText("Fin de la partie");
	JOptionPane.showMessageDialog(
		ecran,
		"Fin de la partie, score final : (Noir)"
			+ controller.getScore() + " (Blanc)");
	switch (JOptionPane.showConfirmDialog(ecran, "Voulez vous rejouer ?",
		"Fin de la partie", JOptionPane.YES_NO_CANCEL_OPTION)) {
	case 0:
	    controller.newGame();
	    break;
	case 1:
	    controller.quit();
	    break;
	case 2:
	    break;
	}
    }
}