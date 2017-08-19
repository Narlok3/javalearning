package com.othello.view;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;

import com.othello.controller.OthelloController;

public class View implements ActionListener {

    private JFrame ecran;
    private OthelloPanel othelloPanel;

    private final static String TITRE_ECRAN = "Othello";
    private final static int FRAME_WIDTH = 530;
    private final static int FRAME_HEIGHT = 557;
    private final static String ABOUT = "C'est le jeu de l'Othello.";
    private final static String WIN = "Gagn�.";
    private final static String LOSE = "Perdu.";

    public View(OthelloController controller) {
	ecran = new JFrame();
	ecran.setTitle(TITRE_ECRAN);
	ecran.setSize(FRAME_WIDTH, FRAME_HEIGHT);
	ecran.setJMenuBar(new OthelloMenu(controller));
	othelloPanel = new OthelloPanel(controller);
	ecran.setContentPane(othelloPanel);
	ecran.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	// window.setContentPane(content);
	// window.setSize(530,557);
	ecran.setLocation(100, 100);
	ecran.setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
	// TODO Auto-generated method stub

    }
}