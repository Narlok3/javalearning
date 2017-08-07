package com.pendu.ihm.actions;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;

import com.pendu.interfaces.PenduController;

//C'est une impl�mentation
//Ne dois d�pendre que d'interfaces
//Ou de classes dans le m�me package ou sous package

public class LetterActionListener implements ActionListener {
	
	private PenduController controller;

	public LetterActionListener(PenduController controller) {
		this.controller = controller;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		JButton b = ((JButton)e.getSource());
		b.setEnabled(false);
		controller.checkLetter(b.getLabel().charAt(0));
	}

}
