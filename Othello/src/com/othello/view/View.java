package com.othello.view;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.LayoutManager;

import javax.swing.BoxLayout;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import com.othello.controller.OthelloController;
import com.othello.interfaces.IView;
import com.othello.util.OthelloConstants;

public class View implements IView {

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

    public View(OthelloController controller) {
    	this.controller = controller;
    	ecran = new JFrame();
    	
    	miscPanel = new JPanel();
    	userTurn = new JLabel();
    	scoreBoard = new JLabel();
    	miscPanel.add(userTurn);
		miscPanel.add(scoreBoard);
    	ecran.setTitle(TITRE_ECRAN);
    	ecran.setSize(FRAME_WIDTH, FRAME_HEIGHT);
		ecran.setJMenuBar(new OthelloMenu(controller));
		othelloPanel = new OthelloPanel(controller);

		ecran.add(othelloPanel,BorderLayout.CENTER); //othello at the center
		ecran.add(miscPanel,BorderLayout.PAGE_END); //misc at the bottom 
		//ecran.setContentPane(othelloPanel); //previous way of doing
		ecran.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		ecran.setLocation(100, 100);		
    }
    
    public void refreshView(){
    	othelloPanel.updatePanels(othelloPanel.getCell(), controller.isShowLegalMoves());
    	if(controller.getModel().getTurn()==OthelloConstants.BLACK)
    		userTurn.setText("Au tour de Noir");
    	if(controller.getModel().getTurn()==OthelloConstants.WHITE)
    		userTurn.setText("Au tour de Blanc");
    	scoreBoard.setText("Score : (Noir)" + controller.getScore() + "(Blanc)");
    }


	public void print() {
		ecran.setVisible(true);
	}


	public void printNewGame() {
		controller.newGame();
	}


	public void close() {
		ecran.dispose();
	}


	public void printAbout() {
		JOptionPane.showMessageDialog(ecran, ABOUT);	
	}


	public void printEndGame() {
		userTurn.setText("Fin de la partie");
		JOptionPane.showMessageDialog(ecran, "Fin de la partie, score final : (Noir)" + controller.getScore() + " (Blanc)");
	}

}