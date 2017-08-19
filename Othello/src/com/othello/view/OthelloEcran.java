package com.othello.view;

import javax.swing.JFrame;

import com.othello.controller.OthelloController;

public class OthelloEcran {

    private OthelloController controller;
    private OthelloPanel othelloPanel;
    private JFrame ecran;

    private final static String TITRE_ECRAN = "Othello";
    private final static int FRAME_WIDTH = 1000;
    private final static int FRAME_HEIGHT = 1000;

    public OthelloEcran(OthelloController controller) {
	this.controller = controller;
	ecran = new JFrame();
	OthelloPanel othelloPanel = new OthelloPanel(controller);
	ecran.setTitle(TITRE_ECRAN);
	ecran.setSize(FRAME_WIDTH, FRAME_HEIGHT);
	ecran.setJMenuBar(new OthelloMenu(controller));

	ecran.setContentPane(othelloPanel);
	ecran.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
    }
}
