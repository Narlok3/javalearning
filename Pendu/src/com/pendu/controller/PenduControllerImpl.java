package com.pendu.controller;

import com.pendu.interfaces.PenduController;
import com.pendu.interfaces.PenduEcran;
import com.pendu.interfaces.PenduMoteur;

//C'est une impl�mentation
//Ne dois d�pendre que d'interfaces
//Ou de classes dans le m�me package ou sous package

public class PenduControllerImpl implements PenduController{
	
	private PenduMoteur moteur;
	private PenduEcran ecran;
	
	public PenduControllerImpl(PenduMoteur moteur){
		this.moteur = moteur;
	}
	
	@Override
	public void start(){
		ecran.print();
	}
	
	@Override
	public void newGame(){
		moteur.initialise();
		ecran.printNewGame();
		ecran.updateMot(moteur.getMot());
	}

	@Override
	public void quit(){
		ecran.close();
	}

	@Override
	public void showAbout() {
		ecran.printAbout();
	}

	@Override
	public void setEcran(PenduEcran ecran) {
		this.ecran = ecran;
	}

	@Override
	public void checkLetter(char c) {
		moteur.checkLetter(c);
		ecran.updateMot(moteur.getMot());
		if(moteur.isWin()){
			ecran.printWin();
		}else if(moteur.isLose()){
			ecran.printLose();
		}
	}
}
