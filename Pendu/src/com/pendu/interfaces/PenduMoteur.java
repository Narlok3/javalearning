package com.pendu.interfaces;

//C'est une interface
//Pas de dependances

public interface PenduMoteur {

	public void initialise();

	public void checkLetter(Character c);

	public boolean isWin();

	public boolean isLose();

	public String getMot();

}
