package com.pendu.ihm.actions;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import com.pendu.interfaces.PenduController;

//C'est une impl�mentation
//Ne dois d�pendre que d'interfaces
//Ou de classes dans le m�me package ou sous package

public class NewActionListener implements ActionListener {
	
	private PenduController controller;

	public NewActionListener(PenduController controller) {
		this.controller = controller;
	}

	@Override
	public void actionPerformed(ActionEvent ae) {
		controller.newGame();
	}

}
