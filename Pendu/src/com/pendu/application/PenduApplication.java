package com.pendu.application;

import com.pendu.controller.PenduControllerImpl;
import com.pendu.ihm.PenduEcranSwing;
import com.pendu.interfaces.PenduController;
import com.pendu.moteur.PenduMoteurImpl;

public class PenduApplication {

	public static void main(String[] args) {
		PenduController controller = new PenduControllerImpl(new PenduMoteurImpl());
		controller.setEcran(new PenduEcranSwing(controller));
		controller.start();
	}

}
