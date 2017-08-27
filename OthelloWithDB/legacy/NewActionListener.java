package com.othello.view.actions;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import com.othello.controller.OthelloController;

//C'est une impl�mentation
//Ne dois d�pendre que d'interfaces
//Ou de classes dans le m�me package ou sous package

public class NewActionListener implements ActionListener {

    private OthelloController controller;

    public NewActionListener(OthelloController controller) {
    	this.controller = controller;
    }

    @Override
    public void actionPerformed(ActionEvent ae) {
    	controller.newGame();
    }

}
